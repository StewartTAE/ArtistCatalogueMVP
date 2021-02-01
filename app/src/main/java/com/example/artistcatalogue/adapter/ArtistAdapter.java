package com.example.artistcatalogue.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.artistcatalogue.R;
import com.example.artistcatalogue.model.ArtistModel;

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {
    private List<ArtistModel> artistList;
    private int rowLayout;
    private Context mContext;

    public ArtistAdapter(List<ArtistModel> artistList, int rowLayout, Context context) {
        this.artistList = artistList;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
            final ArtistModel artist = artistList.get(i);
            viewHolder.artistName.setText(artist.getArtist());
            viewHolder.songTitle.setText(artist.getTitle());
            Glide.with(mContext)
                .load(artist.getImage())
                .into(viewHolder.albumCover);
            viewHolder.price.setText(artist.getPrice());
    }

    @Override
    public int getItemCount() {
        System.out.println(artistList.size());
        return artistList == null ? 0 : artistList.size();

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView artistName;
        public TextView songTitle;
        public TextView price;
        public ImageView albumCover;

        public ViewHolder(View itemView) {
            super(itemView);
            artistName = (TextView) itemView.findViewById(R.id.artist_name);
            songTitle = (TextView) itemView.findViewById(R.id.song_title);
            albumCover = (ImageView) itemView.findViewById(R.id.album_cover);
            price = (TextView) itemView.findViewById(R.id.price);
        }

    }


}
