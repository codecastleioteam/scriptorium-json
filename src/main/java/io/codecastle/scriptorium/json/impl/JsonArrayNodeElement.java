/*
 * Copyright 2019 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json.impl;

import io.codecastle.scriptorium.json.JsonArrayNode;
import java.io.IOException;

/**
 *
 * @author Doug Valenta
 */
final class JsonArrayNodeElement extends AbstractArrayElement<JsonArrayNode<Object>> implements JsonArrayNode<Object> {
	
	JsonArrayNodeElement(JsonFactory.Host host) {
		super(host);
	}

	@Override
	public Object then() throws IOException {
		host.scribe.pop();
		return host.stack.pop();
	}
	
}
