/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json.scribe;

import java.io.IOException;
import net.dougvalenta.scriptorium.Escaper;

/**
 * An {@link Escaper} that escapes characters to be included in JSON string literals,
 * including both values and keys.
 * 
 * <p>
 * This escaper minimally conforms to section 7 of 
 * <a href="https://tools.ietf.org/html/rfc8259#section-7">IETF RFC-8259</a>
 * and may not be appropriate for all use-cases and security contexts. Importantly, not all
 * legal JSON strings are legal JavaScript strings, as JavaScript requires some characters
 * be escaped that JSON does not.
 * 
 * <p>
 * This implementation chooses the shorter (fewer characters) escape sequence
 * for the supplied character when multiple valid escape sequences are allowed.
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
