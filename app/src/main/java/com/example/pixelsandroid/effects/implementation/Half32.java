package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.StuckiDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class Half32 extends AbstractEffect {
    @Override
    public String name() {
        return "Half 32";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFFbe4a2f,
                0xFFd77643,
                0xFFead4aa,
                0xFFe4a672,
                0xFFb86f50,
                0xFF733e39,
                0xFF3e2731,
                0xFFa22633,
                0xFFe43b44,
                0xFFf77622,
                0xFFfeae34,
                0xFFfee761,
                0xFF63c74d,
                0xFF3e8948,
                0xFF265c42,
                0xFF193c3e,
                0xFF124e89,
                0xFF0099db,
                0xFF2ce8f5,
                0xFFffffff,
                0xFFc0cbdc,
                0xFF8b9bb4,
                0xFF5a6988,
                0xFF3a4466,
                0xFF262b44,
                0xFF181425,
                0xFFff0044,
                0xFF68386c,
                0xFFb55088,
                0xFFf6757a,
                0xFFe8b796,
                0xFFc28569
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new StuckiDitheringAlgorithm(palette());
    }
}
