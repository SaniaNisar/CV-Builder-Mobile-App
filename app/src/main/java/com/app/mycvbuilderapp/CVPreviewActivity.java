//package com.app.mycvbuilderapp;
//
//import android.content.SharedPreferences;
//import android.net.Uri;
//import android.os.Bundle;
//import android.widget.ImageView;
//import android.widget.TextView;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class CVPreviewActivity extends AppCompatActivity {
//    ImageView ivProfilePic;
//    TextView tvName, tvEmail, tvPhone, tvLocation;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cvpreview);
//
//        ivProfilePic = findViewById(R.id.ivProfilePic); // Initialize ImageView
//        tvName = findViewById(R.id.tvName);
//        tvEmail = findViewById(R.id.tvEmail);
//        tvPhone = findViewById(R.id.tvPhone);
//        tvLocation = findViewById(R.id.tvLocation);
//
//
//        // Load data from different SharedPreferences files
//        loadProfile();
//        loadPersonalDetails();
//    }
//
//    private void loadProfile() {
//        SharedPreferences preferences = getSharedPreferences("ProfilePrefs", MODE_PRIVATE);
//        String imageUriString = preferences.getString("imageUri", null);
//
//        if (imageUriString != null) {
//            Uri imageUri = Uri.parse(imageUriString);
//            ivProfilePic.setImageURI(imageUri);
//        } else {
//            ivProfilePic.setImageResource(R.drawable.icon_profilepic); // Default image
//        }
//    }
//
//    private void loadPersonalDetails() {
//        SharedPreferences preferences = getSharedPreferences("PersonalDetailsPrefs", MODE_PRIVATE);
//        tvName.setText(preferences.getString("name", "Your Name"));
//        tvEmail.setText(preferences.getString("email", "your.email@example.com"));
//        tvPhone.setText(preferences.getString("phone", "+123-456-7890"));
//        tvLocation.setText(preferences.getString("location", "Lahore,Pakistan"));
//    }
//}

