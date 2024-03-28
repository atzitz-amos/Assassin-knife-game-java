package org.atzitz.core.plugin.actions.args.pipeline;

import org.atzitz.core.exceptions.ArgumentConstraintFailure;
import org.atzitz.core.exceptions.ArgumentTransformerFailure;
import org.atzitz.core.plugin.actions.args.pipeline.constraints.Constraint;
import org.atzitz.core.plugin.actions.args.pipeline.constraints.IConstraint;
import org.atzitz.core.plugin.actions.args.pipeline.transforms.ITransformer;
import org.atzitz.core.plugin.actions.args.pipeline.transforms.Transformer;
import org.atzitz.core.plugin.context.InGameContext;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractPipeline<IN, OUT> {
    protected final IN value;
    protected final InGameContext ctx;

    protected AbstractPipeline(InGameContext ctx, IN value) {
        this.value = value;
        this.ctx = ctx;
    }

    protected abstract OUT transformTo() throws ArgumentTransformerFailure, ArgumentConstraintFailure;


    @NotNull
    public OUT collect() throws ArgumentTransformerFailure, ArgumentConstraintFailure {
        return transformTo();
    }

    public Constraint<OUT> ensure(IConstraint<OUT> constraint)
            throws ArgumentConstraintFailure, ArgumentTransformerFailure {
        return new Constraint<>(ctx, transformTo(), constraint);
    }

    public <X> Transformer<OUT, X> transform(ITransformer<OUT, X> transformer)
            throws ArgumentConstraintFailure, ArgumentTransformerFailure {
        return new Transformer<>(ctx, transformTo(), transformer);
    }
}
