package com.example.myprojectforbagrut;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Переход на экран LogIn при клике на нижний текст "Already have an account? Login here."
        TextView tvAlreadyHaveAccount = findViewById(R.id.tvAlreadyHaveAccount);
        if (tvAlreadyHaveAccount != null) {
            tvAlreadyHaveAccount.setOnClickListener(v -> {
                Intent intent = new Intent(SignUp.this, LogIn.class);
                startActivity(intent);
            });
        }
    }
}