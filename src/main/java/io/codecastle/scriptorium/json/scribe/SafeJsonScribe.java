/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json.scribe;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Deque;
import io.codecastle.scriptorium.FluentNode;
import io.codecastle.scriptorium.Inscription;
import java.util.ArrayDeque;

/**
 * A {@link JsonScribe} that throws {@link IllegalStateException} rather than
 * producing invalid or unexpected output.
 * 
 * @author Doug Valenta
 */
public final class SafeJsonScribe implements JsonScribe {
	
	private enum State {
		OBJECT,
		ARRAY,
		KEY,
		VALUE,
		KEYVALUE
	}
	
	private final JsonAppender appender;
	private final Deque<State> state = new ArrayDeque<>();
	
	private boolean comma;
	
	public SafeJsonScribe(final JsonAppender appender) {
		this.appender = appender;
	}
	
	private void beforeNode() throws IOException {
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
	}
	
	private void beforeValue() throws IOException {
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
	}
	
	@Override
	public JsonScribe emptyObject() throws IOException {
		beforeValue();
		appender.appendEmptyObject();
		comma=true;
		return this;
	}
	
	@Override
	public JsonScribe pushObject() throws IOException {
		beforeNode();
		state.push(State.OBJECT);
		appender.appendOpenBrace();
		comma = false;
		return this;
	}
	
	@Override
	public JsonScribe key(final CharSequence key) throws IOException {
		if (state.peek() != State.OBJECT) {
			throw new IllegalStateException("Not an object");
		}
		if (comma) appender.appendComma();
		state.push(State.KEYVALUE);
		appender.appendQuote().escape(key).appendCloseKey();
		return this;
	}
	
	@Override
	public JsonScribe pushKey() throws IOException {
		if (state.peek() != State.OBJECT) {
			throw new IllegalStateException("Not an object");
		}
		if (comma) appender.appendComma();
		state.push(State.KEY);
		appender.appendQuote();
		return this;
	}
	
	@Override
	public JsonScribe emptyArray() throws IOException {
		beforeValue();
		appender.appendEmptyArray();
		comma = true;
		return this;
	}
	
	@Override
	public JsonScribe pushArray() throws IOException {
		beforeNode();
		state.push(State.ARRAY);
		appender.appendOpenBracket();
		comma = false;
		return this;
	}
	
	@Override
	public JsonScribe value(final CharSequence value) throws IOException {
		beforeValue();
		appender.appendQuote().escape(value).appendQuote();
		comma = true;
		return this;
	}
	
	@Override
	public JsonScribe value(final char value) throws IOException {
		beforeValue();
		appender.appendQuote().escape(value).appendQuote();
		comma = true;
		return this;
	}
	
	@Override
	public JsonScribe value(final BigInteger value) throws IOException {
		beforeValue();
		appender.appendNumber(value);
		comma = true;
		return this;
	}
	
	@Override
	public JsonScribe value(final BigDecimal value) throws IOException {
		beforeValue();
		appender.appendNumber(value);
		comma = true;
		return this;
	}
	
	@Override
	public JsonScribe value(final int value) throws IOException {
		beforeValue();
		appender.appendNumber(value);
		comma = true;
		return this;
	}
	
	@Override
	public JsonScribe value(final float value) throws IOException {
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
	public JsonScribe value(final double value) throws IOException {
		if (!Double.isFinite(value)) return nullValue();
		beforeValue();
		appender.appendNumber(value);
		comma = true;
		return this;
	}
	
	@Override
	public JsonScribe value(final boolean value) throws IOException {
		beforeValue();
		appender.appendBoolean(value);
		comma = true;
		return this;
	}
	
	@Override
	public JsonScribe nullValue() throws IOException {
		beforeValue();
		appender.appendNull();
		comma = true;
		return this;
	}
	
	@Override
	public JsonScribe trueValue() throws IOException {
		beforeValue();
		appender.appendTrue();
		comma = true;
		return this;
	}
	
	@Override
	public JsonScribe falseValue() throws IOException {
		beforeValue();
		appender.appendFalse();
		comma = true;
		return this;
	}
	
	@Override
	public JsonScribe pushValue() throws IOException {
		beforeValue();
		state.push(State.VALUE);
		appender.appendQuote();
		return this;
	}
	
	@Override
	public JsonScribe pop() throws IOException {
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
		if (inscription != null) {
			inscription.close();
			inscription = null;
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
			default: // case VALUE:
				appender.appendQuote();
		}
		return this;
	}
	
	@Override
	public JsonScribe append(final CharSequence sequence) throws IOException {
		return append(sequence, 0, sequence.length());
	}
	
	@Override
	public JsonScribe append(final CharSequence sequence, final int start, final int end) throws IOException {
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
	public JsonScribe append(final char character) throws IOException {
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
	public JsonScribe pop(final int cursor) throws IOException {
		if (cursor < 0) throw new IllegalStateException("Invalid cursor " + cursor);
		if (cursor > state.size()) throw new IllegalStateException("Future cursor " + cursor);
		while (cursor < state.size()) {
			pop();
		}
		return this;
	}
	
	private Inscription<?> inscription;
	
	@Override
	public JsonScribe pushInscription(final Inscription<?> inscription) {
		if (this.inscription != null && this.inscription != inscription) {
			throw new IllegalStateException("Already inscribed");
		}
		this.inscription = inscription;
		return this;
	}
	
}
