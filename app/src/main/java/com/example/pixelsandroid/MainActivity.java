package com.example.pixelsandroid;

import android.annotation.SuppressLint;
import android.content.ContentValues;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;
import com.example.pixelsandroid.effects.implementation.AniView;
import com.example.pixelsandroid.effects.implementation.ArtisticDance;
import com.example.pixelsandroid.effects.implementation.BK0010;
import com.example.pixelsandroid.effects.implementation.BayerMono;
import com.example.pixelsandroid.effects.implementation.BayerMono4;
import com.example.pixelsandroid.effects.implementation.Cherry;
import com.example.pixelsandroid.effects.implementation.CherryView;
import com.example.pixelsandroid.effects.implementation.ClassicC64;
import com.example.pixelsandroid.effects.implementation.ClassicCGA;
import com.example.pixelsandroid.effects.implementation.ClassicPCII;
import com.example.pixelsandroid.effects.implementation.ControlCherry;
import com.example.pixelsandroid.effects.implementation.Coral4;
import com.example.pixelsandroid.effects.implementation.CosmoVors;
import com.example.pixelsandroid.effects.implementation.CrazySanta;
import com.example.pixelsandroid.effects.implementation.CuberSummer;
import com.example.pixelsandroid.effects.implementation.DaytimeSleep;
import com.example.pixelsandroid.effects.implementation.DitherNorm;
import com.example.pixelsandroid.effects.implementation.DreamStar;
import com.example.pixelsandroid.effects.implementation.DualBase;
import com.example.pixelsandroid.effects.implementation.DustyPlace;
import com.example.pixelsandroid.effects.implementation.Elephant;
import com.example.pixelsandroid.effects.implementation.EveningForest;
import com.example.pixelsandroid.effects.implementation.Final14;
import com.example.pixelsandroid.effects.implementation.FrozenPhoto;
import com.example.pixelsandroid.effects.implementation.FruitFive;
import com.example.pixelsandroid.effects.implementation.GreenTree;
import com.example.pixelsandroid.effects.implementation.Half32;
import com.example.pixelsandroid.effects.implementation.HotChocolate;
import com.example.pixelsandroid.effects.implementation.Impulse;
import com.example.pixelsandroid.effects.implementation.Just16;
import com.example.pixelsandroid.effects.implementation.LimeNight;
import com.example.pixelsandroid.effects.implementation.LiquidChrome;
import com.example.pixelsandroid.effects.implementation.LoFiPixels;
import com.example.pixelsandroid.effects.implementation.Lotty;
import com.example.pixelsandroid.effects.implementation.MDR2;
import com.example.pixelsandroid.effects.implementation.MagentaDither;
import com.example.pixelsandroid.effects.implementation.MagentaOdd;
import com.example.pixelsandroid.effects.implementation.Moda12;
import com.example.pixelsandroid.effects.implementation.MonoDither;
import com.example.pixelsandroid.effects.implementation.MonoStucki;
import com.example.pixelsandroid.effects.implementation.OilStucki;
import com.example.pixelsandroid.effects.implementation.OldSunset;
import com.example.pixelsandroid.effects.implementation.PastelHi;
import com.example.pixelsandroid.effects.implementation.PastelView;
import com.example.pixelsandroid.effects.implementation.PhotoPaper;
import com.example.pixelsandroid.effects.implementation.Pico8;
import com.example.pixelsandroid.effects.implementation.PocketConsole;
import com.example.pixelsandroid.effects.implementation.Psycho;
import com.example.pixelsandroid.effects.implementation.Rock16;
import com.example.pixelsandroid.effects.implementation.SimpleCase;
import com.example.pixelsandroid.effects.implementation.Slom;
import com.example.pixelsandroid.effects.implementation.SmallTown;
import com.example.pixelsandroid.effects.implementation.SuggarWorld;
import com.example.pixelsandroid.effects.implementation.SummerTea;
import com.example.pixelsandroid.effects.implementation.T800;
import com.example.pixelsandroid.effects.implementation.T800Dither;
import com.example.pixelsandroid.effects.implementation.T800Flex;
import com.example.pixelsandroid.effects.implementation.Ton60;
import com.example.pixelsandroid.effects.implementation.UndergroundWorms;
import com.example.pixelsandroid.effects.implementation.Vic20;
import com.example.pixelsandroid.effects.implementation.WarmLight;
import com.example.pixelsandroid.effects.implementation.Win16Classic;
import com.example.pixelsandroid.effects.implementation.XRGBDithering;
import com.example.pixelsandroid.effects.implementation.YellowNight;
import com.google.android.material.slider.Slider;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private Slider effectSlider;
    private Bitmap imageData;
    private AbstractEffect currentEffect;
    private ImageView imageView;
    private Bitmap currentBitmap = null;

    private final int[][] sizesArr = new int[][]{
            {128, 128},
            {160, 160},
            {200, 200},
            {220, 220},
            {256, 256},
            {320, 256},
            {320, 220},
            {320, 200}

    };

    private int[] currentSize = new int[]{256, 256};

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

        //Init image view
        imageView = findViewById(R.id.imageView);


        //Effect registration
        AbstractEffect.registerEffects(
                new AbstractEffect[]{
                        new PastelView(),
                        new BayerMono4(),
                        new CosmoVors(),
                        new AniView(),
                        new PocketConsole(),
                        new T800Flex(),
                        new HotChocolate(),
                        new FrozenPhoto(),
                        new ClassicPCII(),
                        new MagentaOdd(),
                        new LiquidChrome(),
                        new Ton60(),
                        new OldSunset(),
                        new BK0010(),
                        new YellowNight(),
                        new XRGBDithering(),
                        new GreenTree(),
                        new DitherNorm(),
                        new MonoStucki(),
                        new FruitFive(),
                        new Pico8(),
                        new Coral4(),
                        new ArtisticDance(),
                        new ClassicC64(),
                        new Elephant(),
                        new EveningForest(),
                        new OilStucki(),
                        new UndergroundWorms(),
                        new PhotoPaper(),
                        new Half32(),
                        new Lotty(),
                        new DualBase(),
                        new Moda12(),
                        new T800(),
                        new MagentaDither(),
                        new CuberSummer(),
                        new Impulse(),
                        new Vic20(),
                        new MonoDither(),
                        new DreamStar(),
                        new Slom(),
                        new Win16Classic(),
                        new Cherry(),
                        new LimeNight(),
                        new DaytimeSleep(),
                        new ControlCherry(),
                        new SuggarWorld(),
                        new CherryView(),
                        new LoFiPixels(),
                        new Final14(),
                        new SummerTea(),
                        new SimpleCase(),
                        new BayerMono(),
                        new ClassicCGA(),
                        new CrazySanta(),
                        new T800Dither(),
                        new SmallTown(),
                        new Rock16(),
                        new WarmLight(),
                        new DustyPlace(),
                        new MDR2(),
                        new PastelHi(),
                        new Just16(),
                        new Psycho()
                }
        );


        //Slider event
        effectSlider = findViewById(R.id.effectSlider);
        effectSlider.addOnChangeListener((slider, value, fromUser) -> drawNewImage(value));

        Spinner effectSpinner = findViewById(R.id.effectSpinner);

        String[] effectsNames = Arrays.stream(AbstractEffect.effects())
                .map(AbstractEffect::name)
                .toArray(String[]::new);

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                this,
                android.R.layout.simple_spinner_item,
                effectsNames
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        effectSpinner.setAdapter(adapter);
        effectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentEffect = AbstractEffect.effects()[position];
                drawNewImage(effectSlider.getValue());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner sizeSpinner = findViewById(R.id.sizeSpinner);

        String[] textSizes = {"128x128",
                "160x160",
                "200x200",
                "220x220",
                "256x256",
                "320x256",
                "320x220",
                "320x200"
        };


        ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter<CharSequence>(
                this,
                android.R.layout.simple_spinner_item,
                textSizes
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(adapter2);
        sizeSpinner.setSelection(4);
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentSize = sizesArr[position];
                drawNewImage(effectSlider.getValue());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Event for random button
        findViewById(R.id.random_effect_button).setOnClickListener(v -> {
            //Spinner listener call effect changer
            effectSpinner.setSelection((int) (Math.random() * AbstractEffect.effects().length));
            effectSlider.setValue((((int) (Math.random() * 144)) * 0.00625f) + 0.1f);
        });


        //Event for save button
        findViewById(R.id.save_button).setOnClickListener(v -> {
            if (currentBitmap != null) {
                String name = "CustomPhoto_" + currentEffect.name() + System.currentTimeMillis();
                int resultCode = saveImageToGallery(currentBitmap, name);

                new AlertDialog.Builder(this)
                        .setTitle("Result")
                        .setMessage(resultCode == 0 ? "Successfully saved to gallery" : "Error, something went wrong")
                        .show();
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
            Bitmap scaledBitmap = algorithm.process(imageData, value, currentSize);
            imageView.setImageBitmap(scaledBitmap);
            currentBitmap = scaledBitmap;
        }
    }

    private int saveImageToGallery(Bitmap bitmap, String name) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DISPLAY_NAME, name + ".jpg");
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/PixelsAndroid");

        try {
            Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            if (uri != null) {
                OutputStream outputStream = getContentResolver().openOutputStream(uri);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

}