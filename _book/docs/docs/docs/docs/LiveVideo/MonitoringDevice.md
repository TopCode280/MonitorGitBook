# 监听设备

###### 1.初始化 AudioTrack 

```java
  int size = AudioTrack.getMinBufferSize(8000,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT);
        mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, // 指定在流的类型
                8000, AudioFormat.CHANNEL_OUT_MONO,// 设置输出声道为双声道立体声
                AudioFormat.ENCODING_PCM_16BIT,// 设置音频数据块是8位还是16位
                size, AudioTrack.MODE_STREAM);
```

###### 2.

```java
 //接收音频 receive audio from camera
                        if (mCamera != null) {
                            if (mAudioTrack.getPlayState() != AudioTrack.PLAYSTATE_PLAYING)
                                mAudioTrack.play();
                            mCamera.startRecvAudio(new Camera.recvAudioCallback() {
                                @Override
                                public void recvAudio(byte[] data, long pts) {
                                    if (mAudioTrack != null)
                                        mAudioTrack.write(data, 0, data.length);
                                }
                            });
                        }
```

