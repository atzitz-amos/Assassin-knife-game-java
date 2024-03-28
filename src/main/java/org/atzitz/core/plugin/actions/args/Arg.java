package org.atzitz.core.plugin.actions.args;

import lombok.Getter;
import org.atzitz.core.plugin.actions.args.pipeline.Pipeline;
import org.atzitz.core.plugin.context.InGameContext;

@Getter
public record Arg(InGameContext ctx, String name, String value) {

    public Pipeline<String> pipe() {
        return new Pipeline<>(ctx, value);
    }

}
