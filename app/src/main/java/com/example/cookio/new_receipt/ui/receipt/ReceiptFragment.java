package com.example.cookio.new_receipt.ui.receipt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.cookio.R;
import com.example.cookio.databinding.ReceiptFragmentLayoutBinding;
import com.example.cookio.domain.entitites.FullReceiptEntity;
import com.squareup.picasso.Picasso;

public class ReceiptFragment extends Fragment {

    private static final String KEY_ID = "id";
    private ReceiptFragmentLayoutBinding binding;
    private ReceiptViewModel receiptViewModel;

    public ReceiptFragment() {
        super(R.layout.receipt_fragment_layout);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = ReceiptFragmentLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        receiptViewModel = new ViewModelProvider(this).get(ReceiptViewModel.class);

        receiptViewModel.stateLiveData.observe(getViewLifecycleOwner(), (state -> {
            FullReceiptEntity fullReceiptEntity = state.getItem();
            if (fullReceiptEntity == null) return;

            if (fullReceiptEntity.getImage() != null)
                Picasso.get().load(fullReceiptEntity.getImage()).into(binding.receiptImage);
            if (fullReceiptEntity.getName() != null)
                binding.receiptName.setText(fullReceiptEntity.getName());

            if (fullReceiptEntity.getDescription() != null)
                binding.description.setText(fullReceiptEntity.getDescription());

        }));

        String id = getArguments() != null ? getArguments().getString(KEY_ID) : null;
        if (id == null) throw new IllegalStateException("ID is null");

        receiptViewModel.load(id);

        binding.startCooking.setOnClickListener(v -> {
            Navigation.findNavController(view)
                    .navigate(R.id.action_receiptFragment_to_receiptIngredientsFragment,
                              ReceiptIngredientsFragment.getBundle(id));
        });
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

    public static Bundle getBundle(@NonNull String id) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_ID, id);
        return bundle;
    }
}
