/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json;

import java.io.IOException;
import net.dougvalenta.scriptorium.FluentNode;
import net.dougvalenta.scriptorium.json.scribe.JsonScribe;

/**
 * Represents a JSON array currently being output within a parent context, typically a
 * containing array or object.
 * 
 * @author Doug Valenta
 */
public final class JsonArrayNode<P> extends CloseableJsonArray<JsonArrayNode<P>> implements FluentNode<P> {

	private final P parent;
	
	JsonArrayNode(final JsonScribe scribe, final P parent) {
		super(scribe);
		this.parent = parent;
	}
	
	/**
	 * Closes this array and any nested arrays or values that remain open and returns
	 * the parent object, typically a containing {@link JsonArray} or {@link JsonObject}.
	 * 
	 * @return the parent object, typically a containing JsonArray or JsonObject
	 * @throws IOException if an I/O error occurs
	 * @see #close()
	 * @see JsonArray#array()
	 * @see JsonKey#array()
	 */
	@Override
	public P then() throws IOException {
		close();
		return parent;
	}
	
}
