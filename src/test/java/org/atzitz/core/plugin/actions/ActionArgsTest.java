package org.atzitz.core.plugin.actions;

import org.assertj.core.api.Assertions;
import org.atzitz.core.exceptions.ArgumentConstraintFailure;
import org.atzitz.core.exceptions.ArgumentTransformerFailure;
import org.atzitz.core.exceptions.MissingRequiredArgumentException;
import org.atzitz.core.plugin.actions.args.Arg;
import org.atzitz.core.plugin.actions.args.ArgsMap;
import org.atzitz.core.plugin.context.InGameContext;
import org.atzitz.datatypes.constants.ActionResult;
import org.junit.jupiter.api.Test;

import java.util.Map;

class ActionArgsTest {
    @Test
    void testMissingRequiredArg() throws
            MissingRequiredArgumentException,
            ArgumentConstraintFailure,
            ArgumentTransformerFailure {
        AbstractAction action = new AbstractAction() {
            @Override
            public ActionResult execute(InGameContext ctx, ArgsMap args) throws
                    ArgumentConstraintFailure,
                    ArgumentTransformerFailure,
                    MissingRequiredArgumentException {
                Arg arg = args.require("target");

                int intTarget = arg.pipe().transform((c, x) -> Integer.parseInt(x)).collect();

                boolean boolTarget = arg.pipe()
                        .transform((c, x) -> Integer.parseInt(x))
                        .transform((c, x) -> x == 2)
                        .ensure((c, x) -> x)
                        .collect();

                Assertions.assertThat(boolTarget).isTrue();
                return ActionResult.SUCCESS;
            }
        };

        Assertions.assertThatThrownBy(() -> action.execute(null, new ArgsMap(Map.of())))
                .isInstanceOf(MissingRequiredArgumentException.class);
    }

    @Test
    void testTransformFailure() throws
            MissingRequiredArgumentException,
            ArgumentConstraintFailure,
            ArgumentTransformerFailure {
        AbstractAction action = new AbstractAction() {
            @Override
            public ActionResult execute(InGameContext ctx, ArgsMap args) throws
                    ArgumentConstraintFailure,
                    ArgumentTransformerFailure,
                    MissingRequiredArgumentException {
                Arg arg = args.require("target");

                int intTarget = arg.pipe().transform((c, x) -> Integer.parseInt(x)).collect();

                boolean boolTarget = arg.pipe()
                        .transform((c, x) -> Integer.parseInt(x))
                        .transform((c, x) -> x == 2)
                        .ensure((c, x) -> x)
                        .collect();

                Assertions.assertThat(boolTarget).isTrue();
                return ActionResult.SUCCESS;
            }
        };

        Assertions.assertThatThrownBy(() -> action.execute(null, new ArgsMap(Map.of("target", new Arg(null, "target", "2s")))))
                .isInstanceOf(ArgumentTransformerFailure.class);
    }

    @Test
    void testConstraintFailure() throws
            MissingRequiredArgumentException,
            ArgumentConstraintFailure,
            ArgumentTransformerFailure {
        AbstractAction action = new AbstractAction() {
            @Override
            public ActionResult execute(InGameContext ctx, ArgsMap args) throws
                    ArgumentConstraintFailure,
                    ArgumentTransformerFailure,
                    MissingRequiredArgumentException {
                Arg arg = args.require("target");

                int intTarget = arg.pipe().transform((c, x) -> Integer.parseInt(x)).collect();

                boolean boolTarget = arg.pipe()
                        .transform((c, x) -> Integer.parseInt(x))
                        .transform((c, x) -> x == 2)
                        .ensure((c, x) -> x)
                        .collect();

                Assertions.assertThat(boolTarget).isTrue();
                return ActionResult.SUCCESS;
            }
        };

        Assertions.assertThatThrownBy(() -> action.execute(null, new ArgsMap(Map.of("target", new Arg(null, "target", "3")))))
                .isInstanceOf(ArgumentConstraintFailure.class);
    }

    @Test
    void testShouldPass() throws
            MissingRequiredArgumentException,
            ArgumentConstraintFailure,
            ArgumentTransformerFailure {
        AbstractAction action = new AbstractAction() {
            @Override
            public ActionResult execute(InGameContext ctx, ArgsMap args) throws
                    ArgumentConstraintFailure,
                    ArgumentTransformerFailure,
                    MissingRequiredArgumentException {

                Arg arg = args.require("target");

                int intTarget = arg.pipe().transform((c, x) -> Integer.parseInt(x)).collect();

                boolean boolTarget = arg.pipe()
                        .transform((c, x) -> Integer.parseInt(x))
                        .transform((c, x) -> x == 2)
                        .ensure((c, x) -> x)
                        .collect();

                Assertions.assertThat(boolTarget).isTrue();
                return ActionResult.SUCCESS;
            }
        };

        action.execute(null, new ArgsMap(Map.of("target", new Arg(null, "target", "2"))));
    }
}