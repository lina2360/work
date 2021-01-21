package com.project14.nccu105.project14_2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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

import java.util.Date;

public class NewArticle extends AppCompatActivity {
    Button btn361;
    ImageView iv361;
    ImageButton ib361;
    EditText et36;
    Bundle bundle;

    private StorageReference mStorageRef;
    private StorageReference riversRef;
    String mynum ;
    String mypic = "null";
    int temp=0;
    int num=0;
    final String iam= FirebaseAuth.getInstance()
            .getCurrentUser()
            .getEmail();
    String nickname="";
    String wdb="";
    String db2="";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newarticle);
//        getSupportActionBar().hide();
        //設定隱藏狀態
        bundle = getIntent().getExtras();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar36);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent7 = new Intent();
                Bundle bundle = getIntent().getExtras();
                if(bundle.getInt("who")==0){
                    intent7.setClass( NewArticle.this, Forum_buyerforum.class);
                }
                else if(bundle.getInt("who")==2){
                    intent7.setClass( NewArticle.this, HotArticle3.class);
                }else{
                    intent7.setClass( NewArticle.this, Forum_sellerforum.class);
                }

                startActivity(intent7);

                NewArticle.this.finish();
            }
        });

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


        temp=bundle.getInt("who");
        num=bundle.getInt("num");
