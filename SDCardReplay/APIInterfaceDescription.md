# API使用接口说明 (API use interface description)

## 1.获取SD卡中的视频文件列表并播放 (Get list of video files in SD card and play)

## 注：以下时间均为 “十位时间戳” 也就是秒级别时间戳！

## Note: The following times are “ten-digit timestamps”, which are second-level timestamps!

```java
/*
* 查询视频文件列表 （Query video file list）
* timeStart  当前时间的零点零分十位时间戳 （Time stamp）
* callback   查询结果回调 (Query result callback)
* */   

Camera.getVideoFiletime(long timeStart, Camera.getVideFileTimeCallback callback)
    
/*
* 查询视频文件列表回调接口 (Query video file list callback interface)
* var1  等于0时查询成功，1时 SD卡不可用，其它查询出错 (When the query is equal to 0, the SD card is unavailable at 1 and other queries are incorrect.)
* var2  文件集合List (List of files)
* */  
   
 public interface getVideFileTimeCallback {
        void getVideoTiletime(int var1, ArrayList<Camera.fileTimeInfo> var2);
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

/*
* 开始播放视频 (Start playing video)
* file  查询出的 videofiletime (Query for videofiletime)
* m  偏移时间 = 需要播放时间 - videofiletime (Offset time = playback time required - videofiletime)
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

