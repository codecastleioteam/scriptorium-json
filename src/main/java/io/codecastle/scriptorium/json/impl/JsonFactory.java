/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json.impl;

import io.codecastle.scriptorium.json.JsonArrayDocument;
import io.codecastle.scriptorium.json.JsonArrayInscription;
import io.codecastle.scriptorium.json.JsonObjectDocument;
import io.codecastle.scriptorium.json.JsonObjectInscription;
import io.codecastle.scriptorium.json.scribe.JsonScribe;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author Doug Valenta
 */
public class JsonFactory {
	
	private static final JsonFactory INSTANCE = new JsonFactory();
	private static final int MAX_POOL_SIZE = 8;
	
	public static JsonObjectDocument object(final JsonScribe scribe) throws IOException {
		final Host host = INSTANCE.getHost();
		host.scribe = scribe.pushObject();
		return host.jsonObjectDocumentElement;
	}
	
	public static JsonArrayDocument array(final JsonScribe scribe) throws IOException {
		final Host host = INSTANCE.getHost();
		host.scribe = scribe.pushArray();
		return host.jsonArrayDocumentElement;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> JsonObjectInscription<T> inscribeObject(final JsonScribe scribe, final T inscribed) throws IOException {
		final Host host = INSTANCE.getHost();
		host.scribe = scribe.pushObject();
		host.stack.push(inscribed);
		return (JsonObjectInscription<T>) (Object) host.jsonObjectInscriptionElement;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> JsonArrayInscription<T> inscribeArray(final JsonScribe scribe, final T inscribed) throws IOException {
		final Host host = INSTANCE.getHost();
		host.scribe = scribe.pushArray();
		host.stack.push(inscribed);
		return (JsonArrayInscription<T>) (Object) host.jsonArrayInscriptionElement;
	}
	
	private final Queue<Host> hosts = new ConcurrentLinkedQueue<>();
	private volatile int poolSize = 0;
	
	private JsonFactory() {}
	
	private Host getHost() {
		Host host = hosts.poll();
		if (host == null) {
			return new Host();
		}
		poolSize--;
		return host;
	}
	
	private void releaseHost(final Host host) {
		if (poolSize < MAX_POOL_SIZE) {
			host.scribe = null;
			host.stack.clear();
			hosts.add(host);
			poolSize++;
		}
	}

	/**
	 *
	 * @author Doug Valenta
	 */
	final class Host {

		final Deque<Object> stack = new ArrayDeque<>();
		final InscribedJsonAppendable inscribedJsonAppendable = new InscribedJsonAppendable(this);
		final InscribedJsonObject inscribedJsonObject = new InscribedJsonObject(this);
		final InscribedJsonArray inscribedJsonArray = new InscribedJsonArray(this);
		final JsonValueElement jsonValueElement = new JsonValueElement(this);
		final JsonKeyElement jsonKeyElement = new JsonKeyElement(this);
		final JsonObjectNodeElement jsonObjectNodeElement = new JsonObjectNodeElement(this);
		final JsonArrayNodeElement jsonArrayNodeElement = new JsonArrayNodeElement(this);
		final JsonObjectDocumentElement jsonObjectDocumentElement = new JsonObjectDocumentElement(this);
		final JsonArrayDocumentElement jsonArrayDocumentElement = new JsonArrayDocumentElement(this);
		final JsonObjectInscriptionElement jsonObjectInscriptionElement = new JsonObjectInscriptionElement(this);
		final JsonArrayInscriptionElement jsonArrayInscriptionElement = new JsonArrayInscriptionElement(this);
		
		JsonScribe scribe;

		void close() throws IOException {
			scribe.close();
			releaseHost(this);
		}
		
		Object then() throws IOException {
			scribe.close();
			final Object result = stack.pop();
			releaseHost(this);
			return result;
		}
		
	}
	
}
