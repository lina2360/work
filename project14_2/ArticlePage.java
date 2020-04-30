package com.project14.nccu105.project14_2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
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


public class ArticlePage extends AppCompatActivity {
    TextView et391,et392,et393,et394;
    ImageButton ib391;
    private FirebaseListAdapter<ChatMessage> adapter;

    EditText input;
    private StorageReference mStorageRef;
    private StorageReference riversRef;
    String[] mypic =new String[1];
    ImageView iv391;
    String fbdata ="";
    String fbpic ="";
    String nickname="";
    String temp ;
    String times;
    ListView listOfMessages;
    Bundle bundle;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article);
//        getSupportActionBar().hide();
//        //設定隱藏狀態



        bundle = getIntent().getExtras();

//        LinearLayout ll39=(LinearLayout)findViewById(R.id.ll39);
//        final TextView Title = (TextView)findViewById(R.id.tv39_1);
        TextView Classify = (TextView)findViewById(R.id.et39_1);
        TextView Time = (TextView)findViewById(R.id.et39_2);
        final TextView User = (TextView)findViewById(R.id.et39_3);
        TextView Content = (TextView)findViewById(R.id.et39_4);
//        ib391 = (ImageButton) findViewById(R.id.ib39_1);

        listOfMessages = (ListView)findViewById(R.id.article_messages);


        //算長度
//        Utility.setListViewHeightBasedOnChildren(listOfMessages);

        iv391= (ImageView)findViewById(R.id.iv39_1);


        input = (EditText)findViewById(R.id.et39_5);

//db
       if(bundle.getInt("who") == 0){
           fbpic="buyer_article";
           if(bundle.getInt("num") == 1){
               fbdata="articletitle";
           }
           else if(bundle.getInt("num") == 2){
               fbdata="articletitle2";
           }
           else if(bundle.getInt("num") == 3){
               fbdata="articletitle3";
           }
           else if(bundle.getInt("num") == 4){
               fbdata="articletitle4";
           }
           else if(bundle.getInt("num") == 5){
               fbdata="articletitle5";
           }
       }
       else if(bundle.getInt("who") == 2){
           fbpic="official_article";
           fbdata="official_article";
       }
       else{
           fbpic="seller_article";
           if(bundle.getInt("num") == 1){
               fbdata="seller_article";
           }
           else if(bundle.getInt("num") == 2){
               fbdata="seller_article2";
           }
           else if(bundle.getInt("num") == 3){
               fbdata="seller_article3";
           }
           else if(bundle.getInt("num") == 4){
               fbdata="seller_article4";
           }
           else if(bundle.getInt("num") == 5){
               fbdata="seller_article5";
           }

       }
//        Log.d("AP", "onCreate: fbdata"+fbdata+" num: "+bundle.getInt("num")+" who: "+bundle.getInt("who"));

        FirebaseStorage storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();


        final boolean[] m = {false};


        DatabaseReference[] reference_contacts2 = {FirebaseDatabase.getInstance().getReference(fbdata)};
        reference_contacts2[0].addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

//                    Log.d("TAG", "ds.child(mail)1: "+ds.child("mail").getValue());
                    if(ds.child("messageTime").getValue().equals(bundle.getLong("time"))){
//                        mynum[0] = ds.child("memberNum").getValue().toString();
                        mypic[0] = ds.child("picture").getValue().toString();
//                        Log.d("BO", "onDataChange1: "+mypic[0]);
                        m[0] =true;

                    }

                    if(m[0]==true){
                        String test=mypic[0];
                        if(test.contains("null")){}
                        else{

                            riversRef = mStorageRef.child(fbpic).child(mypic[0]);
//                            Log.d("down", "onSuccess: "+mypic[0]);
//
                            downloadImg(riversRef);
                        }


                    }


                }


            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
//        if(bundle.containsKey("buyer")){};
        String title=bundle.getString("title");
        String classify = bundle.getString("classify");
        Long time=bundle.getLong("time");
        final String name = bundle.getString("name");
        final String usernick=bundle.getString("usernick");

        String content = bundle.getString("content");
