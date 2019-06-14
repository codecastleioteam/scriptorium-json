/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import io.codecastle.scriptorium.Inscribable;

/**
 * Represents a JSON array currently being output.
 * 
 * <p>
 * Methods named or beginning with the word {@code with} return the same object they are
 * called on after outputting any necessary characters.
 * 
 * <p>
 * Methods named for an output element ({@code element}, {@code array}, {@code object})
 * return a new object representing a nested structure or context. Calling the returned object's
 * {@link io.codecastle.scriptorium.FluentNode#then()} method will return this object.
 * 
 * @author Doug Valenta
 * @param <THIS> this type
 * @see Json#array(Appendable)
 * @see JsonArray#array()
 * @see JsonKey#array()
 */
public interface JsonArray<THIS extends JsonArray<THIS>> extends Inscribable<JsonArray<?>, THIS> {
	
	/**
	 * Appends a {@code null} literal element to the array and returns this object.
	 * 
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * {@link Appendable}
	 */
	THIS withNull() throws IOException;
	
	/**
	 * Appends a {@code true} literal element to the array and returns this object.
	 * 
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #withFalse()
	 * @see #with(boolean)
	 * @see #with(Boolean)
	 */
	THIS withTrue() throws IOException;
	
	/**
	 * Appends a {@code false} literal element to the array and returns this object.
	 * 
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #withTrue()
	 * @see #with(boolean)
	 * @see #with(Boolean)
	 */
	THIS withFalse() throws IOException;
	
	/**
	 * Appends a string literal element to the array and returns this object.
	 * 
	 * <p>
	 * The contents of the provided element will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided element is null, a {@code null} literal element will be appended.
	 * 
	 * @param element the string to append to the array
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #withNull()
	 * @see #with(char)
	 * @see #element(CharSequence)
	 * @see #element()
	 */
	THIS with(CharSequence element) throws IOException;
	
	/**
	 * Appends a single-character string literal element to the array and returns this
	 * object.
	 * 
	 * <p>
	 * The provided character will be double-quoted and escaped.
	 * 
	 * @param element the character to append to the array as a string
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #with(Character)
	 * @see #with(CharSequence)
	 * @see #element(char)
	 * @see #element()
	 */
	THIS with(char element) throws IOException;
	
	/**
	 * Appends a single-character string literal element to the array and
	 * returns this object.
	 * 
	 * <p>
	 * The provided character will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided element is null, a {@code null} literal element will be appended.
	 * 
	 * @param element the character to append to the array as a string
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #withNull()
	 * @see #with(char)
	 * @see #with(CharSequence)
	 * @see #element(char)
	 * @see #element()
	 */
	default THIS with(final Character element) throws IOException {
		if (element == null) return withNull();
		return with((char) element);
	}
	
	/**
	 * Appends a numeric literal element to the array and returns
	 * this object.
	 * 
	 * <p>
	 * If the provided element is null, a {@code null} literal element will be appended.
	 * 
	 * @param element the number to append to the array
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #withNull()
	 */
	THIS with(BigInteger element) throws IOException;
	
	/**
	 * Appends a numeric literal element to the array and returns
	 * this object.
	 * 
	 * <p>
	 * If the provided element is null, a {@code null} literal element will be appended.
	 * 
	 * @param element the number to append to the array
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #withNull()
	 */
	THIS with(BigDecimal element) throws IOException;
	
	/**
	 * Appends a numeric literal element to the array and returns
	 * this object.
	 * 
	 * <p>
	 * If the provided element is null, a {@code null} literal element will be appended.
	 * 
	 * @param element the number to append to the array
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #withNull()
	 * @see #with(int)
	 */
	default THIS with(final Byte element) throws IOException {
		if (element == null) return withNull();
		return with((int) element);
	}
	
	/**
	 * Appends a numeric literal element to the array and returns
	 * this object.
	 * 
	 * <p>
	 * If the provided element is null, a {@code null} literal element will be appended.
	 * 
	 * @param element the number to append to the array
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #withNull()
	 * @see #with(int)
	 */
	default THIS with(final Short element) throws IOException {
		if (element == null) return withNull();
		return with((int) element);
	}
	
