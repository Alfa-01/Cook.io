package com.example.cookio.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cookio.MainActivity;
import com.example.cookio.R;
import com.example.cookio.data.dto.UserDto;
import com.example.cookio.data.network.RetrofitFactory;
import com.example.cookio.data.utils.Utils;
import com.example.cookio.databinding.SignUpLayoutBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AuthenticationSignUpActivity extends AppCompatActivity {

    private SignUpLayoutBinding binding;
    private FirebaseAuth mAuth;
    private DatabaseReference reference;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SignUpLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setStatusBarColor(getResources().getColor(R.color.white));

        mAuth = FirebaseAuth.getInstance();

        binding.signUpButton.setOnClickListener(v -> {
                mAuth.createUserWithEmailAndPassword(binding.emailInput.getText().toString(),
                                binding.passwordInput.getText().toString())
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {

                                FirebaseUser currentUser = mAuth.getCurrentUser();

                                reference =
                                        FirebaseDatabase.getInstance(Utils.DbURL).getReference();
                                reference.child("users").child(currentUser.getUid())
                                        .setValue(new UserDto(
                                                currentUser.getUid(),
                                                null,
                                                binding.nameInput.getText().toString().split(" ")[0],
                                                binding.nameInput.getText().toString().split(" ")[1],
                                                0,
                                                null
                                        ));

                                Intent intent = new Intent(AuthenticationSignUpActivity.this,
                                                            MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                binding.signUpWarning.setVisibility(View.VISIBLE);
                            }
                        });
                });

        binding.signInHint.setOnClickListener(v -> {
            Intent intent = new Intent(AuthenticationSignUpActivity.this,
                    AuthenticationSignInActivity.class);
            startActivity(intent);
            finish();
        });

    }
}
