# 设备设置

##### 1.设备云台转动  Equipment pan/tilt

```java
//云台转动 参数1方向，参数2一次转动多少步  rotate the camera
mCamera.setPtz(AVIOCTRL_PTZ_RIGHT, 30);
mCamera.setPtz(AVIOCTRL_PTZ_LEFT, 30);
mCamera.setPtz(AVIOCTRL_PTZ_UP, 30);
mCamera.setPtz(AVIOCTRL_PTZ_DOWN, 30);
```

##### 2.获取设备信息  Get device information

```java
//获取设备信息 get the camera device information
// sdcardTotal SD卡总容量/MB
// sdcardFree  SD卡剩余容量/MB
// v2 v3 v4    固件版本
// fileSystem  SD卡文件系统格式，建议检测是否为"exfat",若不是则提示格式化SD卡,避免SD卡录像出现的断录等问题
mCamera.queryDeviceInfo(new Camera.deviceInfoCallback() {
                            @Override
                            public void deviceInfo(int sdcardTotal, int sdcardFree, byte v1, byte v2, byte v3, byte v4, String model, String vendor) {
                                String version = builder.append(v2).append(".").append(v3).append(".").append(v4).toString();//固件版本
                            }
                        });
```

##### 3.网络设置  Network settings

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

##### 4.画面翻转  Screen flip

```java
 mCamera.getFlipping(new Camera.successCallback() {
                            @Override
                            public void success(boolean b) {

                            }
                        });
 //b = false 不翻转，true 翻转
 mCamera.setFlipping(1, new Camera.successCallback() {
        @Override
        public void success(boolean b) {

        }
});
```

##### 5.录像设置  Recording settings

```java
 //查询当前sd录像的模式
// 0关闭录像  record off
 // 1连续录像    record continuous
// 2定时计划    schedule record
// 3报警录像    record when alarm
 mCamera.getVideoRecordType(new Camera.successCallbackI() {
        @Override
           public void success(int i) {
            }
         });

//设置SD卡录像模式  set record mode
  mCamera.setVideoRecordType(1, new Camera.successCallback() {
          @Override
          public void success(boolean b) {
               }
         });

//获取定时录像的时间段
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
  //查询设备开关状态
  mCamera.getForceOpenCamera(new Camera.successCallbackI() {
              @Override
                public void success(int i) {
                   // i==1代表开
                  }
               });
  //boolean switch   true 开 false关
  mCamera.forceOpenCamera(switch, new Camera.successCallback() {
               @Override
                  public void success(boolean b) {

                 }
               });
```

##### 7.设备音量设置  Device volume setting

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

##### 8.设置时区  Set time zone

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

##### 9.移动侦测和声音报警侦测  motion detection and sound detection

```java
                      //查询移动侦测的开关状态
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
                        //查询移动侦测灵敏度，等级1-5 5灵敏度最高
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
                        //查询声音侦测报警开关
                        mCamera.geteSoundAlarm(new Camera.successCallback() {
                            @Override
                            public void success(boolean b) {

                            }
                        });
                        //开关声音侦测报警
                        mCamera.setSoundAlarm(true, new Camera.successCallback() {
                            @Override
                            public void success(boolean b) {

                            }
                        });
                        //设备侦测报警后，设备端是否发出报警声音
                        mCamera.getAlarmVolume(new Camera.successCallback() {
                            @Override
                            public void success(boolean b) {

                            }
                        });
                        //设置报警时设备是否发出报警声音
                        mCamera.setAlarmVolume(true, new Camera.successCallback() {
                            @Override
                            public void success(boolean b) {

                            }
                        });
```

##### 10.格式化SD卡 Format SD card

```java
// 谨慎操作 
mCamera.formatSdcard(new Camera.successCallbackI() {
                    @Override
                      public void success(int issuccess) {
                         Log.d(MainActivity.TAG, "format sdcard: " + issuccess);
                        }
                   });
```

##### 11.设备注册广播回调  Device Broadcast

```java
 /**
                         * 设备注册广播回调，比如调用camere.forceOpenCamera成功后，设备会向所有注册该回调的
                         * 设备，发送广播，i代表时间类型，s包含时间的内容参数
                         */
  mCamera.registerActivePush2(new Camera.activePushCallback() {
                            @Override
                            public void activePush(int i, String s) {
                                switch (i) {
                                    case 0://关闭摄像头
                                        KLog.d("关闭摄像头");

                                        break;
                                    case 1://打开摄像头


                                        break;
                                    case 2://设备端开始录制视频
                                        KLog.d("设备端开始录制视频");

                                        break;
                                    case 3://设备端停止录制视频
                                        KLog.d("设备端停止录制视频");

                                        break;
                                    case 4://当前观看视频的人数
                                        int audience = Integer.parseInt(s);
                                        break;
                                }
                            }
                        });
```