	/**
	 * Appends a numeric literal element to the array and returns this object.
	 * 
	 * @param element the number to append to the array
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #with(Integer)
	 */
	THIS with(int element) throws IOException;
	
	/**
	 * Appends a numeric literal element to the array and returns
	 * this object.
	 * 
	 * <p>
	 * If the provided element is null, a {@code null} literal element will be appended.
	 * 
	 * @param element the number to append to the array
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #withNull()
	 * @see #with(int)
	 */
	default THIS with(final Integer element) throws IOException {
		if (element == null) return withNull();
		return with((int) element);
	}
	
	/**
	 * Appends a numeric literal element to the array and returns this object.
	 * 
	 * <p>
	 * If the provided element is not finite, a {@code null} literal element will be appended.
	 * 
	 * @param element the number to append to the array
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #with(Float)
	 */
	THIS with(float element) throws IOException;
	
	/**
	 * Appends a numeric literal element to the array and returns
	 * this object.
	 * 
	 * <p>
	 * If the provided element is null or not finite, a {@code null} literal element will be appended.
	 * 
	 * @param element the number to append to the array
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #withNull()
	 * @see #with(float)
	 */
	default THIS with(final Float element) throws IOException {
		if (element == null) return withNull();
		return with((float) element);
	}
	
	/**
	 * Appends a numeric literal element to the array and returns this object.
	 * 
	 * @param element the number to append to the array
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #with(Long)
	 */
	THIS with(long element) throws IOException;
	
	/**
	 * Appends a numeric literal element to the array and returns
	 * this object.
	 * 
	 * <p>
	 * If the provided element is null, a {@code null} literal element will be appended.
	 * 
	 * @param element the number to append to the array
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #withNull()
	 * @see #with(long)
	 */
	default THIS with(final Long element) throws IOException {
		if (element == null) return withNull();
		return with((long) element);
	}
	
	/**
	 * Appends a numeric literal element to the array and returns this object.
	 * 
	 * <p>
	 * If the provided element is not finite, a {@code null} literal element will be appended.
	 * 
	 * @param element the number to append to the array
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #with(Double)
	 */
	THIS with(double element) throws IOException;
	
	/**
	 * Appends a numeric literal element to the array and returns
	 * this object.
	 * 
	 * <p>
	 * If the provided element is null or not finite, a {@code null} literal element will be appended.
	 * 
	 * @param element the number to append to the array
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #withNull()
	 * @see #with(double)
	 */
	default THIS with(final Double element) throws IOException {
		if (element == null) return withNull();
		return with((double) element);
	}
	
	/**
	 * Appends a Boolean literal element to the array and returns this object.
	 * 
	 * @param element the Boolean value to append to the array
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #with(Boolean)
	 * @see #withTrue()
	 * @see #withFalse()
	 */
	THIS with(boolean element) throws IOException;
	
	/**
	 * Appends a Boolean literal element to the array and returns this object.
	 * 
	 * <p>
	 * If the provided element is null, a {@code null} literal element will be appended.
	 * 
	 * @param element the Boolean value to append to the array
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @see #withNull()
	 * @see #with(boolean)
	 * @see #withTrue()
	 * @see #withFalse() 
	 */
	default THIS with(final Boolean element) throws IOException {
		if (element == null) return withNull();
		return with((boolean) element);
	}
	
	/**
	 * Appends the provided elements to the array and returns this object.
	 * 
	 * <p>
	 * Any null elements will be appended as {@code null} literals.
	 * 
	 * <p>
	 * Any non-null {@link CharSequence} or {@link Character} elements will be appended
	 * as double-quoted and escaped string literals.
	 * 
	 * <p>
	 * Any non-null {@link Byte}, {@link Short}, {@link Integer}, {@link Float},
	 * {@link Long}, {@link Double}, {@link BigInteger}, or {@link BigDecimal}
	 * elements will be appended as numeric literals.
	 * 
	 * <p>
	 * Any non-null {@link Boolean} elements will be appended as Boolean value literals.
	 * 
	 * <p>
	 * Non-null elements of any other type will result in an {@link IllegalArgumentException}.
	 * 
	 * <p>
	 * If {@code elements} is null or empty, this method has no effect.
	 * 
	 * @param elements to append to the array
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @throws IllegalArgumentException if a non-null element of an unsupported type is
	 * provided
	 * @see #withAll(Iterable)
	 */
	THIS withAll(Object... elements) throws IOException;
	
