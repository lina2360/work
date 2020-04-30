package com.project14.nccu105.project14_2;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.format.DateFormat;
import android.transition.Slide;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class Notify extends AppCompatActivity {
    int j;
    Button btn71,btn72,btn73,btn74,btn76,btn77,tv76;
    String mynickname="";
    final String iam= FirebaseAuth.getInstance()
            .getCurrentUser()
            .getEmail();
    boolean m = false;
    String mynum = "";
    private int[] image = {
            R.drawable.notify1, R.drawable.notify2, R.drawable.notify3
    };
    private String[] imgText = {
            "訂單已送達", "訂單已出貨", "訂單已接受"
    };
    private int[] image3 = {
            R.drawable.notify4
    };
    private String[] imgText3 = {
            "您有oo的訊息通知！"
    };

    private StorageReference mStorageRef;
    private StorageReference riversRef;
    int count=1;
    FirebaseListAdapter< ChatMessage> adapter;
    List<Map<String, Object>> items;
    List<Map<String, Object>> sortitems;
    private void setupWindowAnimations() {
        Slide slide = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            slide = new Slide();

            getWindow().setEnterTransition(slide);
        }
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notify);
        setupWindowAnimations();
        getSupportActionBar().hide();
        //設定隱藏狀態
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

        ListView listview1 = (ListView) findViewById(R.id.lv7_1);
        //ListView 要顯示的內容
        String[] str1 = {"主題活動","動態通知","系統通知"};
        int[] img1 = { R.drawable.ic_coupon, R.drawable.ic_noti, R.drawable.ic_bag};

        List<Map<String, Object>> items1 = new ArrayList<Map<String,Object>>();
        for (int i = 0; i < str1.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("image", img1[i]);
            item.put("text", str1[i]);
            items1.add(item);
        }
        //android.R.layout.simple_list_item_1 為內建樣式，還有其他樣式可自行研究
        SimpleAdapter adapter1 = new SimpleAdapter(this, items1,
                R.layout.listview4,new String[]{"image", "text"}, new int[]{R.id.notifyimg, R.id.notifytext}
        );
        listview1.setAdapter(adapter1);
        Utility.setListViewHeightBasedOnChildren(listview1);

//        //android.R.layout.simple_list_item_1 為內建樣式，還有其他樣式可自行研究
//        ArrayAdapter adapter1 = new ArrayAdapter(this,
//                android.R.layout.simple_list_item_1,
//                str1);
//        listview1.setAdapter(adapter1);

        listview1.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch ((int) id){
                            case 0:
                                Intent intent8 = new Intent();
                                intent8.setClass(Notify.this, Notify_activity.class);
                                startActivity(intent8);
                                Notify.this.finish();
                                break;
                            case 1:
                                Intent intent9 = new Intent();
                                intent9.setClass(Notify.this, Notify_anotify.class);
                                startActivity(intent9);
                                Notify.this.finish();
                                break;
                            case 2:
                                Intent intent7 = new Intent();
                                intent7.setClass(Notify.this, Notify_system.class);
                                startActivity(intent7);
                                Notify.this.finish();
                                break;

}

                    }
                }
        );


        final ListView listview2 = (ListView) findViewById(R.id.lv7_2);

//        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//
//                final ArrayList<MyBuy> list = new ArrayList<MyBuy>();
//                //new一個Bundle物件，並將要傳遞的資料傳入
//                DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_file").child(mynum).child("mybuy")};
//                reference_contacts[0].addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        int count=0;
//                        for (DataSnapshot ds : dataSnapshot.getChildren() ) {
////                           list = new ArrayList<Article>();
////                           list.set(0, new ArrayList<Article>());
//
//                            MyBuy article = ds.getValue(MyBuy.class);
//                            list.add(article);
//
//                        }
////                        if(list.get(position).getKind().equals("【購物】")||list.get(position).getKind().equals("【下單】")){
//                            Intent intent7 = new Intent();
//                            intent7.setClass(Notify.this, Order.class);
//                            Bundle bundle = new Bundle();
////                            bundle.putLong("orderNum", list.get(position).getPrice());
//                            bundle.putString("orderNum", list.get(position).getOrderNum());
//                            bundle.putString("orderkind", list.get(position).getKind());
//                            bundle.putString("orderpic", list.get(position).getPicture());
//                            bundle.putString("usernic", list.get(position).getUsernick());
//                            bundle.putString("user", list.get(position).getSeller());
//                            intent7.putExtras(bundle);
//                            startActivity(intent7);
//                            Notify.this.finish();
////                        }
//
//                    }
//
//
//
//
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
////                if(list.get(position).getKind().equals("【購物】")) {
//////                    Notify.this.finish();
////                }
//
//            }
//        });





