package com.example.essam.mapweathertest.getapi;


import com.example.essam.mapweathertest.model.GetAll;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by EsSaM on 10/12/2017.
 */

public class ConnectManager {

    static final String API_KEY = "5f6c99c7cfb6c9388fb5c7c85f53208d";
    private static ConnectManager connectManager;
    String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private Client weatherClient;

    private ConnectManager() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        weatherClient = retrofit.create(Client.class);

    }

    public static ConnectManager getInstance() {
        if (connectManager == null)
            connectManager = new ConnectManager();

        return connectManager;
    }

    public Call<GetAll> getCityWeather(String cityName) {
        return weatherClient.weatherForCities(cityName,API_KEY);
    }


}
