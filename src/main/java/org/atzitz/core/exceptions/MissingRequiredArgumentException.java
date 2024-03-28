package org.atzitz.core.exceptions;

public class MissingRequiredArgumentException extends Exception {
    private final String name;

    public MissingRequiredArgumentException(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MissingRequiredArgumentError: Required argument `" + name + "` was not supplied";
    }
}
