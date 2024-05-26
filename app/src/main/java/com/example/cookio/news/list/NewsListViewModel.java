package com.example.cookio.news.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cookio.data.NewsRepositoryImplementation;
import com.example.cookio.data.utils.State;
import com.example.cookio.domain.GetNewsListUseCase;
import com.example.cookio.domain.entitites.NewsEntity;
import com.example.cookio.domain.entitites.Status;

import java.util.List;

public class NewsListViewModel extends ViewModel {

    private final MutableLiveData<State<NewsEntity>> stateMutableLiveData = new MutableLiveData<>();
    public final LiveData<State<NewsEntity>> stateLiveData = stateMutableLiveData;
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

    private State<NewsEntity> fromState(Status<List<NewsEntity>> status) {
        return new State<NewsEntity>(
                status.getErrors() != null ? status.getErrors().getLocalizedMessage() : null,
                status.getValue(),
                false
        );
    }
}
