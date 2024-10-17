package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.PaletteDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class MagentaDither extends AbstractEffect {
    @Override
    public String name() {
        return "Magenta Dither";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF040026,
                0xFF004bc0,
                0xFF0097ec,
                0xFF00f3fc,
                0xFFffbcff,
                0xFFd569f6,
                0xFF6c1fd3
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new PaletteDitheringAlgorithm(palette());
    }
}
