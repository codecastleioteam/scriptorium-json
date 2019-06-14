/*
 * Copyright 2019 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json.impl;

import io.codecastle.scriptorium.function.IOBiConsumer;
import io.codecastle.scriptorium.function.IOConsumer;
import io.codecastle.scriptorium.json.JsonKey;
import io.codecastle.scriptorium.json.JsonObject;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

/**
 *
 * @author Doug Valenta
 */
abstract class AbstractObjectElement<THIS extends JsonObject<THIS>> extends AbstractElement<JsonObject<?>, THIS> implements JsonObject<THIS> {

	AbstractObjectElement(final JsonFactory.Host host) {
		super(host);
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS withNull(final CharSequence key) throws IOException {
		host.scribe.key(key).nullValue();
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS withTrue(final CharSequence key) throws IOException {
		host.scribe.key(key).trueValue();
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS withFalse(final CharSequence key) throws IOException {
		host.scribe.key(key).falseValue();
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS with(final CharSequence key, final CharSequence value) throws IOException {
		host.scribe.key(key).value(value);
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS with(final CharSequence key, final char value) throws IOException {
		host.scribe.key(key).value(value);
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS with(final CharSequence key, final BigInteger value) throws IOException {
		host.scribe.key(key).value(value);
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS with(final CharSequence key, final BigDecimal value) throws IOException {
		host.scribe.key(key).value(value);
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS with(final CharSequence key, final int value) throws IOException {
		host.scribe.key(key).value(value);
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS with(final CharSequence key, final float value) throws IOException {
		host.scribe.key(key).value(value);
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS with(final CharSequence key, final long value) throws IOException {
		host.scribe.key(key).value(value);
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS with(final CharSequence key, final double value) throws IOException {
		host.scribe.key(key).value(value);
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS with(final CharSequence key, final boolean value) throws IOException {
		host.scribe.key(key).value(value);
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS withIfPresent(final CharSequence key, final Optional<?> optional) throws IOException {
		if (optional.isPresent()) {
			host.scribe.key(key).value(optional.get());
		}
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS withEmptyObject(final CharSequence key) throws IOException {
		host.scribe.key(key).emptyObject();
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS withEmptyArray(final CharSequence key) throws IOException {
		host.scribe.key(key).emptyArray();
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public JsonKey<THIS> key() throws IOException {
		host.scribe.pushKey();
		return (JsonKey<THIS>) (Object) host.jsonKeyElement;
	}

	@Override
	@SuppressWarnings("unchecked")
	public JsonKey<THIS> key(final char key) throws IOException {
		host.scribe.pushKey().append(key);
		return (JsonKey<THIS>) (Object) host.jsonKeyElement;
	}

	@Override
	@SuppressWarnings("unchecked")
	public JsonKey<THIS> key(final CharSequence key) throws IOException {
		host.scribe.pushKey().append(key);
		return (JsonKey<THIS>) (Object) host.jsonKeyElement;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS with(final IOConsumer<? super JsonObject<?>> consumer) throws IOException {
		
		final int scribeCursor = host.scribe.getCursor();
		final int stackCursor = host.stack.size();
		
		consumer.accept(host.inscribedJsonObject);
		
		host.scribe.pop(scribeCursor);
		while (host.stack.size() > stackCursor) {
			host.stack.pop();
		}
		
		return (THIS) this;
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> THIS with(final T element, final IOBiConsumer<? super T, ? super JsonObject<?>> biConsumer) throws IOException {
		
		final int scribeCursor = host.scribe.getCursor();
		final int stackCursor = host.stack.size();
		
		biConsumer.accept(element, host.inscribedJsonObject);
		
		host.scribe.pop(scribeCursor);
		while (host.stack.size() > stackCursor) {
			host.stack.pop();
		}
		
		return (THIS) this;
	}
	
}
