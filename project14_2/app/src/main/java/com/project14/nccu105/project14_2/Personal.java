package com.project14.nccu105.project14_2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.transition.Slide;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

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
import java.util.List;

//4
public class Personal extends AppCompatActivity {
    Button btn41,btn42,btn43,btn44,btn45,btn47,btn48;
    TextView tv41,tv42,tv43,tv44,tv45;
    ImageView iv41;
    private StorageReference mStorageRef;
    private StorageReference riversRef;
    String[] mynum = new String[1];
    String[] mypic = new String[1];
    String mytalk="";

    String nickname="";
    RecyclerView rv1;
    RecyclerView rv2;
    RecyclerView rv3;
    RecyclerView rv4;





    int dbnum=1;
    int i;
    String db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal);
        getSupportActionBar().hide();
        //設定隱藏狀態

        FirebaseStorage storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();
        final TextView tv46=(TextView)findViewById(R.id.tv4_6);
        btn41 = (Button) findViewById(R.id.btn4_1);
//        btn42 = (Button) findViewById(R.id.btn4_2);
        btn43 = (Button) findViewById(R.id.btn4_3);
        btn44 = (Button) findViewById(R.id.btn4_4);
        btn45 = (Button) findViewById(R.id.btn4_5);
        btn47 = (Button) findViewById(R.id.btn4_7);
        btn48 = (Button) findViewById(R.id.btn4_8);

        iv41= (ImageView)findViewById(R.id.iv4_1);

        tv41=(TextView)findViewById(R.id.tv4_1);
        tv42=(TextView)findViewById(R.id.tv4_2);
        tv43=(TextView)findViewById(R.id.tv4_3);
        tv44=(TextView)findViewById(R.id.tv4_4);
        tv45=(TextView)findViewById(R.id.tv4_5);

        rv1 = (RecyclerView) findViewById(R.id.recyclerView4_1);
        rv2 = (RecyclerView) findViewById(R.id.recyclerView4_2);
        rv3= (RecyclerView) findViewById(R.id.recyclerView4_3);
        rv4 = (RecyclerView) findViewById(R.id.recyclerView4_4);

