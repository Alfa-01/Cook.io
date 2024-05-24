package com.example.cookio.domain.entitites;

import androidx.annotation.Nullable;

public class ItemReceiptEntity {
    @Nullable
    private final String id;

    @Nullable
    private final String name;

    public ItemReceiptEntity(@Nullable String id, @Nullable String name) {
        this.id = id;
        this.name = name;
    }

    @Nullable
    public String getId() {
        return id;
    }

    @Nullable
    public String getName() {
        return name;
    }
}
