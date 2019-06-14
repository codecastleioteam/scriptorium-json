/*
 * Copyright 2019 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json.impl;

import io.codecastle.scriptorium.function.IOBiConsumer;
import io.codecastle.scriptorium.function.IOConsumer;
import io.codecastle.scriptorium.json.JsonArray;
import io.codecastle.scriptorium.json.JsonArrayNode;
import io.codecastle.scriptorium.json.JsonObjectNode;
import io.codecastle.scriptorium.json.JsonValue;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author Doug Valenta
 */
abstract class AbstractArrayElement<THIS extends JsonArray<THIS>> extends AbstractElement<JsonArray<?>, THIS> implements JsonArray<THIS> {

	AbstractArrayElement(JsonFactory.Host host) {
		super(host);
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS withNull() throws IOException {
		host.scribe.nullValue();
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS withTrue() throws IOException {
		host.scribe.trueValue();
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS withFalse() throws IOException {
		host.scribe.falseValue();
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS with(final CharSequence element) throws IOException {
		if (element == null) return withNull();
		host.scribe.value(element);
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS with(final char element) throws IOException {
		host.scribe.value(element);
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS with(final BigInteger element) throws IOException {
		if (element == null) return withNull();
		host.scribe.value(element);
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS with(final BigDecimal element) throws IOException {
		if (element == null) return withNull();
		host.scribe.value(element);
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS with(final int element) throws IOException {
		host.scribe.value(element);
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS with(final float element) throws IOException {
		host.scribe.value(element);
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS with(final long element) throws IOException {
		host.scribe.value(element);
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS with(final double element) throws IOException {
		host.scribe.value(element);
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS with(final boolean element) throws IOException {
		host.scribe.value(element);
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS withAll(final Object... elements) throws IOException {
		if (elements != null) {
			for (Object element : elements) {
				host.scribe.value(element);
			}
		}
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS withAll(final Iterable<?> elements) throws IOException {
		if (elements != null) {
			for (Object element : elements) {
				host.scribe.value(element);
			}
		}
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS withEmptyArray() throws IOException {
		host.scribe.emptyArray();
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS withEmptyObject() throws IOException {
		host.scribe.emptyObject();
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public JsonValue<THIS> element() throws IOException {
		host.stack.push(this);
		host.scribe.pushValue();
		return (JsonValue<THIS>) (Object) host.jsonValueElement;
	}

	@Override
	@SuppressWarnings("unchecked")
	public JsonValue<THIS> element(final CharSequence element) throws IOException {
		host.stack.push(this);
		host.scribe.pushValue();
		if (element != null) host.scribe.append(element);
		return (JsonValue<THIS>) (Object) host.jsonValueElement;
	}

	@Override
	@SuppressWarnings("unchecked")
	public JsonValue<THIS> element(final char element) throws IOException {
		host.stack.push(this);
		host.scribe.pushValue().append(element);
		return (JsonValue<THIS>) (Object) host.jsonValueElement;
	}

	@Override
	@SuppressWarnings("unchecked")
	public JsonArrayNode<THIS> array() throws IOException {
		host.scribe.pushArray();
		host.stack.push(this);
		return (JsonArrayNode<THIS>) (Object) host.jsonArrayNodeElement;
	}

	@Override
	@SuppressWarnings("unchecked")
	public JsonObjectNode<THIS> object() throws IOException {
		host.scribe.pushObject();
		host.stack.push(this);
		return (JsonObjectNode<THIS>) (Object) host.jsonObjectNodeElement;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS with(final IOConsumer<? super JsonArray<?>> consumer) throws IOException {
		
		int scribeCursor = host.scribe.getCursor();
		int stackCursor = host.stack.size();
		
		consumer.accept(host.inscribedJsonArray);
		
		host.scribe.pop(scribeCursor);
		while (host.stack.size() > stackCursor) {
			host.stack.pop();
		}
		
		return (THIS) this;
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> THIS with(final T element, final IOBiConsumer<? super T, ? super JsonArray<?>> biConsumer) throws IOException {
		
		int scribeCursor = host.scribe.getCursor();
		int stackCursor = host.stack.size();
		
		biConsumer.accept(element, host.inscribedJsonArray);
		
		host.scribe.pop(scribeCursor);
		while (host.stack.size() > stackCursor) {
			host.stack.pop();
		}
		
		return (THIS) this;
		
	}
	
}
