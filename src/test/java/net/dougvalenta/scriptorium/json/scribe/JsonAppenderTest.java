/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json.scribe;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Doug Valenta
 */
public class JsonAppenderTest {
	
	private static final char MOCK_ESCAPE_CHARACTER = '%';
	
	@Test
	public void testAppendComma() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendComma();
		Assert.assertEquals(",", builder.toString());
	}
	
	@Test
	public void testAppendCommaWithTrue() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendComma(true);
		Assert.assertEquals(",", builder.toString());
	}
	
	@Test
	public void testAppendCommaWithFalse() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendComma(false);
		Assert.assertEquals("", builder.toString());
	}
	
	@Test
	public void testAppendOpenBrace() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendOpenBrace();
		Assert.assertEquals("{", builder.toString());
	}
	
	@Test
	public void testAppendCloseBrace() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendCloseBrace();
		Assert.assertEquals("}", builder.toString());
	}
	
	@Test
	public void testAppendOpenBracket() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendOpenBracket();
		Assert.assertEquals("[", builder.toString());
	}
	
	@Test
	public void testAppendCloseBracket() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendCloseBracket();
		Assert.assertEquals("]", builder.toString());
	}
	
	@Test
	public void testAppendEmptyObject() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendEmptyObject();
		Assert.assertEquals("{}", builder.toString());
	}
	
	@Test
	public void testAppendEmptyArray() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendEmptyArray();
		Assert.assertEquals("[]", builder.toString());
	}
	
	@Test
	public void testAppendQuote() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendQuote();
		Assert.assertEquals("\"", builder.toString());
	}
	
	@Test
	public void testAppendCloseKey() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendCloseKey();
		Assert.assertEquals("\":", builder.toString());
	}
	
	@Test
	public void testAppendNull() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendNull();
		Assert.assertEquals("null", builder.toString());
	}
	
	@Test
	public void testAppendTrue() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendTrue();
		Assert.assertEquals("true", builder.toString());
	}
	
	@Test
	public void testAppendFalse() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendFalse();
		Assert.assertEquals("false", builder.toString());
	}
	
	@Test
	public void testAppendNumberWithBigInteger() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		final String number = "900000000000000000000000000000000000000000000";
		appender.appendNumber(new BigInteger(number));
		Assert.assertEquals(number, builder.toString());
	}
	
	@Test
	public void testAppendNumberWithBigDecimal() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		final String number = "0.123456789123456789";
		appender.appendNumber(new BigDecimal(number));
		Assert.assertEquals(number, builder.toString());
	}
	
	@Test
	public void testAppendNumberWithInt() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendNumber(123456789);
		Assert.assertEquals("123456789", builder.toString());
	}
	
	@Test
	public void testAppendNumberWithLong() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendNumber(123456789123456789L);
		Assert.assertEquals("123456789123456789", builder.toString());
	}
	
	@Test
	public void testAppendNumberWithFloat() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendNumber(0.12345f);
		Assert.assertEquals("0.12345", builder.toString());
	}
	
	@Test
	public void testAppendNumberWithDouble() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendNumber(0.123456789);
		Assert.assertEquals("0.123456789", builder.toString());
	}
	
	@Test
	public void testAppendBooleanWithTrue() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendBoolean(true);
		Assert.assertEquals("true", builder.toString());
	}
	
	@Test
	public void testAppendBooleanWithFalse() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendBoolean(false);
		Assert.assertEquals("false", builder.toString());
	}
	
	@Test
	public void testEscapeWithChar() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.escape('A');
		Assert.assertEquals(new String(new char[]{MOCK_ESCAPE_CHARACTER}), builder.toString());
	}
	
	@Test
	public void testEscapeWithCharSequence() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final MockEscaper escaper = new MockEscaper(MOCK_ESCAPE_CHARACTER);
		final JsonAppender appender = new JsonAppender(builder, escaper);
		appender.escape("ABC");
		Assert.assertEquals(new String(new char[]{MOCK_ESCAPE_CHARACTER, MOCK_ESCAPE_CHARACTER, MOCK_ESCAPE_CHARACTER}), builder.toString());
		Assert.assertEquals("ABC", escaper.spyString());
	}
	
	@Test
	public void testEscapeWithCharSequenceRange() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final MockEscaper escaper = new MockEscaper(MOCK_ESCAPE_CHARACTER);
		final JsonAppender appender = new JsonAppender(builder, escaper);
		appender.escape("ABCDE", 1, 4);
		Assert.assertEquals(new String(new char[]{MOCK_ESCAPE_CHARACTER, MOCK_ESCAPE_CHARACTER, MOCK_ESCAPE_CHARACTER}), builder.toString());
		Assert.assertEquals("BCD", escaper.spyString());
	}
	
}
