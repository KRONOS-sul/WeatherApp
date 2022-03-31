package kg.geektech.weatherapp;

import android.app.Application;

import kg.geektech.weatherapp.data.remote.RetroFitClient;
import kg.geektech.weatherapp.data.remote.WeatherApi;
import kg.geektech.weatherapp.data.repositories.MainRepository;

public class App extends Application {
    private RetroFitClient retroFitClient;
    private WeatherApi api;
    public static MainRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        retroFitClient = new RetroFitClient();
        api = retroFitClient.provideApi();
        repository = new MainRepository(api);
    }
}
