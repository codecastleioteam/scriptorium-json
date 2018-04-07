/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

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
		Json.object(null);
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
		Json.array(null);
	}
	
}
