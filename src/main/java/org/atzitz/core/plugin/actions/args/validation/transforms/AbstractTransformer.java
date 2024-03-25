package org.atzitz.core.plugin.actions.args.validation.transforms;

import org.atzitz.core.exceptions.ArgumentTransformerFailure;
import org.atzitz.core.game.Game;

public abstract class AbstractTransformer<OUT> {
    public abstract <IN> OUT transform(Game g, IN in) throws ArgumentTransformerFailure;
}
