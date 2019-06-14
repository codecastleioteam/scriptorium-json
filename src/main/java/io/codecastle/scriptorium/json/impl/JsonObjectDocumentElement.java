/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json.impl;

import io.codecastle.scriptorium.json.JsonObjectDocument;
import java.io.IOException;

/**
 *
 * @author Doug Valenta
 */
final class JsonObjectDocumentElement extends AbstractObjectElement<JsonObjectDocument> implements JsonObjectDocument {

	JsonObjectDocumentElement(final JsonFactory.Host host) {
		super(host);
	}

	@Override
	public void close() throws IOException {
		host.close();
	}
	
}
