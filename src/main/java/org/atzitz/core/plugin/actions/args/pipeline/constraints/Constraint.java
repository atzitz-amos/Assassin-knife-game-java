package org.atzitz.core.plugin.actions.args.pipeline.constraints;

import lombok.Getter;
import lombok.Setter;
import org.atzitz.core.exceptions.ArgumentConstraintFailure;
import org.atzitz.core.plugin.actions.args.pipeline.AbstractPipeline;
import org.atzitz.core.plugin.context.InGameContext;

@Setter
@Getter
public class Constraint<T> extends AbstractPipeline<T, T> {

    private final IConstraint<T> constraint;

    public Constraint(InGameContext ctx, T value, IConstraint<T> constraint) {
        super(ctx, value);
        this.constraint = constraint;
    }

    @Override
    public T transformTo() throws ArgumentConstraintFailure {
        constraint.validate(ctx, value);
        return value;
    }
}
