/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json;

import java.io.Closeable;
import java.io.IOException;
import io.codecastle.scriptorium.json.scribe.JsonScribe;

/**
 *
 * @author Doug Valenta
 */
abstract class CloseableJsonArray<THIS extends CloseableJsonArray<THIS>> extends AbstractJsonArray<THIS> implements Closeable {

	public CloseableJsonArray(final JsonScribe scribe) {
		super(scribe);
	}
	
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
	public void close() throws IOException {
		if (cursor > 0) {
			scribe.pop(cursor).pop();
			cursor = -1;
		}
	}
	
}
