package com.example.cookio.new_receipt.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cookio.R;
import com.example.cookio.data.utils.Utils;
import com.example.cookio.databinding.NewReceiptFragmentBinding;
import com.example.cookio.new_receipt.ui.list.ReceiptListViewModel;
import com.example.cookio.new_receipt.ui.list.ReceiptRecyclerViewAdapter;

public class NewReceiptFragment extends Fragment {
    private NewReceiptFragmentBinding binding;
    private ReceiptListViewModel receiptListViewModel;

    public NewReceiptFragment() {
        super(R.layout.new_receipt_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = NewReceiptFragmentBinding.inflate(getLayoutInflater());

        receiptListViewModel = new ViewModelProvider(this).get(ReceiptListViewModel.class);

        binding.refresh.setOnRefreshListener(() -> receiptListViewModel.update());

        final ReceiptRecyclerViewAdapter adapter =
                new ReceiptRecyclerViewAdapter(id -> openReceipt(id));
        binding.recycler.setAdapter(adapter);

        subscribe(receiptListViewModel, adapter);

    }

    private void openReceipt(String id) {}


    private void subscribe(final ReceiptListViewModel viewModel,
                           final ReceiptRecyclerViewAdapter adapter) {
        viewModel.stateLiveData.observe(getViewLifecycleOwner(), state -> {
            boolean isSuccess = !state.isLoading()
                    && state.getErrorMessage() == null
                    && state.getItems() != null;

            binding.refresh.setEnabled(!state.isLoading());
            if (!state.isLoading()) binding.refresh.setRefreshing(false);

            binding.recycler.setVisibility(Utils.visibleOrGone(isSuccess));
            binding.error.setVisibility(Utils.visibleOrGone(
                    state.getErrorMessage() != null));
            binding.loading.setVisibility(Utils.visibleOrGone(state.isLoading()));

            binding.error.setText(state.getErrorMessage());
            if (isSuccess) {
                adapter.updateData(state.getItems());
            }
        });
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
