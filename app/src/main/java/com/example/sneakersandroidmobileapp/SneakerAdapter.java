package com.example.sneakersandroidmobileapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SneakerAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SneakerModel> sneakerList;
    private LayoutInflater layoutInflater;

    public SneakerAdapter(Context context, ArrayList<SneakerModel> sneakerList){
        this.context = context;
        this.sneakerList = sneakerList;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return sneakerList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = layoutInflater.inflate(R.layout.sneaker_item, viewGroup, false);
        }

        SneakerModel currentItem = sneakerList.get(i);

        TextView sneakerReleaseDate = view.findViewById(R.id.sneakerReleaseDate);
        ImageView sneakerImage = view.findViewById(R.id.sneakerImage);
        TextView sneakerName = view.findViewById(R.id.sneakerName);
        TextView sneakerPrice = view.findViewById(R.id.sneakerPrice);

        String releaseDate = currentItem.getReleaseDate();
        String imageUrl = currentItem.getGridPicture();
        String name = currentItem.getName();
        int price = currentItem.getPriceCents() / 100;

        sneakerReleaseDate.setText(releaseDate);
        Picasso.get().load(imageUrl).into(sneakerImage);
        sneakerName.setText(name);
        sneakerPrice.setText("$"+price);

        return view;
    }
}
