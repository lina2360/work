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
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class PictureChange extends AppCompatActivity {
    Button btn501, btn502, btn503;
    private static final int REQUEST_EXTERNAL_STORAGE = 404;
    private String imgPath;
    private ProgressBar imgUploadProgress;
    ImageView iv501;
    private StorageReference mStorageRef;
    private StorageReference riversRef;
    private static final int PICKER = 101;
    String mynum ;
    String mypic  ;
    String file2="";
    String from;
    String picdb;
    Bundle bundle;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picturechange);
        getSupportActionBar().hide();
        //設定隱藏狀態
        bundle=getIntent().getExtras();
        from=bundle.getString("from");

        if(from.equals("NewArticle")){
            if(bundle.getInt("who")==0)
               picdb="buyer_article";
            else if(bundle.getInt("who")==2)
                picdb="official_article";
            else
                picdb="seller_article";


        }else if(from.equals("NewBuyerOrder")){
            picdb="buyer_order";
        }else if(from.equals("NewSellerOrder")){
            picdb="seller_order";
        }else if(from.equals("Personal_setting_myinfo_info")){
            picdb="member_picture";
        }
        else if(from.equals("Order_receipt")){
            picdb="order_receipt";
        }

        FirebaseStorage storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();
//        Bundle bundlefrom=new Bundle();

        mynum=bundle.getString("mynum");
        mypic=bundle.getString("mypic");

