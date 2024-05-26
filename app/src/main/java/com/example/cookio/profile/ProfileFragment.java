package com.example.cookio.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cookio.R;
import com.example.cookio.databinding.FragmentProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private FirebaseUser user;

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

        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            binding.email.setText(user.getEmail());
            Log.d("tag", user.getEmail());
        } else {
            Log.d("tag", "User is not logged in");
        }

        binding.logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Log.d("Auth", "Logged out");   });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}