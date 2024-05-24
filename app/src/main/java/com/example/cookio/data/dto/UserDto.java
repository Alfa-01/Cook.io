package com.example.cookio.data.dto;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class UserDto {

    @Nullable
    @SerializedName("id")
    public String id;

    @Nullable
    @SerializedName("image")
    public String image;

    @Nullable
    @SerializedName("name")
    public String name;

    @Nullable
    @SerializedName("lastName")
    public String lastName;

    @Nullable
    @SerializedName("nickName")
    public String nickName;

    @SerializedName("points")
    public int points;

    @Nullable
    @SerializedName("posts")
    public String[] posts;
}
