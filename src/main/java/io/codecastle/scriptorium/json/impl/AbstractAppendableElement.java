/*
 * Copyright 2019 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json.impl;

import io.codecastle.scriptorium.function.IOBiConsumer;
import io.codecastle.scriptorium.function.IOConsumer;
import io.codecastle.scriptorium.json.JsonAppendable;
import java.io.IOException;

/**
 *
 * @author Doug Valenta
 */
abstract class AbstractAppendableElement<THIS extends JsonAppendable<THIS>> extends AbstractElement<JsonAppendable<?>, THIS> implements JsonAppendable<THIS> {

	AbstractAppendableElement(JsonFactory.Host host) {
		super(host);
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS append(final char character) throws IOException {
		host.scribe.append(character);
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS append(final CharSequence sequence, final int start, final int end) throws IOException {
		host.scribe.append(sequence, start, end);
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS append(final CharSequence sequence) throws IOException {
		host.scribe.append(sequence);
		return (THIS) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public THIS with(final IOConsumer<? super JsonAppendable<?>> consumer) throws IOException {
		
		final int scribeCursor = host.scribe.getCursor();
		final int stackCursor = host.stack.size();
		
		consumer.accept(host.inscribedJsonAppendable);
		
		host.scribe.pop(scribeCursor);
		while (host.stack.size() > stackCursor) {
			host.stack.pop();
		}
		
		return (THIS) this;
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> THIS with(final T element, final IOBiConsumer<? super T, ? super JsonAppendable<?>> biConsumer) throws IOException {
		
		final int scribeCursor = host.scribe.getCursor();
		final int stackCursor = host.stack.size();
		
		biConsumer.accept(element, host.inscribedJsonAppendable);
		
		host.scribe.pop(scribeCursor);
		while (host.stack.size() > stackCursor) {
			host.stack.pop();
		}
		
		return (THIS) this;
		
	}
	
}
