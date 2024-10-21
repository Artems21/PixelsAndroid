package com.example.pixelsandroid.algorithms.implementation;

import static com.example.pixelsandroid.utils.Utils.calculateNewDimensions;
import static com.example.pixelsandroid.utils.Utils.clip;
import static com.example.pixelsandroid.utils.Utils.findClosestColor;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class PaletteDitheringAlgorithm extends AbstractAlgorithm {
    public PaletteDitheringAlgorithm(int[] palette) {
        super(palette);
    }

    @Override
    public Bitmap process(Bitmap imageData, float value, int[] sizes) {
        int[] size = calculateNewDimensions(imageData.getWidth(), imageData.getHeight(), sizes);

        int width = size[0];
        int height = size[1];

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(imageData, width, height, true);
        Bitmap newBitmap = scaledBitmap.copy(scaledBitmap.getConfig(), true);


        List<Color> currentError = new ArrayList<>();
        List<Color> nextError = new ArrayList<>();


        for (int i = 0; i < width + 2; i++) {
            currentError.add(Color.valueOf(0, 0, 0));
            nextError.add(Color.valueOf(0, 0, 0));
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = scaledBitmap.getPixel(x, y);

                int r = (pixel >> 16) & 0xFF; // R
                int g = (pixel >> 8) & 0xFF;  // G
                int b = pixel & 0xFF;         // B


                int r2 = clip((int) (r + Math.floor((currentError.get(x + 1).red() * value) / 16)));
                int g2 = clip((int) (g + Math.floor((currentError.get(x + 1).green() * value) / 16)));
                int b2 = clip((int) (b + Math.floor((currentError.get(x + 1).blue() * value) / 16)));


                int closestColor = findClosestColor(r2, g2, b2, getPalette());

                float closestR = (closestColor >> 16) & 0xFF; // R
                float closestG = (closestColor >> 8) & 0xFF;  // G
                float closestB = closestColor & 0xFF;         // B

                newBitmap.setPixel(x, y, closestColor);

                // Calculate error
                int errorR = (int) (r2 - closestR);
                int errorG = (int) (g2 - closestG);
                int errorB = (int) (b2 - closestB);


                currentError.set(x + 2, Color.valueOf(
                        currentError.get(x + 2).red() + 7 * errorR,
                        currentError.get(x + 2).green() + 7 * errorG,
                        currentError.get(x + 2).blue() + 7 * errorB
                ));

                nextError.set(x, Color.valueOf(
                        nextError.get(x).red() + 3 * errorR,
                        nextError.get(x).green() + 3 * errorG,
                        nextError.get(x).blue() + 3 * errorB
                ));

                nextError.set(x + 1, Color.valueOf(
                        nextError.get(x + 1).red() + 5 * errorR,
                        nextError.get(x + 1).green() + 5 * errorG,
                        nextError.get(x + 1).blue() + 5 * errorB
                ));

                nextError.set(x + 2, Color.valueOf(
                        nextError.get(x + 2).red() + 1 * errorR,
                        nextError.get(x + 2).green() + 1 * errorG,
                        nextError.get(x + 2).blue() + 1 * errorB
                ));

            }

            for (int i = 0; i < currentError.size(); i++) {
                currentError.set(i, nextError.get(i));
                nextError.set(i, Color.valueOf(0, 0, 0));
            }

        }
        return newBitmap;
    }

    /*@Override
public Bitmap process(Bitmap imageData, float value, int[] sizes) {
    int[] size = calculateNewDimensions(imageData.getWidth(), imageData.getHeight(), sizes);
    int width = size[0];
    int height = size[1];

    Bitmap scaledBitmap = Bitmap.createScaledBitmap(imageData, width, height, true);
    Bitmap newBitmap = scaledBitmap.copy(scaledBitmap.getConfig(), true);

    // Используем массивы для текущих и следующих ошибок
    int[] currentError = new int[width + 2];
    int[] nextError = new int[width + 2];

    // Инициализируем массивы нулями
    for (int i = 0; i < currentError.length; i++) {
        currentError[i] = 0; // Устанавливаем ошибку в 0 (черный цвет)
        nextError[i] = 0;    // Устанавливаем ошибку в 0 (черный цвет)
    }

    for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
            int pixel = scaledBitmap.getPixel(x, y);

            int r = (pixel >> 16) & 0xFF; // R
            int g = (pixel >> 8) & 0xFF;  // G
            int b = pixel & 0xFF;         // B

            // Применяем текущую ошибку
            int newR = clip(r + (int) Math.floor((currentError[x + 1] >> 16 & 0xFF) * value / 16));
            int newG = clip(g + (int) Math.floor((currentError[x + 1] >> 8 & 0xFF) * value / 16));
            int newB = clip(b + (int) Math.floor((currentError[x + 1] & 0xFF) * value / 16));

            // Находим ближайший цвет
            int closestColor = findClosestColor((newR << 16) | (newG << 8) | newB, getPalette());

            // Получаем компоненты ближайшего цвета
            float closestR = (closestColor >> 16) & 0xFF; // R
            float closestG = (closestColor >> 8) & 0xFF;  // G
            float closestB = closestColor & 0xFF;         // B

            newBitmap.setPixel(x, y, closestColor);

            // Рассчитываем ошибку
            int error = ((newR - (int) closestR) << 16) | ((newG - (int) closestG) << 8) | (newB - (int) closestB);

            // Обновляем текущую ошибку
            currentError[x + 2] += (error & 0xFF0000) + (7 * (error >> 16 & 0xFF));
            currentError[x + 2] += (error & 0x00FF00) + (7 * (error >> 8 & 0xFF));
            currentError[x + 2] += (error & 0x0000FF) + (7 * (error & 0xFF));

            // Обновляем следующую ошибку
            nextError[x] += (error & 0xFF0000) + (3 * (error >> 16 & 0xFF));
            nextError[x] += (error & 0x00FF00) + (3 * (error >> 8 & 0xFF));
            nextError[x] += (error & 0x0000FF) + (3 * (error & 0xFF));

            nextError[x + 1] += (error & 0xFF0000) + (5 * (error >> 16 & 0xFF));
            nextError[x + 1] += (error & 0x00FF00) + (5 * (error >> 8 & 0xFF));
            nextError[x + 1] += (error & 0x0000FF) + (5 * (error & 0xFF));

            nextError[x + 2] += (error & 0xFF0000) + (1 * (error >> 16 & 0xFF));
            nextError[x + 2] += (error & 0x00FF00) + (1 * (error >> 8 & 0xFF));
            nextError[x + 2] += (error & 0x0000FF) + (1 * (error & 0xFF));
        }

        // Обновление текущих и следующих ошибок
        for (int i = 0; i < currentError.length; i++) {
            currentError[i] = nextError[i];
            nextError[i] = 0; // Сбрасываем следующую ошибку
        }
    }

    return newBitmap;
}
*/
}
