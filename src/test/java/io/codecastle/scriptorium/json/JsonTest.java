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
		Assertions.assertEquals("{\"key1\":\"value1\",\"key2\":\"value2\",\"key3\":\"value3\"}", builder.toString());	
	}
	
	@Test
	public void testObjectWithNullAppendable() throws IOException {
		Assertions.assertThrows(IllegalArgumentException.class, ()->{Json.object((Appendable) null);});
	}
	
	@Test
	public void testObjectWithScribe() throws IOException {
		final JsonScribe scribe = Mockito.spy(new MockJsonScribe());
		final JsonObjectDocument document = Json.object(scribe);
		Mockito.verify(scribe).pushObject();
		Mockito.verify(scribe, Mockito.atLeast(0)).getCursor();
		Mockito.verifyNoMoreInteractions(scribe);
		document.close();
		Mockito.verify(scribe).pop();
	}
	
	@Test
	public void testObjectWithNullScribe() {
		Assertions.assertThrows(IllegalArgumentException.class, this::objectWithNullScribe);
	}
	
	private void objectWithNullScribe() throws IOException {
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
		Assertions.assertEquals("[\"element1\",\"element2\",\"element3\"]", builder.toString());	
	}
	
	@Test
	public void testArrayWithNullAppendable() throws IOException {
		Assertions.assertThrows(IllegalArgumentException.class, ()->{Json.array((Appendable) null);});
	}
	
	@Test
	public void testArrayWithScribe() throws IOException {
		final JsonScribe scribe = Mockito.spy(new MockJsonScribe());
		final JsonArrayDocument document = Json.array(scribe);
		Mockito.verify(scribe).pushArray();
		Mockito.verify(scribe, Mockito.atLeast(0)).getCursor();
		Mockito.verifyNoMoreInteractions(scribe);
		document.close();
		Mockito.verify(scribe).pop();
	}
	
	@Test
	public void testArrayWithNullScribe() throws IOException {
		Assertions.assertThrows(IllegalArgumentException.class, ()->{Json.array((JsonScribe) null);});
	}
	
}
