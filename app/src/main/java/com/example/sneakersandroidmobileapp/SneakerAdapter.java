package com.example.sneakersandroidmobileapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class SneakerAdapter extends RecyclerView.Adapter<SneakerAdapter.SneakerViewHolder> {
    private Context mContext;
    private List<SneakerModel> mSneakerList;

    public SneakerAdapter(Context context, List<SneakerModel> sneakerList){
        this.mContext = context;
        this.mSneakerList = sneakerList;
    }

    @NonNull
    @Override
    public SneakerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.sneaker_item, parent, false);
        return new SneakerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SneakerViewHolder holder, int position) {
        SneakerModel currentItem = mSneakerList.get(position);

        String releaseDate = currentItem.getReleaseDate();
        String imageUrl = currentItem.getGridPicture();
        String name = currentItem.getName();
        int price = currentItem.getPriceCents() / 100;

        holder.sneakerReleaseDate.setText(releaseDate);
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.sneakerImage);
        holder.sneakerName.setText(name);
        holder.sneakerPrice.setText(price);
    }

    @Override
    public int getItemCount() {
        return mSneakerList.size();
    }

    public class SneakerViewHolder extends RecyclerView.ViewHolder{
        //declare controls from our custom sneaker layout sneaker_items.xml
        private TextView sneakerReleaseDate;
        private ImageView sneakerImage;
        private TextView sneakerName;
        private TextView sneakerPrice;

        public SneakerViewHolder(@NonNull View itemView) {
            super(itemView);
            sneakerReleaseDate = itemView.findViewById(R.id.sneakerReleaseDate);
            sneakerImage = itemView.findViewById(R.id.sneakerImage);
            sneakerName = itemView.findViewById(R.id.sneakerName);
            sneakerPrice = itemView.findViewById(R.id.sneakerPrice);
        }
    }
}
