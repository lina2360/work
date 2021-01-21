package a1091advanced.lilly.tw.a1091advanced;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class CheckBuyDate extends AppCompatActivity {
//    private int mYear, mMonth, mDay;
    Button back,cancel,history;
    RelativeLayout v;
    TextView tv2,tv3,tv4,t2,t3,t4;
    String orderid;

    @Override
    protected void onResume() {
        super.onResume();
    }

    //    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkbuydate);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/setofont.ttf");

        v= (RelativeLayout)findViewById(R.id.bg4);//找到你要设透明背景的layout 的id
        v.getBackground().setAlpha(200);

        final Bundle bundle=getIntent().getExtras();
        final String UID=bundle.getString("UID");
//        tv1 = (TextView) findViewById(R.id.tv5_1);
        tv2 = (TextView) findViewById(R.id.tv5_2);
        tv3 = (TextView) findViewById(R.id.tv5_3);
        tv4 = (TextView) findViewById(R.id.tv5_4);
//        t1 = (TextView) findViewById(R.id.textView5_1);
//        t2 = (TextView) findViewById(R.id.textView5_2);
//        t3 = (TextView) findViewById(R.id.textView5_3);
//        t4 = (TextView) findViewById(R.id.textView5_4);

        back = (Button)findViewById(R.id.button5_1);
        cancel = (Button)findViewById(R.id.button5_2);
        history = (Button)findViewById(R.id.button5_3);

        back.setTypeface(font);
        cancel.setTypeface(font);
        history.setTypeface(font);
//        tv1.setTypeface(font);
        tv2.setTypeface(font);
        tv3.setTypeface(font);
        tv4.setTypeface(font);
//        t1.setTypeface(font);
//        t2.setTypeface(font);
//        t3.setTypeface(font);
//        t4.setTypeface(font);//

        new Thread(new Runnable(){
            public void run(){
                try{

                    HttpURLConnection connection = null;
                    try{
                        URL url = new URL(" http://34.80.188.97:3000/api/queryHistoryOrder");
//
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
                        String temp= "{\"id\":"+UID+"}";
//

                        stringBuilder.append(temp);

                        dataOutputStream.writeBytes(stringBuilder.toString());
                        dataOutputStream .flush();
                        dataOutputStream.close();

                        if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                            // 當正確響應時處理資料
                            Bundle bundle=new Bundle();
                            StringBuffer response = new StringBuffer();
                            String line;
                            BufferedReader responseReader =
                                    new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));

//                                            String test =response.toString();
//                                            if(test.substring(15,18).equals("200")){
//
                            if(null != (line = responseReader.readLine())) {
                                int indexOfOpen = 0;
                                indexOfOpen = line.indexOf("ResultMessage",indexOfOpen);
                                while((indexOfOpen = line.indexOf("{",indexOfOpen))!=-1){

                                    int indexOfClose = line.indexOf("}", indexOfOpen);
                                    StringBuffer result = new StringBuffer();
                                    String fullTag = line.substring(indexOfOpen, indexOfClose+1);

                                    int left = 0;
                                    left = fullTag.indexOf("OrderID",left);//訂單id
                                    left = fullTag.indexOf(":",left);
                                    int  right = fullTag.indexOf(",", left);

                                    String Order = fullTag.substring(left+1, right);

                                    Log.d("IDID", "okkk"+Order);

                                    String Store;
                                    left = fullTag.indexOf("StoreName",left);//店名
                                    left = fullTag.indexOf(":",left);
                                    right = fullTag.indexOf(",", left);
                                    Store = fullTag.substring(left+2, right-1);

                                    Log.d("IDID", "okkk"+Store);

                                    String over14="false";
                                    String Date;
                                    left = fullTag.indexOf("Date",right);//日期
                                    left = fullTag.indexOf(":",left);
                                    right = fullTag.indexOf(",", left);
                                    Date = fullTag.substring(left+2, right-1);

                                    Log.d("IDID", "okkk"+Math.abs(Integer.parseInt(Date.substring(8,10))));
                                    if(Date.substring(5,7).equals("12")&&Math.abs(Integer.parseInt(Date.substring(8,10)))<31){
                                        over14="true";
                                    }

                                    String nopick="false";
                                    String Pick;
                                    left = fullTag.indexOf("IsPickUp",left);//是否取貨
                                    left = fullTag.indexOf(":",left);
                                    right = fullTag.indexOf("}",left);
                                    Pick = fullTag.substring(left+1,right);
                                    Log.d("IDID", "aloha"+Pick);
                                    if (Pick.equals("false"))
                                    {
                                        nopick="true";
                                    }

                                    //沒超過時間也還沒取貨
                                    if(over14.equals("false")&&nopick.equals("true")){
                                        Log.d("IDID", "okkk"+over14+"qqqqqq"+nopick);
                                        orderid=Order;
                                        tv3.setText("店名："+Store);
                                        tv2.setText("已預購日期："+Date);
                                        tv4.setText("當前預購資格：已新增訂單");

                                    }



                                    indexOfOpen = indexOfClose;
                                }
                            }
                            responseReader.close();

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

        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(CheckBuyDate.this)
                        .setTitle("取消訂單確認")
                        .setMessage("取消訂單後將恢復預購資格。")
                        .setNegativeButton("關閉視窗", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                new Thread(new Runnable(){
                                    public void run(){
                                        try{

                                            HttpURLConnection connection = null;
                                            try{
                                                URL url = new URL("http://34.80.188.97:3000/api/cancelOrder");
//
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
                                                String temp= "{\"id\":"+orderid+"}";//BUG
//

                                                stringBuilder.append(temp);

                                                dataOutputStream.writeBytes(stringBuilder.toString());
                                                dataOutputStream .flush();
                                                dataOutputStream.close();
                                                Log.d("qoqo2222", "run: "+12345);
                                                StringBuffer response = new StringBuffer();
//                                            String line;
                                                BufferedReader responseReader =
                                                        new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));

                                                String test =responseReader.readLine();

                                                if (test.substring(15, 18).equals("200")) {
                                                    tv2.setText("已預購日期：目前尚未有訂單");
                                                    tv3.setText("店名：目前尚未有訂單");
                                                    tv4.setText("當前預購資格：可以預購");
                                                }
                                                else{
                                                    Toast.makeText(CheckBuyDate.this, "目前無預購訂單", Toast.LENGTH_SHORT).show();

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
                        })
                        .show();




            }

        });

        history.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                Bundle his=new Bundle();
                his.putString("UID",UID);
                intent1.putExtras(his);
                intent1.setClass(CheckBuyDate.this, CheckHistory.class);
                startActivity(intent1);
            }

        });



        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                finish();
            }

        });
    }
}