//                final ArrayList<Article> list = new ArrayList<Article>();
        //new一個Bundle物件，並將要傳遞的資料傳入
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
                        mynickname=ds.child("nickname").getValue().toString();
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
                    if (myBuys2.size()>3){
                     size =3;
                    }else{
                        size=myBuys2.size();
                    }


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

                    searchAdapter=new SearchAdapter(Notify.this,items,"null");
                    listview2.setAdapter(searchAdapter);
                    listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                            Intent intent7 = new Intent();
                            intent7.setClass(Notify.this, Order.class);
                            Bundle bundle = new Bundle();
//                            bundle.putLong("orderNum", list.get(position).getPrice());
                            bundle.putString("orderNum", myBuys2.get(position).getOrderNum());
                            bundle.putString("orderkind", myBuys2.get(position).getKind());
                            bundle.putString("orderpic", myBuys2.get(position).getPicture());
                            bundle.putString("usernic", myBuys2.get(position).getUsernick());
                            bundle.putString("user", myBuys2.get(position).getSeller());

                            intent7.putExtras(bundle);
                            startActivity(intent7);
                            finish();

                        }
                    });

//                        Toast.makeText(Notify.this, "大小"+myBuys., Toast.LENGTH_LONG).show();
//                    Utility.setListViewHeightBasedOnChildren(listview2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        final ListView listview3 = (ListView) findViewById(R.id.lv7_3);

        final ArrayList<String> list = new ArrayList<String>();
        final ArrayList<String> who = new ArrayList<String>();
        final ArrayList<String> state = new ArrayList<String>();
        final ArrayList<Long> time = new ArrayList<Long>();
        final ArrayList<String> user1 = new ArrayList<String>();
        final ArrayList<String> user2 = new ArrayList<String>();
        DatabaseReference[] reference_contacts4 = {FirebaseDatabase.getInstance().getReference("chatroom")};
        reference_contacts4[0].addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren() ) {


                    String temp=ds.getKey();

                    if(ds.child("user1").getValue().toString().equals(iam)||ds.child("user2").getValue().toString().equals(iam)){
//                        ChatMessage chat = ds.getValue(ChatMessage.class);

                        int tempcount=0;
                        int num=1;

                        if(ds.hasChild("chat")){
                            list.add(temp);
                            user1.add(ds.child("user1").getValue().toString());
                            user2.add(ds.child("user2").getValue().toString());

                            for (DataSnapshot dds:ds.child("chat").getChildren()){
                                if(!dds.child("useracc").getValue().toString().equals(iam)&&tempcount==0){
                                    who.add(dds.child("messageUser").getValue().toString());
                                    tempcount=1;
                                }
                                else if(num==ds.child("chat").getChildrenCount()&&tempcount==0){
//                                    if(ds.child("user1").getValue().toString().equals(iam))
//                                        who.add(ds.child("user2").getValue().toString());
//                                        else
//                                        ds.child("user1").getValue().toString();
                                    who.add("對方還未接受對話");
                                    time.add(Long.parseLong(dds.child("messageTime").getValue().toString()));
                                    state.add("還未回應");
                                }

                                if(num==ds.child("chat").getChildrenCount()&&tempcount==1&&!dds.child("useracc").getValue().toString().equals(iam)){
                                    state.add("有新訊息！");
                                    time.add(Long.parseLong(dds.child("messageTime").getValue().toString()));
                                }
                                else if(num==ds.child("chat").getChildrenCount()&&tempcount==1&&dds.child("useracc").getValue().toString().equals(iam)){
                                    state.add("還未回應");
                                    time.add(Long.parseLong(dds.child("messageTime").getValue().toString()));
                                }


                                num++;
                                Log.d("loop", "onDataChange: ."+"tempcount="+tempcount+" tmep="+temp+" childcount="+ds.getChildrenCount());
                            }


                        }

                    }
                }

                    items = new ArrayList<Map<String,Object>>();

