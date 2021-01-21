package com.project14.nccu105.project14_2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
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
import java.util.Date;

public class BuyerOrder extends AppCompatActivity {
    Button btn271, btn272, btn93, btn94, btn95, btn96, btn97;
    ImageButton ib271,heart;
    private FirebaseListAdapter<ChatMessage> adapter;

    private StorageReference mStorageRef;
    private StorageReference riversRef;
    String[] mypic =new String[1];
    String userpic ="";
    ImageView iv271,iv272;
    String iam= FirebaseAuth.getInstance()
            .getCurrentUser()
            .getEmail();

    String user="";
    String nickname="";
    String from="";
    String isSeller="0";
    boolean ischange=false;
//    String ordername="";
//    ArrayList likelist=new ArrayList();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyerorder);
//        ib271=(ImageButton)findViewById(R.id.ib27_1);
//        getSupportActionBar().hide();
        //設定隱藏狀態



        iv271= (ImageView)findViewById(R.id.iv27_1);
        iv272= (ImageView)findViewById(R.id.iv27_2);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        final ListView listOfMessages = (ListView)findViewById(R.id.buyer_product_messages);

        TextView Topprice = (TextView)findViewById(R.id.tv27_2);
        TextView Topfee = (TextView)findViewById(R.id.tv27_5);
//        final TextView Title = (TextView)findViewById(R.id.tv26_1);
        TextView Brand = (TextView)findViewById(R.id.tv27_6);
        TextView Classify = (TextView)findViewById(R.id.tv27_10);
        TextView Name = (TextView)findViewById(R.id.tv27_7);
        TextView Standard = (TextView)findViewById(R.id.tv27_8);
        TextView Model = (TextView)findViewById(R.id.tv27_24);
        TextView Url = (TextView)findViewById(R.id.tv27_25);
        TextView Country = (TextView)findViewById(R.id.tv27_26);
        TextView Place = (TextView)findViewById(R.id.tv27_27);
        TextView Other = (TextView)findViewById(R.id.tv27_12);
        TextView Price = (TextView)findViewById(R.id.tv27_15);
        TextView Num = (TextView)findViewById(R.id.tv27_17);
        TextView Fee = (TextView)findViewById(R.id.tv27_19);
        TextView Delivery = (TextView)findViewById(R.id.tv27_29);
        TextView Time = (TextView)findViewById(R.id.tv27_3);
        final TextView User = (TextView)findViewById(R.id.tv27_23);

        TextView Receipt = (TextView)findViewById(R.id.tv27_30);

        final ScrollView sv26 = (ScrollView) findViewById(R.id.sv26);
        sv26.post(new Runnable() {
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.
//                    mRootScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                int[] location = new int[2];
//                Title.getLocationOnScreen(location);
                int offset = location[1] - sv26.getMeasuredHeight();
                if (offset < 0) {
                    offset = 0;
                }
                sv26.smoothScrollTo(0, offset);
            }
        });

        FirebaseStorage storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();

        final Bundle bundle = getIntent().getExtras();
        from=bundle.getString("from");

        final boolean[] m = {false};


        DatabaseReference[] reference_contacts2 = {FirebaseDatabase.getInstance().getReference("buyer_order")};
        reference_contacts2[0].addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

//                    Log.d("TAG", "ds.child(mail)1: "+ds.child("mail").getValue());
                    if(ds.child("messageTime").getValue().equals(bundle.getLong("time"))){
//                        mynum[0] = ds.child("memberNum").getValue().toString();
//                        ordername=ds.getKey();
                        user=ds.child("user").getValue().toString();
                        mypic[0] = ds.child("picture").getValue().toString();
                        Log.d("BO", "onDataChange1: "+mypic[0]);
                        m[0] =true;

                    }

                    if(m[0]==true){
                        String test=mypic[0];
                        if(test.contains("null")){}
                        else{

                            riversRef = mStorageRef.child("buyer_order").child(mypic[0]);

                            Log.d("down", "onSuccess: "+mypic[0]);
//
                            downloadImg(riversRef,iv271);

                        }



                    }


                }


            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });



        final String title=bundle.getString("name");
        final String brand=bundle.getString("brand");
        final String classify=bundle.getString("classify");
        final String standard=bundle.getString("standard");
        String model=bundle.getString("model");
        if(model.equals("")){
            model="(無說明型號)";
        }
        String url=bundle.getString("url");
        if(url.equals(""))
        {
            url="(無參考網址)";
        }
        final String country=bundle.getString("country");
        String place=bundle.getString("place");
        if(place.equals("")){
            place="(無參考地點)";
        }
        String other=bundle.getString("other");
        if(other.equals(""))
        {
            other="無";
        }

        final String price=bundle.getString("price");

        final String num=bundle.getString("num");
        final String fee=bundle.getString("fee");
        final String delivery=bundle.getString("delivery");
        final Long time=bundle.getLong("time");
        final String buyer=bundle.getString("buyer");
        final String buyernick=bundle.getString("buyernick");
        final String receipt=bundle.getString("receipt");

        if(price.equals("旅人出價"))
        {}
        else{
            Topprice.setText("單價NTD$"+price+"起");
        }

            if(fee.equals("旅人出價")){
//            int feetemp=Integer.parseInt(fee);
//            int pricetemp=Integer.parseInt(price);
//            int sum=feetemp+pricetemp;

        }else{
                Topfee.setText("可賺NTD$"+Integer.parseInt(fee)*Integer.parseInt(num));
        }


