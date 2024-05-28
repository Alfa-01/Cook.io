package com.example.cookio.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cookio.MainActivity;
import com.example.cookio.R;
import com.example.cookio.databinding.SignInLayoutBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationSignInActivity extends AppCompatActivity {

    private SignInLayoutBinding binding;
    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(AuthenticationSignInActivity.this,
                                     MainActivity.class));
            finish();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SignInLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setStatusBarColor(getResources().getColor(R.color.white));

        mAuth = FirebaseAuth.getInstance();

        binding.signInButton.setOnClickListener(v -> {
                mAuth.signInWithEmailAndPassword(binding.emailInput.getText().toString(),
                                                 binding.passwordInput.getText().toString())
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {

                                Intent intent = new
                                        Intent(AuthenticationSignInActivity.this,
                                               MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                binding.signInWarning.setVisibility(View.VISIBLE);
                            }
                        });
            });

        binding.signUpHint.setOnClickListener(v -> {
            Intent intent = new Intent(AuthenticationSignInActivity.this,
                    AuthenticationSignUpActivity.class);
            startActivity(intent);
            finish();
        });

    }
}
