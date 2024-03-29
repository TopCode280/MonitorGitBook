



# 设备设置 (Device settings)

##### 设备云台转动  Equipment pan/tilt

```java
//云台转动 参数1方向，参数2一次转动多少步  rotate the camera
mCamera.setPtz(AVIOCTRL_PTZ_RIGHT, 30);
mCamera.setPtz(AVIOCTRL_PTZ_LEFT, 30);
mCamera.setPtz(AVIOCTRL_PTZ_UP, 30);
mCamera.setPtz(AVIOCTRL_PTZ_DOWN, 30);
```

##### 网络设置  Network settings

```java
 //设备周围wifi信号
  mCamera.listWifiAp(new Camera.listWifiApCallback() {
           @Override
        public void listWifiAp(Camera.wifiAp[] wifiAps) {
           }
     });
  //切换wifi和热点模式
  mCamera.switchingAp(true, new Camera.successCallback() {
          @Override
           public void success(boolean b) {
          }
     });
```

##### 画面翻转  Screen flip

```java
 mCamera.getFlipping(new Camera.successCallback() {
                            @Override
                            public void success(boolean b) {

                            }
                        });
 //b = false 不翻转 (Do not flip)，true 翻转 (Flip)
 mCamera.setFlipping(1, new Camera.successCallback() {
        @Override
        public void success(boolean b) {

        }
});
```

##### 录像设置  Recording settings

```java
 //查询当前sd录像的模式 (Query the mode of the current sd video)
// 0关闭录像  record off
 // 1连续录像    record continuous
// 2定时计划    schedule record
// 3报警录像    record when alarm
 mCamera.getVideoRecordType(new Camera.successCallbackI() {
        @Override
           public void success(int i) {
            }
         });

//设置SD卡录像模式 set record mode
  mCamera.setVideoRecordType(1, new Camera.successCallback() {
          @Override
          public void success(boolean b) {
               }
         });

//获取定时录像的时间段 Get the time period of the scheduled recording
mCamera.getTimedRecordVideoTask(new Camera.getTimedCameraTaskCallback() {
         @Override
         public void timedCameraTask(String s) {
                  }
           });
mCamera.addTimedCameraTask("", new Camera.successCallback() {
         @Override
           public void success(boolean b) {
              }
          });
```

##### 6.设备开关 Device Switch

```java
  //查询设备开关状态 (Query device switch status)
  mCamera.getForceOpenCamera(new Camera.successCallbackI() {
              @Override
                public void success(int i) {
                   // i==1 代表开 (Representative)
                  }
               });
  //boolean switch   true 开 (Open) false 关 (Off)
  mCamera.forceOpenCamera(switch, new Camera.successCallback() {
               @Override
                  public void success(boolean b) {

                 }
               });
```

##### 设备音量设置  Device volume setting

``` java
//声音设置 设备音量大小0-100  get camera sound volume
  mCamera.getDeviceVolume(new Camera.successCallbackI() {
              @Override
                public void success(int i) {

                }
              });
   mCamera.setDeviceVolume(50, new Camera.successCallback() {
               @Override
                  public void success(boolean b) {
                    }
              });
```

##### 设置时区  Set time zone

```java
 //设置时区set the timezone
    TimeZone aDefault = TimeZone.getDefault();
           int rawOffset = aDefault.getRawOffset();
            rawOffset = rawOffset / (1000 * 60);//转为分钟，例如北京为+8时区，设置的时区为8*60=480  convert to minute
              mCamera.setTimezone(rawOffset, new Camera.successCallback() {
                      @Override
                      public void success(boolean b) {

                      }
                });
```

##### 移动侦测和声音报警侦测  motion detection and sound detection

```java
                      //查询移动侦测的开关状态 Query the switch status of motion detection
                        mCamera.getAlarmSwitch(new Camera.successCallback() {
                            @Override
                            public void success(boolean b) {

                            }
                        });
                        mCamera.setAlarmSwitch(true, new Camera.successCallback() {
                            @Override
                            public void success(boolean b) {

                            }
                        });
                        //查询移动侦测灵敏度，等级1-5 5灵敏度最高 Query motion detection sensitivity, level 1-5 5 highest sensitivity
                        mCamera.getMotionDetect(new Camera.successCallbackI() {
                            @Override
                            public void success(int i) {

                            }
                        });
                        mCamera.setMotionDetect(3, new Camera.successCallback() {
                            @Override
                            public void success(boolean b) {

                            }
                        });
                        //查询声音侦测报警开关 Query sound detection alarm switch
                        mCamera.geteSoundAlarm(new Camera.successCallback() {
                            @Override
                            public void success(boolean b) {

                            }
                        }); 
                        //开关声音侦测报警 Switch sound detection alarm
                        mCamera.setSoundAlarm(true, new Camera.successCallback() {
                            @Override
                            public void success(boolean b) {

                            }
                        });
                        //设备侦测报警后，设备端是否发出报警声音 Whether the device sends an alarm sound after the device detects the alarm.
                        mCamera.getAlarmVolume(new Camera.successCallback() {
                            @Override
                            public void success(boolean b) {

                            }
                        });
                        //设置报警时设备是否发出报警声音 Whether the device sounds an alarm when setting an alarm
                        mCamera.setAlarmVolume(true, new Camera.successCallback() {
                            @Override
                            public void success(boolean b) {

                            }
                        });
```

