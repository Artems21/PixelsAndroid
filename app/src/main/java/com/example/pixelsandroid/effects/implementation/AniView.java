package com.example.pixelsandroid.effects.implementation;

import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class AniView extends AbstractEffect {
    @Override
    public String name() {
        return "Ani View";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF5ba675,
                0xFF6bc96c,
                0xFFabdd64,
                0xFFfcef8d,
                0xFFffb879,
                0xFFea6262,
                0xFFcc425e,
                0xFFa32858,
                0xFF751756,
                0xFF390947,
                0xFF611851,
                0xFF873555,
                0xFFa6555f,
                0xFFc97373,
                0xFFf2ae99,
                0xFFffc3f2,
                0xFFee8fcb,
                0xFFd46eb3,
                0xFF873e84,
                0xFF1f102a,
                0xFF4a3052,
                0xFF7b5480,
                0xFFa6859f,
                0xFFd9bdc8,
                0xFFffffff,
                0xFFaee2ff,
                0xFF8db7ff,
                0xFF6d80fa,
                0xFF8465ec,
                0xFF834dc4,
                0xFF7d2da0,
                0xFF4e187c
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerDitheringAlgorithm(palette(), Color.valueOf(3, 5,6));
    }
}
