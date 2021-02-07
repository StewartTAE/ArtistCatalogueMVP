package com.example.artistcatalogue.rest;

import com.example.artistcatalogue.model.ArtistModel;
import com.example.artistcatalogue.model.ResponseModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/search?term=classick&amp;media=music&amp;entity=song&amp;limit=50")
    Observable <ResponseModel> getArtistList();

}
