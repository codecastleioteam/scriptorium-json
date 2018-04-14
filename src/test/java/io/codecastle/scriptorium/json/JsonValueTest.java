/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json;

import java.io.IOException;
import io.codecastle.scriptorium.json.scribe.JsonScribe;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Doug Valenta
 */
public class JsonValueTest extends AbstractJsonAppendableTest<JsonValue<Object>> {

	private static final Object PARENT = new Object();
	
	@Override
	protected JsonValue<Object> getJsonAppendable(final JsonScribe scribe) {
		return new JsonValue<>(scribe, PARENT);
	}
	
	@Test
	public void testClose() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonValue<Object> value = getJsonAppendable(scribe);
		value.close();
		Mockito.verify(scribe).pop();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testCloseTwice() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonValue<Object> value = getJsonAppendable(scribe);
		value.close();
		Mockito.verify(scribe).pop();
		value.close();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
	@Test
	public void testThen() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonValue<Object> value = getJsonAppendable(scribe);
		final Object parent = value.then();
		Assert.assertEquals(PARENT, parent);
		Mockito.verify(scribe).pop();
		Mockito.verifyNoMoreInteractions(scribe);
	}
	
}
