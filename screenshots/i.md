My Calory Tracker
A comprehensive Android application built with Jetpack Compose for tracking daily caloric intake and nutritional goals. This app leverages a multi-module architecture and the Open Food Facts API to provide a robust and scalable user experience. It follows modern Android development practices, including clean architecture and dependency injection.

📱 Features
Onboarding Flow: A complete user setup to gather personalized information for tailored results.

Precise Calorie Calculation: Calculates the exact daily calories you need based on your age, gender, height, weight, and activity level.

Comprehensive Meal Tracking: Track your daily food intake across four meal categories: Breakfast, Lunch, Dinner, and Snack.

Food Search with Localized Results: Search and add foods to your meals. You can change the API country to find products from your local market.

Nutritional Goals: Set and monitor your daily nutritional targets to stay on track.

Responsive UI: A beautiful, modern interface built entirely with Jetpack Compose for a seamless experience.

🏗️ Architecture
The application is built using Clean Architecture principles and a multi-module structure to separate concerns and improve maintainability.

App Module: The main application module, responsible for dependency injection and navigation.

Core Module: Contains shared utilities, domain models, and other common functionality.

Core UI Module: Includes shared UI components, themes, and styling.

Onboarding Module: Manages the user onboarding flow and setup screens.

Tracker Module: Contains the core functionality for calorie and nutrient tracking.

Key Technologies
Jetpack Compose: For building a modern, declarative UI.

Open Food Facts API: For sourcing an extensive database of food products.

Dagger Hilt: The dependency injection framework used throughout the app.

Navigation Component: For handling type-safe navigation between screens.

SharedPreferences: For local data persistence of user preferences.

Material Design: To implement Google's design system.

📋 Application Flow
1. Onboarding Process
The app guides new users through a comprehensive setup process to personalize their experience:


Welcome screen introducing users to the app


Gender selection for personalized calculations


Age input for metabolic calculations


Height measurement input


Current weight input


Activity level selection for TDEE calculation


Weight goal selection (lose, maintain, gain)


Final nutritional targets setup

2. Main Tracking Interface
After onboarding, users can access the main tracking features:


Main dashboard showing daily progress and meal breakdown


Food search interface for adding items to meals

🔧 Technical Implementation
Navigation Structure
The app uses a single-activity architecture with Compose Navigation:

// Route definitions
object Route {
    // Onboarding routes
    const val WELCOME = "welcome"
    const val GENDER = "gender"
    const val AGE = "age"
    const val HEIGHT = "height"
    const val WEIGHT = "weight"
    const val ACTIVITY = "activity"
    const val GOAL = "goal"
    const val NUTRIENT_GOAL = "nutrient_goal"

    // Main app routes
    const val TRACKER_OVERVIEW = "tracker_overview"
    const val SEARCH = "search"
}

🚀 Getting Started
Prerequisites
Android Studio Arctic Fox or newer

Kotlin 1.5+

Android SDK 21+

Gradle 7.0+

Installation
Clone the repository

git clone [repository-url]

Open the project in Android Studio

Build and run the application

./gradlew build

Built with ❤️ using Jetpack Compose and modern Android development practices