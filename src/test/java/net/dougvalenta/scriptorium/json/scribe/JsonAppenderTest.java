/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json.scribe;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import net.dougvalenta.scriptorium.Escaper;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

/**
 *
 * @author Doug Valenta
 */
public class JsonAppenderTest {
	
	@Test
	public void testAppendComma() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final Escaper escaper = Mockito.mock(Escaper.class, Mockito.RETURNS_SELF);
		final JsonAppender appender = new JsonAppender(builder, escaper);
		final JsonAppender result = appender.appendComma();
		Assert.assertEquals(appender, result);
		Assert.assertEquals(",", builder.toString());
		Mockito.verifyZeroInteractions(escaper);
	}
	
	@Test
	public void testAppendCommaWithTrue() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final Escaper escaper = Mockito.mock(Escaper.class, Mockito.RETURNS_SELF);
		final JsonAppender appender = new JsonAppender(builder, escaper);
		final JsonAppender result = appender.appendComma(true);
		Assert.assertEquals(appender, result);
		Assert.assertEquals(",", builder.toString());
		Mockito.verifyZeroInteractions(escaper);
	}
	
	@Test
	public void testAppendCommaWithFalse() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final Escaper escaper = Mockito.mock(Escaper.class, Mockito.RETURNS_SELF);
		final JsonAppender appender = new JsonAppender(builder, escaper);
		final JsonAppender result = appender.appendComma(false);
		Assert.assertEquals(appender, result);
		Assert.assertEquals(0, builder.length());
		Mockito.verifyZeroInteractions(escaper);
	}
	
	@Test
	public void testAppendOpenBrace() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final Escaper escaper = Mockito.mock(Escaper.class, Mockito.RETURNS_SELF);
		final JsonAppender appender = new JsonAppender(builder, escaper);
		final JsonAppender result = appender.appendOpenBrace();
		Assert.assertEquals(appender, result);
		Assert.assertEquals("{", builder.toString());
		Mockito.verifyZeroInteractions(escaper);
	}
	
	@Test
	public void testAppendCloseBrace() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final Escaper escaper = Mockito.mock(Escaper.class, Mockito.RETURNS_SELF);
		final JsonAppender appender = new JsonAppender(builder, escaper);
		final JsonAppender result = appender.appendCloseBrace();
		Assert.assertEquals(appender, result);
		Assert.assertEquals("}", builder.toString());
		Mockito.verifyZeroInteractions(escaper);
	}
	
	@Test
	public void testAppendOpenBracket() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final Escaper escaper = Mockito.mock(Escaper.class, Mockito.RETURNS_SELF);
		final JsonAppender appender = new JsonAppender(builder, escaper);
		final JsonAppender result = appender.appendOpenBracket();
		Assert.assertEquals(appender, result);
		Assert.assertEquals("[", builder.toString());
		Mockito.verifyZeroInteractions(escaper);
	}
	
	@Test
	public void testAppendCloseBracket() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final Escaper escaper = Mockito.mock(Escaper.class, Mockito.RETURNS_SELF);
		final JsonAppender appender = new JsonAppender(builder, escaper);
		final JsonAppender result = appender.appendCloseBracket();
		Assert.assertEquals(appender, result);
		Assert.assertEquals("]", builder.toString());
		Mockito.verifyZeroInteractions(escaper);
	}
	
	@Test
	public void testAppendQuote() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final Escaper escaper = Mockito.mock(Escaper.class, Mockito.RETURNS_SELF);
		final JsonAppender appender = new JsonAppender(builder, escaper);
		final JsonAppender result = appender.appendQuote();
		Assert.assertEquals(appender, result);
		Assert.assertEquals("\"", builder.toString());
		Mockito.verifyZeroInteractions(escaper);
	}
	
	@Test
	public void testAppendCloseKey() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final Escaper escaper = Mockito.mock(Escaper.class, Mockito.RETURNS_SELF);
		final JsonAppender appender = new JsonAppender(builder, escaper);
		final JsonAppender result = appender.appendCloseKey();
		Assert.assertEquals(appender, result);
		Assert.assertEquals("\":", builder.toString());
		Mockito.verifyZeroInteractions(escaper);
	}
	
	@Test
	public void testAppendEmptyArray() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final Escaper escaper = Mockito.mock(Escaper.class, Mockito.RETURNS_SELF);
		final JsonAppender appender = new JsonAppender(builder, escaper);
		final JsonAppender result = appender.appendEmptyArray();
		Assert.assertEquals(appender, result);
		Assert.assertEquals("[]", builder.toString());
		Mockito.verifyZeroInteractions(escaper);
	}
	
	@Test
	public void testAppendEmptyObject() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final Escaper escaper = Mockito.mock(Escaper.class, Mockito.RETURNS_SELF);
		final JsonAppender appender = new JsonAppender(builder, escaper);
		final JsonAppender result = appender.appendEmptyObject();
		Assert.assertEquals(appender, result);
		Assert.assertEquals("{}", builder.toString());
		Mockito.verifyZeroInteractions(escaper);
	}
	
	@Test
	public void testAppendNull() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final Escaper escaper = Mockito.mock(Escaper.class, Mockito.RETURNS_SELF);
		final JsonAppender appender = new JsonAppender(builder, escaper);
		final JsonAppender result = appender.appendNull();
		Assert.assertEquals(appender, result);
		Assert.assertEquals("null", builder.toString());
		Mockito.verifyZeroInteractions(escaper);
	}
	
	@Test
	public void testAppendTrue() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final Escaper escaper = Mockito.mock(Escaper.class, Mockito.RETURNS_SELF);
		final JsonAppender appender = new JsonAppender(builder, escaper);
		final JsonAppender result = appender.appendTrue();
		Assert.assertEquals(appender, result);
		Assert.assertEquals("true", builder.toString());
		Mockito.verifyZeroInteractions(escaper);
	}
	
	@Test
	public void testAppendFalse() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final Escaper escaper = Mockito.mock(Escaper.class, Mockito.RETURNS_SELF);
		final JsonAppender appender = new JsonAppender(builder, escaper);
		final JsonAppender result = appender.appendFalse();
		Assert.assertEquals(appender, result);
		Assert.assertEquals("false", builder.toString());
		Mockito.verifyZeroInteractions(escaper);
	}
	
	@Test
	public void testAppendNumberWithBigInteger() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final Escaper escaper = Mockito.mock(Escaper.class, Mockito.RETURNS_SELF);
		final JsonAppender appender = new JsonAppender(builder, escaper);
		final JsonAppender result = appender.appendNumber(new BigInteger("12345"));
		Assert.assertEquals(appender, result);
		Assert.assertEquals("12345", builder.toString());
		Mockito.verifyZeroInteractions(escaper);
	}
	
	@Test
	public void testAppendNumberWithBigDecimal() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final Escaper escaper = Mockito.mock(Escaper.class, Mockito.RETURNS_SELF);
		final JsonAppender appender = new JsonAppender(builder, escaper);
		final JsonAppender result = appender.appendNumber(new BigDecimal("1.2345"));
		Assert.assertEquals(appender, result);
		Assert.assertEquals("1.2345", builder.toString());
		Mockito.verifyZeroInteractions(escaper);
	}
	
	@Test
	public void testAppendNumberWithInt() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final Escaper escaper = Mockito.mock(Escaper.class, Mockito.RETURNS_SELF);
		final JsonAppender appender = new JsonAppender(builder, escaper);
		final JsonAppender result = appender.appendNumber(12345);
		Assert.assertEquals(appender, result);
		Assert.assertEquals("12345", builder.toString());
		Mockito.verifyZeroInteractions(escaper);
	}
	
	@Test
	public void testAppendNumberWithLong() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final Escaper escaper = Mockito.mock(Escaper.class, Mockito.RETURNS_SELF);
		final JsonAppender appender = new JsonAppender(builder, escaper);
		final JsonAppender result = appender.appendNumber(12345L);
		Assert.assertEquals(appender, result);
		Assert.assertEquals("12345", builder.toString());
		Mockito.verifyZeroInteractions(escaper);
	}
	
	@Test
	public void testAppendNumberWithFloat() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final Escaper escaper = Mockito.mock(Escaper.class, Mockito.RETURNS_SELF);
		final JsonAppender appender = new JsonAppender(builder, escaper);
		final JsonAppender result = appender.appendNumber(1.2345f);
		Assert.assertEquals(appender, result);
		Assert.assertEquals("1.2345", builder.toString());
		Mockito.verifyZeroInteractions(escaper);
	}
	
	@Test
	public void testAppendNumberWithDouble() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final Escaper escaper = Mockito.mock(Escaper.class, Mockito.RETURNS_SELF);
		final JsonAppender appender = new JsonAppender(builder, escaper);
		final JsonAppender result = appender.appendNumber(1.2345);
		Assert.assertEquals(appender, result);
		Assert.assertEquals("1.2345", builder.toString());
		Mockito.verifyZeroInteractions(escaper);
	}
	
	@Test
	public void testAppendBooleanWithTrue() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final Escaper escaper = Mockito.mock(Escaper.class, Mockito.RETURNS_SELF);
		final JsonAppender appender = new JsonAppender(builder, escaper);
		final JsonAppender result = appender.appendBoolean(true);
		Assert.assertEquals(appender, result);
		Assert.assertEquals("true", builder.toString());
		Mockito.verifyZeroInteractions(escaper);
	}
	
	@Test
	public void testAppendBooleanWithFalse() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final Escaper escaper = Mockito.mock(Escaper.class, Mockito.RETURNS_SELF);
		final JsonAppender appender = new JsonAppender(builder, escaper);
		final JsonAppender result = appender.appendBoolean(false);
		Assert.assertEquals(appender, result);
		Assert.assertEquals("false", builder.toString());
		Mockito.verifyZeroInteractions(escaper);
	}
	
	@Test
	public void testEscapeChar() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final Escaper escaper = Mockito.mock(Escaper.class, Mockito.RETURNS_SELF);
		final JsonAppender appender = new JsonAppender(builder, escaper);
		final JsonAppender result = appender.escape('a');
		Assert.assertEquals(appender, result);
		Assert.assertEquals(0, builder.length());
		Mockito.verify(escaper).escape('a', builder);
		Mockito.verifyNoMoreInteractions(escaper);
	}
	
	@Test
	public void testEscapeCharSequence() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final Escaper escaper = Mockito.mock(Escaper.class, Mockito.RETURNS_SELF);
		final JsonAppender appender = new JsonAppender(builder, escaper);
		final JsonAppender result = appender.escape("abc");
		Assert.assertEquals(appender, result);
		Assert.assertEquals(0, builder.length());
		InOrder inOrder = Mockito.inOrder(escaper);
		inOrder.verify(escaper).escape('a', builder);
		inOrder.verify(escaper).escape('b', builder);
		inOrder.verify(escaper).escape('c', builder);
		Mockito.verifyNoMoreInteractions(escaper);
	}
	
	@Test
	public void testEscapeCharSequenceWithIndex() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final Escaper escaper = Mockito.mock(Escaper.class, Mockito.RETURNS_SELF);
		final JsonAppender appender = new JsonAppender(builder, escaper);
		final JsonAppender result = appender.escape("abcde", 1, 4);
		Assert.assertEquals(appender, result);
		Assert.assertEquals(0, builder.length());
		InOrder inOrder = Mockito.inOrder(escaper);
		inOrder.verify(escaper).escape('b', builder);
		inOrder.verify(escaper).escape('c', builder);
		inOrder.verify(escaper).escape('d', builder);
		Mockito.verifyNoMoreInteractions(escaper);
	}
	
}
