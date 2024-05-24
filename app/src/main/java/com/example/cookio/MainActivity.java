package com.example.cookio;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.cookio.data.dto.ReceiptDto;
import com.example.cookio.data.network.RetrofitFactory;
import com.example.cookio.databinding.MainActivityBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private MainActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView bottomNavigationView = binding.bottomNavigationView;

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager(
            ).findFragmentById(R.id.navFragment);
        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

//        RetrofitFactory retrofit = RetrofitFactory.getInstance();
//
//        Call<List<ReceiptDto>> response= retrofit.getReceiptApi().getAll();
//
//        response.enqueue(new Callback<List<ReceiptDto>>() {
//            @Override
//            public void onResponse(Call<List<ReceiptDto>> call, Response<List<ReceiptDto>> response) {
//                Log.d("firebase", response.body().get(0).name);
//            }
//
//            @Override
//            public void onFailure(Call<List<ReceiptDto>> call, Throwable throwable) {
//                Log.d("firebase", throwable.getLocalizedMessage());
//            }
//        });

    }
}