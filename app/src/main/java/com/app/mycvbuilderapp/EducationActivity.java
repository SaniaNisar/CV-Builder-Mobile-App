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

public class EducationActivity extends AppCompatActivity {

    private EditText etInstitution, etDegree, etYear;
    private Button btnSave, btnCancel;
    private SharedPreferences sharedPreferences;

    private static final String PREF_NAME = "EducationPrefs";
    private static final String KEY_INSTITUTION = "institution_name";
    private static final String KEY_DEGREE = "degree";
    private static final String KEY_YEAR = "year_of_completion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        etInstitution = findViewById(R.id.etInstitution);
        etDegree = findViewById(R.id.etDegree);
        etYear = findViewById(R.id.etYear);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Load saved data (if available)
        etInstitution.setText(sharedPreferences.getString(KEY_INSTITUTION, ""));
        etDegree.setText(sharedPreferences.getString(KEY_DEGREE, ""));
        etYear.setText(sharedPreferences.getString(KEY_YEAR, ""));

        // Save button logic
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String institution = etInstitution.getText().toString().trim();
                String degree = etDegree.getText().toString().trim();
                String year = etYear.getText().toString().trim();

                // Validation: Check if any field is empty
                if (institution.isEmpty() || degree.isEmpty() || year.isEmpty()) {
                    Toast.makeText(EducationActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_INSTITUTION, institution);
                editor.putString(KEY_DEGREE, degree);
                editor.putString(KEY_YEAR, year);
                editor.apply();

                Toast.makeText(EducationActivity.this, "Education Details Saved", Toast.LENGTH_SHORT).show();

                // Navigate back to Home Screen
                Intent intent = new Intent(EducationActivity.this, MainActivity.class);
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
            Toast.makeText(EducationActivity.this, "Changes Discarded", Toast.LENGTH_SHORT).show();

            // Navigate back to Home Screen
            Intent intent = new Intent(EducationActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