##### 格式化SD卡 Format SD card

```java
// 谨慎操作 Exercise cautiously
mCamera.formatSdcard(new Camera.successCallbackI() {
                    @Override
                      public void success(int issuccess) {
                         Log.d(MainActivity.TAG, "format sdcard: " + issuccess);
                        }
                   });
```

##### 设备注册广播回调  Device Broadcast

```java
 /**
                         * 设备注册广播回调，比如调用camere.forceOpenCamera成功后，设备会向所有注册该回调的 The device registers the broadcast callback. For example, after calling camere.forceOpenCamera successfully, the device will register the callback to all.
                         * 设备，发送广播，i代表时间类型，s包含时间的内容参数 Device, send broadcast, i represents time type, s contains time content parameters
                         */
  mCamera.registerActivePush2(new Camera.activePushCallback() {
                            @Override
                            public void activePush(int i, String s) {
                                switch (i) {
                                    case 0://关闭摄像头 Turn off the camera
                                        KLog.d("关闭摄像头");
                                        break;
                                    case 1://打开摄像头 Open the camera
                                        break;
                                    case 2://设备端开始录制视频 The device starts recording video
                                        KLog.d("设备端开始录制视频");
                                        break;
                                    case 3://设备端停止录制视频 The device stops recording video
                                        KLog.d("设备端停止录制视频");
                                        break;
                                    case 4://当前观看视频的人数 The number of people currently watching the video
                                        int audience = Integer.parseInt(s);
                                        break;
                                }
                            }
                        });
```

##### 搜索局域网设备 Search for LAN devices

```java
 /**
                         * 搜索局域网设备 Search for LAN devices
                         */
                        Camera.searchDevice(new Camera.searchDeviceInfoCallback() {
                            @Override
                            public void searchDeviceInfo(Camera.searchDeviceInfo[] info) {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (info != null) {
                                            Log.d(MainActivity.TAG, "info length: " + info.length);
                                            Toast.makeText(MainActivity.this, "局域网没有搜索到设备数量 (LAN does not find the number of devices)" + info.length, Toast.LENGTH_SHORT).show();
                                            etUid.setText(info[0].UID);
                                        } else {
                                            Toast.makeText(MainActivity.this, "局域网没有搜索到设备 (LAN does not search for devices)", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });


                            }
                        });
```

##### 设置设备推送消息的实际地址  Set the actual address of the device push message

```java
/**
                         * 参数设置为服务器地址，设备推送消息的实际地址是服务器地址+/pushMsg
                         * 设置“www.hao123.com”，设备会向www.hao123.com/pushMsg发送报警消息
                         * ( The parameter is set to the server address, and the actual address of the device push message is the server address +/pushMsg
                          Set "www.hao123.com", the device will send an alarm message to www.hao123.com/pushMsg)
                         */
                        mCamera.setPhpServer("www.hao123.com", new Camera.getPhpServerCallback() {
                            @Override
                            public void getPhpServer(boolean b, String s) {

                            }
                        });
                        //设备报警上传到上述setPhpServer接口的json数据包含图片的名称，可以连接设备后使用mCamera.downLoadFile()下载图片,
                        //但是，连接设备再从设备端下载图片可能由于网络等各种原因造成下载图片失败，为了确保下载图片成功，用户可以调用mCamera.setPictureUrl，
                        //设置一个服务器图片上传的接口，设置改接口后，设备会向该接口上传图片，设备在推送到setPhpServer接口的json数据中包含一个imgUrl字段，就是上传图片的地址，用户可以使用
                        //该url地址从服务器下载报警图片，服务器下载的url地址必须是"服务器上传报警图片接口/图片的名称"
// 服务器接口设置2个参数接收 java类型 @RequestParam("imgFile") MultipartFile imgFile,String uid
// 服务器处理完成该请求如果成功 {int code,String message,Object data} 例如 {1,"success","报警图片的下载地址url"}

// device alarm upload to the above setPhpServer interface json data contains the name of the picture, you can connect to the device and use mCamera.downLoadFile () to download the picture,
                         //However, connecting the device and downloading the image from the device may fail to download the image due to various reasons such as the network. To ensure that the downloaded image is successful, the user can call mCamera.setPictureUrl.
                         // Set a server image upload interface, after setting the interface, the device will upload an image to the interface, the device in the json data pushed to the setPhpServer interface contains an imgUrl field, which is the address of the uploaded image, the user can use
                         //The url address downloads the alarm picture from the server. The url address downloaded by the server must be "server upload alarm picture interface/picture name"
// The server interface sets 2 parameters to receive java type @RequestParam("imgFile") MultipartFile imgFile,String uid
// If the request is successfully processed by the server {int code,String message,Object data} For example {1,"success","download URL of alarm picture"}
                        mCamera.setPictureUrl("服务器上传报警图片接口(全路径)", new Camera.successCallback() {
                            @Override
                            public void success(boolean b) {

                            }
                        });
```

