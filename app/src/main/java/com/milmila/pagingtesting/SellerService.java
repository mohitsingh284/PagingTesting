package com.milmila.pagingtesting;



import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SellerService {
    @POST("articles")
    Single<OrderListResponse> getOrderList(
            @Query("source") String source,
            @Query("apiKey") String apiKey);
}
