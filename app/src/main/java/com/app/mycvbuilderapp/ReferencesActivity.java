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

public class ReferencesActivity extends AppCompatActivity {

    private EditText etName, etDesignation, etContact;
    private Button btnSave, btnCancel;
    private SharedPreferences sharedPreferences;

    private static final String PREF_NAME = "ReferencesPrefs";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_DESIGNATION = "user_designation";
    private static final String KEY_CONTACT = "user_contact";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_references);

        etName = findViewById(R.id.etName);
        etDesignation = findViewById(R.id.etDesignation);
        etContact = findViewById(R.id.etContact);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Load saved data (if available)
        etName.setText(sharedPreferences.getString(KEY_USER_NAME, ""));
        etDesignation.setText(sharedPreferences.getString(KEY_DESIGNATION, ""));
        etContact.setText(sharedPreferences.getString(KEY_CONTACT, ""));

        // Save button logic
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String designation = etDesignation.getText().toString().trim();
                String contact = etContact.getText().toString().trim();

                // Validation: Check if any field is empty
                if (name.isEmpty() || designation.isEmpty() || contact.isEmpty()) {
                    Toast.makeText(ReferencesActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_USER_NAME, name);
                editor.putString(KEY_DESIGNATION, designation);
                editor.putString(KEY_CONTACT, contact);
                editor.apply();

                Toast.makeText(ReferencesActivity.this, "User Details Saved", Toast.LENGTH_SHORT).show();

                // Navigate back to Home Screen
                Intent intent = new Intent(ReferencesActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        // Cancel button: discard changes and go back to Home Screen
        btnCancel.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            Toast.makeText(ReferencesActivity.this, "Changes Discarded", Toast.LENGTH_SHORT).show();

            // Navigate back to Home Screen
            Intent intent = new Intent(ReferencesActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
