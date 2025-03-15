package com.app.mycvbuilderapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    private ImageView imgProfile;
    private Button btnSelectImage, btnSave, btnCancel;
    private Uri imageUri;

    // ActivityResultLauncher for image selection
    private final ActivityResultLauncher<Intent> getImageLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                                imageUri = result.getData().getData();
                                imgProfile.setImageURI(imageUri);
                            } else {
                                Toast.makeText(ProfileActivity.this, "No image selected", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imgProfile = findViewById(R.id.imgProfile);
        btnSelectImage = findViewById(R.id.btnSelectImage);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        loadImage(); // Load saved profile picture

        btnSelectImage.setOnClickListener(v -> openGallery());

        btnSave.setOnClickListener(v -> {
            if (imageUri != null) {
                saveImage(imageUri);
                Toast.makeText(this, "Profile picture saved!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
            }
            finish();
        });

        btnCancel.setOnClickListener(v -> finish());
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        getImageLauncher.launch(intent); // Using new ActivityResultLauncher
    }

    private void saveImage(Uri uri) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyCVApp", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("profile_image", Uri.encode(uri.toString()));
        editor.apply();
    }

    private void loadImage() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyCVApp", MODE_PRIVATE);
        String imageUriString = sharedPreferences.getString("profile_image", null);
        if (imageUriString != null) {
            Uri uri = Uri.parse(Uri.decode(imageUriString));
            imgProfile.setImageURI(uri);
        }
    }
}