	/**
	 * Appends the provided elements to the array and returns this object.
	 * 
	 * <p>
	 * Any null elements will be appended as {@code null} literals.
	 * 
	 * <p>
	 * Any non-null {@link CharSequence} or {@link Character} elements will be appended
	 * as double-quoted and escaped string literals.
	 * 
	 * <p>
	 * Any non-null {@link Byte}, {@link Short}, {@link Integer}, {@link Float},
	 * {@link Long}, {@link Double}, {@link BigInteger}, or {@link BigDecimal}
	 * elements will be appended as numeric literals.
	 * 
	 * <p>
	 * Any non-null {@link Boolean} elements will be appended as Boolean value literals.
	 * 
	 * <p>
	 * Non-null elements of any other type will result in an {@link IllegalArgumentException}.
	 * 
	 * <p>
	 * If {@code elements} is null or empty, this method has no effect.
	 * 
	 * @param elements to append to the array
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 * @throws IllegalArgumentException if a non-null element of an unsupported type is
	 * provided
	 * @see #withAll(Object...) 
	 */
	THIS withAll(Iterable<?> elements) throws IOException;
	
	/**
	 * Appends an empty JSON array as an element of this array and returns this object.
	 * 
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 */
	THIS withEmptyArray() throws IOException;
	
	/**
	 * Appends an empty JSON object as an element of this array and returns this object.
	 * 
	 * @return this object
	 * @throws IOException if an I/O error occurs
	 */
	THIS withEmptyObject() throws IOException;
	
	/**
	 * Begins a new string literal element of this array and returns a {@link JsonValue}
	 * that can be used to append characters to it.
	 * 
	 * <p>
	 * Calling the {@link JsonValue#then()} method of the returned JsonValue will
	 * return this object.
	 * 
	 * <p>
	 * Calling any method of this object before closing the returned JsonValue may result in 
	 * invalid output and/or an unchecked exception.
	 * 
	 * <p>
	 * When this method returns, an opening double-quote (preceded by a comma, if appropriate)
	 * will have already been appended to the underlying {@link Appendable}.
	 * 
	 * @return a JsonValue representing a new string literal element of this array
	 * @throws IOException if an I/O error occurs
	 * @see JsonValue
	 * @see #element(CharSequence)
	 * @see #element(char)
	 * @see #with(CharSequence)
	 * @see #with(Character)
	 * @see #with(char)
	 */
	JsonValue<THIS> element() throws IOException;
	
	/**
	 * Begins a new string literal element of this array beginning with the provided
	 * {@link CharSequence} and returns a {@link JsonValue} that can be used to
	 * append additional characters to it.
	 * 
	 * <p>
	 * Calling the {@link JsonValue#then()} method of the returned JsonValue will
	 * return this object.
	 * 
	 * <p>
	 * Calling any method of this object before closing the returned JsonValue may result in 
	 * invalid output and/or an unchecked exception.
	 * 
	 * <p>
	 * The contents of the provided CharSequence will be escaped.
	 * 
	 * <p>
	 * If the provided CharSequence is null, nothing will be prepended to the element.
	 * I.e., when this method returns only an opening double-quote (preceded by a comma, if 
	 * appropriate) will be appended to the underlying {@link Appendable}
	 * 
	 * @param element the beginning characters of the new string literal element
	 * @return a JsonValue representing a new string literal element of this array
	 * @throws IOException if an I/O error occurs
	 * @see JsonValue
	 * @see #element()
	 * @see #element(Character)
	 * @see #element(char)
	 * @see #with(CharSequence)
	 */
	JsonValue<THIS> element(CharSequence element) throws IOException;
	
