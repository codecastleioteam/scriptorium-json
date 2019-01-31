/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import io.codecastle.scriptorium.FluentNode;
import io.codecastle.scriptorium.function.IOFunction;
import io.codecastle.scriptorium.json.scribe.JsonScribe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
		Assertions.assertEquals(appendable, result);
		Mockito.verify(scribe).append("abc");
		Mockito.verifyNoMoreInteractions(scribe);
	}

	@Test
	public void testAppendNullCharSequence() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A appendable = getJsonAppendable(scribe);
		final A result = appendable.append((CharSequence) null);
		Assertions.assertEquals(appendable, result);
		Mockito.verifyZeroInteractions(scribe);
	}

	@Test
	public void testAppendCharSequenceWithIndexes() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A appendable = getJsonAppendable(scribe);
		final A result = appendable.append("abcde", 1, 4);
		Assertions.assertEquals(appendable, result);
		Mockito.verify(scribe).append("abcde", 1, 4);
		Mockito.verifyNoMoreInteractions(scribe);
	}

	@Test
	public void testAppendCharSequenceWithInclusiveIndexes() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A appendable = getJsonAppendable(scribe);
		final A result = appendable.append("abc", 0, 3);
		Assertions.assertEquals(appendable, result);
		Mockito.verify(scribe).append("abc", 0, 3);
		Mockito.verifyNoMoreInteractions(scribe);
	}

	@Test
	public void testAppendNullCharSequenceWithIndexes() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A appendable = getJsonAppendable(scribe);
		final A result = appendable.append((CharSequence) null, 1, 4);
		Assertions.assertEquals(appendable, result);
		Mockito.verifyZeroInteractions(scribe);
	}

	@Test
	public void testAppendCharSequenceWithNegativeStart() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A appendable = getJsonAppendable(scribe);
		final A result = appendable.append("abcde", -1, 4);
		Assertions.assertEquals(appendable, result);
		Mockito.verifyZeroInteractions(scribe);
	}

	@Test
	public void testAppendChar() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A appendable = getJsonAppendable(scribe);
		final A result = appendable.append('a');
		Assertions.assertEquals(appendable, result);
		Mockito.verify(scribe).append('a');
		Mockito.verifyNoMoreInteractions(scribe);
	}

	@Test
	public void testAppendCharacter() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A appendable = getJsonAppendable(scribe);
		final A result = appendable.append(Character.valueOf('a'));
		Assertions.assertEquals(appendable, result);
		Mockito.verify(scribe).append('a');
		Mockito.verifyNoMoreInteractions(scribe);
	}

	@Test
	public void testAppendNullCharacter() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A appendable = getJsonAppendable(scribe);
		final A result = appendable.append((Character) null);
		Assertions.assertEquals(appendable, result);
		Mockito.verifyZeroInteractions(scribe);
	}

	@Test
	public void testWithConsumer() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A appendable = getJsonAppendable(scribe);
		final AtomicInteger called = new AtomicInteger();
		final A result = appendable.with((a) -> {
			called.incrementAndGet();
			Assertions.assertTrue(a instanceof InscribedJsonAppendable);
			a.append("abc");
			Mockito.verify(scribe).append("abc");
		});
		Assertions.assertEquals(1, called.get());
		Assertions.assertEquals(appendable, result);
	}

	@Test
	public void testWithElementAndConsumer() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A appendable = getJsonAppendable(scribe);
		final Object element = new Object();
		final AtomicInteger called = new AtomicInteger();
		final A result = appendable.with(element, (e, a) -> {
			called.incrementAndGet();
			Assertions.assertEquals(element, e);
			Assertions.assertTrue(a instanceof InscribedJsonAppendable);
			a.append("abc");
			Mockito.verify(scribe).append("abc");
		});
		Assertions.assertEquals(1, called.get());
		Assertions.assertEquals(appendable, result);
	}

	@Test
	public void testInscribe() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final A appendable = getJsonAppendable(scribe);
		Mockito.clearInvocations(scribe);
		final FluentNode<A> inscription = Mockito.mock(FluentNode.class);
		final IOFunction<A, FluentNode<A>> inscriptor = (a) -> {
			Assertions.assertEquals(appendable, a);
			return inscription;
		};
		final FluentNode<A> result = appendable.inscribe(inscriptor);
		Assertions.assertEquals(inscription, result);
		Mockito.verify(scribe).pushInscription(inscription);
		Mockito.verifyNoMoreInteractions(scribe);
	}

}
