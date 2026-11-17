KABARAK UNIVERSITY
Android Mobile Programming
Campus Lost and Found
README
An Android app for reporting, finding and returning lost items on campus
Project Campus Lost and Found
Type Android mobile application (group project)
Languages Kotlin and Java (two equivalent editions)
Tools Android Studio, Git and GitHub
Minimum Android version Android 7.0 (API 24)
Storage On the device (SharedPreferences), no internet needed
Date November 2026
Campus Lost and Found | README Page 2
Contents
Section Topic
1 About the project
2 Features
3 How to use the app
4 Getting started: install and run
5 Project structure
6 How the app works inside
7 Data model and storage
8 Customising the app
9 Testing checklist
10 Teamwork and GitHub
11 Troubleshooting
12 Known limitations and future improvements
13 Team and credits
Campus Lost and Found | README Page 3
1. About the project
Campus Lost and Found helps students and staff report items they have lost or found on
campus, browse everything that has been reported, and contact the person who reported an
item. It was built as the Android Mobile Programming group project at Kabarak University.
The app works fully on the phone. There is no account to create, no server and no internet
connection to set up. Everything a user reports is saved on the device and is still there after
the app is closed.
Project goals
• Build a functional Android app using widgets, layouts and event handling.
• Design a friendly, easy-to-read interface with colours, icons and clear feedback.
• Manage basic data: create, list, search, filter and update reports.
• Work as a team using GitHub, with each member building and explaining a component.
Who it is for
Anyone on campus. A person who lost something reports it as Lost. A person who picked
something up reports it as Found. Everyone can search the combined list to find a match.
2. Features
Feature Description
Home screen App name, welcome message and three buttons: Report Lost Item, Report Found
Item, View Items.
Report form One form for both Lost and Found reports: item name, description, location, date,
contact information and category. The banner colour and field hints change with
the report type.
Date picker The date is chosen from a calendar. Future dates are blocked.
Input checking All five text fields must be filled. Empty fields show a red error message and
nothing is saved.
Confirmation A dialog asks the user to confirm before submitting, and a message confirms the
report was saved.
Item list Cards showing name, Lost/Found badge, category, date, description, location
and contact. Newest reports first.
Search Type part of an item name to narrow the list while typing. Not case-sensitive.
Filters Filter by category (Electronics, Documents, Bags, Clothing, Keys, Other) and by
status (Lost or Found). Filters work together with the search.
Item details Tap an item to see all its information in a dialog.
Copy contact One tap copies the reporter's contact information to the clipboard.
Claimed / Returned Mark an item as claimed or returned. A green label then appears on its card.
Saved on the
device
Reports remain after closing and reopening the app.
Campus Lost and Found | README Page 4
3. How to use the app
Report a lost item
1. Open the app and tap Report Lost Item (red button).
2. Fill in the item name, description, the place you lost it, and your contact information
(phone or email).
3. Tap the date field and pick the day you lost it.
4. Choose a category from the list.
5. Tap Submit Report, then tap Submit in the confirmation box.
Report a found item
Do the same as above but tap Report Found Item (green button). The location and date
then mean where and when you found it.
Look for an item
1. Tap View Items.
2. Type part of the item name in the search box.
3. Use the Category and Status drop-downs to narrow the list.
4. Tap an item to read the full details and the contact information.
Return an item
1. Open the item from the list.
2. Tap Copy contact to copy the reporter's phone number or email, then call or message
them outside the app.
3. When the item has been handed over, tap Mark as Claimed/Returned and confirm. The
card shows "Claimed / Returned".
App flow
Home screen →
Report Lost /
Found form →
Confirm
dialog →
Saved, back to
Home
Home screen → View Items list →
Item details
dialog →
Copy contact or
mark Claimed
Campus Lost and Found | README Page 5
4. Getting started: install and run
Requirements
Requirement Details
Android Studio A recent version (Giraffe or newer is recommended).
Android SDK Installed through Android Studio. The app supports Android 7.0 (API 24) and
above.
Git Needed only to clone the project from GitHub.
A device to run on An emulator created in Android Studio, or a real phone with USB debugging
turned on.
Option 1: Clone from GitHub
1. In Android Studio choose File, New, Project from Version Control.
2. Paste the repository link, choose an empty folder, and click Clone.
3. Click Trust Project if asked, and wait for the Gradle sync to finish.
4. Press the green Run button and choose your emulator or phone.
Or from the command line:
git clone https://github.com/YOUR-USERNAME/campus-lost-and-found.git
Option 2: Build from the provided source code
Create a new Empty Views Activity project named CampusLostAndFound with the package
com.example.campuslostandfound, minimum SDK API 24, and the language you prefer
(Kotlin or Java). Then add the files from the Source Code and Setup Guide that matches your
language.
Running on a real phone
1. Open Settings, About phone and tap Build number seven times to enable Developer
options.
2. In Developer options, turn on USB debugging.
3. Connect the phone with a USB cable, accept the prompt on the phone, and select the
phone in Android Studio's device list.
4. Press Run.
The app needs no special permissions.
Campus Lost and Found | README Page 6
5. Project structure
app/src/main/
 AndroidManifest.xml
 java/com/example/campuslostandfound/
 MainActivity home screen
 ReportItemActivity report form (Lost and Found)
 ViewItemsActivity list, search, filter, details
 ItemAdapter fills each card in the list
 Item / ItemType / data model, Lost or Found,
 Categories category list
 ItemRepository storage, search and filtering
 Utils keeps content clear of system bars
 res/
 layout/ activity_main, activity_report_item,
 activity_view_items, item_row
 drawable/ icons and the badge background
 values/ colors, strings, themes
