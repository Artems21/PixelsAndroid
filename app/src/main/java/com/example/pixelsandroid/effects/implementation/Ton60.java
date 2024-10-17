package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.StuckiDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class Ton60 extends AbstractEffect {
    @Override
    public String name() {
        return "Ton 60";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFFffb780,
                0xFFdf6d5c,
                0xFFb42a43,
                0xFF871638,
                0xFF470d2f,
                0xFFfdf3c9,
                0xFFffe789,
                0xFFffc15c,
                0xFFd66b4d,
                0xFF5e2039,
                0xFFc9e17a,
                0xFF85d25a,
                0xFF33ab47,
                0xFF15674a,
                0xFF093a3c,
                0xFF8acbe8,
                0xFF6c89e1,
                0xFF5642ca,
                0xFF261c65,
                0xFF160b2d,
                0xFFd8abd0,
                0xFFbe7ec5,
                0xFF6c4594,
                0xFF422770,
                0xFF1c122d,
                0xFFf9cec8,
                0xFFe38f9e,
                0xFFc7628a,
                0xFF9a3f72,
                0xFF431e39,
                0xFFc79e9e,
                0xFF8e5f64,
                0xFF583445,
                0xFF3c2230,
                0xFF1c0f18,
                0xFFaa9ccc,
                0xFF5f558d,
                0xFF454070,
                0xFF24213e,
                0xFF121222,
                0xFFa3d8b3,
                0xFF5cb68a,
                0xFF2b8074,
                0xFF1c585e,
                0xFF0e2836,
                0xFFdbbf9e,
                0xFFca977c,
                0xFFae6a52,
                0xFF72363e,
                0xFF471930,
                0xFFe4e2ea,
                0xFFcdc9d8,
                0xFFa49fb6,
                0xFF86809a,
                0xFF5b556f,
                0xFF453f56,
                0xFF322e42,
                0xFF221f2d,
                0xFF13111a,
                0xFF09080d
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new StuckiDitheringAlgorithm(palette());
    }
}
