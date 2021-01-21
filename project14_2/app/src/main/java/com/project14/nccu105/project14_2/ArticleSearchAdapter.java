package com.project14.nccu105.project14_2;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;
import java.util.Map;

public class ArticleSearchAdapter extends BaseAdapter
{
    private LayoutInflater mLayInf;
    List<Map<String, Object>> mItemList;
    Context context;
    String db;
    public ArticleSearchAdapter(Context context, List<Map<String, Object>> itemList,String db)
    {
        this.context=context;
        mLayInf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mItemList = itemList;
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
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //設定與回傳 convertView 作為顯示在這個 position 位置的 Item 的 View。
        View v = mLayInf.inflate(R.layout.articlelist, parent, false);
//        StorageReference mStorageRef;
//        StorageReference riversRef;
//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        mStorageRef = storage.getReference();
//        riversRef = mStorageRef.child(db).child(mItemList.get(position).get("image").toString());

        TextView title=(TextView)v.findViewById(R.id.article_title);
        TextView txtView = (TextView) v.findViewById(R.id.article_content);
        TextView user = (TextView) v.findViewById(R.id.username);
        TextView num = (TextView) v.findViewById(R.id.count);
        TextView time = (TextView) v.findViewById(R.id.time);

//        downloadImg(riversRef,imgView);
        txtView.setText(mItemList.get(position).get("text").toString());
        title.setText(mItemList.get(position).get("title").toString());
        user.setText(mItemList.get(position).get("user").toString());
        num.setText(mItemList.get(position).get("num").toString());
        time.setText(mItemList.get(position).get("time").toString());

        return v;
    }


}

