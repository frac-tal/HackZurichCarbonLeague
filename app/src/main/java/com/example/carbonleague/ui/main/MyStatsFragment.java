package com.example.carbonleague.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carbonleague.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

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
        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(20.5f, "Walking"));
        entries.add(new PieEntry(12.4f, "Biking"));
        entries.add(new PieEntry(20.1f, "Train"));
        entries.add(new PieEntry(40.0f, "Driving"));
        entries.add(new PieEntry(7.0f, "Flight"));
        PieDataSet set = new PieDataSet(entries, "Transport Modes");
        PieData data = new PieData(set);
        pieChart.setData(data);
        set.setColors(new int[]{Color.parseColor("#FF32DA64"),
                Color.parseColor("#FF106e2e"),
                Color.parseColor("#FFfada0a"),
                Color.parseColor("#FFf57905"),
                Color.parseColor("#FF9c0303")});


        pieChart.invalidate(); // refresh

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MyStatsViewModel.class);
        // TODO: Use the ViewModel
    }

}
