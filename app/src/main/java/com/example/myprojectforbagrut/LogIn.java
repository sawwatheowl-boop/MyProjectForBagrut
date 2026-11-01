package com.example.myprojectforbagrut;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Переход на экран SignUp при клике на нижний текст "Don't have an account? Sign up here."
        TextView tvDontHaveAccount = findViewById(R.id.tvDontHaveAccount);
        if (tvDontHaveAccount != null) {
            tvDontHaveAccount.setOnClickListener(v -> {
                Intent intent = new Intent(LogIn.this, SignUp.class);
                startActivity(intent);
            });
        }
    }
}