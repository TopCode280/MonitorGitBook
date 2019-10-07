# 录制视频 (Recording video)

```java
 //初始化 Muxing 对象 Initialize the Muxing object
 muxing = new Muxing();
File file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis()+".mov");
  muxing.open(file.getAbsolutePath(), true);//合成mp4的需要音频则设置TURE，如果要生成的MP4没有音频则写FALSE Set the TURE for the audio required to synthesize mp4, or FALSE if the MP4 to be generated has no audio.
muxing.write("视频帧 Video frame".getBytes(), false);//合成MP4需要同时写入视频和音频文件 Synthesizing MP4 requires writing both video and audio files
 muxing.write("音频帧 Audio frame".getBytes(), true);
//结束录制，生成文件 End recording, generate file
muxing.close();
```

