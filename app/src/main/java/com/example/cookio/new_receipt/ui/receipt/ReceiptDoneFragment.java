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
import com.example.cookio.databinding.ReceiptDoneLayoutBinding;
import com.example.cookio.domain.GetUserByIdUseCase;
import com.example.cookio.domain.entitites.FullReceiptEntity;
import com.example.cookio.domain.entitites.UserEntity;
import com.example.cookio.profile.UserViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReceiptDoneFragment extends Fragment {

    private final static String KEY_ID = "id3";
    private ReceiptDoneLayoutBinding binding;
    private ReceiptViewModel receiptViewModel;
    private DatabaseReference reference;
    private FirebaseUser user;
    private GetUserByIdUseCase userData;
    private UserViewModel userViewModel;
    private int currentPoints;

    public ReceiptDoneFragment() {
        super(R.layout.receipt_done_layout);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = ReceiptDoneLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        receiptViewModel = new ViewModelProvider(this).get(ReceiptViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        user = FirebaseAuth.getInstance().getCurrentUser();

        userViewModel.stateLiveData.observe(getViewLifecycleOwner(), (state -> {
            UserEntity userEntity = state.getItems();
            if (userEntity == null) return;

            currentPoints = state.getItems().getPoints();
        }));

        userViewModel.load(user.getUid());


        receiptViewModel.stateLiveData.observe(getViewLifecycleOwner(), (state -> {
            FullReceiptEntity fullReceiptEntity = state.getItem();
            if (fullReceiptEntity == null) return;

            if (fullReceiptEntity.getName() != null)
                binding.receiptName.setText(fullReceiptEntity.getName());

            binding.difficult.setText(String.valueOf(fullReceiptEntity.getDifficult()));

            binding.time.setText(String.valueOf(fullReceiptEntity.getTime()));

            if (fullReceiptEntity.getIngredients() != null)
                binding.amountOfIngredients.setText(String.valueOf(
                        fullReceiptEntity.getIngredients().length));

            int result = fullReceiptEntity.getDifficult() * 30 +
                    fullReceiptEntity.getTime() * 30 +
                    fullReceiptEntity.getIngredients().length * 10;

            binding.receiptResult.setText(String.valueOf(result));

            reference = FirebaseDatabase.getInstance().getReference();


            reference.child("users")
                    .child(user.getUid())
                    .child("points")
                    .setValue(String.valueOf(currentPoints + result));
        }));

        String id = getArguments() != null ? getArguments().getString(KEY_ID) : null;
        if (id == null) throw new IllegalStateException("ID is null");

        receiptViewModel.load(id);

        binding.backToMain.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(
                    R.id.action_receiptDoneFragment_to_newsFragment);
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
