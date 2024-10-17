package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.StuckiDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class Vic20 extends AbstractEffect {
    @Override
    public String name() {
        return "Vic 20";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF000000,
                0xFFffffff,
                0xFFa8734a,
                0xFFe9b287,
                0xFF772d26,
                0xFFb66862,
                0xFF85d4dc,
                0xFFc5ffff,
                0xFFa85fb4,
                0xFFe99df5,
                0xFF559e4a,
                0xFF92df87,
                0xFF42348b,
                0xFF7e70ca,
                0xFFbdcc71,
                0xFFffffb0
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new StuckiDitheringAlgorithm(palette());
    }
}
