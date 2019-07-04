package com.miv_sher.hatcheryapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.miv_sher.hatcheryapp.R;
import com.miv_sher.hatcheryapp.database.entities.Session;
import com.miv_sher.hatcheryapp.utils.Utils;


public class HatchibatorFragment extends Fragment {
    private TextView timerTextView;
    private ImageView eggImageView;
    TextView inspiringTextView;
    Button startButton;
    Button abortOrGiveUpButton;
    View.OnClickListener onEggClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ChooseEggDialog chooseEggDialog = new ChooseEggDialog(eggDialogListener);
            chooseEggDialog.show(getFragmentManager(), ChooseEggDialog.class.getName());
        }
    };

    ChooseEggDialog.EggDialogListener eggDialogListener = new ChooseEggDialog.EggDialogListener() {
        @Override
        public void onSessionStarted(Session session) {

        }
    };

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

        eggImageView = view.findViewById(R.id.eggImageView);
        Utils.setScaledImage(eggImageView, R.drawable.egg);
        eggImageView.setOnClickListener(onEggClickListener);

        startButton = view.findViewById(R.id.startButton);
        abortOrGiveUpButton = view.findViewById(R.id.abortButton);


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
