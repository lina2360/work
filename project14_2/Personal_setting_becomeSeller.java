package com.project14.nccu105.project14_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Personal_setting_becomeSeller extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_setting_become_seller);

//        getSupportActionBar().hide();

        TextView tv482=(TextView) findViewById(R.id.tv48_2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar48);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(Personal_setting_becomeSeller.this, Personal_setting.class);
                startActivity(intent1);
                finish();
            }
        });

        tv482.setText("     BuyBuyMap提供了一個旅人和買家可以輕鬆媒合彼此需求的旅遊代購平台，但對於我們而言，非常重要的一點是，我們的網上交易平台對於買賣雙方都是可靠且安全的。我們想藉此機會說明在BuyBuyMap上交易的規則“賣家條款”，這些規則亦是我們使用者條款的組成部分。我們的服務僅在某些國家/地區可用。 這些賣家條款還概述了您與買家彼此的權利和義務。" +
                "您可以在“使用者條款”中找到根據銷售合同買家須承擔的義務。若想知道更多使用者相關信息，請查閱我們的使用者條款。"+"\n"
                +"一、\t註冊成為賣家"+"\n"
                +" 您有興趣使用我們的APP來代購商品真是太好了！ 在使用我們的網路代購平台之前，請確保您了解以下內容："+"\n"
                +"	您需要建立一個賣家帳戶。 註冊為賣家時，您必須遵守所有適用的稅務法律和法規，以正確的身份進行註冊，並在註冊時提供所有必需的信息。"+"\n"
                +"我們會需要您驗證您的身份。 要在我們的平台上進行代購，我們可能會要求您提供其他辨識身分的資料。 例如，根據我們的隱私政策，可能會要求您提供身份證明文件，銀行帳號以及出生日期等。在您能夠出售或接收付款之前，我們的付款服務可能會需要這些資料。"+"\n"
                +"二、\t提供代購請求單"+"\n"
                +"註冊為賣家後，您可以提供代購商品單。在此之前，請先確保您了解以下內容："+"\n"
                +"我們將確認您商品單中的商品的合適性。我們將會審核哪些商品適合在我們的平台上出現，確認該商品在市場上是否能合法交易，否則，若有不當交易之情況發生，我們將封鎖您的帳號或拒絕該商品單被放上平台等應對措施。"+"\n"
                +"我們要求一個清晰及完整的商品描述。 提供代購商品單時，您必須清楚及全面地提供有關商品的資料。詳細須填資料可在“使用者條款”中查閱。"+"\n"
                +"您須保證您有權代購並出售該商品。通過交易商品，您保證您不會觸犯下列事項："+"\n"
                +"（1）\t違反任何法律或法規"+"\n"
                +"（2）\t侵犯第三方的任何權利，包括協議和知識產權的權利"+"\n"
                +"（3）\t以某種方式，普遍性來說是欺詐性的行事"+"\n"
                +"（4）\t對第三方有非法或欺詐的方式行事。您亦須保證商品的買賣是真實的交易。更具體地說，您保證您不會提供拍賣品以作為虛假交易的一部分，從而使買家，" +
                "您自己或第三方能夠轉移金額並洗黑錢"+"\n"
                +"您可以在我們的“使用者條款”中找到更多與提供代購請求單相關的資料。"+"\n"
                +"三、\t無法履行您對BuyBuyMap的義務"+"\n"
                +"如果您未能按照賣家條款履行對我們的義務，我們可能決定取消相關代購商品單的交易。取消交易與單純取消商品單不同。如果我們取消交易，我們可以追究您由此造成的所有損失和費用。這意味著您仍然會欠我們保證金。您還要對由於取消而錯過的買家承擔部分責任。" +
                "此外，我們將說明您不遵守賣家條款的後果。"+"\n"
                +"為了確保我們和其他用戶的權利，例如，如果您違反了這些使用條款，我們有權在任何時候根據我們的權力來決定"+"\n"
                +"(1)\t暫停收付款或退款"+"\n"
                +"(2)\t扣留您的收款款項"+"\n"
                +"(3)\t上列這些行為中的任何一項受到強制性（消費者）法律的管制，我們只能根據此類強制性法律進行管制"+"\n"
                +"除上述內容外，為遵守適用的法律，倘若"+"\n"
                +"(1)\t您違反了我們的使用條款"+"\n"
                +"(2)\t您屢次獲得差評，或者我們被告知您的品行或行為有問題"+"\n"
                +"(3)\t我們真誠地認為，必須保護我們的平台市場以及其他用戶，或防止欺詐和其他非法活動，我們可能會採取以下措施："+"\n"
                +"暫時或永久限制您的授權以及代購相關商品之活動"+"\n"
                +"暫時或永久撤銷與您的帳戶相關的任何特殊狀態"+"\n"
                +"暫時或永久限制您訪問您的帳戶"+"\n"
                +"如果您在我們第一次提出請求時仍未提供準確的銀行，聯繫方式或身份證明信息，則您將處於默認狀態，我們有權暫緩或拒絕向您付款和退款"+"\n"
                +"如果您的帳戶被封鎖或您暫時被阻止訪問您的帳戶，而您仍然與另一名用戶有交易上之義務，我們有權取消未結交易，並代表買家解除此筆交易。" +
                "如果您有欠款，您將收到在減去因您未按照我們的使用者條款採取行動而給我們造成的損失後的相應的金額。"+"\n"
                +"對於每一次違反使用者條款（包括賣家條款）的行為，我們也將能發出約新台幣30000元左右之罰款，但不影響我們追回實際損失的權利。我們試圖在處以罰款之前發出警告。但是對於嚴重侵權行為，我們不會在施加罰款之前發出通知。" +
                "以後每次違反我們的使用者條款，罰款將增加10％。 如果可能的話，我們將根據我們的使用條款抵銷該罰款。"+"\n"
                +"四、\t內部溝通"+"\n"
                +"您可以使用我們的平台聊天系統直接與買家溝通。該系統是安排商品交付或回答買家可能有的任何問題的好方法。請注意，我們不允許您將此系統用於以下活動："+"\n"
                +"發送不請自來的廣告或促銷，捐贈請求或垃圾郵件。"+"\n"
                +"侮辱、騷擾或歧視其他用戶。"+"\n"
                +"在沒有BuyBuyMap中介的情況下與用戶訂立交易協議，目的是避免代購費用等。"+"\n"
                +"五、\t與其他用戶的爭端"+"\n"
                +" 我們希望用戶解決彼此之間的任何爭議。但是，如有必要，我們將盡可能的努力在您和買家之間進行調解。" +
                "因此，您需要盡快向我們報告您與買家之間的任何投訴和糾紛。"+"\n"
                +"買家之間進行調解。因此，您需要盡快向我們報告您與買家之間的任何投訴和糾紛。\n" +
                "聯絡買家：透過點擊使用者名稱，能夠看到該方的個人頁面，頁面中會有聊天按鈕可供買賣雙方進行私訊；或是前往已被下單之訂單頁的左下角按鈕“聯絡對方＂私訊。\n"+"\n"
                +"聯絡BuyBuyMap：通過問題回報像我們發送問題，或撥打平台連絡電話聯繫我們。"+"\n"
                +"即使在我們的支持下，如果您沒有得到滿意的解決方案，我們將擁有最終的決定權。 但是，儘管我們做出了決定，您仍然有權獲取相關權利或直接與買家討論與交易有關的索賠。"+"\n"
                +"六、\t其他"+"\n"
                +"此條款主要針對賣家方面進行說明和規範，若想了解更多詳細的相關資訊請查閱“使用者條款”。"+"\n"

        );

        final RadioButton rb481 = (RadioButton)findViewById(R.id.rb48_1);
        Button btn481 = (Button)findViewById(R.id.btn48_1);

        btn481.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!rb481.isChecked())){
                    Toast.makeText(Personal_setting_becomeSeller.this, "請閱讀並勾選成為代購者的條款，謝謝！",
                            Toast.LENGTH_LONG)
                            .show();
                }
                else
                {
                    Intent intent = new Intent();
                    intent.setClass(Personal_setting_becomeSeller.this, Personal_setting_sellerBasicInformation.class);
                    startActivity(intent);
                }

            }

        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent1 = new Intent();
            intent1.setClass(Personal_setting_becomeSeller.this, Personal_setting.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }
}
