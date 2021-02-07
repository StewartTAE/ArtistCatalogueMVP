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
import com.example.artistcatalogue.mvp.ArtistListPresenter;
import com.example.artistcatalogue.mvp.ViewPresenterContract;
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

public class MainActivity extends AppCompatActivity implements ViewPresenterContract.IView {

    private ArtistListPresenter artistListPresenter;
    private RecyclerView mRecyclerView;


   // private CompositeSubscription _subscriptions = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_main);

        artistListPresenter = new ArtistListPresenter(this);
        startRecycler();
        artistListPresenter.showArtistList();
        artistListPresenter.otherLogic();



    }

    public void startRecycler() {
        mRecyclerView = findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void passDataAdapter(List<ArtistModel> artists) {
        ArtistAdapter adapter = new ArtistAdapter(artists, R.layout.artist_row, getApplicationContext());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void setPresenter(ViewPresenterContract.IPresenter Presenter) {

    }




   /* @Override
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

    } */
}