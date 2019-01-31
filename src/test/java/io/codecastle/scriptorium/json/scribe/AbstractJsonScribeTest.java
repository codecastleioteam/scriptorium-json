/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json.scribe;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import io.codecastle.scriptorium.FluentNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

/**
 *
 * @author Doug Valenta
 */
public abstract class AbstractJsonScribeTest {
	
	protected abstract JsonScribe getScribe(JsonAppender appender);
	
	@Test
	public void testPushArrayAndPop() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		JsonScribe result = scribe.pushArray();
		Assertions.assertEquals(scribe, result);
		Mockito.verify(appender).appendOpenBracket();
		Mockito.verifyNoMoreInteractions(appender);
		result = scribe.pop();
		Assertions.assertEquals(scribe, result);
		Mockito.verify(appender).appendCloseBracket();
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPushArrayAndNullsAndPop() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		JsonScribe result = scribe.pushArray().nullValue().nullValue().nullValue().pop();
		Assertions.assertEquals(scribe, result);
		InOrder inOrder = Mockito.inOrder(appender);
		inOrder.verify(appender).appendOpenBracket();
		inOrder.verify(appender).appendNull();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNull();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNull();
		inOrder.verify(appender).appendCloseBracket();
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPushArrayAndCharSequencesAndPop() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		JsonScribe result = scribe.pushArray().value("abc").value("123").value("xyz").pop();
		Assertions.assertEquals(scribe, result);
		InOrder inOrder = Mockito.inOrder(appender);
		inOrder.verify(appender).appendOpenBracket();
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).escape("abc");
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).escape("123");
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).escape("xyz");
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).appendCloseBracket();
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPushArrayAndCharsAndPop() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		JsonScribe result = scribe.pushArray().value('a').value('b').value('c').pop();
		Assertions.assertEquals(scribe, result);
		InOrder inOrder = Mockito.inOrder(appender);
		inOrder.verify(appender).appendOpenBracket();
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).escape('a');
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).escape('b');
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).escape('c');
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).appendCloseBracket();
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPushArrayAndBigIntegersAndPop() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		JsonScribe result = scribe.pushArray().value(new BigInteger("1")).value(new BigInteger("2")).value(new BigInteger("3")).pop();
		Assertions.assertEquals(scribe, result);
		InOrder inOrder = Mockito.inOrder(appender);
		inOrder.verify(appender).appendOpenBracket();
		inOrder.verify(appender).appendNumber(new BigInteger("1"));
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNumber(new BigInteger("2"));
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNumber(new BigInteger("3"));
		inOrder.verify(appender).appendCloseBracket();
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPushArrayAndBigDecimalsAndPop() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		JsonScribe result = scribe.pushArray().value(new BigDecimal("1.0")).value(new BigDecimal("2.0")).value(new BigDecimal("3.0")).pop();
		Assertions.assertEquals(scribe, result);
		InOrder inOrder = Mockito.inOrder(appender);
		inOrder.verify(appender).appendOpenBracket();
		inOrder.verify(appender).appendNumber(new BigDecimal("1.0"));
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNumber(new BigDecimal("2.0"));
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNumber(new BigDecimal("3.0"));
		inOrder.verify(appender).appendCloseBracket();
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPushArrayAndIntsAndPop() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		JsonScribe result = scribe.pushArray().value(1).value(2).value(3).pop();
		Assertions.assertEquals(scribe, result);
		InOrder inOrder = Mockito.inOrder(appender);
		inOrder.verify(appender).appendOpenBracket();
		inOrder.verify(appender).appendNumber(1);
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNumber(2);
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNumber(3);
		inOrder.verify(appender).appendCloseBracket();
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPushArrayAndLongsAndPop() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		JsonScribe result = scribe.pushArray().value(1l).value(2l).value(3l).pop();
		Assertions.assertEquals(scribe, result);
		InOrder inOrder = Mockito.inOrder(appender);
		inOrder.verify(appender).appendOpenBracket();
		inOrder.verify(appender).appendNumber(1l);
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNumber(2l);
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNumber(3l);
		inOrder.verify(appender).appendCloseBracket();
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPushArrayAndFloatsAndPop() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		JsonScribe result = scribe.pushArray().value(1.0f).value(2.0f).value(3.0f).pop();
		Assertions.assertEquals(scribe, result);
		InOrder inOrder = Mockito.inOrder(appender);
		inOrder.verify(appender).appendOpenBracket();
		inOrder.verify(appender).appendNumber(1.0f);
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNumber(2.0f);
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNumber(3.0f);
		inOrder.verify(appender).appendCloseBracket();
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPushArrayAndDoublesAndPop() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		JsonScribe result = scribe.pushArray().value(1.0).value(2.0).value(3.0).pop();
		Assertions.assertEquals(scribe, result);
		InOrder inOrder = Mockito.inOrder(appender);
		inOrder.verify(appender).appendOpenBracket();
		inOrder.verify(appender).appendNumber(1.0);
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNumber(2.0);
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNumber(3.0);
		inOrder.verify(appender).appendCloseBracket();
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPushArrayAndNonFiniteFloatsAndPop() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		JsonScribe result = scribe.pushArray().value(Float.NaN).value(Float.POSITIVE_INFINITY).value(Float.NEGATIVE_INFINITY).pop();
		Assertions.assertEquals(scribe, result);
		InOrder inOrder = Mockito.inOrder(appender);
		inOrder.verify(appender).appendOpenBracket();
		inOrder.verify(appender).appendNull();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNull();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNull();
		inOrder.verify(appender).appendCloseBracket();
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPushArrayAndNonFiniteDoublesAndPop() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		JsonScribe result = scribe.pushArray().value(Double.NaN).value(Double.POSITIVE_INFINITY).value(Double.NEGATIVE_INFINITY).pop();
		Assertions.assertEquals(scribe, result);
		InOrder inOrder = Mockito.inOrder(appender);
		inOrder.verify(appender).appendOpenBracket();
		inOrder.verify(appender).appendNull();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNull();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNull();
		inOrder.verify(appender).appendCloseBracket();
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPushArrayAndBooleansAndPop() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		JsonScribe result = scribe.pushArray().value(true).value(false).value(true).pop();
		Assertions.assertEquals(scribe, result);
		InOrder inOrder = Mockito.inOrder(appender);
		inOrder.verify(appender).appendOpenBracket();
		inOrder.verify(appender).appendBoolean(true);
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendBoolean(false);
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendBoolean(true);
		inOrder.verify(appender).appendCloseBracket();
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPushArrayAndTruesAndPop() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		JsonScribe result = scribe.pushArray().trueValue().trueValue().trueValue().pop();
		Assertions.assertEquals(scribe, result);
		InOrder inOrder = Mockito.inOrder(appender);
		inOrder.verify(appender).appendOpenBracket();
		inOrder.verify(appender).appendTrue();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendTrue();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendTrue();
		inOrder.verify(appender).appendCloseBracket();
		Mockito.verifyNoMoreInteractions(appender);
		inOrder.verifyNoMoreInteractions();
	}
	
	@Test
	public void testPushArrayAndFalsesAndPop() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		JsonScribe result = scribe.pushArray().falseValue().falseValue().falseValue().pop();
		Assertions.assertEquals(scribe, result);
		InOrder inOrder = Mockito.inOrder(appender);
		inOrder.verify(appender).appendOpenBracket();
		inOrder.verify(appender).appendFalse();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendFalse();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendFalse();
		inOrder.verify(appender).appendCloseBracket();
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPushArrayAndObjectsAndPop() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		JsonScribe result = scribe.pushArray()
				.value((Object) "abc")
				.value((Object) 'a')
				.value((Object) BigInteger.ONE)
				.value((Object) BigDecimal.ONE)
				.value((Object) 1)
				.value((Object) 1L)
				.value((Object) 1f)
				.value((Object) 1.0)
				.value((Object) Float.NaN)
				.value((Object) Double.NaN)
				.value((Object) true)
				.value((Object) null)
				.value((Object) (byte) 1)
				.value((Object) (short) 1)
				.pop();
		Assertions.assertEquals(scribe, result);
		InOrder inOrder = Mockito.inOrder(appender);
		inOrder.verify(appender).appendOpenBracket();
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).escape("abc");
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).escape('a');
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNumber(BigInteger.ONE);
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNumber(BigDecimal.ONE);
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNumber(1);
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNumber(1L);
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNumber(1f);
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNumber(1.0);
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNull();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNull();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendBoolean(true);
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNull();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNumber(1);
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNumber(1);
		inOrder.verify(appender).appendCloseBracket();
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPushArrayAndEmptyArraysAndPop() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		JsonScribe result = scribe.pushArray().emptyArray().emptyArray().emptyArray().pop();
		Assertions.assertEquals(scribe, result);
		InOrder inOrder = Mockito.inOrder(appender);
		inOrder.verify(appender).appendOpenBracket();
		inOrder.verify(appender).appendEmptyArray();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendEmptyArray();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendEmptyArray();
		inOrder.verify(appender).appendCloseBracket();
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPushArrayAndEmptyObjectsAndPop() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		JsonScribe result = scribe.pushArray().emptyObject().emptyObject().emptyObject().pop();
		Assertions.assertEquals(scribe, result);
		InOrder inOrder = Mockito.inOrder(appender);
		inOrder.verify(appender).appendOpenBracket();
		inOrder.verify(appender).appendEmptyObject();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendEmptyObject();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendEmptyObject();
		inOrder.verify(appender).appendCloseBracket();
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPushArrayAndPushValuesAndPopAll() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		JsonScribe result = scribe.pushArray().pushValue().pop().pushValue()
				.append("abc")
				.append('x')
				.append("xyzq", 1, 3)
				.pop().pop();
		Assertions.assertEquals(scribe, result);
		InOrder inOrder = Mockito.inOrder(appender);
		inOrder.verify(appender).appendOpenBracket();
		inOrder.verify(appender, Mockito.times(2)).appendQuote();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).escape("abc", 0, 3);
		inOrder.verify(appender).escape('x');
		inOrder.verify(appender).escape("xyzq", 1, 3);
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).appendCloseBracket();
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPushArrayAndPushArraysAndPopAll() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		JsonScribe result = scribe.pushArray().pushArray().pop().pushArray()
				.value("abc")
				.value(99)
				.value(false)
				.pop().pop();
		Assertions.assertEquals(scribe, result);
		InOrder inOrder = Mockito.inOrder(appender);
		inOrder.verify(appender, Mockito.times(2)).appendOpenBracket();
		inOrder.verify(appender).appendCloseBracket();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendOpenBracket();
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).escape("abc");
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendNumber(99);
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendBoolean(false);
		inOrder.verify(appender, Mockito.times(2)).appendCloseBracket();
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPushObjectAndPop() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		JsonScribe result = scribe.pushObject().pop();
		Assertions.assertEquals(scribe, result);
		InOrder inOrder = Mockito.inOrder(appender);
		inOrder.verify(appender).appendOpenBrace();
		inOrder.verify(appender).appendCloseBrace();
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPushObjectAndKeyValuesAndPop() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		JsonScribe result = scribe.pushObject()
				.key("key1")
				.value("value")
				.key("key2")
				.value(1)
				.key("key3")
				.nullValue().pop();
		Assertions.assertEquals(scribe, result);
		InOrder inOrder = Mockito.inOrder(appender);
		inOrder.verify(appender).appendOpenBrace();
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).escape("key1");
		inOrder.verify(appender).appendCloseKey();
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).escape("value");
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).escape("key2");
		inOrder.verify(appender).appendCloseKey();
		inOrder.verify(appender).appendNumber(1);
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).escape("key3");
		inOrder.verify(appender).appendCloseKey();
		inOrder.verify(appender).appendNull();
		inOrder.verify(appender).appendCloseBrace();
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPushObjectAndPushKeysAndValuesAndPop() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		JsonScribe result = scribe.pushObject()
				.pushKey()
					.append("key1")
					.pop()
				.pushValue()
					.append("value")
					.pop()
				.pushKey()
					.append('a')
					.append('b')
					.append('c')
					.pop()
				.pushValue()
					.append("value")
					.pop()
				.pop();
		Assertions.assertEquals(scribe, result);
		InOrder inOrder = Mockito.inOrder(appender);
		inOrder.verify(appender).appendOpenBrace();
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).escape("key1", 0, 4);
		inOrder.verify(appender).appendCloseKey();
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).escape("value", 0, 5);
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).escape('a');
		inOrder.verify(appender).escape('b');
		inOrder.verify(appender).escape('c');
		inOrder.verify(appender).appendCloseKey();
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).escape("value", 0, 5);
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).appendCloseBrace();
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPushObjectsAndArraysAndValuesAndClose() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		JsonScribe result = scribe.pushObject()
				.key("key").pushArray()
					.pushObject()
						.pop()
					.pushObject()
						.key("key").pushArray()
							.pushValue();
		Assertions.assertEquals(scribe, result);
		result.close();
		InOrder inOrder = Mockito.inOrder(appender);
		inOrder.verify(appender).appendOpenBrace();
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).escape("key");
		inOrder.verify(appender).appendCloseKey();
		inOrder.verify(appender).appendOpenBracket();
		inOrder.verify(appender).appendOpenBrace();
		inOrder.verify(appender).appendCloseBrace();
		inOrder.verify(appender).appendComma();
		inOrder.verify(appender).appendOpenBrace();
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).escape("key");
		inOrder.verify(appender).appendCloseKey();
		inOrder.verify(appender).appendOpenBracket();
		inOrder.verify(appender, Mockito.times(2)).appendQuote();
		inOrder.verify(appender).appendCloseBracket();
		inOrder.verify(appender).appendCloseBrace();
		inOrder.verify(appender).appendCloseBracket();
		inOrder.verify(appender).appendCloseBrace();
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPushObjectsAndArraysAndValuesAndPopToCursor() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		JsonScribe result = scribe.pushObject();
		Assertions.assertEquals(scribe, result);
		final int cursor = result.getCursor();
		result.key("key").pushArray()
					.pushObject()
						.key("key").pushArray()
							.pushValue();
		Assertions.assertEquals(scribe, result);
		Assertions.assertNotEquals(cursor, result.getCursor());
		result.pop(cursor);
		Assertions.assertEquals(cursor, result.getCursor());
		InOrder inOrder = Mockito.inOrder(appender);
		inOrder.verify(appender).appendOpenBrace();
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).escape("key");
		inOrder.verify(appender).appendCloseKey();
		inOrder.verify(appender).appendOpenBracket();
		inOrder.verify(appender).appendOpenBrace();
		inOrder.verify(appender).appendQuote();
		inOrder.verify(appender).escape("key");
		inOrder.verify(appender).appendCloseKey();
		inOrder.verify(appender).appendOpenBracket();
		inOrder.verify(appender, Mockito.times(2)).appendQuote();
		inOrder.verify(appender).appendCloseBracket();
		inOrder.verify(appender).appendCloseBrace();
		inOrder.verify(appender).appendCloseBracket();
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPopWithCurrentCursor() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushArray();
		final int cursor = scribe.getCursor();
		Mockito.clearInvocations(appender);
		final JsonScribe result = scribe.pop(cursor);
		Assertions.assertEquals(scribe, result);
		Mockito.verifyZeroInteractions(appender);
		Assertions.assertEquals(cursor, scribe.getCursor());
	}
	
	@Test
	public void testPushArrayAndObjectsWithInvalidType() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		Assertions.assertThrows(IllegalArgumentException.class, ()->{getScribe(appender).pushArray().value(new Object());});
	}
	
	@Test
	public void testPushInscriptionAndPop() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		scribe.pushArray();
		Mockito.clearInvocations(appender);
		final FluentNode<Void> inscription = Mockito.mock(FluentNode.class);
		final JsonScribe result = scribe.pushInscription(inscription);
		Assertions.assertEquals(scribe, result);
		Mockito.verifyZeroInteractions(appender);
		Mockito.verifyZeroInteractions(inscription);
		scribe.pop();
		final InOrder inOrder = Mockito.inOrder(appender, inscription);
		inOrder.verify(inscription).close();
		inOrder.verify(appender).appendCloseBracket();
		Mockito.verifyNoMoreInteractions(appender);
		Mockito.verifyNoMoreInteractions(inscription);
	}
	
	@Test
	public void testPushInscriptionAndPushSameInscriptionAndPop() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		scribe.pushArray();
		Mockito.clearInvocations(appender);
		final FluentNode<Void> inscription = Mockito.mock(FluentNode.class);
		final JsonScribe result = scribe.pushInscription(inscription);
		Assertions.assertEquals(scribe, result);
		scribe.pushInscription(inscription);
		Mockito.verifyZeroInteractions(appender);
		Mockito.verifyZeroInteractions(inscription);
		scribe.pop();
		final InOrder inOrder = Mockito.inOrder(appender, inscription);
		inOrder.verify(inscription).close();
		inOrder.verify(appender).appendCloseBracket();
		Mockito.verifyNoMoreInteractions(appender);
		Mockito.verifyNoMoreInteractions(inscription);
	}
	
	@Test
	public void testPushInscriptionAndPopAndPushDifferentInscriptionAndPop() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		scribe.pushArray();
		Mockito.clearInvocations(appender);
		final FluentNode<Void> inscriptionA = Mockito.mock(FluentNode.class);
		final JsonScribe resultA = scribe.pushInscription(inscriptionA);
		Assertions.assertEquals(scribe, resultA);
		scribe.pushInscription(inscriptionA);
		Mockito.verifyZeroInteractions(appender);
		Mockito.verifyZeroInteractions(inscriptionA);
		scribe.pop();
		final InOrder inOrderA = Mockito.inOrder(appender, inscriptionA);
		inOrderA.verify(inscriptionA).close();
		inOrderA.verify(appender).appendCloseBracket();
		Mockito.verifyNoMoreInteractions(appender);
		Mockito.verifyNoMoreInteractions(inscriptionA);
		scribe.pushArray();
		Mockito.clearInvocations(appender);
		final FluentNode<Void> inscriptionB = Mockito.mock(FluentNode.class);
		final JsonScribe resultB = scribe.pushInscription(inscriptionB);
		Assertions.assertEquals(scribe, resultB);
		scribe.pushInscription(inscriptionB);
		Mockito.verifyZeroInteractions(appender);
		Mockito.verifyZeroInteractions(inscriptionB);
		scribe.pop();
		final InOrder inOrderB = Mockito.inOrder(appender, inscriptionB);
		inOrderB.verify(inscriptionB).close();
		inOrderB.verify(appender).appendCloseBracket();
		Mockito.verifyNoMoreInteractions(appender);
		Mockito.verifyNoMoreInteractions(inscriptionB);
	}
	
}
