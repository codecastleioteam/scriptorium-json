/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json.scribe;

import java.io.Closeable;
import io.codecastle.scriptorium.FluentAppendable;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import io.codecastle.scriptorium.FluentNode;
import io.codecastle.scriptorium.Inscription;

/**
 * A stack-based API to producing valid JSON output.
 * 
 * <p>
 * Methods beginning with the word {@code push} add a state to the top of a hypothetical
 * stack, making that state the current state. The {@link #pop()} and {@link #pop(int)}
 * methods pop states off the stack, potentially outputting properly nested closing tokens.
 * Other methods may have state-specific behaviors (or may be illegal in certain states), but
 * do not modify the current state.
 * 
 * <p>
 * When the state semantic is misused by callers, implementers <b>may</b> throw unchecked
 * exceptions instead of producing invalid or unexpected output.
 * 
 * @author Doug Valenta
 */
public interface JsonScribe extends FluentAppendable<JsonScribe>, Closeable {
	
	/**
	 * Outputs an empty JSON array, preceded by a comma if necessary, and returns this
	 * object.
	 * 
	 * <p>
	 * To produce a well-formed JSON document, this method should only be called when
	 * in the array state, or when in the object state immediately following a key.
	 * Implementations <b>may</b> throw an {@link IllegalStateException} if this
	 * method is called at another time.
	 * 
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #pushArray()
	 */
	JsonScribe emptyArray() throws IOException;
	
	/**
	 * Outputs the opening bracket of a JSON array, preceded by a comma if necessary,
	 * and returns this object.
	 * 
	 * <p>
	 * After a call to this method, a call to {@link #pop()} will output the closing
	 * bracket of the array and return the scribe to its previous state.
	 * 
	 * <p>
	 * To produce a well-formed JSON document, this method should only be called when
	 * in the array state, or when in the object state immediately following a key.
	 * A JSON array fragment may be formed by calling this method on a new scribe.
	 * Implementations <b>may</b> throw an {@link IllegalStateException} if this
	 * method is called at another time.
	 * 
	 * @return this object
	 * @throws IOException if an I/O error occurs 
	 */
	JsonScribe pushArray() throws IOException;
	
	/**
	 * Outputs the opening quotation mark of a JSON string literal, preceded by a
	 * comma if necessary, and returns this object.
	 * 
	 * <p>
	 * After a call to this method, a call to {@link #pop()} will output the closing
	 * quotation mark of the string and return the scribe to its previous state.
	 * 
	 * <p>
	 * To produce a well-formed JSON document, this method should only be called when
	 * in the array state, or when in the object state immediately following a key.
	 * Implementations <b>may</b> throw an {@link IllegalStateException} if this
	 * method is called at another time.
	 * 
	 * @return this object
	 * @throws IOException if an I/O error occurs 
	 */
	JsonScribe pushValue() throws IOException;
	
	/**
	 * Outputs a string literal containing the contents of the provided value, preceded
	 * by a comma if necessary, and returns this object.
	 * 
	 * <p>
	 * The contents of the provided value will be quoted and escaped.
	 * 
	 * <p>
	 * To produce a well-formed JSON document, this method should only be called when
	 * in the array state, or when in the object state immediately following a key.
	 * Implementations <b>may</b> throw an {@link IllegalStateException} if this
	 * method is called at another time.
	 * 
	 * @param value the value to append. The contents will be quoted and escaped.
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 */
	JsonScribe value(CharSequence value) throws IOException;
	
	/**
	 * Outputs a single-character string literal containing the provided value, preceded
	 * by a comma if necessary, and returns this object.
	 * 
	 * <p>
	 * The provided value will be quoted and escaped.
	 * 
	 * <p>
	 * To produce a well-formed JSON document, this method should only be called when
	 * in the array state, or when in the object state immediately following a key.
	 * Implementations <b>may</b> throw an {@link IllegalStateException} if this
	 * method is called at another time.
	 * 
	 * @param value the value to append. The character will be quoted and escaped.
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 */
	JsonScribe value(char value) throws IOException;
	
	/**
	 * Outputs a numeric literal, preceded by a comma if necessary, and returns this
	 * object.
	 * 
	 * <p>
	 * To produce a well-formed JSON document, this method should only be called when
	 * in the array state, or when in the object state immediately following a key.
	 * Implementations <b>may</b> throw an {@link IllegalStateException} if this
	 * method is called at another time.
	 * 
	 * @param value the numeric value to append
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 */
	JsonScribe value(BigInteger value) throws IOException;
	
