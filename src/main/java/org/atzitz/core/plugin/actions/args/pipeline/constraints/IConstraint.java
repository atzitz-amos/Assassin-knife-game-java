package org.atzitz.core.plugin.actions.args.pipeline.constraints;

import org.atzitz.core.exceptions.ArgumentConstraintFailure;
import org.atzitz.core.plugin.context.InGameContext;

public interface IConstraint<IN> {
    boolean validate(InGameContext ctx, IN value) throws ArgumentConstraintFailure;
}
