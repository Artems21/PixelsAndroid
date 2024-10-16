package com.example.pixelsandroid;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.slider.Slider;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    private Slider effectSlider;
    private Bitmap imageData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Permission check
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        //Button event
        findViewById(R.id.selectPhotoButton).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 1);

        });

        //Slider event
        effectSlider = findViewById(R.id.effectSlider);
        effectSlider.addOnChangeListener((slider, value, fromUser) -> drawNewImage(value));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (intent != null && requestCode == 1) {
            changeImageSrc(intent);
            drawNewImage(0.5f);
            effectSlider.setValue(0.5f);
        }
    }

    private void changeImageSrc(Intent intent) {
        Uri imageSrc = intent.getData();
        try {
            imageData = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageSrc);
        } catch (IOException e) {
            throw new RuntimeException("Error when try get bitmap from image \n" + e);
        }
    }

    int[][] bayerMatrix4x4 = {
            {0, 12, 3, 15},
            {8, 4, 11, 7},
            {2, 14, 1, 13},
            {10, 6, 9, 5}
    };

    private void drawNewImage(float value) {

        int newWidth = 256;
        int newHeight = 256;

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(imageData, newWidth, newHeight, true);

        int width = scaledBitmap.getWidth();
        int height = scaledBitmap.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = scaledBitmap.getPixel(x, y);

                int r = (pixel >> 16) & 0xFF; // R
                int g = (pixel >> 8) & 0xFF;  // G
                int b = pixel & 0xFF;         // B

                double bright = (0.299 * r + 0.587 * g + 0.114 * b);

                int bayer = bayerMatrix4x4[y % 4][x % 4];

                int palIndex = (int) Math.floor(
                        (bright * colors.length + (bayer - 8) * 16 * 2 * value) / 256
                );

                if (palIndex < 0) palIndex = 0;
                if (palIndex >= colors.length) palIndex = colors.length - 1;

                int finColor = colors[palIndex];

                scaledBitmap.setPixel(x, y, finColor);
            }
        }

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageBitmap(scaledBitmap);

    }


    static final int[] colors = {
            0xFF10101d,
            0xFF49868b,
            0xFF3eca6b,
            0xFF98eb77,
            0xFF7bb1eb,
            0xFF566ed0,
            0xFF443c7e,
            0xFFaa5ec3,
            0xFFfc83c7,
            0xFFd95959,
            0xFF883a56,
            0xFFec955b,
            0xFFffdd84,
            0xFFffffff
    };

    public static int findClosestColor(int targetColor) {
        int closestColor = colors[0];
        double minDistance = Double.MAX_VALUE;

        for (int color : colors) {
            double distance = calculateDistance(targetColor, color);
            if (distance < minDistance) {
                minDistance = distance;
                closestColor = color;
            }
        }
        return closestColor;
    }

    private static double calculateDistance(int color1, int color2) {
        int r1 = (color1 >> 16) & 0xFF;
        int g1 = (color1 >> 8) & 0xFF;
        int b1 = color1 & 0xFF;

        int r2 = (color2 >> 16) & 0xFF;
        int g2 = (color2 >> 8) & 0xFF;
        int b2 = color2 & 0xFF;

        return Math.sqrt(Math.pow(r2 - r1, 2) + Math.pow(g2 - g1, 2) + Math.pow(b2 - b1, 2));
    }
}