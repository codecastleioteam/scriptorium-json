/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json.scribe;

import java.io.IOException;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Doug Valenta
 */
public abstract class AbstractJsonScribeTest {
	
	protected abstract JsonScribe getScribe(JsonAppender appender);
	
	@Test
	public void testPushArray() throws IOException {
		JsonAppender appender = Mockito.mock(JsonAppender.class);
		getScribe(appender).pushArray();
		Mockito.verify(appender).appendOpenBracket();
		Mockito.verifyNoMoreInteractions(appender);
	}
	
}
