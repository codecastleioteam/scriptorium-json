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
import org.mockito.Mockito;

/**
 *
 * @author Doug Valenta
 */
public class SafeJsonScribeTest extends AbstractJsonScribeTest {
	
	@Override
	protected JsonScribe getScribe(final JsonAppender appender) {
		return new SafeJsonScribe(appender);
	}
	
	@Test
	public void testPopWhenEmpty() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		try {
			scribe.pop();
		} catch (IllegalStateException e) {
			Mockito.verifyZeroInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testPopAfterPushAndPop() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushArray().pop();
		Mockito.verify(appender).appendOpenBracket();
		Mockito.verify(appender).appendCloseBracket();
		try {
			scribe.pop();
		} catch (IllegalStateException e) {
			Mockito.verifyZeroInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testPopWithNegativeCursor() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushArray();
		Mockito.verify(appender).appendOpenBracket();
		try {
			scribe.pop(-1);
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testPopWithZeroCursor() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushArray();
		Mockito.verify(appender).appendOpenBracket();
		Mockito.verifyNoMoreInteractions(appender);
		final JsonScribe result = scribe.pop(0);
		Assert.assertEquals(scribe, result);
		Mockito.verify(appender).appendCloseBracket();
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPopWithFutureCursor() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushArray();
		Mockito.verify(appender).appendOpenBracket();
		try {
			scribe.pop(99);
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testPushValueWhenEmpty() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		try {
			scribe.pushValue();
		} catch (IllegalStateException e) {
			Mockito.verifyZeroInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testNullValueWhenEmpty() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		try {
			scribe.nullValue();
		} catch (IllegalStateException e) {
			Mockito.verifyZeroInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testFalseValueWhenEmpty() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		try {
			scribe.trueValue();
		} catch (IllegalStateException e) {
			Mockito.verifyZeroInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testTrueValueWhenEmpty() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		try {
			scribe.trueValue();
		} catch (IllegalStateException e) {
			Mockito.verifyZeroInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testValueWithCharSequenceWhenEmpty() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		try {
			scribe.value("abc");
		} catch (IllegalStateException e) {
			Mockito.verifyZeroInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testValueWithCharWhenEmpty() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		try {
			scribe.value('a');
		} catch (IllegalStateException e) {
			Mockito.verifyZeroInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testValueWithBigIntegerWhenEmpty() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		try {
			scribe.value(BigInteger.ONE);
		} catch (IllegalStateException e) {
			Mockito.verifyZeroInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testValueWithBigDecimalWhenEmpty() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		try {
			scribe.value(BigDecimal.ONE);
		} catch (IllegalStateException e) {
			Mockito.verifyZeroInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testValueWithIntWhenEmpty() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		try {
			scribe.value(1);
		} catch (IllegalStateException e) {
			Mockito.verifyZeroInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testValueWithLongWhenEmpty() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		try {
			scribe.value(1L);
		} catch (IllegalStateException e) {
			Mockito.verifyZeroInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testValueWithFloatWhenEmpty() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		try {
			scribe.value(1f);
		} catch (IllegalStateException e) {
			Mockito.verifyZeroInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testValueWithDoubleWhenEmpty() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		try {
			scribe.value(1.0);
		} catch (IllegalStateException e) {
			Mockito.verifyZeroInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testValueWithBooleanWhenEmpty() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		try {
			scribe.value(true);
		} catch (IllegalStateException e) {
			Mockito.verifyZeroInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testKeyWhenEmpty() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		try {
			scribe.key("abc");
		} catch (IllegalStateException e) {
			Mockito.verifyZeroInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testPushKeyWhenEmpty() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		try {
			scribe.pushKey();
		} catch (IllegalStateException e) {
			Mockito.verifyZeroInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testPushArrayInObjectWithoutKey() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject();
		Mockito.verify(appender).appendOpenBrace();
		try {
			scribe.pushArray();
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testPushObjectInObjectWithoutKey() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject();
		Mockito.verify(appender).appendOpenBrace();
		try {
			scribe.pushObject();
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testPushValueInObjectWithoutKey() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject();
		Mockito.verify(appender).appendOpenBrace();
		try {
			scribe.pushValue();
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testEmptyArrayInObjectWithoutKey() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject();
		Mockito.verify(appender).appendOpenBrace();
		try {
			scribe.emptyArray();
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testEmptyObjectInObjectWithoutKey() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject();
		Mockito.verify(appender).appendOpenBrace();
		try {
			scribe.emptyObject();
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testNullValueInObjectWithoutKey() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject();
		Mockito.verify(appender).appendOpenBrace();
		try {
			scribe.nullValue();
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testTrueValueInObjectWithoutKey() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject();
		Mockito.verify(appender).appendOpenBrace();
		try {
			scribe.trueValue();
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testFalseValueInObjectWithoutKey() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject();
		Mockito.verify(appender).appendOpenBrace();
		try {
			scribe.falseValue();
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testValueWithCharSequenceInObjectWithoutKey() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject();
		Mockito.verify(appender).appendOpenBrace();
		try {
			scribe.value("abc");
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testValueWithCharInObjectWithoutKey() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject();
		Mockito.verify(appender).appendOpenBrace();
		try {
			scribe.value('a');
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testValueWithBigIntegerInObjectWithoutKey() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject();
		Mockito.verify(appender).appendOpenBrace();
		try {
			scribe.value(BigInteger.ONE);
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testValueWithBigDecimalInObjectWithoutKey() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject();
		Mockito.verify(appender).appendOpenBrace();
		try {
			scribe.value(BigDecimal.ONE);
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testValueWithIntInObjectWithoutKey() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject();
		Mockito.verify(appender).appendOpenBrace();
		try {
			scribe.value(1);
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}

	@Test
	public void testValueWithLongInObjectWithoutKey() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject();
		Mockito.verify(appender).appendOpenBrace();
		try {
			scribe.value(1L);
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testValueWithFloatInObjectWithoutKey() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject();
		Mockito.verify(appender).appendOpenBrace();
		try {
			scribe.value(1f);
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testValueWithDoubleInObjectWithoutKey() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject();
		Mockito.verify(appender).appendOpenBrace();
		try {
			scribe.value(1.0);
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testValueWithBooleanInObjectWithoutKey() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject();
		Mockito.verify(appender).appendOpenBrace();
		try {
			scribe.value(1.0);
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testKeyAfterPushKeyAndPop() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject().pushKey().append("key").pop();
		Mockito.verify(appender).appendOpenBrace();
		Mockito.verify(appender).appendQuote();
		Mockito.verify(appender).escape("key", 0, 3);
		Mockito.verify(appender).appendCloseKey();
		try {
			scribe.key("abc");
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testPushKeyAfterPushKeyAndPop() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject().pushKey().append("key").pop();
		Mockito.verify(appender).appendOpenBrace();
		Mockito.verify(appender).appendQuote();
		Mockito.verify(appender).escape("key", 0, 3);
		Mockito.verify(appender).appendCloseKey();
		try {
			scribe.pushKey();
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testPushKeyAfterKey() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject().key("key");
		Mockito.verify(appender).appendOpenBrace();
		Mockito.verify(appender).appendQuote();
		Mockito.verify(appender).escape("key");
		Mockito.verify(appender).appendCloseKey();
		try {
			scribe.pushKey();
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testKeyAfterKey() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject().key("key");
		Mockito.verify(appender).appendOpenBrace();
		Mockito.verify(appender).appendQuote();
		Mockito.verify(appender).escape("key");
		Mockito.verify(appender).appendCloseKey();
		try {
			scribe.key("abc");
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testKeyInArray() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushArray();
		Mockito.verify(appender).appendOpenBracket();
		try {
			scribe.key("abc");
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testPushKeyInArray() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushArray();
		Mockito.verify(appender).appendOpenBracket();
		try {
			scribe.pushKey();
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testPopAfterKey() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject().key("key");
		Mockito.verify(appender).appendOpenBrace();
		Mockito.verify(appender).appendQuote();
		Mockito.verify(appender).escape("key");
		Mockito.verify(appender).appendCloseKey();
		try {
			scribe.pop();
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testAppendWithCharWhenEmpty() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		try {
			scribe.append('a');
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testAppendWithCharSequenceWhenEmpty() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		try {
			scribe.append("abc");
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testAppendWithCharSequenceIndexesWhenEmpty() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender);
		try {
			scribe.append("abc", 0, 2);
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testAppendWithCharInArray() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushArray();
		Mockito.verify(appender).appendOpenBracket();
		try {
			scribe.append('a');
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testAppendWithCharSequenceInArray() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushArray();
		Mockito.verify(appender).appendOpenBracket();
		try {
			scribe.append("abc");
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testAppendWithCharSequenceIndexesInArray() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushArray();
		Mockito.verify(appender).appendOpenBracket();
		try {
			scribe.append("abc", 0, 2);
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testAppendWithCharInObject() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject();
		Mockito.verify(appender).appendOpenBrace();
		try {
			scribe.append('a');
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testAppendWithCharSequenceInObject() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject();
		Mockito.verify(appender).appendOpenBrace();
		try {
			scribe.append("abc");
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testAppendWithCharSequenceIndexesInObject() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject();
		Mockito.verify(appender).appendOpenBrace();
		try {
			scribe.append("abc", 0, 2);
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testAppendWithCharAfterKey() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject().key("key");
		Mockito.verify(appender).appendOpenBrace();
		Mockito.verify(appender).appendQuote();
		Mockito.verify(appender).escape("key");
		Mockito.verify(appender).appendCloseKey();
		try {
			scribe.append('a');
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testAppendWithCharSequenceAfterKey() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject().key("key");
		Mockito.verify(appender).appendOpenBrace();
		Mockito.verify(appender).appendQuote();
		Mockito.verify(appender).escape("key");
		Mockito.verify(appender).appendCloseKey();
		try {
			scribe.append("abc");
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
	@Test
	public void testAppendWithCharSequenceIndexesAfterKey() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushObject().key("key");
		Mockito.verify(appender).appendOpenBrace();
		Mockito.verify(appender).appendQuote();
		Mockito.verify(appender).escape("key");
		Mockito.verify(appender).appendCloseKey();
		try {
			scribe.append("abc", 0, 2);
		} catch (IllegalStateException e) {
			Mockito.verifyNoMoreInteractions(appender);
			return;
		} catch (Throwable t) {
			Assert.fail("Threw " + t.getClass().getName());
		}
		Assert.fail("Did not throw");
	}
	
}
