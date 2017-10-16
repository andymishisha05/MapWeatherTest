package com.example.essam.mapweathertest.getapi;



import com.example.essam.mapweathertest.model.GetAll;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by EsSaM on 10/12/2017.
 */

public interface Client {

    @GET("group?")
    Call<GetAll> weatherForCities(@Query("id") String cityName, @Query("AppID") String Key);
}
