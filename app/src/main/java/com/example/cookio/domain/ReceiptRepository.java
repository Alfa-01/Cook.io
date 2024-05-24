package com.example.cookio.domain;

import androidx.annotation.NonNull;

import com.example.cookio.domain.entitites.FullReceiptEntity;
import com.example.cookio.domain.entitites.ItemReceiptEntity;
import com.example.cookio.domain.entitites.Status;

import java.util.List;
import java.util.function.Consumer;

public interface ReceiptRepository {

    void getAllReceiptNames(@NonNull Consumer<Status<List<ItemReceiptEntity>>> callback);
    void getReceiptById(@NonNull String id, @NonNull Consumer<Status<FullReceiptEntity>> callback);
}
