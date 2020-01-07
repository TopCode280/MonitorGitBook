# 获取设备信息 (Device Info)

**获取设备Mac地址 局域网IP (Get Device Mac LAN IP)**

```java
 //i 值可以忽略 
//String类型参数 MAC 对应设备MAC地址
//String类型参数 IP 对应局域网IP地址
//需要子线程中调用
Camera.getMacIp((i, MAC, IP) -> {
})
```

**获取设备类型 (Get Device Type)**

```java
Camera.getDevcieType() // 返回 int 值为设备类型，可以在UI线程中直接调用
```

##### 获取设备SD卡信息  Get device information

```java
//获取设备信息 get the camera device information
// sdcardTotal SD卡总容量/MB
// sdcardFree  SD卡剩余容量/MB
// v2 v3 v4    固件版本
// fileSystem  SD卡文件系统格式，建议检测是否为"exfat",若不是则提示格式化SD卡,避免SD卡录像出现的断录等问题
mCamera.queryDeviceInfo(new Camera.deviceInfoCallback() {
                            @Override
                            public void deviceInfo(int sdcardTotal, int sdcardFree, byte v1, byte v2, byte v3, byte v4, String model, String vendor) {
                                String version = builder.append(v2).append(".").append(v3).append(".").append(v4).toString();//固件版本
                            }
                        });
```

##### 