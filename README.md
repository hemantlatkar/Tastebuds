# 🍽️ Tastebuds

**Tastebuds** – An application for discovering and exploring popular recipes.

## 🚀 Features
- **Built using Kotlin** and **MVVM Architecture** for scalable and maintainable code
- **Dagger Hilt** for efficient dependency injection
- Fetches data via a **network call** using **Retrofit2**
- **Visually appealing UI** for an intuitive user experience
- Implements **JUnit, Mockito, and Espresso** for unit and UI testing
- Includes **YouTube links** and **web sources** for each recipe
- Displays **recipes, descriptions, and ingredients** in an interactive list format

---

## 🛠 Built With
- **Kotlin**
- **MVVM Architecture**
- **Dagger Hilt** (Dependency Injection)
- **Coroutines** for asynchronous programming
- **LiveData** for reactive UI updates
- **Repository Pattern** for data management
- **Retrofit2** for API calls
- **AndroidX** for modern Android components
- **TestCases** - using JUnit, Mockito, and Espresso
- **ProGuard and R8** for Shrink, obfuscate, and optimize app

---

## 📂 Project Structure

### **App Module**
```sh
App (Module)
│── di             -> NetworkModule.kt
│── model          -> IngredientUIModel.kt, RecipesModel.kt
│── remote         -> RemoteApiService.kt, RemoteDataModel.kt
│── repository     -> RemoteRepository.kt
│── ui
│   ├── adapter    -> DetailScreenAdapter.kt, IngredientsAdapter.kt, RecipeListAdapter.kt
│   ├── screens    -> HomeScreen.kt, RecipeDetailsScreen.kt
│   ├── viewmodel  -> RecipeViewModel.kt
│   └── utils      -> NetworkUtils.kt, Utils.kt
│── utils          -> Constants.kt, Resource.kt
│── MainApplication.kt
```

### **Testing Module**
```sh
│── Android Test
│   ├── ui         -> RecyclerViewTest.kt
│── Test
│   ├── network    -> ApiServiceTest.kt
│   ├── repository -> RemoteRepositoryTest.kt
│   ├── viewmodel  -> RecipeViewModelTest.kt
```

---

## 🛣️ Included

- **Recipe fetching** via network calls
- Created **Recipe Details Screen**
- Set up **MVVM Architecture with Repository Pattern**
- Integrated **Dagger Hilt** for Dependency Injection
- Wrote **unit tests** for ViewModel and Repository
- Added **custom popup messages**
- Improving **error handling and network retry mechanisms**

---

## 📱 SDK Levels Supported
- **Minimum SDK:** 25
- **Target SDK:** 35

---

## 📦 Application Release
- 📥 [Download APK](https://github.com/hemantlatkar/Tastebuds/blob/main/app/release/Tastebuds_Release_1.0.apk) – Latest Application Build

---

## 🎥 Application Demo
- 🎬 [View Demo](https://drive.google.com/file/d/1b5jIQfkqtwELpMtL8YCNQvgDrqOBXkGk/view?usp=sharing) – App Walkthrough

---


