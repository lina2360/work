package com.project14.nccu105.project14_2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.graphics.Color.rgb;

public class Forum_buyerforum extends AppCompatActivity {
    Button btn371,btn372,btn373,btn374,btn375,btn376;
    ImageButton ib371;
    ListView listOfMessages,lv2,lv3,lv4,lv5;
    Integer data=1;
    SearchAdapter searchAdapter;
//    private MyAdapter mAdapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum_buyerforum);
//        getSupportActionBar().hide();
        //設定隱藏狀態

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar37);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(Forum_buyerforum.this, Forum.class);
                startActivity(intent1);
                finish();
            }
        });

        listOfMessages = (ListView)findViewById(R.id.lv37_1);
        lv2 = (ListView) findViewById(R.id.lv37_2);
        lv3 = (ListView) findViewById(R.id.lv37_3);
        lv4 = (ListView) findViewById(R.id.lv37_4);
        lv5 = (ListView) findViewById(R.id.lv37_5);
        btn371 = (Button) findViewById(R.id.btn37_1);
        btn372 = (Button) findViewById(R.id.btn37_2);
        btn373 = (Button) findViewById(R.id.btn37_3);
        btn374 = (Button) findViewById(R.id.btn37_4);
        btn375 = (Button) findViewById(R.id.btn37_5);
        btn376 = (Button) findViewById(R.id.btn37_6);
//        ib371 = (ImageButton) findViewById(R.id.ib37_1);

        btn371.setBackgroundColor(rgb(255,238,170));

        displayChatMessages();


        final ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.vs37_1);

        Animation slide_in_left = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation slide_out_right = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        viewFlipper.setInAnimation(slide_in_left);
        viewFlipper.setOutAnimation(slide_out_right);




        View.OnClickListener OCL = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v instanceof Button) {
                    int id = ((Button) v).getId();

                    switch (id) {
                        case R.id.btn37_1:
                            viewFlipper.setDisplayedChild(0);
                            data=1;
                            btn371.setBackgroundColor(rgb(255,238,170));
                            btn372.setBackgroundColor(rgb(255,255,255));
                            btn373.setBackgroundColor(rgb(255,255,255));
                            btn374.setBackgroundColor(rgb(255,255,255));
                            btn375.setBackgroundColor(rgb(255,255,255));
                            displayChatMessages();
                            break;
                        case R.id.btn37_2:
                            viewFlipper.setDisplayedChild(1);
                            data=2;
                            btn371.setBackgroundColor(rgb(255,255,255));
                            btn372.setBackgroundColor(rgb(255,238,170));
                            btn373.setBackgroundColor(rgb(255,255,255));
                            btn374.setBackgroundColor(rgb(255,255,255));
                            btn375.setBackgroundColor(rgb(255,255,255));
                            displayChatMessages();
                            break;
                        case R.id.btn37_3:
                            viewFlipper.setDisplayedChild(2);
                            btn371.setBackgroundColor(rgb(255,255,255));
                            btn372.setBackgroundColor(rgb(255,255,255));
                            btn373.setBackgroundColor(rgb(255,238,170));
                            btn374.setBackgroundColor(rgb(255,255,255));
                            btn375.setBackgroundColor(rgb(255,255,255));
                            data=3;
                            displayChatMessages();
                            break;
                        case R.id.btn37_4:
                            viewFlipper.setDisplayedChild(3);
                            btn371.setBackgroundColor(rgb(255,255,255));
                            btn372.setBackgroundColor(rgb(255,255,255));
                            btn373.setBackgroundColor(rgb(255,255,255));
                            btn374.setBackgroundColor(rgb(255,238,170));
                            btn375.setBackgroundColor(rgb(255,255,255));
                            data=4;
                            displayChatMessages();
                            break;
                        case R.id.btn37_5:
                            viewFlipper.setDisplayedChild(4);
                            data=5;
                            btn371.setBackgroundColor(rgb(255,255,255));
                            btn372.setBackgroundColor(rgb(255,255,255));
                            btn373.setBackgroundColor(rgb(255,255,255));
                            btn374.setBackgroundColor(rgb(255,255,255));
                            btn375.setBackgroundColor(rgb(255,238,170));
                            displayChatMessages();
                            break;
                        case R.id.btn37_6:
                            Intent intent6 = new Intent();
                            intent6.setClass(Forum_buyerforum.this, NewArticle.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("who",0);
                            if(viewFlipper.getDisplayedChild()==0){
                                bundle.putInt("num",1);
                                bundle.putString("type","問題板");
                            }
                                else if(viewFlipper.getDisplayedChild()==1){
                                bundle.putInt("num",2);
                                bundle.putString("type","閒聊板");
                            }
                                else if(viewFlipper.getDisplayedChild()==2){
                                bundle.putInt("num",3);
                                bundle.putString("type","情報板");
                            }
                                else if(viewFlipper.getDisplayedChild()==3){
                                bundle.putInt("num",4);
                                bundle.putString("type","推薦板");
                            }
                                else if(viewFlipper.getDisplayedChild()==4){
                                bundle.putInt("num",5);
                                bundle.putString("type","開箱板");
                            }

                            bundle.putString("mypic","null");

                            intent6.putExtras(bundle);
                            startActivity(intent6);
                            Forum_buyerforum.this.finish();
                            break;
                    }
                }

//                if (v instanceof ImageButton) {
//                    int id2 = ((ImageButton) v).getId();
//                    switch (id2) {
//                        case R.id.ib37_1:
//                            Intent intent = new Intent();
//                            intent.setClass( Forum_buyerforum.this, Forum.class);
//                            startActivity(intent);
//                            Forum_buyerforum.this.finish();
//                            break;
//                    }
//                }
            }
        };
        btn371.setOnClickListener(OCL);
        btn372.setOnClickListener(OCL);
        btn373.setOnClickListener(OCL);
        btn374.setOnClickListener(OCL);
        btn375.setOnClickListener(OCL);
        btn376.setOnClickListener(OCL);
