/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json.impl;

import io.codecastle.scriptorium.json.JsonArrayInscription;
import io.codecastle.scriptorium.json.JsonArrayNode;
import java.io.IOException;

/**
 *
 * @author Doug Valenta
 */
final class JsonArrayInscriptionElement extends AbstractArrayElement<JsonArrayNode<Object>> implements JsonArrayInscription<Object> {

	JsonArrayInscriptionElement(final JsonFactory.Host host) {
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
