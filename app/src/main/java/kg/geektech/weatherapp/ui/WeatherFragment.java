
package kg.geektech.weatherapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.weatherapp.R;
import kg.geektech.weatherapp.base.BaseFragment;
import kg.geektech.weatherapp.data.models.MainResponse;
import kg.geektech.weatherapp.databinding.FragmentWeatherBinding;

@AndroidEntryPoint
public class WeatherFragment extends BaseFragment<FragmentWeatherBinding> {
    private WeatherViewModel viewModel;
    private WeatherFragmentArgs args;

    @Override
    protected FragmentWeatherBinding bind() {
        return FragmentWeatherBinding.inflate(getLayoutInflater());
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = WeatherFragmentArgs.fromBundle(getArguments());
    }

    @Override
    protected void setupViews() {
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);

    }

    @Override
    protected void setupListeners() {
        controller = Navigation.findNavController(requireActivity(), R.id.nav_host);
        binding.countryCity.setOnClickListener(view -> {
            controller.navigate(R.id.action_weatherFragment_to_countryCityFragment);
        });

    }

    @Override
    protected void setupObservers() {
        viewModel.liveData.observe(getViewLifecycleOwner(), mainResponseResource -> {
            switch (mainResponseResource.status) {
                case SUCCESS: {
                    setData(mainResponseResource.data);
                    binding.progress.setVisibility(View.GONE);
                    break;
                }
                case ERROR: {
                    binding.progress.setVisibility(View.GONE);
                    break;
                }
                case LOADING: {
                    binding.progress.setVisibility(View.VISIBLE);

                    break;
                }
            }
        });

    }

    @Override
    protected void callRequests() {
        if (args.getCity() != null) {
            viewModel.getWeather(args.getCity());
        }

    }

    private void setData(MainResponse response) {


        String urlImg = "https://openweathermap.org/img/wn/" + response.getWeather().get(0).getIcon() + ".png";
        String maxTemp = Math.round(response.getMain().getTempMax()) + "°C";
        String wind = (int) Math.round(response.getWind().getSpeed()) + " km/h";
        String minTemp = (int) Math.round(response.getMain().getTempMin()) + "°C";
        String humidity = response.getMain().getHumidity() + "%";
        String barometer = response.getMain().getPressure() + "mBar";
        String mainWeather = response.getWeather().get(0).getMain();
        String tempNow = String.valueOf((int) Math.round(response.getMain().getTemp()));

        Glide.with(requireActivity()).load(urlImg).into(binding.weatherImg);
        binding.expectedTemp.setText(maxTemp + " max");
        binding.expectedTemp2.setText(minTemp + " min");
        binding.windPower.setText(wind);
        binding.humidityNumber.setText(humidity);
        binding.temp.setText(tempNow);
        binding.countryCity.setText(response.getName());
        binding.pressurePower.setText(barometer);
        binding.weather.setText(mainWeather);
        binding.sunsetTime.setText(getDate(response.getSys().getSunset(), "hh:mm") + " AM");
        binding.sunriseTime.setText(getDate(response.getSys().getSunrise(), "hh:mm") + " AM");
        binding.dayTimeTime.setText(getDate(response.getDt(), "hh:mm"));

        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("E, dd yyyy  | a h ", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        binding.date.setText(dateText);
    }


    public static String getDate(Integer milliSeconds, String dateFormat) {

        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public void country(View view) {

    }

    @Override
    public void onResume() {
        super.onResume();
        Calendar uh = Calendar.getInstance();
        int timeOfDay = uh.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 7 && timeOfDay < 20) {
            binding.lightOutside.setImageResource(R.drawable.day_light);
        } else {
            binding.lightOutside.setImageResource(R.drawable.night);
        }
    }
}