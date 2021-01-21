package com.project14.nccu105.project14_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class Personal_setting_sellerFinish extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_setting_seller_finish);

        getSupportActionBar().hide();


        Button btn501 = (Button)findViewById(R.id.btn50_1);
        Button btn502 = (Button)findViewById(R.id.btn50_2);

        btn501.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Personal_setting_sellerFinish.this, Seller.class);
                startActivity(intent);
            }

        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent1 = new Intent();
            intent1.setClass(Personal_setting_sellerFinish.this, Personal_setting.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }
}
