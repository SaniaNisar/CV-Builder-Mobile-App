package com.app.mycvbuilderapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PersonalDetailsActivity extends AppCompatActivity {
    private EditText etName, etEmail, etPhone, etLocation;
    private Button btnSave, btnCancel;
    private SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "PersonalDetailsPrefs";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_LOCATION = "location";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        init();
        loadSavedDetails(); // Load saved details on startup
    }

    private void init() {
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etLocation = findViewById(R.id.etLocation);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        btnSave.setOnClickListener(v -> saveDetails());
        btnCancel.setOnClickListener(v -> discardChanges()); // FIXED
    }

    private void saveDetails() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String location = etLocation.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || location.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PHONE, phone);
        editor.putString(KEY_LOCATION, location);
        editor.apply();

        Toast.makeText(this, "Details Saved!", Toast.LENGTH_SHORT).show();
        finish(); // Close activity after saving
    }

    private void loadSavedDetails() {
        etName.setText(sharedPreferences.getString(KEY_NAME, ""));
        etEmail.setText(sharedPreferences.getString(KEY_EMAIL, ""));
        etPhone.setText(sharedPreferences.getString(KEY_PHONE, ""));
        etLocation.setText(sharedPreferences.getString(KEY_LOCATION, ""));
    }

    private void discardChanges() {
        btnCancel.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();

            Toast.makeText(this, "Changes Discarded", Toast.LENGTH_SHORT).show();

            // Navigate back to Home Screen
            Intent intent = new Intent(PersonalDetailsActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
