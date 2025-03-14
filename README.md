# CV Builder Application - Android (Java)

The **CV Builder Application** is an Android app developed in **Java** that demonstrates key Android development concepts, including:
- **Multiple Activities & Navigation**
- **Explicit & Implicit Intents**

## 📱 Features

### 1️⃣ Splash Screen with Animations
- Displays a **logo animation** (fade, scale, or translate).
- Auto-navigates to the **Main CV Builder Screen** after **2-3 seconds**.

### 2️⃣ Home Screen (MainActivity)
- Displays **six options** for user data entry:
  - **Profile Picture**
  - **Personal Details**
  - **Summary**
  - **Education**
  - **Experience**
  - **Certifications & References**
- Each option is **clickable** and navigates to its respective **data entry screen** using **Explicit Intents**.

### 3️⃣ Data Entry Screens
- Each screen allows users to enter specific details using:
  - **EditText fields**
  - **RadioButtons**
  - **Spinners**, etc.
- Each screen contains:
  - ✅ **Save Button** - Stores data and navigates back to the **Home Screen**.
  - ❌ **Cancel Button** - Discards changes and returns to the **Home Screen**.

### 4️⃣ Profile Picture Handling
- Allows users to **select a profile picture** from the **Gallery** using **Implicit Intent**.
- Displays the **selected image** in the Profile Picture section on the **Home Screen**.

### 5️⃣ CV Preview (FinalActivity)
- Displays all entered **CV details in a structured format**.
- Provides a **"Share CV"** button to share the CV via:
  - **Email**
  - **WhatsApp**
  - **Other supported apps (Implicit Intent)**

## 🛠️ Technologies Used
- **Java (Android)**
- **XML (UI Layouts)**
- **Android Intents (Explicit & Implicit)**
- **SharedPreferences / SQLite (Data Storage)**
- **Animations (Splash Screen)**

## 📥 Installation & Setup
1. Clone this repository:
   ```sh
   git clone https://github.com/your-username/cv-builder-android.git
