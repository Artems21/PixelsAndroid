package com.example.pixelsandroid.effects;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;

import java.util.List;

public abstract class AbstractEffect {
    private static AbstractEffect[] _effects;
    public static void registerEffects(AbstractEffect[] effects) {
        _effects = (AbstractEffect[]) effects;
    }
    public static AbstractEffect[] effects() {
        return _effects;
    }

    public abstract String name();

    public abstract int[] palette();

    public abstract AbstractAlgorithm algorithm();

}
