/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json;

import java.io.IOException;
import net.dougvalenta.scriptorium.json.scribe.JsonScribe;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Doug Valenta
 */
public class JsonTest {
	
	@Test
	public void testObject() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonObjectDocument document = Json.object(builder);
		document
				.with("key1", "value1")
				.with("key2", "value2")
				.with("key3", "value3")
		.close();
		Assert.assertEquals("{\"key1\":\"value1\",\"key2\":\"value2\",\"key3\":\"value3\"}", builder.toString());	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testObjectWithNullAppendable() throws IOException {
		Json.object((Appendable) null);
	}
	
	@Test
	public void testObjectWithScribe() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonObjectDocument document = Json.object(scribe);
		Mockito.verify(scribe).pushObject();
		Mockito.verify(scribe, Mockito.atLeast(0)).getCursor();
		Mockito.verifyNoMoreInteractions(scribe);
		document.close();
		Mockito.verify(scribe).pop();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testObjectWithNullScribe() throws IOException {
		Json.object((JsonScribe) null);
	}
	
	@Test
	public void testArray() throws IOException {
		final StringBuilder builder = new StringBuilder();
		final JsonArrayDocument document = Json.array(builder);
		document
				.with("element1")
				.with("element2")
				.with("element3")
		.close();
		Assert.assertEquals("[\"element1\",\"element2\",\"element3\"]", builder.toString());	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testArrayWithNullAppendable() throws IOException {
		Json.array((Appendable) null);
	}
	
	@Test
	public void testArrayWithScribe() throws IOException {
		final JsonScribe scribe = Mockito.mock(JsonScribe.class, Mockito.RETURNS_SELF);
		final JsonArrayDocument document = Json.array(scribe);
		Mockito.verify(scribe).pushArray();
		Mockito.verify(scribe, Mockito.atLeast(0)).getCursor();
		Mockito.verifyNoMoreInteractions(scribe);
		document.close();
		Mockito.verify(scribe).pop();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testArrayWithNullScribe() throws IOException {
		Json.array((JsonScribe) null);
	}
	
}
