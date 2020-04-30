package com.project14.nccu105.project14_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Personal_setting_usage extends AppCompatActivity {
    Button btn251, btn252, btn253, btn254, btn255, btn256;
    ImageButton ib251;
    TextView tv25;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_setting_usage);

//        getSupportActionBar().hide();
        //設定隱藏狀態

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar25);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(Personal_setting_usage.this, Personal_setting.class);
                startActivity(intent1);
                finish();
            }
        });

        btn251 = (Button) findViewById(R.id.btn25_1);
//        btn252 = (Button) findViewById(R.id.btn25_2);
        btn253 = (Button) findViewById(R.id.btn25_3);
        btn254 = (Button) findViewById(R.id.btn25_4);
        btn255 = (Button) findViewById(R.id.btn25_5);
        btn256 = (Button) findViewById(R.id.btn25_6);
//        ib251 = (ImageButton) findViewById(R.id.ib25_1);
        tv25 = (TextView) findViewById(R.id.tv25_2);
        tv25.setText("★政策條款★\n"
                + "\t網路購物原本就有一定之風險，更何況是跨國之交易。「本網站」除提供會員(買家)網路商品代買、代標之服務外，亦致力於降低此購物風險。會員(買家)於服務流程中勾選「我已閱讀且同意政策條款」，即表示您已閱讀、了解並同意接受以下聲明之所有內容：\n"
                + "一、會員(買家)委託下標訂購\n"
                + "\t*為了交易安全，所有在本網站進行委託下標購買商品之使用者，都必須擔保所留存的資料與事實相符。對於使用者所留存的資料。本網站對於會員(買家)所登錄或留存之個人資料其管理運用及保全事項，請參閱本網站的個人隱私權保護聲明。\n"
                + "\t1.被禁止郵寄進口物品之商品：詳細品項可參考本網站 「禁運物品」內之說明。\n"
                + "\t2.包裹材積重量未符合下列國內四大超商、郵局或黑貓宅急便所規定範圍之商品。\n"
                + "★四大超商：\n"
                + "\t物品大小長+寬+高總和需＜105公分，且最長邊需＜45公分，重量需低於5公斤。\n"
                + "★郵局掛號：\n"
                + "\t長+寬+高總和不得逾150公分。單件20公斤以上不收寄。\n"
                + "★黑貓宅急便：\n"
                + "\t長+寬+高之總和要在150公分以內；重量則要在20公斤以下。\n"
                + "\t*本網站提供之代買、代標服務會依據該商品賣家(商家)交易平台之“評價數”或各網站回應之速度給予會員(買家)購買參考與建議。惟有時無法判斷會員(買家)委託代購(買)及代標商品內容之正確性、有用性、可靠性、合法性等，本網站不負相關責任。會員(買家)請於下訂前慎選商品與交易平台再購買。以下提供會員(買家)選擇賣家(商家)之方法與原則供您參考：\n"
                + "\t\t1.是否為新手賣家且該賣家交易紀錄或評價數過低。\n"
                + "\t\t2.賣家最進一個月內之負評較多且負評多為商品未到貨、缺件、瑕疵或與商品描述不符等。\n"
                + "\t\t3.賣家或網站是否有提供退換貨。\n"
                + "\t*會員(買家)請於下訂前慎選商品再購買。若因顏色、尺寸、色差、材質甚至個人因素等問題，購入後如非賣家(商家)錯誤，本網站無法提供退換貨，但若原賣家(商家)願意提供退換貨則不在此限。\n"
                + "\t*會員(買家)於本網站所登載之個人資料與收件地址，應確保其最新、完整及正確性。若會員(買家)因提供不實資料或資料錯誤導致之包裹遞送錯誤或延誤，本網站將無法負擔損失。會員(買家)應於下訂前或於賣家(商家)發貨前提前更新收件人資料或通知本網站予以修正。\n"
                + "二、報價與計價匯率\n"
                + "\t*本網站在收到您的詢問後會在12小時內將商品報價單送出，如超過12小時皆未收到可聯絡我們。\n"
                + "\t*本網站之計價匯率均以【台灣銀行】當日牌告現金匯率報價。報價單送出後若經過三天無任何回應及付款動作則視同棄單。如仍要購買，本網站會重新報價一次。\n"
                + "三、收件人資料\n"
                + "\t*會員(買家) 若直接當收件人時，請在完成付款後提供收件人的下列三項資料，以方便國外賣家(商家) 直寄商品給收件人：\n"
                + "\t\t1.中文姓名之英文拼音：可利用外交部的「外文姓名中譯英系統」翻成英文。\n"
                + "\t\t2.中文地址之英文拼音：可利用郵局的 「中文地址英譯系統」翻成英文。\n"
                + "\t\t3.手機號碼：用意是希望賣家(商家)把它寫在包裹上，以方便必要時郵務或快遞人員直接聯絡(比方確認收件人的中文姓名拼 音等資料)。\n"
                + "四、會員(買家)收貨方式與關稅繳納\n"
                + "\t*本網站提供【由會員(買家)直接當收件人】以及【由本網站先代收再轉寄】這兩種收貨方式。在購物過程中若產生關稅，會員(買家)同意以下列方式繳納：\n"
                + "\t\t1.【由會員(買家)直接當收件人】：由會員(買家)自行繳納[可將關稅直接交給郵差(局)或快遞人員即可]\n"
                + "\t\t2.【由本網站先代收再轉寄】：關稅金額依海關認定之物品價值計收，由本網站先繳納後再實報實銷向會員(買家)收取。依照關稅法規定，當物品價值超過台幣2,000元時將會產生關稅。\n"
                + "五、商品運送\n"
                + "\t*跨國交易有諸多不確定性。例如庫存不足、節慶休假、天候影響、賣家(商家)出貨速度、運輸延遲或其他不可抗拒之因素而導致會員(買家)延遲收貨所造成之任何損失，本網站無法負擔損失與不負相關責任。\n"
                + "\t*飛機運送過程中無法保證絕對不會產生損害，請會員(買家)先行衡量商品是否適合航運，本網站已善盡告知之責任，若商品本身結構問題導致損毀或外箱無明顯破損時，或因運送中產生損毀或非本網站可究責之情事，本網站將不對商品損壞負任何賠償責任。\n"
                + "六、會員(買家)收到商品後\n"
                + "\t*會員(買家) 請在簽收時確認包裹內容，如有異議請拒簽拒收，收件後視同履行合約。\n"
                + "\t*本網站均為會員(買家) 委託代購(買)及代標之商品，不適用七天鑑賞期規則。\n"
                + "\t*會員(買家) 若不幸收到瑕疵商品，請於第一時間內提供該商品之照片與保持其之完整性，本網站會盡力幫會員(買家) 向賣家或網站交涉，以降低會員(買家) 損失。但包裹簽收兩天後則不予受理。若賣家或網站無善意回應，本網站將透過該交易平台反應；若賣家(商家) 已清楚敘述或實照呈現商品瑕疵，但於購買時會員(買家) 遺漏說明，且於購得後不滿意商品時，會員(買家) 須自行承擔購買責任，本網站無法負擔損失。\n"
                + "七、其他\n"
                + "\t*會員(買家) 不得在本網站上使用謾罵、侮辱、誹謗等不雅言語以及其它攻擊其他會員(買家) 和本網站之言語。本網站有權刪除此類留言及其它本網站認為不合適的留言及訊息。\n"
                + "\t*本網站保有接受會員(買家) 訂單與否的權利。所有於本網站所進行之線上消費，會員(買家) 應同意以本網站所紀錄之電子交易資料與通訊軟體對話紀錄為準。如有糾紛，並以該電子交易資料與通訊軟體對話紀錄為認定標準。會員(買家) 如果發現交易資料不正確，應立即通知本網站。");




        View.OnClickListener OCL = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v instanceof Button) {
                    int id = ((Button) v).getId();
                    switch (id) {
                        case R.id.btn25_1:
                            String msg1 = "往論壇頁";
                            Intent intent1 = new Intent();
                            intent1.setClass(Personal_setting_usage.this, Forum.class);
                            startActivity(intent1);
//                            Toast.makeText(Personal_setting_myaddress.this, msg1, Toast.LENGTH_SHORT).show();
                            Personal_setting_usage.this.finish();
                            break;
//                        case R.id.btn25_2:
//                            String msg2 = "往逛逛頁";
//                            Intent intent2 = new Intent();
//                            intent2.setClass(Personal_setting_usage.this, Buyer.class);
//                            startActivity(intent2);
////                            Toast.makeText(Personal_setting_myaddress.this, msg2, Toast.LENGTH_SHORT).show();
//                            Personal_setting_usage.this.finish();
//                            break;
                        case R.id.btn25_3:
                            String msg3 = "往接單頁";
                            Intent intent3 = new Intent();
                            intent3.setClass(Personal_setting_usage.this, Seller.class);
                            startActivity(intent3);
//                            Toast.makeText(Personal_setting_myaddress.this, msg3, Toast.LENGTH_SHORT).show();
                            Personal_setting_usage.this.finish();

                            break;
                        case R.id.btn25_4:
                            String msg4 = "往地圖頁";
                            Intent intent4 = new Intent();
                            intent4.setClass(Personal_setting_usage.this, Maps.class);
                            startActivity(intent4);
//                            Toast.makeText(Personal_setting_myaddress.this, msg4, Toast.LENGTH_SHORT).show();
                            Personal_setting_usage.this.finish();
                            break;
                        case R.id.btn25_5:
                            String msg5 = "往通知頁";
                            Intent intent5 = new Intent();
                            intent5.setClass(Personal_setting_usage.this, Notify.class);
                            startActivity(intent5);
//                            Toast.makeText(Personal_setting_myaddress.this, msg5, Toast.LENGTH_SHORT).show();
                            Personal_setting_usage.this.finish();
                            break;
                        case R.id.btn25_6:
                            String msg6 = "往個人頁";
                            Intent intent6 = new Intent();
                            intent6.setClass(Personal_setting_usage.this, Personal.class);
                            startActivity(intent6);
//                            Toast.makeText(Personal_setting_myaddress.this, msg6, Toast.LENGTH_SHORT).show();
                            Personal_setting_usage.this.finish();
                            break;

                    }

                }

//                if (v instanceof ImageButton) {
//                    int id2 = ((ImageButton) v).getId();
//                    switch (id2) {
//                        case R.id.ib25_1:
//                            Intent intent7 = new Intent();
//                            intent7.setClass(Personal_setting_usage.this, Personal_setting.class);
//                            startActivity(intent7);
//                            Personal_setting_usage.this.finish();
//                            break;
//                    }
//                }
            }
        };
        btn251.setOnClickListener(OCL);
//        btn252.setOnClickListener(OCL);
        btn253.setOnClickListener(OCL);
        btn254.setOnClickListener(OCL);
        btn255.setOnClickListener(OCL);
        btn256.setOnClickListener(OCL);
//        ib251.setOnClickListener(OCL);



    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent1 = new Intent();
            intent1.setClass(Personal_setting_usage.this, Personal_setting.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }

}

