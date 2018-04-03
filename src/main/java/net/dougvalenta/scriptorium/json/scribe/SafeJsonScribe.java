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
public class SafeJsonScribe implements JsonScribe {
	
	private enum State {
		OBJECT,
		ARRAY,
		KEY,
		VALUE,
		KEYVALUE
	}
	
	protected final JsonAppender appender;
	
	private final Deque<State> state = new LinkedList<>();
	
	private boolean comma;
	
	public SafeJsonScribe(final JsonAppender appender) {
		this.appender = appender;
	}
	
	private SafeJsonScribe beforeNode() throws IOException {
		if (!state.isEmpty()) {
			switch (state.peek()) {
				case ARRAY:
					if (comma) appender.appendComma();
					break;
				case KEYVALUE:
					state.pop();
					break;
				default:
					throw new IllegalStateException("Not an array or key-value");
			}
		}
		return this;
	}
	
	private SafeJsonScribe beforeValue() throws IOException {
		if (state.isEmpty()) throw new IllegalStateException("Not an array or key-value");
		switch (state.peek()) {
			case ARRAY:
				if (comma) appender.appendComma();
				break;
			case KEYVALUE:
				state.pop();
				break;
			default:
				throw new IllegalStateException("Not an array or key-value");
		}
		return this;
	}
	
	@Override
	public SafeJsonScribe emptyObject() throws IOException {
		beforeValue();
		appender.appendEmptyObject();
		comma=true;
		return this;
	}
	
	@Override
	public SafeJsonScribe pushObject() throws IOException {
		beforeNode();
		state.push(State.OBJECT);
		appender.appendOpenBrace();
		comma = false;
		return this;
	}
	
	@Override
	public SafeJsonScribe key(final CharSequence key) throws IOException {
		if (state.peek() != State.OBJECT) {
			throw new IllegalStateException("Not an object");
		}
		if (comma) appender.appendComma();
		state.push(State.KEYVALUE);
		appender.appendQuote().escape(key).appendCloseKey();
		return this;
	}
	
	@Override
	public SafeJsonScribe pushKey() throws IOException {
		if (state.peek() != State.OBJECT) {
			throw new IllegalStateException("Not an object");
		}
		if (comma) appender.appendComma();
		state.push(State.KEY);
		appender.appendQuote();
		return this;
	}
	
	@Override
	public SafeJsonScribe emptyArray() throws IOException {
		beforeValue();
		appender.appendEmptyArray();
		comma = true;
		return this;
	}
	
	@Override
	public SafeJsonScribe pushArray() throws IOException {
		beforeNode();
		state.push(State.ARRAY);
		appender.appendOpenBracket();
		comma = false;
		return this;
	}
	
	@Override
	public SafeJsonScribe value(final Object value) throws IOException {
		if (value == null) return nullValue();
		if (value instanceof CharSequence) return value((CharSequence) value);
		if (value instanceof Character) return value((char) value);
		if (value instanceof BigInteger) return value((BigInteger) value);
		if (value instanceof BigDecimal) return value((BigDecimal) value);
		if (value instanceof Byte) return value((int) value);
		if (value instanceof Short) return value((int) value);
		if (value instanceof Integer) return value((int) value);
		if (value instanceof Float) return value((float) value);
		if (value instanceof Long) return value((long) value);
		if (value instanceof Double) return value((double) value);
		if (value instanceof Boolean) return value((boolean) value);
		throw new IllegalArgumentException("Invalid type " + value.getClass().getName());
	}
	
	@Override
	public SafeJsonScribe value(final CharSequence value) throws IOException {
		beforeValue();
		appender.appendQuote().escape(value).appendQuote();
		comma = true;
		return this;
	}
	
	@Override
	public SafeJsonScribe value(final char value) throws IOException {
		beforeValue();
		appender.appendQuote().escape(value).appendQuote();
		comma = true;
		return this;
	}
	
	@Override
	public SafeJsonScribe value(final BigInteger value) throws IOException {
		beforeValue();
		appender.appendNumber(value);
		comma = true;
		return this;
	}
	
	@Override
	public SafeJsonScribe value(final BigDecimal value) throws IOException {
		beforeValue();
		appender.appendNumber(value);
		comma = true;
		return this;
	}
	
	@Override
	public SafeJsonScribe value(final int value) throws IOException {
		beforeValue();
		appender.appendNumber(value);
		comma = true;
		return this;
	}
	
	@Override
	public SafeJsonScribe value(final float value) throws IOException {
		if (!Float.isFinite(value)) return nullValue();
		beforeValue();
		appender.appendNumber(value);
		comma = true;
		return this;
	}
	
	@Override
	public SafeJsonScribe value(final long value) throws IOException {
		beforeValue();
		appender.appendNumber(value);
		comma = true;
		return this;
	}
	
	@Override
	public SafeJsonScribe value(final double value) throws IOException {
		if (!Double.isFinite(value)) return nullValue();
		beforeValue();
		appender.appendNumber(value);
		comma = true;
		return this;
	}
	
	@Override
	public SafeJsonScribe value(final boolean value) throws IOException {
		beforeValue();
		appender.appendBoolean(value);
		comma = true;
		return this;
	}
	
	@Override
	public SafeJsonScribe nullValue() throws IOException {
		beforeValue();
		appender.appendNull();
		comma = true;
		return this;
	}
	
	@Override
	public SafeJsonScribe trueValue() throws IOException {
		beforeValue();
		appender.appendTrue();
		comma = true;
		return this;
	}
	
	@Override
	public SafeJsonScribe falseValue() throws IOException {
		beforeValue();
		appender.appendFalse();
		comma = true;
		return this;
	}
	
	@Override
	public SafeJsonScribe pushValue() throws IOException {
		beforeValue();
		state.push(State.VALUE);
		appender.appendQuote();
		return this;
	}
	
	@Override
	public SafeJsonScribe pop() throws IOException {
		if (state.isEmpty()) {
			throw new IllegalStateException("No state");
		}
		switch (state.peek()) {
			case KEYVALUE:
				throw new IllegalStateException("Cannot pop state");
			case OBJECT:
			case ARRAY:
			case VALUE:
				comma = true;
		}
		switch (state.pop()) {
			case OBJECT:
				appender.appendCloseBrace();
				break;
			case ARRAY:
				appender.appendCloseBracket();
				break;
			case KEY:
				appender.appendCloseKey();
				state.push(State.KEYVALUE);
				break;
			case VALUE:
				appender.appendQuote();
				break;
		}
		return this;
	}
	
	@Override
	public SafeJsonScribe append(final CharSequence sequence) throws IOException {
		return append(sequence, 0, sequence.length());
	}
	
	@Override
	public SafeJsonScribe append(final CharSequence sequence, final int start, final int end) throws IOException {
		if (state.isEmpty()) throw new IllegalStateException("No state");
		switch (state.peek()) {
			case KEY:
			case VALUE:
				break;
			default:
				throw new IllegalStateException("Not a key or value");
		}
		appender.escape(sequence, start, end);
		return this;
	}
	
	@Override
	public SafeJsonScribe append(final char character) throws IOException {
		if (state.isEmpty()) throw new IllegalStateException("No state");
		switch (state.peek()) {
			case KEY:
			case VALUE:
				break;
			default:
				throw new IllegalStateException("Not a key or value");
		}
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
	public SafeJsonScribe pop(final int cursor) throws IOException {
		if (cursor < 0) throw new IllegalStateException("Invalid cursor " + cursor);
		if (cursor > state.size()) throw new IllegalStateException("Future cursor " + cursor);
		while (cursor < state.size()) {
			pop();
		}
		return this;
	}
	
}
