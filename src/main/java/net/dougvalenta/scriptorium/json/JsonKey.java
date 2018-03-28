/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import net.dougvalenta.scriptorium.json.scribe.JsonScribe;

/**
 *
 * @author Doug Valenta
 */
public class JsonKey<P> extends AbstractJsonAppendable<JsonKey<P>> {

	private final P parent;
	
	JsonKey(final JsonScribe scribe, final P parent) {
		super(scribe);
		this.parent = parent;
	}

	public JsonValue<P> value() throws IOException {
		scribe.pop().pushValue();
		return new JsonValue(scribe, parent);
	}
	
	public JsonValue<P> value(final char value) throws IOException {
		scribe.pop().pushValue().append(value);
		return new JsonValue(scribe, parent);
	}
	
	public JsonValue<P> value(final Character value) throws IOException {
		if (value == null) return value();
		return value((char) value);
	}
	
	public JsonValue<P> value(final CharSequence value) throws IOException {
		scribe.pop().pushValue();
		if (value != null) scribe.append(value);
		return new JsonValue(scribe, parent);
	}
	
	public P thenNull() throws IOException {
		scribe.pop().nullValue();
		return parent;
	}
	
	public P thenTrue() throws IOException {
		scribe.pop().trueValue();
		return parent;
	}
	
	public P thenFalse() throws IOException {
		scribe.pop().falseValue();
		return parent;
	}
	
	public P then(final CharSequence value) throws IOException {
		if (value == null) return thenNull();
		scribe.pop().value(value);
		return parent;
	}
	
	public P then(final char value) throws IOException {
		scribe.pop().value(value);
		return parent;
	}
	
	public P then(final Character value) throws IOException {
		if (value == null) return thenNull();
		return then((char) value);
	}
	
	public P then(final BigInteger value) throws IOException {
		scribe.pop().value(value);
		return parent;
	}
	
	public P then(final BigDecimal value) throws IOException {
		scribe.pop().value(value);
		return parent;
	}
	
	public P then(final Byte value) throws IOException {
		if (value == null) return thenNull();
		return then((int) value);
	}
	
	public P then(final Short value) throws IOException {
		if (value == null) return thenNull();
		return then((int) value);
	}
	
	public P then(final int value) throws IOException {
		scribe.pop().value(value);
		return parent;
	}
	
	public P then(final Integer value) throws IOException {
		if (value == null) return thenNull();
		return then((int) value);
	}
	
	public P then(final float value) throws IOException {
		scribe.pop().value(value);
		return parent;
	}
	
	public P then(final Float value) throws IOException {
		if (value == null) return thenNull();
		return then((float) value);
	}
	
	public P then(final long value) throws IOException {
		scribe.pop().value(value);
		return parent;
	}
	
	public P then(final Long value) throws IOException {
		if (value == null) return thenNull();
		return then((long) value);
	}
	
	public P then(final double value) throws IOException {
		scribe.pop().value(value);
		return parent;
	}
	
	public P then(final Double value) throws IOException {
		if (value == null) return thenNull();
		return then((double) value);
	}
	
	public P then(final boolean value) throws IOException {
		scribe.pop().value(value);
		return parent;
	}
	
	public P then(final Boolean value) throws IOException {
		if (value == null) return thenNull();
		return then((boolean) value);
	}
	
	public JsonObjectNode<P> object() throws IOException {
		scribe.pop().pushObject();
		return new JsonObjectNode(scribe, parent);
	}
	
	public JsonArrayNode<P> array() throws IOException {
		scribe.pop().pushArray();
		return new JsonArrayNode(scribe, parent);
	}
	
}
