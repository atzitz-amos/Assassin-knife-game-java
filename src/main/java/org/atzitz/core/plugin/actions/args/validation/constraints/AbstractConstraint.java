package org.atzitz.core.plugin.actions.args.validation.constraints;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class AbstractConstraint<T> {
    public abstract boolean validate(T value);
}
