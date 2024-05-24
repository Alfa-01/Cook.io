package com.example.cookio.profile;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cookio.data.UserRepositoryImplementation;
import com.example.cookio.domain.GetUserByIdUseCase;
import com.example.cookio.domain.entitites.Status;
import com.example.cookio.domain.entitites.UserEntity;


public class UserViewModel extends ViewModel {

    private final MutableLiveData<State> stateMutableLiveData = new MutableLiveData<>();
    public final LiveData<State> stateLiveData = stateMutableLiveData;

    private final GetUserByIdUseCase getUserByIdUseCase =
            new GetUserByIdUseCase(UserRepositoryImplementation.getInstance());

    public UserViewModel() {
        update();
    }

    public void update() {
        stateMutableLiveData.setValue(
                new State(null, null, true));
//        getUserByIdUseCase.execute(status);
    }

    private State fromState(Status<UserEntity> status) {
        return new State(
                status.getErrors() != null ? status.getErrors().getLocalizedMessage() : null,
                status.getValue(),
                false
        );
    }

    public class State {

        @Nullable
        private final String errorMessage;

        @Nullable
        private final UserEntity item;

        private final boolean isLoading;

        @Nullable
        public String getErrorMessage() {
            return errorMessage;
        }

        @Nullable
        public UserEntity getItems() {
            return item;
        }

        public boolean isLoading() {
            return isLoading;
        }

        public State(
                @Nullable String errorMessage,
                @Nullable UserEntity item,
                boolean isLoading) {
            this.errorMessage = errorMessage;
            this.item = item;
            this.isLoading = isLoading;
        }
    }
}
