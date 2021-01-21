package com.project14.nccu105.project14_2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Date;

import static android.graphics.Color.rgb;

public class NewBuyerOrder_1 extends AppCompatActivity {
//    ImageButton ib302;
    ImageView iv291,img;
    CheckBox cbb301,cb302,cb303,cb304,cb305;
    EditText et291,et292, et293,et294, et295,et296,et297,et301,et302,et303;
    TextView tv312,tv314,tv316,tv318,tv3110,tv3112,tv3114,tv3116,tv3129,tv3118,tv3120,tv3122,tv3124,tv3126;
    RadioButton rb291,rb292,rb293;
    String nickname="";
    private StorageReference mStorageRef;
    private StorageReference riversRef;
    String[] mynum = new String[1];
    String mypic = "null";
    final String iam= FirebaseAuth.getInstance()
            .getCurrentUser()
            .getEmail();
    int count = 0;
    String count1="";
    String from="";
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.newbuyerorder_1);
        getSupportActionBar().hide();
        //設定隱藏狀態

        FirebaseStorage storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();

        Bundle bundle=getIntent().getExtras();
        mypic=bundle.getString("mypic");
        from=bundle.getString("from");


        if(mypic.contains("null")){

        }
        else{
            riversRef = mStorageRef.child("buyer_order").child(mypic);
//                            Log.d("down", "onSuccess: ");
            downloadImg(riversRef);
        }

//        final boolean[] m = {false};
//        DatabaseReference[] reference_contacts2 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
//        reference_contacts2[0].addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    if(ds.child("mail").getValue().equals(iam)){
//                        mynum[0] = ds.child("memberNum").getValue().toString();
//                        mypic = ds.child("picture").getValue().toString();
//                        Log.d("TAG", "onDataChange1: "+mypic);
//                        m[0] =true;
//                    }
//                    if(m[0]==true){
//                        String test=mypic;
//                        if(test.contains("null")){}
//                        else{
//                            riversRef = mStorageRef.child("buyer_order").child(mypic);
//                            Log.d("down", "onSuccess: ");
//                            downloadImg(riversRef);
//                        }
//                    }
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//            }
//        });

        Log.d("test", "ok1");
        et291=(EditText)findViewById(R.id.et29_1);
        et292=(EditText)findViewById(R.id.et29_2);
        et293=(EditText)findViewById(R.id.et29_3);
        et294=(EditText)findViewById(R.id.et29_4);
        et295=(EditText)findViewById(R.id.et29_5);
        et296=(EditText)findViewById(R.id.et29_6);
        et297=(EditText)findViewById(R.id.et29_7);
        rb291=(RadioButton)findViewById(R.id.rb29_1);
        rb292=(RadioButton)findViewById(R.id.rb29_2);
        rb293=(RadioButton)findViewById(R.id.rb29_3);
        cbb301=(CheckBox) findViewById(R.id.rb30_1);//tv3126
        cb302=(CheckBox) findViewById(R.id.rb30_2);
        cb303=(CheckBox) findViewById(R.id.rb30_3);
        cb304=(CheckBox) findViewById(R.id.rb30_4);
        cb305=(CheckBox) findViewById(R.id.rb30_5);

        et301=(EditText)findViewById(R.id.et30_1);
        et302=(EditText)findViewById(R.id.et30_2);
        et303=(EditText)findViewById(R.id.et30_3);

        tv314 = (TextView) findViewById(R.id.tv31_4);
        tv316 = (TextView) findViewById(R.id.tv31_6);
        tv318 = (TextView) findViewById(R.id.tv31_8);
        tv3110 = (TextView) findViewById(R.id.tv31_10);
        tv3112 = (TextView) findViewById(R.id.tv31_12);
        tv3116 = (TextView) findViewById(R.id.tv31_16);
        tv3118 = (TextView) findViewById(R.id.tv31_18);
        tv3120 = (TextView) findViewById(R.id.tv31_20);
        tv3122 = (TextView) findViewById(R.id.tv31_22);
        tv3124 = (TextView) findViewById(R.id.tv31_24);
        tv3129 = (TextView) findViewById(R.id.tv31_29);
        tv3126 = (TextView) findViewById(R.id.tv31_26);