//        Title.setText(title);
        Brand.setText(brand);
        Classify.setText(classify);
        Name.setText(title);
        Standard.setText(standard);
        Model.setText(model);
        Url.setText(url);
        Country.setText(country);
        Place.setText(place);
        Other.setText(other);
        Price.setText(price);
        Num.setText(num);
        Fee.setText(fee);
        Delivery.setText(delivery);
        Time.setText(DateFormat.format("yyyy-MM-dd(HH:mm)",
                time));
//        if(buyer.contains("@yahoo.com"))
//            User.setText(buyer.replace("@yahoo.com",""));
//        else if(buyer.contains("@mail2.nccu.tw"))
//            User.setText(buyer.replace("@mail2.nccu.tw",""));
//        else
            User.setText(buyernick);
        Receipt.setText(receipt);

        DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_file")};
        reference_contacts[0].addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

//                    Log.d("TAG", "ds.child(mail)1: "+ds.child("mail").getValue());
                    if(ds.child("mail").getValue().equals(FirebaseAuth.getInstance()
                            .getCurrentUser()
                            .getEmail())){
                        nickname=ds.child("nickname").getValue().toString();
                        isSeller=ds.child("isSeller").getValue().toString();
                        Log.d("272", "onDataChange: "+nickname);

                        if(buyernick.equals(nickname))
                        {}
                        else {

                            User.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent();
                                    intent.setClass(BuyerOrder.this, Member_page.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("from", "BuyerOrder");
                                    bundle.putString("useracc", String.valueOf(buyer));
                                    bundle.putString("user", String.valueOf(buyernick));
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });
                        }

                    }
                    if(ds.child("mail").getValue().equals(user)){
                        String test=ds.child("picture").getValue().toString();
                        if(test.contains("null")){}
                        else{
                        riversRef = mStorageRef.child("member_picture").child(ds.child("picture").getValue().toString());
                        downloadImg(riversRef,iv272);
                    }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
        final String times=Long.toString(time);



        FloatingActionButton fab =
                (FloatingActionButton)findViewById(R.id.fab27);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText input = (EditText)findViewById(R.id.et27_1);


                if (input.getText().toString().equals("")||input.getText().toString().equals(" ")||input.getText().toString().equals("  "))
                {

                    Toast.makeText(BuyerOrder.this, "輸入不可為空", Toast.LENGTH_SHORT).show();

                }
                else {


                    // Read the input field and push a new instance
                    // of ChatMessage to the Firebase database


                    FirebaseDatabase.getInstance()
                            .getReference("buyer_product_chat")
                            .child(times)
                            .push()
                            .setValue(new ChatMessage(input.getText().toString(),
                                    nickname,
                                    iam
                            ));
                    input.setText("");
                }
                listOfMessages.setStackFromBottom(false);
                if (!listOfMessages.isStackFromBottom()) {
                    listOfMessages.setStackFromBottom(true);

                }
            }
        });
        btn271 = (Button)findViewById(R.id.btn27_1);

        btn271.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(buyer.equals(iam)){
                    Toast.makeText(BuyerOrder.this, "這是您的請求單喔~", Toast.LENGTH_SHORT).show();

                }
                 else if(isSeller.equals("0")||isSeller.equals("2")){
                    Toast.makeText(BuyerOrder.this, "您還不是賣家喔！", Toast.LENGTH_SHORT).show();
                }
                else{

                    new AlertDialog.Builder(BuyerOrder.this)
                            .setTitle("訂單確認")
                            .setMessage("如費用需議價，請先和買家確認過單價和代購費再按下'確認'鍵接單唷！謝謝")
                            .setNegativeButton("關閉視窗", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {



                                        Intent intent = new Intent();
                                        Bundle bundle=new Bundle();
                                        bundle.putLong("productOrderTime",time);
                                        bundle.putString("buyer",buyer);
                                        bundle.putInt("num", Integer.parseInt(num));
                                        bundle.putString("picture",mypic[0]);
                                        bundle.putString("product",title);
                                        bundle.putString("receipt",receipt);
                                        if(!price.equals("旅人出價"))
                                            bundle.putInt("price", Integer.parseInt(price));
                                        else
                                            bundle.putInt("price", 0);
                                        if(!fee.equals("旅人出價"))
                                            bundle.putInt("fee", Integer.parseInt(fee));
                                        else
                                            bundle.putInt("fee", 0);

                                        intent.setClass(BuyerOrder.this, Checkout2.class);
                                        intent.putExtras(bundle);
                                        startActivity(intent);


                                }
                            })
                            .show();


                }

            }

        });

        btn272 = (Button)findViewById(R.id.btn27_2);

        btn272.setOnClickListener(new View.OnClickListener() {
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

                                        m[0] =true;
                                    }
                                    Log.d("TAG", "ds.child(mail)2: "+ds.child("mail").getValue());
                                    if(ds.child("mail").getValue().equals(user2)){
                                        user2num[0] = ds.child("mail").getValue().toString();

                                        u[0] =true;
                                    }
                                    if(m[0]==true&&u[0]==true){
                                        if(user2.equals(iam)){
                                            Toast.makeText(BuyerOrder.this, "這是您的請求單喔~", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Log.d("TAG", "mynum: "+mynum[0]+"user2: "+user2num[0]);
                                            Bundle bundle2=new Bundle();

                                            bundle2.putString("user1",mynum[0]);
                                            bundle2.putString("user2",user2num[0]);
                                            bundle2.putString("usernic", String.valueOf(buyernick));


                                            bundle2.putString("classify",classify);
                                            bundle2.putString("brand", brand);
                                            bundle2.putString("name", title);
                                            bundle2.putString("standard", standard);
                                            bundle2.putString("model",bundle.getString("model"));
                                            bundle2.putString("url", bundle.getString("url"));
                                            bundle2.putString("country", country);
                                            bundle2.putString("place",bundle.getString("place"));
                                            bundle2.putString("other", bundle.getString("other"));
                                            bundle2.putString("price", price);
                                            bundle2.putString("num",num);
                                            bundle2.putString("fee", fee);
                                            bundle2.putString("delivery", delivery);
                                            bundle2.putLong("time", time);
                                            bundle2.putString("who",buyer );
                                            bundle2.putString("whonick",buyernick);
                                            bundle2.putString("receipt", receipt);

                                            bundle2.putString("from", "Forun");
                                            bundle2.putString("from2", "BuyerOrder");
                                            Intent intent = new Intent();
                                            intent.setClass(BuyerOrder.this, ChatRoom2.class);
                                            intent.putExtras(bundle2);
                                            startActivity(intent);
                                            BuyerOrder.this.finish();

                                        }


                                    }

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




        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                R.layout.message, FirebaseDatabase.getInstance().getReference("buyer_product_chat").child(times)) {
            @Override
            protected void populateView(View v, final ChatMessage model, int position) {

                // Get references to the views of message.xml
                TextView messageText = (TextView)v.findViewById(R.id.message_text);
                final TextView messageUser = (TextView)v.findViewById(R.id.message_user);
                TextView messageTime = (TextView)v.findViewById(R.id.message_time);

                // Set their text
                messageText.setText(model.getMessageText());
//                messageUser.setText(FirebaseAuth.getInstance()
//                        .getCurrentUser().getEmail()
//                        );
                messageUser.setText(model.getMessageUser());

                if(model.getMessageUser().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail()))
                {}
                else {
                    messageUser.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(BuyerOrder.this, Member_page.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("from", "BuyerOrder");
                            bundle.putString("useracc", model.getUseracc());
                            bundle.putString("user", model.getMessageUser());
                            intent.putExtras(bundle);
                            startActivity(intent);
//                        ArticlePage.this.finish();

                        }
                    });

                }
                // Format the date before showing it"dd-MM-yyyy (HH:mm:ss)"
                messageTime.setText(DateFormat.format("yyyy-MM-dd(HH:mm)",
                        model.getMessageTime()));
            }
        };
