# 二维码配网 (Two-dimensional code distribution network)

###### 1.**在进行二维码配网时需要Android设备连接一个非5G并可连通外网的WIFI。(When performing the two-dimensional code distribution network, the Android device needs to connect to a WIFI that is not 5G and can be connected to the external network.)**

###### 2.**APP端检测是否打开定位权限获取WIFI名称，用户输入WIFI密码，获取到WIFI名称以及密码后保存进入下一步。(The APP detects whether the privilege is enabled to obtain the WIFI name. The user enters the WIFI password, obtains the WIFI name and password, and saves the next step.)**

###### 3.拼接生成二维码需要的字符串 (Stitching the string needed to generate the QR code)

``` java
String wifiString = new StringBuilder()
                .append("S=").append(ssid).append(",")// wifi 名称 (Wifi name)
                .append("P=").append(pwd).append(",") // wifi 密码 (Wifi Password)
                .append("A=").append("15111520684").append(",")//任意字符串或者用户app账号 (Any string or user app account)
                .append("U=").append("abc").append(",")//填一个任意字符串 (Fill in an arbitrary string)
                .append("T=").append(wifiType)
                .toString();
Bitmap mBitmap = CodeUtils.createImage(wifiString, width, width, null);
ivQRcode.setImageBitmap(mBitmap);
```



###### 4.打开组播准备接收设备发出的配网成功消息，收到消息即视为连接成功。（注：设备连接成功后会多次发出组播消息） (The multicast success message sent by the multicast preparation device is opened. If the message is received, the connection is considered successful. (Note: Multicast messages will be sent multiple times after the device is successfully connected))

```java
  String groupHost = "224.0.1.2";  //组播组虚拟IP (Multicast group virtual IP)
  int port = 6008;  //端口 (Port)
  
  String recieveData() {
        String message = "";
        try {
            byte[] data = new byte[512];
            DatagramPacket packet=new DatagramPacket(data, data.length,inetAddress,port);
            multicastSocket.receive(packet); // 通过MulticastSocket实例端口从组播组接收数据 (Receive data from a multicast group through the multicast socket instance port.)
            // 将接受的数据转换成字符串形式 (Convert accepted data to a string)
            message = new String(packet.getData(),0,packet.getLength());
        } catch (Exception e1) {
            System.out.println("Error: " + e1); // 捕捉异常情况 (Capturing anomalies)
            message = "Error: " + e1;
        }
        return message;
    }
```

