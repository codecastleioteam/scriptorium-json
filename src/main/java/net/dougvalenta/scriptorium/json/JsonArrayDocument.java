/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json;

import net.dougvalenta.scriptorium.json.scribe.JsonScribe;

/**
 *
 * @author Doug Valenta
 */
public final class JsonArrayDocument extends CloseableJsonArray<JsonArrayDocument> {
	
	
	public JsonArrayDocument(final JsonScribe scribe) {
		super(scribe);
	}
	
}
