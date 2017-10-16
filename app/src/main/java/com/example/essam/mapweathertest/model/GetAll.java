
package com.example.essam.mapweathertest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetAll implements Serializable {

    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private List<OneCity> list = null;

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public List<OneCity> getList() {
        return list;
    }

    public void setList(List<OneCity> list) {
        this.list = list;
    }

}
