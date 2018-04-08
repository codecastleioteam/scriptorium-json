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
 * Implements all methods specified by {@link JsonAppendable}.
 * 
 * The {@link #with(IOConsumer) } and
 * {@link #with(java.lang.Object, IOBiConsumer) }
 * implementations pass a new {@link InscribedJsonAppendable} to avert inappropriate
 * changes to scribe state (e.g. by calling close or then methods of a subclass).
 * 
 * @author Doug Valenta
 * @since 1.0
 */
class AbstractJsonAppendable<THIS extends AbstractJsonAppendable<THIS>> implements JsonAppendable<THIS> {
	
	final JsonScribe scribe;
	
	/**
	 * The scribe passed in to this method should already have had 
	 * {@link JsonScribe#pushValue()} or {@link JsonScribe#pushKey()} called.
	 * 
	 * @param scribe 
	 */
	AbstractJsonAppendable(final JsonScribe scribe) {
		this.scribe = scribe;
	}
	
	/**
	 * Appends the provided {@link CharSequence} and returns this object.
	 * 
	 * <p>
	 * The provided characters will be escaped.
	 * 
	 * <p>
	 * This method differs from {@link Appendable#append(CharSequence)}: if the provided
	 * sequence is null, this method has no effect.
	 * 
	 * @param sequence the characters to append
	 * @return this object
	 * @throws IOException 
	 * @see Appendable#append(CharSequence)
	 */
	@Override
	public THIS append(final CharSequence sequence) throws IOException {
		if (sequence != null) scribe.append(sequence);
		return (THIS) this;
	}
	
	/**
	 * Appends the specified subsequence of the provided {@link CharSequence} and 
	 * returns this object.
	 * 
	 * <p>
	 * The provided characters will be escaped.
	 * 
	 * <p>
	 * This method differs from {@link Appendable#append(CharSequence, int, int)}: if the provided
	 * sequence is null or if start is less than zero or end is less than or equal to start, 
	 * this method has no effect.
	 * 
	 * @param sequence the characters to append
	 * @param start the first character from {@code sequence} to append
	 * @param end the character after the last character from {@code sequence} to append
	 * @return this object
	 * @throws IOException 
	 * @see Appendable#append(CharSequence, int, int)
	 */
	@Override
	public THIS append(final CharSequence sequence, final int start, final int end) throws IOException {
		if (sequence != null && start >= 0) scribe.append(sequence, start, end);
		return (THIS) this;
	}
	
	/**
	 * Appends the specified subsequence of the provided character and 
	 * returns this object.
	 * 
	 * <p>
	 * The provided characters will be escaped.
	 * 
	 * @param character the character to append
	 * @return this object
	 * @throws IOException 
	 * @see Appendable#append(char)
	 */
	@Override
	public THIS append(final char character) throws IOException {
		scribe.append(character);
		return (THIS) this;
	}

	@Override
	public THIS with(final IOConsumer<? super JsonAppendable<?>> consumer) throws IOException {
		consumer.accept(new InscribedJsonAppendable(scribe));
		return (THIS) this;
	}

	@Override
	public <T> THIS with(final T element, final IOBiConsumer<? super T, ? super JsonAppendable<?>> biConsumer) throws IOException {
		biConsumer.accept(element, new InscribedJsonAppendable(scribe));
		return (THIS) this;
	}
	
}
