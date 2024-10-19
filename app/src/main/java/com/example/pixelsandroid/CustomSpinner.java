package com.example.pixelsandroid;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomSpinner extends ArrayAdapter<String> {
    public CustomSpinner(Context context, List<String> items) {
        super(context, R.layout.custom_spinner_adapter, items);
    }

    @NonNull
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        TextView textView = view.findViewById(R.id.resolution_spinner);
        textView.setText(getItem(position));
        return view;
    }
}