	/**
	 * Outputs a numeric literal, preceded by a comma if necessary, and returns this
	 * object.
	 * 
	 * <p>
	 * To produce a well-formed JSON document, this method should only be called when
	 * in the array state, or when in the object state immediately following a key.
	 * Implementations <b>may</b> throw an {@link IllegalStateException} if this
	 * method is called at another time.
	 * 
	 * @param value the numeric value to append
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 */
	JsonScribe value(BigDecimal value) throws IOException;

	/**
	 * Outputs a numeric literal, preceded by a comma if necessary, and returns this
	 * object.
	 * 
	 * <p>
	 * To produce a well-formed JSON document, this method should only be called when
	 * in the array state, or when in the object state immediately following a key.
	 * Implementations <b>may</b> throw an {@link IllegalStateException} if this
	 * method is called at another time.
	 * 
	 * @param value the numeric value to append
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 */
	JsonScribe value(int value) throws IOException;
	
	/**
	 * Outputs a numeric literal, preceded by a comma if necessary, and returns this
	 * object.
	 * 
	 * <p>
	 * If the provided value is not finite, a {@code null} literal will be appended instead.
	 * 
	 * <p>
	 * To produce a well-formed JSON document, this method should only be called when
	 * in the array state, or when in the object state immediately following a key.
	 * Implementations <b>may</b> throw an {@link IllegalStateException} if this
	 * method is called at another time.
	 * 
	 * @param value the numeric value to append
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #nullValue()
	 */
	JsonScribe value(float value) throws IOException;
	
	/**
	 * Outputs a numeric literal, preceded by a comma if necessary, and returns this
	 * object.
	 * 
	 * <p>
	 * To produce a well-formed JSON document, this method should only be called when
	 * in the array state, or when in the object state immediately following a key.
	 * Implementations <b>may</b> throw an {@link IllegalStateException} if this
	 * method is called at another time.
	 * 
	 * @param value the numeric value to append
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 */
	JsonScribe value(long value) throws IOException;
	
	/**
	 * Outputs a numeric literal, preceded by a comma if necessary, and returns this
	 * object.
	 * 
	 * <p>
	 * If the provided value is not finite, a {@code null} literal will be appended instead.
	 * 
	 * <p>
	 * To produce a well-formed JSON document, this method should only be called when
	 * in the array state, or when in the object state immediately following a key.
	 * Implementations <b>may</b> throw an {@link IllegalStateException} if this
	 * method is called at another time.
	 * 
	 * @param value the numeric value to append
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #nullValue()
	 */
	JsonScribe value(double value) throws IOException;
	
	/**
	 * Outputs a Boolean literal, preceded by a comma if necessary, and returns this
	 * object.
	 * 
	 * <p>
	 * To produce a well-formed JSON document, this method should only be called when
	 * in the array state, or when in the object state immediately following a key.
	 * Implementations <b>may</b> throw an {@link IllegalStateException} if this
	 * method is called at another time.
	 * 
	 * @param value the Boolean value to append
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #trueValue()
	 * @see #falseValue()
	 */
	JsonScribe value(boolean value) throws IOException;
	
	/**
	 * Outputs a value literal, preceded by a comma if necessary, and returns this
	 * object.
	 * 
	 * <p>
	 * Implementations should dispatch to type-specific overloads based on the runtime
	 * type of the provided argument. {@link Byte}, {@link Short}, {@link Integer}, 
	 * {@link Float}, {@link Long}, {@link Double}, {@link Character}, and {@link Boolean}
	 * arguments should be auto-unboxed.
	 * 
	 * <p>
	 * Null arguments of any type should output {@code null} literals.
	 * 
	 * <p>
	 * Implementations <b>may</b> throw an {@link IllegalArgumentException} if a non-null
	 * argument of an unsupported type is provided.
	 * 
	 * <p>
	 * To produce a well-formed JSON document, this method should only be called when
	 * in the array state, or when in the object state immediately following a key.
	 * Implementations <b>may</b> throw an {@link IllegalStateException} if this
	 * method is called at another time.
	 * 
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @throws IllegalArgumentException if the runtime type of a non-null argument is
	 * not supported
	 */
	default JsonScribe value(final Object value) throws IOException {
		if (value == null) return nullValue();
		if (value instanceof CharSequence) return value((CharSequence) value);
		if (value instanceof Character) return value((char) value);
		if (value instanceof BigInteger) return value((BigInteger) value);
		if (value instanceof BigDecimal) return value((BigDecimal) value);
		if (value instanceof Byte) return value((byte) value);
		if (value instanceof Short) return value((short) value);
		if (value instanceof Integer) return value((int) value);
		if (value instanceof Float) return value((float) value);
		if (value instanceof Long) return value((long) value);
		if (value instanceof Double) return value((double) value);
		if (value instanceof Boolean) return value((boolean) value);
		throw new IllegalArgumentException("Invalid type " + value.getClass().getName());
	}
	
