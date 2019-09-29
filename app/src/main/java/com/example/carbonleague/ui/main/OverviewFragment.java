package com.example.carbonleague.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carbonleague.R;

import java.util.ArrayList;
import java.util.List;

public class OverviewFragment extends Fragment {

    private OverviewViewModel mViewModel;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<UserScore> scores;
    MyAdapter mAdapter;

    public static OverviewFragment newInstance() {
        return new OverviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.overview_fragment, container, false);
    }
    @Override
    public void onViewCreated (View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        scores = new ArrayList<>();
        scores.add(new UserScore("Anubhav", 158, false));
        scores.add(new UserScore("Manuel", 203, true));
        scores.add(new UserScore("Alan", 220, false));
        scores.add(new UserScore("Tal", 277, true));

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(scores);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OverviewViewModel.class);
        // TODO: Use the ViewModel
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private List<UserScore> mDataset;
        public View view;
        public TextView textViewUserName;
        public TextView textViewNumber;
        public ImageView imageViewVegan;
        public TextView textViewCo2;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class MyViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public MyViewHolder(View v) {
                super(v);
                view = v;
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(List<UserScore> userScores) {
            mDataset = userScores;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.score_view, parent, false);

            textViewUserName = v.findViewById(R.id.user_name);
            textViewNumber = v.findViewById(R.id.number);
            textViewCo2 = v.findViewById(R.id.carbon_kg);
            imageViewVegan = v.findViewById(R.id.vegan_symbol);

            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            imageViewVegan.setVisibility(View.INVISIBLE);
            textViewNumber.setText(Integer.toString(position + 1));
            UserScore uScore = mDataset.get(position);
            textViewUserName.setText(uScore.getUserName());
            if (uScore.isVegan()) {
                imageViewVegan.setVisibility(View.VISIBLE);
            }
            textViewCo2.setText(String.format("~%d kg CO2", uScore.getCo2()));
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }


}
