/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json;

import java.io.IOException;
import io.codecastle.scriptorium.FluentAppendable;
import io.codecastle.scriptorium.Inscribable;

/**
 * Represents a JSON string or key currently being output.
 * 
 * @author Doug Valenta
 * @param <THIS> this type
 * @see JsonArray#element()
 * @see JsonObject#key()
 * @see JsonKey#value()
 */
public interface JsonAppendable<THIS extends JsonAppendable<THIS>> extends FluentAppendable<THIS>, Inscribable<JsonAppendable<?>, THIS> {
	
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
	public THIS append(CharSequence sequence) throws IOException;
	
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
	public THIS append(CharSequence sequence, int start, int end) throws IOException;
	
	/**
	 * Appends the specified subsequence of the provided character and 
	 * returns this object.
	 * 
	 * <p>
	 * The provided character will be escaped.
	 * 
	 * @param character the character to append
	 * @return this object
	 * @throws IOException 
	 * @see #append(Character)
	 * @see Appendable#append(char)
	 */
	@Override
	public THIS append(char character) throws IOException;
	
	/**
	 * Appends the specified subsequence of the provided character and 
	 * returns this object.
	 * 
	 * <p>
	 * The provided character will be escaped.
	 * 
	 * <p>
	 * If the provided character is null, this method has no effect.
	 * 
	 * @param character the character to append
	 * @return this object
	 * @throws IOException 
	 * @see #append(char)
	 */
	public default THIS append(final Character character) throws IOException {
		if (character != null) return append((char) character);
		return (THIS) this;
	}
	
}
