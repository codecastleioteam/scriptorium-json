/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import net.dougvalenta.scriptorium.FluentNode;
import net.dougvalenta.scriptorium.function.IOBiConsumer;
import net.dougvalenta.scriptorium.function.IOConsumer;
import net.dougvalenta.scriptorium.function.IOFunction;
import net.dougvalenta.scriptorium.json.scribe.JsonScribe;

/**
 *
 * @author Doug Valenta
 */
class AbstractJsonObject<THIS extends AbstractJsonObject<THIS>> implements JsonObject<THIS> {
	
	final JsonScribe scribe;
	final int cursor;
	
	AbstractJsonObject(final JsonScribe scribe) {
		this.scribe = scribe;
		this.cursor = scribe.getCursor();
	}

	@Override
	public THIS withNull(final CharSequence key) throws IOException {
		if (key != null) scribe.key(key).nullValue();
		return (THIS) this;
	}

	@Override
	public THIS withTrue(final CharSequence key) throws IOException {
		if (key != null) scribe.key(key).trueValue();
		return (THIS) this;
	}

	@Override
	public THIS withFalse(final CharSequence key) throws IOException {
		if (key != null) scribe.key(key).falseValue();
		return (THIS) this;
	}

	@Override
	public THIS with(final CharSequence key, final CharSequence value) throws IOException {
		if (value == null) return withNull(key);
		if (key != null) scribe.key(key).value(value);
		return (THIS) this;
	}

	@Override
	public THIS with(final CharSequence key, final char value) throws IOException {
		if (key != null) scribe.key(key).value(value);
		return (THIS) this;
	}

	@Override
	public THIS with(final CharSequence key, final BigInteger value) throws IOException {
		if (value == null) return withNull(key);
		if (key != null) scribe.key(key).value(value);
		return (THIS) this;
	}

	@Override
	public THIS with(final CharSequence key, final BigDecimal value) throws IOException {
		if (value == null) return withNull(key);
		if (key != null) scribe.key(key).value(value);
		return (THIS) this;
	}

	@Override
	public THIS with(final CharSequence key, final int value) throws IOException {
		if (key != null) scribe.key(key).value(value);
		return (THIS) this;
	}

	@Override
	public THIS with(final CharSequence key, final float value) throws IOException {
		if (key != null) scribe.key(key).value(value);
		return (THIS) this;
	}

	@Override
	public THIS with(final CharSequence key, final long value) throws IOException {
		if (key != null) scribe.key(key).value(value);
		return (THIS) this;
	}

	@Override
	public THIS with(final CharSequence key, final double value) throws IOException {
		if (key != null) scribe.key(key).value(value);
		return (THIS) this;
	}

	@Override
	public THIS with(final CharSequence key, final boolean value) throws IOException {
		if (key != null) scribe.key(key).value(value);
		return (THIS) this;
	}

	@Override
	public THIS withEmptyObject(final CharSequence key) throws IOException {
		if (key != null) scribe.key(key).emptyObject();
		return (THIS) this;
	}

	@Override
	public THIS withEmptyArray(final CharSequence key) throws IOException {
		if (key != null) scribe.key(key).emptyArray();
		return (THIS) this;
	}

	@Override
	public JsonKey<THIS> key() throws IOException {
		scribe.pushKey();
		return new JsonKey<>(scribe, (THIS) this);
	}

	@Override
	public JsonKey<THIS> key(final char key) throws IOException {
		scribe.pushKey().append(key);
		return new JsonKey<>(scribe, (THIS) this);
	}

	@Override
	public JsonKey<THIS> key(final CharSequence key) throws IOException {
		scribe.pushKey();
		if (key != null) scribe.append(key);
		return new JsonKey<>(scribe, (THIS) this);
	}

	@Override
	public THIS with(final IOConsumer<? super JsonObject<?>> consumer) throws IOException {
		consumer.accept(new InscribedJsonObject(scribe));
		scribe.pop(cursor);
		return (THIS) this;
	}

	@Override
	public <T> THIS with(T element, IOBiConsumer<? super T, ? super JsonObject<?>> biConsumer) throws IOException {
		biConsumer.accept(element, new InscribedJsonObject(scribe));
		scribe.pop(cursor);
		return (THIS) this;
	}
	
	@Override
	public <T extends FluentNode<THIS>> T inscribe(final IOFunction<? super THIS, T> function) throws IOException {
		final T inscription = function.apply((THIS) this);
		scribe.pushInscription(inscription);
		return inscription;
	}
	
}