//        Log.d("error", "onCrete mynum"+ mynum);
//        Log.d("error", "onCrete mypic"+ mynum);
//        Log.d("error", "onCrete from"+ mynum);

        iv501= (ImageView)findViewById(R.id.iv500);

        btn501 = (Button) findViewById(R.id.btn500_1);
        btn502 = (Button) findViewById(R.id.btn500_2);
        btn503 = (Button) findViewById(R.id.btn500_3);

        //取消鍵
        btn503.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                final Bundle bundlefrom=new Bundle();
                bundlefrom.putString("mypic",bundle.getString("mypic"));
                if(from.equals("NewArticle")){
                    bundlefrom.putString("type",bundle.getString("type"));
                    bundlefrom.putInt("who",bundle.getInt("who"));
                    bundlefrom.putInt("num",bundle.getInt("num"));
                    intent1.putExtras(bundlefrom);
                    intent1.setClass(PictureChange.this, NewArticle.class);
                }else if(from.equals("NewBuyerOrder")){
                    bundlefrom.putString("from",bundle.getString("from0"));
                    intent1.putExtras(bundlefrom);
                    intent1.setClass(PictureChange.this,NewBuyerOrder_1.class);
                }else if(from.equals("NewSellerOrder")){
                    bundlefrom.putString("from",bundle.getString("from0"));
                    intent1.putExtras(bundlefrom);
                    intent1.setClass(PictureChange.this,NewSellerOrder_1.class);
                }else if(from.equals("Personal_setting_myinfo_info")){
                    intent1.setClass(PictureChange.this,Personal_setting_myinfo_info.class);
                }
                else if(from.equals("Order_receipt")){
                    bundlefrom.putString( "ordernum",bundle.getString("ordernum"));
                    bundlefrom.putString("orderkind",bundle.getString("orderkind"));
                    bundlefrom.putString("user2",bundle.getString("user2"));
                    bundlefrom.putString("who", bundle.getString("who"));
                    intent1.putExtras(bundlefrom);
                    intent1.setClass(PictureChange.this,Order_receipt.class);
                }

                startActivity(intent1);
                PictureChange.this.finish();
            }
        });
        //確認鍵
        btn502.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Bundle bundlefrom=new Bundle();

                if(file2.isEmpty()){
                    Toast.makeText(PictureChange.this, "請選擇想更新的圖片喔！", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(mStorageRef.getParent()==null){

                    }else{
                        deleteImg(mStorageRef.child(picdb).child(mypic));//*
                    }

                    if(from.equals("NewSellerOrder")||from.equals("NewBuyerOrder")||from.equals("NewArticle")||from.equals("Order_receipt")){
                        bundlefrom.putString("mypic",file2);

                    }else if(from.equals("Personal_setting_myinfo_info")){
                        DatabaseReference[] reference_contacts3 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
                        reference_contacts3[0].child(mynum).child("picture").setValue(file2);

                    }

                    Toast.makeText(PictureChange.this, "請稍等3秒...", Toast.LENGTH_SHORT).show();
                    if(!TextUtils.isEmpty(imgPath)) {
                        imgUploadProgress.setVisibility(View.VISIBLE);
                        uploadImg(imgPath);

                    } else{
//                        Toast.makeText(PictureChange.this, "plz_pick_img", Toast.LENGTH_SHORT).show();
                    }

                    Timer timer = new Timer();

                    TimerTask task=new TimerTask(){

                        public void run(){

                            Intent intent1 = new Intent();

                            if(from.equals("NewArticle")){

                                bundlefrom.putString("type",bundle.getString("type"));
                                bundlefrom.putInt("who",bundle.getInt("who"));
                                bundlefrom.putInt("num",bundle.getInt("num"));
                                intent1.putExtras(bundlefrom);
                                intent1.setClass(PictureChange.this, NewArticle.class);
                            }else if(from.equals("NewBuyerOrder")){
                                bundlefrom.putInt("from",bundle.getInt("from0"));
                                intent1.putExtras(bundlefrom);
                                intent1.setClass(PictureChange.this,NewBuyerOrder_1.class);
                            }else if(from.equals("NewSellerOrder")){
                                bundlefrom.putInt("from",bundle.getInt("from0"));
                                intent1.putExtras(bundlefrom);
                                intent1.setClass(PictureChange.this,NewSellerOrder_1.class);
                            }else if(from.equals("Personal_setting_myinfo_info")){
                                intent1.setClass(PictureChange.this,Personal_setting_myinfo_info.class);
                            }
                            else if(from.equals("Order_receipt")){
                                String number=bundle.getString("mynum");
                                for(int i=0;i<=1;i++){
                                    FirebaseDatabase.getInstance()
                                            .getReference("buyer_file")
                                            .child(number)
                                            .child("mybuy")
                                            .child(bundle.getString("ordernum"))
                                            .child("receiptpic")
                                            .setValue(file2);
                                    if(i==0)
                                    number=bundle.getString("user2num");

                                }
                                if (bundle.getString("who").equals("1")){
                                    FirebaseDatabase.getInstance()
                                            .getReference("buyer_file")
                                            .child(bundle.getString("user2num"))
                                            .child("receipt")
                                            .child(bundle.getString("ordernum"))
                                            .setValue(new CardViewMember(
                                                    "order_receipt",
                                                    "",
                                                    file2,
                                                            bundle.getString("ordernum")
                                                            )
                                                    );

                                }
                                bundlefrom.putString("who", bundle.getString("who"));
                                bundlefrom.putString( "ordernum",bundle.getString("ordernum"));
                                bundlefrom.putString("orderkind",bundle.getString("orderkind"));
                                bundlefrom.putString("user2",bundle.getString("user2"));
                                intent1.putExtras(bundlefrom);
                                intent1.setClass(PictureChange.this,Order_receipt.class);
                            }

                            startActivity(intent1);
                            PictureChange.this.finish();

//                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

                        }

                    };
                    timer.schedule(task, 3000);


                }



            }
        });
        //換圖片
        btn501.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkPermission();

            }
        });


    }

    private void checkPermission(){
        int permission = ActivityCompat.checkSelfPermission(PictureChange.this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            //未取得權限，向使用者要求允許權限
            ActivityCompat.requestPermissions(this,
                    new String[] {android.Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_EXTERNAL_STORAGE
            );
        } else {
            getLocalImg();
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocalImg();
                } else {
                    Toast.makeText(PictureChange.this, "取消上傳圖片", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void uploadImg(String path){
        Uri file = Uri.fromFile(new File(path));
        StorageMetadata metadata = new StorageMetadata.Builder()
                .setContentDisposition("universe")
                .setContentType("image/jpg")
                .build();

        riversRef = mStorageRef.child(picdb).child(file.getLastPathSegment());//*
        UploadTask uploadTask = riversRef.putFile(file, metadata);


        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

                Toast.makeText(PictureChange.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast.makeText(PictureChange.this,"upload_success", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                int progress = (int)((100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount());
                imgUploadProgress.setProgress(progress);
                if(progress >= 100){
                    imgUploadProgress.setVisibility(View.GONE);
                }
            }
        });
    }
    private void getLocalImg(){
        Intent picker = new Intent(Intent.ACTION_GET_CONTENT);
        picker.setType("image/*");
        picker.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        Intent destIntent = Intent.createChooser(picker, null);
        startActivityForResult(destIntent, PICKER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICKER) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                String path = getPath(PictureChange.this, uri);
//                infoText.setText(path);
                final Uri file = Uri.fromFile(new File(path));

                StorageReference riversRef = mStorageRef.child(picdb).child(file.getLastPathSegment());//*

                UploadTask uploadTask = riversRef.putFile(file);

                file2=file.getLastPathSegment();


//                Log.d("mynum[0]2", "mynum[0]2: "+mynum);


                iv501.setImageURI(file);


                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {

                        Toast.makeText(PictureChange.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Toast.makeText(PictureChange.this, "更新成功！", Toast.LENGTH_SHORT).show();



                    }
                });
            }
        }
    }



    /**
     * Get a file path from a Uri. This will get the the path for Storage Access
     * Framework Documents, as well as the _data field for the MediaStore and
     * other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri The Uri to query.
     * @author paulburke
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }



    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri The Uri to query.
     * @param selection (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private void deleteImg(final StorageReference ref){
        if(ref == null){
//            Toast.makeText(PictureChange.this, "null", Toast.LENGTH_SHORT).show();
            return;
        }
        ref.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
//                Toast.makeText(PictureChange.this, "delete ok", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
//              Toast.makeText(PictureChange.this, "fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent1 = new Intent();
            final Bundle bundlefrom=new Bundle();
            bundlefrom.putString("mypic",bundle.getString("mypic"));
            if(from.equals("NewArticle")){
                bundlefrom.putString("type",bundle.getString("type"));
                bundlefrom.putInt("who",bundle.getInt("who"));
                bundlefrom.putInt("num",bundle.getInt("num"));
                intent1.putExtras(bundlefrom);
                intent1.setClass(PictureChange.this, NewArticle.class);
            }else if(from.equals("NewBuyerOrder")){
                bundlefrom.putString("from",bundle.getString("from0"));
                intent1.putExtras(bundlefrom);
                intent1.setClass(PictureChange.this,NewBuyerOrder_1.class);
            }else if(from.equals("NewSellerOrder")){
                bundlefrom.putString("from",bundle.getString("from0"));
                intent1.putExtras(bundlefrom);
                intent1.setClass(PictureChange.this,NewSellerOrder_1.class);
            }else if(from.equals("Personal_setting_myinfo_info")){
                intent1.setClass(PictureChange.this,Personal_setting_myinfo_info.class);
            }
            else if(from.equals("Order_receipt")){
                bundlefrom.putString( "ordernum",bundle.getString("ordernum"));
                bundlefrom.putString("orderkind",bundle.getString("orderkind"));
                bundlefrom.putString("user2",bundle.getString("user2"));
                bundlefrom.putString("who", bundle.getString("who"));
                intent1.putExtras(bundlefrom);
                intent1.setClass(PictureChange.this,Order_receipt.class);
            }

            startActivity(intent1);
            PictureChange.this.finish();
        }
        return false;
    }

}
