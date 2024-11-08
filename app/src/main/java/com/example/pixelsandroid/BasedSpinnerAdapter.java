package com.example.pixelsandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class BasedSpinnerAdapter extends ArrayAdapter<String> {

    private final LayoutInflater inflater;

    public BasedSpinnerAdapter(@NonNull Context context, String[] list) {
        super(context, R.layout.spinner_item, list);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        @SuppressLint("ViewHolder") View view = inflater.inflate(R.layout.spinner_item, null, false);
        TextView textView = view.findViewById(R.id.nameTextView);
        textView.setText(getItem(position));
        textView.setTextColor(Color.WHITE);
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.spinner_item, parent, false);
        view.setBackgroundColor(Color.parseColor("#373636"));
        TextView textView = view.findViewById(R.id.nameTextView);
        textView.setText(getItem(position));
        textView.setTextColor(Color.WHITE);
        return view;
    }
}
