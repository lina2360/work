package a1091advanced.lilly.tw.a1091advanced;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn21, btn22, btn23;
    EditText et21, et22;
    String input, account;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);





        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/setofont.ttf");
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        getSupportActionBar().setHomeButtonEnabled(true);


//        Toast.makeText(MainPage.this, "token:\n"+ FirebaseInstanceId.getInstance().getToken(),
//                Toast.LENGTH_LONG).show();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId  = "default_notification_channel_id";
            String channelName = "default_notification_channel_name";
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW));
        }


        btn21 = (Button) findViewById(R.id.btn2_1);
        btn22 = (Button) findViewById(R.id.btn2_2);
        btn23 = (Button) findViewById(R.id.btn2_3);
//        btn24 = (Button) findViewById(R.id.btn2_4);
        et21 = (EditText) findViewById(R.id.et2_1);
        et22 = (EditText) findViewById(R.id.et2_2);

        btn21.setTypeface(font);
        btn22.setTypeface(font);
        btn23.setTypeface(font);
        et21.setTypeface(font);
        et22.setTypeface(font);



        View.OnClickListener OCL = new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                int id = ((Button) v).getId();
                switch (id) {
                    case R.id.btn2_1:
                        final String account = et21.getText().toString();
                        final String password = et22.getText().toString();
                        new Thread(new Runnable(){
                            public void run(){
                                try{

                                    HttpURLConnection connection = null;
                                    try{
                                        URL url = new URL(" http://34.80.188.97:3000/api/signIn");//登入
//                                        URL url = new URL(" http://34.80.188.97:3000/api/insertStore");//加店
//                                        URL url = new URL(" http://34.80.188.97:3000/api/insertInventory");//加存貨
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
                                      String temp= "{\"pid\":\""+ account+"\",\"passwd\":\""+password+"\"}";//登入
//                                        String temp= "{\"storeID\":"+ 1+",\"date\":\""+"2020-01-14"+"\",\"stock\":"+300+"}";//存貨
//                                        String temp= "{\"name\":\""+ URLEncoder.encode(account, "UTF-8")+"\"}";//加店
                                        Log.d("123233", temp);

                                        stringBuilder.append(temp);

                                        dataOutputStream.writeBytes(stringBuilder.toString());
                                        dataOutputStream .flush();
                                        dataOutputStream.close();

                                        if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                                            // 當正確響應時處理資料
                                            StringBuffer response = new StringBuffer();
//                                            String line;
                                            BufferedReader responseReader =
                                                    new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));

                                            String test =responseReader.readLine();

                                            if (test.substring(15, 18).equals("200")) {
                                                Bundle bundle = new Bundle();//存id#

                                                if (null != test ) {
//                                                    Log.d("222", "run: "+test);
                                                    int indexOfOpen = 0;
                                                    indexOfOpen = test.indexOf("ResultMessage", indexOfOpen);
                                                    if ((indexOfOpen = test.indexOf("{", indexOfOpen)) != -1) {

                                                        int indexOfClose = test.indexOf("}", indexOfOpen);
                                                        StringBuffer result = new StringBuffer();
                                                        String fullTag = test.substring(indexOfOpen, indexOfClose + 1);

                                                        int left = 0;
                                                        left = fullTag.indexOf("ID", left);
                                                        left = fullTag.indexOf(":", left);
                                                        int right = fullTag.indexOf(",", left);
//                                                        Log.d("####", test);

                                                        String Tag = fullTag.substring(left + 1, right);
//                                                        Log.d("IDID", "okkk" + Tag);

                                                        bundle.putString("UID", Tag);

                                                    }
                                                }
                                                responseReader.close();
                                                Intent intent1 = new Intent();
                                                intent1.putExtras(bundle);
                                                intent1.setClass(MainActivity.this, CheckDate.class);
                                                startActivity(intent1);
                                                finish();

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

                        break;
                    case R.id.btn2_2:
                        et21.setText("");
                        et22.setText("");
                        Toast.makeText(MainActivity.this, "重新輸入", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btn2_3:
//                        String msg = "往註冊頁";
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, Register.class);
                        startActivity(intent);
//                        Toast.makeText(MainPage.this, msg, Toast.LENGTH_SHORT).show();
//                        MainPage.this.finish();
                        break;
//
                }



            }
        };
        btn21.setOnClickListener(OCL);
        btn22.setOnClickListener(OCL);
        btn23.setOnClickListener(OCL);


    }


//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//        super.onActivityResult(requestCode, resultCode, data);
//    }
}
