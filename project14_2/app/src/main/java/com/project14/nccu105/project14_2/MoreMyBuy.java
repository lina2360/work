package com.project14.nccu105.project14_2;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoreMyBuy extends AppCompatActivity {
    int j;
    ImageButton ib51;
    private ListView show;
    final String iam= FirebaseAuth.getInstance()
            .getCurrentUser()
            .getEmail();
    String mynum = "";
    private StorageReference mStorageRef;
    private StorageReference riversRef;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moremybuy);
//        getSupportActionBar().hide();
        //設定隱藏狀態

        FirebaseStorage storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar51);
        toolbar.setTitle("正在處理的訂單");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(MoreMyBuy.this, Notify.class);
                startActivity(intent1);
                finish();
            }
        });

//        ib40 = (ImageButton) findViewById(R.id.ib40_1);
        show = (ListView) findViewById(R.id.lv51);



        DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_file")};
        reference_contacts[0].addValueEventListener(new ValueEventListener() {
            boolean m=false;
            ArrayList<MyBuy> myBuys= new ArrayList<MyBuy>();
            List<Long> time = new ArrayList<Long>();

            List<Long> time2 = new ArrayList<Long>();
            ArrayList<MyBuy> myBuys2= new ArrayList<MyBuy>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren() ) {
                    if(ds.child("mail").getValue().equals(iam)){
                        mynum = ds.child("memberNum").getValue().toString();
//                        mynick
                        m =true;

                        for(DataSnapshot dds : ds.child("mybuy").getChildren()){
                            MyBuy buy = dds.getValue(MyBuy.class);
                            myBuys.add(buy);
                            time.add(Long.parseLong(buy.getRenewTime()));

                        }


                    }

                }


                if(m=true){
                    time2=SelectionSort2(time,time.size());

                    for(j=0 ;j<time2.size();j++){
                        for(int k=0;k<myBuys.size();k++){
                            if(time2.get(j)==Long.parseLong(myBuys.get(k).getRenewTime()))
                                myBuys2.add(myBuys.get(k));
                        }

                    }
//                    Toast.makeText(Notify.this, "大小"+myBuys2.size(), Toast.LENGTH_SHORT).show();


                    List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
                    int size;

                        size=myBuys2.size();



                    for (int i = 0; i < size; i++) {

                        Map<String, Object> item = new HashMap<String, Object>();
                        item.put("from","Notify");
                        item.put("image",myBuys2.get(i).getPicture() );
                        item.put("ordernum", myBuys2.get(i).getOrderNum());
                        item.put("orderstate",myBuys2.get(i).getOrderState() );
                        item.put("kind", myBuys2.get(i).getKind());
                        item.put("ordertime",myBuys2.get(i).getOrderTime());

                        items.add(item);
                    }
                    SearchAdapter searchAdapter;

                    searchAdapter=new SearchAdapter(MoreMyBuy.this,items,"null");
                    show.setAdapter(searchAdapter);

                    show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                            Intent intent7 = new Intent();
                            intent7.setClass(MoreMyBuy.this, Order.class);
                            Bundle bundle = new Bundle();
//                            bundle.putLong("orderNum", list.get(position).getPrice());
                            bundle.putString("orderNum", myBuys2.get(position).getOrderNum());
                            bundle.putString("orderkind", myBuys2.get(position).getKind());
                            bundle.putString("orderpic", myBuys2.get(position).getPicture());
                            bundle.putString("usernic", myBuys2.get(position).getUsernick());
                            bundle.putString("user", myBuys2.get(position).getSeller());

                            intent7.putExtras(bundle);
                            startActivity(intent7);
                            MoreMyBuy.this.finish();

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//        int[] image = {
//                R.drawable.notify1, R.drawable.notify2, R.drawable.notify3,R.drawable.notify1, R.drawable.notify2, R.drawable.notify3,R.drawable.notify1, R.drawable.notify2, R.drawable.notify3,R.drawable.notify1
//        };

//        String[] str1 = {"★夏普滿萬舒馬再折 $666\n一起對抗炎熱，夏普沁涼風扇、空氣清淨、除溼直降85折up，挑戰市場最低價，滿萬輸碼再折 $666，手刀逛逛去！",
//                "★$1抽環遊世界雙人行\n$1奪寶戰送你去環遊世界，5/20 ~ 5/31每天加碼抽各國機票，巴黎、荷蘭、美國等機票只要 $1！快點隨我奪寶去！",
//                "★現領稅月加菜金，最高限折$2,900\n老闆佛心大加碼，全站滿 $2,000打9折最高折 $500，商城滿 $20,000現折$2,400，更有隱藏版無門檻9折券，偷偷跟你說快到『商城最低價』活動業尋找！",
//                "★24h生活館瘋搶5折券！\n24h生活館618年中慶，天天瘋搶5折券！滿 $618再折 $100、滿 $399再折 $50，全場下殺4折up！吃的、用的、喝的、玩的、想省錢的，通通都在這！",
//                "★閃購日限時免運通知\n6/6全家便利商店限定，限時特賣滿 $99免運！鎖定12:00、18:00、22:00搶現折$2,700購物金，United Athle素面短T下殺 $190！前往搶好康！",
//                "★20萬現金與豪宅住一年等你搶！\n618大樂透，20萬現金與冠德千萬豪宅住一年等你搶，6/15 ~ 6/18期間，完成指定任務就有機會抽中現金20萬、冠德千萬豪宅住一年！詳情請見活動頁面！",
//                "★全品項1折起，另想快速到貨！\n22:00準時搶優惠券，滿 $499現折 $40、滿萬現折 $1,100！熱銷BCL早安面膜 $279、小米空氣清淨機2S $3980，下單及想快速到貨，立即搶購！",
//                "★商城最低價通知12點、18點、22點搶購物金，PS4 hits+王國之心3+第二支手把 $11,399！惠而浦大廈扇 $2,880！威技台製除溼機 $3,888！買貴退10倍！",
//                "★大熱氣球出現危機？測你近期最大煩惱\n熱氣球機將墜落！你會先丟下什麼？測你近期最大煩惱，再抽契爾氏金盞花化妝水500ml(市價 $2,031)，快來店裡留言！",
//                "★獨家新品！理膚寶水淨痘無暇潤色組\n夏日偽素顏必敗組，淨痘無暇潤色組，打造裸妝感，修飾抗痘雙重功效！12小時急速修飾皮膚瑕疵，長時間預防痘痘復發，滿額最高再折 $300！"};
//        List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
//        for (int i = 0; i < str1.length; i++) {
//            Map<String, Object> item = new HashMap<String, Object>();
//            item.put("image", image[i]);
//            item.put("text", str1[i]);
//            items.add(item);
//        }
        //android.R.layout.simple_list_item_1 為內建樣式，還有其他樣式可自行研究
//        SimpleAdapter adapter = new SimpleAdapter(this, items,
//                R.layout.listview1,new String[]{"image", "text"}, new int[]{R.id.listimg, R.id.listtext}
//        );
//        show.setAdapter(adapter);



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

    private void downloadImg(final StorageReference ref, final ImageView img){
        if(ref == null){
            Toast.makeText(MoreMyBuy.this, "請先上傳照片", Toast.LENGTH_SHORT).show();
            Log.d("down", "onSuccess: ");
            return;
        }



        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(!isDestroy(MoreMyBuy.this)){
                    Glide.with(MoreMyBuy.this)

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
            intent1.setClass(MoreMyBuy.this, Notify.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }


    public  List<Long> SelectionSort2(  List<Long> list,int n){//只用num
        int max;
        long[] A=new long[list.size()];
        for(int i=0;i<list.size();i++)
        {
            A[i]=list.get(i);
        }
        for(int i=0 ;i<n-1;i++){
            max=i;
            for(int j=(i+1);j<n;j++){
                if(A[j]>A[max]){
                    max=j;
                }

            }
            if(max!=i){

                long temp=A[i];
                A[i]=A[max];
                A[max]=temp;
            }
        }
        List<Long>  num2 =new ArrayList<Long>();
        for(int i=0;i<list.size();i++)
        {
            for(int k=0;k<list.size();k++){
                if (A[i]==list.get(k)){

                    num2.add(list.get(k));

                }
            }

        }

        return  num2;


    }



}

