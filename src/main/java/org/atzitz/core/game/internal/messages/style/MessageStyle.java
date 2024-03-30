package org.atzitz.core.game.internal.messages.style;

import lombok.Getter;

@Getter
public class MessageStyle {
    public static MessageStyle NORMAL = new MessageStyle();
    public static MessageStyle DEAD = new MessageStyle(MessageColor.GREY);
    public static MessageStyle SYSTEM = new MessageStyle(MessagePosition.CENTER);

    private final String hexColor;
    private final MessagePosition position;

    public MessageStyle(String hexColor, MessagePosition position) {
        this.hexColor = hexColor;
        this.position = position;
    }

    public MessageStyle(MessageColor color, MessagePosition position) {
        this(color.getValue(), position);
    }

    public MessageStyle(MessageColor color) {
        this(color, MessagePosition.LEFT);
    }

    public MessageStyle(String hexColor) {
        this(hexColor, MessagePosition.LEFT);
    }

    public MessageStyle(MessagePosition position) {
        this(MessageColor.BLACK, position);
    }

    public MessageStyle() {
        this(MessageColor.BLACK, MessagePosition.LEFT);
    }
}
