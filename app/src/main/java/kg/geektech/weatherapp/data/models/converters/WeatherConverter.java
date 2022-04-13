package kg.geektech.weatherapp.data.models.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import kg.geektech.weatherapp.data.models.Sys;
import kg.geektech.weatherapp.data.models.Weather;

public class WeatherConverter {

    @TypeConverter
    public String fromMainString(List<Weather> weather__1){
        if (weather__1 == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Weather>>() {}.getType();
        return gson.toJson(weather__1,type);
    }
    @TypeConverter
    public List<Weather> fromMainString(String weatherString){
        if (weatherString == null){
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Weather>>() {}.getType();
        return gson.fromJson(weatherString,type);
    }
}
