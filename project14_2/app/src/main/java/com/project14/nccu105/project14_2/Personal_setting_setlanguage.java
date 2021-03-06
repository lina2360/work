package com.project14.nccu105.project14_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Personal_setting_setlanguage  extends AppCompatActivity {

    Button btn231,btn232,btn233,btn234,btn235,btn236;
    RadioGroup rg23;
    RadioButton rb231,rb232,rb233,rb234;
    ImageButton ib231;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_setting_setlanguage);
        getSupportActionBar().hide();
        //設定隱藏狀態

        rg23=(RadioGroup)findViewById(R.id.rg23_1);
        rb231=(RadioButton) findViewById(R.id.rb23_1);
        rb232= (RadioButton) findViewById(R.id.rb23_2);
        rb233=(RadioButton) findViewById(R.id.rb23_3);
        rb234= (RadioButton) findViewById(R.id.rb23_4);
        btn231 = (Button) findViewById(R.id.btn23_1);
//        btn232 = (Button) findViewById(R.id.btn23_2);
        btn233 = (Button) findViewById(R.id.btn23_3);
        btn234 = (Button) findViewById(R.id.btn23_4);
        btn235 = (Button) findViewById(R.id.btn23_5);
        btn236 = (Button) findViewById(R.id.btn23_6);
        ib231 = (ImageButton) findViewById(R.id.ib23_1);




        View.OnClickListener OCL = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(v instanceof Button){
                    int id = ((Button) v).getId();
                    switch (id) {
                        case R.id.btn23_1:
                            String msg1 = "往論壇頁";
                            Intent intent1 = new Intent();
                            intent1.setClass(Personal_setting_setlanguage.this,Forum.class);
                            startActivity(intent1);
//                            Toast.makeText(Personal_setting_setnotify.this, msg1, Toast.LENGTH_SHORT).show();
                            Personal_setting_setlanguage.this.finish();
                            break;
//                        case R.id.btn23_2:
//                            String msg2 = "往逛逛頁";
//                            Intent intent2 = new Intent();
//                            intent2.setClass(Personal_setting_setlanguage.this,Buyer.class);
//                            startActivity(intent2);
////                            Toast.makeText(Personal_setting_setnotify.this, msg2, Toast.LENGTH_SHORT).show();
//                            Personal_setting_setlanguage.this.finish();
//                            break;
                        case R.id.btn23_3:
                            String msg3 = "往接單頁";
                            Intent intent3 = new Intent();
                            intent3.setClass(Personal_setting_setlanguage.this,Seller.class);
                            startActivity(intent3);
//                            Toast.makeText(Personal_setting_setnotify.this, msg3, Toast.LENGTH_SHORT).show();
                            Personal_setting_setlanguage.this.finish();

                            break;
                        case R.id.btn23_4:
                            String msg4 = "往地圖頁";
                            Intent intent4 = new Intent();
                            intent4.setClass(Personal_setting_setlanguage.this,Maps.class);
                            startActivity(intent4);
//                            Toast.makeText(Personal_setting_setnotify.this, msg4, Toast.LENGTH_SHORT).show();
                            Personal_setting_setlanguage.this.finish();
                            break;
                        case R.id.btn23_5:
                            String msg5 = "往通知頁";
                            Intent intent5 = new Intent();
                            intent5.setClass(Personal_setting_setlanguage.this,Notify.class);
                            startActivity(intent5);
//                            Toast.makeText(Personal_setting_setnotify.this, msg5, Toast.LENGTH_SHORT).show();
                            Personal_setting_setlanguage.this.finish();
                            break;
                        case R.id.btn23_6:
                            String msg6 = "往個人頁";
                            Intent intent6 = new Intent();
                            intent6.setClass(Personal_setting_setlanguage.this,Personal.class);
                            startActivity(intent6);
//                            Toast.makeText(Personal_setting_setnotify.this, msg6, Toast.LENGTH_SHORT).show();
                            Personal_setting_setlanguage.this.finish();
                            break;




                    }}
                if(v instanceof ImageButton) {
                    int id2 = ((ImageButton) v).getId();
                    switch (id2) {
                        case R.id.ib23_1:
                            String msg7 = "返回";
                            Intent intent7 = new Intent();
                            intent7.setClass(Personal_setting_setlanguage.this,Personal_setting.class);
                            startActivity(intent7);
//                            Toast.makeText(Personal_setting_setnotify.this, msg7, Toast.LENGTH_SHORT).show();
                            Personal_setting_setlanguage.this.finish();
                            break;
                    }}
                if(v instanceof RadioButton) {
                switch(rg23.getCheckedRadioButtonId()){
                    case R.id.rb23_1:
                        Toast.makeText(Personal_setting_setlanguage.this, "中文 ok", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb23_2:
                        Toast.makeText(Personal_setting_setlanguage.this, "English ok", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb23_3:
                        Toast.makeText(Personal_setting_setlanguage.this, "French ok", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb23_4:
                        Toast.makeText(Personal_setting_setlanguage.this, "日本語 ok", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
            }
        };
        btn231.setOnClickListener(OCL);
//        btn232.setOnClickListener(OCL);
        btn233.setOnClickListener(OCL);
        btn234.setOnClickListener(OCL);
        btn235.setOnClickListener(OCL);
        btn236.setOnClickListener(OCL);
        ib231.setOnClickListener(OCL);
        rb231.setOnClickListener(OCL);
        rb232.setOnClickListener(OCL);
        rb233.setOnClickListener(OCL);
        rb234.setOnClickListener(OCL);

    }


}
