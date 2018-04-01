/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import net.dougvalenta.scriptorium.function.IOBiConsumer;
import net.dougvalenta.scriptorium.function.IOConsumer;
import net.dougvalenta.scriptorium.json.scribe.JsonScribe;

/**
 *
 * @author Doug Valenta
 */
abstract class AbstractJsonArray<THIS extends AbstractJsonArray<THIS>> implements JsonArray<THIS> {

	final JsonScribe scribe;
	final int cursor;
	
	AbstractJsonArray(final JsonScribe scribe) {
		this.scribe = scribe;
		this.cursor = scribe.getCursor();
	}

	@Override
	public THIS withNull() throws IOException {
		scribe.nullValue();
		return (THIS) this;
	}

	@Override
	public THIS withTrue() throws IOException {
		scribe.trueValue();
		return (THIS) this;
	}

	@Override
	public THIS withFalse() throws IOException {
		scribe.falseValue();
		return (THIS) this;
	}

	@Override
	public THIS with(final CharSequence element) throws IOException {
		if (element == null) return withNull();
		scribe.value(element);
		return (THIS) this;
	}

	@Override
	public THIS with(final char element) throws IOException {
		scribe.value(element);
		return (THIS) this;
	}

	@Override
	public THIS with(final BigInteger element) throws IOException {
		if (element == null) return withNull();
		scribe.value(element);
		return (THIS) this;
	}

	@Override
	public THIS with(final BigDecimal element) throws IOException {
		if (element == null) return withNull();
		scribe.value(element);
		return (THIS) this;
	}

	@Override
	public THIS with(final int element) throws IOException {
		scribe.value(element);
		return (THIS) this;
	}

	@Override
	public THIS with(final float element) throws IOException {
		scribe.value(element);
		return (THIS) this;
	}

	@Override
	public THIS with(final long element) throws IOException {
		scribe.value(element);
		return (THIS) this;
	}

	@Override
	public THIS with(final double element) throws IOException {
		scribe.value(element);
		return (THIS) this;
	}

	@Override
	public THIS with(final boolean element) throws IOException {
		scribe.value(element);
		return (THIS) this;
	}

	@Override
	public THIS withAll(final Object... elements) throws IOException {
		if (elements == null) return (THIS) this;
		for (Object element : elements) {
			scribe.value(element);
		}
		return (THIS) this;
	}

	@Override
	public THIS withAll(final Iterable<?> elements) throws IOException {
		if (elements == null) return (THIS) this;
		for (Object element : elements) {
			scribe.value(element);
		}
		return (THIS) this;
	}

	@Override
	public THIS withEmptyArray() throws IOException {
		scribe.emptyArray();
		return (THIS) this;
	}

	@Override
	public THIS withEmptyObject() throws IOException {
		scribe.emptyObject();
		return (THIS) this;
	}

	@Override
	public JsonValue<THIS> element() throws IOException {
		scribe.pushValue();
		return new JsonValue<>(scribe, (THIS) this);
	}

	@Override
	public JsonValue<THIS> element(final CharSequence element) throws IOException {
		scribe.pushValue();
		if (element != null) scribe.append(element);
		return new JsonValue<>(scribe, (THIS) this);
	}

	@Override
	public JsonValue<THIS> element(final char element) throws IOException {
		scribe.pushValue().append(element);
		return new JsonValue<>(scribe, (THIS) this);
	}

	@Override
	public JsonArrayNode<THIS> array() throws IOException {
		scribe.pushArray();
		return new JsonArrayNode<>(scribe, (THIS) this);
	}

	@Override
	public JsonObjectNode<THIS> object() throws IOException {
		scribe.pushObject();
		return new JsonObjectNode<>(scribe, (THIS) this);
	}

	@Override
	public THIS with(final IOConsumer<? super JsonArray<?>> consumer) throws IOException {
		consumer.accept(new InscribedJsonArray(scribe));
		scribe.pop(cursor);
		return (THIS) this;
	}

	@Override
	public <T> THIS with(final T element, final IOBiConsumer<? super T, ? super JsonArray<?>> biConsumer) throws IOException {
		biConsumer.accept(element, new InscribedJsonArray(scribe));
		scribe.pop(cursor);
		return (THIS) this;
	}
	
}
