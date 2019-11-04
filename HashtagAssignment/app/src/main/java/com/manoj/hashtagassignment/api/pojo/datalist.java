package com.manoj.hashtagassignment.api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class datalist {
    @SerializedName("data")
    @Expose
    private List<datadetails> data = null;

    public List<datadetails> getData() {
        return data;
    }

    public void setData(List<datadetails> data) {
        this.data = data;
    }

}
