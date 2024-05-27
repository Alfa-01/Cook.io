package com.example.cookio.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.cookio.R;
import com.example.cookio.databinding.FragmentProfileEditBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileFragment extends Fragment {

    private FragmentProfileEditBinding binding;
    private FirebaseUser currentUser;
    private DatabaseReference reference;

    public EditProfileFragment() {
        super(R.layout.fragment_profile_edit);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentProfileEditBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference();


        binding.save.setOnClickListener(v -> {
            reference.child("users").child(currentUser.getUid())
                    .child("nickName").setValue(binding.nickName.getText().toString());
            reference.child("users").child(currentUser.getUid())
                    .child("name").setValue(binding.name.getText().toString());
            reference.child("users").child(currentUser.getUid())
                    .child("lastName").setValue(binding.lastName.getText().toString());

            Navigation.findNavController(view).navigate(
                    R.id.action_editProfileFragment_to_profileFragment);
        });



    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
