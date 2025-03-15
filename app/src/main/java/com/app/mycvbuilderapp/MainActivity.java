package com.app.mycvbuilderapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up button click listeners
        findViewById(R.id.btnProfilePic).setOnClickListener(view -> openActivity(ProfileActivity.class));
        findViewById(R.id.btnPersonalDetails).setOnClickListener(v -> openActivity(PersonalDetailsActivity.class));
        findViewById(R.id.btnSummary).setOnClickListener(v -> openActivity(SummaryActivity.class));
        findViewById(R.id.btnEducation).setOnClickListener(v -> openActivity(EducationActivity.class));
        findViewById(R.id.btnExperience).setOnClickListener(v -> openActivity(ExperienceActivity.class));
        findViewById(R.id.btnCertifications).setOnClickListener(v -> openActivity(CertificationsActivity.class));
        findViewById(R.id.btnReferences).setOnClickListener(v -> openActivity(ReferencesActivity.class));
    }

    private void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(MainActivity.this, activityClass);
        startActivity(intent);
    }
}
