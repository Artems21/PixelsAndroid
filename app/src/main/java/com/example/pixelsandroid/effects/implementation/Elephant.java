package com.example.pixelsandroid.effects.implementation;

import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class Elephant extends AbstractEffect {
    @Override
    public String name() {
        return "Elephant";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF000000,
                0xFFfcfcfc,
                0xFFf8f8f8,
                0xFFbcbcbc,
                0xFF7c7c7c,
                0xFFa4e4fc,
                0xFF3cbcfc,
                0xFF0078f8,
                0xFF0000fc,
                0xFFb8b8f8,
                0xFF6888fc,
                0xFF0058f8,
                0xFF0000bc,
                0xFFd8b8f8,
                0xFF9878f8,
                0xFF6844fc,
                0xFF4428bc,
                0xFFf8b8f8,
                0xFFf878f8,
                0xFFd800cc,
                0xFF940084,
                0xFFf8a4c0,
                0xFFf85898,
                0xFFe40058,
                0xFFa80020,
                0xFFf0d0b0,
                0xFFf87858,
                0xFFf83800,
                0xFFa81000,
                0xFFfce0a8,
                0xFFfca044,
                0xFFe45c10,
                0xFF881400,
                0xFFf8d878,
                0xFFf8b800,
                0xFFac7c00,
                0xFF503000,
                0xFFd8f878,
                0xFFb8f818,
                0xFF00b800,
                0xFF007800,
                0xFFb8f8b8,
                0xFF58d854,
                0xFF00a800,
                0xFF006800,
                0xFFb8f8d8,
                0xFF58f898,
                0xFF00a844,
                0xFF005800,
                0xFF00fcfc,
                0xFF00e8d8,
                0xFF008888,
                0xFF004058,
                0xFFf8d8f8,
                0xFF787878
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerDitheringAlgorithm(palette(), Color.valueOf(5, 5, 6));
    }
}
