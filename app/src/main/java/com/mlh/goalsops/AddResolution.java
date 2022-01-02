package com.mlh.goalsops;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import com.mlh.goalsops.Models.Resolution;
import com.mlh.goalsops.Utilities.Utility;
import com.mlh.goalsops.databinding.FragmentAddResolutionBinding;

import java.util.Calendar;
import java.util.List;

public class AddResolution extends BottomSheetDialogFragment implements DatePickerDialog.OnDateSetListener {

    private FragmentAddResolutionBinding binding;

    private Context context;

    private String title, description, deadline;
    private String[] months;

    private List<Resolution> resolutions;

    public AddResolution(List<Resolution> resolutions) {
        this.resolutions = resolutions;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddResolutionBinding.inflate(inflater, container, false);

        months = context.getResources().getStringArray(R.array.months);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.cancelBtnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        binding.addBtnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDetails();
            }
        });

        binding.dateSelectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                DatePickerDialog dialog = new DatePickerDialog(context, AddResolution.this,
                        year, month, day);
                dialog.show();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        deadline = dayOfMonth + " " + months[month] + ", " + year;
        binding.dateDeadlineResEt.setText(deadline);
    }

    private void getDetails() {
        title = binding.titleResEt.getText().toString().trim();
        description = binding.descriptionResEt.getText().toString().trim();

        saveDetails();
    }

    private void saveDetails() {
        Resolution resolution = new Resolution(title, description, deadline, System.currentTimeMillis());

        SharedPreferences sharedPreferences = context.getSharedPreferences(Utility.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        resolutions.add(resolution);
        String json = gson.toJson(resolutions);
        editor.putString(Utility.KEY, json);
        editor.apply();

        dismiss();
    }
}