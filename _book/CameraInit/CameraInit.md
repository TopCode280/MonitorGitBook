## 1.概述

> SDK将摄像头封装为一个Camera对象，每个摄像头带有一个唯一的UID，如：MAEVIA-000005-KXPBT和KHJ-070923-LSUFN，通过摄像头UID可以构造camera对象。构造对象后，使用密码连接摄像头，连接成功后才能调用各种方法控制摄像头。

## 2. 连接摄像头

### 2.1构造摄像头对象

> 不同的摄像头连接的p2p服务器不同，根据UID前缀可以区分不同的服务器。

~~~java
private String initString = "EBGCFGBKKHJMGAJPELGNFPEAHNMMHCNPGFFDBMCIBBJJLGLPCNAHCLOPGJLMJGLKANMPLEDAODMKBACDJHNHJN:KHJTEC100";//this for KHJ
private String initString = "EBGEEHBHKOJHHNJNEMGNFPEJHNNHHENNGKFMBCDNAHJBLLKPDMAMCCPKGBLCJFLJAOMPLEDAODMEAGCKJMNGIHAN:KHJTEC001";//this for MAVIEA
private String initString = "EBGJFNBCKJJLGHJLEPGPFNEBHBMFHLNAHAFIALDLAAJBKBKJDIAPDJPMGOKAIPLOAANKKACEPDNNAF:KHJTECHM";//this for IRUMA
private String initString = "EBGCFGBKKHJNGBJJEFHPFBENHLNBHAMGHOEIANDIBOINKHLBCCBDCHOCHOKLJLKIBNNILECMPPNHAP:KHJAISA001";//this for AISA
private String initString = "EDGGFCBNKNJHGLJEEEGFFHEIHNMMHCNLGGFABJCAAGJBLJLHDLAOCDOPGJLKIKLJANMNKBDKOPMGBMCOIG:KEUROPE001";//this for KEUROPE
private String initString = "EEGNFJBDLFINHBINFMGEEJFGGEMMGIMDGDEKACDLBNIKKDKBCPBKDDPOHKKLIALLBDNFKEDHPLNHBACKIC:KHJ002";//this for KHJ002
private String initString = "EBGEFABMKGJOGCJNEMGLFJEFHCMDHNNGHGFMBBDOAHJDLKKODNALCAPIGHLFIFLPBHNCKFDLPHNKBGDI:KUSAKEY001";//this for USA
~~~

> 拼接摄像头UID和服务器编码构造出camera对象，如果 不拼接服务器编码，默认会使用KHJ服务器编码

~~~java
    mCamera = new Camera(device_uid + "," + initString);//新加坡服务器
 	mCamera = new Camera("KHJ-070923-LSUFN");//默认连接KHJ服务器
~~~

### 2.2连接摄像头

通过摄像头的默认账户：admin，以及默认密码：888888，连接摄像头。参数三flag为连接模式，0代表默认模式，首先进行局域网搜索1.5秒，其次尝试p2p打洞5秒，失败后再进行服务器转发。1代表快速连接模式，三种方式同时进行，此种模式会增加服务器转发的概率。

~~~java
 mCamera.connect("admin", "888888", 0, new Camera.onOffLineCallback() {
            @Override
            public void Online(Camera m, final int ret) {
                Log.d("shurun", "camer ison: " + isSuccess);
            }

            @Override
            public void Offline(Camera m) {
                Log.d(MainActivity.TAG, "camera offline");
            }
        });
    }
~~~

> 连接返回值ret=0代表连接成功，其他为失败

|   ret   |   comment   |
| :--: | :--: |
| 0 | 连接成功 |
| -1 | 初始化失败 |
| -3 | 网络连接超时 |
| -6 | 设备离线，服务器没有收到设备心跳包 |
| -8 | UID前缀码与服务器不匹配 |
| -10 | 没有可用的转发服务器 |
| -12 | 摄像头关闭了连接 |
| -90 | 设备离线 |
| -20009 | 密码错误 |





