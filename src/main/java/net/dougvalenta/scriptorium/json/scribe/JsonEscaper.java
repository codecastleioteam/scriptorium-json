/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json.scribe;

import java.io.IOException;
import net.dougvalenta.scriptorium.Escaper;

/**
 *
 * @author Doug Valenta
 */
public class JsonEscaper implements Escaper {

	private static final char BACKSPACE = '\b';
	private static final char FORMFEED = '\f';
	private static final char NEWLINE = '\n';
	private static final char RETURN = '\r';
	private static final char TAB = '\t';
	private static final char QUOTE = '"';
	private static final char BACKSLASH = '\\';
	private static final String ESCAPED_BACKSPACE = "\\\b";
	private static final String ESCAPED_FORMFEED = "\\\f";
	private static final String ESCAPED_NEWLINE = "\\\n";
	private static final String ESCAPED_RETURN = "\\\r";
	private static final String ESCAPED_TAB = "\\\t";
	private static final String ESCAPED_BACKSLASH = "\\\\";
	private static final String ESCAPED_QUOTE = "\\\"";
	private static final String ESCAPED_UNICODE = "\\u";
	private static final String UNICODE_FORMAT = "%04x";
	
	/**
	 * Appends the provided character or its escape sequence to the provided
	 * {@link Appendable}.
	 * 
	 * <p>
	 * This {@link net.dougvalenta.scriptorium.Escaper} escapes only those characters
	 * which MUST be escaped within double-quoted JSON strings: double-quote, backslash,
	 * and control codes (U+0000 - U+001F).
	 * 
	 * @param character the character to escape or append
	 * @param appendable the appendable to append the character or its escape sequence to
	 * @throws IOException if an exception occurs while appending to the underlying
	 * {@link Appendable}
	 */
	@Override
	public void escape(final char character, final Appendable appendable) throws IOException {
		if (character < 32) {
			switch (character) {
				case BACKSPACE:
					appendable.append(ESCAPED_BACKSPACE);
					return;
				case FORMFEED:
					appendable.append(ESCAPED_FORMFEED);
					return;
				case NEWLINE:
					appendable.append(ESCAPED_NEWLINE);
					return;
				case RETURN:
					appendable.append(ESCAPED_RETURN);
					return;
				case TAB:
					appendable.append(ESCAPED_TAB);
					return;
				default:
					appendable.append(ESCAPED_UNICODE).append(String.format(UNICODE_FORMAT, (int) character));
			}
		} else {
			switch (character) {
				case BACKSLASH: 
					appendable.append(ESCAPED_BACKSLASH);
					return;
				case QUOTE:
					appendable.append(ESCAPED_QUOTE);
					return;
				default:
					appendable.append(character);
			}
		}
	}
	
}
