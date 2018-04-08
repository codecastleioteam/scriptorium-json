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
abstract class CloseableJsonObject<THIS extends CloseableJsonObject<THIS>> extends AbstractJsonObject<THIS> implements Closeable {

	public CloseableJsonObject(final JsonScribe scribe) {
		super(scribe);
	}
	
	/**
	 * Closes this JSON object and any nested arrays, objects, or values that remain open.
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	public void close() throws IOException {
		scribe.pop(cursor).pop();
	}
	
}
