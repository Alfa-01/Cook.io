package com.example.cookio.new_receipt.ui.receipt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cookio.data.ReceiptRepositoryImplementation;
import com.example.cookio.domain.GetReceiptByIdUseCase;
import com.example.cookio.domain.entitites.FullReceiptEntity;


public class ReceiptViewModel extends ViewModel {

    private final MutableLiveData<State> stateMutableLiveData = new MutableLiveData<>();
    public final LiveData<State> stateLiveData = stateMutableLiveData;

    public final GetReceiptByIdUseCase getReceiptByIdUseCase =
            new GetReceiptByIdUseCase(ReceiptRepositoryImplementation.getInstance());


    public void load(@NonNull String id) {
        stateMutableLiveData.setValue(new State(null, null, true));
        getReceiptByIdUseCase.execute(id, status -> {
            stateMutableLiveData.postValue(new State(
                    status.getErrors() != null ? status.getErrors().getLocalizedMessage() : null,
                    status.getValue(),
                    false
            ));
        });
    }

    public class State {
        @Nullable
        private final String errorMessage;

        @Nullable
        private final FullReceiptEntity item;

        private final boolean isLoading;

        @Nullable
        public String getErrorMessage() {
            return errorMessage;
        }

        @Nullable
        public FullReceiptEntity getItem() {
            return item;
        }

        public boolean isLoading() {
            return isLoading;
        }

        public State(
                @Nullable String errorMessage,
                @Nullable FullReceiptEntity item,
                boolean isLoading) {
            this.errorMessage = errorMessage;
            this.item = item;
            this.isLoading = isLoading;
        }
    }
}
