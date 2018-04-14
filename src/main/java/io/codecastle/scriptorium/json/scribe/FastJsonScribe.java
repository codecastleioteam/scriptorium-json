/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json.scribe;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Deque;
import java.util.LinkedList;
import io.codecastle.scriptorium.FluentNode;

/**
 * A {@link JsonScribe} that produces invalid or unexpected output and throws
 * unchecked exceptions rather than checking for misuse of stack semantics.
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
	public JsonScribe emptyObject() throws IOException {
		if (comma) appender.appendComma();
		appender.appendEmptyObject();
		comma=true;
		return this;
	}
	
	@Override
	public JsonScribe pushObject() throws IOException {
		if (comma) appender.appendComma();
		state.push(State.OBJECT);
		appender.appendOpenBrace();
		comma = false;
		return this;
	}
	
	@Override
	public JsonScribe key(final CharSequence key) throws IOException {
		if (comma) appender.appendComma();
		appender.appendQuote().escape(key).appendCloseKey();
		comma = false;
		return this;
	}
	
	@Override
	public JsonScribe pushKey() throws IOException {
		if (comma) appender.appendComma();
		state.push(State.KEY);
		appender.appendQuote();
		comma = false;
		return this;
	}
	
	@Override
	public JsonScribe emptyArray() throws IOException {
		if (comma) appender.appendComma();
		appender.appendEmptyArray();
		comma = true;
		return this;
	}
	
	@Override
	public JsonScribe pushArray() throws IOException {
		if (comma) appender.appendComma();
		state.push(State.ARRAY);
		appender.appendOpenBracket();
		comma = false;
		return this;
	}
	
	@Override
	public JsonScribe value(final CharSequence value) throws IOException {
		if (comma) appender.appendComma();
		appender.appendQuote().escape(value).appendQuote();
		comma = true;
		return this;
	}
	
	@Override
	public JsonScribe value(final char value) throws IOException {
		if (comma) appender.appendComma();
		appender.appendQuote().escape(value).appendQuote();
		comma = true;
		return this;
	}
	
	@Override
	public JsonScribe value(final BigInteger value) throws IOException {
		if (comma) appender.appendComma();
		appender.appendNumber(value);
		comma = true;
		return this;
	}
	
	@Override
	public JsonScribe value(final BigDecimal value) throws IOException {
		if (comma) appender.appendComma();
		appender.appendNumber(value);
		comma = true;
		return this;
	}
	
	@Override
	public JsonScribe value(final int value) throws IOException {
		if (comma) appender.appendComma();
		appender.appendNumber(value);
		comma = true;
		return this;
	}
	
	@Override
	public JsonScribe value(final float value) throws IOException {
		if (!Float.isFinite(value)) return nullValue();
		if (comma) appender.appendComma();
		appender.appendNumber(value);
		comma = true;
		return this;
	}
	
	@Override
	public JsonScribe value(final long value) throws IOException {
		if (comma) appender.appendComma();
		appender.appendNumber(value);
		comma = true;
		return this;
	}
	
	@Override
	public JsonScribe value(final double value) throws IOException {
		if (!Double.isFinite(value)) return nullValue();
		if (comma) appender.appendComma();
		appender.appendNumber(value);
		comma = true;
		return this;
	}
	
	@Override
	public JsonScribe value(final boolean value) throws IOException {
		if (comma) appender.appendComma();
		appender.appendBoolean(value);
		comma = true;
		return this;
	}
	
	@Override
	public JsonScribe nullValue() throws IOException {
		if (comma) appender.appendComma();
		appender.appendNull();
		comma = true;
		return this;
	}
	
	@Override
	public JsonScribe trueValue() throws IOException {
		if (comma) appender.appendComma();
		appender.appendTrue();
		comma = true;
		return this;
	}
	
	@Override
	public JsonScribe falseValue() throws IOException {
		if (comma) appender.appendComma();
		appender.appendFalse();
		comma = true;
		return this;
	}
	
	@Override
	public JsonScribe pushValue() throws IOException {
		if (comma) appender.appendComma();
		state.push(State.VALUE);
		appender.appendQuote();
		return this;
	}
	
	@Override
	public JsonScribe pop() throws IOException {
		if (inscription != null) {
			inscription.close();
			inscription = null;
		}
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
	public JsonScribe append(final CharSequence sequence) throws IOException {
		return append(sequence, 0, sequence.length());
	}
	
	@Override
	public JsonScribe append(final CharSequence sequence, final int start, final int end) throws IOException {
		appender.escape(sequence, start, end);
		return this;
	}
	
	@Override
	public JsonScribe append(final char character) throws IOException {
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
		if (cursor >= 0) {
			while (cursor < state.size()) {
				pop();
			}
		}
		return this;
	}
	
	private FluentNode<?> inscription;
	
	@Override
	public JsonScribe pushInscription(final FluentNode<?> inscription) {
		this.inscription = inscription;
		return this;
	}
	
}
