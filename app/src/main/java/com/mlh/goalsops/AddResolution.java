package com.mlh.goalsops;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.mlh.goalsops.databinding.FragmentAddResolutionBinding;

public class AddResolution extends BottomSheetDialogFragment {

    private FragmentAddResolutionBinding binding;

    public AddResolution() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddResolutionBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}