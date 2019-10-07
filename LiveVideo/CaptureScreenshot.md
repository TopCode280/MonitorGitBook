# 抓拍截图 (Capture screenshot)

###### 1.抓拍截图时调用 (Called when taking a screenshot)

```java
  //手机截图，不推建使用以下方法，推建使用 (Mobile phone screenshots, not built using the following methods, built and used) Bitmap bitmap = textureView.getBitmap();
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");//设置日期格式 (Set date format)
                        String name = df.format(new Date()) + ".jpg";
                        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + name;
                        videoDecodec.takeJpeg(path, b -> {
                            Toast.makeText(MainActivity.this,b ? "Success":"Failed",Toast.LENGTH_LONG).show();
                        });
```

