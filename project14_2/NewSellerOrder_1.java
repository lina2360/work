package com.project14.nccu105.project14_2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import static android.graphics.Color.rgb;
import static android.widget.AdapterView.*;

public class NewSellerOrder_1 extends AppCompatActivity {
//    ImageButton ib342;
    ImageView iv331,img;
    CheckBox cbb341,cb342,cb343,cb344,cb345;
    EditText et331,et332, et333, et335,et336,et337,et341,et342,et343;
    TextView tv352,tv354,tv356,tv358,tv3510,tv3512,tv3514,tv3516,tv3529,tv3518,tv3520,tv3522,tv3524,tv3526,tv3531;
    RadioButton rb331,rb332,rb333,rb334,rb335,rb336;
    int count = 0;
    String count1="";
    String nickname="";
    String from="";


    private StorageReference mStorageRef;
    private StorageReference riversRef;
    String[] mynum = new String[1];
    String mypic = "null";

    final String iam= FirebaseAuth.getInstance()
            .getCurrentUser()
            .getEmail();
//    String test="null";
Bundle bundle;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsellerorder_1);
        getSupportActionBar().hide();
        //設定隱藏狀態

        FirebaseStorage storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();

        bundle=getIntent().getExtras();
        mypic=bundle.getString("mypic");
        from=bundle.getString("from");
        if(mypic.contains("null")){

        }
        else{
            riversRef = mStorageRef.child("seller_order").child(mypic);
//                            Log.d("down", "onSuccess: ");
            downloadImg(riversRef);
        }
//
//        final boolean[] m = {false};
//        DatabaseReference[] reference_contacts2 = {FirebaseDatabase.getInstance().getReference("seller_order")};
//        reference_contacts2[0].addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    if(ds.child("1").getValue().equals(iam)){
////                        mynum[0] = ds.child("memberNum").getValue().toString();
//                        mypic[0] = ds.child("picture").getValue().toString();
////                        Log.d("TAG", "onDataChange1: "+mypic[0]);
//                        m[0] =true;
//                    }
//                    if(m[0]==true){
//                        String test=mypic[0];
//                        if(test.contains("null")){}
//                        else{
//                            riversRef = mStorageRef.child("seller_order").child(mypic[0]);
////                            Log.d("down", "onSuccess: ");
//                            downloadImg(riversRef);
//                        }
//                    }
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//            }
//        });

        et331=(EditText)findViewById(R.id.et33_1);
        et332=(EditText)findViewById(R.id.et33_2);
        et333=(EditText)findViewById(R.id.et33_3);
//        et334=(EditText)findViewById(R.id.et33_4);
        et335=(EditText)findViewById(R.id.et33_5);
        et336=(EditText)findViewById(R.id.et33_6);
        et337=(EditText)findViewById(R.id.et33_7);
        rb331=(RadioButton)findViewById(R.id.rb33_1);
        rb332=(RadioButton)findViewById(R.id.rb33_2);
        rb333=(RadioButton)findViewById(R.id.rb33_3);

        rb334=(RadioButton)findViewById(R.id.rb33_4);
        rb335=(RadioButton)findViewById(R.id.rb33_5);
        rb336=(RadioButton)findViewById(R.id.rb33_6);

        cbb341=(CheckBox) findViewById(R.id.rb34_1);//tv3526
        cb342=(CheckBox) findViewById(R.id.rb34_2);
        cb343=(CheckBox) findViewById(R.id.rb34_3);
        cb344=(CheckBox) findViewById(R.id.rb34_4);
        cb345=(CheckBox) findViewById(R.id.rb34_5);


        et341=(EditText)findViewById(R.id.et34_1);
        et342=(EditText)findViewById(R.id.et34_2);
        et343=(EditText)findViewById(R.id.et34_3);

        tv354 = (TextView) findViewById(R.id.tv35_4);
        tv356 = (TextView) findViewById(R.id.tv35_6);
        tv358 = (TextView) findViewById(R.id.tv35_8);
        tv3510 = (TextView) findViewById(R.id.tv35_10);
        tv3512 = (TextView) findViewById(R.id.tv35_12);
        tv3516 = (TextView) findViewById(R.id.tv35_16);
        tv3518 = (TextView) findViewById(R.id.tv35_18);
        tv3520 = (TextView) findViewById(R.id.tv35_20);
        tv3522 = (TextView) findViewById(R.id.tv35_22);
        tv3524 = (TextView) findViewById(R.id.tv35_24);
        tv3529 = (TextView) findViewById(R.id.tv35_29);
        tv3526 = (TextView) findViewById(R.id.tv35_26);
        tv3531 = (TextView) findViewById(R.id.tv35_31);

