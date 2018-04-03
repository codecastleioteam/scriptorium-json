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
public abstract class CloseableJsonArrayTest<A extends CloseableJsonArray<A>> extends AbstractJsonArrayTest<A> {
	
	@Test
	public void testClose() throws IOException {
		final JsonScribe scribe = Mockito.spy(new MockJsonScribe());
		final int startingState = scribe.getCursor();
		Mockito.clearInvocations(scribe);
		final CloseableJsonArray array = getJsonArray(scribe);
		array.close();
		Mockito.verify(scribe, Mockito.atLeast(0)).getCursor();
		Mockito.verify(scribe, Mockito.atLeast(0)).pop();
		Mockito.verify(scribe, Mockito.atLeast(0)).pop(Mockito.anyInt());
		Mockito.verifyNoMoreInteractions(scribe);
		Assert.assertEquals(startingState - 1, scribe.getCursor());
	}
	
}
