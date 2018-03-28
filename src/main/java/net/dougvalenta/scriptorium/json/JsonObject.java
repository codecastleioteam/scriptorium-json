/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import net.dougvalenta.scriptorium.Inscribable;

/**
 *
 * @author Doug Valenta
 */
public interface JsonObject<THIS extends JsonObject<THIS>> extends Inscribable<JsonObject<?>, THIS> {
	
	/**
	 * Appends a key to the JSON object with a {@code null} literal value and returns this object.
	 * 
	 * <p>
	 * The contents of the provided key will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key is null, this method has no effect.
	 * 
	 * @param key the key to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable}
	 */
	public THIS withNull(CharSequence key) throws IOException;
	
	/**
	 * Appends a key to the JSON object with a {@code true} literal value and returns this object.
	 * 
	 * <p>
	 * The contents of the provided key will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key is null, this method has no effect.
	 * 
	 * @param key the key to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable}
	 * @see withFalse(CharSequence)
	 * @see with(CharSequence, boolean)
	 * @see with(CharSequence, Boolean)
	 */
	public THIS withTrue(CharSequence key) throws IOException;
	
	/**
	 * Appends a key to the JSON object with a {@code false} literal value and returns this object.
	 * 
	 * <p>
	 * The contents of the provided key will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key is null, this method has no effect.
	 * 
	 * @param key the key to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable}
	 * @see withTrue(CharSequence)
	 * @see with(CharSequence, boolean)
	 * @see with(CharSequence, Boolean)
	 */
	public THIS withFalse(CharSequence key) throws IOException;
	
	/**
	 * Appends a key to the JSON object with a string literal value and returns this object.
	 * 
	 * <p>
	 * The contents of the provided key and value will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key is null, this method has no effect.
	 * 
	 * <p>
	 * If the provided value is null, a {@code null} literal value will be appended.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable}
	 * @see withNull(CharSequence)
	 * @see withIfNotNull(CharSequence, CharSequence)
	 * @see with(CharSequence, Character)
	 * @see with(CharSequence, char)
	 * @see JsonKey#value(CharSequence)
	 */
	public THIS with(CharSequence key, CharSequence value) throws IOException;
	
	/**
	 * Appends a key to the JSON object with a single-character string literal value
	 * and returns this object.
	 * 
	 * <p>
	 * The contents of the provided key and value will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key is null, this method has no effect.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable}
	 * @see with(CharSequence, CharSequence)
	 * @see with(CharSequence, Character)
	 * @see JsonKey#value(char)
	 */
	public THIS with(CharSequence key, char value) throws IOException;
	
	/**
	 * Appends a key to the JSON object with a single-character string literal value
	 * and returns this object.
	 * 
	 * <p>
	 * The contents of the provided key and value will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key is null, this method has no effect.
	 * 
	 * <p>
	 * If the provided value is null, a {@code null} literal value will be appended.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable}
	 * @see withNull(CharSequence)
	 * @see withIfNotNull(CharSequence, Character)
	 * @see with(CharSequence, CharSequence)
	 * @see with(CharSequence, char)
	 * @see JsonKey#value(Character)
	 */
	public default THIS with(final CharSequence key, final Character value) throws IOException {
		if (value == null) return withNull(key);
		return with(key, (char) value);
	}
	
	/**
	 * Appends a key to the JSON object with a numeric literal value and returns this
	 * object.
	 * 
	 * <p>
	 * The contents of the provided key will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key is null, this method has no effect.
	 * 
	 * <p>
	 * If the provided value is null, a {@code null} literal value will be appended.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying
	 * {@link Appendable}
	 * @see withNull(CharSequence)
	 * @see withIfNotNull(CharSequence, BigInteger)
	 */
	public THIS with(CharSequence key, BigInteger value) throws IOException;
	
