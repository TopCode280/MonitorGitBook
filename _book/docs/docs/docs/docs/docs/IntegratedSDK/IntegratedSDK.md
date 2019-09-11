# 集成SDK

#### 1.在Android Studio中创建你的工程

#### 2.找相关工作人员获取 **<u>Cameralib.jar</u>**  + **<u>jniLibs ->armeabi-v7a</u>** ， 若缺少某一文件将出现不可预估的错误！

#### 3.build.gradle配置

```java
app build.gradle
   defaultConfig {
       ndk {
          abiFilters "armeabi-v7a" //可以适当补填其他
         }
      } 
       
```



#### 4.混淆配置

```java
-keepclasseswithmembernames class * { # 保持native方法不被混淆
    native <methods>;
}
#jar包
-keep class com.khj.**{ *; }
-keep class com.example.cameralib.BuildConfig{ *; }

#------------------  下方是共性的排除项目         ----------------
# 方法名中含有“JNI”字符的，认定是Java Native Interface方法，自动排除
# 方法名中含有“JRI”字符的，认定是Java Reflection Interface方法，自动排除

-keepclasseswithmembers class * {
    ... *JNI*(...);
}

-keepclasseswithmembernames class * {
	... *JRI*(...);
}

-keep class **JNI* {*;}
```

