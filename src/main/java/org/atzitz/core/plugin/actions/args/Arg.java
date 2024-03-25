package org.atzitz.core.plugin.actions.args;

import lombok.Getter;
import org.atzitz.core.plugin.actions.args.constraints.Constraints;

@Getter
public class Arg {
    private final String name;
    private final Constraints constraints;

    public Arg(String name, Constraints constraints) {
        this.name = name;
        this.constraints = constraints;
    }

}
