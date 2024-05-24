package com.example.cookio.data.dto;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class ReceiptDto {

    @Nullable
    @SerializedName("id")
    public String id;

    @Nullable
    @SerializedName("name")
    public String name;

    @Nullable
    @SerializedName("category")
    public String category;

    @Nullable
    @SerializedName("country")
    public String country;

    @Nullable
    @SerializedName("instructions")
    public String instructions;

    @Nullable
    @SerializedName("image")
    public String thumbUrl;

    @Nullable
    @SerializedName("tags")
    public String[] tags;

    @Nullable
    @SerializedName("ingredients")
    public String[] ingredients;

    @Nullable
    @SerializedName("measures")
    public String[] measures;
}
