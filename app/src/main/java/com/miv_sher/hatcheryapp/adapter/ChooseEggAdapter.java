package com.miv_sher.hatcheryapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.miv_sher.hatcheryapp.R;

import java.util.Random;

public final class ChooseEggAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @SuppressWarnings("UnsecureRandomNumberGeneration")
    public final Random mRandom = new Random();
    public int[] mColors;
    public int[] mPosition;
    public Context context;
    public final int[] image={
            R.drawable.dragon_one,
            R.drawable.dragon_two,
            R.drawable.dragon_three,
            R.drawable.dragon_four,
            R.drawable.dragon_five
    };
    public final String[] title={
            "Hasib Prince",
            "Ifakhar Hossain",
            "Jin Yean",
            "Victor 2.0",
            "Badiuzzaman"
    };


    public int mItemsCount = 5;
    LayoutInflater inflater;

    public ChooseEggAdapter(Context context) {
        this.context=context;

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mColors = new int[10];
        mPosition = new int[10];

        for (int i = 0; 10 > i; ++i) {
            //noinspection MagicNumber
            mColors[i] = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
            mPosition[i] = i;

        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = inflater.inflate( R.layout.choose_egg_item, null) ;
        RecyclerView.ViewHolder holder = new RowNewsViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((RowNewsViewHolder) holder).cItem1.setText(title[position]);

        ((RowNewsViewHolder) holder).pp.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), image[position], null));

    }

    @Override
    public int getItemCount() {
        return mItemsCount;
    }

    public static class RowNewsViewHolder extends RecyclerView.ViewHolder {
        TextView cItem1;
        ImageView pp;


        public RowNewsViewHolder(View itemView) {
            super(itemView);

            cItem1 = (TextView) itemView.findViewById(R.id.c_item_1);
            pp = (ImageView)itemView.findViewById(R.id.profilePicture);
        }
    }
}
