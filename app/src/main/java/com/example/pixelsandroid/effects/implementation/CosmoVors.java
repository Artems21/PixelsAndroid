package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.StuckiDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class CosmoVors extends AbstractEffect {
    @Override
    public String name() {
        return "Cosmo Vors";
    }

    @Override
    public int[] palette() {
        return new int[] {
                0xFF3c1c4a,
                0xFF574084,
                0xFF655ec0,
                0xFF5a78e3,
                0xFF549fff,
                0xFF4fd8ff,
                0xFF7fffff,
                0xFFbfffff,
                0xFFffffff,
                0xFFffdaef,
                0xFFffa8df,
                0xFFef60bf,
                0xFFe716ac,
                0xFF991674,
                0xFF5c024a,
                0xFF100005
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new StuckiDitheringAlgorithm(palette());
    }
}
