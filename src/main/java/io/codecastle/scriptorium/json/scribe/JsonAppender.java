/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json.scribe;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import io.codecastle.scriptorium.scribe.Escaper;

/**
 * Outputs JSON tokens, including escaped string literals, to an underlying {@link Appendable}.
 * 
 * @author Doug Valenta
 */
public class JsonAppender {

	private static final char COMMA = ',';
	private static final char OPEN_BRACE = '{';
	private static final char CLOSE_BRACE = '}';
	private static final char OPEN_BRACKET = '[';
	private static final char CLOSE_BRACKET = ']';
	private static final char QUOTE = '"';
	private static final String CLOSE_KEY = "\":";
	private static final String NULL = "null";
	private static final String TRUE = "true";
	private static final String FALSE = "false";
	private static final String EMPTY_OBJECT = "{}";
	private static final String EMPTY_ARRAY = "[]";
	
	private final Appendable appendable;
	private final Escaper escaper;
	
	/**
	 * Creates a new JsonAppender that outputs to the provided {@link Appendable},
	 * and escapes string literals using the provided {@link Escaper}.
	 * 
	 * @param appendable the {@link Appendable} the new JsonAppender will output to
	 * @param escaper the {@link Escaper} the new JsonAppender will use to escape
	 * string literals
	 */
	public JsonAppender(final Appendable appendable, final Escaper escaper) {
		this.appendable = appendable;
		this.escaper = escaper;
	}
	
	/**
	 * Outputs a comma character and returns this object.
	 * 
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable} 
	 */
	public JsonAppender appendComma() throws IOException {
		appendable.append(COMMA);
		return this;
	}
	
	/**
	 * If the provided condition is true, outputs a comma character, then returns this object.
	 * 
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable} 
	 */
	public JsonAppender appendComma(boolean condition) throws IOException {
		if (condition) return appendComma();
		return this;
	}
	
	/**
	 * Outputs an open brace character and returns this object.
	 * 
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying
	 * {@link Appendable}
	 */
	public JsonAppender appendOpenBrace() throws IOException {
		appendable.append(OPEN_BRACE);
		return this;
	}
	
	/**
	 * Outputs a close brace character and returns this object.
	 * 
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying
	 * {@link Appendable}
	 */
	public JsonAppender appendCloseBrace() throws IOException {
		appendable.append(CLOSE_BRACE);
		return this;
	}
	
	/**
	 * Outputs an open bracket character and returns this object.
	 * 
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying
	 * {@link Appendable}
	 */
	public JsonAppender appendOpenBracket() throws IOException {
		appendable.append(OPEN_BRACKET);
		return this;
	}
	
	/**
	 * Outputs a close bracket character and returns this object.
	 * 
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying
	 * {@link Appendable}
	 */
	public JsonAppender appendCloseBracket() throws IOException {
		appendable.append(CLOSE_BRACKET);
		return this;
	}
	
	/**
	 * Outputs an open brace character followed immediately by a close brace
	 * character, and returns this object.
	 * 
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying
	 * {@link Appendable}
	 */
	public JsonAppender appendEmptyObject() throws IOException {
		appendable.append(EMPTY_OBJECT);
		return this;
	}
	
	/**
	 * Outputs an open bracket character followed immediately by a close bracket
	 * character, and returns this object.
	 * 
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying
	 * {@link Appendable}
	 */
	public JsonAppender appendEmptyArray() throws IOException {
		appendable.append(EMPTY_ARRAY);
		return this;
	}
	
	/**
	 * Outputs a double-quote character and returns this object.
	 * 
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable} 
	 */
	public JsonAppender appendQuote() throws IOException {
		appendable.append(QUOTE);
		return this;
	}
	
	/**
	 * Outputs a double-quote character immediately followed by a colon character,
	 * and returns this object.
	 * 
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable} 
	 */
	public JsonAppender appendCloseKey() throws IOException {
		appendable.append(CLOSE_KEY);
		return this;
	}
	
	/**
	 * Outputs the string "null" and returns this object.
	 * 
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable} 
	 */
	public JsonAppender appendNull() throws IOException {
		appendable.append(NULL);
		return this;
	}
	
