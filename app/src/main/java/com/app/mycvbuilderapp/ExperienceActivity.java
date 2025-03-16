package com.app.mycvbuilderapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ExperienceActivity extends AppCompatActivity {

    private EditText etCompanyName, etDesignation;
    private Spinner spinnerStartMonth, spinnerStartYear, spinnerEndMonth, spinnerEndYear;
    private CheckBox chkPresent;
    private Button btnSave, btnCancel;
    private SharedPreferences sharedPreferences;

    private static final String PREF_NAME = "UserPreferences";
    private static final String KEY_COMPANY = "company_name";
    private static final String KEY_DESIGNATION = "designation";
    private static final String KEY_START_MONTH = "start_month";
    private static final String KEY_START_YEAR = "start_year";
    private static final String KEY_END_MONTH = "end_month";
    private static final String KEY_END_YEAR = "end_year";
    private static final String KEY_IS_PRESENT = "is_present";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);

        etCompanyName = findViewById(R.id.etCompanyName);
        etDesignation = findViewById(R.id.etDesignation);
        spinnerStartMonth = findViewById(R.id.spinnerStartMonth);
        spinnerStartYear = findViewById(R.id.spinnerStartYear);
        spinnerEndMonth = findViewById(R.id.spinnerEndMonth);
        spinnerEndYear = findViewById(R.id.spinnerEndYear);
        chkPresent = findViewById(R.id.chkPresent);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        setupSpinners();
        loadSavedData();

        // If "Present" is checked, disable end date spinners
        chkPresent.setOnCheckedChangeListener((buttonView, isChecked) -> {
            spinnerEndMonth.setEnabled(!isChecked);
            spinnerEndYear.setEnabled(!isChecked);
        });

        // Save Experience Data
        btnSave.setOnClickListener(v -> saveExperience());

        // Cancel button: discard changes and go back to Home Screen
        btnCancel.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            Toast.makeText(this, "Changes Discarded", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ExperienceActivity.this, MainActivity.class);
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

        spinnerStartMonth.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, months));
        spinnerStartYear.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, years));
        spinnerEndMonth.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, months));
        spinnerEndYear.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, years));
    }

    private void saveExperience() {
        String companyName = etCompanyName.getText().toString().trim();
        String designation = etDesignation.getText().toString().trim();
        String startMonth = spinnerStartMonth.getSelectedItem().toString();
        String startYear = spinnerStartYear.getSelectedItem().toString();
        String endMonth = chkPresent.isChecked() ? "Present" : spinnerEndMonth.getSelectedItem().toString();
        String endYear = chkPresent.isChecked() ? "Present" : spinnerEndYear.getSelectedItem().toString();
        boolean isPresent = chkPresent.isChecked();

        // Validation to ensure all fields are filled
        if (companyName.isEmpty() || designation.isEmpty() || startMonth.isEmpty() || startYear.isEmpty() || (!isPresent && (endMonth.isEmpty() || endYear.isEmpty()))) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save data in SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_COMPANY, companyName);
        editor.putString(KEY_DESIGNATION, designation);
        editor.putString(KEY_START_MONTH, startMonth);
        editor.putString(KEY_START_YEAR, startYear);
        editor.putString(KEY_END_MONTH, endMonth);
        editor.putString(KEY_END_YEAR, endYear);
        editor.putBoolean(KEY_IS_PRESENT, isPresent);
        editor.apply();

        Toast.makeText(this, "Experience Saved!", Toast.LENGTH_SHORT).show();

        // Navigate back to Home Screen
        Intent intent = new Intent(ExperienceActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void loadSavedData() {
        etCompanyName.setText(sharedPreferences.getString(KEY_COMPANY, ""));
        etDesignation.setText(sharedPreferences.getString(KEY_DESIGNATION, ""));
        spinnerStartMonth.setSelection(getSpinnerIndex(spinnerStartMonth, sharedPreferences.getString(KEY_START_MONTH, "")));
        spinnerStartYear.setSelection(getSpinnerIndex(spinnerStartYear, sharedPreferences.getString(KEY_START_YEAR, "")));
        spinnerEndMonth.setSelection(getSpinnerIndex(spinnerEndMonth, sharedPreferences.getString(KEY_END_MONTH, "")));
        spinnerEndYear.setSelection(getSpinnerIndex(spinnerEndYear, sharedPreferences.getString(KEY_END_YEAR, "")));
        chkPresent.setChecked(sharedPreferences.getBoolean(KEY_IS_PRESENT, false));

        if (chkPresent.isChecked()) {
            spinnerEndMonth.setEnabled(false);
            spinnerEndYear.setEnabled(false);
        }
    }

    private int getSpinnerIndex(Spinner spinner, String value) {
        if (value == null) return 0;
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equals(value)) {
                return i;
            }
        }
        return 0;
    }
}