//
        listOfMessages.setAdapter(adapter);

        listOfMessages.setScrollbarFadingEnabled(false);
        listOfMessages.setOnTouchListener(new AdapterView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                sv26.setFocusable(false);
                listOfMessages.isFocused();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    sv26.stopNestedScroll();
                }
                return false;
            }
        });

//        heart = (ImageButton)findViewById(R.id.btnheart0);
//        heart.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                if(!likelist.contains(ordername))
//                {
//                    Toast.makeText(BuyerOrder.this, "已收藏",Toast.LENGTH_SHORT).show();
//                    heart.setBackgroundResource(R.drawable.ic_heart_dark);
//                    likelist.add(ordername);
////                    ischange=true;
//                }
//                else {
//
//                    heart.setBackgroundResource(R.drawable.ic_heart_light);
//                    likelist.remove(ordername);
////                    ischange=false;
//                }
//            }
//        });

//        View.OnClickListener OCL = new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                if (v instanceof ImageButton) {
//                    int id = ((ImageButton) v).getId();
//                    switch (id) {
//                        case R.id.ib27_1:
////                            Bundle bundlepage = new Bundle();
////                            bundlepage.putString("pagefrom","buyer");
//                            String msg6 = "返回論壇";
//                            Intent intent6 = new Intent();
//                            intent6.setClass(BuyerOrder.this,Seller.class);
//                            startActivity(intent6);
////                        Toast.makeText(Forum.this, msg6, Toast.LENGTH_SHORT).show();
//                            BuyerOrder.this.finish();
//                            break;
//
//                    }
//                };
//            }
//        };
//        ib271.setOnClickListener(OCL);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar27);

        setSupportActionBar(toolbar);
        toolbar.setTitle(title);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                if(from.equals("Seller"))
                    intent1.setClass(BuyerOrder.this, Seller.class);
                else if(from.equals("Result"))
                    BuyerOrder.this.finish();
                else
                    intent1.setClass(BuyerOrder.this, Forum.class);

