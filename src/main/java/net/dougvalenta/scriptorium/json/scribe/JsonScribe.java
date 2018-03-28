/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json.scribe;

import java.io.Closeable;
import net.dougvalenta.scriptorium.FluentAppendable;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author Doug Valenta
 */
public interface JsonScribe extends FluentAppendable<JsonScribe>, Closeable {
	
	public JsonScribe emptyArray() throws IOException;
	
	public JsonScribe pushArray() throws IOException;
	
	public JsonScribe pushValue() throws IOException;
	
	public JsonScribe value(CharSequence value) throws IOException;
	public JsonScribe value(char value) throws IOException;
	public JsonScribe value(BigInteger value) throws IOException;
	public JsonScribe value(BigDecimal value) throws IOException;
	public JsonScribe value(byte value) throws IOException;
	public JsonScribe value(short value) throws IOException;
	public JsonScribe value(int value) throws IOException;
	public JsonScribe value(float value) throws IOException;
	public JsonScribe value(long value) throws IOException;
	public JsonScribe value(double value) throws IOException;
	public JsonScribe value(boolean value) throws IOException;
	public JsonScribe value(Object value) throws IOException;
	
	public JsonScribe nullValue() throws IOException;
	
	public JsonScribe trueValue() throws IOException;
	
	public JsonScribe falseValue() throws IOException;
	
	public JsonScribe emptyObject() throws IOException;
	
	public JsonScribe pushObject() throws IOException;
	
	public JsonScribe pushKey() throws IOException;
	
	public JsonScribe key(CharSequence key) throws IOException;
	
	public JsonScribe pop() throws IOException;
	
	public int getCursor();
	
	public JsonScribe pop(int cursor) throws IOException;
	
}
