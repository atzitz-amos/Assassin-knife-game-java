package org.atzitz.plugins.default_plugin.actions;

import org.atzitz.core.exceptions.ArgumentTransformerFailure;
import org.atzitz.core.game.Game;
import org.atzitz.core.plugin.actions.args.validation.ValidateChain;
import org.atzitz.core.plugin.actions.args.validation.constraints.AbstractConstraint;
import org.atzitz.core.plugin.actions.args.validation.transforms.AbstractTransformer;
import org.junit.jupiter.api.Test;


class AssassinVoteActionTest {
    @Test
    void test() {
        ValidateChain chain = new ValidateChain();
        chain.addTransformer(new AbstractTransformer<Integer>() {
            @Override
            public <IN> Integer transform(Game g, IN in) throws ArgumentTransformerFailure {
                if (in instanceof String s) {
                    return Integer.parseInt(s);
                } else if (in instanceof Integer i) {
                    return i;
                }
                throw new ArgumentTransformerFailure();
            }
        });
        chain.addConstraint(new AbstractConstraint<Integer>() {
            @Override
            public boolean validate(Integer value) {
                return value == 4;
            }
        });

        System.out.println(chain.validate());
    }
}