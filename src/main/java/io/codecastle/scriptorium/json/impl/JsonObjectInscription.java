/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json.impl;

import io.codecastle.scriptorium.json.JsonObject;
import io.codecastle.scriptorium.json.JsonObjectNode;
import java.io.IOException;

/**
 *
 * @author Doug Valenta
 */
final class JsonObjectInscription extends AbstractObjectElement<JsonObjectNode<Object>> implements JsonObjectNode<Object> {

	JsonObjectInscription(JsonFactory.Host host) {
		super(host);
	}

	@Override
	public Object then() throws IOException {
		return host.then();
	}

	@Override
	public void close() throws IOException {
		host.close();
	}
	
}