//                Log.d("look_notify", "listsize="+list.size()+" statesize="+state.size()+" "+state.get(0));
                    for (int i = 0; i < list.size(); i++) {

                        Map<String, Object> item = new HashMap<String, Object>();
//                        Log.d("whyqq", "onDataChange: "+state.get(i));
                        item.put("which", list.get(i));
                        item.put("who",who.get(i));
                        item.put("user1",user1.get(i));
                        item.put("user2",user2.get(i));
                        item.put("text", state.get(i));
                        item.put("time", time.get(i));
                        items.add(item);
                    }
                    sortitems= new ArrayList<Map<String,Object>>();
                    sortitems=SelectionSort(items,items.size());

                    SimpleAdapter adapter2 = new SimpleAdapter(Notify.this, sortitems,
                            R.layout.listview1,new String[]{"text","who"}, new int[]{R.id.listtext,R.id.message_member}
                    );

                listview3.setAdapter(adapter2);
                Utility.setListViewHeightBasedOnChildren(listview3);
                listview3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String msg7 = "往個人頁";
                        Intent intent7 = new Intent();
                        Bundle bundle=new Bundle();
                        bundle.putString("from","Notify");
                        bundle.putString("from2","Notify");
                        bundle.putString("user1",sortitems.get((int)id).get("user1").toString());
                        bundle.putString("user2",sortitems.get((int)id).get("user2").toString());
                        bundle.putString("recognize",sortitems.get((int)id).get("which").toString());
                        intent7.putExtras(bundle);
                        intent7.setClass(Notify.this, ChatRoom2.class);
                        startActivity(intent7);
//                        Toast.makeText(Notify.this, msg6, Toast.LENGTH_SHORT).show();
                        finish();

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        btn71 = (Button) findViewById(R.id.btn7_1);
//        btn72 = (Button) findViewById(R.id.btn7_2);
        btn73 = (Button) findViewById(R.id.btn7_3);
        btn74 = (Button) findViewById(R.id.btn7_4);
        btn76= (Button) findViewById(R.id.btn7_6);
        btn77= (Button) findViewById(R.id.btn7_7);
        tv76= (Button) findViewById(R.id.tv7_6);

        View.OnClickListener OCL = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (v instanceof Button) {
                    int id = ((Button) v).getId();
                    switch (id) {

                        case R.id.btn7_1:
                            String msg1 = "往論壇頁";
                            Intent intent1 = new Intent();
                            intent1.setClass(Notify.this, Forum.class);
                            startActivity(intent1);
                            finish();

                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            break;
//4>2 4>1 3>2 3>1 2>1
                        case R.id.btn7_3:
                            String msg3 = "往接單頁";
                            Intent intent3 = new Intent();
                            intent3.setClass(Notify.this, Seller.class);
                            startActivity(intent3);
                            finish();

                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            break;
                        case R.id.btn7_4:
                            String msg4 = "往地圖頁";
                            Intent intent4 = new Intent();
                            intent4.setClass(Notify.this, Maps.class);
                            startActivity(intent4);
                            finish();

                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            break;
                        case R.id.btn7_6:
                            String msg6 = "往個人頁";
                            Intent intent6 = new Intent();
                            intent6.setClass(Notify.this, Personal.class);
                            startActivity(intent6);
                            finish();

                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            break;
                        case R.id.btn7_7:
//                            String msg6 = "往個人頁";
                            Intent intent7 = new Intent();
                            intent7.setClass(Notify.this, MoreMyBuy.class);
                            finish();
                            startActivity(intent7);
//                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            break;
                        case R.id.tv7_6:
//                            String msg6 = "往個人頁";
                            Intent intent8 = new Intent();
                            intent8.setClass(Notify.this, MoreMyChat.class);
                            Bundle bundle8=new Bundle();
                            bundle8.putString("me",mynickname);
                            bundle8.putString("mynum",mynum);
                            intent8.putExtras(bundle8);
                            finish();
                            startActivity(intent8);
//                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            break;

                    }
                }

            }
        };


        btn71.setOnClickListener(OCL);
//        btn72.setOnClickListener(OCL);
        btn73.setOnClickListener(OCL);
        btn74.setOnClickListener(OCL);
        btn76.setOnClickListener(OCL);
        btn77.setOnClickListener(OCL);
        tv76.setOnClickListener(OCL);



    }

    private void downloadImg(final StorageReference ref, final ImageView img){
        if(ref == null){
            Toast.makeText(Notify.this, "請先上傳照片", Toast.LENGTH_SHORT).show();
            Log.d("down", "onSuccess: ");
            return;
        }



        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(!isDestroy(Notify.this)){
                    Glide.with(Notify.this)

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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchview, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return false;
    }


    public  List<Map<String, Object>> SelectionSort(  List<Map<String, Object>> list,int n){//只用num
        int max;
        long[] A=new long[list.size()];
        for(int i=0;i<list.size();i++)
        {
            A[i]=Long.parseLong(list.get(i).get("time").toString());
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
        List<Map<String, Object>> num2 = new ArrayList<Map<String,Object>>();
        for(int i=0;i<list.size();i++)
        {
            for(int k=0;k<list.size();k++){
                if (A[i]==Long.parseLong(list.get(k).get("time").toString())){

                        num2.add(list.get(k));

                }
            }

        }

        return  num2;


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

