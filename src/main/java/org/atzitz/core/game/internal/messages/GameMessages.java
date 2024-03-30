package org.atzitz.core.game.internal.messages;

import org.atzitz.core.game.internal.messages.style.MessageStyle;
import org.atzitz.core.plugin.context.InGameContext;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GameMessages {
    private final List<Message> messages;

    public GameMessages() {
        messages = new ArrayList<>();
    }

    public void send(Message message) {
        messages.add(message);
    }

    public void send(String compoundKey) {
        messages.add(new Message(compoundKey));
    }

    public void send(String compoundKey, MessageStyle style) {
        messages.add(new Message(compoundKey, style));
    }

    public void send(String compoundKey, MessageStyle style, Predicate<InGameContext> visibility) {
        messages.add(new Message(compoundKey, style, visibility));
    }

    public List<Message> query(InGameContext ctx) {
        return messages.stream().filter(message -> message.visibility().test(ctx)).collect(Collectors.toList());
    }

    public List<Message> queryAll() {
        return messages;
    }
}
