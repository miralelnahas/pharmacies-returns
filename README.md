# Pharmacies Returns

## Installation

You can download latest APK file from the [releases](https://github.com/miralelnahas/pharmacies-returns/releases/tag/release%2F1.0) page

 
## Application Architecture

In Pharmacies Returns App, I followed clean architecture using Model-View-ViewModel (MVVM) pattern

*Clean Architecture Layers:*
- **UI Layer:** Interacts with the user interface.
- **Domain Layer:** Defines user-triggered actions and models
- **Data Layer:** Abstract definition of all the data sources and repositories

*To Acheive this, I divided my app into four modules:*
- **App Module:** Manages app-level dependencies.
- **UI Module:** Handles user interface-related code.
- **Domain Module:** Implements usecases and models
- **Data Module:** Implements the data sources and repositories
- **Common Module:** Handles common models

## Important Technologies Used:
- **Hilt:** Dependency injection framework.
- **Main Navigation:** Handles navigation between different screens.
- **Data binding:** Binds data to views.
- **Paging:** Handles pagination for the Return Requests List.

**Note: The entire app follows the principles of Material Design.**
