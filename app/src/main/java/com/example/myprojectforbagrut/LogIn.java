package com.example.myprojectforbagrut;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
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
    private CheckBox cbRememberMe;
    private Button btnLogin;
    private TextView tvDontHaveAccount;

    private String registeredUser;
    private String registeredPassword;

    private SharedPreferences sharedPreferences;

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

        // 1. Поиск элементов по ID
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        cbRememberMe = findViewById(R.id.cbRememberMe);
        btnLogin = findViewById(R.id.btnLogin);
        tvDontHaveAccount = findViewById(R.id.tvDontHaveAccount);

        // Инициализация SharedPreferences
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);

        // 3. קריאה: Считывание сохраненных данных из SharedPreferences
        boolean isRemembered = sharedPreferences.getBoolean("is_remembered", false);
        if (isRemembered) {
            String savedEmail = sharedPreferences.getString("saved_email", "");
            String savedPassword = sharedPreferences.getString("saved_password", "");
            etEmail.setText(savedEmail);
            etPassword.setText(savedPassword);
            cbRememberMe.setChecked(true);
        }

        // 5. Получение данных с экрана регистрации (SignUp)
        Intent incomingIntent = getIntent();
        if (incomingIntent != null) {
            registeredUser = incomingIntent.getStringExtra("username");
            registeredPassword = incomingIntent.getStringExtra("password");

            if (registeredUser != null && etEmail.getText().toString().trim().isEmpty()) {
                etEmail.setText(registeredUser);
            }
        }

        // 2 & 4. כתיבה והודעה: Логика кнопки входа
        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty()) {
                etEmail.setError("נא להזין אימייל");
                return;
            }

            if (password.isEmpty()) {
                etPassword.setError("נא להזין סיסמה");
                return;
            }

            if (password.length() < 6) {
                etPassword.setError("סיסמה חייבת להכיל לפחות 6 תווים");
                return;
            }

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

            // 2 & 4. כתיבה / מחיקה: Сохранение или очистка данных в SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (cbRememberMe.isChecked()) {
                editor.putBoolean("is_remembered", true);
                editor.putString("saved_email", email);
                editor.putString("saved_password", password);
                Toast.makeText(LogIn.this, "פרטי ההתחברות נשמרו", Toast.LENGTH_SHORT).show();
            } else {
                // 4. מחיקה: Очистка SharedPreferences если галочка не стоит
                editor.clear();
            }
            editor.apply();

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