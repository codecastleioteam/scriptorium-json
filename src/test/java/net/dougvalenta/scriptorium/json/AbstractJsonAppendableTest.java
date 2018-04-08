/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json;

import java.io.IOException;
import net.dougvalenta.scriptorium.json.scribe.JsonScribe;
import net.dougvalenta.scriptorium.json.scribe.MockJsonScribe;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Doug Valenta
 */
public abstract class AbstractJsonAppendableTest<A extends JsonAppendable<A>> {
	
	protected abstract A getJsonAppendable(JsonScribe scribe);
	
	@Test
	public void testAppendCharSequence() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A appendable = getJsonAppendable(scribe);
		final A result = appendable.append("abc");
		Assert.assertEquals(appendable, result);
		Mockito.verify(scribe).append("abc");
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testAppendNullCharSequence() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A appendable = getJsonAppendable(scribe);
		final A result = appendable.append((CharSequence) null);
		Assert.assertEquals(appendable, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testAppendCharSequenceWithIndexes() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A appendable = getJsonAppendable(scribe);
		final A result = appendable.append("abcde", 1, 4);
		Assert.assertEquals(appendable, result);
		Mockito.verify(scribe).append("abcde", 1, 4);
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testAppendNullCharSequenceWithIndexes() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A appendable = getJsonAppendable(scribe);
		final A result = appendable.append((CharSequence) null, 1, 4);
		Assert.assertEquals(appendable, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testAppendCharSequenceWithNegativeStart() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A appendable = getJsonAppendable(scribe);
		final A result = appendable.append("abcde", -1, 4);
		Assert.assertEquals(appendable, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testAppendChar() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A appendable = getJsonAppendable(scribe);
		final A result = appendable.append('a');
		Assert.assertEquals(appendable, result);
		Mockito.verify(scribe).append('a');
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testAppendCharacter() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A appendable = getJsonAppendable(scribe);
		final A result = appendable.append(Character.valueOf('a'));
		Assert.assertEquals(appendable, result);
		Mockito.verify(scribe).append('a');
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testAppendNullCharacter() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A appendable = getJsonAppendable(scribe);
		final A result = appendable.append((Character) null);
		Assert.assertEquals(appendable, result);
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testWithConsumer() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A appendable = getJsonAppendable(scribe);
		final A result = appendable.with((a) -> {
			Assert.assertTrue(a instanceof InscribedJsonAppendable);
			a.append("abc");
			Mockito.verify(scribe).append("abc");
		});
		Assert.assertEquals(appendable, result);
	}
	
	@Test
	public void testWithElementAndConsumer() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A appendable = getJsonAppendable(scribe);
		final Object element = new Object();
		final A result = appendable.with(element, (e, a) -> {
			Assert.assertEquals(element, e);
			Assert.assertTrue(a instanceof InscribedJsonAppendable);
			a.append("abc");
			Mockito.verify(scribe).append("abc");
		});
		Assert.assertEquals(appendable, result);
	}
	
}
