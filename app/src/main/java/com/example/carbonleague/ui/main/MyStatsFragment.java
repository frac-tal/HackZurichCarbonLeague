package com.example.carbonleague.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carbonleague.R;
import com.github.mikephil.charting.charts.PieChart;

public class MyStatsFragment extends Fragment {

    private MyStatsViewModel mViewModel;

    public static MyStatsFragment newInstance() {
        return new MyStatsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_stats_fragment, container, false);
    }

    @Override
    public void onViewCreated (View view, @Nullable Bundle savedInstanceState) {
        PieChart pieChart = view.findViewById(R.id.chart);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MyStatsViewModel.class);
        // TODO: Use the ViewModel
    }

}
