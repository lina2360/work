package com.project14.nccu105.project14_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class ChatRoom2 extends AppCompatActivity {
    private FirebaseListAdapter<ChatMessage> adapter;
    private static final int SIGN_IN_REQUEST_CODE = 5;
    String recognize;
    String nickname="";
//    String me="";
    String user22;
    Toolbar toolbar;
    Bundle bundle;
    String user1;
    String user2;
    String from;

    String from2;
    FloatingActionButton fab;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatroom2);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        toolbar = (Toolbar) findViewById(R.id.toolbar_cr) ;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);

        final Bundle bundle = getIntent().getExtras();
        from=bundle.getString("from");
        from2=bundle.getString("from2");

        TextView tv=(TextView)findViewById(R.id.message_user);



        if(!bundle.getString("from").equals("Notify")){
            user1=bundle.getString("user1");
            user2=bundle.getString("user2");
            toolbar.setTitle(bundle.getString("usernic"));

        }
        else{
            user1=bundle.getString("user1");
            user2=bundle.getString("user2");
//            recognize=bundle.getString("recognize");
        }







        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            // Start sign in/sign up activity
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .build(),
                    SIGN_IN_REQUEST_CODE
            );
        } else {
            // User is already signed in. Therefore, display
            // a welcome Toast
//            Toast.makeText(this,
//                    "Welcome " + FirebaseAuth.getInstance()
//                            .getCurrentUser()
//                            .getEmail(),
//                    Toast.LENGTH_LONG)
//                    .show();

            // Load chat room contents
            displayChatMessages();

        }

      fab =
                (FloatingActionButton)findViewById(R.id.fab);

        DatabaseReference[] reference_contacts3 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
        reference_contacts3[0].addValueEventListener(new ValueEventListener() {
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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText input = (EditText)findViewById(R.id.input);



                    // Read the input field and push a new instance
                    // of ChatMessage to the Firebase database
                if (input.getText().toString().equals("")||input.getText().toString().equals(" ")||input.getText().toString().equals("  "))
                {
                    Toast.makeText(ChatRoom2.this, "輸入不可為空", Toast.LENGTH_SHORT).show();
                }
                else{

                    if (recognize == null) {
//                            Log.d("recog", "displayChatMessages: " + recognize);
                            recognize = Long.toString(new Date().getTime());
//                            String db = "user1";
//                            String value = user1;
                        FirebaseDatabase.getInstance()
                                .getReference("chatroom")
                                .child(recognize)
                                .setValue(new InitialChat(
                                        user1,user2
                                ));

                        FirebaseDatabase.getInstance()
                                .getReference("chatroom")
                                .child(recognize)
                                .child("chat")
                                .push()
                                .setValue(new ChatMessage(input.getText().toString(),
                                        nickname,FirebaseAuth.getInstance()
                                        .getCurrentUser()
                                        .getEmail())
                                );

                            displayChatMessages();




                        }else{
                        FirebaseDatabase.getInstance()
                                .getReference("chatroom")
                                .child(recognize)
                                .child("chat")
                                .push()
                                .setValue(new ChatMessage(input.getText().toString(),
                                        nickname,FirebaseAuth.getInstance()
                                        .getCurrentUser()
                                        .getEmail())
                                );

                    }
                    displayChatMessages();



                    // Clear the input
                    input.setText("");


                }





            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle2=new Bundle();
                Intent intent = new Intent();

                if(from2.equals("SellerOrder")){
                    bundle2.putString("classify",bundle.getString("classify"));
                    bundle2.putString("brand", bundle.getString("brand"));
                    bundle2.putString("name", bundle.getString("name"));
                    bundle2.putString("standard", bundle.getString("standard"));
                    bundle2.putString("model",bundle.getString("model"));
                    bundle2.putString("url", bundle.getString("url"));
                    bundle2.putString("country", bundle.getString("country"));
                    bundle2.putString("place",bundle.getString("place"));
                    bundle2.putString("other", bundle.getString("other"));
                    bundle2.putString("price", bundle.getString("price"));
                    bundle2.putString("num",bundle.getString("num"));
                    bundle2.putString("fee", bundle.getString("fee"));
                    bundle2.putString("delivery", bundle.getString("delivery"));
                    bundle2.putLong("time", bundle.getLong("time"));
                    bundle2.putString("seller",bundle.getString("who"));
                    bundle2.putString("sellernick",bundle.getString("whonick"));
                    bundle2.putString("receipt", bundle.getString("receipt"));
                    bundle2.putString("from", "Forun");

                    intent.setClass(ChatRoom2.this, SellerOrder.class);
                    intent.putExtras(bundle2);
                    startActivity(intent);
                    ChatRoom2.this.finish();
                }
                else if(from2.equals("BuyerOrder")){

                    bundle2.putString("classify",bundle.getString("classify"));
                    bundle2.putString("brand", bundle.getString("brand"));
                    bundle2.putString("name", bundle.getString("name"));
                    bundle2.putString("standard", bundle.getString("standard"));
                    bundle2.putString("model",bundle.getString("model"));
                    bundle2.putString("url", bundle.getString("url"));
                    bundle2.putString("country", bundle.getString("country"));
                    bundle2.putString("place",bundle.getString("place"));
                    bundle2.putString("other", bundle.getString("other"));
                    bundle2.putString("price", bundle.getString("price"));
                    bundle2.putString("num",bundle.getString("num"));
                    bundle2.putString("fee", bundle.getString("fee"));
                    bundle2.putString("delivery", bundle.getString("delivery"));
                    bundle2.putLong("time", bundle.getLong("time"));
                    bundle2.putString("buyer",bundle.getString("who"));
                    bundle2.putString("buyernick",bundle.getString("whonick"));
                    bundle2.putString("receipt", bundle.getString("receipt"));
                    bundle2.putString("from", "Forun");

                    intent.setClass(ChatRoom2.this, BuyerOrder.class);
                    intent.putExtras(bundle2);
                    startActivity(intent);
                    ChatRoom2.this.finish();

                }
                else if(from2.equals("Order")){
                    bundle2.putString("orderNum", bundle.getString("orderNum"));
                    bundle2.putString("orderkind", bundle.getString("orderkind"));
                    bundle2.putString("orderpic", bundle.getString("orderpic"));
                    bundle2.putString("usernic",bundle.getString("usernic"));
                    bundle2.putString("user", bundle.getString("user"));
                    intent.setClass(ChatRoom2.this, Order.class);
                    intent.putExtras(bundle2);
                    startActivity(intent);
                    ChatRoom2.this.finish();
                }
                else if(from2.equals("Member_page")){
                    ChatRoom2.this.finish();
                }
                else if(from2.equals("Notify")) {
                    intent.setClass(ChatRoom2.this, Notify.class);
                    startActivity(intent);
                    ChatRoom2.this.finish();
                }

            }
        });

    }

    private void displayChatMessages() {

        DatabaseReference[] reference_contacts2 = {FirebaseDatabase.getInstance().getReference("chatroom")};
        reference_contacts2[0].addValueEventListener(new ValueEventListener() {
            boolean user1b=false;
            boolean user2b=false;
            int count=0;
            int children=0;
            boolean has=false;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                children=(int)dataSnapshot.getChildrenCount();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    count++;

//                    Log.d("childrenn", "onDataChange: "+children);
                    if(ds.child("user1").getValue().toString().equals(user1)||ds.child("user1").getValue().toString().equals(user2)){
                        user1b=true;
                    }
//                    Log.d("this", "onDataChange: "+ds.child("user2").getValue().toString());
                    if(ds.child("user2").getValue().toString().equals(user1)||ds.child("user2").getValue().toString().equals(user2)){
                        user2b=true;
                    }
                    if(user1b==true&&user2b==true){
                        recognize=ds.getKey();
                        has=true;
//                        Log.d("recognize", "onDataChange: "+recognize);
                    }

                    user1b=false;
                    user2b=false;

                }
                if(count==children&&has==true){



                    final ListView listOfMessages = (ListView)findViewById(R.id.list_of_messages);
//
                    adapter = new FirebaseListAdapter<ChatMessage>(ChatRoom2.this, ChatMessage.class,
//



                            R.layout.message_chat_member, FirebaseDatabase.getInstance().getReference("chatroom").child(recognize).child("chat")) {
                        @Override
                        protected void populateView(View v, ChatMessage model, int position) {

                            TextView messageText = (TextView)v.findViewById(R.id.message_text1);
                            final TextView messageUser = (TextView)v.findViewById(R.id.message_user1);
                            TextView messageTime = (TextView)v.findViewById(R.id.message_time1);
                            ImageView iv5 = (ImageView) v.findViewById(R.id.imageView5);
                            ImageView iv51 = (ImageView) v.findViewById(R.id.imageView18);
                            ImageView iv52 = (ImageView) v.findViewById(R.id.imageView19);
                            ImageView iv53 = (ImageView) v.findViewById(R.id.imageView20);
                            ImageView iv54 = (ImageView) v.findViewById(R.id.imageView21);

                            TextView messageText2 = (TextView)v.findViewById(R.id.message_text2);
                            final TextView messageUser2 = (TextView)v.findViewById(R.id.message_user2);
                            TextView messageTime2 = (TextView)v.findViewById(R.id.message_time2);
                            ImageView iv6 = (ImageView) v.findViewById(R.id.imageView6);
                            ImageView iv61 = (ImageView) v.findViewById(R.id.imageView14);
                            ImageView iv62 = (ImageView) v.findViewById(R.id.imageView15);
                            ImageView iv63 = (ImageView) v.findViewById(R.id.imageView16);
                            ImageView iv64 = (ImageView) v.findViewById(R.id.imageView17);
                            if (!model.getMessageUser().equals(nickname)&&from.equals("Notify")){
                                user22=model.getMessageUser();
                                toolbar.setTitle(user22);
                            }
                            messageText2.setText(model.getMessageText());
                            messageUser2.setText(model.getMessageUser());
                            messageTime2.setText(DateFormat.format("yyyy-MM-dd(HH:mm)",
                                    model.getMessageTime()));



                            // Set their text
                            messageText.setText(model.getMessageText());


//                messageUser.setText(FirebaseAuth.getInstance()
//                        .getCurrentUser().getEmail()
//                        );
                            messageUser.setText(model.getMessageUser());
                            if(model.getMessageUser().equals(nickname))
                            {
                                messageText.setVisibility( View.GONE );
                                messageUser.setVisibility( View.GONE );
                                messageTime.setVisibility( View.GONE );
                                iv5.setVisibility( View.GONE );
                                iv51.setVisibility( View.GONE );
                                iv52.setVisibility( View.GONE );
                                iv53.setVisibility( View.GONE );
                                iv54.setVisibility( View.GONE );

                                messageText2.setVisibility( View.VISIBLE );
                                messageUser2.setVisibility( View.VISIBLE );
                                messageTime2.setVisibility( View.VISIBLE);
                                iv6.setVisibility( View.VISIBLE );
                                iv61.setVisibility( View.VISIBLE );
                                iv62.setVisibility( View.VISIBLE );
                                iv63.setVisibility( View.VISIBLE  );
                                iv64.setVisibility( View.VISIBLE  );

                            }
                            else {

                                messageText2.setVisibility( View.GONE );
                                messageUser2.setVisibility( View.GONE );
                                messageTime2.setVisibility( View.GONE );
                                iv6.setVisibility( View.GONE );
                                iv61.setVisibility( View.GONE );
                                iv62.setVisibility( View.GONE );
                                iv63.setVisibility( View.GONE );
                                iv64.setVisibility( View.GONE );

                                messageText.setVisibility( View.VISIBLE );
                                messageUser.setVisibility( View.VISIBLE );
                                messageTime.setVisibility( View.VISIBLE);
                                iv5.setVisibility( View.VISIBLE );
                                iv51.setVisibility( View.VISIBLE );
                                iv52.setVisibility( View.VISIBLE );
                                iv53.setVisibility( View.VISIBLE );
                                iv54.setVisibility( View.VISIBLE );


                            }
                            // Format the date before showing it
                            messageTime.setText(DateFormat.format("yyyy-MM-dd(HH:mm)",
                                    model.getMessageTime()));








                        }
                    };
//
                    listOfMessages.setAdapter(adapter);
//                    Utility.setListViewHeightBasedOnChildren(listOfMessages);
//                    int[] location = new int[2];
//                    fab.getLocationOnScreen(location);

//                    ScrollView svsv =(ScrollView)findViewById(R.id.svsv);

//                    int offset =  listOfMessages.getHeight();
////                    if (offset < 0) {
////                        offset = 0;
////                    }
//                    listOfMessages.
//                    listOfMessages.fullScroll(ScrollView.FOCUS_DOWN);
//listOfMessages.setSelection(listOfMessages.getChildCount()-1);
//                    listOfMessages.getChildAt( listOfMessages.getChildCount()).getHeight();
//                    listOfMessages.scrollTo(listOfMessages.getScrollX(),   listOfMessages.getScrollY());





                }



            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });




    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SIGN_IN_REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                displayChatMessages();
            } else {
                Toast.makeText(this,
                        "We couldn't sign you in. Please try again later.",
                        Toast.LENGTH_LONG)
                        .show();

                // Close the app
                finish();
            }
        }

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Bundle bundle=getIntent().getExtras();
            Bundle bundle2=new Bundle();
            Intent intent = new Intent();

            if(from2.equals("SellerOrder")){
                bundle2.putString("classify",bundle.getString("classify"));
                bundle2.putString("brand", bundle.getString("brand"));
                bundle2.putString("name", bundle.getString("name"));
                bundle2.putString("standard", bundle.getString("standard"));
                bundle2.putString("model",bundle.getString("model"));
                bundle2.putString("url", bundle.getString("url"));
                bundle2.putString("country", bundle.getString("country"));
                bundle2.putString("place",bundle.getString("place"));
                bundle2.putString("other", bundle.getString("other"));
                bundle2.putString("price", bundle.getString("price"));
                bundle2.putString("num",bundle.getString("num"));
                bundle2.putString("fee", bundle.getString("fee"));
                bundle2.putString("delivery", bundle.getString("delivery"));
                bundle2.putLong("time", bundle.getLong("time"));
                bundle2.putString("seller",bundle.getString("who"));
                bundle2.putString("sellernick",bundle.getString("whonick"));
                bundle2.putString("receipt", bundle.getString("receipt"));
                bundle2.putString("from", "Forun");

                intent.setClass(ChatRoom2.this, SellerOrder.class);
                intent.putExtras(bundle2);
                startActivity(intent);
                ChatRoom2.this.finish();
            }
            else if(from2.equals("BuyerOrder")){

                bundle2.putString("classify",bundle.getString("classify"));
                bundle2.putString("brand", bundle.getString("brand"));
                bundle2.putString("name", bundle.getString("name"));
                bundle2.putString("standard", bundle.getString("standard"));
                bundle2.putString("model",bundle.getString("model"));
                bundle2.putString("url", bundle.getString("url"));
                bundle2.putString("country", bundle.getString("country"));
                bundle2.putString("place",bundle.getString("place"));
                bundle2.putString("other", bundle.getString("other"));
                bundle2.putString("price", bundle.getString("price"));
                bundle2.putString("num",bundle.getString("num"));
                bundle2.putString("fee", bundle.getString("fee"));
                bundle2.putString("delivery", bundle.getString("delivery"));
                bundle2.putLong("time", bundle.getLong("time"));
                bundle2.putString("buyer",bundle.getString("who"));
                bundle2.putString("buyernick",bundle.getString("whonick"));
                bundle2.putString("receipt", bundle.getString("receipt"));
                bundle2.putString("from", "Forun");

                intent.setClass(ChatRoom2.this, BuyerOrder.class);
                intent.putExtras(bundle2);
                startActivity(intent);
                ChatRoom2.this.finish();

            }
            else if(from2.equals("Order")){
                bundle2.putString("orderNum", bundle.getString("orderNum"));
                bundle2.putString("orderkind", bundle.getString("orderkind"));
                bundle2.putString("orderpic", bundle.getString("orderpic"));
                bundle2.putString("usernic",bundle.getString("usernic"));
                bundle2.putString("user", bundle.getString("user"));
                intent.setClass(ChatRoom2.this, Order.class);
                intent.putExtras(bundle2);
                startActivity(intent);
                ChatRoom2.this.finish();
            }
            else if(from2.equals("Member_page")){
                ChatRoom2.this.finish();
            }
            else if(from2.equals("Notify")) {
                intent.setClass(ChatRoom2.this, Notify.class);
                startActivity(intent);
                ChatRoom2.this.finish();
            }
        }
        return false;
    }

}
