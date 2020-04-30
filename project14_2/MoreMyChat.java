package com.project14.nccu105.project14_2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoreMyChat extends AppCompatActivity {
    ImageButton ib51;
    private ListView show;
    final String iam= FirebaseAuth.getInstance()
            .getCurrentUser()
            .getEmail();
    String mynum = "";
    private StorageReference mStorageRef;
    private StorageReference riversRef;
    List<Map<String, Object>> items;
    String mynickname="";
    List<Map<String, Object>> sortitems;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moremybuy);
        getSupportActionBar().hide();
        //設定隱藏狀態

        FirebaseStorage storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar51);
        toolbar.setTitle("您的聊天訊息");
//        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(MoreMyChat.this, Notify.class);
                startActivity(intent1);
                finish();
            }
        });

//        ib40 = (ImageButton) findViewById(R.id.ib40_1);
        show = (ListView) findViewById(R.id.lv51);
        Bundle bundle=getIntent().getExtras();
        mynickname=bundle.getString("me");
        mynum=bundle.getString("mynum");


        final ArrayList<String> list = new ArrayList<String>();
        final ArrayList<String> who = new ArrayList<String>();
        final ArrayList<String> state = new ArrayList<String>();
        final ArrayList<Long> time = new ArrayList<Long>();
        final ArrayList<String> user1 = new ArrayList<String>();
        final ArrayList<String> user2 = new ArrayList<String>();
        DatabaseReference[] reference_contacts4 = {FirebaseDatabase.getInstance().getReference("chatroom")};
        reference_contacts4[0].addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren() ) {


                    String temp=ds.getKey();

                    if(ds.child("user1").getValue().toString().equals(iam)||ds.child("user2").getValue().toString().equals(iam)){
//                        ChatMessage chat = ds.getValue(ChatMessage.class);

                        int tempcount=0;
                        int num=1;

                        if(ds.hasChild("chat")){
                            list.add(temp);
                            user1.add(ds.child("user1").getValue().toString());
                            user2.add(ds.child("user2").getValue().toString());

                            for (DataSnapshot dds:ds.child("chat").getChildren()){
                                if(!dds.child("useracc").getValue().toString().equals(iam)&&tempcount==0){
                                    who.add(dds.child("messageUser").getValue().toString());
                                    tempcount=1;
                                }
                                else if(num==ds.child("chat").getChildrenCount()&&tempcount==0){
//                                    if(ds.child("user1").getValue().toString().equals(iam))
//                                        who.add(ds.child("user2").getValue().toString());
//                                        else
//                                        ds.child("user1").getValue().toString();
                                    who.add("對方還未接受對話");
                                    time.add(Long.parseLong(dds.child("messageTime").getValue().toString()));
                                    state.add("還未回應");
                                }

                                if(num==ds.child("chat").getChildrenCount()&&tempcount==1&&!dds.child("useracc").getValue().toString().equals(iam)){
                                    state.add("有新訊息！");
                                    time.add(Long.parseLong(dds.child("messageTime").getValue().toString()));
                                }
                                else if(num==ds.child("chat").getChildrenCount()&&tempcount==1&&dds.child("useracc").getValue().toString().equals(iam)){
                                    state.add("還未回應");
                                    time.add(Long.parseLong(dds.child("messageTime").getValue().toString()));
                                }


                                num++;
                                Log.d("loop", "onDataChange: ."+"tempcount="+tempcount+" tmep="+temp+" childcount="+ds.getChildrenCount());
                            }


                        }

                    }
                }

                items = new ArrayList<Map<String,Object>>();

//                Log.d("look_notify", "listsize="+list.size()+" statesize="+state.size()+" "+state.get(0));
                for (int i = 0; i < list.size(); i++) {

                    Map<String, Object> item = new HashMap<String, Object>();
//                        Log.d("whyqq", "onDataChange: "+state.get(i));
                    item.put("which", list.get(i));
                    item.put("who",who.get(i));
                    item.put("user1",user1.get(i));
                    item.put("user2",user2.get(i));
                    item.put("text", state.get(i));
                    item.put("time", time.get(i));
                    items.add(item);
                }
                sortitems= new ArrayList<Map<String,Object>>();
                sortitems=SelectionSort(items,items.size());

                SimpleAdapter adapter2 = new SimpleAdapter(MoreMyChat.this, sortitems,
                        R.layout.listview1,new String[]{"text","who"}, new int[]{R.id.listtext,R.id.message_member}
                );

                show.setAdapter(adapter2);
                Utility.setListViewHeightBasedOnChildren(show);
                show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String msg7 = "往個人頁";
                        Intent intent7 = new Intent();
                        Bundle bundle=new Bundle();
                        bundle.putString("from","Notify");
                        bundle.putString("from2","Notify");
                        bundle.putString("user1",sortitems.get((int)id).get("user1").toString());
                        bundle.putString("user2",sortitems.get((int)id).get("user2").toString());
                        bundle.putString("recognize",sortitems.get((int)id).get("which").toString());
                        intent7.putExtras(bundle);
                        intent7.setClass(MoreMyChat.this, ChatRoom2.class);
                        startActivity(intent7);
//                        Toast.makeText(Notify.this, msg6, Toast.LENGTH_SHORT).show();
                        finish();

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void downloadImg(final StorageReference ref, final ImageView img){
        if(ref == null){
            Toast.makeText(MoreMyChat.this, "請先上傳照片", Toast.LENGTH_SHORT).show();
            Log.d("down", "onSuccess: ");
            return;
        }



        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(!isDestroy(MoreMyChat.this)){
                    Glide.with(MoreMyChat.this)

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
            intent1.setClass(MoreMyChat.this, Notify.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }


    public  List<Map<String, Object>> SelectionSort(  List<Map<String, Object>> list,int n){//只用num
        int max;
        long[] A=new long[list.size()];
        for(int i=0;i<list.size();i++)
        {
            A[i]=Long.parseLong(list.get(i).get("time").toString());
        }
        for(int i=0 ;i<n-1;i++){
            max=i;
            for(int j=(i+1);j<n;j++){
                if(A[j]>A[max]){
                    max=j;
                }

            }
            if(max!=i){

                long temp=A[i];
                A[i]=A[max];
                A[max]=temp;
            }
        }
        List<Map<String, Object>> num2 = new ArrayList<Map<String,Object>>();
        for(int i=0;i<list.size();i++)
        {
            for(int k=0;k<list.size();k++){
                if (A[i]==Long.parseLong(list.get(k).get("time").toString())){

                    num2.add(list.get(k));

                }
            }

        }

        return  num2;


    }



}


