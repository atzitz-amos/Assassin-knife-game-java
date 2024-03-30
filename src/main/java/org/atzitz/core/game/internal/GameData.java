package org.atzitz.core.game.internal;

import org.atzitz.core.game.player.Player;

import java.util.HashMap;
import java.util.Map;

public class GameData {

    private final Map<Class<?>, DataSupplier<?>> supplierMap;

    public GameData() {
        supplierMap = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    public <T> DataSupplier<T> supplier(Class<T> clazz) {
        return (DataSupplier<T>) supplierMap.computeIfAbsent(clazz, k -> new DataSupplier<T>());
    }

    public DataSupplier<Integer> intSupplier() {
        return supplier(Integer.class);
    }

    public DataSupplier<Boolean> booleanSupplier() {
        return supplier(Boolean.class);
    }

    public DataSupplier<String> stringSupplier() {
        return supplier(String.class);
    }

    public DataSupplier<Double> doubleSupplier() {
        return supplier(Double.class);
    }

    public DataSupplier<Player> playerSupplier() {
        return supplier(Player.class);
    }


    public final static class DataSupplier<T> {
        Map<String, T> data;

        public T get(String key) {
            return data.get(key);
        }

        public void set(String key, T value) {
            data.put(key, value);
        }
    }

}
