package com.app.mycvbuilderapp;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CVPreviewActivity extends AppCompatActivity {
    ImageView ivProfilePic;
    TextView tvName, tvEmail, tvPhone, tvLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvpreview);

        ivProfilePic = findViewById(R.id.ivProfilePic); // Initialize ImageView
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        tvLocation = findViewById(R.id.tvLocation);


        // Load data from different SharedPreferences files
        loadProfile();
        loadPersonalDetails();
    }

    private void loadProfile() {
        SharedPreferences preferences = getSharedPreferences("ProfilePrefs", MODE_PRIVATE);
        String imageUriString = preferences.getString("imageUri", null);

        if (imageUriString != null) {
            Uri imageUri = Uri.parse(imageUriString);
            ivProfilePic.setImageURI(imageUri);
        } else {
            ivProfilePic.setImageResource(R.drawable.icon_profilepic); // Default image
        }
    }

    private void loadPersonalDetails() {
        SharedPreferences preferences = getSharedPreferences("PersonalDetailsPrefs", MODE_PRIVATE);
        tvName.setText(preferences.getString("name", "Your Name"));
        tvEmail.setText(preferences.getString("email", "your.email@example.com"));
        tvPhone.setText(preferences.getString("phone", "+123-456-7890"));
        tvLocation.setText(preferences.getString("location", "Lahore,Pakistan"));
    }
}