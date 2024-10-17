package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.StuckiDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class MagentaOdd extends AbstractEffect {
    @Override
    public String name() {
        return "Magneta Odd";
    }

    @Override
    public int[] palette() {
        return new int[] {
                0xFF902068,
                0xFFf81868,
                0xFFffa880,
                0xFFff7000,
                0xFFa80010,
                0xFFffa800,
                0xFF5800a8,
                0xFF6828ff,
                0xFFffffff,
                0xFFe0d0ff,
                0xFFa070c8,
                0xFF683090,
                0xFF481868,
                0xFF000000
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new StuckiDitheringAlgorithm(palette());
    }
}
