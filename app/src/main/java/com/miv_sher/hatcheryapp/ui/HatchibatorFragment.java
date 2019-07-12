package com.miv_sher.hatcheryapp.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.miv_sher.hatcheryapp.ApplicationLoader;
import com.miv_sher.hatcheryapp.R;
import com.miv_sher.hatcheryapp.database.entities.Egg;
import com.miv_sher.hatcheryapp.database.entities.Session;
import com.miv_sher.hatcheryapp.model.HatchingAnimationDrawable;
import com.miv_sher.hatcheryapp.utils.TimeUtils;
import com.miv_sher.hatcheryapp.utils.UsageStatsUtils;
import com.miv_sher.hatcheryapp.utils.Utils;
import com.miv_sher.hatcheryapp.viewmodel.HatchibatorViewModel;

import java.util.Date;


public class HatchibatorFragment extends Fragment {
    private final String TAG = "HatchibatorFragment";
    TextView inspiringTextView;
    Button startButton, abortOrGiveUpButton, restartButton;
    Handler handler = new Handler();
    Session currentSession = null;
    boolean needShakeAnim = false;
    private HatchibatorViewModel mHatchibatorVM;
    ChooseEggDialog.EggDialogListener eggDialogListener = new ChooseEggDialog.EggDialogListener() {
        @Override
        public void onSessionStarted(Session session) {
            mHatchibatorVM.saveCurrentSession(session);
        }
    };
    View.OnClickListener onEggClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ChooseEggDialog chooseEggDialog = new ChooseEggDialog(eggDialogListener);
            chooseEggDialog.show(getFragmentManager(), ChooseEggDialog.class.getName());
        }
    };
    private TextView timerTextView;
    private ImageView eggImageView, hatchingEffectsImageView;
    private CountDownTimer countDownTimer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hatchibator, container, false);
        initVM();
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
        inspiringTextView = view.findViewById(R.id.inspiringTextView);
        inspiringTextView.setText("Никогда не сдавайся!");

        eggImageView = view.findViewById(R.id.eggImageView);
        hatchingEffectsImageView = view.findViewById(R.id.hatching_effects_imageView);
        startButton = view.findViewById(R.id.startButton);
        abortOrGiveUpButton = view.findViewById(R.id.abortButton);
        abortOrGiveUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer.cancel();
                mHatchibatorVM.saveCurrentSession(null);
            }
        });
        restartButton = view.findViewById(R.id.restartButton);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer.cancel();
                mHatchibatorVM.saveCurrentSession(null);
            }
        });
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

    private void initVM() {
        mHatchibatorVM = ViewModelProviders.of(this).get(HatchibatorViewModel.class);
        mHatchibatorVM.mLiveSession.observe(this, new Observer<Session>() {
            @Override
            public void onChanged(Session session) {
                Log.d(TAG, "onChanged: " + (session != null ? session.toString() : "null session"));
                currentSession = session;
                refreshFragment();
            }
        });
    }

    private void refreshFragment() {
        if (currentSession == null) {
            restartButton.setVisibility(View.INVISIBLE);
            Utils.setScaledImage(eggImageView, R.drawable.unknown_egg, true);
            eggImageView.setOnClickListener(onEggClickListener);
            timerTextView.setText("00:00");
            timerTextView.setVisibility(View.INVISIBLE);
            abortOrGiveUpButton.setVisibility(View.INVISIBLE);
        } else {
            restartButton.setVisibility(View.INVISIBLE);
            Egg currentEgg = mHatchibatorVM.currentEgg;
            Utils.setScaledImage(eggImageView, currentEgg.getResId(), false);
            eggImageView.setOnClickListener(null);
            abortOrGiveUpButton.setText(R.string.cancel);
            abortOrGiveUpButton.setVisibility(View.VISIBLE);
            timerTextView.setVisibility(View.VISIBLE);
            final int time = TimeUtils.getSecondsFromDateInterval(new Date(), currentSession.getEndDate());
            countDownTimer = new CountDownTimer(time * 1000, 1000) {
                int currentTime = time;

                public void onTick(long millisUntilFinished) {
                    if (time - millisUntilFinished / 1000 > 10) {
                        abortOrGiveUpButton.setText(R.string.give_up_egg);
                    }
                    timerTextView.setText(TimeUtils.getTimerTextFromSecons(currentTime));
                    currentTime--;
                    checkForViolation();
                }

                public void onFinish() {
                    timerTextView.setVisibility(View.INVISIBLE);
                    getEggResults();
                }

            }.start();
        }
    }

    private void checkForViolation() {
        String res = UsageStatsUtils.getUsageStatsString(currentSession.getStartDate().getTime());
        if (!TextUtils.isEmpty(res)) {
            if (ApplicationLoader.getInstance().appIsBackground()) {
                //Intent intent = new Intent(getActivity(), MainActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                //startActivity(intent);
            } else {
                countDownTimer.cancel();
                mHatchibatorVM.saveCurrentSession(null);
                final AlertDialog dialog = new AlertDialog.Builder(getActivity())
                        .setTitle(ApplicationLoader.getContext().getString(R.string.ooops))
                        .setMessage(getActivity().getString(R.string.violated_egg_terms) + "\n\n" + res)
                        .setPositiveButton(getActivity().getString(R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .create();
                dialog.setCanceledOnTouchOutside(true);
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ApplicationLoader.getContext().getResources().getColor(R.color.colorPrimary));
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ApplicationLoader.getContext().getResources().getColor(R.color.colorPrimary));
                    }
                });
                dialog.show();
            }
        }
    }

    private void getEggResults() {
        final Animation animShake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake_animation);
        needShakeAnim = true;
        animShake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (needShakeAnim)
                            eggImageView.startAnimation(animShake);
                    }
                }, 1500);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        abortOrGiveUpButton.setVisibility(View.INVISIBLE);
        timerTextView.setVisibility(View.INVISIBLE);
        eggImageView.startAnimation(animShake);
        eggImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                needShakeAnim = false;
                eggImageView.clearAnimation();
                hatchEgg();
            }
        });
    }

    private void hatchEgg() {
        restartButton.setVisibility(View.VISIBLE);
        eggImageView.setOnClickListener(null);
        HatchingAnimationDrawable cad = new HatchingAnimationDrawable(
                (AnimationDrawable) getResources().getDrawable(
                        R.drawable.hatching_animation)) {
            @Override
            public void onAnimationStart() {
                // Animation has started...
            }

            @Override
            public void onAnimationFinish() {
                Utils.setScaledImage(eggImageView, R.drawable.tiger, false);
                hatchingEffectsImageView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFrameChanged(int frameNumber) {

            }
        };

        // Set the views drawable to our custom drawable
        hatchingEffectsImageView.setBackground(cad);
        hatchingEffectsImageView.setVisibility(View.VISIBLE);
        // Start the animation
        cad.start();

    }


}
