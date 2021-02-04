# 上传设置 (Upload Settings)

1. 设置摄像头申请云存储预签名put请求的后台服务接口地址

   ```java
       /**
        * 设置摄像头申请云存储预签名put请求的后台服务接口地址
        * @param url 接口全路径
        * @param callback data为设置的地址
        */
       public void setCloudStorageGateway(String url,P2PCallback<String> callback){
   
       }
   ```

2. 查询摄像头申请云存储预签名put请求的后台服务接口地址

   ```
   
       /**
        * 查询摄像头申请云存储预签名put请求的后台服务接口地址
        * @param callback data为url地址
        */
       public void getCloudStorageGateway(P2PCallback<String> callback){}
   ```

3. 设置摄像头上传云存储对象成功后，上报服务器接口地址

   ```java
                  mCamera.setCloudStorageUploadGateway("http://116.63.91.111/addFileInfo", new com.khj.P2PCallback<String>() {
                       @Override
                       public void onSuccess(String s) {
                           KLog.i("setCloudStorageUploadGateway:onSuccess:"+s);
                       }
   
                       @Override
                       public void onFailure(int i, String s) {
                           KLog.i("setCloudStorageUploadGateway:onFailure:"+s);
                       }
                   });
   ```

4. 查询摄像头上报云存储对象接口地址

   ```java
                   mCamera.getCloudStorageUploadGateway(new com.khj.P2PCallback<String>() {
                       @Override
                       public void onSuccess(String s) {
                           KLog.i("getCloudStorageUploadGateway:"+s);
                       }
   
                       @Override
                       public void onFailure(int i, String s) {
   
                       }
                   });
   
   ```

   5.获取云存储上传开关状态
   
   ```java
   mCamera.getCloundStorage(new Camera.getCloundStorageCallback(boolean var1, int var2){
       // var1 标识云存储开启状态 true标识开启
       // var2 等于1标识云存储套餐类型
   });
   ```
   
   6.设置云存储上传开关状态
   
   ```java
   mCamera.(boolean start, int type,new Camera.successCallback(boolean var1){
       // start  设置true标识开始 false 标识关闭
       // type   开启或关闭的套餐类型
       // var1   表示设置成功或失败
   })
   ```