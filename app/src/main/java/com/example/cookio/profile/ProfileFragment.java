package com.example.cookio.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.cookio.R;
import com.example.cookio.databinding.FragmentProfileBinding;
import com.example.cookio.domain.entitites.UserEntity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private FirebaseUser user;
    private UserViewModel userViewModel;

    public ProfileFragment() {
        super(R.layout.fragment_profile);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                               @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.stateLiveData.observe(getViewLifecycleOwner(), state -> {

            UserEntity userEntity = state.getItems();
            if (userEntity == null) return;

            if (userEntity.getName() != null) {
                binding.name.setText(userEntity.getName());
            }
            if (userEntity.getLastName() != null) {
                binding.lastName.setText(userEntity.getLastName());
            }
            if (userEntity.getNickName() != null) {
                binding.nickName.setText(userEntity.getNickName());
            }
            binding.cookPoints.setText(String.valueOf(userEntity.getPoints()));

        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        binding.email.setText(user.getEmail());
        userViewModel.load(user.getUid());


        binding.logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Log.d("Auth", "Logged out");   });

        binding.edit.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(
                    R.id.action_profileFragment_to_editProfileFragment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}