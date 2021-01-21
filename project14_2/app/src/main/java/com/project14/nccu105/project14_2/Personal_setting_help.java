package com.project14.nccu105.project14_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class Personal_setting_help extends AppCompatActivity {

    Button btn241,btn242,btn243,btn244,btn245,btn246;
    ListView lv24;
    ImageButton ib241;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_setting_help);
//        getSupportActionBar().hide();
        //設定隱藏狀態

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar24);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(Personal_setting_help.this, Personal_setting.class);
                startActivity(intent1);
                finish();
            }
        });


        btn241 = (Button) findViewById(R.id.btn24_1);
//        btn242 = (Button) findViewById(R.id.btn24_2);
        btn243 = (Button) findViewById(R.id.btn24_3);
        btn244 = (Button) findViewById(R.id.btn24_4);
        btn245 = (Button) findViewById(R.id.btn24_5);
        btn246 = (Button) findViewById(R.id.btn24_6);
//        ib241 = (ImageButton) findViewById(R.id.ib24_1);
        lv24 = (ListView) findViewById(R.id.lv24_1);

//        Toolbar mToolbarTb = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(mToolbarTb);

        String[] value=new String[]{"\b★我是購物者★",
                                    "\b★我可以選擇哪些寄送方式 ?\n寄送方式由購物者決定。\n建議使用以下寄送方式：超商店到店、郵寄宅配。",
                                    "\b★我該如何訂定商品售價 ? \n建議使用者在訂定商品售價時，將可能產生的運費、平台服務費、銀行交易手續費及給旅行者的代購費納入考量。",
                                    "\b★我的商品送達需要多長時間 ?\n旅行者需在回國後 3 - 7 天內寄出商品，您可以在 [ 訂單管理] 查看商品狀態。\n您可以自行斟酌旅行者的返回時間是否符合需求。\n＊若旅行者回國 3 - 7 天後仍舊未寄出您的商品，請聯繫代購平台協助處理。",
                                    "\b★我該如何追蹤我的訂單 ? ",
                                    "\b★怎麼跟接單的旅行者聯絡 ?",
                                    "\b★訂單填寫後可以修改嗎 ?",
                                    "\b★我可以取消我的訂單嗎 ?\n購物者的訂單在被旅行者接受前，都能隨時刪除。一旦旅行者接受訂單後，便無法再做任何更動。",
                                    "\b★我的訂單一直沒有旅行者接單怎麼辦 ?\n如您提出的訂單超過 30 天都沒有旅行者接單，系統會自動取消，您可以至訂單管理一鍵重新提出請求。",
                                    "\b★我是旅行者★",
                                    "\b★我要如何瀏覽買家的訂單請求 ?",
                                    "\b★關於接單的知識 ?\n旅行者在接受訂單時，請務必詳細閱讀購物者填寫的商品資訊 (包含型號、顏色、尺寸、注意細節等)，以免購入錯誤商品。",
                                    "\b★我該如何訂定商品售價 ? \n建議使用者在訂定商品售價時，將可能產生的運費、平台服務費、銀行交易手續費及您預計賺取的利潤納入考量。\n*銀行交易手續費計算方式如下：\n單件商品售價 * 1.95%，若有小數點則四捨五入。 \nex. 商品 A 定價為 500 元，銀行交易手續費為 500 * 1.95%=10 元。",
                                    "\b★如何更新訂單狀態 ? ",
                                    "\b★怎麼跟下單的購物者聯絡 ? " ,
                                    "\b★購物者可以取消訂單嗎 ? \n訂單在被旅行者接受前，購物者都能隨時刪除。一旦旅行者接受訂單後，便無法再做任何更動。",
                                    "\b★商品寄送流程是什麼 ? " ,
                                    "\b★寄出商品後，什麼時候會收到款項 ?",
                                    "\b★接單後無法成功購買商品，我該怎麼辦 ?",
                                    "\b★平台有交易金額上限嗎?\n為遵循國稅局規定，建議賣家每月銷售總金額不要超過新台幣八萬元，若未來開放自動升級商家功能，將協助處理稅務問題。"};
        ListAdapter adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,value);
        lv24.setAdapter(adapter);





        View.OnClickListener OCL = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(v instanceof Button){
                    int id = ((Button) v).getId();
                    switch (id) {
                        case R.id.btn24_1:
                            String msg1 = "往論壇頁";
                            Intent intent1 = new Intent();
                            intent1.setClass(Personal_setting_help.this,Forum.class);
                            startActivity(intent1);
//                            Toast.makeText(Personal_setting_setnotify.this, msg1, Toast.LENGTH_SHORT).show();
                            Personal_setting_help.this.finish();
                            break;
//                        case R.id.btn24_2:
//                            String msg2 = "往逛逛頁";
//                            Intent intent2 = new Intent();
//                            intent2.setClass(Personal_setting_help.this,Buyer.class);
//                            startActivity(intent2);
////                            Toast.makeText(Personal_setting_setnotify.this, msg2, Toast.LENGTH_SHORT).show();
//                            Personal_setting_help.this.finish();
//                            break;
                        case R.id.btn24_3:
                            String msg3 = "往接單頁";
                            Intent intent3 = new Intent();
                            intent3.setClass(Personal_setting_help.this,Seller.class);
                            startActivity(intent3);
//                            Toast.makeText(Personal_setting_setnotify.this, msg3, Toast.LENGTH_SHORT).show();
                            Personal_setting_help.this.finish();

                            break;
                        case R.id.btn24_4:
                            String msg4 = "往地圖頁";
                            Intent intent4 = new Intent();
                            intent4.setClass(Personal_setting_help.this,Maps.class);
                            startActivity(intent4);
//                            Toast.makeText(Personal_setting_setnotify.this, msg4, Toast.LENGTH_SHORT).show();
                            Personal_setting_help.this.finish();
                            break;
                        case R.id.btn24_5:
                            String msg5 = "往通知頁";
                            Intent intent5 = new Intent();
                            intent5.setClass(Personal_setting_help.this,Notify.class);
                            startActivity(intent5);
//                            Toast.makeText(Personal_setting_setnotify.this, msg5, Toast.LENGTH_SHORT).show();
                            Personal_setting_help.this.finish();
                            break;
                        case R.id.btn24_6:
                            String msg6 = "往個人頁";
                            Intent intent6 = new Intent();
                            intent6.setClass(Personal_setting_help.this,Personal.class);
                            startActivity(intent6);
//                            Toast.makeText(Personal_setting_setnotify.this, msg6, Toast.LENGTH_SHORT).show();
                            Personal_setting_help.this.finish();
                            break;




                    }}
