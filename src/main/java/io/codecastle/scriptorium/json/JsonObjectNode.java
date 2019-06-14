/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json;

import java.io.IOException;
import io.codecastle.scriptorium.FluentNode;

/**
 * Represents a JSON object currently being output within a parent context, typically a
 * containing {@link JsonArray} or {@link JsonObject}.
 * 
 * @author Doug Valenta
 * @param <P> the type of the parent object
 * @see JsonArray#object()
 * @see JsonKey#object()
 */
public interface JsonObjectNode<P> extends CloseableJsonObject<JsonObjectNode<P>>, FluentNode<P> {
	
	/**
	 * Closes this JSON object and any nested arrays, objects, or values that remain open and returns
	 * the parent object, typically a containing {@link JsonArray} or {@link JsonObject}.
	 * 
	 * @return the parent object, typically a containing JsonArray or JsonObject
	 * @throws IOException if an I/O error occurs
	 * @see #close()
	 * @see JsonArray#object()
	 * @see JsonKey#object()
	 */
	@Override
	P then() throws IOException;
	
}
