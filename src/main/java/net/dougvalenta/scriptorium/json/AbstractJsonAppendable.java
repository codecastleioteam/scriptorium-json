/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json;

import net.dougvalenta.scriptorium.json.scribe.JsonScribe;
import java.io.IOException;
import net.dougvalenta.scriptorium.function.IOBiConsumer;
import net.dougvalenta.scriptorium.function.IOConsumer;

/**
 * 
 * @author Doug Valenta
 * @since 1.0
 */
class AbstractJsonAppendable<THIS extends AbstractJsonAppendable<THIS>> implements JsonAppendable<THIS> {
	
	protected final JsonScribe scribe;
	
	AbstractJsonAppendable(final JsonScribe scribe) {
		this.scribe = scribe;
	}
	
	@Override
	public THIS append(final CharSequence sequence) throws IOException {
		scribe.append(sequence);
		return (THIS) this;
	}
	
	@Override
	public THIS append(final CharSequence sequence, final int start, final int end) throws IOException {
		scribe.append(sequence, start, end);
		return (THIS) this;
	}
	
	@Override
	public THIS append(final char character) throws IOException {
		scribe.append(character);
		return (THIS) this;
	}

	@Override
	public THIS with(IOConsumer<? super JsonAppendable<?>> consumer) throws IOException {
		consumer.accept(new InscribedJsonAppendable(scribe));
		return (THIS) this;
	}

	@Override
	public <T> THIS with(T element, IOBiConsumer<? super T, ? super JsonAppendable<?>> biConsumer) throws IOException {
		biConsumer.accept(element, new InscribedJsonAppendable(scribe));
		return (THIS) this;
	}
	
}
