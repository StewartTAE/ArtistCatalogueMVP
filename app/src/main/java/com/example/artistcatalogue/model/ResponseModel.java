package com.example.artistcatalogue.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseModel {

    @SerializedName("resultCount")
    private int resultCount;

    @SerializedName("results")
    private List<ArtistModel> artistModel;

    public ResponseModel() {

    }

    public int getResultCount() {
        return resultCount;
    }

    public List<ArtistModel> getArtistModel() {
        return artistModel;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public void setArtistModel(List<ArtistModel> artistModel) {
        this.artistModel = artistModel;
    }
}