	/**
	 * Outputs a {@code null} literal, preceded by a comma if necessary, and returns this
	 * object.
	 * 
	 * <p>
	 * To produce a well-formed JSON document, this method should only be called when
	 * in the array state, or when in the object state immediately following a key.
	 * Implementations <b>may</b> throw an {@link IllegalStateException} if this
	 * method is called at another time.
	 * 
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 */
	JsonScribe nullValue() throws IOException;
	
	/**
	 * Outputs a Boolean {@code true} literal, preceded by a comma if necessary, and returns this
	 * object.
	 * 
	 * <p>
	 * To produce a well-formed JSON document, this method should only be called when
	 * in the array state, or when in the object state immediately following a key.
	 * Implementations <b>may</b> throw an {@link IllegalStateException} if this
	 * method is called at another time.
	 * 
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #falseValue()
	 * @see #value(boolean)
	 */
	JsonScribe trueValue() throws IOException;
	
	/**
	 * Outputs a Boolean {@code false} literal, preceded by a comma if necessary, and returns this
	 * object.
	 * 
	 * <p>
	 * To produce a well-formed JSON document, this method should only be called when
	 * in the array state, or when in the object state immediately following a key.
	 * Implementations <b>may</b> throw an {@link IllegalStateException} if this
	 * method is called at another time.
	 * 
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #trueValue()
	 * @see #value(boolean)
	 */
	JsonScribe falseValue() throws IOException;
	
	/**
	 * Outputs an empty JSON object, preceded by a comma if necessary, and returns this
	 * object.
	 * 
	 * <p>
	 * To produce a well-formed JSON document, this method should only be called when
	 * in the array state, or when in the object state immediately following a key.
	 * Implementations <b>may</b> throw an {@link IllegalStateException} if this
	 * method is called at another time.
	 * 
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #pushObject()
	 */
	JsonScribe emptyObject() throws IOException;
	
	/**
	 * Outputs the opening brace of a JSON object, preceded by a comma if necessary,
	 * and returns this object.
	 * 
	 * <p>
	 * After a call to this method, a call to {@link #pop()} will output the closing
	 * brace of the object and return the scribe to its previous state.
	 * 
	 * <p>
	 * To produce a well-formed JSON document, this method should only be called when
	 * in the array state, or when in the object state immediately following a key.
	 * A JSON object document may be formed by calling this method on a new scribe.
	 * Implementations <b>may</b> throw an {@link IllegalStateException} if this
	 * method is called at another time.
	 * 
	 * @return this object
	 * @throws IOException if an I/O error occurs 
	 */
	JsonScribe pushObject() throws IOException;
	
	/**
	 * Outputs the opening quotation mark of a JSON key, preceded by a
	 * comma if necessary, and returns this object.
	 * 
	 * <p>
	 * After a call to this method, a call to {@link #pop()} will output the closing
	 * quotation mark of the key followed by a colon, and return the scribe to its previous state.
	 * 
	 * <p>
	 * To produce a well-formed JSON document, this method should only be called when
	 * in the object state.
	 * Implementations <b>may</b> throw an {@link IllegalStateException} if this
	 * method is called at another time.
	 * 
	 * @return this object
	 * @throws IOException if an I/O error occurs 
	 */
	JsonScribe pushKey() throws IOException;
	
	/**
	 * Outputs a JSON key containing the contents of the provided key, preceded
	 * by a comma if necessary, and returns this object.
	 * 
	 * <p>
	 * The contents of the provided key will be quoted and escaped, and followed by a colon.
	 * 
	 * <p>
	 * To produce a well-formed JSON document, this method should only be called when
	 * in the object state.
	 * Implementations <b>may</b> throw an {@link IllegalStateException} if this
	 * method is called at another time.
	 * 
	 * @param key the key to append. The contents will be quoted and escaped, and followed by a colon.
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 */
	JsonScribe key(CharSequence key) throws IOException;
	
