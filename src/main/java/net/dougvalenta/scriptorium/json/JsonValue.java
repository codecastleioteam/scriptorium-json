/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json;

import java.io.IOException;
import net.dougvalenta.scriptorium.FluentNode;
import net.dougvalenta.scriptorium.json.scribe.JsonScribe;

/**
 *
 * @author Doug Valenta
 */
public final class JsonValue<P> extends AbstractJsonAppendable<JsonValue<P>> implements FluentNode<P> {
	
	private final P parent;
	
	JsonValue(final JsonScribe scribe, final P parent) {
		super(scribe);
		this.parent = parent;
	}
	
	@Override
	public P then() throws IOException {
		close();
		return parent;
	}
	
	@Override
	public void close() throws IOException {
		scribe.pop();
	}
	
}
