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
public class JsonObjectNode<P> extends AbstractJsonObject<JsonObjectNode<P>> implements FluentNode<P> {

	private final P parent;
	private int cursor;
	
	public JsonObjectNode(final JsonScribe scribe, final P parent) {
		super(scribe);
		this.parent = parent;
		this.cursor = scribe.getCursor();
	}
	
	@Override
	public P then() throws IOException {
		close();
		return parent;
	}
	
	@Override
	public void close() throws IOException {
		scribe.pop(cursor).pop();
		cursor = -1;
	}
	
}