	/**
	 * Appends a key to the JSON object with a numeric literal value and returns this
	 * object.
	 * 
	 * <p>
	 * The contents of the provided key will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key is null, this method has no effect.
	 * 
	 * <p>
	 * If the provided value is null, a {@code null} literal value will be appended.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying
	 * {@link Appendable}
	 * @see withNull(CharSequence)
	 * @see withIfNotNull(CharSequence, BigDecimal)
	 */
	public THIS with(CharSequence key, BigDecimal value) throws IOException;
	
	/**
	 * Appends a key to the JSON object with a numeric literal value and returns this
	 * object.
	 * 
	 * <p>
	 * The contents of the provided key will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key is null, this method has no effect.
	 * 
	 * <p>
	 * If the provided value is null, a {@code null} literal value will be appended.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying
	 * {@link Appendable}
	 * @see withNull()
	 * @see withIfNotNull(CharSequence, Byte)
	 * @see with(CharSequence, int)
	 */
	public default THIS with(final CharSequence key, final Byte value) throws IOException {
		if (value == null) return withNull(key);
		return with(key, (int) value);
	}
	
	/**
	 * Appends a key to the JSON object with a numeric literal value and returns this
	 * object.
	 * 
	 * <p>
	 * The contents of the provided key will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key is null, this method has no effect.
	 * 
	 * <p>
	 * If the provided value is null, a {@code null} literal value will be appended.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying
	 * {@link Appendable}
	 * @see withNull()
	 * @see withIfNotNull(CharSequence, Short)
	 * @see with(CharSequence, int)
	 */
	public default THIS with(final CharSequence key, final Short value) throws IOException {
		if (value == null) return withNull(key);
		return with(key, (int) value);
	}
	
	/**
	 * Appends a key to the JSON object with a numeric literal value and returns this
	 * object.
	 * 
	 * <p>
	 * The contents of the provided key will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key is null, this method has no effect.
	 * 
	 * <p>
	 * If the provided value is null, a {@code null} literal value will be appended.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying
	 * {@link Appendable}
	 * @see withNull()
	 * @see withIfNotNull(CharSequence, Integer)
	 * @see with(CharSequence, int)
	 */
	public default THIS with(final CharSequence key, final Integer value) throws IOException {
		if (value == null) return withNull(key);
		return with(key, (int) value);
	}
	
	/**
	 * Appends a key to the JSON object with a numeric literal value and returns this
	 * object.
	 * 
	 * <p>
	 * The contents of the provided key will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key is null, this method has no effect.
	 * 
	 * <p>
	 * If the provided value is null or not finite, a {@code null} literal value will be appended.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying
	 * {@link Appendable}
	 * @see withNull()
	 * @see withIfNotNull(CharSequence, Float)
	 * @see with(CharSequence, float)
	 */
	public default THIS with(final CharSequence key, final Float value) throws IOException {
		if (value == null) return withNull(key);
		return with(key, (float) value);
	}
	
	/**
	 * Appends a key to the JSON object with a numeric literal value and returns this
	 * object.
	 * 
	 * <p>
	 * The contents of the provided key will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key is null, this method has no effect.
	 * 
	 * <p>
	 * If the provided value is null, a {@code null} literal value will be appended.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying
	 * {@link Appendable}
	 * @see withNull()
	 * @see withIfNotNull(CharSequence, Long)
	 * @see with(CharSequence, long)
	 */
	public default THIS with(final CharSequence key, final Long value) throws IOException {
		if (value == null) return withNull(key);
		return with(key, (long) value);
	}
	
	/**
	 * Appends a key to the JSON object with a numeric literal value and returns this
	 * object.
	 * 
	 * <p>
	 * The contents of the provided key will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key is null, this method has no effect.
	 * 
	 * <p>
	 * If the provided value is null or not finite, a {@code null} literal value will be appended.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying
	 * {@link Appendable}
	 * @see withNull()
	 * @see withIfNotNull(CharSequence, Double)
	 * @see with(CharSequence, double)
	 */
	public default THIS with(final CharSequence key, final Double value) throws IOException {
		if (value == null) return withNull(key);
		return with(key, (double) value);
	}
	
