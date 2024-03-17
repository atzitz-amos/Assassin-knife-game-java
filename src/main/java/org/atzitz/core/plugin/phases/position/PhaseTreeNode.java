package org.atzitz.core.plugin.phases.position;

import lombok.Getter;
import org.atzitz.core.plugin.context.AbstractContext;
import org.atzitz.core.plugin.phases.IPhase;

import java.util.Objects;
import java.util.function.Predicate;

@Getter
public class PhaseTreeNode {
    private final IPhase phase;
    private final IPhase secondary_phase;
    private final Predicate<AbstractContext> predicate;
    private PhaseTreeNode next;
    private PhaseTreeNode before;

    public PhaseTreeNode(IPhase phase, IPhase secondary_phase, Predicate<AbstractContext> predicate) {
        this.phase = phase;
        this.secondary_phase = secondary_phase;
        this.predicate = predicate;
    }

    public void setNext(PhaseTreeNode next) {
        this.next = next;
        next.before = this;
    }

    public IPhase get(AbstractContext context) {
        return predicate.test(context) ? phase : secondary_phase;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (PhaseTreeNode) obj;
        return Objects.equals(this.phase, that.phase) &&
                Objects.equals(this.secondary_phase, that.secondary_phase) &&
                Objects.equals(this.predicate, that.predicate);
    }

    @Override
    public String toString() {
        return (phase != null ? phase.getClass().getSimpleName() : "null") + "->" + next;
    }
}
