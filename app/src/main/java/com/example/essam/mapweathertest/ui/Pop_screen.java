package com.example.essam.mapweathertest.ui;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.essam.mapweathertest.R;
import com.example.essam.mapweathertest.model.OneCity;

import java.util.List;
import java.util.Locale;

/**
 * Created by EsSaM on 10/14/2017.
 */


public class Pop_screen {

    public void displayPopupWindow(View anchorView, int position, Context mContext, List<OneCity> mCitiesWeather) {
        final PopupWindow popup = new PopupWindow(mContext);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View layout = inflater.inflate(R.layout.custom_info_window, null);
        popup.setContentView(layout);
        OneCity currentCity = mCitiesWeather.get(position);
        TextView cityTitle, cityTemp, weatherDescription, maxTemp, minTemp, cityHum, Pressure, windSpeed, windDeg;
        ImageView closePopUp = (ImageView) layout.findViewById(R.id.close_popup);
        cityTitle = (TextView) layout.findViewById(R.id.cityTitle);
        cityTemp = (TextView) layout.findViewById(R.id.cityTemp);
        weatherDescription = (TextView) layout.findViewById(R.id.description);
        maxTemp = (TextView) layout.findViewById(R.id.maxTemp);
        minTemp = (TextView) layout.findViewById(R.id.minTemp);
        cityHum = (TextView) layout.findViewById(R.id.cityHum);
        Pressure = (TextView) layout.findViewById(R.id.pressure);
        windSpeed = (TextView) layout.findViewById(R.id.wind_speed);
        windDeg = (TextView) layout.findViewById(R.id.wind_deg);

        cityTitle.setText(currentCity.getName());
        cityTemp.setText(String.format(Locale.ENGLISH, "%.0f", currentCity.getMain().getTemp() - 273.15));
        cityHum.setText(String.valueOf(currentCity.getMain().getHumidity()));
        windSpeed.setText(String.valueOf(currentCity.getWind().getSpeed()));
        windDeg.setText(String.valueOf(currentCity.getWind().getDeg()));
        weatherDescription.setText(currentCity.getWeather().get(0).getDescription());
        maxTemp.setText(String.format(Locale.ENGLISH, "%.0f", currentCity.getMain().getTempMax() - 273.15));
        minTemp.setText(String.format(Locale.ENGLISH, "%.0f", currentCity.getMain().getTempMin() - 273.15));
        Pressure.setText((String.valueOf(currentCity.getMain().getPressure())));

        closePopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });

        // Set content width and height
        popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        // Closes the popup window when touch outside of it - when looses focus
        // Show anchored to button
//
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        // Show anchored to button
        popup.setBackgroundDrawable(new ColorDrawable(1));
        popup.showAsDropDown(anchorView, 0, -anchorView.getHeight());
    }

}
