/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json;

import java.io.IOException;
import io.codecastle.scriptorium.FluentNode;

/**
 * Represents a JSON string literal currently being output within a containing 
 * {@link JsonArray} or {@link JsonObject}.
 * 
 * @author Doug Valenta
 * @param <P> the type of the containing {@link JsonArray} or {@link JsonObject}
 * @see JsonArray#element()
 * @see JsonKey#value()
 */
public interface JsonValue<P> extends JsonAppendable<JsonValue<P>>, FluentNode<P> {
	
	/**
	 * Closes this JSON string literal and returns
	 * the parent object, typically a containing {@link JsonArray} or {@link JsonObject}.
	 * 
	 * @return the parent object, typically a containing JsonArray or JsonObject
	 * @throws IOException if an I/O error occurs
	 * @see #close()
	 * @see JsonArray#element()
	 * @see JsonKey#value()
	 */
	@Override
	P then() throws IOException;
	
	/**
	 * Closes this JSON string literal.
	 * 
	 * <p>
	 * Subsequent calls to this method after the first on the same object have no
	 * effect.
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	void close() throws IOException;
	
}
