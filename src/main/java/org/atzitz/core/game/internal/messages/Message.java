package org.atzitz.core.game.internal.messages;

import org.atzitz.core.game.internal.messages.style.MessageStyle;
import org.atzitz.core.plugin.context.InGameContext;
import org.atzitz.core.plugin.roles.AbstractRole;

import java.util.Arrays;
import java.util.function.Predicate;

public record Message(String compoundKey, MessageStyle style, Predicate<InGameContext> visibility) {

    public static class Visibility {
        static Predicate<InGameContext> alwaysVisible() {
            return context -> true;
        }

        static Predicate<InGameContext> isDead() {
            return context -> !context.player.isAlive();
        }

        static Predicate<InGameContext> hasRole(Class<? extends AbstractRole> cls) {
            return context -> context.player.getRole().is(cls);
        }

        @SafeVarargs
        static Predicate<InGameContext> hasRoles(Class<? extends AbstractRole>... cls) {
            return context -> Arrays.stream(cls).anyMatch(context.player.getRole()::is);
        }
    }

    public Message(String compoundKey, MessageStyle style) {
        this(compoundKey, style, Visibility.alwaysVisible());
    }

    public Message(String compoundKey) {
        this(compoundKey, MessageStyle.NORMAL, Visibility.alwaysVisible());
    }
}