Kotlin files end in .kt and Java files in .java. In Kotlin, Item, ItemType and Categories live
together in one file (Item.kt). In Java each public class has its own file. Layouts, drawables,
values and the manifest are identical in both editions.
Screens and files
Screen Code Layout
Home MainActivity activity_main.xml
Report an item ReportItemActivity activity_report_item.xml
View items ViewItemsActivity, ItemAdapter activity_view_items.xml, item_row.xml
Android components used
Component Where Purpose
Activity The three screens Manage each screen
TextView Home, cards, counters Show text and item information
EditText
(TextInputEditText)
Report form, search box Accept user input
Button (MaterialButton) Home, Submit Report Perform actions
Spinner Category in the form, two filters in
the list
Choose a category or status
RecyclerView View items screen Show the list of reports efficiently
Intent Home to the other screens Navigate and pass the report type
AlertDialog Submit, details, claimed Confirm actions and show details
DatePickerDialog Report form Pick the date
SharedPreferences ItemRepository Keep reports on the device
6. How the app works inside
The app follows a simple three-part design: screens that the user sees, a data model that
describes a report, and one repository object that stores and searches the reports. All
screens share the same repository, so they always agree.
Campus Lost and Found | README Page 7
Part Responsibility
Screens (Activities) Show the interface, react to taps and typing, and ask the repository for
data.
Item model Describes one report with its fields.
ItemRepository Holds the list of reports, saves them, and performs search and filtering.
ItemAdapter Turns each Item into a card in the RecyclerView.
Reporting an item
1. Home creates an Intent for the report screen and attaches the type (LOST or FOUND).
2. The report screen reads the type, sets the banner colour and the field hints, and fills the
category Spinner.
3. On Submit, every field is checked. Empty ones show an error and the process stops.
4. After confirmation, a new Item is created with a unique id (the current time) and added to
the repository.
5. The repository puts the item at the top of its list and saves the whole list to local storage.
Searching and filtering
Every time the user types in the search box or changes a Spinner, the list screen calls one
function. That function asks the repository for the items whose name contains the typed text,
whose category matches the chosen category (or "All"), and whose status matches the
chosen status (or "All"). The adapter then shows the result, and the screen updates the item
counter and the "No items found" message.
Marking as claimed
The repository finds the item by its id, sets its claimed flag to true, and saves again. The list
refreshes and the card shows the "Claimed / Returned" label.
7. Data model and storage
Each report is an Item with the fields below.
Field Type Description
id Long Unique identifier (time of submission)
name Text Item name
description Text Description of the item
location Text Where the item was lost or found
date Text Date of the incident, written dd/mm/yyyy
contact Text Phone number or email of the reporter
category Text Electronics, Documents, Bags, Clothing, Keys or Other
type LOST or FOUND Whether the item was lost or found
claimed True or false Becomes true when marked as claimed or returned
Campus Lost and Found | README Page 8
How data is saved
The repository keeps the reports in memory and writes them to SharedPreferences as a JSON
text each time something changes. When the app starts, the repository reads that text back
into the list. Because the data lives on the phone, each phone has its own list of reports.
To clear all saved reports during testing, uninstall the app or use Settings, Apps, Campus
Lost and Found, Storage, Clear data on the phone.
8. Customising the app
I want to change... Where to do it
The app name shown under the
icon
res/values/strings.xml, the app_name value
The colours (blue, red for lost,
green for found)
res/values/colors.xml
The theme res/values/themes.xml (and values-night/themes.xml)
The list of categories Kotlin: the Categories object in Item.kt. Java: the LIST in
Categories.java. Add or remove names in the list.
The welcome text on the home
screen
res/layout/activity_main.xml, the android:text of the welcome
TextView
The date format The String.format line in the report screen's date picker code
The icons The vector files in res/drawable/ (ic_search, ic_check, ic_list and
others)
After changing a category list, existing saved reports keep their old category names. Clear the app data
if you want a fresh start.
9. Testing checklist
Use this list before the class demonstration and write the result in the last column.
No. Test Expected result Result
1 Open the app Home screen with name, welcome message and
three buttons
2 Report a lost item with all
fields filled
Confirm dialog, success message, item appears
with a red LOST badge
3 Report a found item with all
fields filled
Item appears with a green FOUND badge
4 Submit the form with empty
fields
Red error messages on the empty fields; nothing is
saved
5 Pick a date in the future Future dates cannot be selected
6 Search by part of a name, in
capital letters
Matching items appear (search ignores capitals)
7 Filter by a category, then by
status
Only matching items are shown; the counter
updates
8 Search for text that matches
nothing
"No items found" message
Campus Lost and Found | README Page 9
No. Test Expected result Result
9 Tap an item Details dialog shows every field including contact
10 Tap Copy contact A message says the contact was copied
11 Mark an item as
Claimed/Returned
The card shows the Claimed / Returned label
12 Close the app completely and
reopen it
All reports are still there
10. Teamwork and GitHub
The group shares the code on GitHub. One person uploads the project first, and the other
joins by cloning that repository. After that, each member works on their own branch and joins
the work to the main branch through a pull request.
Daily routine
git checkout main
git pull
git checkout -b feature/your-part
# ... work in Android Studio ...
git add .
git commit -m "Describe what you changed"
git push -u origin feature/your-part
Then open a pull request on GitHub and merge it. Both members run git checkout main
and git pull afterwards.
Suggested split of work
Component Main files
Home screen and navigation MainActivity, activity_main.xml
Report form and validation ReportItemActivity, activity_report_item.xml
Items list ItemAdapter, item_row.xml
Search, filter and claimed feature ViewItemsActivity, activity_view_items.xml, ItemRepository
Look and feel colors.xml, themes.xml, drawable files
For the step-by-step instructions, see the Simple GitHub Guide and the GitHub Push and Teamwork
Guide.
Campus Lost and Found | README Page 10
11. Troubleshooting
Problem What to do
Gradle sync fails after cloning Wait for the sync to finish, then choose File, Sync Project with
Gradle Files. Make sure the Android SDK is installed.
Unresolved reference: R The package line at the top of a code file is wrong, or a layout file
has an XML error. Fix it, then Build, Rebuild Project.
Theme.CampusLostAndFound not
found
The project name was different. Rename the style in both
themes.xml files and in the manifest's android:theme value.
The app closes immediately on
start
Open Logcat in Android Studio and read the first red error line. A
missing file or a theme name mismatch is the usual cause.
Java: cannot find symbol on a
class name
A Java file is missing, or the file name does not match the class
name.
Reports disappeared The app data was cleared, or the app was uninstalled and
reinstalled. Data is stored only on the device.
12. Known limitations and future improvements
Known limitations
• Reports are stored only on the phone that created them. Other students' phones cannot
see them.
• There are no user accounts, so anyone using the phone can mark any item as claimed.
• Contacting a reporter is done outside the app (copy the contact, then call or message).
• Search works on item names only.
Ideas for the future
• An online database such as Firebase, so all students share one list of reports.
• Photos of items attached to reports.
• Notifications when a matching found item is reported.
• Buttons to call or email the reporter directly from the details screen.
• A Room database for larger numbers of reports.
• Sign-in, so only the reporter can mark an item as claimed.
13. Team and credits
Write each member's name, registration number and the component they built. Each
member explains their component during the class demonstration.
Member name Reg. number Component built
Course: Android Mobile Programming, Kabarak University
Repository: ______________________________________________
Built with: Android Studio, Kotlin, Java, Material Components, Git and GitHub.
