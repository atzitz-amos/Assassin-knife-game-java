package org.atzitz.core.plugin.actions.args.validation;

import org.atzitz.core.game.Game;
import org.atzitz.core.plugin.actions.args.validation.constraints.AbstractConstraint;
import org.atzitz.core.plugin.actions.args.validation.transforms.AbstractTransformer;

public class ValidateChain {
    private ValidateChainStep<?> head;
    private ValidateChainStep<?> chain;

    public void addTransformer(AbstractTransformer<?> transformer) {
        if (head == null) {
            head = new ValidateChainTransformerStep<>(transformer);
            chain = head;
        } else {
            chain.setNext(new ValidateChainTransformerStep<>(transformer));
            chain = chain.getNext();
        }
    }

    public void addConstraint(AbstractConstraint<?> constraint) {
        if (head == null) {
            head = new ValidateChainConstraintStep<>(constraint);
            chain = head;
        } else {
            chain.setNext(new ValidateChainConstraintStep<>(constraint));
            chain = chain.getNext();
        }
    }

    public Object validate(Game g, Object value) {
        Object result;
        chain = head;
        while (chain != null) {
            result = chain.get(g, value);
            chain = chain.getNext();
        }
    }
}
