package com.example.cookio.domain;

import androidx.annotation.NonNull;

import com.example.cookio.domain.entitites.NewsEntity;
import com.example.cookio.domain.entitites.Status;

import java.util.List;
import java.util.function.Consumer;

public interface NewsRepository {

    void getAllNews(@NonNull Consumer<Status<List<NewsEntity>>> callback);
}
