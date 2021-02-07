package com.example.artistcatalogue.mvp;

import com.example.artistcatalogue.model.ArtistModel;

import java.util.List;

public interface ViewPresenterContract {

    //presenter and view
    interface IPresenter extends BasePresenter {
        //calls that the view can call
        void showArtistList();
        void otherLogic();
    }

    interface IView extends BaseView<IPresenter> {
        //calls that the presenter can call
        void passDataAdapter(List<ArtistModel> artists);
    }

}
