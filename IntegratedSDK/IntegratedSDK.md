# 集成SDK (Integrated SDK)

#### 1.在Android Studio中创建你的工程  (Create your project in Android Studio)

#### 2.获取 **<u>Cameralib.jar</u>**  + **<u>jniLibs ->armeabi-v7a</u>** ，放入项目中 (Get Cameralib.jar + jniLibs ->armeabi-v7a and put it into the project)

#### 3.build.gradle配置 (Build.gradle configuration)

```java
app build.gradle
   defaultConfig {
       ndk {
          abiFilters "armeabi-v7a" //可以适当补填其他 Can be filled in other
         }
      } 
       
```



#### 4.混淆配置 (Confused configuration)

```java
-keepclasseswithmembernames class * { # 保持native方法不被混淆 Keep native methods not to be confused
    native <methods>;
}
#jar包 Jar package
-keep class com.khj.**{ *; }
-keep class com.example.cameralib.BuildConfig{ *; }

#------------------  下方是共性的排除项目  Below are common exclusions        ----------------
# 方法名中含有“JNI”字符的，认定是Java Native Interface方法，自动排除 (If the method name contains "JNI" characters, it is determined to be the Java Native Interface method, which is automatically excluded.)
# 方法名中含有“JRI”字符的，认定是Java Reflection Interface方法，自动排除 (If the method name contains "JRI" characters, it is recognized as the Java Reflection Interface method and is automatically excluded.)

-keepclasseswithmembers class * {
    ... *JNI*(...);
}

-keepclasseswithmembernames class * {
	... *JRI*(...);
}

-keep class **JNI* {*;}
```

#### 5.权限配置  (Rights Profile)

``` xml
   // 在AndroidManifest.xml中加入 Join in AndroidManifest.xml
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.RECORD_AUDIO" />
    <uses-permission android:name="android.permission.MODIFY_AUDIO_SETTINGS" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.PROCESS_OUTGOING_CALLS" />
    <uses-permission android:name="android.permission.BOOT_COMPLETED" />
    <uses-permission android:name="android.permission.CAMERA" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```

