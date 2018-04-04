/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json;

import java.io.IOException;
import net.dougvalenta.scriptorium.json.scribe.JsonAppender;
import net.dougvalenta.scriptorium.json.scribe.JsonEscaper;
import net.dougvalenta.scriptorium.json.scribe.SafeJsonScribe;

/**
 * Factory methods for creating new JSON documents and JSON document fragments.
 * 
 * @author Doug Valenta
 */
public final class Json {
	
	/**
	 * Returns a {@link JsonObjectDocument} representing the top-level JSON object of 
	 * a JSON document.
	 * 
	 * <p>
	 * When this method returns, an opening brace will already have been appended to the
	 * provided {@link Appendable}.
	 * 
	 * @param appendable an {@link Appendable} to output the JSON document to
	 * @return a {@link JsonObjectDocument} representing the top-level JSON object of a 
	 * JSON document
	 * @throws IOException if an I/O error occurs
	 * @throws IllegalArgumentException if {@code appendable} is null
	 */
	public static JsonObjectDocument object(final Appendable appendable) throws IOException {
		if (appendable == null) throw new IllegalArgumentException("Missing appendable");
		return new JsonObjectDocument(new SafeJsonScribe(new JsonAppender(appendable, new JsonEscaper())).pushObject());
	}
	
	/**
	 * Returns a {@link JsonArrayDocument} representing the top-level JSON array of a 
	 * JSON document fragment.
	 * 
	 * <p>
	 * When this method returns, an opening bracket will already have been appended to the
	 * provided {@link Appendable}.
	 * 
	 * @param appendable an {@link Appendable} to output the JSON document to
	 * @return a {@link JsonArrayDocument} representing the top-level JSON array of a JSON 
	 * document fragment
	 * @throws IOException if an I/O error occurs
	 * @throws IllegalArgumentException if {@code appendable} is null
	 */
	public static JsonArrayDocument array(final Appendable appendable) throws IOException {
		if (appendable == null) throw new IllegalArgumentException("Missing appendable");
		return new JsonArrayDocument(new SafeJsonScribe(new JsonAppender(appendable, new JsonEscaper())).pushArray());
	}
	
	private Json() {}
	
}
