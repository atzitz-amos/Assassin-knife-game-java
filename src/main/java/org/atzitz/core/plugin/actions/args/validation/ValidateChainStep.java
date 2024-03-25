package org.atzitz.core.plugin.actions.args.validation;

import org.atzitz.core.exceptions.ArgumentConstraintValidationError;
import org.atzitz.core.exceptions.ArgumentTransformerFailure;
import org.atzitz.core.game.Game;

import java.lang.reflect.InvocationTargetException;

public interface ValidateChainStep<T> {
    // Some hack around java type validation
    default Object autocast(ValidateChainStep<?> next, Game g, Object casting) {
        try {
            next.getClass().getMethod("get", Game.class, casting.getClass()).invoke(next, g, casting);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    Object get(Game g, T value) throws ArgumentConstraintValidationError, ArgumentTransformerFailure;

    ValidateChainStep<?> getNext();

    void setNext(ValidateChainStep<?> next);
}
