# 发送语音

######  1.初始化 AudioRecord 

```java
  size = AudioRecord
                .getMinBufferSize(8000, AudioFormat.CHANNEL_IN_MONO,
                        AudioFormat.ENCODING_PCM_16BIT);
        mAudioRecord = new AudioRecord(
                MediaRecorder.AudioSource.MIC, // 指定在流的类型
                8000, AudioFormat.CHANNEL_IN_MONO,// 设置输出声道为单声道
                AudioFormat.ENCODING_PCM_16BIT,// 设置音频数据块是8位还是16位
                size);
```

###### 2.发送音频

```java
  if (mCamera != null) {
                            if (mAudioRecord.getRecordingState() != AudioRecord.RECORDSTATE_RECORDING) {
                                mAudioRecord.startRecording();
                            }
                            mCamera.startSendAudio(data -> {
                                Log.d(MainActivity.TAG, "send audio data length: " + data.length);
                                if (mAudioRecord != null) {
                                    mAudioRecord.read(data, 0, data.length);
                                }
                                return data.length;

                            });
                        }
```

