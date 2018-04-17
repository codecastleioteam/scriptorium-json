/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json;

import java.io.IOException;
import io.codecastle.scriptorium.json.scribe.FastJsonScribe;
import io.codecastle.scriptorium.json.scribe.JsonAppender;
import io.codecastle.scriptorium.json.scribe.JsonEscaper;

/**
 * Inscription functions for JSON documents and JSON document fragments.
 * 
 * @author Doug Valenta
 */
public class JsonInscription {
	
	/**
	 * Inscribes the provided {@link Appendable} with a JSON object document.
	 * 
	 * <p>
	 * When this function returns, an opening brace will have already been output to the
	 * inscribed Appendable.
	 * 
	 * @param <T> the inscribed type
	 * @param inscribed the {@link Appendable} to inscribe
	 * @return a new {@link JsonObjectNode} whose parent is the inscribed Appendable 
	 * @throws IOException if an I/O error occurs
	 * @see io.codecastle.scriptorium.function.IOFunction
	 * @see io.codecastle.scriptorium.Inscribable#inscribe(io.codecastle.scriptorium.function.IOFunction)
	 */
	public static <T extends Appendable> JsonObjectNode<T> object(final T inscribed) throws IOException {
		return new JsonObjectNode<>(new FastJsonScribe(new JsonAppender(inscribed, new JsonEscaper())).pushObject(), inscribed);
	}
	
	/**
	 * Inscribes the provided {@link Appendable} with a JSON array document fragment.
	 * 
	 * <p>
	 * When this function returns, an opening bracket will have already been output to the
	 * inscribed Appendable.
	 * 
	 * @param <T> the inscribed type
	 * @param inscribed the {@link Appendable} to inscribe
	 * @return a new {@link JsonArrayNode} whose parent is the inscribed Appendable 
	 * @throws IOException if an I/O error occurs
	 * @see io.codecastle.scriptorium.function.IOFunction
	 * @see io.codecastle.scriptorium.Inscribable#inscribe(io.codecastle.scriptorium.function.IOFunction)
	 */
	public static <T extends Appendable> JsonArrayNode<T> array(final T inscribed) throws IOException {
		return new JsonArrayNode<>(new FastJsonScribe(new JsonAppender(inscribed, new JsonEscaper())).pushArray(), inscribed);
	}
	
	private JsonInscription() {}
	
}
