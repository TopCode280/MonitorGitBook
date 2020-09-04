# SD卡回放 (SDCard Replay)

摄像头会根据设置的时区，在每天的0点生成一个文件夹，保存当天录制的所有视频。

(The camera will generate a folder in the SDcard at 0 o'clock every day according to the set time zone to save all videos recorded on the day)

使用以下方法，传入某天0点，以秒为单位的时间戳，会返回当天所有录制视频的集合。

(Using the following method, passing in a day's time at 0 o'clock, a timestamp in seconds will return a collection of all recorded videos for that day.)

> Camera.getVideoFiletime(long timeStart, Camera.getVideFileTimeCallback callback)

```java
/*
* 查询视频文件列表回调接口 (Query video file list callback interface)
* ret  等于0时查询成功，1时 SD卡不可用，其它查询出错 ( ret= 0 means success, the SD card is unavailable at 1 and other value are incorrect.)
* var2  文件集合List (List of files)
* */  
   
 public interface getVideFileTimeCallback {
        void getVideoTiletime(int ret, ArrayList<Camera.fileTimeInfo> var2);
 }    

/*
* 视频文件信息Bean (Video File Information Bean)
* videofiletime  视频开始时间 (Video start time)
* playbackTotalTime  视频时长 (Video duration)
* */ 

public static final class fileTimeInfo {
        public long videofiletime;
        public int playbackTotalTime;

        private fileTimeInfo(long a, int b) {
            this.videofiletime = a;
            this.playbackTotalTime = b;
        }
}

```

根据返回的文件信息，可以计算出视频文件名称。SD卡上的视频文件命名规则，是将视频文件的起始时间格式化后加上.mp4后缀

(Based on the returned file information, the video file name can be calculated. The video file naming rule on the SD card is to format the start time of the video file and add the .mp4 suffix)

```java
 public static String getDateTime2(long currentTime) {
        return new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(currentTime);
    }
    
 String fileName = DateUtils.getDateTime2(fileTimeInfo.videofiletime * 1000) + ".mp4";
```





需要播放某一时刻的视频时，需要首先获取当天的所有视频文件信息，计算出需要播放的时候属于哪一个视频文件，并根据视频文件的起始时间计算出播放视频的偏移量。

(When you need to play a video at a certain time, you must first obtain all video file information for the day, calculate which video file belongs to when you need to play, and calculate the offset of the played video based on the start time of the video file.)

```java
/*
* 开始播放视频 (Start playing video)
* fileName  查询出的视频文件名 (the video file name)
* offset  偏移时间 = 需要播放时间 - videofiletime (Offset time = playback time required - videofiletime)
* playBackVideoCallback 视频流回调 (Video stream callback)
* recvAudioCallback 音频流回调 (Audio stream callback)
* */ 

Camera.playBackVideoStart(String file, int m, Camera.playBackVideoCallback callback, Camera.recvAudioCallback callback1)
  

/*
* 视频流回调  （Video stream callback）
* var1  等于0成功，其它出错 （Equal to 0 success, other errors）
* var2  二进制视频流数组 （Binary video stream array）
* var3  视频关键帧总数 （Total number of video key frames）
* var5  当前关键帧 （Current keyframe）
* var7  已废弃不做处理！（Discarded and not disposed of!）
* */    
    
public interface playBackVideoCallback {
        void playBackVideo(int var1, byte[] var2, long var3, long var5, boolean var7);
}   

/*
* 音频流回调 （Audio stream callback）
* var1  二进制音频流数组 （Binary audio stream array）
* var2  已废弃不做处理！（Discarded and not disposed of!）
* */  

public interface recvAudioCallback {
        void recvAudio(byte[] var1, long var2);
}
```

1. 查询sd卡回放倍数

   ```java
   /**
        * 查询sd卡当前回放的倍数，data为倍数，eg. data=1.5代表1.5倍速
        * @param callback
        */
       public void getPlaybackSpeed(P2PCallback<Integer> callback)
   ```

2. 设置sd卡回放倍数

   ```java
   /**
        * 设置当前连接用户回放的倍数
        * @param speed 倍数
        * @param callback data 返回设置成功的倍数
        */
       public void setPlaybackSpeed(Integer speed,P2PCallback<Integer> callback)
   ```

3. 暂停正在播放的sd卡回放

   ```java
   /**
        * 暂停当前播放的sd卡录像
        */
       public void pausePlayback(P2PCallback<String> callback)
   ```

4. 恢复暂停的sd卡回放

   ```java
    /**
        * 恢复播放sd卡视频
        * @param callback
        */
       public void resumePlayback(P2PCallback<String> callback)
   ```