##### 播放录制的视频文件  Play recorded video files

``` java
  //参数二是播放的初始偏移量，毫秒单位 (Parameter two is the initial offset of the playback, in milliseconds)
                        mCamera.playBackVideoStart("文件名", 0, new Camera.playBackVideoCallback() {
                            @Override
//i=0回放正常，bytes 数据，total 视频的I帧总数，current当前第几帧，current/total算出播放进度 (i=0 normal playback, bytes data, total number of I frames of total video, current current number of frames, current/total to calculate playback progress)
                            public void playBackVideo(int i, byte[] bytes, long total, long current, boolean b) {
                                videoDecodec.videoDecodec(bytes, 0);
                            }
                        }, (bytes, l) -> {
// 音频播放 (Music player)
                            if (mAudioTrack != null) 
                                mAudioTrack.write(bytes, 0, bytes.length); 
                        });
```

##### 查询SD中的图片  Query images in SD

``` java
 /**
    * 查询设备SD卡上的图片 (Query the picture on the SD card of the device)
  */
 long date = date2Long("2018-05-10", "yyyy-MM-dd") / 1000;//传入某一天日期的时间戳，单位需要转换为秒 (Pass in the timestamp of the date of a certain day, the unit needs to be converted to seconds)
 mCamera.listJpegFileStart(date);//开始查询 (Start query)
    //该方法每调用一次返回下一页数据，调用listJpegFileStart后会重新从第一页开始查询 (This method returns the next page of data every time it is called. After calling listJpegFileStart, it will start to query again from the first page.)
 mCamera.listJpegfile(date, new Camera.listFileInfoCallback() {
        @Override
        public void listFileInfo(int i, Camera.fileInfo[] fileInfos) {
        //i= 0: 后面还有文件 i=1:最后一个文件后面没有了 2：文件目录发生变化，请重新开始 (i= 0: There is still a file i=1: there is no file after the last file 2: The file directory has changed, please start again)
              if (i == 0) {
                 //可以继续调用mCamera.listJpegfile，返回下一页数据 (You can continue to call mCamera.listJpegfile to return to the next page of data.)
                 } else {
                 //已经是最后一页了 (Already the last page)
                 }
                  if (fileInfos != null) {
                     for (Camera.fileInfo fileInfo : fileInfos) {
                           Log.i("khj", fileInfo.filename);
                           //可以调用 mCamera.downLoadFile(fileInfo.filename,,)下载 (You can call mCamera.downLoadFile(fileInfo.filename,,) to download)
                             }
                            }
                          }
                      });

```

##### 查询SD中的视频   Query the video in SD

```java
  long date2 = date2Long("2018-05-10", "yyyy-MM-dd") / 1000;//传入某一天日期的时间戳，单位需要转换为秒 (Pass in the timestamp of the date of a certain day, the unit needs to be converted to seconds)
     mCamera.listvideoFile(date2, new Camera.listFileInfoCallback() {
          @Override
            public void listFileInfo(int i, Camera.fileInfo[] fileInfos) {
               }
            });
      mCamera.listvideoFile(date2, new Camera.listFileInfoCallback() {
            @Override
             public void listFileInfo(int i, Camera.fileInfo[] fileInfos) {
               }
            });
```

##### 下载SD卡中的文件(图片,视频...)   Download the files in the SD card

