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
import org.mockito.InOrder;
import org.mockito.Mockito;

/**
 *
 * @author Doug Valenta
 */
public class JsonArrayNodeTest extends CloseableJsonArrayTest<JsonArrayNode<Object>> {

	private static final Object PARENT = new Object();
	
	@Override
	protected JsonArrayNode<Object> getJsonArray(final JsonScribe scribe) {
		return new JsonArrayNode<>(scribe, PARENT);
	}
	
	@Test
	public void testThen() throws IOException {
		final JsonScribe scribe = Mockito.spy(new MockJsonScribe());
		final int startingState = scribe.getCursor();
		Mockito.clearInvocations(scribe);
		final JsonArrayNode<Object> node = getJsonArray(scribe);
		final Object parent = node.then();
		Assert.assertEquals(PARENT, parent);
		Mockito.verify(scribe, Mockito.atLeast(0)).getCursor();
		Mockito.verify(scribe, Mockito.atLeast(0)).pop();
		Mockito.verify(scribe, Mockito.atLeast(0)).pop(Mockito.anyInt());
		Mockito.verifyNoMoreInteractions(scribe);
		Assert.assertEquals(startingState - 1, scribe.getCursor());
	}
	
}
