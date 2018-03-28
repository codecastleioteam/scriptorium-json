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
public class MockEscaper implements Escaper {

	private final char escapeCharacter;
	private final StringBuilder builder = new StringBuilder();
	
	public MockEscaper(final char escapeCharacter) {
		this.escapeCharacter = escapeCharacter;
	}
	
	@Override
	public void escape(final char character, final Appendable appendable) throws IOException {
		appendable.append(this.escapeCharacter);
		builder.append(character);
	}
	
	public String spyString() {
		return builder.toString();
	}
	
}
