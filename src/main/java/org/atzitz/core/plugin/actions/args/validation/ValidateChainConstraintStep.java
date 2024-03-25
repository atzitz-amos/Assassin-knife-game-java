package org.atzitz.core.plugin.actions.args.validation;

import lombok.Getter;
import lombok.Setter;
import org.atzitz.core.exceptions.ArgumentConstraintValidationError;
import org.atzitz.core.game.Game;
import org.atzitz.core.plugin.actions.args.validation.constraints.AbstractConstraint;

@Getter
@Setter
public class ValidateChainConstraintStep<T> implements ValidateChainStep<T> {

    private final AbstractConstraint<T> constraint;
    private ValidateChainStep<?> next = null;

    public ValidateChainConstraintStep(AbstractConstraint<T> constraint) {
        this.constraint = constraint;
    }

    @Override
    public Object get(Game g, T value) throws ArgumentConstraintValidationError {
        if (!constraint.validate(value)) {
            throw new ArgumentConstraintValidationError();
        } else {
            if (next != null) {
                return autocast(next, g, value);
            } else {
                return value;
            }
        }
    }

}
