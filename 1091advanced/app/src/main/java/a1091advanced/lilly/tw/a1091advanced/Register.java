package a1091advanced.lilly.tw.a1091advanced;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.SEND_SMS;

public class Register extends AppCompatActivity {
    private static final String TAG = "";
    private static final int REQUEST_SMS = 0;
    Button btn31, btn32, btn33;
    EditText et31, et32, et33, et34, et35, et99;
    LinearLayout v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
//        getSupportActionBar().hide();
        //設定隱藏狀態
        TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/setofont.ttf");

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        v= (LinearLayout)findViewById(R.id.bg2);//找到你要设透明背景的layout 的id
        v.getBackground().setAlpha(200);

        btn32 = (Button) findViewById(R.id.btn3_2);
        btn33 = (Button) findViewById(R.id.btn3_3);
        et31 = (EditText) findViewById(R.id.editText);
        et32 = (EditText) findViewById(R.id.editText4);
        et33 = (EditText) findViewById(R.id.editText5);

        et99 = (EditText) findViewById(R.id.editText99);

        //設字型
        btn32.setTypeface(font);
        btn33.setTypeface(font);
        et31.setTypeface(font);
        et32.setTypeface(font);
        et33.setTypeface(font);
        et99.setTypeface(font);

        View.OnClickListener OCL = new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                int id = ((Button) v).getId();
                switch (id) {
                    case R.id.btn3_2:
                        String msg = "返回";
                        Intent intent = new Intent();
                        intent.setClass(Register.this, MainActivity.class);
                        startActivity(intent);
//                        Toast.makeText(Register.this, msg, Toast.LENGTH_SHORT).show();
                        Register.this.finish();
                        break;
                    case R.id.btn3_3:
                        if(et31.getText().toString().matches("")){
                            Toast.makeText(Register.this, "請填寫身分證", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if(et99.getText().toString().matches("")){
                            Toast.makeText(Register.this, "請填寫暱姓名", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if(et32.getText().toString().matches("")){
                            Toast.makeText(Register.this, "請填寫密碼", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if(et33.getText().toString().matches("")){
                            Toast.makeText(Register.this, "請確認密碼", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(et33.getText().toString().matches(et32.getText().toString())) {

                            final String account = et31.getText().toString();
                            final String password = et32.getText().toString();
                            final String name = et99.getText().toString();
                            new Thread(new Runnable(){
                                public void run(){
                                    try{
                                        HttpURLConnection connection = null;
                                        try{
                                            URL url = new URL("http://34.80.188.97:3000/api/signUp");
                                            connection = (HttpURLConnection) url.openConnection();
                                            connection.setRequestMethod("POST");
                                            connection.setConnectTimeout(8000);
                                            connection.setReadTimeout(8000);
                                            connection.setDoOutput(true);// 使用 URL 連線進行輸出
                                            connection.setDoInput(true);// 使用 URL 連線進行輸入
                                            connection.setUseCaches(false);// 忽略快取
                                            // 建立輸出流，並寫入資料
                                            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
                                            StringBuilder stringBuilder = new StringBuilder();
                                            String temp= "{\"pid\":\""+ account+"\",\"name\":\""+name+"\",\"passwd\":\""+password+"\"}";
                                            stringBuilder.append(temp);

                                            dataOutputStream.writeBytes(stringBuilder.toString());
                                            dataOutputStream .flush();
                                            dataOutputStream.close();
                                            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                                                // 當正確響應時處理資料
                                                StringBuffer response = new StringBuffer();
                                                String line;
                                                BufferedReader responseReader =
                                                        new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));

                                                while (null != (line = responseReader.readLine())) {
                                                    response.append(line);
                                                }

                                                String test =response.toString();
                                                if(test.substring(15,18).equals("200")){
                                                    responseReader.close();
                                                    finish();
                                                }else{
                                                    responseReader.close();
                                                }
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        } finally {
                                            if (null!= connection) {
                                                connection.disconnect();
                                            }
                                        }

                                    }
                                    catch(Exception e)
                                    {
                                    }
                                }
                            }).start();

                        }
                        else{
                            Toast.makeText(Register.this, "與上方密碼不一致，請重新輸入", Toast.LENGTH_SHORT).show();
                            return;

                        }
                        break;

                }


            }

        };
//        btn31.setOnClickListener(OCL);
        btn32.setOnClickListener(OCL);
        btn33.setOnClickListener(OCL);




    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.setClass(Register.this, MainActivity.class);
            startActivity(intent);
            Register.this.finish();
        }
        return false;
    }
}



