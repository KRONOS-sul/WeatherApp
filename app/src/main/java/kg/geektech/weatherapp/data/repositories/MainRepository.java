package kg.geektech.weatherapp.data.repositories;

import androidx.lifecycle.MutableLiveData;

import kg.geektech.weatherapp.common.Resource;
import kg.geektech.weatherapp.data.models.MainResponse;
import kg.geektech.weatherapp.data.remote.WeatherApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private WeatherApi api;
    private String city;

    public MainRepository(WeatherApi api) {
        this.api = api;
    }

    public MainRepository(String city) {
        this.city = city;
    }

    public MutableLiveData<Resource<MainResponse>> getWeatherCharacters(){
        MutableLiveData<Resource<MainResponse>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getWeatherApi("Bishkek","944d35c23dc458eb05e201aeebaaf7bc","metric")
                .enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                if (response.isSuccessful() && response.body() !=null){
                    liveData.setValue(Resource.success(response.body()));
                }else {
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

}
