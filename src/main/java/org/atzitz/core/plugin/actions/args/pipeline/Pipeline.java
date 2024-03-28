package org.atzitz.core.plugin.actions.args.pipeline;

import lombok.Getter;
import org.atzitz.core.plugin.context.InGameContext;


@Getter
public class Pipeline<T> extends AbstractPipeline<T, T> {
    public Pipeline(InGameContext ctx, T value) {
        super(ctx, value);
    }

    @Override
    protected T transformTo() {
        return value;
    }
}
