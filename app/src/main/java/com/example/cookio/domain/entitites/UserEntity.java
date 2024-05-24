package com.example.cookio.domain.entitites;

import androidx.annotation.Nullable;

public class UserEntity {

    @Nullable
    private final String id;
    @Nullable
    private final String name;
    @Nullable
    private final String lastName;
    @Nullable
    private final String nickName;
    @Nullable
    private final String image;
    @Nullable
    private final String[] posts;
    private final int points;

    public int getPoints() {
        return points;
    }

    @Nullable
    public String getId() {
        return id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @Nullable
    public String getLastName() {
        return lastName;
    }

    @Nullable
    public String getNickName() {
        return nickName;
    }

    @Nullable
    public String getImage() {
        return image;
    }

    @Nullable
    public String[] getPosts() {
        return posts;
    }

    public UserEntity(@Nullable String id,
                      @Nullable String name,
                      @Nullable String lastName,
                      @Nullable String nickName,
                      @Nullable String image,
                      @Nullable String[] posts,
                      int points) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.nickName = nickName;
        this.image = image;
        this.posts = posts;
        this.points = points;
    }
}
