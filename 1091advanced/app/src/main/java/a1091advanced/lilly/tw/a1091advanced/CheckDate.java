package a1091advanced.lilly.tw.a1091advanced;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
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
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CheckDate extends AppCompatActivity {
    private int mYear, mMonth, mDay;
    ListView lv1;
    Button logout,mystate;
    String[] tempp;
    RelativeLayout v;
    String UID;

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkdate);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/setofont.ttf");

        lv1=(ListView)findViewById(R.id.lv1);
        mystate = (Button)findViewById(R.id.button1_3);
        logout = (Button)findViewById(R.id.button1_4);

        mystate.setTypeface(font);
        logout.setTypeface(font);

        Bundle bget=getIntent().getExtras();
        UID=bget.getString("UID");

        v= (RelativeLayout)findViewById(R.id.bg3);//找到你要设透明背景的layout 的id
        v.getBackground().setAlpha(200);

        new Thread(new Runnable(){
            public void run(){

                    HttpURLConnection connection = null;
                    try {
                        URL url = new URL("http://34.80.188.97:3000/api/queryStore");
                        connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");//設定訪問方式為“GET”
                        connection.setConnectTimeout(8000);//設定連線伺服器超時時間為8秒
                        connection.setReadTimeout(8000);//設定讀取伺服器資料超時時間為8秒
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
                                    String fullTag = line.substring(indexOfOpen, indexOfClose+1);

                                    int left = 0;
                                    left = fullTag.indexOf(":",left);
                                    int  right = fullTag.indexOf(",", left);

                                    String Tag = fullTag.substring(left+1, right);//id

                                    result.append(Tag);//id:

                                    result.append(": ");

                                    left = fullTag.indexOf(":\"",left);
                                    right = fullTag.indexOf("\"}", left);
                                    Tag = fullTag.substring(left+2, right);//name
                                    result.append(Tag);//id:

                                    con.add(result);
                                    indexOfOpen = indexOfClose;
                                }

                            }

                            tempp=new String[con.size()];
                            for(int i=0;i<con.size();i++){
                                tempp[i]=con.get(i).toString();
                            }

                            ArrayAdapter adapter = new ArrayAdapter(CheckDate.this,
                                    android.R.layout.simple_list_item_1,
                                    tempp);
                            lv1.setAdapter(adapter);
                            lv1.setOnItemClickListener(onClickListView);
//
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


        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                intent1.setClass(CheckDate.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }

        });

        mystate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();

                Bundle bundle2=new Bundle();
                bundle2.putString("UID",UID);
                intent1.putExtras(bundle2);

                intent1.setClass(CheckDate.this, CheckHistory.class);
                startActivity(intent1);
            }

        });

    }
    private AdapterView.OnItemClickListener onClickListView = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
            // Toast 快顯功能 第三個參數 Toast.LENGTH_SHORT 2秒  LENGTH_LONG 5秒
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            String store=String.valueOf(lv1.getItemAtPosition(position));
            int left = 0;
            int  right = store.indexOf(":",left);
            final String storeid = store.substring(left, right);//id
            final String storename=store.substring(right+1);

//            Toast.makeText(CheckDate.this, position, Toast.LENGTH_SHORT).show();
            Log.d("test", storeid+"123"+storename+"456");


            DatePickerDialog dialog=new DatePickerDialog(CheckDate.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int day) {

                    Intent intent = new Intent();
                    Bundle bundle=new Bundle();
                    month=month+1;
                    String cmonth;
                    String cday;
                    if(month<10){
                       cmonth="0"+Integer.toString(month);
                    }else{
                        cmonth=Integer.toString(month);
                    }
                    if(day<10){
                        cday="0"+Integer.toString(day);
                    }else{
                        cday=Integer.toString(day);
                    }

//                    Log.d("qwq" ,"qwq"+storeid);
                    bundle.putString("chooseDate",year+"-"+cmonth+"-"+cday);
                    bundle.putString("chooseName", storename);
                    bundle.putString("id",storeid);
                    bundle.putString("UID",UID);

                    intent.putExtras(bundle);
                    intent.setClass(CheckDate.this, CurrentState.class);

                    startActivity(intent);

                }

            }, mYear,mMonth, mDay);
            DatePicker datePicker = dialog.getDatePicker();
            Date date = new Date("2021/01/14 00:00:01");//当前时间
            long time = date.getTime();
            Date date2 = new Date("2021/01/28 00:00:01");//当前时间
            long time2 = date2.getTime();
            datePicker.setMinDate(time);
            datePicker.setMaxDate(time2);
            dialog.show();
        }
    };

}