	/**
	 * Appends a key to the JSON object with a numeric literal value and returns this
	 * object.
	 * 
	 * <p>
	 * The contents of the provided key will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key is null, this method has no effect.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying
	 * {@link Appendable}
	 * @see with(CharSequence, Byte)
	 * @see with(CharSequence, Short)
	 * @see with(CharSequence, Integer)
	 */
	public THIS with(CharSequence key, int value) throws IOException;
	
	/**
	 * Appends a key to the JSON object with a numeric literal value and returns this
	 * object.
	 * 
	 * <p>
	 * The contents of the provided key will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key is null, this method has no effect.
	 * 
	 * <p>
	 * If the provided value is not finite, a {@code null} literal value will be appended.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying
	 * {@link Appendable}
	 * @see with(CharSequence, Float)
	 * @see withIfFinite(CharSequence, float)
	 */
	public THIS with(CharSequence key, float value) throws IOException;
	
	/**
	 * Appends a key to the JSON object with a numeric literal value and returns this
	 * object.
	 * 
	 * <p>
	 * The contents of the provided key will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key is null, this method has no effect.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying
	 * {@link Appendable}
	 * @see with(CharSequence, Long)
	 */
	public THIS with(CharSequence key, long value) throws IOException;
	
	/**
	 * Appends a key to the JSON object with a numeric literal value and returns this
	 * object.
	 * 
	 * <p>
	 * The contents of the provided key will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key is null, this method has no effect.
	 * 
	 * <p>
	 * If the provided value is not finite, a {@code null} literal value will be appended.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying
	 * {@link Appendable}
	 * @see with(CharSequence, Double)
	 * @see withIfFinite(CharSequence, double)
	 */
	public THIS with(CharSequence key, double value) throws IOException;
	
	/**
	 * Appends a key to the JSON object with a Boolean literal value and returns this
	 * object.
	 * 
	 * <p>
	 * The contents of the provided key will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key is null, this method has no effect.
	 * 
	 * <p>
	 * If the provided value is null, a {@code null} literal value will be appended.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying
	 * {@link Appendable}
	 * @see withNull(CharSequence)
	 * @see withIfNotNull(CharSequence, Boolean)
	 * @see with(CharSequence, boolean)
	 * @see withTrue(CharSequence)
	 * @see withFalse(CharSequence)
	 */
	public default THIS with(final CharSequence key, final Boolean value) throws IOException {
		if (value == null) return withNull(key);
		return with(key, (boolean) value);
	}
	
	/**
	 * Appends a key to the JSON object with a Boolean literal value and returns this
	 * object.
	 * 
	 * <p>
	 * The contents of the provided key will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key is null, this method has no effect.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying
	 * {@link Appendable}
	 * @see with(CharSequence, Boolean)
	 * @see withTrue(CharSequence)
	 * @see withFalse(CharSequence)
	 */
	public THIS with(CharSequence key, boolean value) throws IOException;
	
	/**
	 * Appends a key to the JSON object with a string literal value only if the provided
	 * value is not null, and returns this object.
	 * 
	 * <p>
	 * The contents of the provided key and value will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key or value is null, this method has no effect.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable}
	 * @see with(CharSequence, CharSequence)
	 * @see withIfNotNull(CharSequence, Character)
	 */
	public default THIS withIfNotNull(final CharSequence key, final CharSequence value) throws IOException {
		if (value != null) return with(key, value);
		return (THIS) this;
	}
	
