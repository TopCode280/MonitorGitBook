# 消息推送 (Message push)

### 1.在设置打开报警推送后，设备会将消息推送至 setPhpServer 的地址，服务器需做好接口准备转发消息至APP1。
(After setting the alarm push to be turned on, the device will push the message to the address of setPhpServer, and the server needs to prepare the interface to forward the message to the APP.)

#### 2.设置报警图片的推送地址,将报警图片缓存至远端，在设置setPictureUrl后设备将会把图片和uid信息推送至设置的服务器地址，由服务器处理后将图片存在服务器的地址返回给设备，那么在下一个pushMsg时imageUrl将是这个返回的地址推送消息。

（Set the push address of the alarm picture and cache the alarm picture to the remote end. After setting setPictureUrl, the device will push the picture and uid information to the set server address, and the server will process the picture and return the address of the server to the device. When a pushMsg is used, imageUrl will be the returned address to push the message.）



#### `流程图`

![alarmPushProcess](https://github.com/TopCode280/MonitorGitBook/blob/master/images/alarmPushProcess.png)