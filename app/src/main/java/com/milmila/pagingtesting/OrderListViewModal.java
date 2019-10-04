package com.milmila.pagingtesting;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.disposables.CompositeDisposable;

public class OrderListViewModal extends AndroidViewModel {
    LiveData<OrderListDataSource> movieDataSourceLiveData;
    private Executor executor;
    private LiveData<PagedList<OrderListEntity>> moviesPagedList;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private MutableLiveData<PagedList<OrderListEntity>> pagedListMutableLiveData = null;
    private OrderListDataFactory factory;


    public OrderListViewModal(Application application) {
        super(application);

        SellerService sellerService=RetrofitInstance.getService();
        factory = new OrderListDataFactory(sellerService, application);
        movieDataSourceLiveData = factory.getMutableLiveData();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();

        executor = Executors.newFixedThreadPool(5);

        moviesPagedList = new LivePagedListBuilder<Integer, OrderListEntity>(factory, config)
                .setFetchExecutor(executor)
                .build();

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    public MutableLiveData<PagedList<OrderListEntity>> observeOrderList() {
        if (pagedListMutableLiveData == null) {
            pagedListMutableLiveData = new MutableLiveData<>();
        }
        return pagedListMutableLiveData;

    }

    public LiveData<PagedList<OrderListEntity>> getMoviesPagedList() {
        return moviesPagedList;
    }

    public void getOrderList() {

    }
}
