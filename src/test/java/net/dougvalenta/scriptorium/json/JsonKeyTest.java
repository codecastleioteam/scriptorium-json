/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import net.dougvalenta.scriptorium.json.scribe.JsonScribe;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

/**
 *
 * @author Doug Valenta
 */
public class JsonKeyTest extends AbstractJsonAppendableTest<JsonKey<Object>> {

	private final Object PARENT = new Object();
	
	@Override
	protected JsonKey<Object> getJsonAppendable(final JsonScribe scribe) {
		return new JsonKey<>(scribe, PARENT);
	}
	
	@Test
	public void testValue() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final JsonValue<Object> value = key.value();
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).pushValue();
		Mockito.verifyNoMoreInteractions(scribe);
		Assert.assertEquals(PARENT, value.then());
	}
	
	@Test
	public void testValueWithCharSequence() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final JsonValue<Object> value = key.value("abc");
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).pushValue();
		inOrder.verify(scribe).append("abc");
		Mockito.verifyNoMoreInteractions(scribe);
		Assert.assertEquals(PARENT, value.then());
	}
	
	@Test
	public void testValueWithNullCharSequence() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final JsonValue<Object> value = key.value((CharSequence) null);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).pushValue();
		Mockito.verifyNoMoreInteractions(scribe);
		Assert.assertEquals(PARENT, value.then());
	}
	
	@Test
	public void testValueWithChar() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final JsonValue<Object> value = key.value('a');
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).pushValue();
		inOrder.verify(scribe).append('a');
		Mockito.verifyNoMoreInteractions(scribe);
		Assert.assertEquals(PARENT, value.then());
	}
	
	@Test
	public void testValueWithCharacter() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final JsonValue<Object> value = key.value(Character.valueOf('a'));
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).pushValue();
		inOrder.verify(scribe).append('a');
		Mockito.verifyNoMoreInteractions(scribe);
		Assert.assertEquals(PARENT, value.then());
	}
	
	@Test
	public void testValueWithNullCharacter() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final JsonValue<Object> value = key.value((Character) null);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).pushValue();
		Mockito.verifyNoMoreInteractions(scribe);
		Assert.assertEquals(PARENT, value.then());
	}
	
	@Test
	public void testThenNull() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.thenNull();
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenTrue() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.thenTrue();
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).trueValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenFalse() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.thenFalse();
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).falseValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenEmptyObject() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.thenEmptyObject();
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).emptyObject();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenEmptyArray() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.thenEmptyArray();
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).emptyArray();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenCharSequence() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then("abc");
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).value("abc");
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenNullCharSequence() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then((CharSequence) null);
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenChar() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then('a');
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).value('a');
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenCharacter() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then(Character.valueOf('a'));
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).value('a');
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenNullCharacter() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then((Character) null);
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenBigInteger() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then(BigInteger.ONE);
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).value(BigInteger.ONE);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenNullBigInteger() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then((BigInteger) null);
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenBigDecimal() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then(BigDecimal.ONE);
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).value(BigDecimal.ONE);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenNullBigDecimal() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then((BigDecimal) null);
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenInt() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then(1);
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).value(1);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenByte() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then(Byte.valueOf((byte) 1));
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).value(1);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenNullByte() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then((Byte) null);
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenShort() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then(Short.valueOf((short) 1));
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).value(1);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenNullShort() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then((Short) null);
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenInteger() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then(Integer.valueOf(1));
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).value(1);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenNullInteger() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then((Integer) null);
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenPrimitiveLong() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then(1L);
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).value(1L);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenLong() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then(Long.valueOf(1L));
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).value(1L);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenNullLong() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then((Long) null);
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenPrimitiveBoolean() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then(true);
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).value(true);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenBoolean() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then(Boolean.valueOf(true));
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).value(true);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenNullBoolean() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then((Boolean) null);
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenPrimitiveFloat() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then(1f);
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).value(1f);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenFloat() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then(Float.valueOf(1f));
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).value(1f);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenNullFloat() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then((Float) null);
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenPrimitiveDouble() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then(1.0);
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).value(1.0);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenDouble() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then(Double.valueOf(1.0));
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).value(1.0);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThenNullDouble() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final Object parent = key.then((Double) null);
		Assert.assertEquals(PARENT, parent);
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testArray() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final JsonArrayNode<Object> array = key.array();
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).pushArray();
		inOrder.verify(scribe, Mockito.atLeast(0)).getCursor();
		Mockito.verifyNoMoreInteractions(scribe);
		Assert.assertEquals(PARENT, array.then());
	}
	
	@Test
	public void testObject() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonKey<Object> key = getJsonAppendable(scribe);
		final JsonObjectNode<Object> object = key.object();
		final InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pop();
		inOrder.verify(scribe).pushObject();
		inOrder.verify(scribe, Mockito.atLeast(0)).getCursor();
		Mockito.verifyNoMoreInteractions(scribe);
		Assert.assertEquals(PARENT, object.then());
	}
	
}
