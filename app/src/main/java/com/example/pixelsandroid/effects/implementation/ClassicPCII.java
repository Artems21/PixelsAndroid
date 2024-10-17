package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.StuckiDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class ClassicPCII extends AbstractEffect {
    @Override
    public String name() {
        return "Classic PC II";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFFffffff,
                0xFFffff00,
                0xFFff6500,
                0xFFdc0000,
                0xFFff0097,
                0xFF360097,
                0xFF0000ca,
                0xFF0097ff,
                0xFF00a800,
                0xFF006500,
                0xFF653600,
                0xFF976536,
                0xFFb9b9b9,
                0xFF868686,
                0xFF454545,
                0xFF000000
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new StuckiDitheringAlgorithm(palette());
    }
}