//        RecyclerView rv1 = (RecyclerView) findViewById(R.id.recyclerView4_1);
//        RecyclerView rv1 = (RecyclerView) findViewById(R.id.recyclerView4_1);
        rv1.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rv2.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rv3.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rv4.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));




        final boolean[] m = {false};
        final String iam= FirebaseAuth.getInstance()
                .getCurrentUser()
                .getEmail();




        DatabaseReference[] reference_contacts2 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
        reference_contacts2[0].addValueEventListener(new ValueEventListener() {


            List<CardViewMember>  memberList2 = new ArrayList<>();
            List<CardViewMember>  memberList3 = new ArrayList<>();
            boolean have=false;
            boolean have2=false;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

//                    Log.d("TAG", "ds.child(mail)1: "+ds.child("mail").getValue());
                    if(ds.child("mail").getValue().equals(FirebaseAuth.getInstance()
                            .getCurrentUser()
                            .getEmail())){
                        mynum[0] = ds.child("memberNum").getValue().toString();
                        mypic[0] = ds.child("picture").getValue().toString();
                        mytalk=ds.child("personalArticle").getValue().toString();
                        nickname=ds.child("nickname").getValue().toString();
                        tv46.setText(mytalk);
                        tv41.setText(nickname);
                        if(ds.child("fansNum").exists()&&ds.child("fansNum").hasChildren()){//想要商品
                            have=true;
                            for (DataSnapshot dds : ds.child("fansNum").getChildren()){
//                                SellerOrder_Container article2 = dds.getValue(SellerOrder_Container.class);
//                                list2.add(article2);

                                memberList2.add(new CardViewMember("seller_order","", dds.child("picture").getValue().toString(),dds.child("ordername").getValue().toString()));
                            }
                        }
                        if(ds.child("focusNum").exists()){//文章
                            have2=true;
                            for (DataSnapshot dds : ds.child("focusNum").getChildren()){
//                               Article article3 = dds.getValue(Article.class);
//                                list3.add(article3);
                                memberList3.add(new CardViewMember(dds.child("db").getValue().toString(),dds.child("db2").getValue().toString(),dds.child("picture").getValue().toString(),dds.child("ordername").getValue().toString()));
                            }
                        }

                        Log.d("TAG", "onDataChange1: "+mypic[0]);
                        m[0] =true;

                    }

                    if(m[0]==true){
                        String test=mypic[0];
                        if(test.contains("null")){}
                        else{

                            riversRef = mStorageRef.child("member_picture").child(mypic[0]);
                            Log.d("down", "onSuccess: ");
//
                            downloadImg(riversRef);
                        }
                        if(have==true){
                            CardViewMemberAdapter c2=new CardViewMemberAdapter(Personal.this, memberList2);
                            rv2.setAdapter(c2);
                            c2.setOnItemClickListener(new CardViewMemberAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, final int position) {

                                    DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("seller_order")};
                                    reference_contacts[0].addValueEventListener(new ValueEventListener() {
                                        ArrayList<SellerOrder_Container> list2 = new ArrayList<SellerOrder_Container>();
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            int count=0;
                                            for (DataSnapshot ds : dataSnapshot.getChildren() ) {
                                                if(ds.child("name").getValue().toString().equals(memberList2.get(position).getName())){
                                                    SellerOrder_Container article2 = ds.getValue(SellerOrder_Container.class);
                                                    list2.add(article2);
                                                }


                                            }
//
                                            Intent intent7 = new Intent();
                                            intent7.setClass(Personal.this, SellerOrder.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putString("classify", list2.get(0).getClassify());
                                            bundle.putString("brand", list2.get(0).getBrand());
                                            bundle.putString("name", list2.get(0).getName());
                                            bundle.putString("standard", list2.get(0).getStandard());
                                            bundle.putString("model", list2.get(0).getModel());
                                            bundle.putString("url", list2.get(0).getUrl());
                                            bundle.putString("country", list2.get(0).getCountry());
                                            bundle.putString("place", list2.get(0).getPlace());
                                            bundle.putString("other", list2.get(0).getOther());
                                            bundle.putString("price", list2.get(0).getPrice());
                                            bundle.putString("num", list2.get(0).getNum());
                                            bundle.putString("fee", list2.get(0).getFee());
                                            bundle.putString("delivery", list2.get(0).getDelivery());
                                            bundle.putLong("time", list2.get(0).getMessageTime());
                                            bundle.putString("seller", list2.get(0).getUser());
                                            bundle.putString("sellernick", list2.get(0).getUsernick());
                                            bundle.putString("receipt", list2.get(0).getReceipt());

                                            bundle.putString("from", "Result");
                                            intent7.putExtras(bundle);
                                            startActivity(intent7);
                                        }





                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });




                                }


                            });



                        }
                        if(have2==true){
                            CardViewMemberAdapter c3=new CardViewMemberAdapter(Personal.this, memberList3);
                            rv3.setAdapter(c3);

                            c3.setOnItemClickListener(new CardViewMemberAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, final int position) {

                                    DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference(memberList3.get(position).getDb2())};
                                    reference_contacts[0].addValueEventListener(new ValueEventListener() {
                                        ArrayList<Article> list3 = new ArrayList<Article>();
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            int count=0;
                                            for (DataSnapshot ds : dataSnapshot.getChildren() ) {
                                                if(ds.child("article").getValue().toString().equals(memberList3.get(position).getName())){
                                                    Article article3 = ds.getValue(Article.class);
                                                    list3.add(article3);
                                                }


                                            }
//
                                            Intent intent7 = new Intent();
                                            intent7.setClass(Personal.this, ArticlePage.class);
                                            Bundle bundle = new Bundle();
                                            Log.d("size", "onDataChange: "+memberList3.size());

                                            if(memberList3.get(position).getDb2().contains("title"))
                                                bundle.putInt("who",0);
                                            else
                                                bundle.putInt("who",1);


                                            if(memberList3.get(position).getDb2().contains("2"))
                                                bundle.putInt("num",2);
                                            else if(memberList3.get(position).getDb2().contains("3"))
                                                bundle.putInt("num",3);
                                            else if(memberList3.get(position).getDb2().contains("4"))
                                                bundle.putInt("num",4);
                                            else if(memberList3.get(position).getDb2().contains("5"))
                                                bundle.putInt("num",5);
                                            else
                                                bundle.putInt("num",1);


                                            bundle.putString("title", list3.get(0).getArticle());
                                            bundle.putString("classify",list3.get(0).getClassify());

                                            bundle.putLong("time",list3.get(0).getMessageTime());
                                            bundle.putString("name",list3.get(0).getMessageUser());
                                            bundle.putString("usernick",list3.get(0).getUsernick());

                                            bundle.putString("content",list3.get(0).getMessageText());
                                            intent7.putExtras(bundle);
                                            startActivity(intent7);}





                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });




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






//        seller1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//                final ArrayList<BuyerOrder_Container> list = new ArrayList<BuyerOrder_Container>();
//                //new一個Bundle物件，並將要傳遞的資料傳入
//
//                Seller.this.finish();
//
//
//
//            }
//        });

