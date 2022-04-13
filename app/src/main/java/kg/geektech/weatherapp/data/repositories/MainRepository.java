package kg.geektech.weatherapp.data.repositories;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import kg.geektech.weatherapp.common.Resource;
import kg.geektech.weatherapp.data.models.MainResponse;
import kg.geektech.weatherapp.data.remote.WeatherApi;
import kg.geektech.weatherapp.room.WeatherDao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private WeatherApi api;
    private WeatherDao dao;

    @Inject
    public MainRepository(WeatherApi api, WeatherDao dao) {
        this.api = api;
        this.dao = dao;
    }



    public MutableLiveData<Resource<MainResponse>> getWeatherCharacters(String city) {
        MutableLiveData<Resource<MainResponse>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getWeatherApi(city, "944d35c23dc458eb05e201aeebaaf7bc", "metric")
                .enqueue(new Callback<MainResponse>() {
                    @Override
                    public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            liveData.setValue(Resource.success(response.body()));
                        } else {
                            liveData.setValue(Resource.error(response.message(), null));
                        }
                    }

                    @Override
                    public void onFailure(Call<MainResponse> call, Throwable t) {
                        liveData.setValue(Resource.error(t.getLocalizedMessage(), null));
                    }
                });

        return liveData;
    }
    public List<MainResponse> getWeatherFromDb(){
        return dao.getAllWeather();
    }

}
