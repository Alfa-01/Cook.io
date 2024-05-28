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
import com.example.cookio.databinding.ReceiptIngredientsBinding;
import com.example.cookio.domain.entitites.FullReceiptEntity;

public class ReceiptIngredientsFragment extends Fragment {

    private final static String KEY_ID = "id2";
    private ReceiptIngredientsBinding binding;
    private ReceiptViewModel receiptViewModel;

    public ReceiptIngredientsFragment() {
        super(R.layout.receipt_ingredients);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = ReceiptIngredientsBinding.inflate(inflater, container, false);
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
                binding.receiptNameInIngredients.setText(fullReceiptEntity.getName());
            }

            if (fullReceiptEntity.getIngredients() != null
                    && fullReceiptEntity.getMeasures() != null) {
                String[] elems = new String[fullReceiptEntity.getIngredients().length];
                for (int i = 0; i < fullReceiptEntity.getIngredients().length; i++) {
                    elems[i] = fullReceiptEntity.getIngredients()[i] + " - " +
                            fullReceiptEntity.getMeasures()[i];
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
            Navigation.findNavController(view)
                    .navigate(
                            R.id.action_receiptIngredientsFragment_to_receiptInstructionsFragment,
                            ReceiptInstructionsFragment.getBundle(id));
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