package com.app.mycvbuilderapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CVPreviewActivity extends AppCompatActivity {
    private ImageView imgProfile;
    private TextView txtName, txtEmail, txtPhone, txtSummary, txtEducation, txtExperience, txtCertifications, txtReferences;

    // Preference keys
    private static final String PROFILE_PREFS = "ProfilePrefs";
    private static final String PERSONAL_PREFS = "PersonalDetailsPrefs";
    private static final String SUMMARY_PREFS = "SummaryPrefs";
    private static final String EDUCATION_PREFS = "EducationPrefs";
    private static final String EXPERIENCE_PREFS = "ExperiencePrefs";
    private static final String CERTIFICATIONS_PREFS = "CertificationsPrefs";
    private static final String REFERENCES_PREFS = "ReferencesPrefs";

    private static final String KEY_IMAGE_URI = "imageUri";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_SUMMARY = "summary_text";
    private static final String KEY_INSTITUTION = "institution_name";
    private static final String KEY_DEGREE = "degree";
    private static final String KEY_YEAR = "year_of_completion";
    private static final String KEY_COMPANY = "company_name";
    private static final String KEY_POSITION = "designation";
    private static final String KEY_START_MONTH = "start_month";
    private static final String KEY_START_YEAR = "start_year";
    private static final String KEY_END_MONTH = "end_month";
    private static final String KEY_END_YEAR = "end_year";
    private static final String KEY_COURSE_PROVIDER = "course_provider";
    private static final String KEY_COURSE_NAME = "course_name";
    private static final String KEY_SPINNER_YEAR= "certification_year";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_DESIGNATION = "user_designation";
    private static final String KEY_CONTACT = "user_contact";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvpreview);

        // Initialize UI components
        imgProfile = findViewById(R.id.imgProfile);
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
        txtSummary = findViewById(R.id.txtSummary);
        txtEducation = findViewById(R.id.txtEducation);
        txtExperience = findViewById(R.id.txtExperience);
        txtCertifications = findViewById(R.id.txtCertifications);
        txtReferences = findViewById(R.id.txtReferences);
        Button btnShare = findViewById(R.id.btnShare);

        // Load user data from SharedPreferences
        loadProfileData();

        // Set share button action
        btnShare.setOnClickListener(v -> shareCV());
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProfileData();
    }

    private void loadProfileData() {
        SharedPreferences profilePrefs = getSharedPreferences(PROFILE_PREFS, Context.MODE_PRIVATE);
        SharedPreferences personalPrefs = getSharedPreferences(PERSONAL_PREFS, Context.MODE_PRIVATE);
        SharedPreferences summaryPrefs = getSharedPreferences(SUMMARY_PREFS, Context.MODE_PRIVATE);
        SharedPreferences educationPrefs = getSharedPreferences(EDUCATION_PREFS, Context.MODE_PRIVATE);
        SharedPreferences experiencePrefs = getSharedPreferences(EXPERIENCE_PREFS, Context.MODE_PRIVATE);
        SharedPreferences certificationsPrefs = getSharedPreferences(CERTIFICATIONS_PREFS, Context.MODE_PRIVATE);
        SharedPreferences referencesPrefs = getSharedPreferences(REFERENCES_PREFS, Context.MODE_PRIVATE);

        // Load and display profile image
        String imageUriString = profilePrefs.getString(KEY_IMAGE_URI, null);
        if (imageUriString != null) {
            imgProfile.setImageURI(Uri.parse(imageUriString));
        }

        // Load and display text data
        txtName.setText(personalPrefs.getString(KEY_NAME, "Not Available"));
        txtEmail.setText(personalPrefs.getString(KEY_EMAIL, "Not Available"));
        txtPhone.setText(personalPrefs.getString(KEY_PHONE, "Not Available"));
        txtSummary.setText(summaryPrefs.getString(KEY_SUMMARY, "Not Provided"));
        txtEducation.setText(educationPrefs.getString(KEY_INSTITUTION, "Institution: N/A") + "\n" + educationPrefs.getString(KEY_DEGREE, "Degree: N/A") + " (" + educationPrefs.getString(KEY_YEAR, "Year: N/A") + ")");
        txtExperience.setText(experiencePrefs.getString(KEY_COMPANY, "Company: N/A") + "\n" + experiencePrefs.getString(KEY_POSITION, "Position: N/A") + "\n" + experiencePrefs.getString(KEY_START_MONTH, "Duration: N/A") + "," + experiencePrefs.getString(KEY_START_YEAR, "Duration: N/A") + " - " + experiencePrefs.getString(KEY_END_YEAR, "Duration: N/A") );
        txtCertifications.setText(certificationsPrefs.getString(KEY_COURSE_NAME, "N/A")+ "\n" + certificationsPrefs.getString(KEY_COURSE_PROVIDER, "N/A") + "\n" + certificationsPrefs.getString(KEY_SPINNER_YEAR, "N/A"));
        txtReferences.setText(referencesPrefs.getString(KEY_USER_NAME, "N/A")+ "\n" + "(" + referencesPrefs.getString(KEY_DESIGNATION, "Designation: N/A") + ")"+ "\n" + referencesPrefs.getString(KEY_CONTACT, "Contact: N/A"));
    }

    private void shareCV() {
        String cvText = "Name: " + txtName.getText().toString() + "\n" +
                "Email: " + txtEmail.getText().toString() + "\n" +
                "Phone: " + txtPhone.getText().toString() + "\n\n" +
                "Summary: " + txtSummary.getText().toString() + "\n\n" +
                "Education: " + txtEducation.getText().toString() + "\n\n" +
                "Experience: " + txtExperience.getText().toString() + "\n\n" +
                "Certifications: " + txtCertifications.getText().toString() + "\n\n" +
                "References: " + txtReferences.getText().toString();

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, cvText);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }
}