//1

        DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_order")};
        reference_contacts[0].addValueEventListener(new ValueEventListener() {
            boolean have=false;
            List<CardViewMember>  memberList1 = new ArrayList<>();
            ArrayList<BuyerOrder_Container> list1 = new ArrayList<BuyerOrder_Container>();
            int num=1;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren() ) {
                    if(ds.child("user").getValue().toString().equals(iam)){
                        BuyerOrder_Container article = ds.getValue(BuyerOrder_Container.class);
                        list1.add(article);

                        memberList1.add(new CardViewMember("buyer_order","",ds.child("picture").getValue().toString(),ds.child("name").getValue().toString()));
                        num++;
//                        riversRef = mStorageRef.child("buyer_order").child(ds.);
//                downloadImg(riversRef,img);
                        have=true;
                    }



                }
                if(have==true){
                    CardViewMemberAdapter c1=new CardViewMemberAdapter(Personal.this, memberList1);
                    rv1.setAdapter(c1);


                    c1.setOnItemClickListener(new CardViewMemberAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                            Intent intent7 = new Intent();
                            intent7.setClass(Personal.this, BuyerOrder.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("classify", list1.get(position).getClassify());
                            bundle.putString("brand", list1.get(position).getBrand());
                            bundle.putString("name", list1.get(position).getName());
                            bundle.putString("standard", list1.get(position).getStandard());
                            bundle.putString("model", list1.get(position).getModel());
                            bundle.putString("url", list1.get(position).getUrl());
                            bundle.putString("country", list1.get(position).getCountry());
                            bundle.putString("place", list1.get(position).getPlace());
                            bundle.putString("other", list1.get(position).getOther());
                            bundle.putString("price", list1.get(position).getPrice());
                            bundle.putString("num", list1.get(position).getNum());
                            bundle.putString("fee", list1.get(position).getFee());
                            bundle.putString("delivery", list1.get(position).getDelivery());
                            bundle.putLong("time", list1.get(position).getMessageTime());
                            bundle.putString("buyer", list1.get(position).getUser());
                            bundle.putString("buyernick", list1.get(position).getUsernick());
                            bundle.putString("receipt", list1.get(position).getReceipt());

                            bundle.putString("from", "Result");
                            intent7.putExtras(bundle);
                            startActivity(intent7);

                        }


                    });


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        final ScrollView sv4 = (ScrollView) findViewById(R.id.sv4);
        sv4.post(new Runnable() {
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.
//                    mRootScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                int[] location = new int[2];
                int offset = location[1] - sv4.getMeasuredHeight();
                if (offset < 0) {
                    offset = 0;
                }
                sv4.scrollTo(0, offset);
            }
        });

//        db="articletitle";

//        for(i=1;i<=2;i++){


//            DatabaseReference[] reference_contacts3 = {FirebaseDatabase.getInstance().getReference(db)};
//            reference_contacts3[0].addValueEventListener(new ValueEventListener() {
//                boolean have=false;
//
//
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//
//                    for (DataSnapshot ds : dataSnapshot.getChildren() ) {
//                        Log.d("Personal", "gogo "+db+ds.child("messageUser").getValue().toString());
//                        if(ds.child("messageUser").getValue().toString().equals(iam)){
//
//                            memberList3.add(new CardViewMember(0, R.drawable.bag103,ds.child("article").getValue().toString()));
//                            Log.d("Personal", "onDataChange: "+"num"+"okok");
//        //                        riversRef = mStorageRef.child("buyer_order").child(ds.);
//        //                downloadImg(riversRef,img);
//                            have=true;
//                        }
//
//
//
//                    }
//                    if(dbnum==5){//&&(!memberList3.isEmpty())
//
//                        rv3.setAdapter(new CardViewMemberAdapter(Personal.this, memberList3));
//                        Log.d("Personal", "onDataChange: "+"ok"+memberList3.size());
//
//                    }else{
//                        dbnum++;
//                        db="articletitle"+dbnum;
//                        Log.d("Personal", "onDataChange: "+db);
//
//                    }
//
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//
//            }
//
//
//            );

//        }


