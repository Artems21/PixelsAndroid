package com.example.pixelsandroid.algorithms.implementation;

import static com.example.pixelsandroid.utils.Utils.calculateNewDimensions;
import static com.example.pixelsandroid.utils.Utils.clip;
import static com.example.pixelsandroid.utils.Utils.findClosestColor;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BayerDitheringAlgorithm extends AbstractAlgorithm {

    private final int[][] bayerMatrix4x4 = {
            {0, 12, 3, 15},
            {8, 4, 11, 7},
            {2, 14, 1, 13},
            {10, 6, 9, 5}
    };

    private final Color levels;

    public BayerDitheringAlgorithm(int[] palette, Color levels) {
        super(palette);
        this.levels = levels;
    }

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

                    int r = (pixel >> 16) & 0xFF; // R
                    int g = (pixel >> 8) & 0xFF;  // G
                    int b = pixel & 0xFF;         // B

                    int bayer = bayerMatrix4x4[row % 4][x % 4];

                    int levelR = (int) Math.floor((r * (this.levels.red() + 1) + (bayer - 8) * 16 * value) / 256);
                    int levelG = (int) Math.floor((g * (this.levels.green() + 1) + (bayer - 8) * 16 * value) / 256);
                    int levelB = (int) Math.floor((b * (this.levels.blue() + 1) + (bayer - 8) * 16 * value) / 256);

                    int r2 = this.levels.red() > 1 ? clip((int) (levelR * (255.0f / (this.levels.red() - 1)))) : r;
                    int g2 = this.levels.green() > 1 ? clip((int) (levelG * (255.0f / (this.levels.green() - 1)))) : g;
                    int b2 = this.levels.blue() > 1 ? clip((int) (levelB * (255.0f / (this.levels.blue() - 1)))) : b;

                    int newColor = findClosestColor(r2, g2, b2, getPalette());
                    newBitmap.setPixel(x, row, newColor);
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
