package com.project14.nccu105.project14_2;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

//2
public class MainPage extends AppCompatActivity {
    Button btn21, btn22, btn23, btn24;
    LoginButton loginButton;
    EditText et21, et22;
    String input, account;
    TextInputLayout ll21,ll22;
    CallbackManager callbackManager;
    private FirebaseAuth mAuth;
    String count1="";
    int count = 0;
//    TextView tv23;
boolean fb=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

        FacebookSdk.sdkInitialize(getApplicationContext());
        FacebookSdk.setIsDebugEnabled(true);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        AppEventsLogger.activateApp(this);



        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        getSupportActionBar().setHomeButtonEnabled(true);


//        Toast.makeText(MainPage.this, "token:\n"+ FirebaseInstanceId.getInstance().getToken(),
//                Toast.LENGTH_LONG).show();
        Log.i("MyFirebaseService","token:\n"+ FirebaseInstanceId.getInstance().getToken());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId  = "default_notification_channel_id";
            String channelName = "default_notification_channel_name";
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW));
        }


        btn21 = (Button) findViewById(R.id.btn2_1);
        btn22 = (Button) findViewById(R.id.btn2_2);
        btn23 = (Button) findViewById(R.id.btn2_3);
//        btn24 = (Button) findViewById(R.id.btn2_4);
        et21 = (EditText) findViewById(R.id.et2_1);
        et22 = (EditText) findViewById(R.id.et2_2);
        ll21 = (TextInputLayout) findViewById(R.id.ll21);
        ll22 = (TextInputLayout) findViewById(R.id.ll22);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "public_profile");
//        tv23 = (TextView) findViewById(R.id.tv2_3);

        callbackManager = CallbackManager.Factory.create();
        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser user = mAuth.getCurrentUser();

        // 登入回傳資訊處理
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //成功登入 Facebook 時的處理
                AccessToken token = loginResult.getAccessToken();
                AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
                mAuth.signInWithCredential(credential)
                        .addOnCompleteListener(MainPage.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull com.google.android.gms.tasks.Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // 成功登入顯示使用者訊
                                    final FirebaseUser user = mAuth.getCurrentUser();
//                                    message.setText(user.getDisplayName());

                                    Toast.makeText(MainPage.this, user.getDisplayName(),
                                            Toast.LENGTH_SHORT).show();
                                    final int[] temp = new int[1];

                                    DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_file")};
                                    reference_contacts[0].addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            for (DataSnapshot ds : dataSnapshot.getChildren()) {

                                                temp[0] = count+1;
                                                count++;
                                                if(ds.child("mail").getValue().equals(user.getEmail())){
                                                    fb=true;
                                                }

                                            }


                                            count1=Integer.toString(temp[0]);
                                            if(fb==false){

                                                Toast.makeText(MainPage.this, "註冊成功", Toast.LENGTH_SHORT).show();
                                                FirebaseDatabase.getInstance()
                                                        .getReference("buyer_file")
                                                        .child(count1)
                                                        .setValue(new BuyerFile_Container(
                                                                        user.getEmail(),
                                                                        0,
                                                                        0,
                                                                        "null",
                                                                        "null",
                                                                        "請立即補上",
                                                                        "null",
                                                                        "null",
                                                                        0,
                                                                        0,
                                                                        "新手買家，請多指教",
                                                                        count1,
                                                                        "null",
                                                                        "null",
                                                                        user.getDisplayName(),
                                                                "null"

                                                                )

                                                        );
                                                Intent intent11 = new Intent();
                                                intent11.setClass(MainPage.this, Forum.class);
                                                startActivity(intent11);
                                                MainPage.this.finish();


                                            }else{
                                                Intent intent11 = new Intent();
                                                intent11.setClass(MainPage.this, Forum.class);
                                                startActivity(intent11);
                                                MainPage.this.finish();
                                            }





                                        }


                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }


                                    });




                                } else {
                                    // 登入失敗顯示
                                    Toast.makeText(MainPage.this, "登入失敗",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }


                        });
            }
            @Override
            public void onCancel() {
                // App code 取消登入時的處理
                Toast.makeText(MainPage.this,"取消", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(FacebookException exception) {
                // App code 登入錯誤時的處理
                Toast.makeText(MainPage.this,"失敗", Toast.LENGTH_SHORT).show();
            }
        });
//        Profile fbProfile = Profile.getCurrentProfile();
//        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
//            @Override
//            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken,
//                                                       AccessToken currentAccessToken) {
//                if (currentAccessToken == null) {
//                    mAuth.signOut();
////                    message.setText("請登入");
////                    deleteContact();//This is my code
//                }
//            }
//        };


        View.OnClickListener OCL = new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                int id = ((Button) v).getId();
                switch (id) {
                    case R.id.btn2_1:


                        String account = et21.getText().toString();
                        String password = et22.getText().toString();


                        ll21.setError("");
                        ll22.setError("");

                        if(TextUtils.isEmpty(account)){
                            ll21.setError(getString(R.string.login_account));
                            return;
                        }
                        else if(TextUtils.isEmpty(password)){
                            ll22.setError(getString(R.string.login_password));
                            return;
                        }

                        mAuth.signInWithEmailAndPassword(account, password)
                                .addOnCompleteListener(MainPage.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(MainPage.this, R.string.login_success, Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent();
                                            intent.setClass(MainPage.this, Forum.class);
                                            startActivity(intent);
                                            finish();
                                        }

                                        else {
                                            Toast.makeText(MainPage.this, R.string.login_failure, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                        break;
                    case R.id.btn2_2:
                        et21.setText("");
                        et22.setText("");
                        Toast.makeText(MainPage.this, "重新輸入", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btn2_3:
//                        String msg = "往註冊頁";
                        Intent intent = new Intent();
                        intent.setClass(MainPage.this, Register.class);
                        startActivity(intent);
//                        Toast.makeText(MainPage.this, msg, Toast.LENGTH_SHORT).show();
//                        MainPage.this.finish();
                        break;
//                    case R.id.btn2_4:

//                        FacebookAuthProvider providerF = new firebase.auth.FacebookAuthProvider();
//
//                        var btnFacebookPopup = document.getElementById('facebookSingUpPopup');
//
//                        btnFacebookPopup.onclick = function() {
//                        firebase.auth().signInWithPopup(providerF).then(function(result) {
//                            var token = result.credential.accessToken;
//                            var user = result.user;
//                        })
//                    }
//
//                    var btnFacebookRedirect = document.getElementById('facebookSingUpRedirect');
//
//                    btnFacebookRedirect.onclick = function() {
//                        firebase.auth().signInWithRedirect(providerF).then(function(result) {
//                            var token = result.credential.accessToken;
//                            var user = result.user;
//                        })
//                    }
//                    就能夠使


//                        break;
                }



            }
        };
        btn21.setOnClickListener(OCL);
        btn22.setOnClickListener(OCL);
        btn23.setOnClickListener(OCL);


    }



@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    callbackManager.onActivityResult(requestCode, resultCode, data);
    super.onActivityResult(requestCode, resultCode, data);
    }

}
