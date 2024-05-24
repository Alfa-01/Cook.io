package com.example.cookio.new_receipt.ui.list;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cookio.data.ReceiptRepositoryImplementation;
import com.example.cookio.domain.GetReceiptNamesListUseCase;
import com.example.cookio.domain.entitites.ItemReceiptEntity;
import com.example.cookio.domain.entitites.Status;

import java.util.List;

public class ReceiptListViewModel extends ViewModel {

    private final MutableLiveData<State> stateMutableLiveData = new MutableLiveData<>();
    public final LiveData<State> stateLiveData = stateMutableLiveData;
    private final GetReceiptNamesListUseCase getReceiptNamesListUseCase =
            new GetReceiptNamesListUseCase(ReceiptRepositoryImplementation.getInstance());

    public ReceiptListViewModel() {
        update();
    }

    public void update() {
        stateMutableLiveData.setValue(new State(null, null, true));
        getReceiptNamesListUseCase.execute(status -> {
            stateMutableLiveData.postValue(fromState(status));
        });
    }

    private State fromState(Status<List<ItemReceiptEntity>> status) {
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
        private final List<ItemReceiptEntity> items;

        private final boolean isLoading;

        @Nullable
        public String getErrorMessage() {
            return errorMessage;
        }

        @Nullable
        public List<ItemReceiptEntity> getItems() {
            return items;
        }

        public boolean isLoading() {
            return isLoading;
        }

        public State(@Nullable String errorMessage,
                     @Nullable List<ItemReceiptEntity> items,
                     boolean isLoading) {
            this.errorMessage = errorMessage;
            this.items = items;
            this.isLoading = isLoading;
        }
    }
}
