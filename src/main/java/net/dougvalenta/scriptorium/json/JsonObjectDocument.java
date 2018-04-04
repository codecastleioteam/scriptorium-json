/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json;

import net.dougvalenta.scriptorium.json.scribe.JsonScribe;

/**
 * Represents a JSON object currently being output as a JSON document.
 * 
 * @author Doug Valenta
 * @see Json#object(Appendable)
 */
public final class JsonObjectDocument extends CloseableJsonObject<JsonObjectDocument> {
	
	JsonObjectDocument(final JsonScribe scribe) {
		super(scribe);
	}
	
}
