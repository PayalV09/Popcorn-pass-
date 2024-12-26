package com.example.popcorntest1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText editTextAdminId, editTextAdminPassword, editTextAdminEmail;
    private Button buttonLogin;

    // Hardcoded admin credentials and email
    private static final String ADMIN_ID = "p1";
    private static final String ADMIN_PASSWORD = "123";
    private static final String ADMIN_EMAIL = "payalvidhate09@gmail.com"; // Admin email

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        // Initialize UI elements
        editTextAdminId = findViewById(R.id.admin_id_edit_text);
        editTextAdminPassword = findViewById(R.id.admin_password_edit_text);
        editTextAdminEmail = findViewById(R.id.admin_email_edit_text); // Email input field
        buttonLogin = findViewById(R.id.login_button);

        // Set the login button click listener
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredAdminId = editTextAdminId.getText().toString();
                String enteredAdminPassword = editTextAdminPassword.getText().toString();
                String enteredAdminEmail = editTextAdminEmail.getText().toString(); // Get entered email

                // Check if the entered credentials match the hardcoded values (Admin ID, Password, and Email)
                if (enteredAdminId.equals(ADMIN_ID) && enteredAdminPassword.equals(ADMIN_PASSWORD) && enteredAdminEmail.equals(ADMIN_EMAIL)) {
                    // Successful login
                    Toast.makeText(AdminLoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                } else {
                    // Failed login
                    Toast.makeText(AdminLoginActivity.this, "Invalid Admin ID, Password, or Email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
