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
                0x16171a,
                0x7f0622,
                0xd62411,
                0xff8426,
                0xffd100,
                0xfafdff,
                0xff80a4,
                0xff2674,
                0x94216a,
                0x430067
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new StuckiDitheringAlgorithm(palette());
    }
}
