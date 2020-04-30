package com.project14.nccu105.project14_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

public class Personal_setting_setprivacy extends AppCompatActivity {
    Button btn221, btn222, btn223, btn224, btn225, btn226;
    ImageButton ib221;
    Switch sw221,sw222,sw223;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_setting_setprivacy);
        getSupportActionBar().hide();
        //設定隱藏狀態

        btn221 = (Button) findViewById(R.id.btn22_1);
//        btn222 = (Button) findViewById(R.id.btn22_2);
        btn223 = (Button) findViewById(R.id.btn22_3);
        btn224 = (Button) findViewById(R.id.btn22_4);
        btn225 = (Button) findViewById(R.id.btn22_5);
        btn226 = (Button) findViewById(R.id.btn22_6);
        ib221 = (ImageButton) findViewById(R.id.ib22_1);
        sw221 = (Switch) findViewById(R.id.sw22_1);
        sw222 = (Switch) findViewById(R.id.sw22_2);
        sw223 = (Switch) findViewById(R.id.sw22_3);

        sw221.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    sw221.setText("開啟");
                }else {
                    sw221.setText("關閉");
                }
            }
        });
        sw222.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    sw222.setText("開啟");
                }else {
                    sw222.setText("關閉");
                }
            }
        });
        sw223.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    sw223.setText("開啟");
                }else {
                    sw223.setText("關閉");
                }
            }
        });




        View.OnClickListener OCL = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v instanceof Button) {
                    int id = ((Button) v).getId();
                    switch (id) {
                        case R.id.btn22_1:
                            String msg1 = "往論壇頁";
                            Intent intent1 = new Intent();
                            intent1.setClass(Personal_setting_setprivacy.this, Forum.class);
                            startActivity(intent1);
//                            Toast.makeText(Personal_setting_myaddress.this, msg1, Toast.LENGTH_SHORT).show();
                            Personal_setting_setprivacy.this.finish();
                            break;
//                        case R.id.btn22_2:
//                            String msg2 = "往逛逛頁";
//                            Intent intent2 = new Intent();
//                            intent2.setClass(Personal_setting_setprivacy.this, Buyer.class);
//                            startActivity(intent2);
////                            Toast.makeText(Personal_setting_myaddress.this, msg2, Toast.LENGTH_SHORT).show();
//                            Personal_setting_setprivacy.this.finish();
//                            break;
                        case R.id.btn22_3:
                            String msg3 = "往接單頁";
                            Intent intent3 = new Intent();
                            intent3.setClass(Personal_setting_setprivacy.this, Seller.class);
                            startActivity(intent3);
//                            Toast.makeText(Personal_setting_myaddress.this, msg3, Toast.LENGTH_SHORT).show();
                            Personal_setting_setprivacy.this.finish();

                            break;
                        case R.id.btn22_4:
                            String msg4 = "往地圖頁";
                            Intent intent4 = new Intent();
                            intent4.setClass(Personal_setting_setprivacy.this, Maps.class);
                            startActivity(intent4);
//                            Toast.makeText(Personal_setting_myaddress.this, msg4, Toast.LENGTH_SHORT).show();
                            Personal_setting_setprivacy.this.finish();
                            break;
                        case R.id.btn22_5:
                            String msg5 = "往通知頁";
                            Intent intent5 = new Intent();
                            intent5.setClass(Personal_setting_setprivacy.this, Notify.class);
                            startActivity(intent5);
//                            Toast.makeText(Personal_setting_myaddress.this, msg5, Toast.LENGTH_SHORT).show();
                            Personal_setting_setprivacy.this.finish();
                            break;
                        case R.id.btn22_6:
                            String msg6 = "往個人頁";
                            Intent intent6 = new Intent();
                            intent6.setClass(Personal_setting_setprivacy.this, Personal.class);
                            startActivity(intent6);
//                            Toast.makeText(Personal_setting_myaddress.this, msg6, Toast.LENGTH_SHORT).show();
                            Personal_setting_setprivacy.this.finish();
                            break;

                    }

                }

                if (v instanceof ImageButton) {
                    int id2 = ((ImageButton) v).getId();
                    switch (id2) {
                        case R.id.ib22_1:
                            Intent intent7 = new Intent();
                            intent7.setClass(Personal_setting_setprivacy.this, Personal_setting.class);
                            startActivity(intent7);
                            Personal_setting_setprivacy.this.finish();
                            break;
                    }
                }
            }
        };
        btn221.setOnClickListener(OCL);
//        btn222.setOnClickListener(OCL);
        btn223.setOnClickListener(OCL);
        btn224.setOnClickListener(OCL);
        btn225.setOnClickListener(OCL);
        btn226.setOnClickListener(OCL);
        ib221.setOnClickListener(OCL);



    }


}


