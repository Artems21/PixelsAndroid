package com.example.pixelsandroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;
import com.example.pixelsandroid.effects.implementation.BayerMono4;
import com.example.pixelsandroid.effects.implementation.PsychoEffect;
import com.google.android.material.slider.Slider;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    private Slider effectSlider;
    private Bitmap imageData;

    private AbstractEffect currentEffect;

    @SuppressLint("MissingInflatedId")
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

        //Effect registration
        AbstractEffect.registerEffects(
                new AbstractEffect[]{
                        new PsychoEffect(),
                        new BayerMono4()
                }
        );


        //Slider event
        effectSlider = findViewById(R.id.effectSlider);
        effectSlider.addOnChangeListener((slider, value, fromUser) -> drawNewImage(value));

        Spinner spinner = findViewById(R.id.themeSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.theme_array,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentEffect = AbstractEffect.effects()[position];
                effectSlider.setValue(0.5f);
                drawNewImage(0.5f);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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


    private void drawNewImage(float value) {
        if (imageData != null) {
            AbstractAlgorithm algorithm = currentEffect.algorithm();
            Bitmap scaledBitmap = algorithm.process(imageData, value);

            ImageView imageView = findViewById(R.id.imageView);
            imageView.setImageBitmap(scaledBitmap);
        }
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