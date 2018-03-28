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
	
	@Test(expected=IOException.class)
	public void testAppendCommaWithException() throws IOException {
		final JsonAppender appender = new JsonAppender(new ThrowingAppendable(), new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendComma();
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
	
	@Test(expected=IOException.class)
	public void testAppendCommaWithTrueWithException() throws IOException {
		final JsonAppender appender = new JsonAppender(new ThrowingAppendable(), new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendComma(true);
	}
	
	@Test
	public void testAppendOpenBrace() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendOpenBrace();
		Assert.assertEquals("{", builder.toString());
	}
	
	@Test(expected=IOException.class)
	public void testAppendOpenBraceWithException() throws IOException {
		final JsonAppender appender = new JsonAppender(new ThrowingAppendable(), new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendOpenBrace();
	}
	
	@Test
	public void testAppendCloseBrace() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendCloseBrace();
		Assert.assertEquals("}", builder.toString());
	}
	
	@Test(expected=IOException.class)
	public void testAppendCloseBraceWithException() throws IOException {
		final JsonAppender appender = new JsonAppender(new ThrowingAppendable(), new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendCloseBrace();
	}
	
	@Test
	public void testAppendOpenBracket() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendOpenBracket();
		Assert.assertEquals("[", builder.toString());
	}
	
	@Test(expected=IOException.class)
	public void testAppendOpenBracketWithException() throws IOException {
		final JsonAppender appender = new JsonAppender(new ThrowingAppendable(), new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendOpenBracket();
	}
	
	@Test
	public void testAppendCloseBracket() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendCloseBracket();
		Assert.assertEquals("]", builder.toString());
	}
	
	@Test(expected=IOException.class)
	public void testAppendCloseBracketWithException() throws IOException {
		final JsonAppender appender = new JsonAppender(new ThrowingAppendable(), new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendCloseBracket();
	}
	
	@Test
	public void testAppendEmptyObject() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendEmptyObject();
		Assert.assertEquals("{}", builder.toString());
	}
	
	@Test(expected=IOException.class)
	public void testAppendEmptyObjectWithException() throws IOException {
		final JsonAppender appender = new JsonAppender(new ThrowingAppendable(), new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendEmptyObject();
	}
	
	@Test
	public void testAppendEmptyArray() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendEmptyArray();
		Assert.assertEquals("[]", builder.toString());
	}
	
	@Test(expected=IOException.class)
	public void testAppendEmptyArrayWithException() throws IOException {
		final JsonAppender appender = new JsonAppender(new ThrowingAppendable(), new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendEmptyArray();
	}
	
	@Test
	public void testAppendQuote() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendQuote();
		Assert.assertEquals("\"", builder.toString());
	}
	
	@Test(expected=IOException.class)
	public void testAppendQuoteWithException() throws IOException {
		final JsonAppender appender = new JsonAppender(new ThrowingAppendable(), new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendQuote();
	}
	
	@Test
	public void testAppendCloseKey() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendCloseKey();
		Assert.assertEquals("\":", builder.toString());
	}
	
	@Test(expected=IOException.class)
	public void testAppendCloseKeyWithException() throws IOException {
		final JsonAppender appender = new JsonAppender(new ThrowingAppendable(), new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendCloseKey();
	}
	
	@Test
	public void testAppendNull() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendNull();
		Assert.assertEquals("null", builder.toString());
	}
	
	@Test(expected=IOException.class)
	public void testAppendNullWithException() throws IOException {
		final JsonAppender appender = new JsonAppender(new ThrowingAppendable(), new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendNull();
	}
	
	@Test
	public void testAppendTrue() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendTrue();
		Assert.assertEquals("true", builder.toString());
	}
	
	@Test(expected=IOException.class)
	public void testAppendTrueWithException() throws IOException {
		final JsonAppender appender = new JsonAppender(new ThrowingAppendable(), new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendTrue();
	}
	
	@Test
	public void testAppendFalse() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonAppender appender = new JsonAppender(builder, new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendFalse();
		Assert.assertEquals("false", builder.toString());
	}
	
	@Test(expected=IOException.class)
	public void testAppendFalseWithException() throws IOException {
		final JsonAppender appender = new JsonAppender(new ThrowingAppendable(), new MockEscaper(MOCK_ESCAPE_CHARACTER));
		appender.appendFalse();
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
		appender.appendNumber(0.12345);
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
