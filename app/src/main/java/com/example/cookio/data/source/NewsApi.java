package com.example.cookio.data.source;

import com.example.cookio.data.dto.NewsDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsApi {

    @GET("news.json")
    Call<List<NewsDto>> getAllNews();
}
