package org.atzitz.core.plugin.actions;

import org.atzitz.core.plugin.ActionHandler;
import org.atzitz.datatypes.constants.ActionResult;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ActionsHandler {
    public ActionResult handleAction(ActionHolder action) {
        try {
            Arrays.stream(this.getClass().getMethods())
                    .filter(method -> method.isAnnotationPresent(ActionHandler.class)
                            && method.getAnnotation(ActionHandler.class).value() == action.getActionCls())
                    .forEach(method -> {
                        try {
                            method.invoke(this, action);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (RuntimeException e) {
            return ActionResult.VALUE_ERROR;
        }

        return ActionResult.SUCCESS;
    }
}
