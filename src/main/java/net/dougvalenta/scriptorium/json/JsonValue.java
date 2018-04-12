/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json;

import java.io.IOException;
import net.dougvalenta.scriptorium.FluentNode;
import net.dougvalenta.scriptorium.json.scribe.JsonScribe;

/**
 * Represents a JSON string literal currently being output within a containing 
 * {@link JsonArray} or {@link JsonObject}.
 * 
 * @author Doug Valenta
 * @param <P> the type of the containing {@link JsonArray} or {@link JsonObject}
 * @see JsonArray#element()
 * @see JsonKey#value()
 */
public final class JsonValue<P> extends AbstractJsonAppendable<JsonValue<P>> implements FluentNode<P> {
	
	private final P parent;
	
	JsonValue(final JsonScribe scribe, final P parent) {
		super(scribe);
		this.parent = parent;
	}
	
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
	public P then() throws IOException {
		close();
		return parent;
	}
	
	private boolean closed = false;
	
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
	public void close() throws IOException {
		if (!closed) {
			scribe.pop();
			closed = true;
		}
	}
	
}
