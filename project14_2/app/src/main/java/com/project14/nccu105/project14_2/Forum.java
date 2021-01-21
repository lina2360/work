package com.project14.nccu105.project14_2;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//6
public class Forum  extends AppCompatActivity {
    Button btn62,btn63,btn64,btn65,btn66,btn67,btn68;
    ImageView iv61,iv62,iv63,ib68,ib69;
    Gallery buyerorder ;
    Gallery sellerorder ;
    Intent intent;
    Slide slide = null;
    private StorageReference mStorageRef;
    private StorageReference riversRef;
    SearchAdapter searchAdapter;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum);
        getSupportActionBar().hide();
        //設定隱藏狀態

        FirebaseStorage storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();

        Gallery gallery = (Gallery) findViewById(R.id.g6_1);
        Integer[] mImageIds = {
                R.drawable.ad1,
//                R.drawable.ad2,
//                R.drawable.ad3,
//                R.drawable.ad5,
                R.drawable.ad4,
                R.drawable.ad6,
                R.drawable.ad7,
                R.drawable.ad8
        };
        ImageAdapter imageAdapter =  new ImageAdapter(this,mImageIds,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);       // gallery添加ImageAdapter图片资源
        //設定圖片來源

        //設定圖片的位置
        imageAdapter.setmImageIds(mImageIds);
        //圖片高度
        imageAdapter.setHeight(800);
        //圖片寬度
        imageAdapter.setWidth(1000);
        gallery.setAdapter(imageAdapter);
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                if(id==0)
                    Toast.makeText(Forum.this, "請多多指教拉！", Toast.LENGTH_LONG).show();
//                if(id==1)
//                    Toast.makeText(Forum.this, "快來抽機票！耶～", Toast.LENGTH_LONG).show();
//                if(id==2)
//                    Toast.makeText(Forum.this, "佛心賣家在這裡！", Toast.LENGTH_LONG).show();
//                if(id==3)
//                    Toast.makeText(Forum.this, "優惠券開搶！", Toast.LENGTH_LONG).show();
                if(id==4)
                    Toast.makeText(Forum.this, "快來寫下您的感想吧！", Toast.LENGTH_LONG).show();
                if(id==5)
                    Toast.makeText(Forum.this, "遇到問題別慌張~\n其他問題還可以參考個人設定中的「幫助中心」喔！\n或是可以填寫問券反應！", Toast.LENGTH_LONG).show();
                if(id==6)
                    Toast.makeText(Forum.this, "買家一定要知道！", Toast.LENGTH_LONG).show();
                if(id==7)
                    Toast.makeText(Forum.this, "成為優秀的賣家的訣竅在這~", Toast.LENGTH_LONG).show();
            }
        });

        displayChatMessages();
