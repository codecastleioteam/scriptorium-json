/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json.scribe;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * @author Doug Valenta
 */
public final class FastJsonScribe implements JsonScribe {
	
	private enum State {
		OBJECT,
		ARRAY,
		KEY,
		VALUE
	}
	
	private final JsonAppender appender;
	private final Deque<State> state = new LinkedList<>();
	
	private boolean comma;
	
	public FastJsonScribe(final JsonAppender appender) {
		this.appender = appender;
	}
	
	@Override
	public FastJsonScribe emptyObject() throws IOException {
		if (comma) appender.appendComma();
		appender.appendEmptyObject();
		comma=true;
		return this;
	}
	
	@Override
	public FastJsonScribe pushObject() throws IOException {
		if (comma) appender.appendComma();
		state.push(State.OBJECT);
		appender.appendOpenBrace();
		comma = false;
		return this;
	}
	
	@Override
	public FastJsonScribe key(final CharSequence key) throws IOException {
		if (comma) appender.appendComma();
		appender.appendQuote().escape(key).appendCloseKey();
		comma = false;
		return this;
	}
	
	@Override
	public FastJsonScribe pushKey() throws IOException {
		if (comma) appender.appendComma();
		state.push(State.KEY);
		appender.appendQuote();
		comma = false;
		return this;
	}
	
	@Override
	public FastJsonScribe emptyArray() throws IOException {
		if (comma) appender.appendComma();
		appender.appendEmptyArray();
		comma = true;
		return this;
	}
	
	@Override
	public FastJsonScribe pushArray() throws IOException {
		if (comma) appender.appendComma();
		state.push(State.ARRAY);
		appender.appendOpenBracket();
		comma = false;
		return this;
	}
	
	@Override
	public FastJsonScribe value(final Object value) throws IOException {
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
		return this;
	}
	
	@Override
	public FastJsonScribe value(final CharSequence value) throws IOException {
		if (comma) appender.appendComma();
		appender.appendQuote().escape(value).appendQuote();
		comma = true;
		return this;
	}
	
	@Override
	public FastJsonScribe value(final char value) throws IOException {
		if (comma) appender.appendComma();
		appender.appendQuote().escape(value).appendQuote();
		comma = true;
		return this;
	}
	
	@Override
	public FastJsonScribe value(final BigInteger value) throws IOException {
		if (comma) appender.appendComma();
		appender.appendNumber(value);
		comma = true;
		return this;
	}
	
	@Override
	public FastJsonScribe value(final BigDecimal value) throws IOException {
		if (comma) appender.appendComma();
		appender.appendNumber(value);
		comma = true;
		return this;
	}
	
	@Override
	public FastJsonScribe value(final int value) throws IOException {
		if (comma) appender.appendComma();
		appender.appendNumber(value);
		comma = true;
		return this;
	}
	
	@Override
	public FastJsonScribe value(final float value) throws IOException {
		if (!Float.isFinite(value)) return nullValue();
		if (comma) appender.appendComma();
		appender.appendNumber(value);
		comma = true;
		return this;
	}
	
	@Override
	public FastJsonScribe value(final long value) throws IOException {
		if (comma) appender.appendComma();
		appender.appendNumber(value);
		comma = true;
		return this;
	}
	
	@Override
	public FastJsonScribe value(final double value) throws IOException {
		if (!Double.isFinite(value)) return nullValue();
		if (comma) appender.appendComma();
		appender.appendNumber(value);
		comma = true;
		return this;
	}
	
	@Override
	public FastJsonScribe value(final boolean value) throws IOException {
		if (comma) appender.appendComma();
		appender.appendBoolean(value);
		comma = true;
		return this;
	}
	
	@Override
	public FastJsonScribe nullValue() throws IOException {
		if (comma) appender.appendComma();
		appender.appendNull();
		comma = true;
		return this;
	}
	
	@Override
	public FastJsonScribe trueValue() throws IOException {
		if (comma) appender.appendComma();
		appender.appendTrue();
		comma = true;
		return this;
	}
	
	@Override
	public FastJsonScribe falseValue() throws IOException {
		if (comma) appender.appendComma();
		appender.appendFalse();
		comma = true;
		return this;
	}
	
	@Override
	public FastJsonScribe pushValue() throws IOException {
		if (comma) appender.appendComma();
		state.push(State.VALUE);
		appender.appendQuote();
		return this;
	}
	
	@Override
	public FastJsonScribe pop() throws IOException {
		switch (state.pop()) {
			case OBJECT:
				appender.appendCloseBrace();
				comma = true;
				break;
			case ARRAY:
				appender.appendCloseBracket();
				comma = true;
				break;
			case VALUE:
				appender.appendQuote();
				comma = true;
				break;
			default: // case KEY:
				appender.appendCloseKey();
				break;
		}
		return this;
	}
	
	@Override
	public FastJsonScribe append(final CharSequence sequence) throws IOException {
		return append(sequence, 0, sequence.length());
	}
	
	@Override
	public FastJsonScribe append(final CharSequence sequence, final int start, final int end) throws IOException {
		appender.escape(sequence, start, end);
		return this;
	}
	
	@Override
	public FastJsonScribe append(final char character) throws IOException {
		appender.escape(character);
		return this;
	}
	
	@Override
	public void close() throws IOException {
		while (!state.isEmpty()) pop();
	}
	
	@Override
	public int getCursor() {
		return state.size();
	}
	
	@Override
	public FastJsonScribe pop(final int cursor) throws IOException {
		if (cursor >= 0) {
			while (cursor < state.size()) {
				pop();
			}
		}
		return this;
	}
	
}
