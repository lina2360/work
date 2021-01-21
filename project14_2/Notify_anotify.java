package com.project14.nccu105.project14_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Notify_anotify extends AppCompatActivity {
    ImageButton ib40;
    TextView tv40;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notify_activity);
//        getSupportActionBar().hide();
        //設定隱藏狀態

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar40);
        toolbar.setTitle("動態通知");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(Notify_anotify.this, Notify.class);
                startActivity(intent1);
                finish();
            }
        });

//        ib40 = (ImageButton) findViewById(R.id.ib40_1);
//        tv40=(TextView)findViewById(R.id.tv40_1);
//        tv40.setText("動態通知");

        ListView listview = (ListView) findViewById(R.id.lv40);
        int[] image = {
                R.drawable.img_ffb266,R.drawable.img_63c2c2,R.drawable.img_bf73a6,R.drawable.img_63cf74,R.drawable.img_ff695f
        };
        String[] str2 = {"評價更新",
                "訂單更新",
                "訂單更新",
                "訂單更新",
                "賣家申請"};

       /* String[] str1 = {"★你Facebook上的好友 姍姍也用 shanshan開始代購囉！",
                "★fyfyfy以平價，前往查看。",
                "★yumixyang已開始關注你",
                "★你Facebook上的好友 Mim Tsai也用mimtsai開始代購囉！",
                "★Bing1995已評價了此訂單E21725932155，前往查看。",
                "★278149199.tw已開始關注你",
                "★你Facebook上的好友 林立穎也用 yy_ing開始代購囉！",
                "★hongyuhan已評價了此訂單E20477194159，前往查看。",
                "★sunnybg已開始關注你",


                "★你Facebook上的好友 泰璇也用 feb0820.開始代購囉！"};*/
        String[] str1 = {
                "嵐已評價訂單11573492489560，前往個人頁面以查看他 對你的評價。",
                "你的訂單11573492489560已完成，在'訂單詳情'中可給予本次交易的評價。",
                "你的訂單11573492489560已出貨，在'通知'>'訂單更新通知'中可察看訂單詳情。",
                "你的訂單11573492489560已被嵐接受，使用私訊功能可以與他進行細節確認。",
                "恭喜你成功申請為賣家，現在可以開始接單，或者使用新增商品。"};


        List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
        for (int i = 0; i < str1.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("image", image[i]);
            item.put("text", str1[i]);
            item.put("title", str2[i]);
            items.add(item);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, items,
                R.layout.listview5,new String[]{"image","text","title"},new int[]{R.id.notify_img, R.id.notify_message,R.id.notify_title}
        );
        listview.setAdapter(adapter);


//        View.OnClickListener OCL = new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                if(v instanceof ImageButton) {
//                    int id2 = ((ImageButton) v).getId();
//                    switch (id2) {
//                        case R.id.ib40_1:
//                            Intent intent1 = new Intent();
//                            intent1.setClass(Notify_anotify.this,Notify.class);
//                            startActivity(intent1);
//                            Notify_anotify.this.finish();
//                            break;
//                    }}
//
//            }
//        };
//        ib40.setOnClickListener(OCL);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent1 = new Intent();
            intent1.setClass(Notify_anotify.this, Notify.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }

}