	/**
	 * Appends a key to the JSON object with a single-character string literal value only if the provided
	 * value is not null, and returns this object.
	 * 
	 * <p>
	 * The contents of the provided key and value will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key or value is null, this method has no effect.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable}
	 * @see with(CharSequence, Character)
	 * @see withIfNotNull(CharSequence, CharSequence)
	 */
	public default THIS withIfNotNull(final CharSequence key, final Character value) throws IOException {
		if (value != null) return with(key, (char) value);
		return (THIS) this;
	}
	
	/**
	 * Appends a key to the JSON object with a numeric literal value only if the provided
	 * value is not null, and returns this object.
	 * 
	 * <p>
	 * The contents of the provided key will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key or value is null, this method has no effect.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable}
	 * @see with(CharSequence, BigInteger)
	 */
	public default THIS withIfNotNull(final CharSequence key, final BigInteger value) throws IOException {
		if (value != null) return with(key, value);
		return (THIS) this;
	}
	
	/**
	 * Appends a key to the JSON object with a numeric literal value only if the provided
	 * value is not null, and returns this object.
	 * 
	 * <p>
	 * The contents of the provided key will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key or value is null, this method has no effect.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable}
	 * @see with(CharSequence, BigDecimal)
	 */
	public default THIS withIfNotNull(final CharSequence key, final BigDecimal value) throws IOException {
		if (value != null) return with(key, value);
		return (THIS) this;
	}
	
	/**
	 * Appends a key to the JSON object with a numeric literal value only if the provided
	 * value is not null, and returns this object.
	 * 
	 * <p>
	 * The contents of the provided key will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key or value is null, this method has no effect.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable}
	 * @see with(CharSequence, Byte)
	 */
	public default THIS withIfNotNull(final CharSequence key, final Byte value) throws IOException {
		if (value != null) return with(key, (int) value);
		return (THIS) this;
	}
	
	/**
	 * Appends a key to the JSON object with a numeric literal value only if the provided
	 * value is not null, and returns this object.
	 * 
	 * <p>
	 * The contents of the provided key will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key or value is null, this method has no effect.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable}
	 * @see with(CharSequence, Short)
	 */
	public default THIS withIfNotNull(final CharSequence key, final Short value) throws IOException {
		if (value != null) return with(key, (int) value);
		return (THIS) this;
	}
	
	/**
	 * Appends a key to the JSON object with a numeric literal value only if the provided
	 * value is not null, and returns this object.
	 * 
	 * <p>
	 * The contents of the provided key will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key or value is null, this method has no effect.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable}
	 * @see with(CharSequence, Integer)
	 */
	public default THIS withIfNotNull(final CharSequence key, final Integer value) throws IOException {
		if (value != null) return with(key, (int) value);
		return (THIS) this;
	}
	
	/**
	 * Appends a key to the JSON object with a numeric literal value only if the provided
	 * value is not null, and returns this object.
	 * 
	 * <p>
	 * The contents of the provided key will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key or value is null, or if the provided value is not finite,
	 * this method has no effect.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable}
	 * @see with(CharSequence, Float)
	 * @see withIfFinite(CharSequence, float)
	 */
	public default THIS withIfNotNull(final CharSequence key, final Float value) throws IOException {
		if (value != null) return withIfFinite(key, (float) value);
		return (THIS) this;
	}
	
	/**
	 * Appends a key to the JSON object with a numeric literal value only if the provided
	 * value is not null, and returns this object.
	 * 
	 * <p>
	 * The contents of the provided key will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key or value is null, this method has no effect.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable}
	 * @see with(CharSequence, Long)
	 */
	public default THIS withIfNotNull(final CharSequence key, final Long value) throws IOException {
		if (value != null) return with(key, (long) value);
		return (THIS) this;
	}
	
	/**
	 * Appends a key to the JSON object with a numeric literal value only if the provided
	 * value is not null, and returns this object.
	 * 
	 * <p>
	 * The contents of the provided key will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key or value is null, or if the provided value is not finite,
	 * this method has no effect.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable}
	 * @see with(CharSequence, Double)
	 * @see withIfFinite(CharSequence, float)
	 */
	public default THIS withIfNotNull(final CharSequence key, final Double value) throws IOException {
		if (value != null) return withIfFinite(key, (double) value);
		return (THIS) this;
	}
	