```java
 /**
  * 下载SD卡上的文件，包括视频和图片 (Download files on the SD card, including videos and images)
  */
   try { 
        FileOutputStream fileOutputStream = new FileOutputStream("目标文件夹全路径 (Target folder full path)");
        mCamera.downLoadFile("查询到的SD卡上文件名 (Query the file name on the SD card)", fileOutputStream, new         Camera.downLoadFileCallback() {
           @Override
            //result=0成功，1文件不存在，2正在发送文件，3失败 (Result=0 is successful, 1 file does not exist, 2 is sending file, 3 fails)
           public void complete(int result, byte[] bytes, long total, long current, Object o) {
           FileOutputStream o1 = (FileOutputStream) o;
              if (result == 0) {
                   int progress = (int) (current * 100 / total);
                 if (current >= total) {
                       //下载完成 (Download completed)
                       KLog.i(progress + "total" + total);
                        try {
                         o1.write(bytes);
                         o1.flush();
                         o1.close();
                         mCamera.cancelDownLoadFile();//下载完成需要调用该方法才能继续下载下一个文件 (This method needs to be called to complete the download to continue downloading the next file.)
                        } catch (IOException e) {
                             e.printStackTrace();
                                }
                        } else {
                               KLog.i(progress + "total" + total);
                              try {
                                  o1.write(bytes);
                              } catch (IOException e) {
                                    e.printStackTrace();
                               }
                            }
                                    } else {
                                        //下载失败 (download failed)
                                    }
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

```

##### 设备布防和撤防

设置布防状态, Camera.sendCommonBuffer 7 第二个字符串参数设置为0表示撤防，设置为1表示布防

```java
Camera.sendCommonBuffer((byte) 7,"0", (i, b, s) -> { // 设置0表示撤防
    if(s.equals("OK")){
        Log.i( set success)
    }else{
        Log.i( set failure)
    }
      })
```

##### 查询设备的防御状态

```java
Camera.sendCommonBuffer((byte) 6, "", (i, b, s) -> {   
if (!TextUtils.isEmpty(s)) {         
    int defenceStatus =  Integer.parseInt(s);  // 如果 defenceStatus == 0 表示当前设备为撤防状态，defenceStatus == 1 则为布防状态      
   }
});
```

###### 定时开关机

```java
String plan = "1618915092000-1618915092001\n1618915092033-1618915092055" // 关闭时间-开启时间,多个定时任务用 \n 分隔
camera.addTimedRecordVideoTask(plan,b -> {
                             if(b){
                                 log.i("设置定时任务成功")
                             }
                            });
```

##### 智能夜视

```java

camera.setSwitch(off, b -> {  // off = 0 no = 1 int值 1开0关 设备各种开关量的状态，按位计算。第0位代表枪机白光灯，第一位代表智能夜视
     if(b){
         log.i("设置智能夜视模式成功");
       }
});
```

##### 摄像头校准

```java
camera.setPtz(Camera.AVIOCTRL_MOTOR_RESET_POSITION, 0);
```

##### 白光灯开关

```java
camera.setSwitch(finalI, b -> { // finalI = 1 开 =0 关 设备各种开关量的状态，按位计算。第0位代表枪机白光灯，第一位代表智能夜视
                            emitter.onNext(b);
                            emitter.onComplete();
                        });
```

##### 重启设备

```java
 camera.deviceReboot(b -> {
     if(b){
        log.i("重启成功");
       }
});
```

###### 设备反转

```java
 CameraSendCommandBufferCall cameraSendCommandBufferCall = new CameraSendCommandBufferCall() {
            @Override
            public void call(int var1, byte var2, String var3) {
                
                // 查询回调
                  if(b == 32){
                // 上下反转回调
                  if( s.eqals("1")){
                   // 上下反转打开
                  } else if( s.eqals("0")) {
                   // 上下反转关闭
                  }
                } else if (b == 34){
                 // 左右反转回调
                  if( s.eqals("1")){
                   // 左右反转打开
                  } else if( s.eqals("0")) {
                   // 左右反转关闭
                  }
                } 
                
                // 设置回调
                if (b == 33){
                 // 上下反转设置回调
                  if( s.equalsIgnoreCase("ok")){
                   // 上下反转设置成功
                  } else  {
                   // 上下反转设置失败
                  }
                } else if (b == 35){
                  // 左右反转设置回调
                  if( s.equalsIgnoreCase("ok")){
                  // 左右反转设置成功
                  } else {
                  // 左右反转设置失败
                  }
                }
            }
        };

设置设备反转

@param type byte类型 type == 33 上下反转 type == 35左右反转
@param data String类型 "1" 反转 "0" 不反转 

camera.sendCommonBuffer((byte) type, data, cameraSendCommandBufferCall);

查询设备反转设置
@param type byte类型 type == 32 查询上下反转 type == 34查询左右反转
@param data String类型 查询时传""
    
camera.sendCommonBuffer((byte) type, data, cameraSendCommandBufferCall );
```

