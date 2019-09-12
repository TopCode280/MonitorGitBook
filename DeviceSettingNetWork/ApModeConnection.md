# AP模式配网

###### 1.设备播完 “请连接的语音” 后会打开一个WIFI热点，热点名称为  **camera_+UID** 密码为 “12345678”，**APP**连接时需要获取 WIFI 权限，接着获取 **WIFI** 列表。

###### 2.确保手机连上设备的WIFI热点

###### 3.建立与设备的TCP连接

``` java
/**
     * 通过TCP连接设备,发送配置信息到设备，TCP可能连接失败，建议增加重试机制
     */
    private int retryTimes=5;
    private void connectCamera(){
        new Thread(() -> {
            KLog.e("尝试连接*************************");
            StringBuilder builder = new StringBuilder();
            TimeZone aDefault = TimeZone.getDefault();
            int rawOffset = aDefault.getRawOffset();
            rawOffset = rawOffset / (1000 * 60);
            String wifiString = new StringBuilder()
                    .append("S=").append(ssid).append(",") //wifi名称
                    .append("P=").append(pwd).append(",") //wifi密码
                    .append("T=").append(1).append(",") //加密方式
                    .append("Z=").append(rawOffset).append(",") //设置设备时区，这个字段可以不设置
                    .append("A=").append("账号").append(",")//用户账号account
                    .append("U=").append("公司名称或者app名称")//公司代号appName
                    .toString();

            try {
                connectByTcp(wifiString);
            } catch (Exception e) {

                e.printStackTrace();
                KLog.e(e.getMessage()+"*"+retryTimes);
                if (retryTimes>0){
                    retryTimes--;
                    connectCamera();
                }
            }
        }).start();
    }
	
```

###### 4.接收TCP回调判断是否连接成功

``` java
 private void connectByTcp(String wifiString) throws IOException {
        Socket socket = null;
        socket = new Socket("192.168.201.1",10000);//设备固定的ip和端口
        socket.setSoTimeout(5000);
        Socket finalSocket = socket;
        new Thread(() -> {
            try {
                finalSocket.getOutputStream().write(wifiString.getBytes());
                finalSocket.getOutputStream().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();

        String s = new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();//成功会返回OK
        KLog.e(s);
        SystemClock.sleep(5000);
    }
```

