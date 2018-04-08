/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Assert;
import net.dougvalenta.scriptorium.json.scribe.JsonScribe;
import net.dougvalenta.scriptorium.json.scribe.MockJsonScribe;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;

/**
 *
 * @author Doug Valenta
 */
public abstract class AbstractJsonArrayTest<A extends JsonArray<A>> {
	
	protected abstract A getJsonArray(JsonScribe scribe);
	
	@Test
	public void testWithNull() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.withNull();
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithTrue() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.withTrue();
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).trueValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithFalse() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.withFalse();
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).falseValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithCharSequence() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with("abc");
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).value("abc");
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithNullCharSequence() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with((CharSequence) null);
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithChar() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with('a');
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).value('a');
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithCharacter() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with(Character.valueOf('a'));
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).value('a');
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithNullCharacter() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with((Character) null);
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithBigInteger() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with(BigInteger.ONE);
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).value(BigInteger.ONE);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithNullBigInteger() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with((BigInteger) null);
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithBigDecimal() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with(BigDecimal.ONE);
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).value(BigDecimal.ONE);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithNullBigDecimal() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with((BigDecimal) null);
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithInt() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with(1);
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).value(1);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithInteger() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with(Integer.valueOf(1));
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).value(1);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithNullInteger() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with((Integer) null);
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithShort() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with(Short.valueOf((short) 1));
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).value(1);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithNullShort() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with((Short) null);
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithByte() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with(Byte.valueOf((byte) 1));
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).value(1);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithNullByte() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with((Byte) null);
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithLongPrimitive() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with(1L);
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).value(1L);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithLong() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with(Long.valueOf(1L));
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).value(1L);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithNullLong() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with((Long) null);
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithFloatPrimitive() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with(1f);
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).value(1f);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithFloat() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with(Float.valueOf(1f));
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).value(1f);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithNullFloat() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with((Float) null);
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithDoublePrimitive() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with(1.0);
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).value(1.0);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithDouble() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with(Double.valueOf(1.0));
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).value(1.0);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithNullDouble() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with((Double) null);
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithBooleanPrimitive() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with(true);
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).value(true);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithBoolean() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with(Boolean.TRUE);
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).value(true);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithNullBoolean() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.with((Boolean) null);
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).nullValue();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithAllWithArray() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final Object[] objects = new Object[] {new Object(), new Object(), new Object()};
		final A result = array.withAll(objects);
		Assert.assertEquals(array, result);
		ArgumentCaptor<Object> captor = ArgumentCaptor.forClass(Object.class);
		Mockito.verify(scribe, Mockito.times(3)).value(captor.capture());
		Mockito.verifyNoMoreInteractions(scribe);
		List<Object> capturedObjects = captor.getAllValues();
		Assert.assertEquals(3, capturedObjects.size());
		Assert.assertEquals(objects[0], capturedObjects.get(0));
		Assert.assertEquals(objects[1], capturedObjects.get(1));
		Assert.assertEquals(objects[2], capturedObjects.get(2));
	}
	
	@Test
	public void testWithAllWithNullArray() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.withAll((Object[]) null);
		Assert.assertEquals(array, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithAllWithIterable() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final List<Object> objects = new ArrayList<>(3);
		objects.add(new Object());
		objects.add(new Object());
		objects.add(new Object());
		final A result = array.withAll(objects);
		Assert.assertEquals(array, result);
		ArgumentCaptor<Object> captor = ArgumentCaptor.forClass(Object.class);
		Mockito.verify(scribe, Mockito.times(3)).value(captor.capture());
		Mockito.verifyNoMoreInteractions(scribe);
		List<Object> capturedObjects = captor.getAllValues();
		Assert.assertEquals(3, capturedObjects.size());
		Assert.assertEquals(objects.get(0), capturedObjects.get(0));
		Assert.assertEquals(objects.get(1), capturedObjects.get(1));
		Assert.assertEquals(objects.get(2), capturedObjects.get(2));
	}
	
	@Test
	public void testWithAllWithNullIterable() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.withAll((Iterable<Object>) null);
		Assert.assertEquals(array, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithEmptyArray() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.withEmptyArray();
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).emptyArray();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testWithEmptyObject() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final A result = array.withEmptyObject();
		Assert.assertEquals(array, result);
		Mockito.verify(scribe).emptyObject();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testElement() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final JsonValue<A> value = array.element();
		Mockito.verify(scribe).pushValue();
		Mockito.verifyNoMoreInteractions(scribe);
		Assert.assertEquals(array, value.then());
	}
	
	@Test
	public void testElementWithNullCharSequence() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final JsonValue<A> value = array.element((CharSequence) null);
		Mockito.verify(scribe).pushValue();
		Mockito.verifyNoMoreInteractions(scribe);
		Assert.assertEquals(array, value.then());
	}
	
	@Test
	public void testElementWithCharSequence() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final JsonValue<A> value = array.element("abc");
		InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pushValue();
		inOrder.verify(scribe).append("abc");
		Mockito.verifyNoMoreInteractions(scribe);
		Assert.assertEquals(array, value.then());
	}
	
	@Test
	public void testElementWithChar() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final JsonValue<A> value = array.element('a');
		InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pushValue();
		inOrder.verify(scribe).append('a');
		Mockito.verifyNoMoreInteractions(scribe);
		Assert.assertEquals(array, value.then());
	}
	
	@Test
	public void testElementWithCharacter() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final JsonValue<A> value = array.element(Character.valueOf('a'));
		InOrder inOrder = Mockito.inOrder(scribe);
		inOrder.verify(scribe).pushValue();
		inOrder.verify(scribe).append('a');
		Mockito.verifyNoMoreInteractions(scribe);
		Assert.assertEquals(array, value.then());
	}
	
	@Test
	public void testElementWithNullCharacter() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final JsonValue<A> value = array.element((Character) null);
		Mockito.verify(scribe).pushValue();
		Mockito.verifyNoMoreInteractions(scribe);
		Assert.assertEquals(array, value.then());
	}
	
	@Test
	public void testArray() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final JsonArrayNode<A> element = array.array();
		Mockito.verify(scribe).pushArray();
		Mockito.verify(scribe, Mockito.atLeast(0)).getCursor();
		Mockito.verifyNoMoreInteractions(scribe);
		Assert.assertEquals(array, element.then());
	}
	
	@Test
	public void testObject() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A array = getJsonArray(scribe);
		Mockito.clearInvocations(scribe);
		final JsonObjectNode<A> element = array.object();
		Mockito.verify(scribe).pushObject();
		Mockito.verify(scribe, Mockito.atLeast(0)).getCursor();
		Mockito.verifyNoMoreInteractions(scribe);
		Assert.assertEquals(array, element.then());
	}
	
	@Test
	public void testWithConsumer() throws IOException {
		final JsonScribe scribe = Mockito.spy(new MockJsonScribe());
		final A array = getJsonArray(scribe);
		final int cursor = scribe.getCursor();
		Mockito.clearInvocations(scribe);
		final AtomicInteger called = new AtomicInteger();
		final A result = array.with((a) -> {
			called.incrementAndGet();
			Assert.assertTrue(a instanceof InscribedJsonArray);
			Assert.assertEquals(cursor, scribe.getCursor());
			a.object();
			Assert.assertNotEquals(cursor, scribe.getCursor());
		});
		Assert.assertEquals(1, called.get());
		Assert.assertEquals(array, result);
		Assert.assertEquals(cursor, scribe.getCursor());
	}
	
	@Test
	public void testWithElementAndConsumer() throws IOException {
		final JsonScribe scribe = Mockito.spy(new MockJsonScribe());
		final A array = getJsonArray(scribe);
		final int cursor = scribe.getCursor();
		Mockito.clearInvocations(scribe);
		final AtomicInteger called = new AtomicInteger();
		final Object element = new Object();
		final A result = array.with(element, (e, a) -> {
			called.incrementAndGet();
			Assert.assertEquals(element, e);
			Assert.assertTrue(a instanceof InscribedJsonArray);
			Assert.assertEquals(cursor, scribe.getCursor());
			a.object();
			Assert.assertNotEquals(cursor, scribe.getCursor());
		});
		Assert.assertEquals(1, called.get());
		Assert.assertEquals(array, result);
		Assert.assertEquals(cursor, scribe.getCursor());
	}
	
}