//                if(likelist.contains(ordername)){
//                    DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_file")};
//                    reference_contacts[0].addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//
//                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                                if(ds.child("mail").getValue().equals(FirebaseAuth.getInstance()
//                                        .getCurrentUser()
//                                        .getEmail())){
//                                    FirebaseDatabase.getInstance().getReference("buyer_file")
//                                            .child(ds.child("memberNum").getValue().toString())
//                                            .child("fansNum").child(ordername).setValue(ordername);
//
//                                }
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//
//
//                    });
//
//                }
//                else{
//
//                    DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_file")};
//                    reference_contacts[0].addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//
//                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                                if(ds.child("mail").getValue().equals(FirebaseAuth.getInstance()
//                                        .getCurrentUser()
//                                        .getEmail())){
//                                    for (DataSnapshot dds : ds.child("fansNum").getChildren()){
//                                        if(dds.getKey().equals(ordername)){
//                                            FirebaseDatabase.getInstance().getReference("buyer_file")
//                                                    .child(ds.child("memberNum").getValue().toString())
//                                                    .child("fansNum").child(ordername).removeValue();
//
//                                        }
//                                    }
//
//                                }
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//
//
//                    });
//
//
//                }


                if(!from.equals("Result")){
                    startActivity(intent1);
                    BuyerOrder.this.finish();
                }

            }
        });
    }

    private void downloadImg(final StorageReference ref, final ImageView img){
        if(ref == null){
            Toast.makeText(BuyerOrder.this, "請先上傳照片", Toast.LENGTH_SHORT).show();
            Log.d("down", "onSuccess: ");
            return;
        }



        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(!isDestroy(BuyerOrder.this)){
                    Glide.with(BuyerOrder.this)

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
                intent1.setClass(BuyerOrder.this, Seller.class);
            else if(from.equals("Result"))
                BuyerOrder.this.finish();
            else
                intent1.setClass(BuyerOrder.this, Forum.class);
            startActivity(intent1);

//            if(likelist.contains(ordername)){
//                DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_file")};
//                reference_contacts[0].addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//
//                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                            if(ds.child("mail").getValue().equals(FirebaseAuth.getInstance()
//                                    .getCurrentUser()
//                                    .getEmail())){
//                                FirebaseDatabase.getInstance().getReference("buyer_file")
//                                        .child(ds.child("memberNum").getValue().toString())
//                                        .child("fansNum").child(ordername).setValue(ordername);
//
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//
//
//                });
//
//            }
//            else{
//
//                DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_file")};
//                reference_contacts[0].addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//
//                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                            if(ds.child("mail").getValue().equals(FirebaseAuth.getInstance()
//                                    .getCurrentUser()
//                                    .getEmail())){
//                                for (DataSnapshot dds : ds.child("fansNum").getChildren()){
//                                    if(dds.getKey().equals(ordername)){
//                                        FirebaseDatabase.getInstance().getReference("buyer_file")
//                                                .child(ds.child("memberNum").getValue().toString())
//                                                .child("fansNum").child(ordername).removeValue();
//
//                                    }
//                                }
//
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//
//
//                });
//
//
//            }

            if(!from.equals("Result")){
                startActivity(intent1);
                BuyerOrder.this.finish();
            }
        }
        return false;
    }

}