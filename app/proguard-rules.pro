# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile



# Preserve TypeToken
-keepclassmembers,allowobfuscation class * {
    ** (com.google.gson.reflect.TypeToken);
}

# Keep Gson generic type information
-keepclassmembers class * extends com.google.gson.reflect.TypeToken {
    <fields>;
    <methods>;
}

# Keep specific classes used with TypeToken (e.g., your models or any class that uses TypeToken)
-keep class com.yourpackage.model.** { *; }

# Optionally, add rules for Gson
-keep class com.google.gson.** { *; }

# Don't shrink TypeToken (used for Gson deserialization)
-keep class * implements com.google.gson.reflect.TypeToken







# Retrofit: Keep Retrofit's core classes
-keep class retrofit2.** { *; }

# Keep the Retrofit interface methods
-keepclassmembers class * {
    @retrofit2.http.* <methods>;
}

# GsonConverterFactory: Keep the Gson converter
-keep class retrofit2.converter.gson.GsonConverterFactory { *; }

# OkHttp: Keep OkHttp classes (used by Retrofit)
-keep class okhttp3.** { *; }

# If you're using Moshi, Gson, or any other converter, add the corresponding rules:

# Gson converter (if using Gson with Retrofit)
-keep class com.google.gson.** { *; }

# Moshi converter (if using Moshi with Retrofit)
-keep class com.squareup.moshi.** { *; }

# OkHttp Interceptor (if using custom interceptors)
-keep class okhttp3.Interceptor { *; }

# Keep the generic type information used in Retrofit
-keep class retrofit2.CallAdapterFactory { *; }
-keepclassmembers class * extends retrofit2.CallAdapterFactory { *; }

# Keep TypeToken class used with Gson (if you're using it)
-keep class com.google.gson.reflect.TypeToken { *; }

# If you're using Retrofit with Kotlin's coroutines:
-keepclassmembers class * implements kotlinx.coroutines.Deferred { *; }