//        ib342 = (ImageButton) findViewById(R.id.ib34_2);
        iv331=( ImageView)findViewById(R.id.iv33_1);

        img=(ImageView)findViewById(R.id.ib35_1);

        TextView tran = (TextView) findViewById(R.id.tran2);
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




        final Button button11 = (Button) findViewById(R.id.btn33_7);
        final Button button1 = (Button) findViewById(R.id.btn33_8);
        final Button button2 = (Button) findViewById(R.id.btn33_9);
        button11.setBackgroundColor(rgb(255,238,170));

        Button button7 = (Button) findViewById(R.id.btn33_10);
        Button button10 = (Button) findViewById(R.id.btn33_11);

        Button button8 = (Button) findViewById(R.id.btn34_10);
        Button button9 = (Button) findViewById(R.id.btn35_10);

        final RadioButton cb341 = (RadioButton) findViewById(R.id.cb34_1);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        final ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.vs33_1);

        Animation slide_in_left = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation slide_out_right = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        viewFlipper.setInAnimation(slide_in_left);
        viewFlipper.setOutAnimation(slide_out_right);

        Spinner spinner = (Spinner) findViewById(R.id.sp33_1);
        final ArrayAdapter<CharSequence> typeList = ArrayAdapter.createFromResource(NewSellerOrder_1.this,
                R.array.type,
                android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(typeList);
        tv352 = (TextView) findViewById(R.id.tv35_2);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener(){


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv352.setText(typeList.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        Spinner spinner2 = (Spinner) findViewById(R.id.sp33_2);
        final ArrayAdapter<CharSequence> countryList = ArrayAdapter.createFromResource(NewSellerOrder_1.this,
                R.array.country,
                android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(countryList);
        tv3514 = (TextView) findViewById(R.id.tv35_14);
        spinner2.setOnItemSelectedListener(new OnItemSelectedListener(){


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv3514.setText(countryList.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        View.OnClickListener OCL = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v instanceof Button) {
                    int id = ((Button) v).getId();

                    switch (id) {
                        case R.id.btn33_10:
                            button11.setBackgroundColor(rgb(255,255,255));
                            button1.setBackgroundColor(rgb(255,238,170));
                            button2.setBackgroundColor(rgb(255,255,255));
                            viewFlipper.setDisplayedChild(1);
                            break;
                        case R.id.btn33_11:
//                            if(mStorageRef.getParent()==null){
//
//                            }else{
//                                deleteImg(mStorageRef.child(picdb).child(mypic));//*
//                            }
                            Intent intent1 = new Intent();
                            if(from.equals("Seller"))
                                intent1.setClass(NewSellerOrder_1.this, Seller.class);
                            else
                                intent1.setClass(NewSellerOrder_1.this, Forum.class);
                            startActivity(intent1);

                            NewSellerOrder_1.this.finish();
                            break;
                        case R.id.btn34_10:

                            if (cb341.isChecked()) {
                                button11.setBackgroundColor(rgb(255,255,255));
                                button1.setBackgroundColor(rgb(255,255,255));
                                button2.setBackgroundColor(rgb(255,238,170));
                                viewFlipper.setDisplayedChild(2);
                                tv354.setText(et331.getText());
                                tv356.setText(et332.getText());
                                tv358.setText(et333.getText());
//                                tv3510.setText(et334.getText());
                                tv3512.setText(et335.getText());
                                tv3516.setText(et336.getText());
                                tv3518.setText(et337.getText());
                                tv3520.setText(et341.getText());
                                tv3522.setText(et342.getText());
                                tv3524.setText(et343.getText());
                            } else {
                                String msg3410 = "請先勾選'我已閱讀交易條款'，並確實讀過，謝謝！";
                                Toast.makeText(NewSellerOrder_1.this, msg3410, Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case R.id.btn35_10:



                            TextView classify= (TextView)findViewById(R.id.tv35_2);
                            TextView brand = (TextView)findViewById(R.id.tv35_4);
                            TextView name = (TextView)findViewById(R.id.tv35_6);
                            TextView standard = (TextView)findViewById(R.id.tv35_8);

                            TextView url = (TextView)findViewById(R.id.tv35_12);
                            TextView country = (TextView)findViewById(R.id.tv35_14);
                            TextView place = (TextView)findViewById(R.id.tv35_16);
                            TextView other = (TextView)findViewById(R.id.tv35_18);
                            TextView price = (TextView)findViewById(R.id.tv35_20);
                            TextView num = (TextView)findViewById(R.id.tv35_22);
                            TextView fee = (TextView)findViewById(R.id.tv35_24);
                            TextView delivery = (TextView)findViewById(R.id.tv35_26);
                            TextView receipt = (TextView)findViewById(R.id.tv35_29);

                            TextView goodstate = (TextView)findViewById(R.id.tv35_31);
                            if(brand.getText().length()==0||name.getText().length()==0||standard.getText().length()==0||price.getText().length()==0||num.getText().length()==0||fee.getText().length()==0||delivery.getText().length()==0){
                                Toast.makeText(NewSellerOrder_1.this, "請檢查\n1.品牌名稱\n2.商品名稱\n3.商品規格\n4.單價\n5.數量\n6.希望代購費\n7.可交易方式\n有無確實填寫，謝謝！" , Toast.LENGTH_SHORT).show();

                            }
                            else {

                                String msg6 = "送出訂單!";
                                Intent intent6 = new Intent();
//                                if(bundle.getString("from").matches("Seller"))
                                   intent6.setClass(NewSellerOrder_1.this, Seller.class);
//                                else
//                                    intent6.setClass(NewSellerOrder_1.this, Forum.class);

                                  long messageTime = new Date().getTime();
                                  String time=Long.toString(messageTime);

                                // Clear the input

                                FirebaseDatabase.getInstance()
                                        .getReference("seller_order")
                                        .child(time)
                                        .setValue(new SellerOrder_Container(

                                                classify.getText().toString(),
                                                brand.getText().toString(),
                                                name.getText().toString(),
                                                standard.getText().toString(),
                                                goodstate.getText().toString(),
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
                                                receipt.getText().toString()
                                                )


                                        );
//                               FirebaseDatabase.getInstance().getReference("seller_product_chat")
//                                                .push()
//                                                .setValue(time);


//                                else
//                                    intent6.setClass(NewSellerOrder_1.this, Forum.class);
                                startActivity(intent6);
                                Toast.makeText(NewSellerOrder_1.this, msg6, Toast.LENGTH_SHORT).show();
                                NewSellerOrder_1.this.finish();

                            }

                            break;

                        case R.id.btn33_7:
                            // viewSwitcher.showPrevious();切换效果类似
                            button11.setBackgroundColor(rgb(255,238,170));
                            button1.setBackgroundColor(rgb(255,255,255));
                            button2.setBackgroundColor(rgb(255,255,255));
                            viewFlipper.setDisplayedChild(0);
                            break;

                        case R.id.btn33_8:
                            // viewSwitcher.showPrevious();切换效果类似
                            button11.setBackgroundColor(rgb(255,255,255));
                            button1.setBackgroundColor(rgb(255,238,170));
                            button2.setBackgroundColor(rgb(255,255,255));
                            viewFlipper.setDisplayedChild(1);
                            break;
                        case R.id.btn33_9:
                            // viewSwitcher.showPrevious();切换效果类似
                            if (cb341.isChecked()) {
                                button11.setBackgroundColor(rgb(255,255,255));
                                button1.setBackgroundColor(rgb(255,255,255));
                                button2.setBackgroundColor(rgb(255,238,170));
                                viewFlipper.setDisplayedChild(2);
                                tv354.setText(et331.getText());
                                tv356.setText(et332.getText());
                                tv358.setText(et333.getText());
//                                tv3510.setText(et334.getText());
                                tv3512.setText(et335.getText());
                                tv3516.setText(et336.getText());
                                tv3518.setText(et337.getText());
                                tv3520.setText(et341.getText());
                                tv3522.setText(et342.getText());
                                tv3524.setText(et343.getText());
                            } else {
                                String msg3410 = "請檢查定價頁(第二頁)最底下\n'我已閱讀交易條款'\n有無勾起，並確實讀過，謝謝！";
                                Toast.makeText(NewSellerOrder_1.this, msg3410, Toast.LENGTH_SHORT).show();
                            }
                            break;


                    }
                }
                if(v instanceof RadioButton) {
                    int id2 = ((RadioButton) v).getId();
                    switch (id2) {
                        case R.id.rb33_1:
                            tv3529.setText(rb331.getText());
                            break;
                        case R.id.rb33_2:
                            tv3529.setText(rb332.getText());
                            break;
                        case R.id.rb33_3:
                            tv3529.setText(rb333.getText());
                            break;

                        case R.id.rb33_4:
                            tv3531.setText(rb334.getText());
                            break;
                        case R.id.rb33_5:
                            tv3531.setText(rb335.getText());
                            break;
                        case R.id.rb33_6:
                            tv3531.setText(rb336.getText());
                            break;
                    }
                }
                if(v instanceof CheckBox) {
                    int id3 = ((CheckBox) v).getId();
                    String str=tv3526.getText().toString();
                        switch (id3) {
                            case R.id.rb34_1:
                                if(str.isEmpty()&&cbb341.isChecked())
                                {
                                    tv3526.setText(cbb341.getText());
                                }
                                else if (str.contains(cbb341.getText().toString())&&cbb341.isChecked()){
                                }
                                else if(cbb341.isChecked())
                                {
                                    tv3526.append(","+cbb341.getText());
                                }
                                else if(cbb341.isChecked()==false)
                                {
                                    if(str.contains(","+cbb341.getText()))
                                         tv3526.setText( str.replace(","+cbb341.getText(),""));
                                    else if (str.contains(cbb341.getText()))
                                        tv3526.setText(str.replace(cbb341.getText() + ",", ""));


                                }
                                break;
                            case R.id.rb34_2:
                                if(str.isEmpty()&&cb342.isChecked())
                                {
                                    tv3526.setText(cb342.getText());
                                }
                                else if (str.contains(cb342.getText().toString())&&cb342.isChecked()){
                                }
                                else if(cb342.isChecked())
                                {
                                    tv3526.append(","+cb342.getText());
                                }
                                else if(cb342.isChecked()==false)
                                {
                                    if(str.contains(","+cb342.getText()))
                                        tv3526.setText( str.replace(","+cb342.getText(),""));
                                    else if (str.contains(cb342.getText()))
                                        tv3526.setText(str.replace(cb342.getText() + ",", ""));

                                }
                                break;
                            case R.id.rb34_3:
                                if(str.isEmpty()&&cb343.isChecked())
                                {
                                    tv3526.setText(cb343.getText());
                                }
                                else if (str.contains(cb343.getText().toString())&&cb343.isChecked()){
                                }
                                else if(cb343.isChecked())
                                {
                                    tv3526.append(","+cb343.getText());
                                }
                                else if(cb343.isChecked()==false)
                                {
                                    if(str.contains(","+cb343.getText()))
                                        tv3526.setText( str.replace(","+cb343.getText(),""));
                                    else if (str.contains(cb343.getText()))
                                        tv3526.setText(str.replace(cb343.getText() + ",", ""));


                                }
                                break;
                            case R.id.rb34_4:
                                if(str.isEmpty()&&cb344.isChecked())
                                {
                                    tv3526.setText(cb344.getText());
                                }
                                else if (str.contains(cb344.getText().toString())&&cb344.isChecked()){
                                }
                                else if(cb344.isChecked())
                                {
                                    tv3526.append(","+cb344.getText());
                                }
                                else if(cb344.isChecked()==false)
                                {
                                    if(str.contains(","+cb344.getText()))
                                        tv3526.setText( str.replace(","+cb344.getText(),""));
                                    else if (str.contains(cb344.getText()))
                                        tv3526.setText(str.replace(cb344.getText() + ",", ""));

                                }
                                break;
                            case R.id.rb34_5:
                                if(str.isEmpty()&&cb345.isChecked())
                                {
                                    tv3526.setText(cb345.getText());
                                }
                                else if (str.contains(cb345.getText().toString())&&cb345.isChecked()){
                                }
                                else if(cb345.isChecked())
                                {
                                    tv3526.append(","+cb345.getText());
                                }
                                else if(cb345.isChecked()==false)
                                {
                                    if(str.contains(","+cb345.getText()))
                                        tv3526.setText( str.replace(","+cb345.getText(),""));
                                    else if (str.contains(cb345.getText()))
                                        tv3526.setText(str.replace(cb345.getText() + ",", ""));

                                }
                                break;
                        }



                }
                if(cbb341.isChecked() == false&&cb342.isChecked() == false&&cb343.isChecked() == false&&cb344.isChecked() == false&&cb345.isChecked() == false)
                {
                    tv3526.setText("");
                }
                if(v instanceof TextView) {
                    int id4 = ((TextView) v).getId();
                    if(id4==R.id.tran2) {
                        new AlertDialog.Builder(NewSellerOrder_1.this)
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
                    if (id5 == R.id.iv33_1) {
                        Intent intent7 = new Intent();

                        intent7.setClass(NewSellerOrder_1.this, PictureChange.class);
                        Bundle bundle=new Bundle();
                            bundle.putString("mynum", mynum[0]);
                            bundle.putString("mypic",mypic);
                            bundle.putString("from0",from);
                            bundle.putString("from","NewSellerOrder");
                        intent7.putExtras(bundle);
                        startActivity(intent7);
                        NewSellerOrder_1.this.finish();
                    }

                }
            }
        };

        button11.setOnClickListener(OCL);
        button1.setOnClickListener(OCL);
        button2.setOnClickListener(OCL);

        button7.setOnClickListener(OCL);
        button8.setOnClickListener(OCL);
        button9.setOnClickListener(OCL);
        button10.setOnClickListener(OCL);

        rb331.setOnClickListener(OCL);
        rb332.setOnClickListener(OCL);
        rb333.setOnClickListener(OCL);
        rb334.setOnClickListener(OCL);
        rb335.setOnClickListener(OCL);
        rb336.setOnClickListener(OCL);
        cbb341.setOnClickListener(OCL);
        cb342.setOnClickListener(OCL);
        cb343.setOnClickListener(OCL);
        cb344.setOnClickListener(OCL);
        cb345.setOnClickListener(OCL);
        tran.setOnClickListener(OCL);
        iv331.setOnClickListener(OCL);


    }

    private void downloadImg(final StorageReference ref){
        if(ref == null){
            Toast.makeText(NewSellerOrder_1.this, "請先上傳照片", Toast.LENGTH_SHORT).show();
            Log.d("down", "onSuccess: ");
            return;
        }



        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(!isDestroy(NewSellerOrder_1.this)){
                    Glide.with(NewSellerOrder_1.this)

                            .load(ref)
                            .into(iv331);
                    Glide.with(NewSellerOrder_1.this)

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
                intent1.setClass(NewSellerOrder_1.this, Seller.class);
            else
                intent1.setClass(NewSellerOrder_1.this, Forum.class);
            startActivity(intent1);
            NewSellerOrder_1.this.finish();
        }
        return false;
    }

}