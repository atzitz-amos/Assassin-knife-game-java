package org.atzitz.core.exceptions;

import org.atzitz.core.plugin.actions.args.pipeline.constraints.IConstraint;

public class ArgumentConstraintFailure extends Exception {
    public ArgumentConstraintFailure(IConstraint<?> constraint) {
        super("Argument constraint failed: " + constraint.getClass().getSimpleName() + ".class");
    }
}
