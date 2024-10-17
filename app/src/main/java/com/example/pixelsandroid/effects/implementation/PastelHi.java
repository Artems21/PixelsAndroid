package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.StuckiDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class PastelHi extends AbstractEffect {
    @Override
    public String name() {
        return "Pastel Hi";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFFe7ebf8,
                0xFFadb1e0,
                0xFFb06bb5,
                0xFF6d2ea4,
                0xFFaae474,
                0xFF14ada0,
                0xFF597acd,
                0xFF7dc9de,
                0xFFfff7a7,
                0xFFffbe6c,
                0xFFff6773,
                0xFFbb027a,
                0xFFf8c8af,
                0xFFa17374,
                0xFF2e5b86,
                0xFF0c2a47
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new StuckiDitheringAlgorithm(palette());
    }
}
