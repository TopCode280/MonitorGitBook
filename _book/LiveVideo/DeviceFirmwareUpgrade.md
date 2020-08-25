# 设备固件升级 (Device firmware upgrade)

1. 更新设备固件 ，指定固件下载地址通知设备更新固件(Update the device firmware, specify the firmware download address to notify the device to update the firmware)

```java
String url = "127.0.0.1:aaa.img"
Camera.notifyUpgradeUrl(url, new P2PCallback<String>() {
            @Override
            public void onSuccess(String s) {   // 升级成功
         
            }

            @Override
            public void onFailure(int i, String s) { // 升级失败
            
            }
        });
```

​	

