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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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

import org.w3c.dom.Text;

public class Personal_setting_myinfo_info extends AppCompatActivity {
    Button btn141,btn142,btn143,btn144,btn145,btn146;
    ImageButton ib141;
    ImageView iv101;
    String[] mynum = new String[1];
    String[] mypic = new String[1];
    private StorageReference mStorageRef;
    private StorageReference riversRef;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_setting_myinfo_info);
//        getSupportActionBar().hide();
        //設定隱藏狀態
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();

        btn141 = (Button) findViewById(R.id.btn14_1);
//        btn142 = (Button) findViewById(R.id.btn14_2);
        btn143 = (Button) findViewById(R.id.btn14_3);
        btn144 = (Button) findViewById(R.id.btn14_4);
        btn145 = (Button) findViewById(R.id.btn14_5);
        btn146 = (Button) findViewById(R.id.btn14_6);

        iv101= (ImageView)findViewById(R.id.iv10_1);

        final TextView tv143=(TextView)findViewById(R.id.tv14_3);
        final TextView tv1411=(TextView)findViewById(R.id.tv14_11);
        final TextView tv145=(TextView)findViewById(R.id.tv14_5);
        final TextView tv147=(TextView)findViewById(R.id.tv14_7);
        final TextView tv149=(TextView)findViewById(R.id.tv14_9);
//        ib141 = (ImageButton) findViewById(R.id.ib14_1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar14);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(Personal_setting_myinfo_info.this, Personal_setting.class);
                startActivity(intent1);
                finish();
            }
        });

        final boolean[] m = {false};
        final String iam= FirebaseAuth.getInstance()
                .getCurrentUser()
                .getEmail();

        DatabaseReference[] reference_contacts2 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
        reference_contacts2[0].addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

//                    Log.d("TAG", "ds.child(mail)1: "+ds.child("mail").getValue());
                    if(ds.child("mail").getValue().equals(FirebaseAuth.getInstance()
                            .getCurrentUser()
                            .getEmail())){

                        mynum[0] = ds.child("memberNum").getValue().toString();
                        mypic[0] = ds.child("picture").getValue().toString();
                        m[0] =true;
                        tv143.setText(FirebaseAuth.getInstance()
                                .getCurrentUser()
                                .getEmail());
                        tv1411.setText(ds.child("nickname").getValue().toString());
                        tv145.setText(ds.child("birthDay").getValue().toString());
//                        FirebaseDatabase.getInstance()
//                                .getReference("buyer_file")
//                                .child(mynum[0])
//                                .child("personalArticle")
//                                .setValue(et601.getText().toString());
                        tv147.setText(ds.child("personalArticle").getValue().toString());
                       tv149.setText(ds.child("phoneNum").getValue().toString());
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


                    }



                }


            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });


        View.OnClickListener OCL = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v instanceof Button) {
                    int id = ((Button) v).getId();
                    switch (id) {
                        case R.id.btn14_1:
                            String msg1 = "往論壇頁";
                            Intent intent1 = new Intent();
                            intent1.setClass(Personal_setting_myinfo_info.this, Forum.class);
                            startActivity(intent1);
//                            Toast.makeText(Personal_setting_myinfo_info.this, msg1, Toast.LENGTH_SHORT).show();
                            Personal_setting_myinfo_info.this.finish();
                            break;
//                        case R.id.btn14_2:
//                            String msg2 = "往逛逛頁";
//                            Intent intent2 = new Intent();
//                            intent2.setClass(Personal_setting_myinfo_info.this, Buyer.class);
//                            startActivity(intent2);
////                            Toast.makeText(Personal_setting_myinfo_info.this, msg2, Toast.LENGTH_SHORT).show();
//                            Personal_setting_myinfo_info.this.finish();
//                            break;
                        case R.id.btn14_3:
                            String msg3 = "往接單頁";
                            Intent intent3 = new Intent();
                            intent3.setClass(Personal_setting_myinfo_info.this, Seller.class);
                            startActivity(intent3);
//                            Toast.makeText(Personal_setting_myinfo_info.this, msg3, Toast.LENGTH_SHORT).show();
                            Personal_setting_myinfo_info.this.finish();
                            break;
                        case R.id.btn14_4:
                            String msg4 = "往地圖頁";
                            Intent intent4 = new Intent();
                            intent4.setClass(Personal_setting_myinfo_info.this, Maps.class);
                            startActivity(intent4);
//                            Toast.makeText(Personal_setting_myinfo_info.this, msg4, Toast.LENGTH_SHORT).show();
                            Personal_setting_myinfo_info.this.finish();
                            break;
                        case R.id.btn14_5:
                            String msg5 = "往通知頁";
                            Intent intent5 = new Intent();
                        intent5.setClass(Personal_setting_myinfo_info.this,Notify.class);
                            startActivity(intent5);
//                            Toast.makeText(Personal_setting_myinfo_info.this, msg5, Toast.LENGTH_SHORT).show();
                            Personal_setting_myinfo_info.this.finish();
                            break;

                        case R.id.btn14_6:
                            String msg6 = "往個人頁";
                            Intent intent6 = new Intent();
                            intent6.setClass(Personal_setting_myinfo_info.this, Personal.class);
                            startActivity(intent6);
//                            Toast.makeText(Personal_setting_myinfo_info.this, msg6, Toast.LENGTH_SHORT).show();
                            Personal_setting_myinfo_info.this.finish();
                            break;
                    }

                }
                if (v instanceof ImageView) {
                    int id3 = ((ImageView) v).getId();
                    switch (id3) {
                        case R.id.iv10_1:
                            Intent intent7 = new Intent();

                            intent7.setClass(Personal_setting_myinfo_info.this, PictureChange.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("mynum", mynum[0]);
                            bundle.putString("mypic",mypic[0]);
                            bundle.putString("from","Personal_setting_myinfo_info");
                            intent7.putExtras(bundle);
                            startActivity(intent7);
                            Personal_setting_myinfo_info.this.finish();



                            break;
                    }


                }

//                if (v instanceof ImageButton) {
//                    int id2 = ((ImageButton) v).getId();
//                    switch (id2) {
//                        case R.id.ib14_1:
//                            Intent intent7 = new Intent();
//                            intent7.setClass(Personal_setting_myinfo_info.this, Personal_setting_myinfo.class);
//                            startActivity(intent7);
//                            Personal_setting_myinfo_info.this.finish();
//                            break;
//                    }
//                }
            }

            };
        btn141.setOnClickListener(OCL);
//        btn142.setOnClickListener(OCL);
        btn143.setOnClickListener(OCL);
        btn144.setOnClickListener(OCL);
        btn145.setOnClickListener(OCL);
        btn146.setOnClickListener(OCL);
        iv101.setOnClickListener(OCL);
//        ib141.setOnClickListener(OCL);

        }

    private void downloadImg(final StorageReference ref){
        if(ref == null){
            Toast.makeText(Personal_setting_myinfo_info.this, "請先上傳照片", Toast.LENGTH_SHORT).show();
            Log.d("down", "onSuccess: ");
            return;
        }



        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(!isDestroy(Personal_setting_myinfo_info.this)){
                    Glide.with(Personal_setting_myinfo_info.this)

                            .load(ref)
                            .into(iv101);

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
            intent1.setClass(Personal_setting_myinfo_info.this, Personal_setting.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }
    }
