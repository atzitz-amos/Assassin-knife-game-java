package org.atzitz.core.plugin.actions;

import org.atzitz.core.exceptions.ArgumentConstraintFailure;
import org.atzitz.core.exceptions.ArgumentTransformerFailure;
import org.atzitz.core.exceptions.MissingRequiredArgumentException;
import org.atzitz.core.plugin.actions.args.ArgsMap;
import org.atzitz.core.plugin.context.InGameContext;
import org.atzitz.datatypes.constants.ActionResult;

public abstract class AbstractAction {

    abstract public ActionResult execute(InGameContext ctx, ArgsMap args)
            throws ArgumentConstraintFailure, ArgumentTransformerFailure, MissingRequiredArgumentException;

}