	/**
	 * Appends a key to the JSON object with a boolean literal value only if the provided
	 * value is not null, and returns this object.
	 * 
	 * <p>
	 * The contents of the provided key will be double-quoted and escaped.
	 * 
	 * <p>
	 * If the provided key or value is null, this method has no effect.
	 * 
	 * @param key the key to append
	 * @param value the value to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable}
	 * @see with(CharSequence, Boolean)
	 */
	public default THIS withIfNotNull(final CharSequence key, final Boolean value) throws IOException {
		if (value != null) return with(key, (boolean) value);
		return (THIS) this;
	}
	
	public default THIS withIfFinite(final CharSequence key, final float value) throws IOException {
		if (Float.isFinite(value)) return with(key, value);
		return (THIS) this;
	}
	
	public default THIS withIfFinite(final CharSequence key, final double value) throws IOException {
		if (Double.isFinite(value)) return with(key, value);
		return (THIS) this;
	}
	
	/**
	 * Appends a key to the JSON object with an empty object value and returns this object.
	 * 
	 * <p>
	 * If the provided key is null, this method has no effect.
	 * 
	 * @param key the key to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable}
	 */
	public THIS withEmptyObject(CharSequence key) throws IOException;
	
	/**
	 * Appends a key to the JSON object with an empty array value and returns this object.
	 * 
	 * <p>
	 * If the provided key is null, this method has no effect.
	 * 
	 * @param key the key to append
	 * @return this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable}
	 */
	public THIS withEmptyArray(CharSequence key) throws IOException;
	
	/**
	 * Begins a new key of this JSON object and returns a {@link JsonKey} object that
	 * can be used to append characters to it and assign it a value.
	 * 
	 * <p>
	 * Calling any of the overloads of the {@code then} method of the returned JsonKey object
	 * will return this object. Additionally, calling the {@link JsonValue#then()},
	 * {@link JsonArrayNode#then()}, or {@link JsonObjectNode#then()} methods of JsonValue,
	 * JsonArrayNode, or JsonObjectNode objects returned by the returned JsonKey object's
	 * {@link JsonKey#value()}, {@link JsonKey#array()}, or {@link JsonKey#object()}
	 * methods will return this object.
	 * 
	 * <p>
	 * Calling any method of this object before any of the above-named methods
	 * of the returned JsonKey object or of the JsonValue, JsonArrayNode, or JsonObjectNode
	 * object it returns may result in invalid output and/or an unchecked
	 * exception.
	 * 
	 * <p>
	 * When this method returns, an opening double-quote (preceded by a comma, if appropriate)
	 * will have already been appended to the underlying {@link Appendable}.
	 * 
	 * @return a JsonKey representing a new key of this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable}
	 * @see key(char)
	 * @see key(Character)
	 * @see key(CharSequence)
	 */
	public JsonKey<THIS> key() throws IOException;
	
	/**
	 * Begins a new key of this JSON object beginning with the provided character
	 * and returns a {@link JsonKey} object that
	 * can be used to append characters to it and assign it a value.
	 * 
	 * <p>
	 * Calling any of the overloads of the {@code then} method of the returned JsonKey object
	 * will return this object. Additionally, calling the {@link JsonValue#then()},
	 * {@link JsonArrayNode#then()}, or {@link JsonObjectNode#then()} methods of JsonValue,
	 * JsonArrayNode, or JsonObjectNode objects returned by the returned JsonKey object's
	 * {@link JsonKey#value()}, {@link JsonKey#array()}, or {@link JsonKey#object()}
	 * methods will return this object.
	 * 
	 * <p>
	 * Calling any method of this object before any of the above-named methods
	 * of the returned JsonKey object or of the JsonValue, JsonArrayNode, or JsonObjectNode
	 * object it returns may result in invalid output and/or an unchecked
	 * exception.
	 * 
	 * <p>
	 * When this method returns, an opening bracket (preceded by a comma, if appropriate)
	 * and the provided character will have already been appended to the underlying 
	 * {@link Appendable}. The provided character will be escaped.
	 * 
	 * @param key the first character of the new key
	 * @return a JsonKey representing a new key of this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable}
	 * @see key()
	 * @see key(Character)
	 * @see key(CharSequence)
	 */
	public JsonKey<THIS> key(char key) throws IOException;
	
