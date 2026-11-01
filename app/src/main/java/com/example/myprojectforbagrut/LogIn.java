package com.example.myprojectforbagrut;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LogIn extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvDontHaveAccount;

    private String registeredUser;
    private String registeredPassword;

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

        // 3. Поиск элементов по ID
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvDontHaveAccount = findViewById(R.id.tvDontHaveAccount);

        // 5. Получение переданных данных с проверкой на null
        Intent incomingIntent = getIntent();
        if (incomingIntent != null) {
            registeredUser = incomingIntent.getStringExtra("username");
            registeredPassword = incomingIntent.getStringExtra("password");

            // Заполнение поля email, если данные пришла из регистрации
            if (registeredUser != null) {
                etEmail.setText(registeredUser);
            }
        }

        // 6. Проверка при нажатии на кнопку входа
        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            // Проверка на пустоту с setError() и return
            if (email.isEmpty()) {
                etEmail.setError("נא להזין אימייל");
                return;
            }

            if (password.isEmpty()) {
                etPassword.setError("נא להזין סיסמה");
                return;
            }

            // Проверка длинной сисмы
            if (password.length() < 6) {
                etPassword.setError("סיסמה חייבת להכיל לפחות 6 תווים");
                return;
            }

            // 6. Сравнение введенных данных с сохраненными через equals()
            if (registeredUser != null && registeredPassword != null) {
                if (!email.equals(registeredUser)) {
                    etEmail.setError("שם משתמש שגוי");
                    return;
                }
                if (!password.equals(registeredPassword)) {
                    etPassword.setError("סיסמה שגויה");
                    return;
                }
            }

            Toast.makeText(LogIn.this, "התחברת בהצלחה!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LogIn.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        tvDontHaveAccount.setOnClickListener(v -> {
            Intent intent = new Intent(LogIn.this, SignUp.class);
            startActivity(intent);
            finish();
        });
    }
}