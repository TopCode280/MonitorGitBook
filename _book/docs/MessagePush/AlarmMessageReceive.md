# 报警侦测消息接收 (Alarm detection message reception)

**1\. 上传报警消息 (Upload alarm message)**

###### 服务器接口函数 (Server interface function)

> 接收报警消息 (Receive alarm message)

###### URL

> /pushMsg

编码格式 (Encoding format)

> application/x-www-form-urlencoded

###### HTTP 请求方式 （HTTP request method）

> POST

###### 请求参数 （Request parameter）

| 参数(Parameter) | 是否一定存在 (Whether it must exist) | 类型 (Types of) | 描述 (Description)  |
| :--------- | :----------- | :----- | --------------------------------- |
| deviceUid  | 是 （Yes）     | string | 设备ID (Device ID)  |
| msgType    | 是（Yes）      | int    | 报警类型 (Alarm type) |
| timeStamp  | 是（Yes）      | long   | UTC时间戳 (UTC timestamp) |
| imageName  | 是（Yes）      | string | 报警图片文件名 (Alarm picture file name) |
| imageUrl   | 否（No）     | string | 如果未设置setPictureUrl接口那么该参数为null ，若设置该参数该值则是推送图片后服务器返回的 "data" 图片地址。(If the setPictureUrl interface is not set, then the parameter is null. If the parameter is set, the value is the "data" picture address returned by the server after the picture is pushed.) |
| deviceName | 否（No）       | string | 由camera.setDeviceAlias设置的别名 (Alias set by camera.setDeviceAlias) |
| moduleName | 是（Yes）      | string | 设备的别名或外部传感器的名称 (The alias of the device or the name of the external sensor) |

##### 1. 如果 camera.setPhpSever("http://msg.khjdevice.com:5656"),那么警报消息将发布到 "http://msg.khjdevice.com:5656/pushMsg" (If camera.setPhpSever("http://msg.khjdevice.com:5656") then the alert message will be posted to "http://msg.khjdevice.com:5656/pushMsg")



##### 2. 在旧的固件版本中，deviceName和moduleName可能为null，但在将来的发行版本中，我们将确保设备传递参数正确！(In the old firmware version, deviceName and moduleName may be null, but in future releases, we will ensure that the device passes the parameters correctly )

##### 3.关于msgType，不同的摄像机支持不同类型的警报。（Regarding msgType, different cameras support different types of alerts.）

| 消息类型(msgType) | 描述(comment)             |
| :---------------- | :------------------------ |
| 1                 | motion detection          |
| 2                 | sound detection           |
| 6                 | face detection            |
| 7                 | Human detection           |
| greater than 255  | external sensor detection |

###### 返回 (return)

> Camera忽略来自服务器的返回 (Camera ignores the return from the server)



APP建议集成友盟推送，点击下方链接快速集成 (APP recommends integration with Union Push, click on the link below to quickly integrate)

https://developer.umeng.com/docs/119267/cate/118577 