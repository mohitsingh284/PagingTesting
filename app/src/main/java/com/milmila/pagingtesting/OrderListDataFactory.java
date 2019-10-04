package com.milmila.pagingtesting;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import io.reactivex.disposables.CompositeDisposable;

public class OrderListDataFactory extends DataSource.Factory<Integer, OrderListEntity> {

    private OrderListDataSource movieDataSource;
    private SellerService movieDataService;
    private Application application;
    private MutableLiveData<OrderListDataSource> mutableLiveData;
    private SellerPanelRepository sellerPanelRepository;
    private CompositeDisposable compositeDisposable;

    public OrderListDataFactory(SellerService movieDataService, Application application) {
        this.movieDataService = movieDataService;
        this.application = application;
        mutableLiveData=new MutableLiveData<>();
    }

    @Override
    public DataSource<Integer, OrderListEntity> create() {

        movieDataSource=new OrderListDataSource(movieDataService,application);
        mutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<OrderListDataSource> getMutableLiveData() {
        return mutableLiveData;
    }


}
