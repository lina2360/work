package com.project14.nccu105.project14_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
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

public class HotArticle3 extends AppCompatActivity {//買賣資料庫差和單詞差和返回差

    private StorageReference mStorageRef;
    private StorageReference riversRef;
    SearchAdapter searchAdapter;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_add){
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt("who",2);
            bundle.putInt("num",1);
            bundle.putString("type","官方板");
            bundle.putString("mypic","null");
            intent.putExtras(bundle);
            intent.setClass(HotArticle3.this, NewArticle.class);
            startActivity(intent);
            HotArticle3.this.finish();
//            Intent intent = new Intent();
//            intent.setClass(HotArticle3.this, CreditCard.class);
//            startActivity(intent);
//            HotArticle3.this.finish();
        }


        return super.onOptionsItemSelected(item);
    }
    ListView lv62;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
//        getSupportActionBar().hide();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_62);
        toolbar.inflateMenu(R.menu.menu_main);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        final Bundle bundle=getIntent().getExtras();
        toolbar.setTitle("資訊快報");
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(HotArticle3.this, Forum.class);
                startActivity(intent1);
                finish();
            }
        });

        FirebaseStorage storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();


        lv62= (ListView)findViewById(R.id.lv62);

        DatabaseReference[] reference_contacts11 = {FirebaseDatabase.getInstance().getReference("official_article")};
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
                        item.put("from","Forum_seller");//選擇searchaapter種類
                        item.put("chatdb","official_article_chat");
                        item.put("title", list1.get(i).getArticle().toString());
                        item.put("content", list1.get(i).getMessageText().toString());
                        item.put("user", list1.get(i).getUsernick().toString());
                        item.put("time", list1.get(i).getMessageTime());

                        items.add(item);
                    }

                    if(have2==true) {

                        if(items.size()!=0){
                            searchAdapter=new SearchAdapter(HotArticle3.this,items,"null");
                           lv62.setAdapter(searchAdapter);


                            lv62.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View v, final int position, long id) {

                                    Intent intent7 = new Intent();
                                    intent7.setClass(HotArticle3.this, ArticlePage.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("who",2);
                                    bundle.putInt("num",1);

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
            intent1.setClass(HotArticle3.this, Forum.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }
}



