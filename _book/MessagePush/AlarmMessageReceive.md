# 报警侦测消息接收

**1\. 上传报警消息**

###### 服务器接口函数 

> 接收报警消息

###### URL

> /pushMsg

编码格式

> application/x-www-form-urlencoded

###### HTTP 请求方式

> POST

###### request parameters

| parameter  | required | type   | comment                                            |
| :--------- | :------- | :----- | -------------------------------------------------- |
| deviceUid  | ture     | string | Device id                                          |
| msgType    | true     | int    | Alarm type                                         |
| timeStamp  | true     | long   | The UTC timestamp                                  |
| imageName  | true     | string | Absolute  path of alarm picture on device sd card  |
| imageUrl   | false    | string | Deprecated,always null                             |
| deviceName | false    | string | The alias  which set by camera.setDeviceAlias      |
| moduleName | true     | string | The alias of device or the name of external sensor |

##### 1. 如果 camera.setPhpSever("http://msg.khjdevice.com:5656"),那么警报消息将发布到 "http://msg.khjdevice.com:5656/pushMsg"



##### 2. 在旧的固件版本中，deviceName和moduleName可能为null，但在将来的发行版本中，我们将确保设备传递参数正确！

##### 3.关于msgType，不同的摄像机支持不同类型的警报。

| msgType          | comment                   |
| :--------------- | :------------------------ |
| 1                | motion detection          |
| 2                | sound detection           |
| 6                | face detection            |
| 7                | Human detection           |
| greater than 255 | external sensor detection |

###### 返回

> Camera忽略来自服务器的返回



APP建议集成友盟推送，点击下方链接快速集成

https://developer.umeng.com/docs/119267/cate/118577