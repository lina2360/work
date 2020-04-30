package com.project14.nccu105.project14_2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
//import android.widget.Toast;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;

import java.io.File;

import static android.graphics.Color.rgb;

public class Personal_rating extends AppCompatActivity {

    String mynum = "";
    int data=1;
    String fbdata = "";
    String fbdatac = "";
    String m="";
    FirebaseListAdapter<RatingContainer> adapter;

    final String iam= FirebaseAuth.getInstance()
            .getCurrentUser()
            .getEmail();
    ListView lv411,lv412;

    String user="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_rating);
//        getSupportActionBar().hide();

        final Button btn411=(Button)findViewById(R.id.btn41_1);
        final Button btn412=(Button)findViewById(R.id.btn41_2);

        lv411=(ListView)findViewById(R.id.lv41_1);
        lv412=(ListView)findViewById(R.id.lv41_2);
        Bundle b=getIntent().getExtras();
        if(b.getString("from").equals("Member_page"))
            user=b.getString("acc");
        else
            user=iam;


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar41);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        displayChatMessages();

        final ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.vs41_1);

        Animation slide_in_left = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation slide_out_right = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        viewFlipper.setInAnimation(slide_in_left);
        viewFlipper.setOutAnimation(slide_out_right);
        btn411.setBackgroundColor(rgb(255,238,170));
        btn411.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.setDisplayedChild(0);
                btn411.setBackgroundColor(rgb(255,238,170));
                btn412.setBackgroundColor(rgb(255,255,255));
                data=1;
                displayChatMessages();
            }
        });
        btn412.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewFlipper.setDisplayedChild(1);
                btn411.setBackgroundColor(rgb(255,255,255));
                btn412.setBackgroundColor(rgb(255,238,170));
                mynum="";
                data=2;
                displayChatMessages();



            }
        });


        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(Personal_rating.this, Personal.class);
                startActivity(intent1);
                finish();
            }
        });


    }

    private void displayChatMessages() {



        if(data==1){
            fbdata="buyer_file";
            fbdatac="buyerRating";
            m="mail";
        }
        else{
            fbdata="seller_file";
            fbdatac="sellerrating";
            m="email";
        }
        final Boolean[] test = {false};

        DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference(fbdata)};

        reference_contacts[0].addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren() ) {
                    if(ds.child(m).getValue().equals(user)){
                        if(data==1){
                            mynum = ds.child("memberNum").getValue().toString();
                            test[0] =true;
                        }

                        else{
                            mynum = ds.child("sellernum").getValue().toString();
                            test[0] =true;
//                            Toast.makeText(Personal_rating.this, "mynum:"+mynum+" mail:"+m+" rate:"+fbdata, Toast.LENGTH_LONG).show();
                        }



                    }

                    if(test[0] ==true&&!mynum.equals("")){


                        adapter = new FirebaseListAdapter<RatingContainer>(Personal_rating.this, RatingContainer.class,
                                R.layout.ratinglist, FirebaseDatabase.getInstance().getReference(fbdata).child(mynum).child(fbdatac)) {
                            @Override
                            protected void populateView(View v, final RatingContainer model, int position) {
                                // Get references to the views of message.xml
                                TextView Comment = (TextView)v.findViewById(R.id.article_title59);
                                TextView Order = (TextView)v.findViewById(R.id.classify59);
                                final TextView user = (TextView)v.findViewById(R.id.username59);
                                TextView Time = (TextView)v.findViewById(R.id.time59);
                                RatingBar rating = (RatingBar)v.findViewById(R.id.ratingBar559);
                                TextView orderName = (TextView)v.findViewById(R.id.ordername59);
                                rating.setRating(model.getScore());


//rating.setRating(model.getScore());
                                // Set their text
                                orderName.setText(model.getProduct());
                                Comment.setText(model.getComment());
                                Order.setText(model.getKind());
                                user.setText(model.getUsernick());
                                Time.setText(DateFormat.format("yyyy-MM-dd(HH:mm)",
                                        model.getTime()));
                                if(model.getWho().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail()))
                                {

                                }else {
                                    user.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent();
                                            intent.setClass(Personal_rating.this, Member_page.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putString("from", "Personal_rating");
                                            bundle.putString("user", model.getUsernick());
                                            bundle.putString("useracc", model.getWho());
                                            intent.putExtras(bundle);
                                            startActivity(intent);


                                        }
                                    });
                                }



                            }

                        };

                        lv411.setAdapter(adapter);
                        if(fbdata.equals("seller_file")){
                            lv412.setAdapter(adapter);
                        }


                    }


                }
                if(mynum.equals("")){
                    Toast.makeText(Personal_rating.this, "還沒有賣家評價喔！", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent1 = new Intent();
            intent1.setClass(Personal_rating.this, Personal.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }
}
