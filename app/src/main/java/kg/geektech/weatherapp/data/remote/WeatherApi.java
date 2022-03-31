package kg.geektech.weatherapp.data.remote;

import kg.geektech.weatherapp.data.models.MainResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("weather?")
    Call<MainResponse> getWeatherApi(@Query("q") String city,
                                     @Query("appid") String appId,
                                     @Query("units") String unts);
}
