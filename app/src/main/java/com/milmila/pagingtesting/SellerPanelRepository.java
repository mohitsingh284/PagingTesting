package com.milmila.pagingtesting;

import androidx.lifecycle.MutableLiveData;



import io.reactivex.Single;

public class SellerPanelRepository {

    public Single<OrderListResponse> getOrderList(int index) {
        SellerService service = RetrofitInstance.getService();


        return service.getOrderList(Constants.sources[index], String.valueOf(Constants.API_KEY));
    }

    public SellerPanelRepository() {

    }
}
