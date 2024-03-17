package org.atzitz.core.plugin.phases.position;

import lombok.Getter;
import lombok.Setter;
import org.atzitz.core.plugin.phases.IPhase;

import java.util.List;
import java.util.Objects;

@Getter
public class PhaseTreeNode {
    private final IPhase phase;
    private @Setter List<PhaseTreeNode> sideNodes;
    private PhaseTreeNode next;
    private PhaseTreeNode before;

    public PhaseTreeNode(IPhase phase) {
        this.phase = phase;
    }

    public void setNext(PhaseTreeNode next) {
        this.next = next;
        if (next != null) next.before = this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (PhaseTreeNode) obj;
        return Objects.equals(this.phase, that.phase);
    }

    @Override
    public String toString() {
        return (phase != null ? phase.getClass().getSimpleName() : "null") + "->" + next;
    }

}
