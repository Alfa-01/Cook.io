package com.example.cookio.data;

import androidx.annotation.NonNull;

import com.example.cookio.data.network.RetrofitFactory;
import com.example.cookio.data.source.UserApi;
import com.example.cookio.data.utils.CallToConsumer;
import com.example.cookio.domain.UserRepository;
import com.example.cookio.domain.entitites.Status;
import com.example.cookio.domain.entitites.UserEntity;

import java.util.function.Consumer;

public class UserRepositoryImplementation implements UserRepository {

    private static UserRepositoryImplementation INSTANCE;
    private final UserApi userApi = RetrofitFactory.getInstance().getUserApi();

    private UserRepositoryImplementation() {}

    public static synchronized UserRepositoryImplementation getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRepositoryImplementation();
        }
        return INSTANCE;
    }

    @Override
    public void getUserById(@NonNull String id, @NonNull Consumer<Status<UserEntity>> callback) {
        userApi.getUserById(id).enqueue(new CallToConsumer<>(
                callback,
                userDto -> {
                    final String ResultId = userDto.id;
                    final String name = userDto.name;
                    if (ResultId != null && name != null) {
                        return new UserEntity(
                                ResultId,
                                name,
                                userDto.lastName,
                                userDto.nickName,
                                userDto.image,
                                userDto.posts,
                                userDto.points
                        );
                    } else {
                        return null;
                    }
                }
        ));

    }
}
