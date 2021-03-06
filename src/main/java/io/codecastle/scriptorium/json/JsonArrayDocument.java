/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json;

import io.codecastle.scriptorium.json.scribe.JsonScribe;

/**
 * Represents a JSON array currently being output as a JSON document fragment.
 * 
 * @author Doug Valenta
 * @see Json#array(Appendable)
 */
public final class JsonArrayDocument extends CloseableJsonArray<JsonArrayDocument> {
	
	
	JsonArrayDocument(final JsonScribe scribe) {
		super(scribe);
	}
	
}
