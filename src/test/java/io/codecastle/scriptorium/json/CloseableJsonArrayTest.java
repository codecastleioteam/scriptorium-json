/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json;

import java.io.IOException;
import io.codecastle.scriptorium.json.scribe.JsonScribe;
import io.codecastle.scriptorium.json.scribe.MockJsonScribe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 *
 * @author Doug Valenta
 */
public abstract class CloseableJsonArrayTest<A extends CloseableJsonArray<A>> extends AbstractJsonArrayTest<A> {
	
	@Test
	public void testClose() throws IOException {
		final JsonScribe scribe = Mockito.spy(new MockJsonScribe()).pushArray();
		final int startingState = scribe.getCursor();
		Mockito.clearInvocations(scribe);
		final A array = getJsonArray(scribe);
		array.close();
		Assertions.assertFalse(Mockito.mockingDetails(scribe).getInvocations().isEmpty());
		Mockito.verify(scribe, Mockito.atLeast(0)).getCursor();
		Mockito.verify(scribe, Mockito.atLeast(0)).pop();
		Mockito.verify(scribe, Mockito.atLeast(0)).pop(Mockito.anyInt());
		Mockito.verifyNoMoreInteractions(scribe);
		Assertions.assertEquals(startingState - 1, scribe.getCursor());
	}
	
	@Test
	public void testCloseTwice() throws IOException {
		final JsonScribe scribe = Mockito.spy(new MockJsonScribe()).pushArray();
		final int startingState = scribe.getCursor();
		Mockito.clearInvocations(scribe);
		final A array = getJsonArray(scribe);
		array.close();
		Assertions.assertFalse(Mockito.mockingDetails(scribe).getInvocations().isEmpty());
		Mockito.verify(scribe, Mockito.atLeast(0)).getCursor();
		Mockito.verify(scribe, Mockito.atLeast(0)).pop();
		Mockito.verify(scribe, Mockito.atLeast(0)).pop(Mockito.anyInt());
		Mockito.verifyNoMoreInteractions(scribe);
		Assertions.assertEquals(startingState - 1, scribe.getCursor());
		Mockito.clearInvocations(scribe);
		array.close();
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testCloseAtZero() throws IOException {
		final JsonScribe scribe = Mockito.spy(new MockJsonScribe());
		Assertions.assertEquals(0, scribe.getCursor());
		Mockito.clearInvocations(scribe);
		final A array = getJsonArray(scribe);
		Mockito.verify(scribe, Mockito.atLeast(0)).getCursor();
		array.close();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
}
