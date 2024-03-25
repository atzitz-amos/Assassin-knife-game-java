package org.atzitz.core.plugin;

import org.atzitz.core.plugin.actions.IAction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ActionHandler {
    Class<? extends IAction> value();
}
