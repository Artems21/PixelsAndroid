package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.StuckiDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class Slom extends AbstractEffect {
    @Override
    public String name() {
        return "Slom";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0x1e1d39,
                0x402751,
                0x7a367b,
                0xa23e8c,
                0xc65197,
                0xdf84a5,
                0x341c27,
                0x602c2c,
                0x884b2b,
                0xbe772b,
                0xde9e41,
                0xe8c170
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new StuckiDitheringAlgorithm(palette());
    }
}
