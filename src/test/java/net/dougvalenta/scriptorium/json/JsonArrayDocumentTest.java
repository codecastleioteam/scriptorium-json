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
public class JsonArrayDocumentTest extends CloseableJsonArrayTest<JsonArrayDocument> {

	@Override
	protected JsonArrayDocument getJsonArray(final JsonScribe scribe) {
		return new JsonArrayDocument(scribe);
	}
	
}
