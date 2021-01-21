package a1091advanced.lilly.tw.a1091advanced;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CheckHistory extends AppCompatActivity {
    private int mYear, mMonth, mDay;
    ListView lv1;
    Button back;
    String[] tempp;
    RelativeLayout v;
    String UID;

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkhistory);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/setofont.ttf");

        lv1=(ListView)findViewById(R.id.lv2);
        back = (Button)findViewById(R.id.button2_4);

        back.setTypeface(font);

        Bundle bget=getIntent().getExtras();
        UID=bget.getString("UID");

        v= (RelativeLayout)findViewById(R.id.bg5);//找到你要设透明背景的layout 的id
        v.getBackground().setAlpha(200);

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

                            StringBuffer response = new StringBuffer();
                            String line;
                            BufferedReader responseReader =
                                    new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
                            ArrayList con=new ArrayList();


                            while (null != (line = responseReader.readLine())) {
                                int indexOfOpen = 0;
                                indexOfOpen = line.indexOf("ResultCode",indexOfOpen);
                                while((indexOfOpen = line.indexOf("{",indexOfOpen))!=-1){

                                    int indexOfClose = line.indexOf("}", indexOfOpen);
                                    StringBuffer result = new StringBuffer();
                                    String fullTag = line.substring(indexOfOpen+1, indexOfClose);
//                                    Log.d("hihihihi", "run: "+fullTag);

                                    int left = 0;
                                    left = fullTag.indexOf("OrderID",left);//訂單id
                                    left = fullTag.indexOf(":",left);
                                    int  right = fullTag.indexOf(",", left);
                                    String Tag = fullTag.substring(left+1, right);
                                    result.append("訂單編號："+Tag+"\n");//id:

                                    left = fullTag.indexOf("StoreName",left);//店名
                                    left = fullTag.indexOf(":",left);
                                    right = fullTag.indexOf(",", left);
                                    Tag = fullTag.substring(left+2, right-1);
                                    result.append("店家名稱："+Tag+"\n");

                                    left = fullTag.indexOf("Date",left);//日期
                                    left = fullTag.indexOf(":",left);
                                    right = fullTag.indexOf(",", left);
                                    Tag = fullTag.substring(left+2, right-1);
                                    result.append("訂購日期："+Tag+"\n");

                                    left = fullTag.indexOf("IsPickUp",left);//是否取貨
                                    left = fullTag.indexOf(":",left);
//                                    right = fullTag.indexOf(",", left);
                                    Tag = fullTag.substring(left+1);
                                    if(Tag.equals("false")){
                                        result.append("取貨狀態：未取貨");
                                    }else{
                                        result.append("取貨狀態：已取貨");
                                    }


                                    con.add(result);
                                    indexOfOpen = indexOfClose;
                                }

                            }

                            tempp=new String[con.size()];
                            for(int i=0;i<con.size();i++){
                                tempp[i]=con.get(i).toString();
                            }

                            ArrayAdapter adapter = new ArrayAdapter(CheckHistory.this,
                                    android.R.layout.simple_list_item_1,
                                    tempp);
                            lv1.setAdapter(adapter);
                            lv1.setOnItemClickListener(onClickListView);
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


        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                finish();
            }

        });


    }


    private AdapterView.OnItemClickListener onClickListView = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
            final String store=String.valueOf(lv1.getItemAtPosition(position));
            int left = 0;
            left = store.indexOf("：",left);
            int right = store.indexOf("\n",left);
            final String orderid = store.substring(left+1, right);//id


            new AlertDialog.Builder(CheckHistory.this)
                    .setTitle("取消訂單確認")
                    .setMessage("確定要取消訂單嗎(只能取消有效訂單)")
                    .setNegativeButton("關閉視窗", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


//
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
//                                            Log.d("qoqo2222", "run: "+12345+temp);
                                            StringBuffer response = new StringBuffer();
//
                                            BufferedReader responseReader =
                                                    new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));

                                            String test =responseReader.readLine();

                                            if (test.substring(15, 18).equals("200")) {
                                                Intent intent1 = new Intent();

                                                Bundle bundle2=new Bundle();
                                                bundle2.putString("UID",UID);
                                                intent1.putExtras(bundle2);

                                                intent1.setClass(CheckHistory.this, CheckHistory.class);
                                                startActivity(intent1);
                                                finish();
//
                                            }
                                            else{
//
                                                CheckHistory.this.runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Toast.makeText(CheckHistory.this, "無法取消", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

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
                            }
                            ).start();







                        }
                    })
                    .show();

        }
    };
}
