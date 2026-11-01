package com.example.myprojectforbagrut;

import android.content.Intent;
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

public class SignUp extends AppCompatActivity {

    private EditText etFirstName, etLastName, etEmail, etPassword, etConfirmPassword;
    private CheckBox cbTerms;
    private Button btnSignUp;
    private TextView tvAlreadyHaveAccount;

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

        // 3. Поиск элементов по ID
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        cbTerms = findViewById(R.id.cbTerms);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvAlreadyHaveAccount = findViewById(R.id.tvAlreadyHaveAccount);

        // 3-4. Поэтапная проверка с setError() и return
        btnSignUp.setOnClickListener(v -> {
            String firstName = etFirstName.getText().toString().trim();
            String lastName = etLastName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            // Проверка имени
            if (firstName.isEmpty()) {
                etFirstName.setError("נא להזין שם פרטי");
                return;
            }

            // Проверка фамилии
            if (lastName.isEmpty()) {
                etLastName.setError("נא להזין שם משפחה");
                return;
            }

            // Проверка אימייל
            if (email.isEmpty()) {
                etEmail.setError("נא להזין אימייל");
                return;
            }

            // Проверка длины пароля (минимум 6 символов)
            if (password.isEmpty()) {
                etPassword.setError("נא להזין סיסמה");
                return;
            }
            if (password.length() < 6) {
                etPassword.setError("סיסמה חייבת להכיל לפחות 6 תווים");
                return;
            }

            // Проверка совпадения паролей через equals()
            if (confirmPassword.isEmpty()) {
                etConfirmPassword.setError("נא לאמת סיסמה");
                return;
            }
            if (!password.equals(confirmPassword)) {
                etConfirmPassword.setError("הסיסמאות אינן תואמות");
                return;
            }

            // Проверка чекбокса условий
            if (!cbTerms.isChecked()) {
                Toast.makeText(SignUp.this, "יש לאשר את תנאי השימוש", Toast.LENGTH_SHORT).show();
                return;
            }

            // 4. Передача данных в LogIn через putExtra()
            Toast.makeText(SignUp.this, "הרשמה בוצעה בהצלחה!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignUp.this, LogIn.class);
            intent.putExtra("username", email);
            intent.putExtra("password", password);
            startActivity(intent);
            finish();
        });

        tvAlreadyHaveAccount.setOnClickListener(v -> {
            Intent intent = new Intent(SignUp.this, LogIn.class);
            startActivity(intent);
            finish();
        });
    }
}