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

    @SerializedName("points")
    public int points;

    @Nullable
    @SerializedName("posts")
    public String[] posts;

    public UserDto(
                   @Nullable String id,
                   @Nullable String image,
                   @Nullable String name,
                   @Nullable String lastName,
                   int points,
                   @Nullable String[] posts) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.lastName = lastName;
        this.points = points;
        this.posts = posts;
    }
}
