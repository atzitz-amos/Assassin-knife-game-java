package org.atzitz.core.plugin.actions.args.constraints;

public abstract class AbstractConstraint {
    protected final ConstraintRelationship relationship;

    protected AbstractConstraint(ConstraintRelationship relationship) {
        this.relationship = relationship;
    }
}
