package org.atzitz.core.plugin.actions.args.validation;

import lombok.Getter;
import lombok.Setter;
import org.atzitz.core.exceptions.ArgumentConstraintValidationError;
import org.atzitz.core.exceptions.ArgumentTransformerFailure;
import org.atzitz.core.game.Game;
import org.atzitz.core.plugin.actions.args.validation.transforms.AbstractTransformer;

@Getter
@Setter
public class ValidateChainTransformerStep<T> implements ValidateChainStep<T> {

    private final AbstractTransformer<T> transformer;
    private ValidateChainStep<?> next;

    public ValidateChainTransformerStep(AbstractTransformer<T> transformer) {
        this.transformer = transformer;
    }

    @Override
    public Object get(Game g, Object value) throws ArgumentConstraintValidationError, ArgumentTransformerFailure {
        if (next != null) return autocast(next, g, transformer.transform(g, value));
        else {
            return transformer.transform(g, value);
        }
    }
}
