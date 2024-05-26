package com.example.cookio.data.utils;

import androidx.annotation.Nullable;

import java.util.List;


public class State<T> {
    @Nullable
    private final String errorMessage;

    @Nullable
    private final List<T> item;

    private final boolean isLoading;

    @Nullable
    public String getErrorMessage() {
        return errorMessage;
    }

    @Nullable
    public List<T> getItems() {
        return item;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public State(
            @Nullable String errorMessage,
            @Nullable List<T> item,
            boolean isLoading) {
        this.errorMessage = errorMessage;
        this.item = item;
        this.isLoading = isLoading;
    }
}

