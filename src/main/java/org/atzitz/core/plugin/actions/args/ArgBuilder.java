package org.atzitz.core.plugin.actions.args;

import org.atzitz.core.plugin.actions.args.constraints.Constraints;

public class ArgBuilder {
    private String name;
    private final Constraints constraints = new Constraints();

    public ArgBuilder name(String name) {
        this.name = name;
        return this;
    }

    public Arg build() {
        return new Arg(name, constraints);
    }
}