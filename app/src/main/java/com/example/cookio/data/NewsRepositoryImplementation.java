package com.example.cookio.data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.cookio.data.dto.NewsDto;
import com.example.cookio.data.network.RetrofitFactory;
import com.example.cookio.data.source.NewsApi;
import com.example.cookio.data.utils.CallToConsumer;
import com.example.cookio.domain.NewsRepository;
import com.example.cookio.domain.entitites.NewsEntity;
import com.example.cookio.domain.entitites.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class NewsRepositoryImplementation implements NewsRepository {

    private static NewsRepositoryImplementation INSTANCE;
    private final NewsApi newsApi = RetrofitFactory.getInstance().getNewsApi();

    private NewsRepositoryImplementation() {}

    public static synchronized NewsRepositoryImplementation getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NewsRepositoryImplementation();
        }
        return INSTANCE;
    }


    @Override
    public void getAllNews(@NonNull Consumer<Status<List<NewsEntity>>> callback) {
        newsApi.getAllNews().enqueue(new CallToConsumer<>(
                callback,
                newsDtos -> {
                    ArrayList<NewsEntity> result = new ArrayList<>(newsDtos.size());
                    for (NewsDto newsDto: newsDtos) {
                        final String id = newsDto.id;
                        if (id != null) {
                            Log.d("info", newsDto.authorNickname + " ");
                            result.add(
                                    new NewsEntity(
                                            id,
                                            newsDto.image,
                                            newsDto.authorId,
                                            newsDto.description,
                                            newsDto.authorImagePreview,
                                            newsDto.authorNickname,
                                            newsDto.likes));
                        }
                    }
                    return result;
                }
        ));
    }
}
