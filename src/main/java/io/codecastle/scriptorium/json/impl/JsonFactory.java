/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json.impl;

import io.codecastle.scriptorium.json.JsonArrayDocument;
import io.codecastle.scriptorium.json.JsonArrayNode;
import io.codecastle.scriptorium.json.JsonObjectDocument;
import io.codecastle.scriptorium.json.JsonObjectNode;
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
	public static <T> JsonObjectNode<T> inscribeObject(final JsonScribe scribe, final T inscribed) throws IOException {
		final Host host = INSTANCE.getHost();
		host.scribe = scribe.pushObject();
		host.stack.push(inscribed);
		return (JsonObjectNode<T>) (Object) host.jsonObjectNodeElement;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> JsonArrayNode<T> inscribeArray(final JsonScribe scribe, final T inscribed) throws IOException {
		final Host host = INSTANCE.getHost();
		host.scribe = scribe.pushArray();
		host.stack.push(inscribed);
		return (JsonArrayNode<T>) (Object) host.jsonArrayNodeElement;
	}
	
	private final Queue<Host> hosts = new ConcurrentLinkedQueue<>();
	private volatile int poolSize = 0;
	
	private JsonFactory() {}
	
	private Host getHost() {
		if (hosts.isEmpty()) {
			return new Host();
		}
		return hosts.remove();
	}
	
	private void releaseHost(final Host host) {
		if (poolSize < MAX_POOL_SIZE) {
			host.scribe = null;
			hosts.add(host);
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
		
		JsonScribe scribe;

		void close() throws IOException {
			scribe.close();
			stack.clear();
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
