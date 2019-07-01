package com.miv_sher.hatcheryapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.miv_sher.hatcheryapp.R;
import com.miv_sher.hatcheryapp.utils.Utils;


public class HatchibatorFragment extends Fragment {
    private TextView timerTextView;
    private ImageView backgroundImageView;
    TextView inspiringTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hatchibator, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        timerTextView = view.findViewById(R.id.timerTextView);
        timerTextView.setText("34:55");
        inspiringTextView = view.findViewById(R.id.inspiringTextView);
        inspiringTextView.setText("Никогда не сдавайся!");
        backgroundImageView = view.findViewById(R.id.backgroundImageView);
        Utils.setScaledImage(backgroundImageView, R.drawable.background);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
