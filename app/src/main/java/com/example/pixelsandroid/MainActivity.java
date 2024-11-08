package com.example.pixelsandroid;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;
import com.example.pixelsandroid.effects.EmptyEffect;
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
import com.example.pixelsandroid.effects.implementation.FunOrange;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    private Slider effectSlider;
    private Bitmap imageData;
    private AbstractEffect currentEffect;
    private ImageView imageView;
    private Bitmap currentBitmap = null;

    public static final String CUSTOM_EFFECTS_NAME = "CustomEffects.json";

    private final int[][] resolutionArr = new int[][]{
            {128, 128},
            {160, 160},
            {200, 200},
            {220, 220},
            {256, 256},
            {320, 256},
            {320, 220},
            {320, 200}

    };

    private int[] currentResolution = new int[]{256, 256};

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

        Window window = getWindow();
        window.setStatusBarColor(Color.BLACK);

        File file = new File(getFilesDir(), "CustomEffects.json");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Swipe button event
        findViewById(R.id.goto_main_button).setOnClickListener(v -> {
            Intent intent = new Intent(this, EffectCreatorActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);


        });

        //Init image view
        imageView = findViewById(R.id.creator_image_view);

        //Photo button event
        findViewById(R.id.select_photo_button).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 1);

        });

        Gson gson = new Gson();
        ArrayList<EmptyEffect> effectsList = new ArrayList<>();
        Type listType = new TypeToken<ArrayList<EmptyEffect>>() {
        }.getType();

        try {

            FileReader reader = new FileReader(file);

            effectsList = gson.fromJson(reader, listType);

            if (effectsList == null) {
                effectsList = new ArrayList<>();
                System.out.println("null");
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        AbstractEffect[] customEffects = effectsList.toArray(new EmptyEffect[0]);

        AbstractEffect[] basedEffects = new AbstractEffect[]{
                new AniView(),
                new ArtisticDance(),
                new BayerMono(),
                new BayerMono4(),
                new BK0010(),
                new CuberSummer(),
                new Cherry(),
                new CherryView(),
                new ControlCherry(),
                new CosmoVors(),
                new Coral4(),
                new CrazySanta(),
                new ClassicC64(),
                new ClassicCGA(),
                new ClassicPCII(),
                new DaytimeSleep(),
                new DitherNorm(),
                new DreamStar(),
                new DualBase(),
                new DustyPlace(),
                new Elephant(),
                new EveningForest(),
                new Final14(),
                new FruitFive(),
                new FunOrange(),
                new FrozenPhoto(),
                new GreenTree(),
                new Half32(),
                new HotChocolate(),
                new Impulse(),
                new Just16(),
                new LiquidChrome(),
                new LimeNight(),
                new LoFiPixels(),
                new Lotty(),
                new MagentaDither(),
                new MagentaOdd(),
                new MDR2(),
                new Moda12(),
                new MonoDither(),
                new MonoStucki(),
                new OilStucki(),
                new OldSunset(),
                new PastelHi(),
                new PastelView(),
                new Pico8(),
                new PhotoPaper(),
                new Psycho(),
                new PocketConsole(),
                new Rock16(),
                new Slom(),
                new SimpleCase(),
                new SmallTown(),
                new SummerTea(),
                new SuggarWorld(),
                new T800(),
                new T800Dither(),
                new T800Flex(),
                new Ton60(),
                new UndergroundWorms(),
                new Vic20(),
                new WarmLight(),
                new Win16Classic(),
                new XRGBDithering(),
                new YellowNight()
        };

        AbstractEffect[] combinedArray = new AbstractEffect[basedEffects.length + customEffects.length];
        System.arraycopy(basedEffects, 0, combinedArray, 0, basedEffects.length);
        System.arraycopy(customEffects, 0, combinedArray, basedEffects.length, customEffects.length);


        //Effect registration
        AbstractEffect.registerEffects(
                combinedArray
        );


        //Slider event
        effectSlider = findViewById(R.id.value_slider);
        effectSlider.addOnChangeListener((slider, value, fromUser) -> drawNewImage(value));

        Spinner effectSpinner = findViewById(R.id.effects_spinner);

        String[] effectsNames = Arrays.stream(AbstractEffect.effects())
                .map(AbstractEffect::name)
                .toArray(String[]::new);

        effectSpinner.setAdapter(new BasedSpinnerAdapter(this, effectsNames));
        effectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentEffect = AbstractEffect.effects()[position];
                drawNewImage(effectSlider.getValue());
                setBackground(currentEffect.palette());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner resolutionSpinner = findViewById(R.id.resolution_spinner);

        String[] textSizes = {
                "128x128",
                "160x160",
                "200x200",
                "220x220",
                "256x256",
                "320x256",
                "320x220",
                "320x200"
        };


        resolutionSpinner.setAdapter(new BasedSpinnerAdapter(this, textSizes));
        resolutionSpinner.setSelection(4);
        resolutionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentResolution = resolutionArr[position];
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
            effectSlider.setValue((((int) (Math.random() * 144)) * 0.0025f) + 0.1f);
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

        //Event for next resolution button
        findViewById(R.id.next_res_button).setOnClickListener(v -> {
            int maxPosition = resolutionArr.length - 1;
            int currentPosition = resolutionSpinner.getSelectedItemPosition();
            resolutionSpinner.setSelection((currentPosition != maxPosition) ? (currentPosition + 1) : 0);
        });
        //Event for back resolution button
        findViewById(R.id.back_res_button).setOnClickListener(v -> {
            int maxPosition = resolutionArr.length - 1;
            int currentPosition = resolutionSpinner.getSelectedItemPosition();
            resolutionSpinner.setSelection((currentPosition != 0) ? (currentPosition - 1) : maxPosition);
        });
        //Event for next effect button
        findViewById(R.id.next_effect_button).setOnClickListener(v -> {
            int maxPosition = AbstractEffect.effects().length - 1;
            int currentPosition = effectSpinner.getSelectedItemPosition();
            effectSpinner.setSelection((currentPosition != maxPosition) ? (currentPosition + 1) : 0);
        });
        //Event for back effect button
        findViewById(R.id.back_effect_button).setOnClickListener(v -> {
            int maxPosition = AbstractEffect.effects().length - 1;
            int currentPosition = effectSpinner.getSelectedItemPosition();
            effectSpinner.setSelection((currentPosition != 0) ? (currentPosition - 1) : maxPosition);
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
            Bitmap scaledBitmap = algorithm.process(imageData, value, currentResolution);
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


    private void setBackground(int[] colors) {
        if (getSupportActionBar() != null) {
            GradientDrawable gradientDrawable = new GradientDrawable(
                    GradientDrawable.Orientation.BL_TR,
                    colors
            );
            getSupportActionBar().setBackgroundDrawable(gradientDrawable);
        }
    }
}