//        btn62 = (Button) findViewById(R.id.btn6_2);
        btn63 = (Button) findViewById(R.id.btn6_3);
        btn64 = (Button) findViewById(R.id.btn6_4);
        btn65 = (Button) findViewById(R.id.btn6_5);
        btn66 = (Button) findViewById(R.id.btn6_6);
        btn67 = (Button) findViewById(R.id.btn6_7);
        btn68 = (Button) findViewById(R.id.btn6_8);



        ib68=(ImageView)findViewById(R.id.ib6_8);
        ib69=(ImageView)findViewById(R.id.ib6_9);


        iv61=(ImageView)findViewById(R.id.iv6_1);
        iv62=(ImageView)findViewById(R.id.iv6_2);
        iv63=(ImageView)findViewById(R.id.iv6_3);

        View.OnClickListener OCL = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (v instanceof Button) {
                    int id = ((Button) v).getId();
                    switch (id) {

                        case R.id.btn6_3:
                            String msg3 = "往接單頁";
                            intent = new Intent();
                            intent.setClass(Forum.this, Seller.class);
                            Forum.this.finish();
                            startActivity(intent);
                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            break;
                        case R.id.btn6_4:
                            String msg4 = "往地圖頁";
                            intent = new Intent();
                            intent.setClass(Forum.this, Maps.class);

                            Forum.this.finish();
                            startActivity(intent);
                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            break;
                        case R.id.btn6_5:
                            String msg5 = "往通知頁";
                            intent = new Intent();
                            intent.setClass(Forum.this, Notify.class);
                            Forum.this.finish();
                            startActivity(intent);
                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            break;
                        case R.id.btn6_6:
                            String msg6 = "往個人頁";
                            intent = new Intent();
                            intent.setClass(Forum.this, Personal.class);
                            Forum.this.finish();
                            startActivity(intent);
                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            break;
                        case R.id.btn6_7:
                            String msg7 = "新增請求";
                            Intent intent7 = new Intent();
                            intent7.setClass(Forum.this, NewBuyerOrder_1.class);
                            Bundle bundle7 =new Bundle();
                            bundle7.putString("mypic","null");
                            bundle7.putString("from","Forum");
                            intent7.putExtras(bundle7);
                            startActivity(intent7);

                            Forum.this.finish();
                            break;
                        case R.id.btn6_8:

                            DatabaseReference[] reference_contacts2 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
                            reference_contacts2[0].addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

//                    Log.d("TAG", "ds.child(mail)1: "+ds.child("mail").getValue());
                                        if(ds.child("mail").getValue().equals(FirebaseAuth.getInstance()
                                                .getCurrentUser()
                                                .getEmail())){
                                            if(ds.child("isSeller").getValue().toString().equals("0")||ds.child("isSeller").getValue().toString().equals("2")){
                                                Toast.makeText(Forum.this, "您還不是賣家喔！", Toast.LENGTH_SHORT).show();
                                            }else{

                                                String msg8 = "新增商品";
                                                Intent intent8 = new Intent();
                                                intent8.setClass(Forum.this, NewSellerOrder_1.class);
                                                Bundle bundle8 =new Bundle();
                                                bundle8.putString("mypic","null");
                                                bundle8.putString("from","Forum");
                                                intent8.putExtras(bundle8);
                                                startActivity(intent8);
                                                Forum.this.finish();

                                            }

                                        }



                                    }


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }


                            });


                            break;
                    }
                }
                if (v instanceof ImageView) {
                    int id = ((ImageView) v).getId();
                    switch (id) {
                        case R.id.ib6_8:
//                            String msg7 = "測試商品頁";
                            Intent intent8 = new Intent();
                            intent8.setClass(Forum.this, Forum_buyerforum.class);
                            startActivity(intent8);
//                        Toast.makeText(Forum.this, msg6, Toast.LENGTH_SHORT).show();
                            Forum.this.finish();
                            break;
                        case R.id.ib6_9:
//                            String msg7 = "測試商品頁";
                            Intent intent9 = new Intent();
                            intent9.setClass(Forum.this, Forum_sellerforum.class);
                            startActivity(intent9);
//                        Toast.makeText(Forum.this, msg6, Toast.LENGTH_SHORT).show();
                            Forum.this.finish();
                            break;
                        case R.id.iv6_1:
//                            String msg7 = "測試商品頁";
                            Intent intent10 = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putString("單詞", "推薦小物");//建新的class裝搜尋文章
                            intent10.setClass(Forum.this, HotArticle1.class);
                            intent10.putExtras(bundle);
                            startActivity(intent10);

//                        Toast.makeText(Forum.this, msg6, Toast.LENGTH_SHORT).show();
                            Forum.this.finish();
                            break;
                        case R.id.iv6_2:
//                            String msg7 = "測試商品頁";
                            Intent intent11 = new Intent();
                            Bundle bundle2 = new Bundle();
                            bundle2.putString("單詞", "萌新專區");//建新的class裝搜尋文章
                            intent11.setClass(Forum.this, HotArticle2.class);
                            intent11.putExtras(bundle2);
                            startActivity(intent11);

//                        Toast.makeText(Forum.this, msg6, Toast.LENGTH_SHORT).show();
                            Forum.this.finish();
                            break;
                        case R.id.iv6_3:
//                            String msg7 = "測試商品頁";
                            Intent intent12 = new Intent();
                            Bundle bundle3 = new Bundle();
                            bundle3.putString("單詞", "資訊快報");//建新的class裝搜尋文章
                            intent12.setClass(Forum.this, HotArticle3.class);
                            intent12.putExtras(bundle3);
                            startActivity(intent12);
//                        Toast.makeText(Forum.this, msg6, Toast.LENGTH_SHORT).show();
                            Forum.this.finish();
                            break;

                    }
                }
            }
        };

        btn63.setOnClickListener(OCL);
        btn64.setOnClickListener(OCL);
        btn65.setOnClickListener(OCL);
        btn66.setOnClickListener(OCL);
        btn67.setOnClickListener(OCL);
        btn68.setOnClickListener(OCL);

        ib68.setOnClickListener(OCL);
        ib69.setOnClickListener(OCL);
        iv61.setOnClickListener(OCL);
        iv62.setOnClickListener(OCL);
        iv63.setOnClickListener(OCL);

    }

    private FirebaseListAdapter<BuyerOrder_Container> adapter;
    private FirebaseListAdapter<SellerOrder_Container> adapter2;
    private void displayChatMessages() {
//        listOfMessages = (HorizontalListView) findViewById(R.id.lv8_1);
        buyerorder= (Gallery) findViewById(R.id.g6_2);
        sellerorder= (Gallery) findViewById(R.id.g6_3);
        buyerorder.setScrollbarFadingEnabled(false);
        sellerorder.setScrollbarFadingEnabled(false);

        DatabaseReference[] reference_contacts11 = {FirebaseDatabase.getInstance().getReference("buyer_order")};//第一頁第1行
        reference_contacts11[0].addValueEventListener(new ValueEventListener() {
            boolean has=false;
            boolean have2=false;
            int j;
            ArrayList<BuyerOrder_Container>  list1 = new ArrayList<BuyerOrder_Container>();
            ArrayList<BuyerOrder_Container>  list2 = new ArrayList<BuyerOrder_Container>();
            List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    BuyerOrder_Container article = ds.getValue(BuyerOrder_Container.class);
                    list1.add(article);
                    has=true;
                }
                if(has==true){

                    for(int i=list1.size()-1;i>=0;i--){
                        list2.add(list1.get(i));
                        have2=true;
                        Map<String, Object> item = new HashMap<String, Object>();
                        item.put("from","Seller");
                        item.put("image",list1.get(i).getPicture().toString() );
                        item.put("text", list1.get(i).getName().toString());
                        items.add(item);
                    }

                    if(have2==true) {

                        if(items.size()!=0){
                            searchAdapter=new SearchAdapter(Forum.this,items,"buyer_order");
                            buyerorder.setAdapter(searchAdapter);

                            buyerorder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                                    Intent intent7 = new Intent();
                                    intent7.setClass(Forum.this, BuyerOrder.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("classify", list2.get(position).getClassify());
                                    bundle.putString("brand", list2.get(position).getBrand());
                                    bundle.putString("name", list2.get(position).getName());
                                    bundle.putString("standard", list2.get(position).getStandard());
                                    bundle.putString("model", list2.get(position).getModel());
                                    bundle.putString("url", list2.get(position).getUrl());
                                    bundle.putString("country", list2.get(position).getCountry());
                                    bundle.putString("place", list2.get(position).getPlace());
                                    bundle.putString("other", list2.get(position).getOther());
                                    bundle.putString("price", list2.get(position).getPrice());
                                    bundle.putString("num", list2.get(position).getNum());
                                    bundle.putString("fee", list2.get(position).getFee());
                                    bundle.putString("delivery", list2.get(position).getDelivery());
                                    bundle.putLong("time", list2.get(position).getMessageTime());
                                    bundle.putString("buyer", list2.get(position).getUser());
                                    bundle.putString("buyernick", list2.get(position).getUsernick());
                                    bundle.putString("receipt", list2.get(position).getReceipt());

                                    bundle.putString("from", "Forun");
                                    intent7.putExtras(bundle);
                                    startActivity(intent7);

                                }
                            });


                        }

                    }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        DatabaseReference[] reference_contacts12 = {FirebaseDatabase.getInstance().getReference("seller_order")};//第一頁第2行
        reference_contacts12[0].addValueEventListener(new ValueEventListener() {
            boolean has=false;
            boolean have2=false;
            int j;
            ArrayList<SellerOrder_Container>  list1 = new ArrayList<SellerOrder_Container>();
            ArrayList<SellerOrder_Container>  list2 = new ArrayList<SellerOrder_Container>();
            List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    SellerOrder_Container article = ds.getValue(SellerOrder_Container.class);
                    list1.add(article);
                    has=true;
                }
                if(has==true){

                    for(int i=list1.size()-1;i>=0;i--){
                        list2.add(list1.get(i));
                        have2=true;
                        Map<String, Object> item = new HashMap<String, Object>();
                        item.put("from","Seller");
                        item.put("image",list1.get(i).getPicture().toString() );
                        item.put("text", list1.get(i).getName().toString());
                        items.add(item);
                    }

                    if(have2==true) {

                        if(items.size()!=0){
                            searchAdapter=new SearchAdapter(Forum.this,items,"seller_order");
                            sellerorder.setAdapter(searchAdapter);

                            sellerorder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                                    Intent intent7 = new Intent();
                                    intent7.setClass(Forum.this, SellerOrder.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("classify", list2.get(position).getClassify());
                                    bundle.putString("brand", list2.get(position).getBrand());
                                    bundle.putString("name", list2.get(position).getName());
                                    bundle.putString("standard", list2.get(position).getStandard());
                                    bundle.putString("model", list2.get(position).getModel());
                                    bundle.putString("url", list2.get(position).getUrl());
                                    bundle.putString("country", list2.get(position).getCountry());
                                    bundle.putString("place", list2.get(position).getPlace());
                                    bundle.putString("other", list2.get(position).getOther());
                                    bundle.putString("price", list2.get(position).getPrice());
                                    bundle.putString("num", list2.get(position).getNum());
                                    bundle.putString("fee", list2.get(position).getFee());
                                    bundle.putString("delivery", list2.get(position).getDelivery());
                                    bundle.putLong("time", list2.get(position).getMessageTime());
                                    bundle.putString("seller", list2.get(position).getUser());
                                    bundle.putString("sellernick", list2.get(position).getUsernick());
                                    bundle.putString("receipt", list2.get(position).getReceipt());

                                    bundle.putString("from", "Forun");
                                    intent7.putExtras(bundle);
                                    startActivity(intent7);

                                }
                            });


                        }

                    }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams)buyerorder.getLayoutParams();
        ViewGroup.MarginLayoutParams layoutParams2 = (ViewGroup.MarginLayoutParams)sellerorder.getLayoutParams();
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        layoutParams.setMargins(-width*2/3, 0, 0, 0);
        layoutParams2.setMargins(-width*2/3, 0, 0, 0);



    }


    private void downloadImg(final StorageReference ref, final ImageView img){
        if(ref == null){
            Toast.makeText(Forum.this, "請先上傳照片", Toast.LENGTH_SHORT).show();
            Log.d("down", "onSuccess: ");
            return;
        }



        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(!isDestroy(Forum.this)){
                    Glide.with(Forum.this)

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
            moveTaskToBack(true);
        }
        return false;
    }




}