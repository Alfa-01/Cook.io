package com.example.cookio.domain;

import androidx.annotation.NonNull;

import com.example.cookio.domain.entitites.Status;
import com.example.cookio.domain.entitites.UserEntity;

import java.util.List;
import java.util.function.Consumer;


public interface UserRepository {

    void getUserById(@NonNull String id, @NonNull Consumer<Status<UserEntity>> callback);
}
