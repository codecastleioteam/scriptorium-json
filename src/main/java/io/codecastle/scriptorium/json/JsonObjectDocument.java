/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json;

import java.io.Closeable;
import java.io.IOException;

/**
 * Represents a JSON object currently being output as a JSON document.
 * 
 * @author Doug Valenta
 * @see Json#object(Appendable)
 */
public interface JsonObjectDocument extends JsonObject<JsonObjectDocument>, Closeable {
	
	/**
	 * Closes this JSON object and any nested arrays, objects, or values that remain open.
	 * 
	 * <p>
	 * Subsequent calls to this method after the first on the same object have no
	 * effect.
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	void close() throws IOException;
	
}
