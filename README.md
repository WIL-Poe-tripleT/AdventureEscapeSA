# Adventure Escape SA

**Explore. Experience. Escape.**

Adventure Escape SA is an Android mobile application designed to introduce users to outdoor adventures and activities in South Africa. The application provides information about available adventures, displays activity details, allows users to select activities, and calculates estimated booking fees.

The application was developed as part of a group mobile application development project using Android Studio, Kotlin, and Jetpack Compose.

## Table of Contents

* [Project Overview](#project-overview)
* [Project Objectives](#project-objectives)
* [Features](#features)
* [Available Adventures](#available-adventures)
* [Fee Calculation](#fee-calculation)
* [Technologies Used](#technologies-used)
* [Application Structure](#application-structure)
* [Installation and Setup](#installation-and-setup)
* [Testing](#testing)
* [Known Limitations](#known-limitations)
* [Group Members](#group-members)
* [Repository](#repository)

## Project Overview

Adventure Escape SA aims to provide a simple and user-friendly mobile experience for people interested in outdoor activities.

The application brings adventure information and estimated fee calculations together in one place. Its interface uses consistent colours, clear headings, activity cards, and straightforward navigation to make the main features easy to access.

Users can explore activities, view individual adventure details, calculate estimated costs for adults and children, and access the available contact information.

## Project Objectives

The objectives of the project are to:

* Develop a functional Android mobile application.
* Provide an intuitive interface suitable for mobile devices.
* Display adventure activities and their relevant information.
* Allow users to select activities and calculate estimated booking costs.
* Apply discount and VAT calculations to the estimated fees.
* Validate user input and provide appropriate feedback.
* Implement navigation between the application's main screens.
* Apply Kotlin and Jetpack Compose in the development of an Android application.
* Collaborate through a shared GitHub repository.

## Features

### 1. Welcome Screen

* Displays the Adventure Escape SA branding and logo.
* Introduces the application with the tagline "Explore. Experience. Escape."
* Provides an Explore Adventures button to enter the application.

### 2. Home Screen

* Introduces the available outdoor experiences.
* Displays featured and popular adventures.
* Provides navigation to the application's main features.

### 3. About Us

* Introduces the purpose of Adventure Escape SA.
* Provides information about the application's focus on outdoor activities and adventure experiences.

### 4. Adventure Overview

* Displays the available adventure categories.
* Allows users to filter activities by category.
* Provides access to individual adventure information.

Available filters include:

* All
* Hiking
* Water
* Outdoor

### 5. Individual Adventure Details

Each adventure detail screen presents relevant activity information, including:

* Adventure name
* Description
* Adult and child prices
* Estimated duration
* Difficulty level
* Favourite selection
* Booking navigation

### 6. Calculate Fees

The fee calculation screen allows users to:

* Enter customer details, including name and email address.
* Select one or more adventures using checkboxes.
* Specify the number of adults and children.
* Calculate estimated booking fees.
* Apply the group discount when the eligibility requirement is met.
* Calculate illustrative VAT.
* Display an invoice with the calculated amounts.
* Validate required information and provide feedback when input is invalid.

### 7. Contact Us

The Contact Us screen provides:

* Phone contact information
* Email contact information
* A physical address
* A map-opening button
* Instagram and Facebook buttons
* A contact form with input validation and confirmation feedback

### 8. Navigation

The application includes bottom navigation for the following sections:

* Home
* About
* Overview
* Fees
* Contact

This allows users to move between the main application screens.

## Available Adventures

The application currently includes the following activities and illustrative prices.

| Adventure           | Adult Price | Child Price | Duration  | Difficulty |
| ------------------- | ----------: | ----------: | --------- | ---------- |
| Mountain Hiking     |        R500 |        R250 | 3–4 hours | Moderate   |
| Water Adventures    |        R650 |        R325 | 2–3 hours | Moderate   |
| Outdoor Exploration |        R450 |        R225 | 2 hours   | Easy       |

These prices are used by the application for estimated fee calculations. They should not be interpreted as verified prices offered by a real booking provider.

## Fee Calculation

The Calculate Fees feature uses the selected activities and participant quantities to calculate an estimated booking amount.

### Calculation process

1. Calculate the cost of each selected adventure using the applicable adult and child prices.
2. Add the costs of the selected adventures to obtain the subtotal.
3. Apply a 10% group discount when the total number of participants is at least 10.
4. Calculate illustrative VAT at 15% on the amount remaining after the discount.
5. Add VAT to the discounted amount to obtain the final estimated total.
6. Display the calculation in an invoice.

### Example

For Mountain Hiking with five adults and five children:

| Description             |        Amount |
| ----------------------- | ------------: |
| Five adults             |     R2,500.00 |
| Five children           |     R1,250.00 |
| Subtotal                |     R3,750.00 |
| Group discount (10%)    |      -R375.00 |
| Subtotal after discount |     R3,375.00 |
| Illustrative VAT (15%)  |       R506.25 |
| **Estimated total**     | **R3,881.25** |

The fee calculation demonstrates the application's pricing logic. The VAT rate and treatment are illustrative assumptions for the project and are not a statement of the tax treatment of a real booking service.

## Technologies Used

The application uses the following technologies and development tools:

* **Kotlin** — the primary programming language.
* **Android Studio** — the Android development environment.
* **Jetpack Compose** — the toolkit used to build the user interface.
* **Material 3** — components and design elements for the interface.
* **Gradle** — project build and dependency management.
* **Git** — version control.
* **GitHub** — source code hosting and group collaboration.
* **Android Emulator** — application execution and testing during development.

## Application Structure

The project follows the standard Android application structure.

```text
AdventureEscapeSA/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── assets/
│   │   │   ├── java/
│   │   │   │   └── com/example/adventureescapesa/
│   │   │   │       ├── MainActivity.kt
│   │   │   │       └── ui/theme/
│   │   │   └── res/
│   │   ├── androidTest/
│   │   └── test/
│   └── build.gradle.kts
├── gradle/
├── build.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
├── settings.gradle.kts
└── README.md
```

The structure above highlights the main project files and directories. Some generated or configuration files may be omitted from this overview.

## Installation and Setup

### Prerequisites

To open and run the application, you need:

* Android Studio
* A compatible Java Development Kit configured through Android Studio
* The Android SDK required by the project
* An Android emulator or compatible Android device
* Git, if you want to clone the repository

### Clone the repository

Open a terminal and run:

```bash
git clone https://github.com/WIL-Poe-tripleT/AdventureEscapeSA.git
```

Open Android Studio and select **Open**.

Navigate to the cloned `AdventureEscapeSA` folder and open the project.

### Sync and run

1. Allow Android Studio to sync the Gradle project.
2. Install any SDK components requested by Android Studio.
3. Select an Android emulator or connected device.
4. Wait for the project to finish building.
5. Click **Run** to launch the application.

The project should be built using the Gradle configuration included in the repository.

## Testing

The application was tested on an Android emulator during development.

The reported functional checks included:

* Opening the application and navigating between screens.
* Viewing the Home and About Us screens.
* Filtering adventures by category.
* Opening individual adventure details.
* Selecting and deselecting a favourite adventure.
* Navigating from an adventure to the fee calculation screen.
* Selecting adventures and entering adult and child quantities.
* Calculating subtotals, discounts, VAT, and final totals.
* Displaying the generated invoice.
* Checking required form fields and input validation.
* Opening the available map and social media destinations.
* Submitting the contact form and displaying confirmation feedback.

The fee calculation was checked using sample participant quantities and the displayed totals matched the application's calculation rules.

These results describe the functional checks performed during development. They do not constitute a formal test report for every device, Android version, or possible input.

## Known Limitations

* The adventure prices are illustrative project values and have not been verified against a real booking provider.
* The application calculates estimated fees; it does not process payments.
* The contact form displays confirmation feedback but does not send messages to a backend service.
* The contact details are demonstration information and should be verified before any real-world use.
* The social media buttons open general Instagram and Facebook destinations rather than verified official Adventure Escape SA profiles.
* The map button opens a map destination and does not represent a confirmed business location.
* The application does not currently provide a complete online booking and reservation service.

## Group Members

This is a collaborative group project.

| Student Number | Group Member                 |
| -------------- | ---------------------------- |
| ST10517860     | Owam Mtebeni |
| ST10533474     | Nelly Mgijima |
| ST10457180     | Rhiannon Theys |

## Repository

**GitHub repository:**
https://github.com/WIL-Poe-tripleT/AdventureEscapeSA

The repository contains the Android application source code and project configuration files required to open the project in Android Studio.

## Project Status

The core mobile application screens and reported functional checks have been completed. The repository will continue to reflect any further improvements, testing, documentation, and corrections made by the group.
