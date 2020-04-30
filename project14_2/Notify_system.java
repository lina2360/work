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

public class Notify_system  extends AppCompatActivity {
    ImageButton ib40;
    TextView tv40;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notify_activity);
//        getSupportActionBar().hide();
        //設定隱藏狀態

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar40);
        toolbar.setTitle("系統通知");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(Notify_system.this, Notify.class);
                startActivity(intent1);
                finish();
            }
        });

//        ib40 = (ImageButton) findViewById(R.id.ib40_1);
//        tv40=(TextView)findViewById(R.id.tv40_1);
//        tv40.setText("系統通知");

        ListView listview = (ListView) findViewById(R.id.lv40);
        int[] image = {
                R.drawable.img_ffb266,R.drawable.img_63c2c2,R.drawable.img_bf73a6,R.drawable.img_63cf74,R.drawable.img_ff695f
        };

        String[] str2 = {
                "重要通知",
                "重要通知",
                "使用回饋",
                "問題回報",
                "正式營運"};
        /*String[] str1 = {"★推薦朋友使用代購\n讓您的朋友獲得200%代幣回饋於他們完成他的的第一筆訂單時，一旦他們使用您的券代碼。您將獲得100代幣！",
                "★濫用評論獎勵機制將隱藏評論\n親愛的用戶您好，為維護用戶體驗，落實評論獎勵機制，請確實填寫商品評論內容，避免使用過多重複文字、符號、無異議內容等。例如：讚讚讚讚讚(重複文字)、我不知道要打甚麼我來亂打為了湊30字…(無意義內容)。如有以上類似情形，我們將隱藏該評論，並有權收回您因該評論而獲得之獎勵代幣，感謝您的理解與配合！",
                "★新用戶專屬200元購物金\n歡迎你加入此代購APP，6/13 0:00 ~ 6/15 23:59，單筆消費滿 $1,999，結帳時輸入折扣碼【SPMID200】不囉嗦現折 $200！答應我，把我花掉好嗎？(此折扣碼僅適用於優選賣家賣場，不含服務、票券類別，若有任何使用上的問題請洽詢客服，我們將竭誠為你服務。",
                "★賣家計分系統免運獎勵通知\n恭喜您，由於您的賣場本季至2019/5/30止，沒有得到任何的賣家計分，並且平均出貨速度小於等於3天，因此，買家在6/1、6/8、6/15及6/22的0:00 ~ 23:59於符合免運獎勵制度資格之賣場消費，選擇7-11超商取貨前5萬筆訂單消費滿 $299即享免運(5萬筆訂單後為滿 $299運費半價)，趕快檢查您的商品庫存、設定商品折扣，為您的賣場帶來更多人氣吧！",
                "★您的想法，我聽！\n我們在乎您的意見，請幫忙完成這份問卷，讓我們變的更好，給你最棒的購物體驗！只要兩分鐘就好！",
                "★電子發票開立通知\n已開立電子發票乙張：2018-7-15，發票號碼：FS41074081，金額 $60，會員編號：28456838，歸戶認證碼：3bb20b，若你已驗證Email信箱，請前往查收;若未驗證，可至網站查詢發票詳情！",
                "★我們將於4/17起收取成交手續費\n親愛的用戶您好，為提供更好的交易環境，我們將於4/17中午12點起開始收取成交手續費0.5%、信用卡交易手續費1.5%，商品在成交前您不須負擔任何費用，刊登商品仍維持完全免費。讓我們一起繼續做頭家！！",
                "★代購條款更新重要通知\n親愛的用戶您好：感謝您對我們的支持，這邊特別通知您代購使用規則有針對服務條款、退貨與退款政策等通用法規做了更新及新增，為保障您自身的權益，請您留意並且詳細閱讀內文，謝謝您。",
                "★申請成功\n恭喜您！您已通過優選賣家的申請。我們將於5個工作天內新增優選賣家標籤至你的賣場跟商品，並會以推播通知您，再請您耐心等候喔！",
                "★全家超商包裹配送延遲，敬請耐心等候\n因近期天候不佳，以及節慶將至交通狀況不穩定，全家超商包裹將延遲2 ~ 3天配達門市，敬請耐心等候！如訂單已配達門市，請無須理會此訊息～ 謝謝您！"};
*/

        String[] str1 = {
                "濫用評論獎勵機制將隱藏評論\n親愛的用戶您好，為維護用戶體驗，落實評論獎勵機制，請確實填寫商品評論內容，避免使用過多重複文字、符號、無異議內容等。例如：讚讚讚讚讚(重複文字)、我不知道要打甚麼我來亂打為了湊30字…(無意義內容)。如有以上類似情形，我們將隱藏該評論，並有權收回您因該評論而獲得之獎勵代幣，感謝您的理解與配合！",
                "代購條款更新重要通知\n親愛的用戶您好：感謝您對我們的支持，這邊特別通知您代購使用規則有針對服務條款、退貨與退款政策等通用法規做了更新及新增，為保障您自身的權益，請您留意並且詳細閱讀內文，謝謝您。",
                "如果有希望改進的地方，可於'個人'>'設定'>'使用回饋'中回饋，你的每一份回饋，都能使我們更加進步。",
                "在使用過程中遇到任何問題，可於'個人'>'設定'>'問題回報'中回饋，我們會盡快為你提供幫助。",
                "BuyBuyMap正式營運，今日起我們正式開始為大家服務啦!我們期待使用後能獲得各位得好評~也請多多關注平台活動!"};
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
//                            intent1.setClass(Notify_system.this,Notify.class);
//                            startActivity(intent1);
//                            Notify_system.this.finish();
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
            intent1.setClass(Notify_system.this, Notify.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }

}


