package com.example.cookio.domain;

import androidx.annotation.NonNull;

import com.example.cookio.domain.entitites.ItemReceiptEntity;
import com.example.cookio.domain.entitites.Status;

import java.util.List;
import java.util.function.Consumer;

public class GetReceiptNamesListUseCase {

    private final ReceiptRepository repository;

    public GetReceiptNamesListUseCase(ReceiptRepository repository) {
        this.repository = repository;
    }

    public void execute(@NonNull Consumer<Status<List<ItemReceiptEntity>>> callback) {
        repository.getAllReceiptNames(callback);
    }
}
