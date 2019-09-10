# 报警侦测消息接收

##### 报警侦测数据实体  (JSON格式)

``` java
private Long id;
private String userAccount;      // 用户ID
private String deviceName;       // 设备名称
private long timeStamp;          // 时间戳
private String deviceUid;        // 设备UID
private int type;                // 侦测类型  1:移动侦测  2:声音侦测  4:哭声检测  5:物体检测  6:人脸  7:人形
private String imageName;        // 图片名称
```

###### APP建议集成友盟推送，点击下方链接快速集成

https://developer.umeng.com/docs/119267/cate/118577

