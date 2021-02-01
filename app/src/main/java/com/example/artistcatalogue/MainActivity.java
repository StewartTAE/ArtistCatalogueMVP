package com.example.artistcatalogue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.example.artistcatalogue.adapter.ArtistAdapter;
import com.example.artistcatalogue.model.ArtistModel;
import com.example.artistcatalogue.rest.ApiClient;
import com.example.artistcatalogue.rest.ApiInterface;
import com.example.artistcatalogue.utilities.RxUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArtistAdapter mAdapter;
    private ProgressDialog mDialog;

    private CompositeSubscription _subscriptions = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_main);

        startRecycler();
        startProgress();
        showArtistList();

    }

    public void startRecycler() {
        mRecyclerView = findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void startProgress() {
        mDialog = new ProgressDialog(this);
        mDialog.setMessage("Loading...");
        mDialog.show();
    }

    private void hideMDialog() {
        if(mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    public void showArtistList() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        apiService.getArtistList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArtistModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ArtistModel artistModel) {
                        if(mRecyclerView != null) {
                            List<ArtistModel> artistModels = artistModel.getResults();
                            mAdapter = new ArtistAdapter(artistModels, R.layout.artist_row, getApplicationContext());
                            mRecyclerView.setAdapter(mAdapter);
                            hideMDialog();
                        }
                    }



                    @Override
                    public void onError(@NonNull Throwable e) {
                        hideMDialog();
                        Log.d("DEBUG_TEST", e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        _subscriptions = RxUtils.getNewCompositeSubIfUnsubscribed(_subscriptions);
    }

    @Override
    public void onPause() {
        super.onPause();
        RxUtils.unsubscribeIfNotNull(_subscriptions);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hideMDialog();
    }
}