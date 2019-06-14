/*
 * Copyright 2019 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json.impl;

import io.codecastle.scriptorium.json.JsonArrayNode;
import io.codecastle.scriptorium.json.JsonKey;
import io.codecastle.scriptorium.json.JsonObjectNode;
import io.codecastle.scriptorium.json.JsonValue;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author Doug Valenta
 */
final class JsonKeyElement extends AbstractAppendableElement<JsonKey<Object>> implements JsonKey<Object> {

	JsonKeyElement(final JsonFactory.Host host) {
		super(host);
	}

	@Override
	public JsonValue<Object> value() throws IOException {
		host.scribe.pop().pushValue();
		return host.jsonValueElement;
	}

	@Override
	public JsonValue<Object> value(final char value) throws IOException {
		host.scribe.pop().pushValue().append(value);
		return host.jsonValueElement;
	}

	@Override
	public JsonValue<Object> value(final CharSequence value) throws IOException {
		host.scribe.pop().pushValue().append(value);
		return host.jsonValueElement;
	}

	@Override
	public Object thenNull() throws IOException {
		host.scribe.pop().nullValue();
		return host.stack.pop();
	}

	@Override
	public Object thenTrue() throws IOException {
		host.scribe.pop().trueValue();
		return host.stack.pop();
	}

	@Override
	public Object thenFalse() throws IOException {
		host.scribe.pop().falseValue();
		return host.stack.pop();
	}

	@Override
	public Object thenEmptyArray() throws IOException {
		host.scribe.pop().emptyArray();
		return host.stack.pop();
	}

	@Override
	public Object thenEmptyObject() throws IOException {
		host.scribe.pop().emptyObject();
		return host.stack.pop();
	}

	@Override
	public Object then(final CharSequence value) throws IOException {
		if (value == null) return thenNull();
		host.scribe.pop().value(value);
		return host.stack.pop();
	}

	@Override
	public Object then(final char value) throws IOException {
		host.scribe.pop().value(value);
		return host.stack.pop();
	}

	@Override
	public Object then(final BigInteger value) throws IOException {
		if (value == null) return thenNull();
		host.scribe.pop().value(value);
		return host.stack.pop();
	}

	@Override
	public Object then(final BigDecimal value) throws IOException {
		if (value == null) return thenNull();
		host.scribe.pop().value(value);
		return host.stack.pop();
	}

	@Override
	public Object then(final int value) throws IOException {
		host.scribe.pop().value(value);
		return host.stack.pop();
	}

	@Override
	public Object then(final float value) throws IOException {
		host.scribe.pop().value(value);
		return host.stack.pop();
	}

	@Override
	public Object then(final long value) throws IOException {
		host.scribe.pop().value(value);
		return host.stack.pop();
	}

	@Override
	public Object then(final double value) throws IOException {
		host.scribe.pop().value(value);
		return host.stack.pop();
	}

	@Override
	public Object then(final boolean value) throws IOException {
		host.scribe.pop().value(value);
		return host.stack.pop();
	}

	@Override
	public JsonObjectNode<Object> object() throws IOException {
		host.scribe.pop().pushObject();
		return host.jsonObjectNodeElement;
	}

	@Override
	public JsonArrayNode<Object> array() throws IOException {
		host.scribe.pop().pushArray();
		return host.jsonArrayNodeElement;
	}
	
}