//        ib371.setOnClickListener(OCL);
    }
    private FirebaseListAdapter<Article> adapter;
    private void displayChatMessages() {

        String fbdata = null;
        if(data==1){
            fbdata="articletitle";
        }
        else if(data==2){
            fbdata="articletitle2";
        }
        else if(data==3){
            fbdata="articletitle3";

        }
        else if(data==4){
            fbdata="articletitle4";
        }
        else if(data==5){
            fbdata="articletitle5";
        }

        DatabaseReference[] reference_contacts11 = {FirebaseDatabase.getInstance().getReference(fbdata)};
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
                            searchAdapter=new SearchAdapter(Forum_buyerforum.this,items,"null");
                            listOfMessages.setAdapter(searchAdapter);
                            lv2.setAdapter(searchAdapter);
                            lv3.setAdapter(searchAdapter);
                            lv4.setAdapter(searchAdapter);
                            lv5.setAdapter(searchAdapter);

                            listOfMessages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                                                intent.setClass(Forum_buyerforum.this, Member_page.class);
                                                Bundle bundle = new Bundle();
                                                bundle.putString("from", "Forum_buyerforum");
                                                bundle.putString("user", list2.get(position).getUsernick());
                                                bundle.putString("useracc", list2.get(position).getMessageUser());
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                                Forum_buyerforum.this.finish();

                                            }
                                        });
                                    }

                                    Intent intent7 = new Intent();
                                    intent7.setClass(Forum_buyerforum.this, ArticlePage.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("who",0);
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
                            lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                                                intent.setClass(Forum_buyerforum.this, Member_page.class);
                                                Bundle bundle = new Bundle();
                                                bundle.putString("from", "Forum_buyerforum");
                                                bundle.putString("user", list2.get(position).getUsernick());
                                                bundle.putString("useracc", list2.get(position).getMessageUser());
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                                Forum_buyerforum.this.finish();

                                            }
                                        });
                                    }

                                    Intent intent7 = new Intent();
                                    intent7.setClass(Forum_buyerforum.this, ArticlePage.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("who",0);
                                    bundle.putInt("num",2);

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

                            lv3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                                                intent.setClass(Forum_buyerforum.this, Member_page.class);
                                                Bundle bundle = new Bundle();
                                                bundle.putString("from", "Forum_buyerforum");
                                                bundle.putString("user", list2.get(position).getUsernick());
                                                bundle.putString("useracc", list2.get(position).getMessageUser());
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                                Forum_buyerforum.this.finish();

                                            }
                                        });
                                    }

                                    Intent intent7 = new Intent();
                                    intent7.setClass(Forum_buyerforum.this, ArticlePage.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("who",0);
                                    bundle.putInt("num",3);

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

                            lv4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                                                intent.setClass(Forum_buyerforum.this, Member_page.class);
                                                Bundle bundle = new Bundle();
                                                bundle.putString("from", "Forum_buyerforum");
                                                bundle.putString("user", list2.get(position).getUsernick());
                                                bundle.putString("useracc", list2.get(position).getMessageUser());
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                                Forum_buyerforum.this.finish();

                                            }
                                        });
                                    }

                                    Intent intent7 = new Intent();
                                    intent7.setClass(Forum_buyerforum.this, ArticlePage.class);
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

                            lv5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                                                intent.setClass(Forum_buyerforum.this, Member_page.class);
                                                Bundle bundle = new Bundle();
                                                bundle.putString("from", "Forum_buyerforum");
                                                bundle.putString("user", list2.get(position).getUsernick());
                                                bundle.putString("useracc", list2.get(position).getMessageUser());
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                                Forum_buyerforum.this.finish();

                                            }
                                        });
                                    }

                                    Intent intent7 = new Intent();
                                    intent7.setClass(Forum_buyerforum.this, ArticlePage.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("who",0);
                                    bundle.putInt("num",5);

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
            intent1.setClass(Forum_buyerforum.this, Forum.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }

}
