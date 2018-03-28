/*
 * Copyright 2018 Doug Valenta.
 * Licensed under the terms of the MIT License.
 */
package net.dougvalenta.scriptorium.json;

import net.dougvalenta.scriptorium.FluentAppendable;
import net.dougvalenta.scriptorium.Inscribable;

/**
 *
 * @author Doug Valenta
 */
public interface JsonAppendable<THIS extends JsonAppendable<THIS>> extends FluentAppendable<THIS>, Inscribable<JsonAppendable<?>, THIS> {
	
}
