package com.milmila.pagingtesting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;


import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePageOrderListAdapter extends PagedListAdapter<OrderListEntity,HomePageOrderListAdapter.ViewHolder> {

    private Context context;

    public HomePageOrderListAdapter(Context context) {
        super(OrderListEntity.DIFF_CALLBACK);
        this.context = context;
    }

   /* @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return 0;

        return -1;
    }*/

    @NonNull
    @Override
    public HomePageOrderListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View mainView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_page_order_list_item, parent, false);

        return new ViewHolder(mainView);
    }

    @Override
    public void onBindViewHolder(@NonNull HomePageOrderListAdapter.ViewHolder holder, int position) {

        switch (holder.getItemViewType()){
            case 0:
                HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
                configureHeaderView(headerHolder, position);
                break;
            case -1:
                ViewHolder mainViewHolder = (ViewHolder) holder;
                configureMainView(mainViewHolder, position);
                break;
        }

    }

    public void configureHeaderView(HeaderViewHolder headerViewHolder, int position){

    }

    public void configureMainView(ViewHolder viewHolder, int position){

        OrderListEntity orderListEntity=getItem(position);
        viewHolder.orderDate_tv.setText(orderListEntity.getNewsTitle());
    }

   /* @Override
    public int getItemCount() {
        return 10;
    }*/

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.parent)
        ConstraintLayout parent;

        @BindView(R.id.orderDate_tv)
        TextView orderDate_tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class HeaderViewHolder extends HomePageOrderListAdapter.ViewHolder {
        @BindView(R.id.parent)
        ConstraintLayout parent;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
