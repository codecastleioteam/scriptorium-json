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
public class InscribedJsonAppendableTest extends AbstractJsonAppendableTest<InscribedJsonAppendable> {

	@Override
	protected InscribedJsonAppendable getJsonAppendable(final JsonScribe scribe) {
		return new InscribedJsonAppendable(scribe);
	}
	
}
