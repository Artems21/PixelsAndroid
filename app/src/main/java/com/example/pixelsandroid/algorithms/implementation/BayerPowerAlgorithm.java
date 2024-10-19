package com.example.pixelsandroid.algorithms.implementation;

import static com.example.pixelsandroid.utils.Utils.calculateNewDimensions;

import android.graphics.Bitmap;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;

public class BayerPowerAlgorithm extends AbstractAlgorithm {

    public BayerPowerAlgorithm(int[] palette) {
        super(palette);
    }

    private final int[][] bayerMatrix4x4 = {
            {0, 12, 3, 15},
            {8, 4, 11, 7},
            {2, 14, 1, 13},
            {10, 6, 9, 5}
    };
    @Override
    public Bitmap process(Bitmap imageData, float value, int[] sizes) {

        int[] size = calculateNewDimensions(imageData.getWidth(), imageData.getHeight(), sizes);

        int width = size[0];
        int height = size[1];

        Bitmap scaledBitmap =  Bitmap.createScaledBitmap(imageData, width, height, true);
        Bitmap newBitmap = scaledBitmap.copy(scaledBitmap.getConfig(), true);


        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = scaledBitmap.getPixel(x, y);

                int r = (pixel >> 16) & 0xFF; // R
                int g = (pixel >> 8) & 0xFF;  // G
                int b = pixel & 0xFF;         // B

                double bright = (0.299 * r + 0.587 * g + 0.114 * b);

                int bayer = bayerMatrix4x4[y % 4][x % 4];
                int[] palette = super.getPalette();

                int palIndex = (int) Math.floor(
                        (bright * palette.length + (bayer - 8) * 16 * 2 * value) / 256
                );

                if (palIndex < 0) palIndex = 0;
                if (palIndex >= super.getPalette().length) palIndex = palette.length - 1;

                int finColor = palette[palIndex];
                newBitmap.setPixel(x, y, finColor);
            }
        }
        return newBitmap;
    }

}
