package org.atzitz.core.plugin.phases.position;

import lombok.Getter;
import org.atzitz.core.exceptions.MalformedPluginData;
import org.atzitz.core.plugin.phases.IPhase;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Getter
public class PhaseTree {
    private final PhaseTreeNode head;

    private PhaseTree(PhaseTreeNode head) {
        this.head = head;
    }

    private static PhaseTreeNode parse(Collection<IPhase> phases) throws MalformedPluginData {
        return parseIsolated(phases).get(0);
    }

    private static List<PhaseTreeNode> parseIsolated(Collection<IPhase> phases) throws MalformedPluginData {
        PhaseTreeNode root = new PhaseTreeNode(null);
        Map<Class<? extends IPhase>, PhaseTreeNode> nodeMap = phases.stream().collect(Collectors.toMap(IPhase::getClass, PhaseTreeNode::new));
        Map<Class<? extends IPhase>, Collection<IPhase>> duringMap = new HashMap<>();

        phases.forEach(phase -> {
            PhasePosition position = phase.getPhasePosition();
            if (nodeMap.containsKey(position.getDuring())) {
                if (!duringMap.containsKey(position.getDuring())) {
                    duringMap.put(position.getDuring(), new ArrayList<>());
                }
                duringMap.get(position.getDuring()).add(phase);
                nodeMap.remove(phase.getClass());
            } else if (position.isFirst()) {
                delegate(root, nodeMap.get(phase.getClass()), p -> p.getPhasePosition().isFirst());
            } else if (position.getAfter() != null) {
                delegate(nodeMap.get(position.getAfter()), nodeMap.get(phase.getClass()), p -> p.getPhasePosition().getAfter() == position.getAfter());
            }
        });

        duringMap.forEach((k, v) -> {
            try {
                nodeMap.get(k).setSideNodes(parseIsolated(v));
            } catch (MalformedPluginData e) {
                throw new RuntimeException(e);
            }
        });

        return nodeMap.values().stream().filter(node -> node.getBefore() == null || node.getBefore().getPhase() == null).toList();
    }

    private static void delegate(PhaseTreeNode root, PhaseTreeNode node, Predicate<IPhase> predicate) {
        PhaseTreeNode current = root;
        while (current.getNext() != null && predicate.test(current.getNext().getPhase()) && current.getNext().getPhase().getPhasePosition().getPriority() >= node.getPhase().getPhasePosition().getPriority()) {
            current = current.getNext();
        }
        PhaseTreeNode item = node;
        while (item.getNext() != null) item = item.getNext();

        item.setNext(current.getNext());
        current.setNext(node);
    }

    public static PhaseTree of(Collection<IPhase> phases) throws MalformedPluginData {
        return new PhaseTree(parse(phases));
    }
}
