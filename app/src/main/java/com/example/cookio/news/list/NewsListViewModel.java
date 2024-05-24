package com.example.cookio.news.list;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cookio.data.NewsRepositoryImplementation;
import com.example.cookio.domain.GetNewsListUseCase;
import com.example.cookio.domain.entitites.NewsEntity;
import com.example.cookio.domain.entitites.Status;

import java.util.List;

public class NewsListViewModel extends ViewModel {

    private final MutableLiveData<State> stateMutableLiveData = new MutableLiveData<>();
    public final LiveData<State> stateLiveData = stateMutableLiveData;
    private final GetNewsListUseCase getNewsListUseCase =
            new GetNewsListUseCase(NewsRepositoryImplementation.getInstance());

    public NewsListViewModel() {
        update();
    }

    public void update() {
        stateMutableLiveData.setValue(
                new State(null, null, true));
        getNewsListUseCase.execute(status -> {
            stateMutableLiveData.postValue(fromState(status));
        });
    }

    private State fromState(Status<List<NewsEntity>> status) {
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
        private final List<NewsEntity> items;

        private final boolean isLoading;

        @Nullable
        public String getErrorMessage() {
            return errorMessage;
        }

        @Nullable
        public List<NewsEntity> getItems() {
            return items;
        }

        public boolean isLoading() {
            return isLoading;
        }

        public State(
                @Nullable String errorMessage,
                @Nullable List<NewsEntity> items,
                boolean isLoading) {
            this.errorMessage = errorMessage;
            this.items = items;
            this.isLoading = isLoading;
        }
    }
}
