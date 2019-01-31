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
public class JsonArrayNodeTest extends CloseableJsonArrayTest<JsonArrayNode<Object>> {

	private static final Object PARENT = new Object();
	
	@Override
	protected JsonArrayNode<Object> getJsonArray(final JsonScribe scribe) {
		return new JsonArrayNode<>(scribe, PARENT);
	}
	
	@Test
	public void testThen() throws IOException {
		final JsonScribe scribe = Mockito.spy(new MockJsonScribe()).pushArray();
		final int startingState = scribe.getCursor();
		Mockito.clearInvocations(scribe);
		final JsonArrayNode<Object> node = getJsonArray(scribe);
		final Object parent = node.then();
		Assertions.assertEquals(PARENT, parent);
		Assertions.assertFalse(Mockito.mockingDetails(scribe).getInvocations().isEmpty());
		Mockito.verify(scribe, Mockito.atLeast(0)).getCursor();
		Mockito.verify(scribe, Mockito.atLeast(0)).pop();
		Mockito.verify(scribe, Mockito.atLeast(0)).pop(Mockito.anyInt());
		Mockito.verifyNoMoreInteractions(scribe);
		Assertions.assertEquals(startingState - 1, scribe.getCursor());
	}
	
}
