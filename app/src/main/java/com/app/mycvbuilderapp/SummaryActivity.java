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

public class SummaryActivity extends AppCompatActivity {

    private EditText etSummary;
    private Button btnSave, btnCancel;
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "SummaryPrefs";
    private static final String KEY_SUMMARY = "summary_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        etSummary = findViewById(R.id.etSummary);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Load previously saved summary (if exists)
        String savedSummary = sharedPreferences.getString(KEY_SUMMARY, "");
        if (!savedSummary.isEmpty()) {
            etSummary.setText(savedSummary);
        }

        // Save summary when Save button is clicked
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String summaryText = etSummary.getText().toString().trim();

                // Check if summary is empty
                if (summaryText.isEmpty()) {
                    Toast.makeText(SummaryActivity.this, "Please enter your summary", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_SUMMARY, summaryText);
                editor.apply();

                Toast.makeText(SummaryActivity.this, "Summary Saved", Toast.LENGTH_SHORT).show();

                // Navigate back to Home Screen
                Intent intent = new Intent(SummaryActivity.this, MainActivity.class);
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
            Toast.makeText(this, "Changes Discarded", Toast.LENGTH_SHORT).show();

            // Navigate back to Home Screen
            Intent intent = new Intent(SummaryActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
