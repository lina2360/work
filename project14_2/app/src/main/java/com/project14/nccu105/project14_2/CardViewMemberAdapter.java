package com.project14.nccu105.project14_2;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.lang.reflect.Member;
import java.util.List;

public class CardViewMemberAdapter extends RecyclerView.Adapter<CardViewMemberAdapter.ViewHolder>  implements View.OnClickListener{
    private Context context;
    private List<CardViewMember> memberList;

    CardViewMemberAdapter(Context context, List<CardViewMember> memberList) {
        this.context = context;
        this.memberList = memberList;
    }

    @Override
    public CardViewMemberAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_cardview_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        view.setOnClickListener(this);
        return vh;
    }

    private OnItemClickListener mOnItemClickListener = null;

    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    @Override
    public void onBindViewHolder(CardViewMemberAdapter.ViewHolder holder, int position) {
        final CardViewMember member = memberList.get(position);
//        holder.imageId.setImageResource();
//        holder.textName.setText(position);
        //将position保存在itemView的Tag中，以便点击时进行获取



//        holder.textId.setText(String.valueOf(member.getId()));
        if(member.getName().length()>5)
            holder.textName.setText(member.getName().substring(0,5)+"...");
        else
            holder.textName.setText(member.getName());

        StorageReference mStorageRef;
        StorageReference riversRef;
        FirebaseStorage storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();
        riversRef = mStorageRef.child(member.getDb()).child(member.getImage());
        downloadImg(riversRef,holder.imageId);


        holder.itemView.setTag(position);

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "請先上傳照片", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    //Adapter 需要一個 ViewHolder，只要實作它的 constructor 就好，保存起來的view會放在itemView裡面
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageId;
        TextView textId, textName;
        ViewHolder(View itemView) {
            super(itemView);
            imageId = (ImageView) itemView.findViewById(R.id.imageId);

            textName = (TextView) itemView.findViewById(R.id.textName);



        }
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
//                if(!isDestroy(context)){
                Glide.with(context)

                        .load(ref)
                        .into(img);

//                }

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