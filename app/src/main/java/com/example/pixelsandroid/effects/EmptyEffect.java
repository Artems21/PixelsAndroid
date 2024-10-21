package com.example.pixelsandroid.effects;

import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerDitheringAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerPowerAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.PaletteDitheringAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.StuckiDitheringAlgorithm;

public class EmptyEffect extends AbstractEffect {
    private String name;
    private int[] palette;
    private int algorithm;


    @Override
    public String name() {
        return name;
    }

    @Override
    public int[] palette() {
        return palette;
    }

    @Override
    public AbstractAlgorithm algorithm() {
        switch (algorithm) {
            case 0:
                return new BayerDitheringAlgorithm(palette, Color.valueOf(3, 3, 3));
            case 1:
                return new BayerPowerAlgorithm(palette);

            case 2:
                return new PaletteDitheringAlgorithm(palette);
            case 3:
                return new StuckiDitheringAlgorithm(palette);
            default:
                return null;
        }
    }

    public EmptyEffect(String name, int[] palette, int algorithm) {
        this.name = name;
        this.palette = palette;
        this.algorithm = algorithm;
    }
}
