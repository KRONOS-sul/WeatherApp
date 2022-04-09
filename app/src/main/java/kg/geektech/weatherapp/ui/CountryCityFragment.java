package kg.geektech.weatherapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kg.geektech.weatherapp.R;
import kg.geektech.weatherapp.base.BaseFragment;
import kg.geektech.weatherapp.databinding.FragmentCountryCityBinding;

public class CountryCityFragment extends BaseFragment<FragmentCountryCityBinding> {


    @Override
    protected FragmentCountryCityBinding bind() {
        return FragmentCountryCityBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    protected void setupViews() {

    }

    @Override
    protected void setupListeners() {
        binding.searchBtn.setOnClickListener(view -> {
            String city = binding.searchEdTxt.getText().toString();
            controller.navigate(CountryCityFragmentDirections.
                    actionCountryCityFragmentToWeatherFragment().setCity(city));
        });

    }

    @Override
    protected void setupObservers() {

    }

    @Override
    protected void callRequests() {

    }
}