##### 12.搜索局域网设备 Search for LAN devices

```java
 /**
                         * 搜索局域网设备
                         */
                        Camera.searchDevice(new Camera.searchDeviceInfoCallback() {
                            @Override
                            public void searchDeviceInfo(Camera.searchDeviceInfo[] info) {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (info != null) {
                                            Log.d(MainActivity.TAG, "info length: " + info.length);
                                            Toast.makeText(MainActivity.this, "局域网没有搜索到设备数量" + info.length, Toast.LENGTH_SHORT).show();
                                            etUid.setText(info[0].UID);
                                        } else {
                                            Toast.makeText(MainActivity.this, "局域网没有搜索到设备", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });


                            }
                        });
```

##### 13.设置设备推送消息的实际地址  Set the actual address of the device push message

```java
/**
                         * 参数设置为服务器地址，设备推送消息的实际地址是服务器地址+/pushMsg
                         * 设置“www.hao123.com”，设备会向www.hao123.com/pushMsg发送报警消息
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
                        mCamera.setPictureUrl("服务器上传报警图片接口", new Camera.successCallback() {
                            @Override
                            public void success(boolean b) {

                            }
                        });
```

##### 14.播放录制的视频文件  Play recorded video files

``` java
  //参数二是播放的初始偏移量，毫秒单位
                        mCamera.playBackVideoStart("文件名", 0, new Camera.playBackVideoCallback() {
                            @Override
//i=0回放正常，bytes 数据，total 视频的I帧总数，current当前第几帧，current/total算出播放进度
                            public void playBackVideo(int i, byte[] bytes, long total, long current, boolean b) {
                                videoDecodec.videoDecodec(bytes, 0);
                            }
                        }, (bytes, l) -> {
// 音频播放
                            if (mAudioTrack != null) 
                                mAudioTrack.write(bytes, 0, bytes.length); 
                        });
```

##### 15.查询SD中的图片  Query images in SD

``` java
 /**
    * 查询设备SD卡上的图片
  */
 long date = date2Long("2018-05-10", "yyyy-MM-dd") / 1000;//传入某一天日期的时间戳，单位需要转换为秒
 mCamera.listJpegFileStart(date);//开始查询
    //该方法每调用一次返回下一页数据，调用listJpegFileStart后会重新从第一页开始查询
 mCamera.listJpegfile(date, new Camera.listFileInfoCallback() {
        @Override
        public void listFileInfo(int i, Camera.fileInfo[] fileInfos) {
        //i= 0: 后面还有文件 i=1:最后一个文件后面没有了 2：文件目录发生变化，请重新开始
              if (i == 0) {
                 //可以继续调用mCamera.listJpegfile，返回下一页数据
                 } else {
                 //已经是最后一页了
                 }
                  if (fileInfos != null) {
                     for (Camera.fileInfo fileInfo : fileInfos) {
                           Log.i("khj", fileInfo.filename);
                           //可以调用 mCamera.downLoadFile(fileInfo.filename,,)下载
                             }
                            }
                          }
                      });

```

##### 16.查询SD中的视频   Query the video in SD

```java
  long date2 = date2Long("2018-05-10", "yyyy-MM-dd") / 1000;//传入某一天日期的时间戳，单位需要转换为秒
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

##### 17.下载SD卡中的文件(图片,视频...)   Download the files in the SD card

```java
 /**
  * 下载SD卡上的文件，包括视频和图片
  */
   try {
        FileOutputStream fileOutputStream = new FileOutputStream("目标文件夹全路径");
        mCamera.downLoadFile("查询到的SD卡上文件名", fileOutputStream, new         Camera.downLoadFileCallback() {
           @Override
            //result=0成功，1文件不存在，2正在发送文件，3失败
           public void complete(int result, byte[] bytes, long total, long current, Object o) {
           FileOutputStream o1 = (FileOutputStream) o;
              if (result == 0) {
                   int progress = (int) (current * 100 / total);
                 if (current >= total) {
                       //下载完成
                       KLog.i(progress + "total" + total);
                        try {
                         o1.write(bytes);
                         o1.flush();
                         o1.close();
                         mCamera.cancelDownLoadFile();//下载完成需要调用该方法才能继续下载下一个文件
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
                                        //下载失败
                                    }
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

```