	/**
	 * Returns to the previous scribe state and returns this object.
	 * 
	 * <p>
	 * When the scribe is in the array state, a call to this method outputs a closing bracket.
	 * 
	 * <p>
	 * When the scribe is in the object state, a call to this method outputs a closing brace.
	 * 
	 * <p>
	 * When the scribe is in the value state, a call to this method outputs a quotation mark.
	 * 
	 * <p>
	 * When the scribe is in the key state, a call to this method outputs a quotation mark
	 * followed by a colon.
	 * 
	 * <p>
	 * If this method is called without being preceded by a corresponding call to a
	 * {@code push*()} method, implementations <b>may</b> throw an {@link IllegalStateException}
	 * 
	 * @return this object
	 * @throws IOException if an I/O error occurs 
	 */
	JsonScribe pop() throws IOException;
	
	/**
	 * Returns a cursor representing the current state of the scribe.
	 * 
	 * @return a cursor
	 * @see #pop(int)
	 */
	int getCursor();
	
	/**
	 * Returns the scribe to the state represented by the cursor and returns this object.
	 * 
	 * <p>
	 * Characters will be output as though {@link #pop()} were called in succession until
	 * the desired state was reached.
	 * 
	 * <p>
	 * Implementations <b>may</b> throw {@link IllegalStateException} if the provided
	 * cursor represents an invalid state, or a state that has already been popped.
	 * 
	 * @param cursor a cursor previously returned by {@link #getCursor()}
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #getCursor()
	 * @see #pop()
	 * @see #close()
	 */
	JsonScribe pop(int cursor) throws IOException;
	
	/**
	 * Returns the scribe to its initial state.
	 * 
	 * <p>
	 * Characters will be output as though {@link #pop()} were called in succession until
	 * the initial state was reached.
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	void close() throws IOException;
	
	/**
	 * Outputs the provided sequence as a JSON string and returns this object. 
	 * 
	 * <p>
	 * The contents of the sequence will be escaped.
	 * 
	 * <p>
	 * To produce a well-formed JSON document, this method should only be called after
	 * a call to {@link #pushValue()} or {@link #pushKey()}.
	 * Implementations <b>may</b> throw an {@link IllegalStateException} if this
	 * method is called at another time.
	 * 
	 * @param sequence the sequence to append. The contents of the sequence will be escaped.
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #append(CharSequence, int, int)
	 * @see #append(char)
	 * @see Appendable#append(CharSequence)
	 */
	@Override
	JsonScribe append(CharSequence sequence) throws IOException;
	
	/**
	 * Outputs the provided sequence as a JSON string and returns this object. 
	 * 
	 * <p>
	 * The contents of the sequence will be escaped.
	 * 
	 * <p>
	 * To produce a well-formed JSON document, this method should only be called after
	 * a call to {@link #pushValue()} or {@link #pushKey()}.
	 * Implementations <b>may</b> throw an {@link IllegalStateException} if this
	 * method is called at another time.
	 * 
	 * @param sequence the sequence to append. The contents of the sequence will be escaped.
	 * @param start the index of the first character of the sequence to append
	 * @param end the first index after the last character of the sequence to append
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #append(CharSequence)
	 * @see #append(char)
	 * @see Appendable#append(CharSequence, int, int)
	 */
	@Override
	JsonScribe append(CharSequence sequence, int start, int end) throws IOException;
	
	/**
	 * Outputs the provided character as a JSON string and returns this object. 
	 * 
	 * <p>
	 * The character will be escaped.
	 * 
	 * <p>
	 * To produce a well-formed JSON document, this method should only be called after
	 * a call to {@link #pushValue()} or {@link #pushKey()}.
	 * Implementations <b>may</b> throw an {@link IllegalStateException} if this
	 * method is called at another time.
	 * 
	 * @param character the character to append. The character will be escaped.
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #append(CharSequence)
	 * @see #append(CharSequence, int, int)
	 * @see Appendable#append(char)
	 */
	@Override
	JsonScribe append(char character) throws IOException;
	
	/**
	 * Registers an inscription node that will be closed the next time this scribe's
	 * {@link #pop()}, {@link #pop(int)}, or {@link #close()} methods are called.
	 * 
	 * <p>
	 * Implementations <b>may</b> throw an {@link IllegalStateException} if this method
	 * is called twice with different arguments between calls to the above-named methods.
	 *
	 * @param inscription the inscription node to register to be closed
	 */
	JsonScribe pushInscription(Inscription<?> inscription);
	
}
