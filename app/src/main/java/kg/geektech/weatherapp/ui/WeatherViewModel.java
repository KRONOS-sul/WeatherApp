package kg.geektech.weatherapp.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kg.geektech.weatherapp.App;
import kg.geektech.weatherapp.common.Resource;
import kg.geektech.weatherapp.data.models.MainResponse;
import kg.geektech.weatherapp.data.repositories.MainRepository;
@HiltViewModel
public class WeatherViewModel extends ViewModel {
    public MainRepository repository;
    public LiveData<Resource<MainResponse>> liveData;

    @Inject
    public WeatherViewModel(MainRepository repository) {
        this.repository = repository;
    }

    public void getWeather(String city) {
        liveData = repository.getWeatherCharacters(city);
    }
}
