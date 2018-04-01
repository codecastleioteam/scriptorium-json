/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json.scribe;

/**
 *
 * @author Doug Valenta
 */
public class SafeJsonScribeTest extends AbstractJsonScribeTest {
	
	@Override
	protected JsonScribe getScribe(final JsonAppender appender) {
		return new SafeJsonScribe(appender);
	}

}
