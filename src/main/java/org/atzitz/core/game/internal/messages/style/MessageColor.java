package org.atzitz.core.game.internal.messages.style;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MessageColor {
    BLACK("#000000"),
    RED("#FF0000"),
    GREEN("#00FF00"),
    YELLOW("#FFFF00"),
    BLUE("#0000FF"),
    PURPLE("#800080"),
    CYAN("#00FFFF"),
    WHITE("#FFFFFF"),
    ORANGE("#FFA500"),
    PINK("#FFC0CB"),
    GREY("#808080"),
    BROWN("#A52A2A"),
    LIGHT_GRAY("#D3D3D3"),
    DARK_GRAY("#A9A9A9"),
    DARK_RED("#8B0000"),
    ROYAL_BLUE("#4169E1"),
    MIDNIGHT_BLUE("#191970"),
    DARK_GREEN("#006400"),
    OLIVE("#808000"),
    MAROON("#800000"),
    NAVY("#000080"),
    TEAL("#008080"),
    INDIGO("#4B0082"),
    DARK_OLIVE_GREEN("#556B2F"),
    DARK_ORCHID("#9932CC"),
    DARK_SALMON("#E9967A"),
    DARK_SEA_GREEN("#8FBC8F"),
    DARK_SLATE_BLUE("#483D8B"),
    DARK_SLATE_GRAY("#2F4F4F"),
    DARK_TURQUOISE("#00CED1"),
    DARK_VIOLET("#9400D3");

    private final String value;
}
