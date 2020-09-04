# 报警图片推送设置 (Alarm picture push settings)

**1\. 上传报警图片 (Upload alarm picture)**

###### 服务器接口函数 (Server interface function)

> 接收报警图片 (Receive alarm picture)

###### URL

> **setPictureUrl** 设置的第一个参数


编码格式 (Encoding format)

> multipart/form-data

###### HTTP 请求方式 （HTTP request method）

> POST

###### 请求参数 （Request parameter）

| 参数(Parameter) | 是否一定存在 (Whether it must exist) | 类型 (Types of) | 描述 (Description)  |
| :--------- | :----------- | :----- | --------------------------------- |
| imgFile | 是 （Yes）     | multipart/form-data | 图片文件流 (Picture file stream) |
| uid | 是（Yes）      | String | 推送设备UID (Push device UID) |

> ######  1.1.如果 camera.setPictureUrl("http://msg.khjdevice.com:5656/uploadAlarmImage"),那么警报图片将发布到 "http://msg.khjdevice.com:5656/uploadAlarmImage" (If camera.setPictureUrl("http://msg.khjdevice.com:5656/uploadAlarmImage"), then the alert picture will be posted to "http://msg.khjdevice.com:5656/uploadAlarmImage").

###### 返回 (return)

> 对于**setPictureUrl** 应当规范返回格式，因为设备将会处理返回的Json数据 (For **setPictureUrl**, the return format should be standardized, because the device will process the returned Json data).

###### 服务器返回参数 （setPictureUrl Return Parameters）

| 参数(Parameter) | 是否一定存在 (Whether it must exist) | 类型 (Types of) | 描述 (Description)  |
| :--------- | :----------- | :----- | --------------------------------- |
| code | 是 （Yes）     | int | 处理结果代码 1表成功 0表失败 (Processing result code 1 table success 0 table failure) |
| message | 是（Yes）      | String | 可忽略 (Ignorable) |
| data | 是（Yes）      | String | 服务器保存的图片地址,注:需全路径返回给设备(The image address saved by the server, note: the full path must be returned to the device) |

