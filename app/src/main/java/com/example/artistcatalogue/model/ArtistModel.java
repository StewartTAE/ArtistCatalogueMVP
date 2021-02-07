package com.example.artistcatalogue.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArtistModel {


    @SerializedName("artistName")
    private String artist;

    @SerializedName("trackName")
    private String title;

    @SerializedName("trackPrice")
    private String price;

    @SerializedName("artworkUrl30")
    private String image;

    public ArtistModel() {

    }

    public String getArtist() {
        return artist; }

    public void setArtist(String artist) {this.artist = artist;}

    public String getTitle() {return title; }

    public void setTitle(String title) {this.title = title;}

    public String getPrice() {return price;}

    public void setPrice(String price) {this.price = price; }

    public String getImage() { return image;}
}