//        Title.setText(title);
        Classify.setText(classify);
        Time.setText(DateFormat.format("yyyy-MM-dd(HH:mm)",
                time));
        User.setText(usernick);

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

                        if(usernick.equals(nickname))
                        {}
                        else {

                            User.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent();
                                    intent.setClass(ArticlePage.this, Member_page.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("from", "ArticlePage");
                                    bundle.putString("user", String.valueOf(usernick));
                                    bundle.putString("useracc", String.valueOf(name));
                                    intent.putExtras(bundle);
                                    startActivity(intent);
//                        ArticlePage.this.finish();

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
        Content.setText(content);




        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        times=Long.toString(time);

        FloatingActionButton fab =
                (FloatingActionButton)findViewById(R.id.fab39);

        if(bundle.getInt("who") == 0){
            temp="buyer_article_chat";
        }
        else  if(bundle.getInt("who") == 2){
            temp="official_article_chat";
        }
        else{
            temp ="seller_article_chat";
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (input.getText().toString().equals("")||input.getText().toString().equals(" ")||input.getText().toString().equals("  "))
                {

                    Toast.makeText(ArticlePage.this, "輸入不可為空", Toast.LENGTH_SHORT).show();

                }
                else {
                    // Read the input field and push a new instance
                    // of ChatMessage to the Firebase database



                    FirebaseDatabase.getInstance()
                            .getReference(temp)
                            .child(times)
                            .push()
                            .setValue(new ChatMessage(input.getText().toString(),
                                    nickname,
                                    FirebaseAuth.getInstance()
                                            .getCurrentUser()
                                            .getEmail()
                            ));
                    input.setText("");


                    // Clear the input

                }
                listOfMessages.setStackFromBottom(false);
                if (!listOfMessages.isStackFromBottom()) {
                    listOfMessages.setStackFromBottom(true);
                    Utility.setListViewHeightBasedOnChildren(listOfMessages);
                }



            }
        });

        displayChatMessages();


        TextView tv396 = (TextView)findViewById(R.id.tv39_6);
        tv396.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.setListViewHeightBasedOnChildren(listOfMessages);
            }
        });


        //算長度
//        Utility.setListViewHeightBasedOnChildren(listOfMessages);

//        listOfMessages.setScrollbarFadingEnabled(false);

        final ScrollView sv39 = (ScrollView) findViewById(R.id.sv39);
        sv39.post(new Runnable() {
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.
//                    mRootScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                int[] location = new int[2];
//                Title.getLocationOnScreen(location);
                int offset = location[1] - sv39.getMeasuredHeight();
                if (offset < 0) {
                    offset = 0;
                }
                sv39.smoothScrollTo(0, offset);
            }
        });





        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar39);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if (bundle.getInt("who") == 0) {
//                    intent.setClass(ArticlePage.this, Forum_buyerforum.class);
//                    startActivity(intent);
                }
                else if (bundle.getInt("who") == 2) {

                }
                else if (bundle.getInt("who") == 3) {

                }else {
//                    intent.setClass(ArticlePage.this, Forum_sellerforum.class);
//                    startActivity(intent);
                }



                ArticlePage.this.finish();
            }
        });


    }

    private void displayChatMessages() {


        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                R.layout.message_article, FirebaseDatabase.getInstance().getReference(temp).child(times)) {
            @Override
            protected void populateView(View v, final ChatMessage model, int position) {

                // Get references to the views of message.xml
                TextView messageText = (TextView)v.findViewById(R.id.message_art_text);
                final TextView messageUser = (TextView)v.findViewById(R.id.message_art_user);
                TextView messageTime = (TextView)v.findViewById(R.id.message_art_time);
                TextView messagenum = (TextView)v.findViewById(R.id.message_art_num);
                messagenum.setText("#"+Integer.toString(position+1));
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
                            intent.setClass(ArticlePage.this, Member_page.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("from", "ArticlePage");
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
        Utility.setListViewHeightBasedOnChildren(listOfMessages);

    }



    private void downloadImg(final StorageReference ref){
        if(ref == null){
            Toast.makeText(ArticlePage.this, "請先上傳照片", Toast.LENGTH_SHORT).show();
            Log.d("down", "onSuccess: ");
            return;
        }



        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(!isDestroy(ArticlePage.this)){
                    Glide.with(ArticlePage.this)

                            .load(ref)
                            .into(iv391);

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
            final Bundle bundle = getIntent().getExtras();
            Intent intent = new Intent();
            if (bundle.getInt("who") == 0) {
//                intent.setClass(ArticlePage.this, Forum_buyerforum.class);
            } else {
//                intent.setClass(ArticlePage.this, Forum_sellerforum.class);
            }

//            startActivity(intent);

            ArticlePage.this.finish();
        }
        return false;
    }


}
