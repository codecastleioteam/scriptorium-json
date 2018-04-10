/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json;

import java.io.IOException;
import net.dougvalenta.scriptorium.json.scribe.FastJsonScribe;
import net.dougvalenta.scriptorium.json.scribe.JsonAppender;
import net.dougvalenta.scriptorium.json.scribe.JsonEscaper;

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
	 * @see net.dougvalenta.scriptorium.function.IOFunction
	 * @see net.dougvalenta.scriptorium.Inscribable#inscribe(net.dougvalenta.scriptorium.function.IOFunction)
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
	 * @see net.dougvalenta.scriptorium.function.IOFunction
	 * @see net.dougvalenta.scriptorium.Inscribable#inscribe(net.dougvalenta.scriptorium.function.IOFunction)
	 */
	public static <T extends Appendable> JsonArrayNode<T> array(final T inscribed) throws IOException {
		return new JsonArrayNode<>(new FastJsonScribe(new JsonAppender(inscribed, new JsonEscaper())).pushArray(), inscribed);
	}
	
	private JsonInscription() {}
	
}
