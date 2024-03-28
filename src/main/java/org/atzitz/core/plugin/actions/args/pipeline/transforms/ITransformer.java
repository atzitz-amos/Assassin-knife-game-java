package org.atzitz.core.plugin.actions.args.pipeline.transforms;

import org.atzitz.core.exceptions.ArgumentTransformerFailure;
import org.atzitz.core.plugin.context.InGameContext;
import org.jetbrains.annotations.NotNull;

public interface ITransformer<IN, OUT> {
    @NotNull
    OUT transform(InGameContext ctx, IN value) throws ArgumentTransformerFailure;
}
