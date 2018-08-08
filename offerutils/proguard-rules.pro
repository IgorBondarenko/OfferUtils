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

#startapp
-keep class * extends java.lang.annotation.Annotation { *; }

-keep class com.startapp.** { *; }
-keep class com.truenet.** { *; }

-keepattributes Exceptions, InnerClasses, Signature, Deprecated, SourceFile, LineNumberTable, *Annotation*, EnclosingMethod
-dontwarn android.webkit.JavascriptInterface
-dontwarn com.startapp.**

-dontwarn org.jetbrains.annotations.**

#fyber
# Google Advertising Id
-keep class com.google.android.gms.ads.identifier.** { *; }
-dontwarn com.google.android.gms.**

#adcolony
# For communication with AdColony's WebView
-keepclassmembers class * { @android.webkit.JavascriptInterface <methods>; }

# Keep ADCNative class members unobfuscated
-keepclassmembers class com.adcolony.sdk.ADCNative** { *; }

#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keep class com.google.gson.** { *; }

#vungle
-dontwarn com.vungle.**
-dontnote com.vungle.**
-keep class com.vungle.** { *; }
-keep class javax.inject.*

    # GreenRobot
    -dontwarn de.greenrobot.event.util.**

    # RxJava
    -dontwarn rx.internal.util.unsafe.**
    -keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
        long producerIndex;
        long consumerIndex;
    }
    -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
        rx.internal.util.atomic.LinkedQueueNode producerNode;
    }
    -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
        rx.internal.util.atomic.LinkedQueueNode consumerNode;
    }
    -keep class rx.schedulers.Schedulers { public static <methods>; }
    -keep class rx.schedulers.ImmediateScheduler { public <methods>; }
    -keep class rx.schedulers.TestScheduler { public <methods>; }
    -keep class rx.schedulers.Schedulers { public static ** test(); }

    # MOAT
    -dontwarn com.moat.**
    -keep class com.moat.** { public protected private *; }

    # Retrofit
    -dontwarn okio.**
    -dontwarn retrofit2.Platform$Java8

    # OkHttp3
    -keepattributes Signature
    -keepattributes *Annotation*
    -keep class okhttp3.** { *; }
    -keep interface okhttp3.** { *; }
    -dontwarn okhttp3.**