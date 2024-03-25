package org.atzitz.core.plugin.actions;

import lombok.Getter;
import org.atzitz.core.plugin.actions.args.ArgsMapping;

@Getter
public class ActionHolder {
    private final Class<? extends IAction> actionCls;
    private final ArgsMapping args;

    public ActionHolder(Class<? extends IAction> action, ArgsMapping args) {
        this.actionCls = action;
        this.args = args;
    }
}
