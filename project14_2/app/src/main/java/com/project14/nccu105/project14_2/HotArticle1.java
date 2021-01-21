package com.project14.nccu105.project14_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotArticle1  extends AppCompatActivity {//買賣資料庫差和單詞差和返回差
    ListView lv681,lv682;
    private StorageReference mStorageRef;
    private StorageReference riversRef;
    SearchAdapter searchAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result2);
        getSupportActionBar().hide();

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_68);
//        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        final Bundle bundle=getIntent().getExtras();
        toolbar.setTitle(bundle.getString("單詞"));

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(HotArticle1.this, Forum.class);
                startActivity(intent1);
                finish();
            }
        });



        lv681= (ListView)findViewById(R.id.lv68_1);

        DatabaseReference[] reference_contacts11 = {FirebaseDatabase.getInstance().getReference("articletitle4")};//買家推薦
        reference_contacts11[0].addValueEventListener(new ValueEventListener() {
            boolean has=false;
            boolean have2=false;
            int j;
            ArrayList<Article>  list1 = new ArrayList<Article>();
            ArrayList<Article>  list2 = new ArrayList<Article>();
            List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Article article = ds.getValue(Article.class);
                    list1.add(article);
                    has=true;
                }
                if(has==true){

                    for(int i=list1.size()-1;i>=0;i--){
                        list2.add(list1.get(i));
                        have2=true;
                        Map<String, Object> item = new HashMap<String, Object>();
                        item.put("from","Forum_seller");
                        item.put("chatdb","buyer_article_chat");
                        item.put("title", list1.get(i).getArticle().toString());
                        item.put("content", list1.get(i).getMessageText().toString());
                        item.put("user", list1.get(i).getUsernick().toString());
                        item.put("time", list1.get(i).getMessageTime());

                        items.add(item);
                    }

                    if(have2==true) {

                        if(items.size()!=0){
                            searchAdapter=new SearchAdapter(HotArticle1.this,items,"null");
                            lv681.setAdapter(searchAdapter);
                            Utility.setListViewHeightBasedOnChildren(lv681);

                            lv681.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View v, final int position, long id) {
                                    TextView user = (TextView)v.findViewById(R.id.username);

                                    if(list2.get(position).getMessageUser().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail()))
                                    {}
                                    else {
                                        user.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent();
                                                intent.setClass(HotArticle1.this, Member_page.class);
                                                Bundle bundle = new Bundle();
                                                bundle.putString("from", "HotArticle1");
                                                bundle.putString("user", list2.get(position).getUsernick());
                                                bundle.putString("useracc", list2.get(position).getMessageUser());
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                                HotArticle1.this.finish();

                                            }
                                        });
                                    }

                                    Intent intent7 = new Intent();
                                    intent7.setClass(HotArticle1.this, ArticlePage.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("who",0);
                                    bundle.putInt("num",4);

                                    bundle.putString("title", list2.get(position).getArticle());
                                    bundle.putString("classify",list2.get(position).getClassify());

                                    bundle.putLong("time",list2.get(position).getMessageTime());
                                    bundle.putString("name",list2.get(position).getMessageUser());
                                    bundle.putString("usernick",list2.get(position).getUsernick());

                                    bundle.putString("content",list2.get(position).getMessageText());
                                    intent7.putExtras(bundle);
                                    startActivity(intent7);

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


        lv682= (ListView)findViewById(R.id.lv68_2);


        DatabaseReference[] reference_contacts12 = {FirebaseDatabase.getInstance().getReference("seller_article4")};
        reference_contacts12[0].addValueEventListener(new ValueEventListener() {
            boolean has=false;
            boolean have2=false;
            int j;
            ArrayList<Article>  list1 = new ArrayList<Article>();
            ArrayList<Article>  list2 = new ArrayList<Article>();
            List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Article article = ds.getValue(Article.class);
                    list1.add(article);
                    has=true;
                }
                if(has==true){

                    for(int i=list1.size()-1;i>=0;i--){
                        list2.add(list1.get(i));
                        have2=true;
                        Map<String, Object> item = new HashMap<String, Object>();
                        item.put("from","Forum_seller");
                        item.put("chatdb","seller_article_chat");
                        item.put("title", list1.get(i).getArticle().toString());
                        item.put("content", list1.get(i).getMessageText().toString());
                        item.put("user", list1.get(i).getUsernick().toString());
                        item.put("time", list1.get(i).getMessageTime());

                        items.add(item);
                    }

                    if(have2==true) {

                        if(items.size()!=0){
                            searchAdapter=new SearchAdapter(HotArticle1.this,items,"null");
                            lv682.setAdapter(searchAdapter);
                            Utility.setListViewHeightBasedOnChildren(lv682);

                            lv682.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View v, final int position, long id) {

                                    TextView user = (TextView)v.findViewById(R.id.username);

                                    if(list2.get(position).getMessageUser().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail()))
                                    {}
                                    else {
                                        user.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent();
                                                intent.setClass(HotArticle1.this, Member_page.class);
                                                Bundle bundle = new Bundle();
                                                bundle.putString("from", "HotArticle1");
                                                bundle.putString("user", list2.get(position).getUsernick());
                                                bundle.putString("useracc", list2.get(position).getMessageUser());
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                                HotArticle1.this.finish();

                                            }
                                        });
                                    }

                                    Intent intent7 = new Intent();
                                    intent7.setClass(HotArticle1.this, ArticlePage.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("who",1);
                                    bundle.putInt("num",4);

                                    bundle.putString("title", list2.get(position).getArticle());
                                    bundle.putString("classify",list2.get(position).getClassify());

                                    bundle.putLong("time",list2.get(position).getMessageTime());
                                    bundle.putString("name",list2.get(position).getMessageUser());
                                    bundle.putString("usernick",list2.get(position).getUsernick());

                                    bundle.putString("content",list2.get(position).getMessageText());
                                    intent7.putExtras(bundle);
                                    startActivity(intent7);

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

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent1 = new Intent();
            intent1.setClass(HotArticle1.this, Seller.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }
}


