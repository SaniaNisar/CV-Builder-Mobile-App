package com.app.mycvbuilderapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CertificationsActivity extends AppCompatActivity {

    private EditText etCourseProvided, etName;
    private Spinner etYear;
    private Button btnSave, btnCancel;
    private SharedPreferences sharedPreferences;

    private static final String PREF_NAME = "UserPreferences";
    private static final String KEY_COURSE_PROVIDER = "course_provider";
    private static final String KEY_COURSE_NAME = "course_name";
    private static final String KEY_SPINNER_YEAR= "certification_year";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certifications);

        etCourseProvided = findViewById(R.id.etCourseProvided);
        etName = findViewById(R.id.etCourseName);
        etYear = findViewById(R.id.spinnerYear);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Initialize Spinner before loading saved data
        setupSpinners();

        // Load saved data (if available)
        etCourseProvided.setText(sharedPreferences.getString(KEY_COURSE_PROVIDER, ""));
        etName.setText(sharedPreferences.getString(KEY_COURSE_NAME, ""));

        // Set the correct year in the Spinner if saved
        String savedYear = sharedPreferences.getString(KEY_SPINNER_YEAR, "");
        if (!savedYear.isEmpty()) {
            int position = ((ArrayAdapter<String>) etYear.getAdapter()).getPosition(savedYear);
            etYear.setSelection(position);
        }

        // Save button logic
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String institution = etCourseProvided.getText().toString().trim();
                String degree = etName.getText().toString().trim();
                String year = etYear.getSelectedItem().toString();

                // Validation: Check if any field is empty
                if (institution.isEmpty() || degree.isEmpty() || year.isEmpty()) {
                    Toast.makeText(CertificationsActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_COURSE_PROVIDER, institution);
                editor.putString(KEY_COURSE_NAME, degree);
                editor.putString(KEY_SPINNER_YEAR, year);
                editor.apply();

                Toast.makeText(CertificationsActivity.this, "Certification Details Saved", Toast.LENGTH_SHORT).show();

                // Navigate back to Home Screen
                Intent intent = new Intent(CertificationsActivity.this, MainActivity.class);
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
            Toast.makeText(CertificationsActivity.this, "Changes Discarded", Toast.LENGTH_SHORT).show();

            // Navigate back to Home Screen
            Intent intent = new Intent(CertificationsActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

    private void setupSpinners() {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        List<String> years = new ArrayList<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear; i >= 1980; i--) {
            years.add(String.valueOf(i));
        }

        etYear.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, years));
    }
}
