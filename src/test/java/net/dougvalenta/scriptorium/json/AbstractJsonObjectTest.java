/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import net.dougvalenta.scriptorium.FluentNode;
import net.dougvalenta.scriptorium.function.IOFunction;
import net.dougvalenta.scriptorium.json.scribe.JsonScribe;
import net.dougvalenta.scriptorium.json.scribe.MockJsonScribe;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

/**
 *
 * @author Doug Valenta
 */
public abstract class AbstractJsonObjectTest<O extends JsonObject<O>> {
	
	protected abstract O getJsonObject(JsonScribe scribe);
	
	@Test
	public void testWithNull() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withNull("abc");
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("abc");
		inOrder.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithNullWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withNull(null);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithTrue() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withTrue("abc");
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("abc");
		inOrder.verify(scribe).trueValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithTrueWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withTrue(null);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithFalse() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withFalse("abc");
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("abc");
		inOrder.verify(scribe).falseValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithFalseWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withFalse(null);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithCharSequence() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", "value");
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value("value");
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithNullCharSequence() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", (CharSequence) null);
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithCharSequenceWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with(null, "value");
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithCharacter() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", Character.valueOf('a'));
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value('a');
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithNullCharacter() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", (Character) null);
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithCharacterWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with(null, Character.valueOf('a'));
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithChar() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", 'a');
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value('a');
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithCharWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with(null, 'a');
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithBigInteger() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", BigInteger.ONE);
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value(BigInteger.ONE);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithNullBigInteger() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", (BigInteger) null);
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithBigIntegerWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with(null, BigInteger.ONE);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithBigDecimal() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", BigDecimal.ONE);
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value(BigDecimal.ONE);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithNullBigDecimal() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", (BigDecimal) null);
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithBigDecimalWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with(null, BigDecimal.ONE);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithByte() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", Byte.valueOf((byte) 1));
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value(1);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithNullByte() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", (Byte) null);
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithByteWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with(null, Byte.valueOf((byte) 1));
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithShort() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", Short.valueOf((short) 1));
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value(1);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithNullShort() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", (Short) null);
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithShortWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with(null, Short.valueOf((short) 1));
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithInteger() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", Integer.valueOf(1));
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value(1);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithNullInteger() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", (Integer) null);
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithIntegerWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with(null, Integer.valueOf(1));
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithInt() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", 1);
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value(1);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithIntWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with(null, 1);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithLong() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", Long.valueOf(1L));
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value(1L);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithNullLong() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", (Long) null);
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithLongWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with(null, Long.valueOf(1L));
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithPrimitiveLong() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", 1L);
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value(1L);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithPrimitiveLongWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with(null, 1L);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithFloat() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", Float.valueOf(1f));
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value(1f);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithNullFloat() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", (Float) null);
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithFloatWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with(null, Float.valueOf(1f));
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithPrimitiveFloat() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", 1f);
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value(1f);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithPrimitiveFloatWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with(null, 1f);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithDouble() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", Double.valueOf(1.0));
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value(1.0);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithNullDouble() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", (Double) null);
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithDoubleWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with(null, Double.valueOf(1.0));
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithPrimitiveDouble() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", 1.0);
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value(1.0);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithPrimitiveDoubleWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with(null, 1.0);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithBoolean() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", Boolean.TRUE);
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value(true);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithNullBoolean() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", (Boolean) null);
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithBooleanWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with(null, Boolean.TRUE);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithPrimitiveBoolean() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with("key", true);
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value(true);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithPrimitiveBooleanWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.with(null, true);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullCharSequence() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull("key", "value");
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value("value");
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullNullCharSequence() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull("key", (CharSequence) null);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullCharSequenceWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull(null, "value");
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullCharacter() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull("key", Character.valueOf('a'));
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value('a');
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullNullCharacter() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull("key", (Character) null);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullCharacterWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull(null, Character.valueOf('a'));
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullByte() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull("key", Byte.valueOf((byte) 1));
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value(1);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullNullByte() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull("key", (Byte) null);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullByteWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull(null, Byte.valueOf((byte) 1));
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullShort() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull("key", Short.valueOf((short) 1));
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value(1);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullNullShort() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull("key", (Short) null);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullShortWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull(null, Short.valueOf((byte) 1));
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullInteger() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull("key", Integer.valueOf(1));
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value(1);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullNullInteger() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull("key", (Integer) null);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullIntegerWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull(null, Integer.valueOf(1));
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullFloat() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull("key", Float.valueOf(1f));
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value(1f);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullNullFloat() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull("key", (Float) null);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullNonFiniteFloat() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull("key", Float.valueOf(Float.NaN));
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullFloatWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull(null, Float.valueOf(1f));
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullLong() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull("key", Long.valueOf(1L));
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value(1L);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullNullLong() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull("key", (Long) null);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullLongWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull(null, Long.valueOf(1L));
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullDouble() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull("key", Double.valueOf(1.0));
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value(1.0);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullNullDouble() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull("key", (Double) null);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullNonFiniteDouble() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull("key", Double.valueOf(Double.NaN));
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullDoubleWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull(null, Double.valueOf(1.0));
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullBigInteger() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull("key", BigInteger.ONE);
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value(BigInteger.ONE);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullNullBigInteger() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull("key", (BigInteger) null);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullBigIntegerWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull(null, BigInteger.ONE);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullBigDecimal() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull("key", BigDecimal.ONE);
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value(BigDecimal.ONE);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullNullBigDecimal() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull("key", (BigDecimal) null);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullBigDecimalWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull(null, BigDecimal.ONE);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullBoolean() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull("key", Boolean.TRUE);
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value(true);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullNullBoolean() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull("key", (Boolean) null);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfNotNullBooleanWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfNotNull(null, Boolean.TRUE);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfFiniteDouble() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfFinite("key", 1.0);
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value(1.0);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithIfFiniteNonFiniteDouble() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfFinite("key", Double.NaN);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfFiniteDoubleWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfFinite(null, 1.0);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfFiniteFloat() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfFinite("key", 1f);
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).value(1f);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithIfFiniteNonFiniteFloat() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfFinite("key", Float.NaN);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithIfFiniteFloatWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withIfFinite(null, 1f);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithEmptyArray() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withEmptyArray("key");
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).emptyArray();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithEmptyArrayWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withEmptyArray(null);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithEmptyObject() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withEmptyObject("key");
		Assert.assertEquals(object, result);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).key("key");
		inOrder.verify(scribe).emptyObject();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithEmptyObjectWithNullKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final O result = object.withEmptyObject(null);
		Assert.assertEquals(object, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testKey() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final JsonKey<O> key = object.key();
		Mockito.verify(scribe).pushKey();
		Mockito.verifyNoMoreInteractions(scribe);
		Assert.assertEquals(object, key.thenNull());
	}
	
	@Test
	public void testKeyWithChar() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final JsonKey<O> key = object.key('a');
		Mockito.verify(scribe).pushKey();
		Mockito.verify(scribe).append('a');
		Mockito.verifyNoMoreInteractions(scribe);
		Assert.assertEquals(object, key.thenNull());
	}
	
	@Test
	public void testKeyWithCharacter() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final JsonKey<O> key = object.key(Character.valueOf('a'));
		Mockito.verify(scribe).pushKey();
		Mockito.verify(scribe).append('a');
		Mockito.verifyNoMoreInteractions(scribe);
		Assert.assertEquals(object, key.thenNull());
	}
	
	@Test
	public void testKeyWithNullCharacter() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final JsonKey<O> key = object.key((Character) null);
		Mockito.verify(scribe).pushKey();
		Mockito.verifyNoMoreInteractions(scribe);
		Assert.assertEquals(object, key.thenNull());
	}
	
	@Test
	public void testKeyWithCharSequence() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final JsonKey<O> key = object.key("abc");
		Mockito.verify(scribe).pushKey();
		Mockito.verify(scribe).append("abc");
		Mockito.verifyNoMoreInteractions(scribe);
		Assert.assertEquals(object, key.thenNull());
	}
	
	@Test
	public void testKeyWithNullCharSequence() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final JsonKey<O> key = object.key((CharSequence) null);
		Mockito.verify(scribe).pushKey();
		Mockito.verifyNoMoreInteractions(scribe);
		Assert.assertEquals(object, key.thenNull());
	}
	
	@Test
	public void testWithConsumer() throws IOException {
		final JsonScribe scribe = Mockito.spy(new MockJsonScribe());
		final O object = getJsonObject(scribe);
		final int cursor = scribe.getCursor();
		Mockito.clearInvocations(scribe);
		final AtomicInteger called = new AtomicInteger();
		final O result = object.with((o) -> {
			called.incrementAndGet();
			Assert.assertTrue(o instanceof InscribedJsonObject);
			Assert.assertEquals(cursor, scribe.getCursor());
			o.key().value();
			Assert.assertNotEquals(cursor, scribe.getCursor());
		});
		Assert.assertEquals(1, called.get());
		Assert.assertEquals(object, result);
		Assert.assertEquals(cursor, scribe.getCursor());
	}
	
	@Test
	public void testWithElementAndConsumer() throws IOException {
		final JsonScribe scribe = Mockito.spy(new MockJsonScribe());
		final O object = getJsonObject(scribe);
		final int cursor = scribe.getCursor();
		Mockito.clearInvocations(scribe);
		final AtomicInteger called = new AtomicInteger();
		final Object element = new Object();
		final O result = object.with(element, (e, o) -> {
			called.getAndIncrement();
			Assert.assertEquals(element, e);
			Assert.assertTrue(o instanceof InscribedJsonObject);
			Assert.assertEquals(cursor, scribe.getCursor());
			o.key().value();
			Assert.assertNotEquals(cursor, scribe.getCursor());
		});
		Assert.assertEquals(1, called.get());
		Assert.assertEquals(object, result);
		Assert.assertEquals(cursor, scribe.getCursor());
	}
	
	@Test
	public void testInscribe() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final O object = getJsonObject(scribe);
		Mockito.clearInvocations(scribe);
		final FluentNode<O> inscription = Mockito.mock(FluentNode.class);
		final IOFunction<O, FluentNode<O>> inscriptor = (o) -> {
			Assert.assertEquals(object, o);
			return inscription;
		};
		final FluentNode<O> result = object.inscribe(inscriptor);
		Assert.assertEquals(inscription, result);
		Mockito.verify(scribe).pushInscription(inscription);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
}
