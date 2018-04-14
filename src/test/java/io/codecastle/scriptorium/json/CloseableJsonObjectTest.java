/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json;

import java.io.IOException;
import io.codecastle.scriptorium.json.scribe.JsonScribe;
import io.codecastle.scriptorium.json.scribe.MockJsonScribe;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Doug Valenta
 */
public abstract class CloseableJsonObjectTest<O extends CloseableJsonObject<O>> extends AbstractJsonObjectTest<O> {

	@Test
	public void testClose() throws IOException {
		final JsonScribe scribe = Mockito.spy(new MockJsonScribe()).pushObject();
		final int startingState = scribe.getCursor();
		Mockito.clearInvocations(scribe);
		final O object = getJsonObject(scribe);
		object.close();
		Assert.assertFalse(Mockito.mockingDetails(scribe).getInvocations().isEmpty());
		Mockito.verify(scribe, Mockito.atLeast(0)).getCursor();
		Mockito.verify(scribe, Mockito.atLeast(0)).pop();
		Mockito.verify(scribe, Mockito.atLeast(0)).pop(Mockito.anyInt());
		Mockito.verifyNoMoreInteractions(scribe);
		Assert.assertEquals(startingState - 1, scribe.getCursor());
	}
	
	@Test
	public void testCloseTwice() throws IOException {
		final JsonScribe scribe = Mockito.spy(new MockJsonScribe()).pushObject();
		final int startingState = scribe.getCursor();
		Mockito.clearInvocations(scribe);
		final O object = getJsonObject(scribe);
		object.close();
		Assert.assertFalse(Mockito.mockingDetails(scribe).getInvocations().isEmpty());
		Mockito.verify(scribe, Mockito.atLeast(0)).getCursor();
		Mockito.verify(scribe, Mockito.atLeast(0)).pop();
		Mockito.verify(scribe, Mockito.atLeast(0)).pop(Mockito.anyInt());
		Mockito.verifyNoMoreInteractions(scribe);
		Assert.assertEquals(startingState - 1, scribe.getCursor());
		Mockito.clearInvocations(scribe);
		object.close();
		Mockito.verifyZeroInteractions(scribe);
	}
	
	@Test
	public void testCloseAtZero() throws IOException {
		final JsonScribe scribe = Mockito.spy(new MockJsonScribe());
		Assert.assertEquals(0, scribe.getCursor());
		Mockito.clearInvocations(scribe);
		final O object = getJsonObject(scribe);
		Mockito.verify(scribe, Mockito.atLeast(0)).getCursor();
		object.close();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
}