//        Log.d("NA", "onCrete: "+bundle.getInt("num"));


        mypic=bundle.getString("mypic");

        FirebaseStorage storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();

        if(mypic.contains("null")){

        }
        else{
            if(bundle.getInt("who")==0)
                riversRef = mStorageRef.child("buyer_article").child(mypic);
            else if(bundle.getInt("who")==2)
                riversRef = mStorageRef.child("official_article").child(mypic);
            else
                riversRef = mStorageRef.child("seller_article").child(mypic);

            downloadImg(riversRef);
        }


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//        ib361 = (ImageButton) findViewById(R.id.ib36_1);
        btn361 = (Button) findViewById(R.id.btn36_1);
        et36 = (EditText) findViewById(R.id.et36_2);
        String type=bundle.getString("type");
        et36.setText("  "+type);


        iv361=( ImageView)findViewById(R.id.iv36_1);


        View.OnClickListener OCL = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (v instanceof Button) {
                    int id = ((Button) v).getId();
                    switch (id) {
                        case R.id.btn36_1:
                            final EditText  title= (EditText)findViewById(R.id.et36_1);
                            EditText classify = (EditText)findViewById(R.id.et36_2);
                            EditText content = (EditText)findViewById(R.id.et36_3);
                            // Read the input field and push a new instance
                            // of ChatMessage to the Firebase database

                            bundle = getIntent().getExtras();
                            // Clear the input
//                            Bundle bundle = getIntent().getExtras();

                            Log.d("NA", "onClick: "+bundle.getInt("num"));
                            long messageTime = new Date().getTime();
                            final String time=Long.toString(messageTime);


                            if(temp==0){


                                if(num==1){
                                    wdb="buyer_article";
                                    db2="articletitle";
                                    FirebaseDatabase.getInstance()
                                            .getReference("articletitle")
                                            .child(time)
                                            .setValue(new Article(
                                                    title.getText().toString(),
                                                    classify.getText().toString(),
                                                    content.getText().toString(),
                                                    FirebaseAuth.getInstance()
                                                            .getCurrentUser()
                                                            .getEmail()
                                                    ,mypic
                                                    ,nickname)
                                            );
                                }
                                else if(num==2){
                                    wdb="buyer_article";
                                    db2="articletitle2";
                                    FirebaseDatabase.getInstance()
                                            .getReference("articletitle2")
                                            .child(time)
                                            .setValue(new Article(
                                                    title.getText().toString(),
                                                    classify.getText().toString(),
                                                    content.getText().toString(),
                                                    FirebaseAuth.getInstance()
                                                            .getCurrentUser()
                                                            .getEmail()
                                                    ,mypic,
                                                    nickname)
                                            );

                                }
                                else if(num==3){
                                    wdb="buyer_article";
                                    db2="articletitle3";
                                    FirebaseDatabase.getInstance()
                                            .getReference("articletitle3")
                                            .child(time)
                                            .setValue(new Article(
                                                    title.getText().toString(),
                                                    classify.getText().toString(),
                                                    content.getText().toString(),
                                                    FirebaseAuth.getInstance()
                                                            .getCurrentUser()
                                                            .getEmail()
                                                    ,mypic,
                                                    nickname)
                                            );
                                }
                                else if(num==4){
                                    wdb="buyer_article";
                                    db2="articletitle4";
                                    FirebaseDatabase.getInstance()
                                            .getReference("articletitle4")
                                            .child(time)
                                            .setValue(new Article(
                                                    title.getText().toString(),
                                                    classify.getText().toString(),
                                                    content.getText().toString(),
                                                    FirebaseAuth.getInstance()
                                                            .getCurrentUser()
                                                            .getEmail()
                                                    ,mypic,
                                                    nickname)
                                            );
                                }
                                else if(num==5){
                                    wdb="buyer_article";
                                    db2="articletitle5";
                                    FirebaseDatabase.getInstance()
                                            .getReference("articletitle5")
                                            .child(time)
                                            .setValue(new Article(
                                                    title.getText().toString(),
                                                    classify.getText().toString(),
                                                    content.getText().toString(),
                                                    FirebaseAuth.getInstance()
                                                            .getCurrentUser()
                                                            .getEmail()
                                                    ,mypic,
                                                    nickname)
                                            );
                                }
                                //存自己發過的文章資料
                                DatabaseReference[] reference_contacts2 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
                                reference_contacts2[0].addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                            if(ds.child("mail").getValue().equals(FirebaseAuth.getInstance()
                                                    .getCurrentUser()
                                                    .getEmail())){
                                                FirebaseDatabase.getInstance()
                                                        .getReference("buyer_file")
                                                        .child(ds.child("memberNum").getValue().toString())
                                                        .child("focusNum")
                                                        .child(time)
                                                        .setValue(new MyArticle(
                                                                time,
                                                                title.getText().toString(),
                                                                wdb,
                                                                mypic,
                                                                db2
                                                        ))
                                                        ;

                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }


                                });

                                Intent intent1 = new Intent();
                                intent1.setClass( NewArticle.this, Forum_buyerforum.class);
                                startActivity(intent1);
                                NewArticle.this.finish();
                                break;





                            }
                            else if(temp==2){
                                wdb="official_article";
                                db2="official_article";
                                FirebaseDatabase.getInstance()
                                        .getReference("official_article")
                                        .child(time)
                                        .setValue(new Article(
                                                title.getText().toString(),
                                                classify.getText().toString(),
                                                content.getText().toString(),
                                                FirebaseAuth.getInstance()
                                                        .getCurrentUser()
                                                        .getEmail()
                                                ,mypic
                                                ,nickname)
                                        );

                                Intent intent1 = new Intent();
                                intent1.setClass( NewArticle.this, HotArticle3.class);
                                startActivity(intent1);
                                NewArticle.this.finish();

                            }
                            else{

                                if(num==1){
                                    wdb="seller_article";
                                    db2="seller_article";
                                    FirebaseDatabase.getInstance()
                                            .getReference("seller_article")
                                            .child(time)
                                            .setValue(new Article(title.getText().toString(),
                                                    classify.getText().toString(),
                                                    content.getText().toString(),
                                                    FirebaseAuth.getInstance()
                                                            .getCurrentUser()
                                                            .getEmail()
                                                    ,mypic,
                                                    nickname)
                                            );
                                    Log.d("num", "1");
                                }
                                else if(num==2){
                                    wdb="seller_article";
                                    db2="seller_article2";
                                    FirebaseDatabase.getInstance()
                                            .getReference("seller_article2")
                                            .child(time)
                                            .setValue(new Article(title.getText().toString(),
                                                    classify.getText().toString(),
                                                    content.getText().toString(),
                                                    FirebaseAuth.getInstance()
                                                            .getCurrentUser()
                                                            .getEmail()
                                                    ,mypic,
                                                    nickname)
                                            );
                                    Log.d("num", "2");
                                }
                                else if(num==3){
                                    wdb="seller_article";
                                    db2="seller_article3";
                                    FirebaseDatabase.getInstance()
                                            .getReference("seller_article3")
                                            .child(time)
                                            .setValue(new Article(title.getText().toString(),
                                                    classify.getText().toString(),
                                                    content.getText().toString(),
                                                    FirebaseAuth.getInstance()
                                                            .getCurrentUser()
                                                            .getEmail()
                                                    ,mypic,
                                                    nickname)
                                            );
                                    Log.d("num", "3");
                                }
                                else if(num==4){
                                    wdb="seller_article";
                                    db2="seller_article4";
                                    FirebaseDatabase.getInstance()
                                            .getReference("seller_article4")
                                            .child(time)
                                            .setValue(new Article(title.getText().toString(),
                                                    classify.getText().toString(),
                                                    content.getText().toString(),
                                                    FirebaseAuth.getInstance()
                                                            .getCurrentUser()
                                                            .getEmail()
                                                    ,mypic,
                                                    nickname)
                                            );
                                    Log.d("num", "4");
                                }
                                else if(num==5){
                                    wdb="seller_article";
                                    db2="seller_article5";
                                    FirebaseDatabase.getInstance()
                                            .getReference("seller_article5")
                                            .child(time)
                                            .setValue(new Article(title.getText().toString(),
                                                    classify.getText().toString(),
                                                    content.getText().toString(),
                                                    FirebaseAuth.getInstance()
                                                            .getCurrentUser()
                                                            .getEmail()
                                                    ,mypic,
                                                    nickname)
                                            );
                                    Log.d("num", "5");
                                }
                                //存自己發過的文章資料
                                DatabaseReference[] reference_contacts4 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
                                reference_contacts4[0].addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                            if(ds.child("mail").getValue().equals(FirebaseAuth.getInstance()
                                                    .getCurrentUser()
                                                    .getEmail())){
                                                FirebaseDatabase.getInstance()
                                                        .getReference("buyer_file")
                                                        .child(ds.child("memberNum").getValue().toString())
                                                        .child("focusNum")
                                                        .child(time)
                                                        .setValue(new MyArticle(
                                                                time,
                                                                title.getText().toString(),
                                                                wdb,
                                                                mypic,
                                                                db2

                                                                )
                                                        )
                                                ;

                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }


                                });

                                Intent intent1 = new Intent();
                                intent1.setClass( NewArticle.this, Forum_sellerforum.class);
                                startActivity(intent1);
                                NewArticle.this.finish();
                                break;
                            }



                    }
                }

                if (v instanceof ImageView) {
                    int id5 = ((ImageView) v).getId();
                    if (id5 == R.id.iv36_1) {
                        Intent intent7 = new Intent();

                        intent7.setClass(NewArticle.this, PictureChange.class);
                        Bundle bundle1=new Bundle();
                            bundle1.putString("mynum", mynum);
                            bundle1.putString("mypic",mypic);
                            bundle1.putString("from","NewArticle");
                            bundle1.putInt("who",bundle.getInt("who"));
                            bundle1.putInt("num",bundle.getInt("num"));

                            bundle1.putString("type",et36.getText().toString());
                        intent7.putExtras(bundle1);
                        startActivity(intent7);
//                        NewArticle.this.finish();

                    }

                }

            }


        };
        btn361.setOnClickListener(OCL);
//        ib361.setOnClickListener(OCL);
        iv361.setOnClickListener(OCL);
    }

    private void downloadImg(final StorageReference ref){
        if(ref == null){
            Toast.makeText(NewArticle.this, "請先上傳照片", Toast.LENGTH_SHORT).show();
            Log.d("down", "onSuccess: ");
            return;
        }



        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(!isDestroy(NewArticle.this)){
                    Glide.with(NewArticle.this)

                            .load(ref)
                            .into(iv361);

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
            Intent intent7 = new Intent();
            Bundle bundle = getIntent().getExtras();
            if(bundle.getInt("who")==0){
                intent7.setClass( NewArticle.this, Forum_buyerforum.class);
            }
            else if(bundle.getInt("who")==2){
                intent7.setClass( NewArticle.this, HotArticle3.class);
            }
            else{
                intent7.setClass( NewArticle.this, Forum_sellerforum.class);
            }

            startActivity(intent7);

            NewArticle.this.finish();
        }
        return false;
    }





}

