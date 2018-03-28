/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json.scribe;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Doug Valenta
 */
public class JsonEscaperTest {
	
	@Test
	public void testEscapeBackslash() throws IOException {
		final JsonEscaper escaper = new JsonEscaper();
		final StringBuilder builder = new StringBuilder();
		escaper.escape('\\', builder);
		Assert.assertEquals("\\\\", builder.toString());
	}
	
	@Test
	public void testEscapeDoubleQuote() throws IOException {
		final JsonEscaper escaper = new JsonEscaper();
		final StringBuilder builder = new StringBuilder();
		escaper.escape('"', builder);
		Assert.assertEquals("\\\"", builder.toString());
	}
	
	@Test
	public void testEscapeControlCodes() throws IOException {
		final JsonEscaper escaper = new JsonEscaper();
		final StringBuilder builder = new StringBuilder();
		for (char i = 0; i < 32; i++) {
			escaper.escape(i, builder);
		}
		Assert.assertEquals("\\u0000\\u0001\\u0002\\u0003\\u0004\\u0005\\u0006\\u0007\\\b\\\t\\\n\\u000b\\\f\\\r\\u000e\\u000f\\u0010\\u0011\\u0012\\u0013\\u0014\\u0015\\u0016\\u0017\\u0018\\u0019\\u001a\\u001b\\u001c\\u001d\\u001e\\u001f", builder.toString());
	}
	
	@Test
	public void testUnescapedCharacters() throws IOException {
		final JsonEscaper escaper = new JsonEscaper();
		final StringBuilder builder = new StringBuilder();
		escaper.escape('A', builder);
		escaper.escape(' ', builder); // U+0020 first non-control character
		escaper.escape('Д', builder);
		Assert.assertEquals("A Д", builder.toString());
	}
	
}
