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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
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

public class Member_page extends AppCompatActivity {
    ImageButton ib421;
    Button btn427, btn420,btn428;
    Bundle bundle;
    String iam= FirebaseAuth.getInstance()
            .getCurrentUser()
            .getEmail();

    TextView tv421;
    ImageView iv421;
    String[] mypic = new String[1];
    private StorageReference mStorageRef;
    private StorageReference riversRef;
    RecyclerView rv1;
    RecyclerView rv2;
    RecyclerView rv3;
    RecyclerView rv4;
    TextView tv422,tv423,tv424,tv425;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_page);
//        getSupportActionBar().hide();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        bundle= getIntent().getExtras();
        final String user= bundle.getString("useracc");
        final String usernic= bundle.getString("user");

        rv1 = (RecyclerView) findViewById(R.id.recyclerView42_1);
        rv2 = (RecyclerView) findViewById(R.id.recyclerView42_2);
        rv3= (RecyclerView) findViewById(R.id.recyclerView42_3);
        rv4 = (RecyclerView) findViewById(R.id.recyclerView42_4);


        rv1.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rv2.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rv3.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rv4.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        final ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.vs42_1);

        Animation slide_in_left = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation slide_out_right = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        viewFlipper.setInAnimation(slide_in_left);
        viewFlipper.setOutAnimation(slide_out_right);

        tv422=(TextView)findViewById(R.id.tv42_2);
        tv423=(TextView)findViewById(R.id.tv42_3);
        tv424=(TextView)findViewById(R.id.tv42_4);
        tv425=(TextView)findViewById(R.id.tv42_5);

        iv421= (ImageView)findViewById(R.id.iv42_1);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();

        final ScrollView sv42 = (ScrollView) findViewById(R.id.sv42);
        sv42.post(new Runnable() {
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.
//                    mRootScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                int[] location = new int[2];
                int offset = location[1] - sv42.getMeasuredHeight();
                if (offset < 0) {
                    offset = 0;
                }
                sv42.scrollTo(0, offset);
            }
        });

        final boolean[] m = {false};
        DatabaseReference[] reference_contacts5 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
        reference_contacts5[0].addValueEventListener(new ValueEventListener() {
            List<CardViewMember>  memberList2 = new ArrayList<>();
            List<CardViewMember>  memberList3 = new ArrayList<>();
            boolean have=false;
            boolean have2=false;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    if(ds.child("mail").getValue().equals(user)){
                        mypic[0] = ds.child("picture").getValue().toString();
                        m[0] =true;

                        if(ds.child("fansNum").exists()){
                            have=true;
                            for (DataSnapshot dds : ds.child("fansNum").getChildren()){
                                memberList2.add(new CardViewMember("seller_order", "",dds.child("picture").getValue().toString(),dds.child("ordername").getValue().toString()));
                            }
                        }
                        if(ds.child("focusNum").exists()){
                            have2=true;
                            for (DataSnapshot dds : ds.child("focusNum").getChildren()){
                                memberList3.add(new CardViewMember(dds.child("db").getValue().toString(),dds.child("db2").getValue().toString(),dds.child("picture").getValue().toString(),dds.child("ordername").getValue().toString()));
                            }
                        }
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
                            CardViewMemberAdapter c2=new CardViewMemberAdapter(Member_page.this, memberList2);
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
                                            intent7.setClass(Member_page.this, SellerOrder.class);
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


                            CardViewMemberAdapter c3=new CardViewMemberAdapter(Member_page.this, memberList3);
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
                                            intent7.setClass(Member_page.this, ArticlePage.class);
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

//        final ArrayList<BuyerOrder_Container> list = new ArrayList<BuyerOrder_Container>();
        DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_order")};
        reference_contacts[0].addValueEventListener(new ValueEventListener() {
            boolean have=false;
            List<CardViewMember> memberList1 = new ArrayList<>();
            ArrayList<BuyerOrder_Container> list1 = new ArrayList<BuyerOrder_Container>();
            int num=1;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren() ) {
                    if(ds.child("user").getValue().toString().equals(user)){
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
                    CardViewMemberAdapter c1=new CardViewMemberAdapter(Member_page.this, memberList1);
                    rv1.setAdapter(c1);


                    c1.setOnItemClickListener(new CardViewMemberAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                            Intent intent7 = new Intent();
                            intent7.setClass(Member_page.this, BuyerOrder.class);
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


        DatabaseReference[] reference_contacts4 = {FirebaseDatabase.getInstance().getReference("seller_order")};
        reference_contacts4[0].addValueEventListener(new ValueEventListener() {
            boolean have=false;
            List<CardViewMember>  memberList4 = new ArrayList<>();
            final ArrayList<SellerOrder_Container> list4 = new ArrayList<SellerOrder_Container>();
            int num=1;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren() ) {
                    if(ds.child("user").getValue().toString().equals(user)){
                        SellerOrder_Container article4 = ds.getValue(SellerOrder_Container.class);
                        list4.add(article4);
                        memberList4.add(new CardViewMember("seller_order", "",ds.child("picture").getValue().toString(),ds.child("name").getValue().toString()));
                        have=true;
                    }



                }
                if(have==true){
                    CardViewMemberAdapter c4=new CardViewMemberAdapter(Member_page.this, memberList4);
                    rv4.setAdapter(c4);
                    c4.setOnItemClickListener(new CardViewMemberAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                            Intent intent7 = new Intent();
                            intent7.setClass(Member_page.this, SellerOrder.class);
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


        tv421=(TextView)findViewById(R.id.tv42_1);
        tv421.setText(usernic);

        btn427=(Button)findViewById(R.id.btn42_7);

        btn427.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int[] count = {0};
                DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_file")};
                reference_contacts[0].addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            count[0] = count[0] +1;
                        }

                        final String user2=user;

                        final String[] mynum = new String[1];
                        final boolean[] m = {false};
                        final String[] user2num = new String[1];
                        final boolean[] u = {false};

                            DatabaseReference[] reference_contacts2 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
                            reference_contacts2[0].addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                                        Log.d("TAG", "ds.child(mail)1: "+ds.child("mail").getValue());
                                        if(ds.child("mail").getValue().equals(iam)){
                                            mynum[0] = ds.child("mail").getValue().toString();
//                                            Log.d("TAG", "onDataChange1: "+mynum[0]);
                                            m[0] =true;
                                        }
                                        Log.d("TAG", "ds.child(mail)2: "+ds.child("mail").getValue());
                                        if(ds.child("mail").getValue().equals(user2)){
                                            user2num[0] = ds.child("mail").getValue().toString();
//                                            Log.d("TAG", "onDataChange2: "+user2num[0]);
                                            u[0] =true;
                                        }


                                    }
                                    if(m[0]==true&&u[0]==true){
//                                        Log.d("TAG", "mynum: "+mynum[0]+"user2: "+user2num[0]);
                                        Bundle bundle2=new Bundle();
//um[0]));
//                                        Log.d("TAG", "max "+max+"min "+min);
                                        bundle2.putString("user1",mynum[0]);
                                        bundle2.putString("user2",user2num[0]);
                                        bundle2.putString("usernic",usernic);
                                        bundle2.putString("from",bundle.getString("from"));
                                        bundle2.putString("from2","Member_page");
                                        Intent intent = new Intent();
                                        intent.setClass(Member_page.this, ChatRoom2.class);
                                        intent.putExtras(bundle2);
                                        startActivity(intent);

                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }


                            });
//                        if(count==0){
//                            FirebaseDatabase.getInstance()
//                                    .getReference("chatroom")
//                                    .child(recognize)
//                                    .push()
//                                    .setValue(new ChatMessage(
//                                                    "weclome!",
//                                                    "system"
//                                            )
//                                    );
//                            }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btn428=(Button)findViewById(R.id.btn42_8);
        btn428.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg8 = "評價";
                Intent intent8 = new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("from","Member_page");
                bundle.putString("acc",user);
                intent8.setClass(Member_page.this, Personal_rating.class);
                intent8.putExtras(bundle);
                Member_page.this.finish();
                startActivity(intent8);

            }
        });


        btn420=(Button)findViewById(R.id.btn42_0);
        btn420.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String from=bundle.getString("from");

                Intent intent = new Intent();
//                if(from.equals("ChatRoom2"))
//                    intent.setClass(Member_page.this,ChatRoom2.class);
//                else
                    if(from.equals("Forum_buyerforum")){
                    intent.setClass(Member_page.this,Forum_buyerforum.class);
                    startActivity(intent);
                }

                else if(from.equals("Forum_sellerforum")){
                    intent.setClass(Member_page.this,Forum_sellerforum.class);
                    startActivity(intent);
                }
                    else if(from.equals("HotArticle1")){
                        intent.setClass(Member_page.this,HotArticle1.class);
                        startActivity(intent);
                    }
                    else if(from.equals("HotArticle2")){
                        intent.setClass(Member_page.this,HotArticle2.class);
                        startActivity(intent);
                    }
                    else if(from.equals("HotArticle3")){
                        intent.setClass(Member_page.this,HotArticle3.class);
                        startActivity(intent);
                    }

                else if(from.equals("ArticlePage")){
//                        intent.setClass(Member_page.this,ArticlePage.class);
//                        startActivity(intent);

                }
//                    intent.setClass(Member_page.this,ArticlePage.class);
                else if(from.equals("BuyerOrder")){

//                        intent.setClass(Member_page.this,BuyerOrder.class);
//                        startActivity(intent);

                }
                else if(from.equals("SellerOrder")){
//                        intent.setClass(Member_page.this,SellerOrder.class);
//                        startActivity(intent);

                }
                Member_page.this.finish();
            }

        });
        View.OnClickListener OCL = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
          if (v instanceof TextView) {
                    int id2 = ((TextView) v).getId();
                    switch (id2) {
                        case R.id.tv42_2:
                            viewFlipper.setDisplayedChild(0);
                            break;
                        case R.id.tv42_3:
                            viewFlipper.setDisplayedChild(1);
                            break;
                        case R.id.tv42_4:
                            viewFlipper.setDisplayedChild(2);
                            break;
                        case R.id.tv42_5:
                            viewFlipper.setDisplayedChild(3);
                            break;
                    }
                }
            }
        };
        tv422.setOnClickListener(OCL);
        tv423.setOnClickListener(OCL);
        tv424.setOnClickListener(OCL);
        tv425.setOnClickListener(OCL);

