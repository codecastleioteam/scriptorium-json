/*
 * Copyright 2019 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json;

import io.codecastle.scriptorium.Inscription;
import java.io.IOException;

/**
 * Represents a JSON object currently being inscribed within a parent context.
 * 
 * @author Doug Valenta
 * @param <P> the type of the parent object
 * @see JsonInscription#object(Appendable)
 * @see io.codecastle.scriptorium.Inscribable#inscribe(IOFunction)
 */
public interface JsonObjectInscription<P> extends JsonObjectNode<P>, Inscription<P> {
	
	/**
	 * Closes this JSON object and any nested arrays, objects, or values that remain
	 * open, and returns the inscribed parent context.
	 * 
	 * @return the inscribed parent context
	 * @throws IOException if an I/O error occurs
	 * @see #close()
	 * @see JsonInscription#object()
	 */
	@Override
	P then() throws IOException;
	
	/**
	 * Closes this JSON object and any nested arrays, objects, or values that remain open.
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
