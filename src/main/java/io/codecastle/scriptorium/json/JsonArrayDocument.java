/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json;

/**
 * Represents a JSON array currently being output as a JSON document fragment.
 * 
 * @author Doug Valenta
 * @see Json#array(Appendable)
 */
public interface JsonArrayDocument extends CloseableJsonArray<JsonArrayDocument> {
	
}
