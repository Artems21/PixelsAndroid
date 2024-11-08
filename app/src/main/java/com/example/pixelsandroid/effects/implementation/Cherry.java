package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.StuckiDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class Cherry extends AbstractEffect {
    @Override
    public String name() {
        return "Cherry";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF16171a,
                0xFF7f0622,
                0xFFd62411,
                0xFFff8426,
                0xFFffd100,
                0xFFfafdff,
                0xFFff80a4,
                0xFFff2674,
                0xFF94216a,
                0xFF430067
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new StuckiDitheringAlgorithm(palette());
    }
}