	/**
	 * Outputs the string "true" and returns this object.
	 * 
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable} 
	 */
	public JsonAppender appendTrue() throws IOException {
		appendable.append(TRUE);
		return this;
	}
	
	/**
	 * Outputs the string "false" and returns this object.
	 * 
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable} 
	 */
	public JsonAppender appendFalse() throws IOException {
		appendable.append(FALSE);
		return this;
	}
	
	/**
	 * Outputs the provided {@link BigInteger} as a JSON numeric literal and returns
	 * this object.
	 * 
	 * <p>
	 * Behavior is undefined when the provided BigInteger is null.
	 * 
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable} 
	 */
	public JsonAppender appendNumber(final BigInteger value) throws IOException {
		appendable.append(value.toString());
		return this;
	}
	
	/**
	 * Outputs the provided {@link BigDecimal} as a JSON numeric literal and returns
	 * this object.
	 * 
	 * <p>
	 * Behavior is undefined when the provided BigDecimal is null.
	 * 
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable} 
	 */
	public JsonAppender appendNumber(final BigDecimal value) throws IOException {
		appendable.append(value.toString());
		return this;
	}
	
	/**
	 * Outputs the provided int as a JSON numeric literal and returns
	 * this object.
	 * 
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable} 
	 */
	public JsonAppender appendNumber(final int value) throws IOException {
		appendable.append(String.valueOf(value));
		return this;
	}
	
	/**
	 * Outputs the provided float as a JSON numeric literal and returns
	 * this object.
	 * 
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable} 
	 */
	public JsonAppender appendNumber(final float value) throws IOException {
		appendable.append(String.valueOf(value));
		return this;
	}
	
	/**
	 * Outputs the provided long as a JSON numeric literal and returns
	 * this object.
	 * 
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable} 
	 */
	public JsonAppender appendNumber(final long value) throws IOException {
		appendable.append(String.valueOf(value));
		return this;
	}
	
	/**
	 * Outputs the provided double as a JSON numeric literal and returns
	 * this object.
	 * 
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable} 
	 */
	public JsonAppender appendNumber(final double value) throws IOException {
		appendable.append(String.valueOf(value));
		return this;
	}
	
	/**
	 * If the provided value is true, outputs the string "true", otherwise
	 * outputs the string "false", then returns this object.
	 * 
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable} 
	 */
	public JsonAppender appendBoolean(final boolean value) throws IOException {
		appendable.append(value ? TRUE : FALSE);
		return this;
	}
	
	/**
	 * Outputs the provided character or its escape sequence, using the provided
	 * {@link io.codecastle.scriptorium.scribe.Escaper}, and returns this object.
	 * 
	 * @param character the character to output or escape
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable} 
	 */
	public JsonAppender escape(final char character) throws IOException {
		escaper.escape(character, appendable);
		return this;
	}

	/**
	 * Outputs the characters of the provided {@link CharSequence} or their escape 
	 * sequences, using the provided {@link io.codecastle.scriptorium.scribe.Escaper}, 
	 * and returns this object.
	 * 
	 * <p>
	 * Behavior is undefined if the provided CharSequence is null.
	 * 
	 * @param sequence the {@link CharSequence} containing characters to output or escape
	 * @param start the index of the first character in {@code sequence} to output or escape
	 * @param end the index of the first character in {@code sequence} after the last character
	 * that will be output or escaped
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable} 
	 */
	public JsonAppender escape(final CharSequence sequence, int start, int end) throws IOException {
		for (int i = start; i < end; i++) {
			escaper.escape(sequence.charAt(i), appendable);
		}
		return this;
	}

	/**
	 * Outputs the characters of the provided {@link CharSequence} or their escape 
	 * sequences, using the provided {@link io.codecastle.scriptorium.scribe.Escaper}, 
	 * and returns this object.
	 * 
	 * <p>
	 * Behavior is undefined if the provided CharSequence is null.
	 * 
	 * @param sequence the {@link CharSequence} containing characters to output or escape
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable} 
	 */
	public JsonAppender escape(final CharSequence sequence) throws IOException {
		return escape(sequence, 0, sequence.length());
	}
	
}
