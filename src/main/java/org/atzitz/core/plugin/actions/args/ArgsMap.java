package org.atzitz.core.plugin.actions.args;

import org.atzitz.core.exceptions.MissingRequiredArgumentException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ArgsMap {

    private final Map<String, Arg> args;

    public ArgsMap(Map<String, Arg> args) {
        this.args = args;
    }


    @Nullable
    public Arg get(String key) {
        return args.get(key);
    }

    @NotNull
    public Arg require(String key) throws MissingRequiredArgumentException {
        Arg arg = args.get(key);
        if (arg == null) {
            throw new MissingRequiredArgumentException(key);
        }
        return arg;
    }
}
