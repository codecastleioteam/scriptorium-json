/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json;

import java.io.Closeable;
import java.io.IOException;

/**
 *
 * @author Doug Valenta
 */
interface CloseableJsonArray<THIS extends CloseableJsonArray<THIS>> extends JsonArray<THIS>, Closeable {
	
	/**
	 * Closes this JSON array and any nested arrays, objects, or values that remain open.
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
