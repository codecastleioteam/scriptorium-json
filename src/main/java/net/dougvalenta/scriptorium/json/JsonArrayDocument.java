/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json;

import net.dougvalenta.scriptorium.json.scribe.JsonScribe;

/**
 * Represents a JSON array currently being output as a JSON document fragment.
 * 
 * @author Doug Valenta
 */
public final class JsonArrayDocument extends CloseableJsonArray<JsonArrayDocument> {
	
	
	JsonArrayDocument(final JsonScribe scribe) {
		super(scribe);
	}
	
}
