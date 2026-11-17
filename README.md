# KABARAK UNIVERSITY
# Campus Lost and Found
### Android Mobile Programming Project

An Android application designed to help students and staff report, search for, and recover lost items within the campus.

---

## Project Information

| Detail | Description |
|---|---|
| **Project Name** | Campus Lost and Found |
| **Project Type** | Android Mobile Application (Group Project) |
| **Programming Languages** | Kotlin and Java (Two Equivalent Editions) |
| **Development Tools** | Android Studio, Git, and GitHub |
| **Minimum Android Version** | Android 7.0 (API 24) |
| **Data Storage** | Local device storage using SharedPreferences |
| **Internet Requirement** | No internet connection required |
| **Date** | November 2026 |

---

## Table of Contents

1. [About the Project](#1-about-the-project)
2. [Features](#2-features)
3. [How to Use the Application](#3-how-to-use-the-application)
4. [Getting Started: Installation and Setup](#4-getting-started-installation-and-setup)
5. [Project Structure](#5-project-structure)
6. [How the Application Works](#6-how-the-application-works)
7. [Data Model and Storage](#7-data-model-and-storage)
8. [Customising the Application](#8-customising-the-application)
9. [Testing Checklist](#9-testing-checklist)
10. [Teamwork and GitHub Collaboration](#10-teamwork-and-github-collaboration)
11. [Troubleshooting](#11-troubleshooting)
12. [Known Limitations and Future Improvements](#12-known-limitations-and-future-improvements)
13. [Team Members and Credits](#13-team-members-and-credits)

---

## 1. About the Project

Campus Lost and Found is an Android mobile application developed to help students and staff at Kabarak University report lost or found items, browse reported items, and communicate with individuals who have reported them.

The application was developed as part of the **Android Mobile Programming group project** at Kabarak University.

The application operates entirely on the user's Android device. It does not require user registration, an online server, or an internet connection. All submitted reports are stored locally on the device and remain available even after the application is closed and reopened.

### Project Objectives

The main objectives of this project are to:

- Develop a functional Android application using Android widgets, layouts, and event handling.
- Create a user-friendly and visually appealing interface with appropriate colours, icons, and clear feedback.
- Implement basic data management operations, including creating, displaying, searching, filtering, and updating reports.
- Promote teamwork and collaboration through GitHub, with each team member contributing to and explaining a specific component of the application.

### Target Users

The application is designed for students and staff within the university campus.

- **Lost Item Reporting:** Users who have misplaced an item can submit a Lost Item report.
- **Found Item Reporting:** Users who have recovered or picked up an item can submit a Found Item report.
- **Item Searching:** All users can browse and search through the combined list of reported lost and found items to identify possible matches.

---

## 2. Features

The application provides the following features:

| Feature | Description |
|---|---|
| **Home Screen** | Displays the application name, welcome message, and three navigation buttons: Report Lost Item, Report Found Item, and View Items. |
| **Report Form** | Provides a single form for submitting both Lost and Found reports. The form includes item name, description, location, date, contact information, and category. The banner colour and field hints change depending on the selected report type. |
| **Date Picker** | Allows users to select a date using a calendar interface. Future dates cannot be selected. |
| **Input Validation** | Requires all five text fields to be completed before submission. Empty fields display red error messages, and incomplete reports are not saved. |
| **Submission Confirmation** | Displays a confirmation dialog before submitting a report. A success message appears after the report has been saved. |
| **Item Listing** | Displays reported items in individual cards containing the item name, Lost/Found status, category, date, description, location, and contact information. The newest reports appear first. |
| **Search Functionality** | Allows users to search for items by entering part or all of an item name. Search results update as the user types, and searches are not case-sensitive. |
| **Filtering** | Allows users to filter reports by category (Electronics, Documents, Bags, Clothing, Keys, or Other) and status (Lost or Found). Search and filters can be used together. |
| **Item Details** | Allows users to select an item and view all its information in a dialog. |
| **Copy Contact Information** | Allows users to copy the reporter's phone number or email address to the device clipboard with one tap. |
| **Claimed/Returned Status** | Allows users to mark an item as claimed or returned. A green status label is displayed on the corresponding item card. |
| **Local Data Persistence** | Automatically saves reports on the device so they remain available after the application is closed and reopened. |

---

## 3. How to Use the Application

This section explains how users can interact with the application.

### 3.1 Reporting a Lost Item

Follow these steps to report an item that has been lost:

1. Open the application.
2. Select **Report Lost Item** (the red button) from the home screen.
3. Enter the item name and description.
4. Provide the location where the item was lost.
5. Enter your contact information (phone number or email address).
6. Select the date on which the item was lost using the date picker.
7. Choose the appropriate category from the available options.
8. Tap **Submit Report**.
9. Review the information and confirm the submission by selecting **Submit** in the confirmation dialog.

The report will be saved and made available in the items list.

### 3.2 Reporting a Found Item

To report an item that has been found:

1. Open the application.
2. Select **Report Found Item** (the green button).
3. Complete the same form used when reporting a lost item.
4. Enter the location where the item was found and the date it was found.
5. Select the appropriate category.
6. Tap **Submit Report** and confirm the submission.

The application will save the report as a Found Item.

### 3.3 Searching for an Item

To search for a reported item:

1. Open the application.
2. Select **View Items**.
3. Enter part or all of the item name in the search field.
4. Use the **Category** and **Status** drop-down menus to narrow down the results.
5. Select an item from the displayed list to view its complete details and contact information.

### 3.4 Returning a Found Item

To contact the person who reported an item and arrange its return:

1. Open the **View Items** screen.
2. Select the relevant item from the list.
3. View the item details and select **Copy Contact** to copy the reporter's phone number or email address.
4. Contact the reporter outside the application using the copied information.
5. After the item has been handed over, select **Mark as Claimed/Returned**.
6. Confirm the action when prompted.

The item's card will display a **Claimed / Returned** label to indicate that the item has been recovered.

### 3.5 Application Navigation Flow

The application follows two main navigation paths:

**Reporting an Item**

Home Screen → Report Lost/Found Form → Confirmation Dialog → Report Saved → Return to Home Screen

**Viewing and Managing Items**

Home Screen → View Items List → Item Details Dialog → Copy Contact Information or Mark as Claimed/Returned

---

## 4. Getting Started: Installation and Setup

This section provides instructions for setting up, building, and running the application.

### 4.1 System Requirements

Ensure that the following requirements are met before running the project:

| Requirement | Description |
|---|---|
| **Android Studio** | A recent version of Android Studio is required. Giraffe or newer is recommended. |
| **Android SDK** | The Android SDK must be installed through Android Studio. The application supports Android 7.0 (API 24) and above. |
| **Git** | Required when cloning the project from GitHub. |
| **Android Device or Emulator** | The application can run on an Android emulator created through Android Studio or on a physical Android device with USB debugging enabled. |

### 4.2 Option 1: Clone the Project from GitHub

To obtain the project directly from GitHub:

1. Open Android Studio.
2. Navigate to **File → New → Project from Version Control**.
3. Paste the GitHub repository URL.
4. Select an appropriate destination folder.
5. Click **Clone**.
6. If prompted, select **Trust Project**.
7. Allow Android Studio to complete the Gradle synchronisation process.
8. Once synchronisation is complete, click the green **Run** button.
9. Select your preferred Android emulator or connected physical device.

Alternatively, clone the repository using the command line:

```bash
git clone https://github.com/ImanLamarAL/campus-lost-and-found.git
```

### 4.3 Option 2: Build the Project Using the Provided Source Code

If you are working with the provided source code:

1. Open Android Studio.
2. Create a new **Empty Views Activity** project.
3. Set the project name to `CampusLostAndFound`.
4. Set the package name to `com.example.campuslostandfound`.
5. Set the minimum SDK version to **API 24**.
6. Select your preferred programming language: Kotlin or Java.
7. Add the relevant source files from the corresponding Source Code and Setup Guide for your selected language.

### 4.4 Running the Application on a Physical Android Device

To run the application on a physical Android phone:

1. Open the phone's **Settings**.
2. Navigate to **About Phone**.
3. Tap **Build Number** seven times to enable Developer Options.
4. Return to Settings and open **Developer Options**.
5. Enable **USB Debugging**.
6. Connect the phone to your computer using a USB cable.
7. Accept the USB debugging authorisation prompt displayed on the phone.
8. Open Android Studio and select your connected phone from the available devices.
9. Click **Run** to install and launch the application.

**Note:** The application does not require any special permissions to operate.

---

## 5. Project Structure

The project follows the standard Android application directory structure.

### 5.1 Directory Organisation

```text
app/
└── src/
    └── main/
        ├── AndroidManifest.xml
        │
        ├── java/
        │   └── com/
        │       └── example/
        │           └── campuslostandfound/
        │               ├── MainActivity
        │               ├── ReportItemActivity
        │               ├── ViewItemsActivity
        │               ├── ItemAdapter
        │               ├── Item
        │               ├── ItemType
        │               ├── Categories
        │               ├── ItemRepository
        │               └── Utils
        │
        └── res/
            ├── layout/
            │   ├── activity_main.xml
            │   ├── activity_report_item.xml
            │   ├── activity_view_items.xml
            │   └── item_row.xml
            │
            ├── drawable/
            │   └── Icons and badge backgrounds
            │
            └── values/
                ├── colors.xml
                ├── strings.xml
                └── themes.xml
```

### 5.2 Description of Main Source Files

| File/Class | Responsibility |
|---|---|
| **MainActivity** | Manages the home screen and application navigation. |
| **ReportItemActivity** | Handles the Lost and Found reporting form. |
| **ViewItemsActivity** | Manages the item listing, searching, filtering, and item details. |
| **ItemAdapter** | Displays individual item cards in the RecyclerView. |
| **Item** | Defines the data structure for an individual report. |
| **ItemType** | Defines the report types: Lost and Found. |
| **Categories** | Contains the available item categories. |
| **ItemRepository** | Handles local storage, searching, and filtering of reports. |
| **Utils** | Contains utility functions, including keeping content clear of system bars. |

**Programming Language Differences:**

- Kotlin source files use the `.kt` extension.
- Java source files use the `.java` extension.
- In the Kotlin edition, `Item`, `ItemType`, and `Categories` are defined together in `Item.kt`.
- In the Java edition, each public class is maintained in its own separate file.
- The layout files, drawable resources, values, and Android manifest are identical in both editions.

### 5.3 Screens and Their Corresponding Files

| Application Screen | Source File | Layout File |
|---|---|---|
| Home Screen | `MainActivity` | `activity_main.xml` |
| Report an Item | `ReportItemActivity` | `activity_report_item.xml` |
| View Items | `ViewItemsActivity`, `ItemAdapter` | `activity_view_items.xml`, `item_row.xml` |

### 5.4 Android Components Used

The application uses the following Android components:

| Component | Application Usage | Purpose |
|---|---|---|
| **Activity** | All three application screens | Manages individual screens and their interactions. |
| **TextView** | Home screen, item cards, and counters | Displays text and item information. |
| **EditText (TextInputEditText)** | Reporting form and search field | Accepts user input. |
| **Button (MaterialButton)** | Home screen and submission form | Performs user-triggered actions. |
| **Spinner** | Category selection and list filters | Allows users to select categories and report statuses. |
| **RecyclerView** | View Items screen | Efficiently displays the list of reports. |
| **Intent** | Navigation between screens | Transfers users between activities and passes the report type. |
| **AlertDialog** | Submission, item details, and claimed confirmation | Displays information and requests confirmation for actions. |
| **DatePickerDialog** | Reporting form | Allows users to select a date. |
| **SharedPreferences** | ItemRepository | Stores reports locally on the device. |

---

## 6. How the Application Works

The application follows a simple three-part architecture consisting of the user interface, data model, and data repository.

All application screens use the same repository instance to access and manage report information, ensuring consistency across the application.

### 6.1 Main Application Components

| Component | Responsibility |
|---|---|
| **Screens (Activities)** | Display the user interface, respond to user interactions such as taps and typing, and request data from the repository. |
| **Item Model** | Defines the structure and information associated with an individual report. |
| **ItemRepository** | Maintains the list of reports, handles data storage, and performs searching and filtering operations. |
| **ItemAdapter** | Converts individual Item objects into visual cards displayed through the RecyclerView. |

### 6.2 Reporting an Item: Internal Process

When a user submits a Lost or Found report, the following process takes place:

1. The home screen creates an Intent to open the reporting screen and attaches the selected report type (`LOST` or `FOUND`).
2. The reporting screen retrieves the report type and adjusts the banner colour and field hints accordingly. It also populates the category Spinner.
3. When the user selects **Submit**, the application validates all required fields.
4. If any required field is empty, an error message is displayed, and the submission process stops.
5. After the user confirms the submission, the application creates a new Item object with a unique ID based on the current time.
6. The new item is added to the repository.
7. The repository places the new report at the top of the list and saves the complete list to local storage.

### 6.3 Searching and Filtering: Internal Process

Whenever a user enters text in the search field or changes a filter selection, the application refreshes the displayed list.

The list screen calls a single filtering function, which retrieves reports based on the following conditions:

- **Item Name:** The item name must contain the text entered in the search field.
- **Category:** The item's category must match the selected category, unless `All` is selected.
- **Status:** The report type must match the selected status, unless `All` is selected.

The ItemAdapter then displays the matching results.

The application also updates the item counter and displays the **No items found** message when no reports match the selected conditions.

### 6.4 Marking an Item as Claimed or Returned

When a user marks an item as claimed or returned:

1. The repository identifies the relevant item using its unique ID.
2. The item's `claimed` flag is updated to `true`.
3. The updated information is saved to local storage.
4. The item list is refreshed.
5. The corresponding item card displays the **Claimed / Returned** status label.

---

## 7. Data Model and Storage

Each reported item is represented by an `Item` object containing the following fields:

### 7.1 Item Data Structure

| Field | Data Type | Description |
|---|---|---|
| `id` | Long | Unique identifier generated using the time of submission. |
| `name` | Text | Name of the lost or found item. |
| `description` | Text | Description of the item. |
| `location` | Text | Location where the item was lost or found. |
| `date` | Text | Date of the incident, formatted as `dd/mm/yyyy`. |
| `contact` | Text | Phone number or email address of the person reporting the item. |
| `category` | Text | Item category: Electronics, Documents, Bags, Clothing, Keys, or Other. |
| `type` | LOST or FOUND | Indicates whether the item was reported as lost or found. |
| `claimed` | True or False | Indicates whether the item has been marked as claimed or returned. |

### 7.2 How Data Is Stored

The application uses `SharedPreferences` for local data persistence.

The storage process works as follows:

1. The repository maintains the reports in memory while the application is running.
2. Whenever a report is added or updated, the repository converts the complete list into JSON text.
3. The JSON data is saved in SharedPreferences on the device.
4. When the application starts, the repository retrieves the saved JSON text.
5. The retrieved information is converted back into the application's item list.

Because reports are stored locally, each device maintains its own independent collection of reports.

**Important:** Reports created on one device are not automatically available on another device.

### 7.3 Clearing Saved Data During Testing

To remove all saved reports during testing, use either of the following methods:

- Uninstall the application from the device.
- Navigate to **Settings → Apps → Campus Lost and Found → Storage → Clear Data**.

This will remove the locally stored reports and reset the application's saved data.

---

## 8. Customising the Application

The following table identifies where specific application elements can be modified.

| Element to Modify | File or Location |
|---|---|
| **Application Name** | `res/values/strings.xml` — modify the `app_name` value. |
| **Application Colours** | `res/values/colors.xml` — modify the colour definitions, including blue, red for Lost items, and green for Found items. |
| **Application Theme** | `res/values/themes.xml` and `res/values-night/themes.xml`. |
| **Item Categories** | Kotlin: The `Categories` object in `Item.kt`. Java: The list in `Categories.java`. Add or remove category names as required. |
| **Home Screen Welcome Message** | `res/layout/activity_main.xml` — modify the `android:text` attribute of the welcome TextView. |
| **Date Format** | Modify the `String.format` statement in the date picker code within the reporting screen. |
| **Application Icons** | Modify the vector drawable files in `res/drawable/`, including `ic_search`, `ic_check`, `ic_list`, and other related icons. |

**Note:** When modifying the category list, previously saved reports will retain their original category names. To start with a clean set of data, clear the application's stored data.

---

## 9. Testing Checklist

The following checklist should be completed before the class demonstration to verify that the application's main features function as expected.

Record the outcome of each test in the Result column.

| No. | Test Case | Expected Result | Result |
|---|---|---|---|
| 1 | Open the application. | The home screen displays the application name, welcome message, and three navigation buttons. | |
| 2 | Submit a Lost Item report with all fields completed. | A confirmation dialog appears, a success message is displayed, and the item appears in the list with a red LOST badge. | |
| 3 | Submit a Found Item report with all fields completed. | The item appears in the list with a green FOUND badge. | |
| 4 | Submit the reporting form with empty fields. | Red error messages appear on the empty fields, and the report is not saved. | |
| 5 | Attempt to select a future date. | Future dates cannot be selected. | |
| 6 | Search for an item using part of its name and capital letters. | Matching items are displayed, regardless of letter case. | |
| 7 | Apply a category filter followed by a status filter. | Only items matching the selected filters are displayed, and the item counter updates accordingly. | |
| 8 | Search for an item using text that does not match any report. | The application displays the **No items found** message. | |
| 9 | Select an item from the list. | The details dialog displays all item information, including contact details. | |
| 10 | Select Copy Contact. | A message confirms that the contact information has been copied. | |
| 11 | Mark an item as Claimed/Returned. | The corresponding item card displays the **Claimed / Returned** label. | |
| 12 | Completely close and reopen the application. | Previously submitted reports remain available. | |

---

## 10. Teamwork and GitHub Collaboration

The project is maintained collaboratively through GitHub.

One team member initially uploads the project to a GitHub repository. The other team member then obtains the project by cloning the same repository.

To maintain an organised development process and minimise conflicts, each team member works on an individual branch. Completed changes are submitted to the main branch through pull requests.

### 10.1 Recommended Git Workflow

The following commands represent the recommended daily workflow for each team member.

**Step 1: Switch to the Main Branch**

```bash
git checkout main
```

**Step 2: Retrieve the Latest Changes**

```bash
git pull
```

**Step 3: Create a New Feature Branch**

```bash
git checkout -b feature/your-part
```

Replace `your-part` with the name of the component or feature you are working on.

**Step 4: Make Your Changes**

Open the project in Android Studio and implement your assigned changes.

**Step 5: Stage Your Changes**

```bash
git add .
```

**Step 6: Commit Your Changes**

```bash
git commit -m "Describe what you changed"
```

Use a meaningful commit message that explains the changes made.

**Step 7: Push Your Branch to GitHub**

```bash
git push -u origin feature/your-part
```

**Step 8: Create a Pull Request**

Open the repository on GitHub and create a pull request from your feature branch into the `main` branch.

Review and merge the pull request once the changes are ready.

**Step 9: Update the Local Main Branch**

After the pull request has been merged, both team members should run:

```bash
git checkout main
git pull
```

This ensures that everyone has the latest version of the project.

### 10.2 Suggested Division of Team Responsibilities

The following table provides a suggested division of work among team members.

| Component | Main Files |
|---|---|
| **Home Screen and Navigation** | `MainActivity`, `activity_main.xml` |
| **Reporting Form and Validation** | `ReportItemActivity`, `activity_report_item.xml` |
| **Items List** | `ItemAdapter`, `item_row.xml` |
| **Search, Filtering, and Claimed Feature** | `ViewItemsActivity`, `activity_view_items.xml`, `ItemRepository` |
| **Application Design and Appearance** | `colors.xml`, `themes.xml`, drawable files |

For additional step-by-step instructions on GitHub collaboration, refer to the following project guides:

- Simple GitHub Guide
- GitHub Push and Teamwork Guide

---

## 11. Troubleshooting

This section describes common problems that may occur during project setup, development, or execution and provides recommended solutions.

| Problem | Recommended Solution |
|---|---|
| **Gradle synchronisation fails after cloning.** | Allow the initial synchronisation process to finish. If the problem persists, navigate to **File → Sync Project with Gradle Files**. Ensure that the required Android SDK is installed. |
| **Unresolved reference: R** | Check whether the package declaration at the top of the source file is correct. Also inspect the layout XML files for errors. After correcting the issue, select **Build → Rebuild Project**. |
| **Theme.CampusLostAndFound not found.** | The project name or theme name may be different. Ensure that the style name is consistent in both `themes.xml` files and in the manifest's `android:theme` attribute. |
| **The application closes immediately after starting.** | Open Logcat in Android Studio and inspect the first red error message. A missing file or incorrect theme name is a common cause. |
| **Java: cannot find symbol for a class name.** | Check whether the required Java source file exists and whether its filename matches the corresponding class name. |
| **Previously submitted reports have disappeared.** | The application's data may have been cleared, or the application may have been uninstalled and reinstalled. Remember that reports are stored locally on the device. |

---

## 12. Known Limitations and Future Improvements

Although the application provides the essential functionality required for campus lost-and-found management, it has some limitations that could be addressed in future versions.

### 12.1 Known Limitations

The current version has the following limitations:

- **Local Data Storage:** Reports are stored only on the device where they were created. Other students using different devices cannot access those reports.
- **No User Authentication:** The application does not have user accounts. Consequently, anyone using the device can mark any item as claimed or returned.
- **External Communication:** Users must copy the reporter's contact information and contact them separately through a phone call or message. Communication is not handled directly within the application.
- **Limited Search Functionality:** The search feature currently supports searching by item name only.

### 12.2 Proposed Future Improvements

The following improvements could be implemented in future versions to enhance the application's functionality and user experience:

1. **Cloud-Based Database:** Integrate an online database such as Firebase to allow all students to access and share a common list of lost and found reports.
2. **Image Uploads:** Allow users to attach photographs of lost or found items when submitting reports.
3. **Notifications:** Introduce notifications to alert users when a matching found item is reported.
4. **Direct Communication:** Add buttons that allow users to call or email reporters directly from the item details screen.
5. **Room Database Integration:** Consider using a Room database to improve local data management when handling larger numbers of reports.
6. **User Authentication:** Introduce a sign-in system to ensure that only the original reporter can mark an item as claimed or returned.

---

## 13. Team Members and Credits

This project was developed as part of the Android Mobile Programming course at Kabarak University.

The project was collaboratively developed by the following two team members.

### 13.1 Team Members

1. **Chogo Andrew Luceno**
2. **Steven Nderi**

### 13.2 Project Details

| Detail | Information |
|---|---|
| **Institution** | Kabarak University |
| **Course** | Android Mobile Programming |
| **Project** | Campus Lost and Found |
| **GitHub Repository** | [Campus Lost and Found – GitHub](https://github.com/ImanLamarAL/campus-lost-and-found) |
| **Development Tools** | Android Studio, Kotlin, Java, Material Components, Git, and GitHub |

---

**Developed by Chogo Andrew Luceno and Steven Nderi as part of the Android Mobile Programming group project at Kabarak University.**
