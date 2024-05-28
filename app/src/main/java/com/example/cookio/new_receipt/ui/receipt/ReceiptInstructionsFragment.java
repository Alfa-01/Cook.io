package com.example.cookio.new_receipt.ui.receipt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.cookio.R;
import com.example.cookio.databinding.ReceiptInstructionsBinding;
import com.example.cookio.domain.entitites.FullReceiptEntity;

public class ReceiptInstructionsFragment extends Fragment {

    private final static String KEY_ID = "id3";
    private ReceiptInstructionsBinding binding;
    private ReceiptViewModel receiptViewModel;

    public ReceiptInstructionsFragment() {
        super(R.layout.receipt_instructions);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = ReceiptInstructionsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        receiptViewModel = new ViewModelProvider(this).get(ReceiptViewModel.class);

        receiptViewModel.stateLiveData.observe(getViewLifecycleOwner(), (state -> {
            FullReceiptEntity fullReceiptEntity = state.getItem();
            if (fullReceiptEntity == null) return;

            if (fullReceiptEntity.getName() != null) {
                binding.receiptNameInInstructions.setText(fullReceiptEntity.getName());
            }

            if (fullReceiptEntity.getInstructions() != null) {

                String[] elems = fullReceiptEntity.getInstructions().split("\\.");

                for (int i = 0; i < elems.length; i ++) {
                    elems[i] = elems[i].trim();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        this.getContext(), R.layout.ingredient_layout, elems
                );
                binding.ingredients.setAdapter(adapter);
            }


        }));

        String id = getArguments() != null ? getArguments().getString(KEY_ID) : null;
        if (id == null) throw new IllegalStateException("ID is null");

        receiptViewModel.load(id);

        binding.nextToReceiptButton.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(
                    R.id.action_receiptInstructionsFragment_to_receiptDoneFragment,
                    ReceiptDoneFragment.getBundle(id));
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
