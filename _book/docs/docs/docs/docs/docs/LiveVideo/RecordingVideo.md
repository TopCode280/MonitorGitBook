# 录制视频

```
 //初始化 Muxing 对象
 muxing = new Muxing();
File file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis()+".mov");
  muxing.open(file.getAbsolutePath(), true);//合成mp4的需要音频则设置TURE，如果要生成的MP4没有音频则写FALSE
muxing.write("视频帧".getBytes(), false);//合成MP4需要同时写入视频和音频文件
 muxing.write("音频帧".getBytes(), true);
  
//结束录制，生成文件
muxing.close();
```

