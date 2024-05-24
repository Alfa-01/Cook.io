package com.example.cookio.domain.entitites;

import androidx.annotation.Nullable;

public class NewsEntity {

    @Nullable
    private final String id;
    @Nullable
    private final String image;
    @Nullable
    private final String authorId;
    @Nullable
    private final String description;
    private final int likes;

    @Nullable
    public String getId() {
        return id;
    }

    @Nullable
    public String getImage() {
        return image;
    }

    @Nullable
    public String getAuthorId() {
        return authorId;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public int getLikes() {
        return likes;
    }

    public NewsEntity(@Nullable String id,
                      @Nullable String image,
                      @Nullable String authorId,
                      @Nullable String description,
                      int likes) {
        this.id = id;
        this.image = image;
        this.authorId = authorId;
        this.description = description;
        this.likes = likes;
    }
}
