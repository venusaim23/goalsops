package com.mlh.goalsops;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mlh.goalsops.Adapters.ResolutionsAdapter;
import com.mlh.goalsops.Models.Resolution;
import com.mlh.goalsops.databinding.FragmentResolutionsBinding;

import java.util.List;

public class ResolutionsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    FragmentResolutionsBinding binding;

    private List<Resolution> resolutions;
    private ResolutionsAdapter adapter;

    private Context context;
    private ResolutionListener listener;

    public interface ResolutionListener {
        void refreshResolutions(ResolutionsAdapter adapter);
    }

    public ResolutionsFragment(List<Resolution> resolutions) {
        this.resolutions = resolutions;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;

        if (context instanceof ResolutionListener)
            listener = (ResolutionListener) context;
        else
            throw new RuntimeException(context.toString() + " must implement ResolutionListener");
    }

    @Override
    public void onRefresh() {
        if (resolutions != null)
            resolutions.clear();
        adapter.notifyDataSetChanged();
        binding.swipeRefreshLayoutRes.setRefreshing(true);
        setTimer();
        getResolutions();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentResolutionsBinding.inflate(inflater, container, false);

        adapter = new ResolutionsAdapter(context, resolutions);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.swipeRefreshLayoutRes.setColorSchemeResources(R.color.orange_base);
        binding.swipeRefreshLayoutRes.setOnRefreshListener(this);
        binding.swipeRefreshLayoutRes.setRefreshing(true);

        binding.recyclerViewRes.setLayoutManager(new LinearLayoutManager(context));
        binding.recyclerViewRes.setHasFixedSize(true);
        binding.recyclerViewRes.setAdapter(adapter);

        getResolutions();
        setTimer();
    }

    private void setTimer() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (resolutions.isEmpty()) {
                    binding.swipeRefreshLayoutRes.setRefreshing(true);
                    binding.emptyTvRes.setVisibility(View.VISIBLE);
                }
            }
        }, 1000);
    }

    private void getResolutions() {
        listener.refreshResolutions(adapter);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 100:
                //edit
                return true;

            case 101:
                //delete
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}