//        ib302 = (ImageButton) findViewById(R.id.ib30_2);

        iv291=( ImageView)findViewById(R.id.iv29_1);

        img = (ImageView) findViewById(R.id.ib31_1);
        TextView tran = (TextView) findViewById(R.id.tran1);
        tran.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        DatabaseReference[] reference_contacts2 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
        reference_contacts2[0].addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

//                    Log.d("TAG", "ds.child(mail)1: "+ds.child("mail").getValue());
                    if(ds.child("mail").getValue().equals(FirebaseAuth.getInstance()
                            .getCurrentUser()
                            .getEmail())){
                        nickname=ds.child("nickname").getValue().toString();

                    }



                }


            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

        final Button button11 = (Button) findViewById(R.id.btn29_7);
        final Button button1 = (Button) findViewById(R.id.btn29_8);
        final Button button2 = (Button) findViewById(R.id.btn29_9);
        button11.setBackgroundColor(rgb(255,238,170));


        Button button7 = (Button) findViewById(R.id.btn29_10);
        Button button10 = (Button) findViewById(R.id.btn29_11);

        Button button8 = (Button) findViewById(R.id.btn30_10);
        Button button9 = (Button) findViewById(R.id.btn31_10);
        Log.d("test", "ok4");
        final RadioButton cb301 = (RadioButton) findViewById(R.id.cb30_1);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        final ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.vs29_1);

        Animation slide_in_left = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation slide_out_right = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        viewFlipper.setInAnimation(slide_in_left);
        viewFlipper.setOutAnimation(slide_out_right);
        Log.d("test", "ok5");
        Spinner spinner = (Spinner)findViewById(R.id.sp29_1);
        final ArrayAdapter<CharSequence> typeList = ArrayAdapter.createFromResource(NewBuyerOrder_1.this,
                R.array.type,
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(typeList);
        tv312 = (TextView) findViewById(R.id.tv31_2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv312.setText(typeList.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Log.d("test", "ok6");

        Spinner spinner2 = (Spinner)findViewById(R.id.sp29_2);
        final ArrayAdapter<CharSequence> countryList = ArrayAdapter.createFromResource(NewBuyerOrder_1.this,
                R.array.country,
                android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(countryList);
        tv3114 = (TextView) findViewById(R.id.tv31_14);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv3114.setText(countryList.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Log.d("test", "ok7");


        View.OnClickListener OCL = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v instanceof Button) {
                    int id = ((Button) v).getId();

                    switch (id) {
                        case R.id.btn29_10:
                            button11.setBackgroundColor(rgb(255, 255, 255));
                            button1.setBackgroundColor(rgb(255,238,170));
                            button2.setBackgroundColor(rgb(255, 255, 255));
                            viewFlipper.setDisplayedChild(1);
                            break;
                        case R.id.btn29_11:
                            Intent intent1 = new Intent();
                            if(from.equals("Seller"))
                                intent1.setClass(NewBuyerOrder_1.this, Seller.class);
                            else
                                intent1.setClass(NewBuyerOrder_1.this, Forum.class);
                            startActivity(intent1);
                            NewBuyerOrder_1.this.finish();
                            break;
                        case R.id.btn30_10:
                            if (cb301.isChecked()) {
                                button11.setBackgroundColor(rgb(255, 255, 255));
                                button1.setBackgroundColor(rgb(255, 255, 255));
                                button2.setBackgroundColor(rgb(255,238,170));
                                viewFlipper.setDisplayedChild(2);
                                tv314.setText(et291.getText());
                                tv316.setText(et292.getText());
                                tv318.setText(et293.getText());
                                tv3110.setText(et294.getText());
                                tv3112.setText(et295.getText());
                                tv3116.setText(et296.getText());
                                tv3118.setText(et297.getText());
                                tv3120.setText(et301.getText());
                                tv3122.setText(et302.getText());
                                tv3124.setText(et303.getText());
                                if(et301.getText().length()==0){
                                    tv3120.setText("旅人出價");
                                }
                                if(et303.getText().length()==0){
                                    tv3124.setText("旅人出價");
                                }
                            } else {
                                String msg3010 = "請先勾選'我已閱讀交易條款'，並確實讀過，謝謝！";
                                Toast.makeText(NewBuyerOrder_1.this, msg3010, Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case R.id.btn31_10:



                            final TextView classify = (TextView) findViewById(R.id.tv31_2);
                            final TextView brand = (TextView) findViewById(R.id.tv31_4);
                            final TextView name = (TextView) findViewById(R.id.tv31_6);
                            final TextView standard = (TextView) findViewById(R.id.tv31_8);
                            final TextView model = (TextView) findViewById(R.id.tv31_10);
                            final TextView url = (TextView) findViewById(R.id.tv31_12);
                            final TextView country = (TextView) findViewById(R.id.tv31_14);
                            final TextView place = (TextView) findViewById(R.id.tv31_16);
                            final TextView other = (TextView) findViewById(R.id.tv31_18);
                            final TextView price = (TextView) findViewById(R.id.tv31_20);
                            final TextView num = (TextView) findViewById(R.id.tv31_22);
                            final TextView fee = (TextView) findViewById(R.id.tv31_24);
                            final TextView delivery = (TextView) findViewById(R.id.tv31_26);
                            if (brand.getText().length()==0 || name.getText().length()==0 || standard.getText().length()==0  || num.getText().length()==0 ||delivery.getText().length()==0) {
                                Toast.makeText(NewBuyerOrder_1.this, "請檢查\n1.品牌名稱\n2.商品名稱\n3.商品規格\n4.數量\n5.可交易方式\n有無確實填寫，謝謝！", Toast.LENGTH_SHORT).show();

                            } else  {

                                String msg6 = "送出訂單!";
                                Intent intent6 = new Intent();


//                                if(from.equals("Seller"))
                                    intent6.setClass(NewBuyerOrder_1.this, Seller.class);
//                                else
//                                    intent6.setClass(NewBuyerOrder_1.this, Forum.class);

                                long messageTime = new Date().getTime();
                                String time=Long.toString(messageTime);

                                // Clear the input

                                FirebaseDatabase.getInstance()
                                        .getReference("buyer_order")
                                        .child(time)
                                        .setValue(new BuyerOrder_Container(

                                                classify.getText().toString(),
                                                brand.getText().toString(),
                                                name.getText().toString(),
                                                standard.getText().toString(),
                                                model.getText().toString(),
                                                url.getText().toString(),
                                                country.getText().toString(),
                                                place.getText().toString(),
                                                other.getText().toString(),
                                                price.getText().toString(),
                                                num.getText().toString(),
                                                fee.getText().toString(),
                                                delivery.getText().toString(),
                                                FirebaseAuth.getInstance()
                                                        .getCurrentUser()
                                                        .getEmail()
                                                ,messageTime,
                                                mypic,
                                                nickname,
                                                tv3129.getText().toString()
                                                )

                                        );




                                startActivity(intent6);
                                Toast.makeText(NewBuyerOrder_1.this, msg6, Toast.LENGTH_SHORT).show();
                                NewBuyerOrder_1.this.finish();
                            }
                            break;
                        case R.id.btn29_7:
                            // viewSwitcher.showPrevious();切换效果类似
                            button11.setBackgroundColor(rgb(255,238,170));
                            button1.setBackgroundColor(rgb(255,255,255));
                            button2.setBackgroundColor(rgb(255,255,255));
                            viewFlipper.setDisplayedChild(0);
                            break;
                        case R.id.btn29_8:
                            // viewSwitcher.showPrevious();切换效果类似
                            button11.setBackgroundColor(rgb(255,255,255));
                            button1.setBackgroundColor(rgb(255,238,170));
                            button2.setBackgroundColor(rgb(255,255,255));
                            viewFlipper.setDisplayedChild(1);
                            break;
                        case R.id.btn29_9:
                            // viewSwitcher.showPrevious();切换效果类似
                            if (cb301.isChecked()) {
                                button11.setBackgroundColor(rgb(255,255,255));
                                button1.setBackgroundColor(rgb(255,255,255));
                                button2.setBackgroundColor(rgb(255,238,170));
                                viewFlipper.setDisplayedChild(2);
                                tv314.setText(et291.getText());
                                tv316.setText(et292.getText());
                                tv318.setText(et293.getText());
                                tv3110.setText(et294.getText());
                                tv3112.setText(et295.getText());
                                tv3116.setText(et296.getText());
                                tv3118.setText(et297.getText());
                                tv3120.setText(et301.getText());
                                tv3122.setText(et302.getText());
                                tv3124.setText(et303.getText());
                                if(et301.getText().length()==0){
                                    tv3120.setText("旅人出價");
                                }
                                if(et303.getText().length()==0){
                                    tv3124.setText("旅人出價");
                                }
                            } else {
                                String msg3010 = "請檢查定價頁(第二頁)最底下\n'我已閱讀交易條款'\n有無勾起，並確實讀過，謝謝！";
                                Toast.makeText(NewBuyerOrder_1.this, msg3010, Toast.LENGTH_SHORT).show();
                            }
                            break;
                    }
                }
                if (v instanceof RadioButton) {
                    int id2 = ((RadioButton) v).getId();
                    switch (id2) {
                        case R.id.rb29_1:
                            tv3129.setText(rb291.getText());
                            break;
                        case R.id.rb29_2:
                            tv3129.setText(rb292.getText());
                            break;
                        case R.id.rb29_3:
                            tv3129.setText(rb293.getText());
                            break;
                    }
                }
                if (v instanceof CheckBox) {
                    int id3 = ((CheckBox) v).getId();
                    String str = tv3126.getText().toString();
                    switch (id3) {
                        case R.id.rb30_1:
                            if (str.isEmpty() && cbb301.isChecked()) {
                                tv3126.setText(cbb301.getText());
                            } else if (str.contains(cbb301.getText().toString()) && cbb301.isChecked()) {
                            } else if (cbb301.isChecked()) {
                                tv3126.append("," + cbb301.getText());
                            } else if (cbb301.isChecked() == false) {
                                if (str.contains("," + cbb301.getText()))
                                    tv3126.setText(str.replace("," + cbb301.getText(), ""));
                                else  if (str.contains(cbb301.getText()))
                                    tv3126.setText(str.replace(cbb301.getText() + ",", ""));

                            }
                            break;
                        case R.id.rb30_2:
                            if (str.isEmpty() && cb302.isChecked()) {
                                tv3126.setText(cb302.getText());
                            } else if (str.contains(cb302.getText().toString()) && cb302.isChecked()) {
                            } else if (cb302.isChecked()) {
                                tv3126.append("," + cb302.getText());
                            } else if (cb302.isChecked() == false) {
                                if (str.contains("," + cb302.getText()))
                                    tv3126.setText(str.replace("," + cb302.getText(), ""));
                                else   if (str.contains(cb302.getText()))
                                    tv3126.setText(str.replace(cb302.getText() + ",", ""));

                            }
                            break;
                        case R.id.rb30_3:
                            if (str.isEmpty() && cb303.isChecked()) {
                                tv3126.setText(cb303.getText());
                            } else if (str.contains(cb303.getText().toString()) && cb303.isChecked()) {
                            } else if (cb303.isChecked()) {
                                tv3126.append("," + cb303.getText());
                            } else if (cb303.isChecked() == false) {
                                if (str.contains("," + cb303.getText()))
                                    tv3126.setText(str.replace("," + cb303.getText(), ""));
                                else if (str.contains(cb303.getText()))
                                    tv3126.setText(str.replace(cb303.getText() + ",", ""));

                            }
                            break;
                        case R.id.rb30_4:
                            if (str.isEmpty() && cb304.isChecked()) {
                                tv3126.setText(cb304.getText());
                            } else if (str.contains(cb304.getText().toString()) && cb304.isChecked()) {
                            } else if (cb304.isChecked()) {
                                tv3126.append("," + cb304.getText());
                            } else if (cb304.isChecked() == false) {
                                if (str.contains("," + cb304.getText()))
                                    tv3126.setText(str.replace("," + cb304.getText(), ""));
                                else if (str.contains(cb304.getText()))
                                    tv3126.setText(str.replace(cb304.getText() + ",", ""));

                            }
                            break;
                        case R.id.rb30_5:
                            if (str.isEmpty() && cb305.isChecked()) {
                                tv3126.setText(cb305.getText());
                            } else if (str.contains(cb305.getText().toString()) && cb305.isChecked()) {
                            } else if (cb305.isChecked()) {
                                tv3126.append("," + cb305.getText());
                            } else if (cb305.isChecked() == false) {
                                if (str.contains("," + cb305.getText()))
                                    tv3126.setText(str.replace("," + cb305.getText(), ""));
                                else if (str.contains(cb305.getText()))
                                    tv3126.setText(str.replace(cb305.getText() + ",", ""));

                            }
                            break;
                    }
                }
                if(cbb301.isChecked() == false&&cb302.isChecked() == false&&cb303.isChecked() == false&&cb304.isChecked() == false&&cb305.isChecked() == false)
                {
                    tv3126.setText("");
                }




                if (v instanceof TextView) {
                    int id4 = ((TextView) v).getId();
                    if (id4 == R.id.tran1) {
                        new AlertDialog.Builder(NewBuyerOrder_1.this)
                                .setTitle("使用者條款")
                                .setMessage("★政策條款★\n"
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
                                        + "\t*本網站保有接受會員(買家) 訂單與否的權利。所有於本網站所進行之線上消費，會員(買家) 應同意以本網站所紀錄之電子交易資料與通訊軟體對話紀錄為準。如有糾紛，並以該電子交易資料與通訊軟體對話紀錄為認定標準。會員(買家) 如果發現交易資料不正確，應立即通知本網站。")
                                .setPositiveButton("關閉視窗", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                                .show();
                    }


                }

                if (v instanceof ImageView) {
                    int id5 = ((ImageView) v).getId();
                    if (id5 == R.id.iv29_1) {
                        Intent intent7 = new Intent();

                        intent7.setClass(NewBuyerOrder_1.this, PictureChange.class);
                        Bundle bundle=new Bundle();
                            bundle.putString("mynum", mynum[0]);
                            bundle.putString("mypic",mypic);
                            bundle.putString("from0",from);
                            bundle.putString("from","NewBuyerOrder");
                        intent7.putExtras(bundle);
                        startActivity(intent7);
                        NewBuyerOrder_1.this.finish();

                    }

                }
            }

        };
//        Log.d("test", "ok9");
         button11.setOnClickListener(OCL);
        button1.setOnClickListener(OCL);
        button2.setOnClickListener(OCL);

        button7.setOnClickListener(OCL);
        button8.setOnClickListener(OCL);
        button9.setOnClickListener(OCL);
        button10.setOnClickListener(OCL);
        rb291.setOnClickListener(OCL);
        rb292.setOnClickListener(OCL);
        rb293.setOnClickListener(OCL);
        cbb301.setOnClickListener(OCL);
        cb302.setOnClickListener(OCL);
        cb303.setOnClickListener(OCL);
        cb304.setOnClickListener(OCL);
        cb305.setOnClickListener(OCL);
//        ib302.setOnClickListener(OCL);
        iv291.setOnClickListener(OCL);
        tran.setOnClickListener(OCL);
    }

    private void downloadImg(final StorageReference ref){
        if(ref == null){
            Toast.makeText(NewBuyerOrder_1.this, "請先上傳照片", Toast.LENGTH_SHORT).show();
            Log.d("down", "onSuccess: ");
            return;
        }



        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(!isDestroy(NewBuyerOrder_1.this)){
                    Glide.with(NewBuyerOrder_1.this)

                            .load(ref)
                            .into(iv291);

                    Glide.with(NewBuyerOrder_1.this)

                            .load(ref)
                            .into(img);

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
            }
        });
    }
    public static boolean isDestroy(Activity activity) {
        if (activity == null || activity.isFinishing() || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent1 = new Intent();
            if(from.equals("Seller"))
                intent1.setClass(NewBuyerOrder_1.this, Seller.class);
            else
                intent1.setClass(NewBuyerOrder_1.this, Forum.class);
            startActivity(intent1);
            NewBuyerOrder_1.this.finish();
        }
        return false;
    }
}