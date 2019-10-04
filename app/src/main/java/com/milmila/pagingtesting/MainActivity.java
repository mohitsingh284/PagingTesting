package com.milmila.pagingtesting;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.orderDetails_rv)
    RecyclerView orderDetails_rv;

    private HomePageOrderListAdapter homePageOrderListAdapter;
    private LinearLayoutManager linearLayoutManager;
    private OrderListViewModal orderListViewModal;

    private PagedList<OrderListEntity> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initUI();
    }

    public void initUI(){
        SellerService service= RetrofitInstance.getService();
        orderListViewModal= ViewModelProviders.of(this).get(OrderListViewModal.class);
        /*orderListViewModal.observeOrderList().observe(this, new Observer<PagedList<OrderListEntity>>() {
            @Override
            public void onChanged(PagedList<OrderListEntity> orderListEntities) {
                movies=orderListEntities;
                showOnRecyclerView();
            }
        });*/
        orderListViewModal.getMoviesPagedList().observe(this, new Observer<PagedList<OrderListEntity>>() {
            @Override
            public void onChanged(@Nullable PagedList<OrderListEntity> moviesFromLiveData) {
                movies=moviesFromLiveData;
                showOnRecyclerView();
            }
        });
    }


    public void showOnRecyclerView(){
        homePageOrderListAdapter=new HomePageOrderListAdapter(this);
        homePageOrderListAdapter.submitList(movies);
        linearLayoutManager=new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        orderDetails_rv.setHasFixedSize(true);
        orderDetails_rv.setLayoutManager(linearLayoutManager);
        orderDetails_rv.setAdapter(homePageOrderListAdapter);
        homePageOrderListAdapter.notifyDataSetChanged();



    }
}
