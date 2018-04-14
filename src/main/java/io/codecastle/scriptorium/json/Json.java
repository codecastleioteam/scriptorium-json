/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json;

import java.io.IOException;
import io.codecastle.scriptorium.json.scribe.JsonAppender;
import io.codecastle.scriptorium.json.scribe.JsonEscaper;
import io.codecastle.scriptorium.json.scribe.FastJsonScribe;
import io.codecastle.scriptorium.json.scribe.JsonScribe;

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
	 * <p>
	 * The {@link JsonObjectDocument} returned by this method will use a 
	 * {@link FastJsonScribe}.
	 * 
	 * @param appendable an {@link Appendable} to output the JSON document to
	 * @return a {@link JsonObjectDocument} representing the top-level JSON object of a 
	 * JSON document
	 * @throws IOException if an I/O error occurs
	 * @throws IllegalArgumentException if {@code appendable} is null
	 */
	public static JsonObjectDocument object(final Appendable appendable) throws IOException {
		if (appendable == null) throw new IllegalArgumentException("Missing appendable");
		return new JsonObjectDocument(new FastJsonScribe(new JsonAppender(appendable, new JsonEscaper())).pushObject());
	}
	
	/**
	 * Returns a {@link JsonObjectDocument} representing the top-level JSON object of 
	 * a JSON document.
	 * 
	 * <p>
	 * When this method returns, the provided scribe's {@link JsonScribe#pushObject()}
	 * method will already have been called.
	 * 
	 * @param scribe the {@link JsonScribe} to use to output the JSON document
	 * @return a {@link JsonObjectDocument} representing the top-level JSON object of a 
	 * JSON document
	 * @throws IOException if an I/O error occurs
	 * @throws IllegalArgumentException if {@code scribe} is null
	 */
	public static JsonObjectDocument object(final JsonScribe scribe) throws IOException {
		if (scribe == null) throw new IllegalArgumentException("Missing scribe");
		return new JsonObjectDocument(scribe.pushObject());
	}
	
	/**
	 * Returns a {@link JsonArrayDocument} representing the top-level JSON array of a 
	 * JSON document fragment.
	 * 
	 * <p>
	 * When this method returns, an opening bracket will already have been appended to the
	 * provided {@link Appendable}.
	 * 
	 * <p>
	 * The {@link JsonArrayDocument} returned by this method will use a 
	 * {@link FastJsonScribe}.
	 * 
	 * @param appendable an {@link Appendable} to output the JSON document to
	 * @return a {@link JsonArrayDocument} representing the top-level JSON array of a JSON 
	 * document fragment
	 * @throws IOException if an I/O error occurs
	 * @throws IllegalArgumentException if {@code appendable} is null
	 */
	public static JsonArrayDocument array(final Appendable appendable) throws IOException {
		if (appendable == null) throw new IllegalArgumentException("Missing appendable");
		return new JsonArrayDocument(new FastJsonScribe(new JsonAppender(appendable, new JsonEscaper())).pushArray());
	}
	
	/**
	 * Returns a {@link JsonArrayDocument} representing the top-level JSON array of a 
	 * JSON document fragment.
	 * 
	 * <p>
	 * When this method returns, the provided scribe's {@link JsonScribe#pushArray()}
	 * method will already have been called.
	 * 
	 * @param scribe the {@link JsonScribe} to use to output the JSON document fragment
	 * @return a {@link JsonArrayDocument} representing the top-level JSON array of a JSON 
	 * document fragment
	 * @throws IOException if an I/O error occurs
	 * @throws IllegalArgumentException if {@code appendable} is null
	 */
	public static JsonArrayDocument array(final JsonScribe scribe) throws IOException {
		if (scribe == null) throw new IllegalArgumentException("Missing scribe");
		return new JsonArrayDocument(scribe.pushArray());
	}
	
	private Json() {}
	
}
