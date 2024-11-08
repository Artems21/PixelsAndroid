package com.example.pixelsandroid.algorithms.implementation;

import static com.example.pixelsandroid.utils.Utils.calculateNewDimensions;

import android.graphics.Bitmap;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(imageData, width, height, true);
        Bitmap newBitmap = Bitmap.createBitmap(width, height, scaledBitmap.getConfig());

        int[] pixels = new int[width * height];
        scaledBitmap.getPixels(pixels, 0, width, 0, 0, width, height);

        int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future<Void>> futures = new ArrayList<>();

        for (int y = 0; y < height; y++) {
            final int row = y;
            futures.add(executor.submit(() -> {
                for (int x = 0; x < width; x++) {
                    int pixel = pixels[row * width + x];

                    int r = (pixel >> 16) & 0xFF;
                    int g = (pixel >> 8) & 0xFF;
                    int b = pixel & 0xFF;

                    double bright = (0.299 * r + 0.587 * g + 0.114 * b);

                    int bayer = bayerMatrix4x4[row % 4][x % 4];
                    int[] palette = super.getPalette();

                    int palIndex = (int) Math.floor(
                            (bright * palette.length + (bayer - 8) * 16 * 2 * value) / 256
                    );

                    if (palIndex < 0) palIndex = 0;
                    if (palIndex >= palette.length) palIndex = palette.length - 1;

                    int finColor = palette[palIndex];
                    newBitmap.setPixel(x, row, finColor);
                }
                return null;
            }));
        }

        for (Future<Void> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        scaledBitmap.recycle();
        return newBitmap;
    }


}
