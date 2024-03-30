package org.atzitz.core.plugin.actions.args;

import org.atzitz.core.plugin.actions.args.pipeline.Pipeline;
import org.atzitz.core.plugin.context.InGameContext;

public record Arg(InGameContext ctx, String name, String value) {

    public Pipeline<String> pipe() {
        return new Pipeline<>(ctx, value);
    }

}
