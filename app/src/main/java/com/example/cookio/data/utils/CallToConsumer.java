package com.example.cookio.data.utils;

import androidx.annotation.NonNull;

import com.example.cookio.domain.entitites.Status;

import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallToConsumer<SOURCE, DEST> implements Callback<SOURCE> {

    public CallToConsumer(
            @NonNull Consumer<Status<DEST>> callback,
            @NonNull Mapper<SOURCE, DEST> mapper) {
        this.callback = callback;
        this.mapper = mapper;
    }

    @NonNull
    private final Consumer<Status<DEST>> callback;

    @NonNull
    private final Mapper<SOURCE, DEST> mapper;

    @Override
    public void onResponse(@NonNull Call<SOURCE> call, @NonNull Response<SOURCE> response) {
        callback.accept(new Status<>(
                response.code(), mapper.map(response.body()), null
        ));

    }

    @Override
    public void onFailure(@NonNull Call<SOURCE> call, @NonNull Throwable throwable) {
        callback.accept(
                new Status<>(
                        -1, null, throwable
                )
        );

    }

    public interface Mapper<SOURCE, DEST> {
        DEST map(SOURCE source);

    }
}
