package com.example.essam.mapweathertest.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.essam.mapweathertest.R;
import com.example.essam.mapweathertest.getapi.ConnectManager;
import com.example.essam.mapweathertest.model.GetAll;
import com.example.essam.mapweathertest.model.OneCity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {

    Context context = SplashScreen.this;
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    public static List<OneCity> Cities = new ArrayList<>();
    String idsOfCites = "360630,360995,361058,359792,358619,352733,359280,359783,359796," +
            "360502,361055,361055,347634,361320,358048,358448,361546,348112,354502";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        fetchData(idsOfCites);

    }

    private void fetchData(String id) {
        ConnectManager.getInstance().getCityWeather(id).enqueue(new Callback<GetAll>() {
            @Override
            public void onResponse(Call<GetAll> call, Response<GetAll> response) {
                Log.i("Response",response.toString());
                GetAll getAll = response.body();
                List<OneCity> list = getAll.getList();
                Cities = list;
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, SPLASH_DISPLAY_LENGTH);


            }

            @Override
            public void onFailure(Call<GetAll> call, Throwable t) {
                Log.i("Error",t.toString());
                Toast.makeText(context,"check ur network",Toast.LENGTH_LONG).show();
            }
        });
    }

}
