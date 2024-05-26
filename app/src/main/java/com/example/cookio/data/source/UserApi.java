package com.example.cookio.data.source;

import com.example.cookio.data.dto.UserDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserApi {
    @GET("users/{id}.json")
    Call<UserDto> getUserById(@Path("id") String id);
}
