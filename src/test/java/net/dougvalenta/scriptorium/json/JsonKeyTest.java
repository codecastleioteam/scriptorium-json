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
public class JsonKeyTest extends AbstractJsonAppendableTest<JsonKey<Object>> {

	private final Object PARENT = new Object();
	
	@Override
	protected JsonKey<Object> getJsonAppendable(final JsonScribe scribe) {
		return new JsonKey<>(scribe, PARENT);
	}
	
}
