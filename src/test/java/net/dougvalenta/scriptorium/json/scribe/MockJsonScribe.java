/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json.scribe;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import net.dougvalenta.scriptorium.FluentNode;

/**
 *
 * @author Doug Valenta
 */
public class MockJsonScribe implements JsonScribe {

	private int state;
	
	@Override
	public JsonScribe emptyArray() throws IOException {
		return this;
	}

	@Override
	public JsonScribe pushArray() throws IOException {
		state++;
		return this;
	}

	@Override
	public JsonScribe pushValue() throws IOException {
		state++;
		return this;
	}

	@Override
	public JsonScribe value(CharSequence value) throws IOException {
		return this;
	}

	@Override
	public JsonScribe value(char value) throws IOException {
		return this;
	}

	@Override
	public JsonScribe value(BigInteger value) throws IOException {
		return this;
	}

	@Override
	public JsonScribe value(BigDecimal value) throws IOException {
		return this;
	}

	@Override
	public JsonScribe value(int value) throws IOException {
		return this;
	}

	@Override
	public JsonScribe value(float value) throws IOException {
		return this;
	}

	@Override
	public JsonScribe value(long value) throws IOException {
		return this;
	}

	@Override
	public JsonScribe value(double value) throws IOException {
		return this;
	}

	@Override
	public JsonScribe value(boolean value) throws IOException {
		return this;
	}

	@Override
	public JsonScribe value(Object value) throws IOException {
		return this;
	}

	@Override
	public JsonScribe nullValue() throws IOException {
		return this;
	}

	@Override
	public JsonScribe trueValue() throws IOException {
		return this;
	}

	@Override
	public JsonScribe falseValue() throws IOException {
		return this;
	}

	@Override
	public JsonScribe emptyObject() throws IOException {
		return this;
	}

	@Override
	public JsonScribe pushObject() throws IOException {
		state++;
		return this;
	}

	@Override
	public JsonScribe pushKey() throws IOException {
		state++;
		return this;
	}

	@Override
	public JsonScribe key(CharSequence key) throws IOException {
		return this;
	}

	@Override
	public JsonScribe pop() throws IOException {
		state--;
		return this;
	}

	@Override
	public int getCursor() {
		return state;
	}

	@Override
	public JsonScribe pop(int cursor) throws IOException {
		state = cursor;
		return this;
	}

	@Override
	public void close() throws IOException {
		state = 0;
	}

	@Override
	public JsonScribe append(CharSequence sequence) throws IOException {
		return this;
	}

	@Override
	public JsonScribe append(CharSequence sequence, int start, int end) throws IOException {
		return this;
	}

	@Override
	public JsonScribe append(char character) throws IOException {
		return this;
	}

	@Override
	public JsonScribe pushInscription(FluentNode<?> inscription) {
		return this;
	}
	
}
