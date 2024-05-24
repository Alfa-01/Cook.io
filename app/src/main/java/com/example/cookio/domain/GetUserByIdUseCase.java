package com.example.cookio.domain;

import androidx.annotation.NonNull;

import com.example.cookio.domain.entitites.Status;
import com.example.cookio.domain.entitites.UserEntity;

import java.util.function.Consumer;

public class GetUserByIdUseCase {

    private final UserRepository repository;

    public GetUserByIdUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public void execute(@NonNull String id, @NonNull Consumer<Status<UserEntity>> callback) {
        repository.getUserById(id, callback);
    }
}