//        ib421.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String from=bundle.getString("from");
//
//                Intent intent = new Intent();
//                if(from.equals("ChatRoom2"))
//                intent.setClass(Member_page.this,ChatRoom2.class);
//                else if(from.equals("Forum_buyerforum")){
//                    intent.setClass(Member_page.this,Forum_buyerforum.class);
//                    startActivity(intent);
//                }
//
//                else if(from.equals("Forum_sellerforum")){
//                    intent.setClass(Member_page.this,Forum_sellerforum.class);
//                    startActivity(intent);
//                }
//
//                else if(from.equals("ArticlePage")){
//
//                }
////                    intent.setClass(Member_page.this,ArticlePage.class);
//                else if(from.equals("BuyerOrder")){
//
//                }
//                else if(from.equals("SellerOrder")){
//
//                }
//
//
//                Member_page.this.finish();
//
//            }
//        });

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            String from=bundle.getString("from");

            Intent intent = new Intent();
//            if(from.equals("ChatRoom2"))
//                intent.setClass(Member_page.this,ChatRoom2.class);
//            else
                if(from.equals("Forum_buyerforum")){
                intent.setClass(Member_page.this,Forum_buyerforum.class);
                startActivity(intent);
            }

            else if(from.equals("Forum_sellerforum")){
                intent.setClass(Member_page.this,Forum_sellerforum.class);
                startActivity(intent);
            }
                else if(from.equals("HotArticle1")){
                    intent.setClass(Member_page.this,HotArticle1.class);
                    startActivity(intent);
                }
                else if(from.equals("HotArticle2")){
                    intent.setClass(Member_page.this,HotArticle2.class);
                    startActivity(intent);
                }
                else if(from.equals("HotArticle3")){
                    intent.setClass(Member_page.this,HotArticle3.class);
                    startActivity(intent);
                }

            else if(from.equals("ArticlePage")){

            }
//                    intent.setClass(Member_page.this,ArticlePage.class);
            else if(from.equals("BuyerOrder")){

            }
            else if(from.equals("SellerOrder")){

            }
            Member_page.this.finish();
        }
        return false;
    }

    private void downloadImg(final StorageReference ref){
        if(ref == null){
            Toast.makeText(Member_page.this, "請先上傳照片", Toast.LENGTH_SHORT).show();
            Log.d("down", "onSuccess: ");
            return;
        }



        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(!isDestroy(Member_page.this)){
                    Glide.with(Member_page.this)

                            .load(ref)
                            .into(iv421);

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

}