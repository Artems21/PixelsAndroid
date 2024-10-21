package com.example.pixelsandroid;

import static com.example.pixelsandroid.MainActivity.CUSTOM_EFFECTS_NAME;
import static com.example.pixelsandroid.utils.Utils.sortColorsByBrightness;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerDitheringAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerPowerAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.PaletteDitheringAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.StuckiDitheringAlgorithm;
import com.example.pixelsandroid.effects.EmptyEffect;
import com.google.android.material.slider.Slider;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

import yuku.ambilwarna.AmbilWarnaDialog;

public class EffectCreatorActivity extends AppCompatActivity {

    private ImageView imageView;
    private Bitmap imageData;
    private int mDefaultColor = -1;
    private ArrayList<Integer> colorsList = new ArrayList<Integer>();
    private Spinner colorsSpinner;
    private Spinner algorithmsSpinner;
    private CustomSpinnerAdapter adapter;
    private AbstractAlgorithm algorithm;
private Slider valueSlider;

    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_effect_creater);

        Window window = getWindow();
        window.setStatusBarColor(Color.BLACK);


        colorsSpinner = findViewById(R.id.colors_spinner);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF000000));


        adapter = new CustomSpinnerAdapter(this, new ArrayList<>());
        colorsSpinner.setAdapter(adapter);

        colorsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                findViewById(R.id.color_picker_button).setBackgroundColor(colorsList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        valueSlider = findViewById(R.id.value_creator_slider);
        valueSlider.addOnChangeListener((slider, value, fromUser) -> updateImageView());

        //Swipe button event
        findViewById(R.id.goto_main_button).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
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

        algorithmsSpinner = findViewById(R.id.algorithm_spinner);

        String[] algorithms = {
                "BayerDithering",
                "BayerPowerDithering",
                "PaletteDithering",
                "StuckiDithering"
        };


        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                this,
                android.R.layout.simple_spinner_item,
                algorithms
        ) {
            @Override
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                view.setPadding(16, 16, 16, 16);
                return view;
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        algorithmsSpinner.setAdapter(adapter);
        algorithmsSpinner.setSelection(0);
        algorithmsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                       algorithm = new BayerDitheringAlgorithm(null, Color.valueOf(3, 3,3));
                        break;
                    case 1:
                        algorithm = new BayerPowerAlgorithm(null);
                        break;
                    case 2:
                        algorithm = new PaletteDitheringAlgorithm(null);
                        break;
                    case 3:
                        algorithm = new StuckiDitheringAlgorithm(null);
                        break;
                    default:
                        break;
                }
                updateImageView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        EditText editText = findViewById(R.id.name_edit_text);
        editText.setCursorVisible(false);
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (editText.getText().toString().contains("Effect name")) {
                    editText.setText("");
                }
                return false;
            }
        });


        findViewById(R.id.color_picker_button).setOnClickListener(
                v -> {
                    final AmbilWarnaDialog colorPickerDialogue = new AmbilWarnaDialog(EffectCreatorActivity.this, mDefaultColor,
                            new AmbilWarnaDialog.OnAmbilWarnaListener() {
                                @Override
                                public void onCancel(AmbilWarnaDialog dialog) {

                                }

                                @Override
                                public void onOk(AmbilWarnaDialog dialog, int color) {
                                    mDefaultColor = color;
                                    findViewById(R.id.color_picker_button).setBackgroundColor(mDefaultColor);
                                }
                            });
                    colorPickerDialogue.show();
                });


        //Add button event
        findViewById(R.id.add_color_button).setOnClickListener(v -> {
            if (colorsList.contains(mDefaultColor)) {
                new AlertDialog.Builder(this)
                        .setTitle("Error Color")
                        .setMessage("Already added 0x" + Integer.toHexString(mDefaultColor).toUpperCase())
                        .show();
            } else {
                colorsList.add(mDefaultColor);
                sortColorsByBrightness(colorsList);
                int[] colors = colorsList.stream()
                        .mapToInt(obj -> {
                            return (Integer) obj;
                        })
                        .toArray();
                updateSpinner(colors);
                setBackground(colors);
                colorsSpinner.setSelection(colorsList.size() - 1);
                new AlertDialog.Builder(this)
                        .setTitle("New Color")
                        .setMessage("0x" + Integer.toHexString(mDefaultColor).toUpperCase())
                        .show();
                updateImageView();

            }
        });

        //Delete button event
        findViewById(R.id.remove_color_button).setOnClickListener(v -> {
            if (!colorsList.isEmpty()) {
                int currentPosition = colorsSpinner.getSelectedItemPosition();
                colorsList.remove(currentPosition);
                sortColorsByBrightness(colorsList);
                int[] colors = colorsList.stream().mapToInt(Integer::intValue).toArray();
                updateSpinner(colors);
                setBackground(colors);
                new AlertDialog.Builder(this)
                        .setTitle("Delete Color")
                        .setMessage("0x" + Integer.toHexString(mDefaultColor).toUpperCase())
                        .show();
                updateImageView();

            }
        });

        findViewById(R.id.save_effect_button).setOnClickListener(v -> {
            int[] colors = colorsList.stream().mapToInt(Integer::intValue).toArray();
            serializeEffect(editText.getText().toString(), colors, algorithmsSpinner.getSelectedItemPosition());
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (intent != null && requestCode == 1) {
            changeImageSrc(intent);
            drawNewImage();
            //effectSlider.setValue(0.5f);*/
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

    private void updateImageView() {
        if (imageData == null)
            return;
        Bitmap newBitmap;
        int[] colors = colorsList.stream().mapToInt(Integer::intValue).toArray();
        algorithm.updatePalette(colors);
        newBitmap = algorithm.process(imageData, valueSlider.getValue(), new int[]{256, 256});
        imageView.setImageBitmap(newBitmap);
    }

    private void drawNewImage() {
        imageView.setImageBitmap(imageData);
    }

    private void updateSpinner(int[] colors) {
        String[] colorsInText = Arrays.stream(colors)
                .mapToObj(Integer::toHexString)
                .map(String::toUpperCase)
                .map("0x"::concat)
                .toArray(String[]::new);

        adapter.clear();
        adapter.addAll(colorsInText);
        adapter.notifyDataSetChanged();
    }

    private void setBackground(int[] colors) {
        if (getSupportActionBar() != null) {
            if (colors.length >= 2) {
                GradientDrawable gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.BL_TR,
                        colors
                );
                getSupportActionBar().setBackgroundDrawable(gradientDrawable);
            } else {
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF000000));
            }
        }
    }

    private void serializeEffect(String name, int[] palette, int algorithm) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<EmptyEffect>>() {}.getType();

        ArrayList<EmptyEffect> effectsList = new ArrayList<>();
        try {
            File file = new File(getFilesDir(), "CustomEffects.json");

            if (file.exists()) {
                FileReader reader = new FileReader(file);

                effectsList = gson.fromJson(reader, listType);

                if (effectsList == null) {
                    effectsList = new ArrayList<>();
                }

                reader.close();
            }

            EmptyEffect newEffect = new EmptyEffect("[CUSTOM] " + name, palette, algorithm);
            effectsList.add(newEffect);

            FileWriter writer = new FileWriter(file);
            gson.toJson(effectsList, writer);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        new AlertDialog.Builder(this)
                .setTitle("OK!")
                .setMessage("Added new effect " + name)
                .show();
    }

}