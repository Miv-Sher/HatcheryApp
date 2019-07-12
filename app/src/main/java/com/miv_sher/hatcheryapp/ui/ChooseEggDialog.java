package com.miv_sher.hatcheryapp.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.miv_sher.hatcheryapp.R;
import com.miv_sher.hatcheryapp.adapter.ChooseEggAdapter;
import com.miv_sher.hatcheryapp.database.entities.Session;

import java.util.Date;

import static android.widget.AdapterView.INVALID_POSITION;
import static com.miv_sher.hatcheryapp.utils.TimeUtils.addMinutesToDate;

public class ChooseEggDialog extends DialogFragment implements DialogInterface.OnClickListener {
    RecyclerView eggRecyclerView;
    ChooseEggAdapter eggAdapter;
    Button startButton;
    Spinner timeSpinner;
    private EggDialogListener listener;

    public ChooseEggDialog(EggDialogListener listener) {
        this.listener = listener;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle("Title!");
        View v = inflater.inflate(R.layout.choose_egg_dialog, null);

        CarouselLayoutManager carouselLayoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, true);
        eggAdapter = new ChooseEggAdapter(getActivity(), carouselLayoutManager);
        eggRecyclerView = v.findViewById(R.id.choose_eggs_rv);
        initRecyclerView(eggRecyclerView, carouselLayoutManager, eggAdapter);
        timeSpinner = v.findViewById(R.id.spinner);
        startButton = v.findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    Date startDate = new Date();
                    int minutes = 20;
                    switch (timeSpinner.getSelectedItemPosition()) {
                        case 0:
                            minutes = 1;
                            break;
                        case 1:
                            minutes = 20;
                            break;
                        case 2:
                            minutes = 60;
                            break;
                        case 3:
                            minutes = 120;
                            break;
                        case 4:
                            minutes = 240;
                            break;
                    }
                    listener.onSessionStarted(new Session(startDate, addMinutesToDate(startDate, minutes), eggAdapter.getCenterItem().getKey()));
                }
                dismiss();
            }
        });
        return v;
    }


    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case Dialog.BUTTON_POSITIVE:
                break;
            case Dialog.BUTTON_NEGATIVE:
                break;
            case Dialog.BUTTON_NEUTRAL:
                break;
        }
    }

    private void initRecyclerView(final RecyclerView recyclerView, final CarouselLayoutManager layoutManager, final ChooseEggAdapter adapter) {
        // enable zoom effect. this line can be customized
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());

        recyclerView.setLayoutManager(layoutManager);
        // we expect only fixed sized item for now
        recyclerView.setHasFixedSize(true);
        // sample adapter with random data
        recyclerView.setAdapter(adapter);
        // enable center post scrolling
        recyclerView.addOnScrollListener(new CenterScrollListener());

        layoutManager.addOnItemSelectionListener(new CarouselLayoutManager.OnCenterItemSelectionListener() {

            @Override
            public void onCenterItemChanged(final int adapterPosition) {
                if (INVALID_POSITION != adapterPosition) {
                    final int value = adapter.mPosition[adapterPosition];
                    adapter.mPosition[adapterPosition] = (value % 10) + (value / 10 + 1) * 10;
                    adapter.notifyItemChanged(adapterPosition);
                }
            }
        });
    }

    public interface EggDialogListener {
        void onSessionStarted(Session session);
    }
}
