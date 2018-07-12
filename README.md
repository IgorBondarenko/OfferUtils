# OfferUtils

**How to use**

Main dependency:

[![](https://jitpack.io/v/IgorBondarenko/OfferUtils.svg)](https://jitpack.io/#IgorBondarenko/OfferUtils)

Also add this to your project gradle:

```
android {

  packagingOptions {
    exclude 'META-INF/main.kotlin_module'
  }
  
}

dependencies {
  //coroutines
  implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:0.23.3'
  implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:0.23.3'

  //string generator
  implementation 'commons-lang:commons-lang:2.6'

  //hawk
  implementation 'com.orhanobut:hawk:2.0.1'
}
```
