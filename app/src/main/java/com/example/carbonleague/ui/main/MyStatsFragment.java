package com.example.carbonleague.ui.main;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProviders;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MyStatsFragment extends Fragment {

    private MyStatsViewModel mViewModel;
    public int carEmissions = 0;
    public int flightEmissions = 0;

    public static MyStatsFragment newInstance() {
        return new MyStatsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_stats_fragment, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onViewCreated (View view, @Nullable Bundle savedInstanceState) {
        PieChart pieChart = view.findViewById(R.id.chart);
        List<PieEntry> entries = new ArrayList<>();

        try {
            JSONObject jsonRootObj = new JSONObject(loadJSONFromAsset());
            JSONArray carDataJsonArray = jsonRootObj.getJSONArray("all_car_data");
            JSONArray flightDataJsonArray = jsonRootObj.getJSONArray("all_flight_data");
            for (int i=0; i<carDataJsonArray.length(); i++) {
                String params = carDataJsonArray.getString(i);
                //NetworkStuff async = new NetworkStuff();
                //async.execute(params);
            }

        } catch (JSONException e) {
        }
        entries.add(new PieEntry(20.1f, "Train"));
        entries.add(new PieEntry(40, "Driving"));
        entries.add(new PieEntry(207.0f, "Flight"));
        PieDataSet set = new PieDataSet(entries, "Transport Modes");
        PieData data = new PieData(set);
        pieChart.setData(data);
        set.setColors(new int[]{
                Color.parseColor("#FFfada0a"),
                Color.parseColor("#FFf57905"),
                Color.parseColor("#FF9c0303")});
        pieChart.invalidate(); // refresh
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("requestData.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MyStatsViewModel.class);
    }





    class NetworkStuff extends AsyncTask<String, Void, Integer>{


        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Integer doInBackground(String... params) {
            URL url = null;
            try {
                url = new URL("https://climate-api-test.dakar.moccu.net/api/calculate?api-key=PungeTh3ohpohngi2zioseeLoom5ucie6weid4ee&format=json");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            URLConnection con = null;
            try {
                con = url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            HttpURLConnection http = (HttpURLConnection)con;
            try {
                http.setRequestMethod("POST");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            http.setDoOutput(true);
            byte[] out = params[0].getBytes(StandardCharsets.UTF_8);
            int length = out.length;
            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            try {
                http.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try(OutputStream os = http.getOutputStream()) {
                os.write(out);
            } catch (IOException e) {
                e.printStackTrace();
            }
            StringBuilder content = null;
            try (BufferedReader input = new BufferedReader(new InputStreamReader(http.getInputStream()))) {
                String line;
                content = new StringBuilder();
                while ((line = input.readLine()) != null) {
                    // Append each line of the response and separate them
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            } catch (IOException e) {
                System.out.println(http.getErrorStream());
                e.printStackTrace();
            } finally {
                http.disconnect();
            }
            JSONObject resultRootJson = null;
            try {
                resultRootJson = new JSONObject(String.valueOf(content));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                carEmissions += resultRootJson.getInt("result");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }


}
