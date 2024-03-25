package org.atzitz.core.plugin.actions.args;

import org.atzitz.core.plugin.actions.args.validation.ValidateChain;
import org.atzitz.core.plugin.actions.args.validation.constraints.AbstractConstraint;
import org.atzitz.core.plugin.actions.args.validation.transforms.AbstractTransformer;

public class ArgBuilder {
    private final ValidateChain validateChain = new ValidateChain();
    private String name;

    public ArgBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ArgBuilder transform(AbstractTransformer<Object> transformer) {
        validateChain.addTransformer(transformer);
        return this;
    }

    public ArgBuilder ensure(AbstractConstraint<?> constraint) {
        validateChain.addConstraint(constraint);
        return this;
    }

    public Arg build() {
        return new Arg(name, validateChain);
    }
}