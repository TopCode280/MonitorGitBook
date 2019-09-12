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

###### 请求参数

| 参数       | 是否一定存在 | 类型   | 描述                              |
| :--------- | :----------- | :----- | --------------------------------- |
| deviceUid  | 是           | string | Device id                         |
| msgType    | 是           | int    | 报警类型                          |
| timeStamp  | 是           | long   | The UTC timestamp                 |
| imageName  | 是           | string | 报警图片文件名                    |
| imageUrl   | 否           | string | 不推荐使用，始终为null            |
| deviceName | 否           | string | 由camera.setDeviceAlias设置的别名 |
| moduleName | 是           | string | 设备的别名或外部传感器的名称      |

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