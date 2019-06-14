/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json;

import java.io.IOException;
import io.codecastle.scriptorium.FluentNode;

/**
 * Represents a JSON array currently being output within a parent context, typically a
 * containing {@link JsonArray} or {@link JsonObject}.
 * 
 * @author Doug Valenta
 * @param <P> the type of the parent object
 * @see JsonArray#array()
 * @see JsonKey#array()
 */
public interface JsonArrayNode<P> extends CloseableJsonArray<JsonArrayNode<P>>, FluentNode<P> {
	
	/**
	 * Closes this JSON array and any nested arrays, objects, or values that remain open and returns
	 * the parent object, typically a containing {@link JsonArray} or {@link JsonObject}.
	 * 
	 * @return the parent object, typically a containing JsonArray or JsonObject
	 * @throws IOException if an I/O error occurs
	 * @see #close()
	 * @see JsonArray#array()
	 * @see JsonKey#array()
	 */
	@Override
	public P then() throws IOException;
	
}
