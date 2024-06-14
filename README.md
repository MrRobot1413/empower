# Beneficiary Information App

This Android application displays a list of beneficiaries and their detailed information. It uses a JSON file for data and builds the UI programmatically without XML layouts.

## Features

- Display a list of beneficiaries.
- Show detailed information about a selected beneficiary.
- Format and display date of birth in "MM/dd/yyyy" format.

## Requirements

- No third-party dependencies.
- Programmatic UI without XML layouts.
- JSON parsing using `JSONObject` and `JSONArray`.

## Installation

1. Clone the repository.
2. Open the project in Android Studio.
3. Sync the project with Gradle.
4. Run the app on an emulator or physical device.

## Implementation

### MainActivity

`MainActivity` displays the list of beneficiaries using a `ScrollView` and `LinearLayout`.

### BeneficiaryDetailActivity

`BeneficiaryDetailActivity` displays detailed information about a selected beneficiary using a `LinearLayout`.

### ViewModel

`MainViewModel` handles fetching and storing the list of beneficiaries using `LiveData` and `MutableLiveData`.

### Data Classes

- `Beneficiary`: Represents a beneficiary with details such as name, designation, type, SSN, date of birth, phone number, and address.
- `BeneficiaryAddress`: Represents the address of a beneficiary.

### DataProvider

`DataProvider` handles reading the JSON file from the `raw` resource and parsing it into a list of `Beneficiary` objects.
