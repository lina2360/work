package com.project14.nccu105.project14_2;

import android.annotation.TargetApi;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
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


//10
public class Personal_setting_myinfo extends AppCompatActivity {


    Button btn101, btn103, btn104, btn105, btn106;
    ListView lv101;
    ImageButton ib101;
    ImageView iv101;
    private StorageReference mStorageRef;

    private StorageReference riversRef;


    String[] mynum = new String[1];
    String[] mypic = new String[1];
    final String iam= FirebaseAuth.getInstance()
            .getCurrentUser()
            .getEmail();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_setting_myinfo);

//        getSupportActionBar().hide();
        //設定隱藏狀態

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar10);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(Personal_setting_myinfo.this, Personal_setting.class);
                startActivity(intent1);
                finish();
            }
        });

        FirebaseStorage storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();

        final boolean[] m = {false};


            DatabaseReference[] reference_contacts2 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
            reference_contacts2[0].addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

//                    Log.d("TAG", "ds.child(mail)1: "+ds.child("mail").getValue());
                        if(ds.child("mail").getValue().equals(iam)){
                            mynum[0] = ds.child("memberNum").getValue().toString();
                            mypic[0] = ds.child("picture").getValue().toString();
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


                        }


                    }


                }




                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


            });



//        }







        btn101 = (Button) findViewById(R.id.btn10_1);
//        btn102 = (Button) findViewById(R.id.btn10_2);
        btn103 = (Button) findViewById(R.id.btn10_3);
        btn104 = (Button) findViewById(R.id.btn10_4);
        btn105 = (Button) findViewById(R.id.btn10_5);
        btn106 = (Button) findViewById(R.id.btn10_6);
        lv101 = (ListView) findViewById(R.id.lv10_1);
        iv101= (ImageView)findViewById(R.id.iv10_1);
//        ib101 = (ImageButton) findViewById(R.id.ib10_1);

        View.OnClickListener OCL = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v instanceof Button) {
                    int id = ((Button) v).getId();
                    switch (id) {
                        case R.id.btn10_1:
                            String msg1 = "往論壇頁";
                            Intent intent1 = new Intent();
                            intent1.setClass(Personal_setting_myinfo.this, Forum.class);
                            startActivity(intent1);
//                            Toast.makeText(Personal_setting_myinfo.this, msg1, Toast.LENGTH_SHORT).show();
                            Personal_setting_myinfo.this.finish();
                            break;
//                        case R.id.btn10_2:
//                            String msg2 = "往逛逛頁";
//                            Intent intent2 = new Intent();
//                            intent2.setClass(Personal_setting_myinfo.this, Buyer.class);
//                            startActivity(intent2);
////                            Toast.makeText(Personal_setting_myinfo.this, msg2, Toast.LENGTH_SHORT).show();
//                            Personal_setting_myinfo.this.finish();
//                            break;
                        case R.id.btn10_3:
                            String msg3 = "往接單頁";
                            Intent intent3 = new Intent();
                            intent3.setClass(Personal_setting_myinfo.this, Seller.class);
                            startActivity(intent3);
//                            Toast.makeText(Personal_setting_myinfo.this, msg3, Toast.LENGTH_SHORT).show();
                            Personal_setting_myinfo.this.finish();

                            break;
                        case R.id.btn10_4:
                            String msg4 = "往地圖頁";
                            Intent intent4 = new Intent();
                            intent4.setClass(Personal_setting_myinfo.this, Maps.class);
                            startActivity(intent4);
//                            Toast.makeText(Personal_setting_myinfo.this, msg4, Toast.LENGTH_SHORT).show();
                            Personal_setting_myinfo.this.finish();
                            break;
                        case R.id.btn10_5:
                            String msg5 = "往通知頁";
                            Intent intent5 = new Intent();
                            intent5.setClass(Personal_setting_myinfo.this, Notify.class);
                            startActivity(intent5);
//                            Toast.makeText(Personal_setting_myinfo.this, msg5, Toast.LENGTH_SHORT).show();
                            Personal_setting_myinfo.this.finish();
                            break;
                        case R.id.btn10_6:
                            String msg6 = "往個人頁";
                            Intent intent6 = new Intent();
                            intent6.setClass(Personal_setting_myinfo.this, Personal.class);
                            startActivity(intent6);
//                            Toast.makeText(Personal_setting_myinfo.this, msg6, Toast.LENGTH_SHORT).show();
                            Personal_setting_myinfo.this.finish();
                            break;

                    }

                }

