package com.manoj.hashtagassignment.api;


import com.manoj.hashtagassignment.api.pojo.datalist;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface api {
    String baseurl = "https://api.myjson.com/bins/";

    @GET("rov51")
    Call<datalist> getdata(
    );
}
