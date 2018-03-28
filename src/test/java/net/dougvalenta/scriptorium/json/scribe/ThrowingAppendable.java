/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json.scribe;

import java.io.IOException;

/**
 *
 * @author Doug Valenta
 */
public class ThrowingAppendable implements Appendable {

	@Override
	public Appendable append(CharSequence cs) throws IOException {
		throw new IOException();
	}

	@Override
	public Appendable append(CharSequence cs, int i, int i1) throws IOException {
		throw new IOException();
	}

	@Override
	public Appendable append(char c) throws IOException {
		throw new IOException();
	}
	
}
