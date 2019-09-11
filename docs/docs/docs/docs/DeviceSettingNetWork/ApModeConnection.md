

# AP模式配网

###### 1.设备播完 “请连接的语音” 后会打开一个WIFI热点，热点名称为  **camera_+UID** , **APP**连接时需要获取 WIFI 权限，接着获取 **WIFI** 列表

###### 2.给列表View的item项设置Click事件

``` java
  new Thread(() -> {
                    //设备处于Ap配网下，热点的密码为"12345678"，此时连接上设备热点
                    wiFiUtil.changeToWifi(wifiName, "12345678", WiFiUtil.Data.WIFI_CIPHER_WPA2); 
                    SystemClock.sleep(10000);//确保手机连接到设备热点，不然socket建立会失败，可以让用户手动连接设备热点在建立socket
                    //连上设备热点后，与设备通信，连接失败请手动连接到设备热点
                    if (wiFiUtil.getSSID().contains("camera_")) {
                        try {
                            connectCamera();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ApActivity.this, "手动连接到设备热点", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
     }).start();
```

```java 
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
        //通过服务器查询配网成功的UID或者也可以截取设备热点名称后20位获取uid
        OkGo.<String>post("http://www.khjtecapp.com/smart-camera-ucenter/temp/queryTempDevice")
                .params("account","账号")
                .params("appName","公司名称或者app名称")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        KLog.e(response.body());
                          new Handler().post(() -> Toast.makeText(ApActivity.this, response.body(), Toast.LENGTH_SHORT).show());
                        });
                    }
                });
    }
```

