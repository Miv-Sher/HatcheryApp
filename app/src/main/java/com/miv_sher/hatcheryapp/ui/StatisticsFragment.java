package com.miv_sher.hatcheryapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.miv_sher.hatcheryapp.R;
import com.miv_sher.hatcheryapp.utils.UsageStatsUtils;

import java.util.Date;

import static com.miv_sher.hatcheryapp.utils.UsageStatsUtils.checkAndAskPermission;

public class StatisticsFragment extends Fragment {

    TextView statsTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        loadAppList();
    }

    private void loadAppList() {
        checkAndAskPermission(getActivity());
        Date date = new Date();
        String res = UsageStatsUtils.getUsageStatsString(date.getTime() - 10 * 1000 * 60);
        //statsTextView.setText(res);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        statsTextView = view.findViewById(R.id.statsTextView);


    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadAppList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}