	/**
	 * Begins a new string literal element of this array beginning with the provided
	 * character and returns a {@link JsonValue} that can be used to append additional
	 * characters to it.
	 * 
	 * <p>
	 * Calling the {@link JsonValue#then()} method of the returned JsonValue will
	 * return this object.
	 * 
	 * <p>
	 * Calling any method of this object before closing the returned JsonValue may result in 
	 * invalid output and/or an unchecked exception.
	 * 
	 * <p>
	 * When this method returns, an opening bracket (preceded by a comma, if appropriate)
	 * and the provided character will have already been appended to the underlying 
	 * {@link Appendable}. The provided character will be escaped.
	 * 
	 * @param element the first character of the new string literal element
	 * @return a JsonValue representing a new string literal element of this array
	 * @throws IOException if an I/O error occurs
	 * @see JsonValue
	 * @see #element()
	 * @see #element(CharSequence)
	 * @see #element(Character)
	 * @see #with(char)
	 */
	JsonValue<THIS> element(char element) throws IOException;
	
	/**
	 * Begins a new string literal element of this array beginning with the provided
	 * character and returns a {@link JsonValue} that can be used to append additional
	 * characters to it.
	 * 
	 * <p>
	 * Calling the {@link JsonValue#then()} method of the returned JsonValue will
	 * return this object.
	 * 
	 * <p>
	 * Calling any method of this object before closing the returned JsonValue may result in 
	 * invalid output and/or an unchecked exception.
	 * 
	 * <p>
	 * The provided character will be escaped.
	 * 
	 * <p>
	 * If the provided {@link Character} is null, nothing will be prepended to the element.
	 * I.e., when this method returns only an opening double-quote (preceded by a comma, if 
	 * appropriate) will be appended to the underlying {@link Appendable}
	 * 
	 * @param element the first character of the new string literal element
	 * @return a JsonValue representing a new string literal element of this array
	 * @throws IOException if an I/O error occurs
	 * @see JsonValue
	 * @see #element()
	 * @see #element(CharSequence)
	 * @see #element(char)
	 * @see #with(char)
	 */
	default JsonValue<THIS> element(final Character element) throws IOException {
		if (element == null) return element();
		return element((char) element);
	}
	
	/**
	 * Begins a new JSON array element of this array and returns a {@link JsonArrayNode}
	 * that can be used to append elements to it.
	 * 
	 * <p>
	 * Calling the {@link JsonArrayNode#then()} method of the returned JsonArrayNode will
	 * return this object.
	 * 
	 * <p>
	 * Calling any method of this object before closing the returned JsonArrayNode may result in 
	 * invalid output and/or an unchecked exception.
	 * 
	 * <p>
	 * When this method returns, an opening bracket (preceded by a comma, if appropriate)
	 * will have already been appended to the underlying {@link Appendable}
	 * 
	 * @return a {@link JsonArrayNode} representing the new JSON array element 
	 * @throws IOException if an I/O error occurs
	 * @see #withEmptyArray()
	 */
	JsonArrayNode<THIS> array() throws IOException;
	
	/**
	 * Begins a new JSON object element of this array and returns a {@link JsonObjectNode}
	 * that can be used to append key-value pairs to it.
	 * 
	 * <p>
	 * Calling the {@link JsonObjectNode#then()} method of the returned JsonObjectNode will
	 * return this object.
	 * 
	 * <p>
	 * Calling any method of this object before closing the returned JsonObjectNode may result in 
	 * invalid output and/or an unchecked exception.
	 * 
	 * <p>
	 * When this method returns, an opening brace (preceded by a comma, if appropriate)
	 * will have already been appended to the underlying {@link Appendable}
	 * 
	 * @return a {@link JsonObjectNode} representing the new JSON object element
	 * @throws IOException if an I/O error occurs
	 * @see #withEmptyObject()
	 */
	JsonObjectNode<THIS> object() throws IOException;
	
}
