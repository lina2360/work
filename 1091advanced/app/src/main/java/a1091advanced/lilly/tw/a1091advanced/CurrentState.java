package a1091advanced.lilly.tw.a1091advanced;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class CurrentState extends AppCompatActivity {
    Button buy,back;
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7;
    RelativeLayout v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currentstate);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/setofont.ttf");


        long messageTime = new Date().getTime();
        final String time=Long.toString(messageTime);

        buy= (Button) findViewById(R.id.button4_1);
        back= (Button) findViewById(R.id.button4_2);
        buy.setTypeface(font);
        back.setTypeface(font);

        v= (RelativeLayout)findViewById(R.id.bg);//找到你要设透明背景的layout 的id
        v.getBackground().setAlpha(200);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
//        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        tv6 = (TextView) findViewById(R.id.tv6);
        tv7 = (TextView) findViewById(R.id.tv7);

        tv1.setTypeface(font);
        tv2.setTypeface(font);
//        tv3.setTypeface(font);
        tv4.setTypeface(font);
        tv5.setTypeface(font);
        tv6.setTypeface(font);
        tv7.setTypeface(font);

        final Bundle bundle=getIntent().getExtras();
        final String chooseDate=bundle.getString("chooseDate");
        String name=bundle.getString("chooseName");
        final String id=bundle.getString("id");
        //還要一個user id
        final String UID=bundle.getString("UID");
        Log.d("ttt", "run: 1221"+id);

        CharSequence date=DateFormat.format("yyyy-MM-dd(HH:mm)",messageTime);
        tv2.setText("當前時間: "+date);
        tv1.setText("選擇日期："+chooseDate);


        tv6.setText("店家名稱："+name);

        new Thread(new Runnable(){//存貨和資格
            public void run(){

                HttpURLConnection connection = null;
                HttpURLConnection connection2 = null;
                try {

                    String urls="http://34.80.188.97:3000/api/queryStockByStore/"+id;//存貨
                    String urls2="http://34.80.188.97:3000/api/queryHistoryOrder";//有沒有該用戶訂單，沒有就還可預購

//                    Log.d("ttt", "run: 111"+urls);

                    URL url = new URL(urls);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");//設定訪問方式為“GET”
                    connection.setConnectTimeout(8000);//設定連線伺服器超時時間為8秒
                    connection.setReadTimeout(8000);//設定讀取伺服器資料超時時間為8秒

                    URL url2 = new URL(urls2);
                    connection2 = (HttpURLConnection) url2.openConnection();
                    connection2.setRequestMethod("POST");
                    connection2.setConnectTimeout(8000);
                    connection2.setReadTimeout(8000);
                    connection2.setDoOutput(true);// 使用 URL 連線進行輸出
                    connection2.setDoInput(true);// 使用 URL 連線進行輸入
                    connection2.setUseCaches(false);// 忽略快取
                    DataOutputStream dataOutputStream = new DataOutputStream(connection2.getOutputStream());
                    StringBuilder stringBuilder = new StringBuilder();
                    String temp= "{\"id\":"+UID+"}";
                    stringBuilder.append(temp);
                    dataOutputStream.writeBytes(stringBuilder.toString());
                    dataOutputStream .flush();
                    dataOutputStream.close();

                    if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {//存貨

                        StringBuffer response = new StringBuffer();
                        String line;
                        BufferedReader responseReader =
                                new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
//                        ArrayList con=new ArrayList();

//
//
                        if(null != (line = responseReader.readLine())) {//存貨
                            int indexOfOpen = 0;
                            indexOfOpen = line.indexOf("ResultCode",indexOfOpen);
                            while((indexOfOpen = line.indexOf("{",indexOfOpen))!=-1){

                                int indexOfClose = line.indexOf("}", indexOfOpen);
                                StringBuffer result = new StringBuffer();
                                String fullTag = line.substring(indexOfOpen, indexOfClose+1);
//                                Log.d("ttt", "run: 234578"+responseReader.readLine());

                                int left = 0;
                                left = fullTag.indexOf(":",left);
                                int  right = fullTag.indexOf(",", left);

                                String Tag = fullTag.substring(left+2, right-1);
                                Log.d("1234321", "okkk"+Tag+" .12321. "+chooseDate);
                                if(Tag.equals(chooseDate)){

                                    left = fullTag.indexOf(":",right);
                                    right = fullTag.indexOf("}", right);
                                    Tag = fullTag.substring(left+1, right);
                                    result.append(Tag);//id:
                                    tv4.setText("剩餘數量："+result);//要連資料庫
//                                con.add(result);



                                }

                                indexOfOpen = indexOfClose;

                            }

                        }
//
                    }

                    if (HttpURLConnection.HTTP_OK == connection2.getResponseCode()) {
                        // 當正確響應時處理資料
                        Bundle bundle=new Bundle();
                        StringBuffer response = new StringBuffer();
                        String line;
                        BufferedReader responseReader =
                                new BufferedReader(new InputStreamReader(connection2.getInputStream(), "utf-8"));

//                                            String test =response.toString();
//                                            if(test.substring(15,18).equals("200")){
//
                        if(null != (line = responseReader.readLine())) {
//                            int indexOfOpen = 0;
//                            indexOfOpen = line.indexOf("ResultMessage",indexOfOpen);
                            int indexOfOpen = 0;
                            indexOfOpen = line.indexOf("ResultMessage",indexOfOpen);
                            if((indexOfOpen = line.indexOf("{",indexOfOpen))!=-1){
                                Log.d("qwerty", "run: "+line);


//                                int indexOfClose = line.indexOf("}", indexOfOpen);
//                                StringBuffer result = new StringBuffer();
//                                String fullTag = line.substring(indexOfOpen, indexOfClose+1);

//                                int left = 0;
//                                left = fullTag.indexOf("StoreName",left);//店名
//                                left = fullTag.indexOf(":",left);
//                                int  right = fullTag.indexOf(",", left);
//
//                                String Tag = fullTag.substring(left+2, right-1);
////                                tv3.setText(Tag);
//                                Log.d("IDID", "okkk"+Tag);
//
//                                left = fullTag.indexOf("Date",right);//日期
//                                left = fullTag.indexOf(":",left);
//                                right = fullTag.indexOf(",", left);
//
//                                Tag = fullTag.substring(left+2, right-1);
////                                tv2.setText(Tag);
//                                Log.d("IDID", "okkk"+Tag);

                                tv5.setText("當前預購資格：無法預購(已有訂單)");



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
        }).start();


        buy.setOnClickListener(new View.OnClickListener(){//預購
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(CurrentState.this)
                        .setTitle("預購確認")
                        .setMessage("請確定購買日期與店家後再下單。")
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
                                                URL url = new URL("http://34.80.188.97:3000/api/book");
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
                                                String temp= "{\"userid\":"+UID+",\"storeid\":"+id+",\"date\":\""+chooseDate+"\"}";
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
                                                        Log.d("tete", "run: hihi");
                                                        responseReader.close();
                                                        Intent intent1 = new Intent();

                                                        Bundle bundle2=new Bundle();
                                                        bundle2.putString("UID",UID);
                                                        intent1.putExtras(bundle2);

                                                        intent1.setClass(CurrentState.this, CheckHistory.class);
                                                        startActivity(intent1);
                                                        finish();

                                                    }else if(test.substring(15,18).equals("412")){
//                                                        tv7.setText("距前次購買日期不足14天");
                                                          CurrentState.this.runOnUiThread(new Runnable() {
                                                              @Override
                                                              public void run() {
                                                                  Toast.makeText(CurrentState.this, "距前次購買日期不足14天", Toast.LENGTH_SHORT).show();
                                                              }
                                                          });
//                                                        Toast.makeText(CurrentState.this, "距前次購買日期不足14天", Toast.LENGTH_SHORT).show();

                                                        responseReader.close();
                                                    }else if(test.substring(15,18).equals("413")){
//                                                        tv7.setText("有已成立對取消之訂單");
                                                        CurrentState.this.runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                Toast.makeText(CurrentState.this, "有已成立但未取消之訂單", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
//                                                        Toast.makeText(CurrentState.this, "有已成立對取消之訂單", Toast.LENGTH_SHORT).show();
                                                        responseReader.close();

                                                    }else if(test.substring(15,18).equals("414")){
//                                                        tv7.setText("存貨不足");
                                                        CurrentState.this.runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                Toast.makeText(CurrentState.this, "存貨不足", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                                        responseReader.close();
//                                                        Toast.makeText(CurrentState.this, "存貨不足", Toast.LENGTH_SHORT).show();

                                                    }else if(test.substring(15,18).equals("415")){
//                                                        tv7.setText("棄單次數已達3次");
                                                        CurrentState.this.runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                Toast.makeText(CurrentState.this, "棄單次數已達3次", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                                        responseReader.close();
//                                                        Toast.makeText(CurrentState.this, "棄單次數已達3次", Toast.LENGTH_SHORT).show();

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
                        })
                        .show();




            }

        });
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                Intent intent1 = new Intent();
//                intent1.setClass(CurrentState.this, CheckDate.class);
//                startActivity(intent1);
                finish();
            }

        });




    }

    public static String getJson(String fileName,Context context) {
        //將json資料變成字串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //獲取assets資源管理器
            AssetManager assetManager = context.getAssets();
            //通過管理器開啟檔案並讀取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