//4

        DatabaseReference[] reference_contacts4 = {FirebaseDatabase.getInstance().getReference("seller_order")};
        reference_contacts4[0].addValueEventListener(new ValueEventListener() {
            boolean have=false;
            List<CardViewMember>  memberList4 = new ArrayList<>();
            final ArrayList<SellerOrder_Container> list4 = new ArrayList<SellerOrder_Container>();
            int num=1;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren() ) {
                    if(ds.child("user").getValue().toString().equals(iam)){

                        SellerOrder_Container article4 = ds.getValue(SellerOrder_Container.class);
                        list4.add(article4);
                        memberList4.add(new CardViewMember("seller_order","", ds.child("picture").getValue().toString(),ds.child("name").getValue().toString()));
                        num++;
                        have=true;
                    }



                }
                if(have==true){
                    CardViewMemberAdapter c4=new CardViewMemberAdapter(Personal.this, memberList4);
                    rv4.setAdapter(c4);
                    c4.setOnItemClickListener(new CardViewMemberAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                            Intent intent7 = new Intent();
                            intent7.setClass(Personal.this, SellerOrder.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("classify", list4.get(position).getClassify());
                            bundle.putString("brand", list4.get(position).getBrand());
                            bundle.putString("name", list4.get(position).getName());
                            bundle.putString("standard", list4.get(position).getStandard());
                            bundle.putString("model", list4.get(position).getModel());
                            bundle.putString("url", list4.get(position).getUrl());
                            bundle.putString("country", list4.get(position).getCountry());
                            bundle.putString("place", list4.get(position).getPlace());
                            bundle.putString("other", list4.get(position).getOther());
                            bundle.putString("price", list4.get(position).getPrice());
                            bundle.putString("num", list4.get(position).getNum());
                            bundle.putString("fee", list4.get(position).getFee());
                            bundle.putString("delivery", list4.get(position).getDelivery());
                            bundle.putLong("time", list4.get(position).getMessageTime());
                            bundle.putString("seller", list4.get(position).getUser());
                            bundle.putString("sellernick", list4.get(position).getUsernick());
                            bundle.putString("receipt", list4.get(position).getReceipt());

                            bundle.putString("from", "Result");
                            intent7.putExtras(bundle);
                            startActivity(intent7);

                        }


                    });

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        final ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.vs4_1);

        Animation slide_in_left = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation slide_out_right = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        viewFlipper.setInAnimation(slide_in_left);
        viewFlipper.setOutAnimation(slide_out_right);



        View.OnClickListener OCL = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (v instanceof Button) {
                    int id = ((Button) v).getId();
                    switch (id) {
                        case R.id.btn4_1:
                            String msg1 = "往論壇頁";
                            Intent intent1 = new Intent();
                            intent1.setClass(Personal.this, Forum.class);
                            Personal.this.finish();
                            startActivity(intent1);
                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            break;

                        case R.id.btn4_3:
                            String msg3 = "往接單頁";
                            Intent intent3 = new Intent();
                            intent3.setClass(Personal.this, Seller.class);
                            Personal.this.finish();
                            startActivity(intent3);
                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            break;
                        case R.id.btn4_4:
                            String msg4 = "往地圖頁";
                            Intent intent4 = new Intent();
                            intent4.setClass(Personal.this, Maps.class);
                            Personal.this.finish();
                            startActivity(intent4);
                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            break;
                        case R.id.btn4_5:
                            String msg5 = "往通知頁";
                            Intent intent5 = new Intent();
                            intent5.setClass(Personal.this, Notify.class);
                            Personal.this.finish();
                            startActivity(intent5);
                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            break;
                        case R.id.btn4_7:
                            String msg7 = "設定";
                            Intent intent7 = new Intent();
                            intent7.setClass(Personal.this, Personal_setting.class);
                            Personal.this.finish();
                            startActivity(intent7);

                            break;
                        case R.id.btn4_8:
                            String msg8 = "評價";
                            Intent intent8 = new Intent();
                            Bundle bundle=new Bundle();
                            bundle.putString("from","Personal");
                            intent8.setClass(Personal.this, Personal_rating.class);
                            intent8.putExtras(bundle);
                            Personal.this.finish();
                            startActivity(intent8);

                            break;


                    }
                }
                else if (v instanceof TextView) {
                    int id2 = ((TextView) v).getId();
                    switch (id2) {
                        case R.id.tv4_2:
                            viewFlipper.setDisplayedChild(0);
                            break;
                        case R.id.tv4_3:
                            viewFlipper.setDisplayedChild(1);
                            break;
                        case R.id.tv4_4:
                            viewFlipper.setDisplayedChild(2);
                            break;
                        case R.id.tv4_5:
                            viewFlipper.setDisplayedChild(3);
                            break;
                    }
                }
            }
        };


        btn41.setOnClickListener(OCL);
//        btn42.setOnClickListener(OCL);
        btn43.setOnClickListener(OCL);
        btn44.setOnClickListener(OCL);
        btn45.setOnClickListener(OCL);
        btn47.setOnClickListener(OCL);
        btn48.setOnClickListener(OCL);
        tv42.setOnClickListener(OCL);
        tv43.setOnClickListener(OCL);
        tv44.setOnClickListener(OCL);
        tv45.setOnClickListener(OCL);

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

    private void downloadImg(final StorageReference ref){
        if(ref == null){
            Toast.makeText(Personal.this, "請先上傳照片", Toast.LENGTH_SHORT).show();
            Log.d("down", "onSuccess: ");
            return;
        }



        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(!isDestroy(Personal.this)){
                    Glide.with(Personal.this)

                            .load(ref)
                            .into(iv41);

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

