/* 
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
*/
package io.codecastle.scriptorium.json;

import java.io.IOException;
import io.codecastle.scriptorium.FluentAppendable;
import io.codecastle.scriptorium.FluentNode;
import io.codecastle.scriptorium.Inscribable;
import io.codecastle.scriptorium.function.IOBiConsumer;
import io.codecastle.scriptorium.function.IOConsumer;
import io.codecastle.scriptorium.function.IOFunction;

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

	@Override
	public <T extends FluentNode<MockInscribableAppendable>> T inscribe(IOFunction<? super MockInscribableAppendable, T> function) throws IOException {
		return function.apply(this);
	}
	
}
