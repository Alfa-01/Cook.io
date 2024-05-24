package com.example.cookio.domain;

import androidx.annotation.NonNull;

import com.example.cookio.domain.entitites.NewsEntity;
import com.example.cookio.domain.entitites.Status;

import java.util.List;
import java.util.function.Consumer;


public class GetNewsListUseCase {

    private final NewsRepository repository;

    public GetNewsListUseCase(NewsRepository repository) {
        this.repository = repository;
    }

    public void execute(@NonNull Consumer<Status<List<NewsEntity>>> callback) {
        repository.getAllNews(callback);
    }

}
