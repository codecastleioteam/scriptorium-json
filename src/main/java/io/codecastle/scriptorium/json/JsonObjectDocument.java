/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json;

/**
 * Represents a JSON object currently being output as a JSON document.
 * 
 * @author Doug Valenta
 * @see Json#object(Appendable)
 */
public interface JsonObjectDocument extends CloseableJsonObject<JsonObjectDocument> {
	
}
