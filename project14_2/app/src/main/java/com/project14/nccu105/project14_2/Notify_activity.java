package com.project14.nccu105.project14_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Notify_activity  extends AppCompatActivity {
    ImageButton ib40;
    private ListView show;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notify_activity);
//        getSupportActionBar().hide();
        //設定隱藏狀態

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar40);
        toolbar.setTitle("主題活動");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(Notify_activity.this, Notify.class);
                startActivity(intent1);
                finish();
            }
        });

//        ib40 = (ImageButton) findViewById(R.id.ib40_1);
        show = (ListView) findViewById(R.id.lv40);

        int[] image = {
                R.drawable.img_ffb266,R.drawable.img_63c2c2
        };
        String[] str2 = {"熱門商品",
                "用戶好禮" };

       /* String[] str1 = {"★夏普滿萬舒馬再折 $666\n一起對抗炎熱，夏普沁涼風扇、空氣清淨、除溼直降85折up，挑戰市場最低價，滿萬輸碼再折 $666，手刀逛逛去！",
                         "★$1抽環遊世界雙人行\n$1奪寶戰送你去環遊世界，5/20 ~ 5/31每天加碼抽各國機票，巴黎、荷蘭、美國等機票只要 $1！快點隨我奪寶去！",
                         "★現領稅月加菜金，最高限折$2,900\n老闆佛心大加碼，全站滿 $2,000打9折最高折 $500，商城滿 $20,000現折$2,400，更有隱藏版無門檻9折券，偷偷跟你說快到『商城最低價』活動業尋找！",
                         "★24h生活館瘋搶5折券！\n24h生活館618年中慶，天天瘋搶5折券！滿 $618再折 $100、滿 $399再折 $50，全場下殺4折up！吃的、用的、喝的、玩的、想省錢的，通通都在這！",
                         "★閃購日限時免運通知\n6/6全家便利商店限定，限時特賣滿 $99免運！鎖定12:00、18:00、22:00搶現折$2,700購物金，United Athle素面短T下殺 $190！前往搶好康！",
                         "★20萬現金與豪宅住一年等你搶！\n618大樂透，20萬現金與冠德千萬豪宅住一年等你搶，6/15 ~ 6/18期間，完成指定任務就有機會抽中現金20萬、冠德千萬豪宅住一年！詳情請見活動頁面！",
                         "★全品項1折起，另想快速到貨！\n22:00準時搶優惠券，滿 $499現折 $40、滿萬現折 $1,100！熱銷BCL早安面膜 $279、小米空氣清淨機2S $3980，下單及想快速到貨，立即搶購！",
                         "★商城最低價通知12點、18點、22點搶購物金，PS4 hits+王國之心3+第二支手把 $11,399！惠而浦大廈扇 $2,880！威技台製除溼機 $3,888！買貴退10倍！",
                         "★大熱氣球出現危機？測你近期最大煩惱\n熱氣球機將墜落！你會先丟下什麼？測你近期最大煩惱，再抽契爾氏金盞花化妝水500ml(市價 $2,031)，快來店裡留言！",
                         "★獨家新品！理膚寶水淨痘無暇潤色組\n夏日偽素顏必敗組，淨痘無暇潤色組，打造裸妝感，修飾抗痘雙重功效！12小時急速修飾皮膚瑕疵，長時間預防痘痘復發，滿額最高再折 $300！"};
     */

        String[] str1 = {"最近有多位旅人賣家上架Kiehl's商品，手刀去購買！",
                "新用戶首次接下請求及購買商品免收平台服務費！" };
        List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
        for (int i = 0; i < str1.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("image", image[i]);
            item.put("text", str1[i]);
            item.put("title",str2[i]);
            items.add(item);
        }
        //android.R.layout.simple_list_item_1 為內建樣式，還有其他樣式可自行研究
        SimpleAdapter adapter = new SimpleAdapter(this, items,
                R.layout.listview5,new String[]{"image", "text","title"}, new int[]{R.id.notify_img, R.id.notify_message,R.id.notify_title}
        );
        show.setAdapter(adapter);



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
//                            intent1.setClass(Notify_activity.this,Notify.class);
//                            startActivity(intent1);
//                            Notify_activity.this.finish();
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
            intent1.setClass(Notify_activity.this, Notify.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }

}