	/**
	 * Begins a new key of this JSON object beginning with the provided character
	 * and returns a {@link JsonKey} object that
	 * can be used to append characters to it and assign it a value.
	 * 
	 * <p>
	 * Calling any of the overloads of the {@code then} method of the returned JsonKey object
	 * will return this object. Additionally, calling the {@link JsonValue#then()},
	 * {@link JsonArrayNode#then()}, or {@link JsonObjectNode#then()} methods of JsonValue,
	 * JsonArrayNode, or JsonObjectNode objects returned by the returned JsonKey object's
	 * {@link JsonKey#value()}, {@link JsonKey#array()}, or {@link JsonKey#object()}
	 * methods will return this object.
	 * 
	 * <p>
	 * Calling any method of this object before any of the above-named methods
	 * of the returned JsonKey object or of the JsonValue, JsonArrayNode, or JsonObjectNode
	 * object it returns may result in invalid output and/or an unchecked
	 * exception.
	 * 
	 * <p>
	 * The provided character will be escaped.
	 * 
	 * <p>
	 * If the provided {@link Character} is null, nothing will be prepended to the element.
	 * I.e., when this method returns only an opening double-quote (preceded by a comma, if 
	 * appropriate) will be appended to the underlying {@link Appendable}
	 * 
	 * @param key the first character of the new key
	 * @return a JsonKey representing a new key of this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable}
	 * @see key()
	 * @see key(Character)
	 * @see key(CharSequence)
	 */
	public default JsonKey<THIS> key(final Character key) throws IOException {
		if (key == null) return key();
		return key((char) key);
	}
	
	/**
	 * Begins a new key of this JSON object beginning with the provided {@link CharSequence}
	 * and returns a {@link JsonKey} object that
	 * can be used to append characters to it and assign it a value.
	 * 
	 * <p>
	 * Calling any of the overloads of the {@code then} method of the returned JsonKey object
	 * will return this object. Additionally, calling the {@link JsonValue#then()},
	 * {@link JsonArrayNode#then()}, or {@link JsonObjectNode#then()} methods of JsonValue,
	 * JsonArrayNode, or JsonObjectNode objects returned by the returned JsonKey object's
	 * {@link JsonKey#value()}, {@link JsonKey#array()}, or {@link JsonKey#object()}
	 * methods will return this object.
	 * 
	 * <p>
	 * Calling any method of this object before any of the above-named methods
	 * of the returned JsonKey object or of the JsonValue, JsonArrayNode, or JsonObjectNode
	 * object it returns may result in invalid output and/or an unchecked
	 * exception.
	 * 
	 * <p>
	 * The contents of the provided CharSequence will be escaped.
	 * 
	 * <p>
	 * If the provided CharSequence is null, nothing will be prepended to the element.
	 * I.e., when this method returns only an opening double-quote (preceded by a comma, if 
	 * appropriate) will be appended to the underlying {@link Appendable}
	 * 
	 * @param key the first character of the new key
	 * @return a JsonKey representing a new key of this object
	 * @throws IOException if an exception occurs while appending to the underlying 
	 * {@link Appendable}
	 * @see key()
	 * @see key(Character)
	 * @see key(CharSequence)
	 */
	public JsonKey<THIS> key(CharSequence key) throws IOException;
	
}
