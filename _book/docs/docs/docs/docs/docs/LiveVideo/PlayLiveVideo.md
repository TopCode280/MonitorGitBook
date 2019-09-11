# 接收播放直播视频

###### 1.动态申请权限

```java
public void requestPerm() {
        AndPermission.with(this)
                .permission(
                        Permission.Group.MICROPHONE,
                        Permission.Group.STORAGE,
                        Permission.Group.CAMERA,
                        Permission.Group.LOCATION

                ).onGranted(permissions -> {
    
                }).onDenied(permissions -> Toast.makeText(MainActivity.this, "拒绝麦克风和存储权限无法录像和录音，截屏", Toast.LENGTH_SHORT).show())
                .start();
    }

```

###### 2.初始化解码器，视图View采用 TextureView 

``` java
// 解码器于 View 绑定
videoDecodec = new glVideoDecodec();
        mTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                Surface surface1 = new Surface(surface);
                videoDecodec.videoDecodecStart(surface1);
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                Surface surface1 = new Surface(surface);
                videoDecodec.videoDecodecStart(surface1);
            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                if (videoDecodec != null) {
                    videoDecodec.videoDecodecStop();
                }

                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });
```



###### 3.传入设备UID连接设备并初始化Camera对象（Camera是全局重点需要维护的对象）。

```java
/**
     * 通过uid，账号，密码连接设备 这的密码为第一次连接默认密码,连接后自行修改
     */
    private void connectCamera() {
        mCamera = new Camera("KHJA-053802-CXCLM");
        mCamera.connect("admin", "888888", new Camera.onOffLineCallback() {
            @Override
            public void Online(Camera m, final int isSuccess) {
                Log.e("shurun", "camer ison: " + isSuccess);
                if (isSuccess == 0) {
                    mHandler.sendEmptyMessage(1);
                } else {
                    mHandler.post(() -> Toast.makeText(MainActivity.this, "连接失败: " + isSuccess, Toast.LENGTH_LONG).show());
                }
            }

            @Override
            public void Offline(Camera m) {
                Log.d(MainActivity.TAG, "camera offline");
            }
        });
    }

// 修改设备密码，若没有做存储而丢失密码只能复位至原始密码重新绑定
mCamera.changePassword("888888", newPwd, b -> {
                        if (b) {
                            KLog.e("pwd", "原始密码连接成功，修改密码 = " + newPwd);
                        }
       });
```

###### 4.接收视频，这一步如果一切正常将播放视频。

```java
private void startReceiceVideo() {
        if (mCamera != null) {
            int ret = mCamera.startRecvVideo(new Camera.recvVideoCallback() {
                @Override
                public void recvVideo(byte[] data, long pts, int keyframe) {
                    if (!isRunning.get() && keyframe == 1 && videoDecodec != null) {
                        videoDecodec.videoDecodec(data, pts);
                        isRunning.set(true);
                    } else if (isRunning.get() && videoDecodec != null)
                        videoDecodec.videoDecodec(data, pts);
                }
            });
            Log.e(MainActivity.TAG, "start recv video ret: " + ret);
        }
    }
```

###### 5.视频清晰度切换

```
  // VIDEO_QUALITY 分为5个档次，分别为 1 - 5,数字越小清晰度越低。VIDEO_QUALITY = 1 高清码流在800K上下，VIDEO_QUALITY = 3 标清码流在300K上下,VIDEO_QUALITY = 5 流畅码流在100K上下。（注：若网络情况较差,但却开到最高清晰度会发生丢帧的情况，视图表现为黑屏。）
  mCamera.setQuality(int VIDEO_QUALITY, b -> {
      if(b){
           Log.i("khj","Change Video Dpi Success")
        }
    });
```



###### 6.停止接受视频

```java
mCamera.stopRecvVideo();  //stop  receive video
```

###### 7.断开Camera连接

```
  mCamera.disconnect(); //该方法只是断开了连接，mCamera还可以使用connect方法重新连接 disconnect                           //camera but not release,can reconnect
 mCamera.release();//这个方法将销毁对象，不能再使用  release camera object
```

