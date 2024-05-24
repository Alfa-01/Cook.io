package com.example.cookio.data.network;


import com.example.cookio.data.source.NewsApi;
import com.example.cookio.data.source.ReceiptApi;
import com.example.cookio.data.source.UserApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static RetrofitFactory INSTANCE;

    private RetrofitFactory() {}

    public static synchronized RetrofitFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RetrofitFactory();
        }
        return INSTANCE;
    }

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://cookio-5e0a7-default-rtdb.europe-west1.firebasedatabase.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public ReceiptApi getReceiptApi() {
        return retrofit.create(ReceiptApi.class);
    }

    public NewsApi getNewsApi() {
        return retrofit.create(NewsApi.class);
    }

    public UserApi getUserApi() {
        return retrofit.create(UserApi.class);
    }
}
