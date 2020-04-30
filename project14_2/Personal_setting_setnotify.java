package com.project14.nccu105.project14_2;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Personal_setting_setnotify extends AppCompatActivity {

    Button btn211,btn212,btn213,btn214,btn215,btn216;
    Switch sw211;
    TextView tv211;
    ImageButton ib211;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_setting_setnotify);
//        getSupportActionBar().hide();
        //設定隱藏狀態

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar21);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(Personal_setting_setnotify.this, Personal_setting.class);
                startActivity(intent1);
                finish();
            }
        });

        btn211 = (Button) findViewById(R.id.btn21_1);
//        btn212 = (Button) findViewById(R.id.btn21_2);
        btn213 = (Button) findViewById(R.id.btn21_3);
        btn214 = (Button) findViewById(R.id.btn21_4);
        btn215 = (Button) findViewById(R.id.btn21_5);
        btn216 = (Button) findViewById(R.id.btn21_6);
//        ib211 = (ImageButton) findViewById(R.id.ib21_1);

        sw211 = (Switch) findViewById(R.id.sw21_1);
//        tv211 = (TextView) findViewById(R.id.tv21_2);
        // 添加监听
        sw211.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    sw211.setText("開啟");
                }else {
                    sw211.setText("關閉");
                }
            }
        });


        View.OnClickListener OCL = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(v instanceof Button){
                    int id = ((Button) v).getId();
                    switch (id) {
                        case R.id.btn21_1:
                            String msg1 = "往論壇頁";
                            Intent intent1 = new Intent();
                            intent1.setClass(Personal_setting_setnotify.this,Forum.class);
                            startActivity(intent1);
//                            Toast.makeText(Personal_setting_setnotify.this, msg1, Toast.LENGTH_SHORT).show();
                            Personal_setting_setnotify.this.finish();
                            break;
//                        case R.id.btn21_2:
//                            String msg2 = "往逛逛頁";
//                            Intent intent2 = new Intent();
//                            intent2.setClass(Personal_setting_setnotify.this,Buyer.class);
//                            startActivity(intent2);
////                            Toast.makeText(Personal_setting_setnotify.this, msg2, Toast.LENGTH_SHORT).show();
//                            Personal_setting_setnotify.this.finish();
//                            break;
                        case R.id.btn21_3:
                            String msg3 = "往接單頁";
                            Intent intent3 = new Intent();
                            intent3.setClass(Personal_setting_setnotify.this,Seller.class);
                            startActivity(intent3);
//                            Toast.makeText(Personal_setting_setnotify.this, msg3, Toast.LENGTH_SHORT).show();
                            Personal_setting_setnotify.this.finish();

                            break;
                        case R.id.btn21_4:
                            String msg4 = "往地圖頁";
                            Intent intent4 = new Intent();
                            intent4.setClass(Personal_setting_setnotify.this,Maps.class);
                            startActivity(intent4);
//                            Toast.makeText(Personal_setting_setnotify.this, msg4, Toast.LENGTH_SHORT).show();
                            Personal_setting_setnotify.this.finish();
                            break;
                        case R.id.btn21_5:
                            String msg5 = "往通知頁";
                            Intent intent5 = new Intent();
                            intent5.setClass(Personal_setting_setnotify.this,Notify.class);
                            startActivity(intent5);
//                            Toast.makeText(Personal_setting_setnotify.this, msg5, Toast.LENGTH_SHORT).show();
                            Personal_setting_setnotify.this.finish();
                            break;
                        case R.id.btn21_6:
                            String msg6 = "往個人頁";
                            Intent intent6 = new Intent();
                            intent6.setClass(Personal_setting_setnotify.this,Personal.class);
                            startActivity(intent6);
//                            Toast.makeText(Personal_setting_setnotify.this, msg6, Toast.LENGTH_SHORT).show();
                            Personal_setting_setnotify.this.finish();
                            break;




                    }}
//                if(v instanceof ImageButton) {
//                    int id2 = ((ImageButton) v).getId();
//                    switch (id2) {
//                        case R.id.ib21_1:
//                            String msg7 = "返回";
//                            Intent intent7 = new Intent();
//                            intent7.setClass(Personal_setting_setnotify.this,Personal_setting.class);
//                            startActivity(intent7);
////                            Toast.makeText(Personal_setting_setnotify.this, msg7, Toast.LENGTH_SHORT).show();
//                            Personal_setting_setnotify.this.finish();
//                            break;
//                    }}
            }
        };
        btn211.setOnClickListener(OCL);
//        btn212.setOnClickListener(OCL);
        btn213.setOnClickListener(OCL);
        btn214.setOnClickListener(OCL);
        btn215.setOnClickListener(OCL);
        btn216.setOnClickListener(OCL);
//        ib211.setOnClickListener(OCL);

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent1 = new Intent();
            intent1.setClass(Personal_setting_setnotify.this, Personal_setting.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }

}