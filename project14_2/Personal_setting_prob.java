package com.project14.nccu105.project14_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Personal_setting_prob extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_setting_prob);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar61);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(Personal_setting_prob.this, Personal_setting.class);
                startActivity(intent1);
                finish();
            }
        });

        WebView webview = (WebView) findViewById(R.id.wv);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient()); //不調用系統瀏覽器
        webview.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSc-F0FW4BW5TUOSLVo7l-orA2NIe-VslmbVPqWymbJp3ypnTg/viewform");
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent1 = new Intent();
            intent1.setClass(Personal_setting_prob.this, Personal_setting.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }
}
