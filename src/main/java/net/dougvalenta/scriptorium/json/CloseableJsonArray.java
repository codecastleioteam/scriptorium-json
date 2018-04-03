/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json;

import java.io.Closeable;
import java.io.IOException;
import net.dougvalenta.scriptorium.json.scribe.JsonScribe;

/**
 *
 * @author Doug Valenta
 */
abstract class CloseableJsonArray<THIS extends CloseableJsonArray<THIS>> extends AbstractJsonArray<THIS> implements Closeable {

	public CloseableJsonArray(final JsonScribe scribe) {
		super(scribe);
	}
	
	/**
	 * Closes this array and any nested arrays or values that remain open.
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	public void close() throws IOException {
		scribe.pop(cursor).pop();
	}
	
}
