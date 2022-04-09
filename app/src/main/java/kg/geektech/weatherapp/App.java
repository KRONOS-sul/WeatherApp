package kg.geektech.weatherapp;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;
import kg.geektech.weatherapp.data.remote.RetroFitClient;
import kg.geektech.weatherapp.data.remote.WeatherApi;
import kg.geektech.weatherapp.data.repositories.MainRepository;

@HiltAndroidApp
public class App extends Application {}
