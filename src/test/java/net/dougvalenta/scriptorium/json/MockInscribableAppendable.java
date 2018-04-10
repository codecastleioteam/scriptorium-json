/* 
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
*/
package net.dougvalenta.scriptorium.json;

import java.io.IOException;
import net.dougvalenta.scriptorium.FluentAppendable;
import net.dougvalenta.scriptorium.Inscribable;
import net.dougvalenta.scriptorium.function.IOBiConsumer;
import net.dougvalenta.scriptorium.function.IOConsumer;

/**
 *
 * @author Doug Valenta
 */
public class MockInscribableAppendable implements FluentAppendable<MockInscribableAppendable>, Inscribable<Appendable, MockInscribableAppendable> {

	private final Appendable appendable;
	
	public MockInscribableAppendable(final Appendable appendable) {
		this.appendable = appendable;
	}
	
	@Override
	public MockInscribableAppendable append(CharSequence cs) throws IOException {
		appendable.append(cs);
		return this;
	}

	@Override
	public MockInscribableAppendable append(CharSequence cs, int i, int i1) throws IOException {
		appendable.append(cs, i, i1);
		return this;
	}

	@Override
	public MockInscribableAppendable append(char c) throws IOException {
		appendable.append(c);
		return this;
	}

	@Override
	public MockInscribableAppendable with(IOConsumer<? super Appendable> consumer) throws IOException {
		return this;
	}

	@Override
	public <T> MockInscribableAppendable with(T element, IOBiConsumer<? super T, ? super Appendable> biConsumer) throws IOException {
		return this;
	}
	
}
