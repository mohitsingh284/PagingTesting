package com.milmila.pagingtesting;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class OrderListEntity {

    private String newsImg, newsTitle;

    public String getNewsImg() {
        return newsImg;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public OrderListEntity(String newsTitle, String newsImg) {
        this.newsImg = newsImg;
        this.newsTitle = newsTitle;
    }

    public static DiffUtil.ItemCallback<OrderListEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<OrderListEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull OrderListEntity oldItem, @NonNull OrderListEntity newItem) {
            return oldItem.newsTitle.equals(newItem.newsTitle);
        }

        @Override
        public boolean areContentsTheSame(@NonNull OrderListEntity oldItem, @NonNull OrderListEntity newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        OrderListEntity article = (OrderListEntity) obj;
        return article.newsTitle.equals(this.newsTitle);
    }

}