//                if (v instanceof ImageButton) {
//                    int id2 = ((ImageButton) v).getId();
//                    switch (id2) {
//                        case R.id.ib10_1:
//                            Intent intent7 = new Intent();
//                            intent7.setClass(Personal_setting_myinfo.this, Personal_setting.class);
//                            startActivity(intent7);
//                            Personal_setting_myinfo.this.finish();
//                            break;
//                    }
//                }
                if (v instanceof ImageView) {
                    int id3 = ((ImageView) v).getId();
                    switch (id3) {
                        case R.id.iv10_1:
                            Intent intent7 = new Intent();

                            intent7.setClass(Personal_setting_myinfo.this, PictureChange.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("mynum", mynum[0]);
                            bundle.putString("mypic",mypic[0]);
                            bundle.putString("from","Personal_setting_myinfo");
                            intent7.putExtras(bundle);
                            startActivity(intent7);
                            Personal_setting_myinfo.this.finish();



                            break;
                    }


                }
            }
        };

        btn101.setOnClickListener(OCL);
        btn103.setOnClickListener(OCL);
        btn104.setOnClickListener(OCL);
        btn105.setOnClickListener(OCL);
        btn106.setOnClickListener(OCL);
//        ib101.setOnClickListener(OCL);
        iv101.setOnClickListener(OCL);


        ListView listView1 = (ListView) findViewById(R.id.lv10_1);
        String[] value1 = new String[]{"我的資訊","使用者匿名變更", "密碼變更", "手機號碼變更","個人簡介變更"};
        ListAdapter adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, value1);

        listView1.setAdapter(adapter1);


        lv101.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {

                switch (index) {
                    case 0:

                        Intent intent7 = new Intent();
                        intent7.setClass(Personal_setting_myinfo.this, Personal_setting_myinfo_info.class);
                        startActivity(intent7);

                        Personal_setting_myinfo.this.finish();
                        break;
                    case 1:

                        Intent intent8 = new Intent();
                        intent8.setClass(Personal_setting_myinfo.this, Personal_setting_myinfo_setaccount.class);
                        startActivity(intent8);

                        Personal_setting_myinfo.this.finish();
                        break;
                    case 2:

                        Intent intent9 = new Intent();
                        intent9.setClass(Personal_setting_myinfo.this, Personal_setting_myinfo_setpassword.class);
                        startActivity(intent9);

                        Personal_setting_myinfo.this.finish();
                        break;
                    case 3:

                        Intent intent10 = new Intent();
                        intent10.setClass(Personal_setting_myinfo.this, Personal_setting_myinfo_setphone.class);
                        startActivity(intent10);

                        Personal_setting_myinfo.this.finish();
                        break;
//                    case 4:
//
//                        Intent intent11 = new Intent();
//                        intent11.setClass(Personal_setting_myinfo.this, Personal_setting_myinfo_setemail.class);
//                        startActivity(intent11);
//
//                        Personal_setting_myinfo.this.finish();
//                        break;
                    case 4:

                        Intent intent12 = new Intent();
                        intent12.setClass(Personal_setting_myinfo.this, Personal_setting_myinfo_setIntr.class);
                        startActivity(intent12);

                        Personal_setting_myinfo.this.finish();
                        break;

//                    case 5:
//
//                        Intent intent13 = new Intent();
//                        intent13.setClass(Personal_setting_myinfo.this, Personal_setting_myinfo_bindaccount.class);
//                        startActivity(intent13);
//
//                        Personal_setting_myinfo.this.finish();
//                        break;

                }


            }
        });
    }

   private void downloadImg(final StorageReference ref){
        if(ref == null){
            Toast.makeText(Personal_setting_myinfo.this, "請先上傳照片", Toast.LENGTH_SHORT).show();
            Log.d("down", "onSuccess: ");
            return;
        }



        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(!isDestroy(Personal_setting_myinfo.this)){
                    Glide.with(Personal_setting_myinfo.this)

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
            intent1.setClass(Personal_setting_myinfo.this, Personal_setting.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }



}
