package com.example.cookio.data.dto;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class NewsDto {

    @SerializedName("likes")
    public int likes;

    @Nullable
    @SerializedName("id")
    public String id;

    @Nullable
    @SerializedName("authorId")
    public String authorId;

    @Nullable
    @SerializedName("description")
    public String description;

    @Nullable
    @SerializedName("image")
    public String image;

    @Nullable
    @SerializedName("authorImagePreview")
    public String authorImagePreview;

    @Nullable
    @SerializedName("authorNickname")
    public String authorNickname;
}
