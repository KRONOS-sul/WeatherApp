package kg.geektech.weatherapp.data.models.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import kg.geektech.weatherapp.data.models.Clouds;

public class CloudConverter {
    @TypeConverter
    public String fromMainString(Clouds clouds) {
        if (clouds == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Clouds>() {
        }.getType();
        return gson.toJson(clouds, type);
    }

    @TypeConverter
    public Clouds fromMainString(String cloudString) {
        if (cloudString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Clouds>() {
        }.getType();
        return gson.fromJson(cloudString, type);
    }
}
