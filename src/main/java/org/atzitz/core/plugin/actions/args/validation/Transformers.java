package org.atzitz.core.plugin.actions.args.validation;

import org.atzitz.core.plugin.actions.args.validation.transforms.AbstractTransformer;
import org.atzitz.core.plugin.actions.args.validation.transforms.PlayerTransformer;

public final class Transformers {
    public static AbstractTransformer<Object> toPlayer() {
        return new PlayerTransformer();
    }
}
