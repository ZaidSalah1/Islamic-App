package com.example.islamicapp.FragmentActivities;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.islamicapp.R;

public class FragmentOne extends Fragment {

    public FragmentOne() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one_layout, container, false);

        // Access any UI components defined in the layout
        TextView textViewFragmentOne = view.findViewById(R.id.textViewFragmentOne);
        // Customize or interact with UI elements as needed

        return view;
    }
}
