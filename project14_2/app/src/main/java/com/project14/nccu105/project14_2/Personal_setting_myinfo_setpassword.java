package com.project14.nccu105.project14_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Personal_setting_myinfo_setpassword extends AppCompatActivity {
    Button btn121,btn122,btn123,btn124,btn125,btn126,btn127;
    ImageButton ib121;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_setting_myinfo_setpassword);
//        getSupportActionBar().hide();
        //設定隱藏狀態

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar12);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(Personal_setting_myinfo_setpassword.this, Personal_setting_myinfo.class);
                startActivity(intent1);
                finish();
            }
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        btn121 = (Button) findViewById(R.id.btn12_1);
//        btn122 = (Button) findViewById(R.id.btn12_2);
        btn123 = (Button) findViewById(R.id.btn12_3);
        btn124 = (Button) findViewById(R.id.btn12_4);
        btn125 = (Button) findViewById(R.id.btn12_5);
        btn126 = (Button) findViewById(R.id.btn12_6);
        btn127 = (Button) findViewById(R.id.btn12_7);
//        ib121 = (ImageButton) findViewById(R.id.ib12_1);

        View.OnClickListener OCL = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v instanceof Button) {
                    int id = ((Button) v).getId();
                    switch (id) {
                        case R.id.btn12_1:
                            String msg1 = "往論壇頁";
                            Intent intent1 = new Intent();
                            intent1.setClass(Personal_setting_myinfo_setpassword.this, Forum.class);
                            startActivity(intent1);
//                            Toast.makeText(Personal_setting_myinfo_setpassword.this, msg1, Toast.LENGTH_SHORT).show();
                            Personal_setting_myinfo_setpassword.this.finish();
                            break;
//                        case R.id.btn12_2:
//                            String msg2 = "往逛逛頁";
//                            Intent intent2 = new Intent();
//                            intent2.setClass(Personal_setting_myinfo_setpassword.this, Buyer.class);
//                            startActivity(intent2);
////                            Toast.makeText(Personal_setting_myinfo_setpassword.this, msg2, Toast.LENGTH_SHORT).show();
//                            Personal_setting_myinfo_setpassword.this.finish();
//                            break;
                        case R.id.btn12_3:
                            String msg3 = "往接單頁";
                            Intent intent3 = new Intent();
                            intent3.setClass(Personal_setting_myinfo_setpassword.this, Seller.class);
                            startActivity(intent3);
//                            Toast.makeText(Personal_setting_myinfo_setpassword.this, msg3, Toast.LENGTH_SHORT).show();
                            Personal_setting_myinfo_setpassword.this.finish();
                            break;
                        case R.id.btn12_4:
                            String msg4 = "往地圖頁";
                            Intent intent4 = new Intent();
                            intent4.setClass(Personal_setting_myinfo_setpassword.this, Maps.class);
                            startActivity(intent4);
//                            Toast.makeText(Personal_setting_myinfo_setpassword.this, msg4, Toast.LENGTH_SHORT).show();
                            Personal_setting_myinfo_setpassword.this.finish();
                            break;
                        case R.id.btn12_5:
                            String msg5 = "往通知頁";
                            Intent intent5 = new Intent();
                        intent5.setClass(Personal_setting_myinfo_setpassword.this, Notify.class);
                            startActivity(intent5);
//                            Toast.makeText(Personal_setting_myinfo_setpassword.this, msg5, Toast.LENGTH_SHORT).show();
                            Personal_setting_myinfo_setpassword.this.finish();
                            break;

                        case R.id.btn12_6:
                            String msg6 = "往個人頁";
                            Intent intent6 = new Intent();
                            intent6.setClass(Personal_setting_myinfo_setpassword.this, Personal.class);
                            startActivity(intent6);
//                            Toast.makeText(Personal_setting_myinfo_setpassword.this, msg6, Toast.LENGTH_SHORT).show();
                            Personal_setting_myinfo_setpassword.this.finish();
                            break;
                        case R.id.btn12_7:
                            String msg7 = "確認送出";
                            Intent intent7 = new Intent();
                            intent7.setClass(Personal_setting_myinfo_setpassword.this, Personal_setting_myinfo.class);
                            startActivity(intent7);
//                            Toast.makeText(Personal_setting_myinfo_setpassword.this, msg7, Toast.LENGTH_SHORT).show();
                            Personal_setting_myinfo_setpassword.this.finish();
                            break;

                    }
                }
//                if (v instanceof ImageButton) {
//                    int id2 = ((ImageButton) v).getId();
//                    switch (id2) {
//                        case R.id.ib12_1:
//                            Intent intent8 = new Intent();
//                            intent8.setClass(Personal_setting_myinfo_setpassword.this, Personal_setting_myinfo.class);
//                            startActivity(intent8);
//                            Personal_setting_myinfo_setpassword.this.finish();
//                            break;
//                    }
//                }
            }
        };
        btn121.setOnClickListener(OCL);
//        btn122.setOnClickListener(OCL);
        btn123.setOnClickListener(OCL);
        btn124.setOnClickListener(OCL);
        btn125.setOnClickListener(OCL);
        btn126.setOnClickListener(OCL);
        btn127.setOnClickListener(OCL);
//        ib121.setOnClickListener(OCL);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent1 = new Intent();
            intent1.setClass(Personal_setting_myinfo_setpassword.this, Personal_setting_myinfo.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }

}