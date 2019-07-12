package com.miv_sher.hatcheryapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.miv_sher.hatcheryapp.R;
import com.miv_sher.hatcheryapp.database.entities.Egg;
import com.miv_sher.hatcheryapp.utils.SampleData;

import java.util.List;
import java.util.Random;

public final class ChooseEggAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @SuppressWarnings("UnsecureRandomNumberGeneration")
    public final Random mRandom = new Random();
    public int[] mColors;
    public int[] mPosition;
    public Context context;
    public List<Egg> eggItemList = SampleData.getEggs();
    CarouselLayoutManager carouselLayoutManager;

    LayoutInflater inflater;

    public ChooseEggAdapter(Context context, CarouselLayoutManager carouselLayoutManager) {
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.carouselLayoutManager = carouselLayoutManager;
        mColors = new int[eggItemList.size()];
        mPosition = new int[eggItemList.size()];

        for (int i = 0; eggItemList.size() > i; ++i) {
            //noinspection MagicNumber
            mColors[i] = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
            mPosition[i] = i;
        }

    }

    public Egg getCenterItem() {
        return eggItemList.get(carouselLayoutManager.getCenterItemPosition());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = inflater.inflate(R.layout.choose_egg_item, null);
        RecyclerView.ViewHolder holder = new RowNewsViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((RowNewsViewHolder) holder).cItem1.setText(eggItemList.get(position).getDescription());
        ((RowNewsViewHolder) holder).pp.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), eggItemList.get(position).getResId(), null));

    }

    @Override
    public int getItemCount() {
        return eggItemList.size();
    }

    public static class RowNewsViewHolder extends RecyclerView.ViewHolder {
        TextView cItem1;
        ImageView pp;


        public RowNewsViewHolder(View itemView) {
            super(itemView);
            cItem1 = itemView.findViewById(R.id.c_item_1);
            pp = itemView.findViewById(R.id.profilePicture);
        }
    }
}
