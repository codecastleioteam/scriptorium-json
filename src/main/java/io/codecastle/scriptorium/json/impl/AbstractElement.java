/*
 * Copyright 2019 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package io.codecastle.scriptorium.json.impl;

import io.codecastle.scriptorium.Inscribable;
import io.codecastle.scriptorium.Inscription;
import io.codecastle.scriptorium.function.IOFunction;
import java.io.IOException;

/**
 *
 * @author Doug Valenta
 */
abstract class AbstractElement<I, THIS extends Inscribable<I, THIS>> implements Inscribable<I, THIS> {
	
	protected JsonFactory.Host host;
	
	AbstractElement(final JsonFactory.Host host) {
		this.host = host;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T extends Inscription<THIS>> T inscribe(IOFunction<? super THIS, T> function) throws IOException {
		final T inscription = function.apply((THIS) this);
		host.scribe.pushInscription(inscription);
		return inscription;
	}
	
}
