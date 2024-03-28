package org.atzitz.core.plugin.actions.args.pipeline.transforms;

import lombok.Getter;
import org.atzitz.core.exceptions.ArgumentConstraintFailure;
import org.atzitz.core.exceptions.ArgumentTransformerFailure;
import org.atzitz.core.plugin.actions.args.pipeline.AbstractPipeline;
import org.atzitz.core.plugin.context.InGameContext;
import org.jetbrains.annotations.NotNull;

@Getter
public class Transformer<IN, OUT> extends AbstractPipeline<IN, OUT> {
    private final ITransformer<IN, OUT> transformer;

    public Transformer(InGameContext ctx, IN value, ITransformer<IN, OUT> transformer) {
        super(ctx, value);
        this.transformer = transformer;
    }

    @Override
    @NotNull
    protected OUT transformTo() throws ArgumentTransformerFailure {
        return transformer.transform(ctx, value);
    }
}
