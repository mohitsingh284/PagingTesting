package com.milmila.pagingtesting;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class OrderListDataSource extends PageKeyedDataSource<Integer, OrderListEntity> {

    private SellerPanelRepository sellerPanelRepository;
    private int sourceIndex;

    private SellerService sellerService;
    private Application application;
    private CompositeDisposable disposable;

    private MutableLiveData<List<OrderListEntity>> orderListResponseMutableLiveData=new MutableLiveData<>();

    public OrderListDataSource(SellerService sellerService, Application application) {
        this.sellerService = sellerService;
        this.application = application;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, OrderListEntity> callback) {

        CompositeDisposable disposable=new CompositeDisposable();
        disposable.add(sellerService.getOrderList(Constants.sources[0], String.valueOf(Constants.API_KEY)).
                subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<OrderListResponse>() {
                    @Override
                    public void onSuccess(OrderListResponse value) {
                        OrderListResponse movieDBResponse=value;
                        ArrayList<OrderListEntity> movies = new ArrayList<>();

                        if(movieDBResponse != null && movieDBResponse.getOrderListEntity() != null) {
                            movies = (ArrayList<OrderListEntity>) movieDBResponse.getOrderListEntity();

                            callback.onResult(movies, null, (Integer) 2);
                        }
                       // orderListResponseMutableLiveData.postValue(movies);
                    }

                    @Override
                    public void onError(Throwable e) {
                        //orderListResponseMutableLiveData.postValue(null);
                        Log.e("Res",e.toString());

                    }
                }));
        /*Call<OrderListResponse> call = service.getOrderList(payLoad);

        call.enqueue(new Callback<OrderListResponse>() {
            @Override
            public void onResponse(Call<OrderListResponse> call, Response<OrderListResponse> response) {

                OrderListResponse movieDBResponse=response.body();
                ArrayList<OrderListEntity> movies = new ArrayList<>();

                if(movieDBResponse != null && movieDBResponse.getOrderListEntity() != null) {
                    movies = (ArrayList<OrderListEntity>) movieDBResponse.getOrderListEntity();

                    callback.onResult(movies, null, (Integer) 2);
                }
            }

            @Override
            public void onFailure(Call<OrderListResponse> call, Throwable t) {

            }
        });
*/

    }

    @Override
    public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, OrderListEntity> callback) {

        disposable.add(sellerService.getOrderList(Constants.sources[params.key], String.valueOf(Constants.API_KEY)).
                subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<OrderListResponse>() {
                    @Override
                    public void onSuccess(OrderListResponse value) {
                        OrderListResponse movieDBResponse=value;
                        ArrayList<OrderListEntity> movies = new ArrayList<>();

                        if(movieDBResponse != null && movieDBResponse.getOrderListEntity() != null) {
                            movies = (ArrayList<OrderListEntity>) movieDBResponse.getOrderListEntity();

                            callback.onResult(movies, params.key + 1);
                        }
                        orderListResponseMutableLiveData.postValue(movies);
                    }

                    @Override
                    public void onError(Throwable e) {
                        //orderListResponseMutableLiveData.postValue(null);

                    }
                }));
        /*Call<OrderListResponse> call = service.getOrderList(payLoad);

        call.enqueue(new Callback<OrderListResponse>() {
            @Override
            public void onResponse(Call<OrderListResponse> call, Response<OrderListResponse> response) {

                OrderListResponse movieDBResponse=response.body();
                ArrayList<OrderListEntity> movies = new ArrayList<>();

                if(movieDBResponse != null && movieDBResponse.getOrderListEntity() != null) {
                    movies = (ArrayList<OrderListEntity>) movieDBResponse.getOrderListEntity();

                    callback.onResult(movies, params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<OrderListResponse> call, Throwable t) {

            }
        });*/
    }


}