//                if(v instanceof ImageButton) {
//                    int id2 = ((ImageButton) v).getId();
//                    switch (id2) {
//                        case R.id.ib24_1:
//                            String msg7 = "返回";
//                            Intent intent7 = new Intent();
//                            intent7.setClass(Personal_setting_help.this,Personal_setting.class);
//                            startActivity(intent7);
////                            Toast.makeText(Personal_setting_setnotify.this, msg7, Toast.LENGTH_SHORT).show();
//                            Personal_setting_help.this.finish();
//                            break;
//                    }}


            }
        };


        btn241.setOnClickListener(OCL);
//        btn242.setOnClickListener(OCL);
        btn243.setOnClickListener(OCL);
        btn244.setOnClickListener(OCL);
        btn245.setOnClickListener(OCL);
        btn246.setOnClickListener(OCL);
//        ib241.setOnClickListener(OCL);



//        ListView lv241=(ListView)findViewById(R.id.lv24_1);
//        String[] value1=new String[]{"平台有交易金額上限嗎?","信用卡優惠事項","為什麼我的商品被下架?","為什麼帳戶被凍結","新手賣家如何上手?"
//                ,"如何定商品售價?","有哪些寄送方式?","賣家買錯商品怎麼辦?"};
//        ListAdapter adapter1=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,value1);
//        lv241.setAdapter(adapter1);
//
//
//        lv241.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
//
//                switch(index){
//                    case 0:
//                        String msg7 = "平台有交易金額上限嗎?";
////                        Intent intent7 = new Intent();
////                        intent7.setClass(Personal_setting_help.this,Personal_setting_myinfo.class);
////                        startActivity(intent7);
//                        Toast.makeText(Personal_setting_help.this, msg7, Toast.LENGTH_SHORT).show();
////                        Personal_setting_help.this.finish();
//                        break;
//                    case 1:
//                        String msg8 = "信用卡優惠事項";
////                        Intent intent8 = new Intent();
////                        intent8.setClass(Personal_setting_help.this,Personal_setting_myaddress.class);
////                        startActivity(intent8);
//                        Toast.makeText(Personal_setting_help.this, msg8, Toast.LENGTH_SHORT).show();
////                        Personal_setting_help.this.finish();
//                        break;
//                    case 2:
//                        String msg9 = "為什麼我的商品被下架?";
////                        Intent intent9 = new Intent();
////                        intent9.setClass(Personal_setting_help.this,Personal_setting_mycard.class);
////                        startActivity(intent9);
//                        Toast.makeText(Personal_setting_help.this, msg9, Toast.LENGTH_SHORT).show();
////                        Personal_setting_help.this.finish();
//                        break;
//                    case 3:
//                        String msg10 = "為什麼帳戶被凍結";
////                        Intent intent10 = new Intent();
////                        intent10.setClass(Personal_setting_help.this,Personal.class);
////                        startActivity(intent10);
//                        Toast.makeText(Personal_setting_help.this, msg10, Toast.LENGTH_SHORT).show();
////                        Personal_setting_help.this.finish();
//                        break;
//                    case 4:
//                        String msg11 = "新手賣家如何上手?";
////                        Intent intent10 = new Intent();
////                        intent10.setClass(Personal_setting_help.this,Personal.class);
////                        startActivity(intent10);
//                        Toast.makeText(Personal_setting_help.this, msg11, Toast.LENGTH_SHORT).show();
////                        Personal_setting_help.this.finish();
//                        break;
//                    case 5:
//                        String msg12 = "如何定商品售價?";
////                        Intent intent10 = new Intent();
////                        intent10.setClass(Personal_setting_help.this,Personal.class);
////                        startActivity(intent10);
//                        Toast.makeText(Personal_setting_help.this, msg12, Toast.LENGTH_SHORT).show();
////                        Personal_setting_help.this.finish();
//                        break;
//                    case 6:
//                        String msg13 = "有哪些寄送方式?";
////                        Intent intent10 = new Intent();
////                        intent10.setClass(Personal_setting_help.this,Personal.class);
////                        startActivity(intent10);
//                        Toast.makeText(Personal_setting_help.this, msg13, Toast.LENGTH_SHORT).show();
////                        Personal_setting_help.this.finish();
//                        break;
//                    case 7:
//                        String msg14 = "賣家買錯商品怎麼辦?";
////                        Intent intent10 = new Intent();
////                        intent10.setClass(Personal_setting_help.this,Personal.class);
////                        startActivity(intent10);
//                        Toast.makeText(Personal_setting_help.this, msg14, Toast.LENGTH_SHORT).show();
////                        Personal_setting_help.this.finish();
//                        break;
//                }
//
//
//
//
//            }});

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                //TODO search
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent1 = new Intent();
            intent1.setClass(Personal_setting_help.this, Personal_setting.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }

}

