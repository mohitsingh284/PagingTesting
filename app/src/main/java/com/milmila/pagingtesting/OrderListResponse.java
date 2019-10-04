package com.milmila.pagingtesting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderListResponse {

    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("sCode")
    @Expose
    private Integer sCode;

    @SerializedName("error")
    @Expose
    private Object error;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getsCode() {
        return sCode;
    }

    public void setsCode(Integer sCode) {
        this.sCode = sCode;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @SerializedName("data")
    @Expose
    private List<OrderListEntity> orderListEntity;

    public List<OrderListEntity> getOrderListEntity() {
        return orderListEntity;
    }

    public void setOrderListEntity(List<OrderListEntity> orderListEntity) {
        this.orderListEntity = orderListEntity;
    }
}
