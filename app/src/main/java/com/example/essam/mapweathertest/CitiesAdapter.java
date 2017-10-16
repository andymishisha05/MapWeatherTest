package com.example.essam.mapweathertest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.essam.mapweathertest.model.OneCity;
import com.example.essam.mapweathertest.ui.Pop_screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by EsSaM on 10/14/2017.
 */


public  class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ViewHolder> implements Filterable {

    Context mContext;
    List<OneCity> mCitiesWeather;
    private List<OneCity> cityList = null;
    private List<OneCity> filteredCityList = null;
    private CityFilter cityFilter;


    public CitiesAdapter(Context context, List<OneCity> citiesWeather) {
        mContext = context;
        mCitiesWeather = citiesWeather;
        cityList = citiesWeather;
        this.filteredCityList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.city_row, parent, false));

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        OneCity currentCity = mCitiesWeather.get(position);
        holder.cityName.setText(currentCity.getName());
        holder.temp.setText(String.format(Locale.ENGLISH, "%.0f", currentCity.getMain().getTemp() - 273.15));
        holder.hum.setText(String.valueOf(currentCity.getMain().getHumidity()));
        holder.windSpeed.setText(String.valueOf(currentCity.getWind().getSpeed()));
        holder.windDeg.setText(String.valueOf(currentCity.getWind().getDeg()));
    }

    @Override
    public int getItemCount() {
        return mCitiesWeather.size();
    }

    @Override
    public Filter getFilter() {
        if (cityFilter == null)
            cityFilter = new CityFilter(this, cityList);
        return cityFilter;
    }

    private static class CityFilter extends Filter {

        private final CitiesAdapter adapter;

        private final List<OneCity> originalList;

        private final List<OneCity> filteredList;

        private CityFilter(CitiesAdapter adapter, List<OneCity> originalList) {
            super();
            this.adapter = adapter;
            this.originalList = new ArrayList<>(originalList);
            this.filteredList = new ArrayList<>();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filteredList.clear();
            final FilterResults results = new FilterResults();

            if (constraint.length() == 0) {
                filteredList.addAll(originalList);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (final OneCity list : originalList) {
                    if (list.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(list);
                    }
                }
            }
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            adapter.filteredCityList.clear();
            adapter.filteredCityList.addAll((ArrayList<OneCity>) results.values);
            adapter.mCitiesWeather.clear();
            adapter.mCitiesWeather.addAll(filteredList);
            adapter.notifyDataSetChanged();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.city)
        TextView cityName;
        @BindView(R.id.temp)
        TextView temp;
        @BindView(R.id.hum)
        TextView hum;
        @BindView(R.id.windSpeed)
        TextView windSpeed;
        @BindView(R.id.windDeg)
        TextView windDeg;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pop_screen popup = new Pop_screen();
                    popup.displayPopupWindow(v, getAdapterPosition(), mContext, mCitiesWeather);

                }
            });
        }
    }


}
