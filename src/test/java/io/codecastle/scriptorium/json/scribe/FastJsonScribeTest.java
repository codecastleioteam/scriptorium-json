/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json.scribe;

import java.io.IOException;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * 
 * @author Doug Valenta
 */
public class FastJsonScribeTest extends AbstractJsonScribeTest {

	@Override
	protected JsonScribe getScribe(final JsonAppender appender) {
		return new FastJsonScribe(appender);
	}
	
	@Test
	public void testPopWithNegativeCursor() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushArray();
		Mockito.verify(appender).appendOpenBracket();
		final JsonScribe result = scribe.pop(-1);
		Assertions.assertEquals(scribe, result);
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPopWithZeroCursor() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushArray();
		Mockito.verify(appender).appendOpenBracket();
		Mockito.verifyNoMoreInteractions(appender);
		final JsonScribe result = scribe.pop(0);
		Assertions.assertEquals(scribe, result);
		Mockito.verify(appender).appendCloseBracket();
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPopWithFutureCursor() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		final JsonScribe scribe = getScribe(appender).pushArray();
		Mockito.verify(appender).appendOpenBracket();
		final JsonScribe result = scribe.pop(99);
		Assertions.assertEquals(scribe, result);
		Mockito.verifyNoMoreInteractions(appender);
	}
	
	@Test
	public void testPopWhenEmpty() {
		Assertions.assertThrows(NoSuchElementException.class, this::popWhenEmpty);
	}
		
	private void popWhenEmpty() throws IOException {
		final JsonAppender appender = Mockito.mock(JsonAppender.class, Mockito.RETURNS_SELF);
		getScribe(appender).pop();
	}
	
}
