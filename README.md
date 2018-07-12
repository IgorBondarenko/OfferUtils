# OfferUtils

**How to use**

Main dependency:

```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
   }
}

dependencies {
  implementation 'com.github.IgorBondarenko:OfferUtils:1.0.9'
}
```

Also don't forget to add this dependecies:

```
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
