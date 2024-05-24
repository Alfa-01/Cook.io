package com.example.cookio.profile;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cookio.R;
import com.example.cookio.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    public ProfileFragment() {
        super(R.layout.fragment_profile);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstance) {
        super.onCreate(savedInstance);
        binding = FragmentProfileBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

}
