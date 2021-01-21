package com.project14.nccu105.project14_2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
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
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.List;
import java.util.Map;

public class SearchAdapter extends BaseAdapter
{
    private LayoutInflater mLayInf;
    List<Map<String, Object>> mItemList;
    Context context;
    String db;
    public SearchAdapter(Context context, List<Map<String, Object>> itemList,String db)
    {
        this.context=context;
        mLayInf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mItemList = itemList;
//        Toast.makeText(context, context.getText(0), Toast.LENGTH_LONG).show();
        this.db=db;
    }

    @Override
    public int getCount()
    {
        //取得 ListView 列表 Item 的數量
        return mItemList.size();
    }

    @Override
    public Object getItem(int position)
    {
        //取得 ListView 列表於 position 位置上的 Item
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        //取得 ListView 列表於 position 位置上的 Item 的 ID
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        View v;


        if(mItemList.get(position).get("from").toString().equals("Seller")){
            v = mLayInf.inflate(R.layout.product, parent, false);
            StorageReference mStorageRef;
            StorageReference riversRef;
            FirebaseStorage storage = FirebaseStorage.getInstance();
            mStorageRef = storage.getReference();
            riversRef = mStorageRef.child(db).child(mItemList.get(position).get("image").toString());

            ImageView img = (ImageView) v.findViewById(R.id.img_list);
            TextView Title = (TextView)v.findViewById(R.id.text_list);

            downloadImg(riversRef,img);

            if(mItemList.get(position).get("text").toString().length()>5)
                Title.setText(mItemList.get(position).get("text").toString().substring(0,5)+"...");
            else
                Title.setText(mItemList.get(position).get("text").toString());




        }  else if(mItemList.get(position).get("from").toString().equals("Seller22")){
            v = mLayInf.inflate(R.layout.product2, parent, false);
            ImageView img = (ImageView) v.findViewById(R.id.img_list2);
            TextView Title = (TextView)v.findViewById(R.id.text_list2);
            TextView price = (TextView) v.findViewById(R.id.ptv2);
            TextView num = (TextView) v.findViewById(R.id.ptv4);
            TextView fee = (TextView) v.findViewById(R.id.ptv7);

            StorageReference mStorageRef;
            StorageReference riversRef;
            FirebaseStorage storage = FirebaseStorage.getInstance();
            mStorageRef = storage.getReference();
            riversRef = mStorageRef.child(db).child(mItemList.get(position).get("image").toString());
            downloadImg(riversRef,img);

            if(mItemList.get(position).get("text").toString().length()>5)
                Title.setText(mItemList.get(position).get("text").toString().substring(0,5)+"...");
            else
                Title.setText(mItemList.get(position).get("text").toString());

            fee.setText(mItemList.get(position).get("fee").toString());
            price.setText(mItemList.get(position).get("price").toString());
            num.setText(mItemList.get(position).get("num").toString());

        }
        else if(mItemList.get(position).get("from").toString().equals("Notify")){
                v = mLayInf.inflate(R.layout.listview2, parent, false);
                ImageView img = (ImageView)v.findViewById(R.id.listimg2);
                TextView Product = (TextView)v.findViewById(R.id.listtext2_1);
                TextView Time = (TextView)v.findViewById(R.id.listtext2_2);
                TextView State = (TextView)v.findViewById(R.id.listtext2_3);
                TextView Kind = (TextView)v.findViewById(R.id.listtext2_4);
                // Set their text
                Product.setText("您的訂單"+mItemList.get(position).get("ordernum").toString());
        //
                State.setText("目前狀態為「" + mItemList.get(position).get("orderstate").toString() +"」");
                Kind.setText(mItemList.get(position).get("kind").toString());
                if(Kind.getText().equals("【購物】")||Kind.getText().equals("【下單】"))
                {
                    Uri file = Uri.fromFile(new File(mItemList.get(position).get("image").toString()));

                    StorageReference mStorageRef;
                    StorageReference riversRef;
                    FirebaseStorage storage = FirebaseStorage.getInstance();
                    mStorageRef = storage.getReference();
                    riversRef = mStorageRef.child("seller_order").child(file.getLastPathSegment());
                    Log.d("hihi", "populateView: "+file.getLastPathSegment());
                    downloadImg(riversRef,img);
                }
                if(Kind.getText().equals("【接單】")||Kind.getText().equals("【代購】"))
                {
                    Uri file = Uri.fromFile(new File(mItemList.get(position).get("image").toString()));

                    StorageReference mStorageRef;
                    StorageReference riversRef;
                    FirebaseStorage storage = FirebaseStorage.getInstance();
                    mStorageRef = storage.getReference();
                    riversRef = mStorageRef.child("buyer_order").child(file.getLastPathSegment());
                    Log.d("hihi", "populateView: "+file.getLastPathSegment());
                    downloadImg(riversRef,img);
                }
        //
        //                                // Format the date before showing it
                Time.setText(DateFormat.format("yyyy-MM-dd(HH:mm)",
                        Long.parseLong(mItemList.get(position).get("ordertime").toString())));
        //                                break


          } else if(mItemList.get(position).get("from").toString().equals("Forum_seller")){
            v = mLayInf.inflate(R.layout.articlelist, parent, false);

            TextView Title = (TextView)v.findViewById(R.id.article_title);
            final TextView Count = (TextView)v.findViewById(R.id.count);
            TextView Content = (TextView)v.findViewById(R.id.article_content);
            final TextView user = (TextView)v.findViewById(R.id.username);
            TextView Time = (TextView)v.findViewById(R.id.time);

            DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference(mItemList.get(position).get("chatdb").toString())};
            reference_contacts[0].addValueEventListener(new ValueEventListener() {
                int num =0;
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        if(mItemList.get(position).get("time").toString().equals(ds.getKey())){
                            num = (int) ds.getChildrenCount();
                            Count.setText(Integer.toString(num));
                            num=0;
                        }

                    }
                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            // Set their text
            if(mItemList.get(position).get("title").toString().length()>15)
                Title.setText(mItemList.get(position).get("title").toString().substring(0,14)+"...");
            else
                Title.setText(mItemList.get(position).get("title").toString());


            if(mItemList.get(position).get("content").toString().length()>19)
                Content.setText(mItemList.get(position).get("content").toString().substring(0,18)+"...");
            else
                Content.setText(mItemList.get(position).get("content").toString());

            user.setText(mItemList.get(position).get("user").toString());

            Time.setText(DateFormat.format("yyyy-MM-dd(HH:mm)",
                    Long.parseLong(mItemList.get(position).get("time").toString())));


        }

        else{//result
        //設定與回傳 convertView 作為顯示在這個 position 位置的 Item 的 View。
        v = mLayInf.inflate(R.layout.productlist, parent, false);
        StorageReference mStorageRef;
        StorageReference riversRef;
        FirebaseStorage storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();
        riversRef = mStorageRef.child(db).child(mItemList.get(position).get("image").toString());

        ImageView imgView = (ImageView) v.findViewById(R.id.proimg);
        TextView brand = (TextView) v.findViewById(R.id.protext1);
        TextView txtView = (TextView) v.findViewById(R.id.protext3);
        TextView price = (TextView) v.findViewById(R.id.protext5);
        TextView num = (TextView) v.findViewById(R.id.protext7);
        TextView fee = (TextView) v.findViewById(R.id.protext9);

        downloadImg(riversRef,imgView);
        String string=mItemList.get(position).get("brand").toString()+" - "+mItemList.get(position).get("text").toString();
        //保存16個字
        if(string.length()>16){
            int i1=mItemList.get(position).get("brand").toString().length();
            int i12=mItemList.get(position).get("text").toString().length();
            if(i1>9&&i12>6){
                brand.setText(mItemList.get(position).get("brand").toString().substring(0,8)+"...");
                txtView.setText(mItemList.get(position).get("text").toString().substring(0,5)+"...");
            }
            else if(i1>9&&i12<=6){
                brand.setText(mItemList.get(position).get("brand").toString().substring(0,8)+"...");
                txtView.setText(mItemList.get(position).get("text").toString());
            }
            else {
                int i2=13-i1;
                brand.setText(mItemList.get(position).get("brand").toString());
                txtView.setText(mItemList.get(position).get("text").toString().substring(0,i2)+"...");
            }
        }else//<=16
        {
                brand.setText(mItemList.get(position).get("brand").toString());
                txtView.setText(mItemList.get(position).get("text").toString());

        }


        price.setText(mItemList.get(position).get("price").toString());
        num.setText(mItemList.get(position).get("num").toString());
        fee.setText(mItemList.get(position).get("fee").toString());

    }


        return v;
    }


    private void downloadImg(final StorageReference ref, final ImageView img){
        if(ref == null){
//            Toast.makeText(SearchAdapter.this, "請先上傳照片", Toast.LENGTH_SHORT).show();
            Log.d("down", "onSuccess: ");
            return;
        }



        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Activity activity= (Activity) context;
                if(!isDestroy(activity)){
                    Glide.with(context)

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

}
