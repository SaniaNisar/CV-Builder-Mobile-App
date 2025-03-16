//package com.app.mycvbuilderapp;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.activity.result.ActivityResultLauncher;
//import androidx.activity.result.contract.ActivityResultContracts;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//public class ProfileActivity extends AppCompatActivity {
//    private ImageView ivProfilePic;
//    private FloatingActionButton fabSetProfilePic;
//    private Button btnSelectImage, btnSave, btnCancel;
//    private ActivityResultLauncher<Intent> getImageLauncher;
//
//    private SharedPreferences sharedPreferences;
//    private static final String PREFS_NAME = "ProfilePrefs";
//    private static final String KEY_IMAGE_URI = "imageUri";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_profile);
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        init();
//        loadSavedImage(); // Load image when activity starts
//    }
//
//    private void init() {
//        ivProfilePic = findViewById(R.id.ivProfilePic);
//        fabSetProfilePic = findViewById(R.id.fab_setProfilePic);
//        btnSelectImage = findViewById(R.id.btnSelectImage);
//        btnSave = findViewById(R.id.btnSave);
//        btnCancel = findViewById(R.id.btnCancel); // Initialize Cancel button
//
//        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
//
//        getImageLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
//                (result) -> {
//                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
//                        Uri imageUri = result.getData().getData();
//                        ivProfilePic.setImageURI(imageUri);
//                        saveImage(imageUri); // Save the selected image
//                    } else {
//                        Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//        // Set click listeners for both FAB and Select Image button
//        View.OnClickListener imagePickerClickListener = v -> {
//            Intent intent = new Intent(Intent.ACTION_PICK);
//            intent.setType("image/*");
//            getImageLauncher.launch(intent);
//        };
//
//        fabSetProfilePic.setOnClickListener(imagePickerClickListener);
//        btnSelectImage.setOnClickListener(imagePickerClickListener);
//
//        // Save button to persist the selected image
//        btnSave.setOnClickListener(v -> {
//            if (sharedPreferences.contains(KEY_IMAGE_URI)) {
//                Toast.makeText(this, "Image saved successfully!", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(this, "Please select an image first", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        // Cancel button to remove the image and reset to default
//        btnCancel.setOnClickListener(v -> {
//            removeSavedImage();
//            ivProfilePic.setImageResource(R.drawable.icon_profilepic); // Set default profile image
//            Toast.makeText(this, "Profile image removed", Toast.LENGTH_SHORT).show();
//        });
//    }
//
//    private void saveImage(Uri imageUri) {
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(KEY_IMAGE_URI, imageUri.toString());
//        editor.apply();
//    }
//
//    private void loadSavedImage() {
//        String savedImageUri = sharedPreferences.getString(KEY_IMAGE_URI, null);
//        if (savedImageUri != null) {
//            try {
//                Uri imageUri = Uri.parse(savedImageUri);
//                ivProfilePic.setImageURI(imageUri);
//            } catch (Exception e) {
//                e.printStackTrace();
//                Toast.makeText(this, "Failed to load saved image", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    private void removeSavedImage() {
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.remove(KEY_IMAGE_URI);
//        editor.apply();
//    }
//}
package com.app.mycvbuilderapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProfileActivity extends AppCompatActivity {
    private ImageView ivProfilePic;
    private FloatingActionButton fabSetProfilePic;
    private Button btnSelectImage, btnSave, btnCancel;
    private ActivityResultLauncher<Intent> getImageLauncher;

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "ProfilePrefs";
    private static final String KEY_IMAGE_URI = "imageUri";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        loadSavedImage(); // Load image when activity starts
//        Intent intent = new Intent(ProfileActivity.this, AnotherActivity.class);
//        intent.putExtra("imageUri", sharedPreferences.getString(KEY_IMAGE_URI, null));
//        startActivity(intent);
    }

    private void init() {
        ivProfilePic = findViewById(R.id.ivProfilePic);
        fabSetProfilePic = findViewById(R.id.fab_setProfilePic);
        btnSelectImage = findViewById(R.id.btnSelectImage);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel); // Initialize Cancel button

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        getImageLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                (result) -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        ivProfilePic.setImageURI(imageUri);
                        saveImage(imageUri); // Save the selected image
                        hideImageSelectionButtons(); // Hide buttons after selection
                    } else {
                        Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
                    }
                });

        // Set click listeners for both FAB and Select Image button
        View.OnClickListener imagePickerClickListener = v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            getImageLauncher.launch(intent);
        };

        fabSetProfilePic.setOnClickListener(imagePickerClickListener);
        btnSelectImage.setOnClickListener(imagePickerClickListener);

        // Save button to persist the selected image
        btnSave.setOnClickListener(v -> {
            if (sharedPreferences.contains(KEY_IMAGE_URI)) {
                Toast.makeText(this, "Image saved successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please select an image first", Toast.LENGTH_SHORT).show();
            }
        });

        // Cancel button to remove the image and reset to default
        btnCancel.setOnClickListener(v -> {
            removeSavedImage();
            ivProfilePic.setImageResource(R.drawable.icon_profilepic); // Set default profile image
            showImageSelectionButtons(); // Show buttons when image is removed
            Toast.makeText(this, "Profile image removed", Toast.LENGTH_SHORT).show();
        });
    }

    private void saveImage(Uri imageUri) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_IMAGE_URI, imageUri.toString());
        editor.apply();
    }

    private void loadSavedImage() {
        String savedImageUri = sharedPreferences.getString(KEY_IMAGE_URI, null);
        if (savedImageUri != null) {
            try {
                Uri imageUri = Uri.parse(savedImageUri);
                ivProfilePic.setImageURI(imageUri);
                hideImageSelectionButtons(); // Hide buttons if image is already saved
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to load saved image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void removeSavedImage() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_IMAGE_URI);
        editor.apply();
    }

    private void hideImageSelectionButtons() {
        fabSetProfilePic.setVisibility(View.GONE); // Completely hides the FAB
        btnSelectImage.setVisibility(View.GONE); // Completely hides the button
    }

    private void showImageSelectionButtons() {
        fabSetProfilePic.setVisibility(View.VISIBLE); // Show FAB again
        btnSelectImage.setVisibility(View.VISIBLE); // Show button again
    }

    private void navigateBackToHome() {
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Close current activity
    }
}
