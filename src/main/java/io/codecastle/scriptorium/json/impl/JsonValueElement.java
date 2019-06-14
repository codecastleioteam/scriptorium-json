/*
 * Copyright 2019 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json.impl;

import io.codecastle.scriptorium.json.JsonValue;
import java.io.IOException;

/**
 *
 * @author Doug Valenta
 */
final class JsonValueElement extends AbstractAppendableElement<JsonValue<Object>> implements JsonValue<Object> {

	JsonValueElement(final JsonFactory.Host host) {
		super(host);
	}

	@Override
	public Object then() throws IOException {
		host.scribe.pop();
		return host.stack.pop();
	}

	@Override
	public void close() throws IOException {
		host.close();
	}

}
