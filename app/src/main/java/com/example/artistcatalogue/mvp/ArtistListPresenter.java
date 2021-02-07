package com.example.artistcatalogue.mvp;

import android.util.Log;

import com.example.artistcatalogue.R;
import com.example.artistcatalogue.adapter.ArtistAdapter;
import com.example.artistcatalogue.model.ArtistModel;
import com.example.artistcatalogue.model.ResponseModel;
import com.example.artistcatalogue.rest.ApiClient;
import com.example.artistcatalogue.rest.ApiInterface;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class ArtistListPresenter implements ViewPresenterContract.IPresenter {

    private ViewPresenterContract.IView iView;

    public ArtistListPresenter(ViewPresenterContract.IView iView) { this.iView = iView; }


    @Override
    public void showArtistList() {
        Retrofit myRetrofit = ApiClient.getClient();
        ApiInterface apiService = myRetrofit.create(ApiInterface.class);
        apiService.getArtistList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseModel> () {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d("subscribe", d.toString());
                    }

                    @Override
                    public void onNext(@NonNull ResponseModel responseModel) {
                        iView.passDataAdapter(responseModel.getArtistModel());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //hideMDialog();
                        Log.d("DEBUG_TEST", e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void otherLogic() {

    }
}
