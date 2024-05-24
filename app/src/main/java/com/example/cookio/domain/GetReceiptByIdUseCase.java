package com.example.cookio.domain;

import androidx.annotation.NonNull;

import com.example.cookio.domain.entitites.FullReceiptEntity;
import com.example.cookio.domain.entitites.Status;

import java.util.function.Consumer;

public class GetReceiptByIdUseCase {

    private final ReceiptRepository repository;

    public GetReceiptByIdUseCase(ReceiptRepository repository) {
        this.repository = repository;
    }

    public void execute(@NonNull String id, @NonNull Consumer<Status<FullReceiptEntity>> callback) {
        repository.getReceiptById(id, callback);
    }
}
