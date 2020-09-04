package com.example.zhaotao.camera_test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.socks.library.KLog;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.net.wifi.WifiManager.WIFI_STATE_ENABLED;

public class ConfigureNetActivity extends AppCompatActivity {

    @BindView(R.id.tv_selectWifi)
    Button tvSelectWifi;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.btn_createQRcode)
    Button btnCreateQRcode;
    @BindView(R.id.btn_ap)
    Button btnAp;
    @BindView(R.id.ivQRcode)
    ImageView ivQRcode;
    @BindView(R.id.btn_queryUID)
    Button btnQueryUID;
    private WiFiUtil wiFiUtil;
    private MulticastServer multicastServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_net);
        ButterKnife.bind(this);
        wiFiUtil = WiFiUtil.getInstance(getApplicationContext());
    }

    @OnClick({R.id.tv_selectWifi, R.id.btn_createQRcode, R.id.btn_ap,R.id.btn_queryUID})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_selectWifi:
                Intent wifiSettingsIntent = new Intent("android.settings.WIFI_SETTINGS");
                startActivity(wifiSettingsIntent);
                break;
            case R.id.btn_createQRcode:
                createQRcode();//
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        receiveMulticast();
                    }
                }).start();
                break;
            case R.id.btn_ap:
                String ssid = tvSelectWifi.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();
                if (ssid.equals("") || pwd.equals("")) {
                    Toast.makeText(this, "选择要连接的路由器SSID和密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(this, ApActivity.class);
                intent.putExtra("SSID", ssid);
                intent.putExtra("PWD", pwd);
                startActivity(intent);
                break;
//            case R.id.btn_queryUID:
//                OkGo.<String>post("http://www.khjtecapp.com/smart-camera-ucenter/temp/queryTempDevice")
//                        .params("account","账号")
//                        .params("appName","公司名称或者app名称")
//                        .execute(new StringCallback() {
//                            @Override
//                            public void onSuccess(Response<String> response) {
//                                KLog.e(response.body());
//                            }
//                        });
//                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (wiFiUtil.checkState() == WIFI_STATE_ENABLED) {
            //获取的SSID带有双引号
            String temp = wiFiUtil.getSSID();
            KLog.e(temp);
            tvSelectWifi.setText(temp.replace("\"", ""));
        } else {

        }
    }

    /**
     * ssid: the wifi ssid
     * PWD:the wifi password
     */
    private void createQRcode() {
        String ssid = tvSelectWifi.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
//        int wifiType=wiFiUtil.getType();
        int wifiType = 1;//wpa/wpa2 encrypt type

        StringBuilder builder = new StringBuilder();
        String wifiString =
                builder.append("S=").append(ssid).append(",")
                .append("P=").append(pwd).append(",")
                .append("A=").append("15111520684").append(",")//任意字符串或者用户app账号
                .append("U=").append("abc").append(",")//填一个任意字符串
                .append("T=").append(wifiType)
                .toString();


        int with = 800;
        Bitmap mBitmap = CodeUtils.createImage(wifiString, with, with, null);
        ivQRcode.setImageBitmap(mBitmap);
    }


    private void receiveMulticast() {
        multicastServer = new MulticastServer();
        KLog.e("准备接受消息");
        String s1 = multicastServer.recieveData();
        KLog.e("收到组播消息 receive message:" + s1);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (multicastServer!=null){
            multicastServer.release();
        }

    }
}
