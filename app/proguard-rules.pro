# Keep Dagger Hilt generated classes
-keep class dagger.** { *; }
-keep class javax.inject.** { *; }
-keep class com.example.tastebuds.** { *; }  # Keep your app's classes

# Keep Retrofit and Gson classes
-keep class retrofit2.** { *; }
-keep class com.google.gson.** { *; }

# Keep Parcelable classes
-keepclassmembers class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# Keep ViewModel classes
-keep class androidx.lifecycle.ViewModel { *; }

# Keep generic type information for Gson
-keepattributes Signature

# Prevent Gson from stripping generic class info
-keep class com.example.tastebuds.model.** { *; }

# Keep Retrofit models
-keep class com.example.tastebuds.network.** { *; }

# Keep Gson TypeToken
-keep class com.google.gson.reflect.TypeToken { *; }
