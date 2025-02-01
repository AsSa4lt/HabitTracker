Habit Tracker
===========

Habit Tracker is a Java application that allows users to create and track their habits. Additionally, it includes a mini-game where users can try their luck—if they win, all their habits are marked as completed.
Also you can try to run a program as a Guest user and all data won't be saved.
### Installation


Clone the repository and navigate to the project directory:

git clone https://github.com/AsSa4lt/HabitTracker.git
cd HabitTracker

### Compilation

Use Maven to compile the project:

mvn clean install

### Running the Application

Run the application using:

java -cp out cz.cuni.mff.java.Main

### Getting Started

When launching the application for the first time, you will need to create a new user. If you prefer, you can use the default user:

Username: TestUser1

This user comes with example data to help you get started quickly.

### Features

From the main menu, you can:

1) View all habits – Print a list of all created habits.
2) Filter habits – Apply filters to view only specific habits.
3) Create a new habit – Add a new habit with a custom name and target.
4) Try your luck – If you win, all your habits are marked as completed.
5) Log out – Switch to another user.
6) Save data – While data is saved automatically in most cases, you can manually save it to ensure no progress is lost.
7) Exit the program – Any unsaved changes will be discarded.

### Data Persistence

The Habit Tracker saves user data in files located in the user’s directory. Most actions automatically trigger a save, but you can also manually save your progress from the menu.


