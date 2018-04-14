/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json;

import io.codecastle.scriptorium.json.scribe.JsonScribe;

/**
 *
 * @author Doug Valenta
 */
public class InscribedJsonArrayTest extends AbstractJsonArrayTest<InscribedJsonArray> {

	@Override
	protected InscribedJsonArray getJsonArray(final JsonScribe scribe) {
		return new InscribedJsonArray(scribe);
	}
	
}
