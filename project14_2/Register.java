package com.project14.nccu105.project14_2;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.SEND_SMS;

public class Register extends AppCompatActivity {
    private static final String TAG = "";
    private static final int REQUEST_SMS = 0;
    Button btn31, btn32, btn33;
    EditText et31, et32, et33, et34, et35, et99;
    private FirebaseAuth mAuth;
    static int buyercount = 0;
    String count1 = "";
    int count = 0;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private boolean mVerificationInProgress = false;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        getSupportActionBar().hide();
        //設定隱藏狀態
        TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

        mAuth = FirebaseAuth.getInstance();

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        btn31 = (Button) findViewById(R.id.btn3_1);
        btn32 = (Button) findViewById(R.id.btn3_2);
        btn33 = (Button) findViewById(R.id.btn3_3);
        et31 = (EditText) findViewById(R.id.editText);
        et32 = (EditText) findViewById(R.id.editText4);
        et33 = (EditText) findViewById(R.id.editText5);
        et34 = (EditText) findViewById(R.id.et3_4);
        et35 = (EditText) findViewById(R.id.editText3);
        et99 = (EditText) findViewById(R.id.editText99);


        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:" + credential);
                // [START_EXCLUDE silent]
                mVerificationInProgress = false;
                // [END_EXCLUDE]


            }


            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e);
                // [START_EXCLUDE silent]
                mVerificationInProgress = false;
                // [END_EXCLUDE]

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // [START_EXCLUDE]
                    et34.setError("Invalid phone number.");
                    // [END_EXCLUDE]
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // [START_EXCLUDE]
                    Snackbar.make(findViewById(android.R.id.content), "Quota exceeded.",
                            Snackbar.LENGTH_SHORT).show();
                    // [END_EXCLUDE]
                }

                // Show a message and update the UI
                // [START_EXCLUDE]
//                updateUI(STATE_VERIFY_FAILED);
                // [END_EXCLUDE]
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
//                Toast.makeText(Register.this, "123456", Toast.LENGTH_SHORT).show();
                // [START_EXCLUDE]
                // Update UI
//                updateUI(STATE_CODE_SENT);
                // [END_EXCLUDE]
            }
        };



//
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }


        View.OnClickListener OCL = new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                int id = ((Button) v).getId();
                switch (id) {
                    case R.id.btn3_1:



                        if (!TextUtils.isEmpty(et34.getText().toString())) {
                                    int permission = ActivityCompat.checkSelfPermission(Register.this, SEND_SMS);
                                    if (permission != PackageManager.PERMISSION_GRANTED){
                                        ActivityCompat.requestPermissions(
                                                Register.this,
                                                new String[]{READ_SMS, SEND_SMS,}, REQUEST_SMS);
                                    }else{
                                        sendSMS();

                                }


                                }


                        break;
                    case R.id.btn3_2:
                        String msg = "返回";
                        Intent intent = new Intent();
                        intent.setClass(Register.this, MainPage.class);
                        startActivity(intent);
//                        Toast.makeText(Register.this, msg, Toast.LENGTH_SHORT).show();
                        Register.this.finish();
                        break;
                    case R.id.btn3_3:



                        if(!et35.getText().toString().matches("47m2z5")){
                            Toast.makeText(Register.this, "請檢查驗證碼", Toast.LENGTH_SHORT).show();
                            return;
                        }
                     else if(et31.getText().toString().matches("")){
                        Toast.makeText(Register.this, "請填寫信箱", Toast.LENGTH_SHORT).show();
                        return;
                    }
                     else if(et99.getText().toString().matches("")){
                         Toast.makeText(Register.this, "請填寫暱稱", Toast.LENGTH_SHORT).show();
                         return;
                     }
                    else if(et32.getText().toString().matches("")){
                        Toast.makeText(Register.this, "請填寫密碼", Toast.LENGTH_SHORT).show();
                        return;
                    }
                        else if(et33.getText().toString().matches("")){
                            Toast.makeText(Register.this, "請確認密碼", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(et33.getText().toString().matches(et32.getText().toString())){

                            final String account = et31.getText().toString();
                            final String password = et32.getText().toString();

                            final int[] temp = new int[1];
                            DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_file")};
                            reference_contacts[0].addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                                        temp[0] = count+1;
                                        count++;

                                    }


                                    count1=Integer.toString(temp[0]);


                                    mAuth.createUserWithEmailAndPassword(account, password)
                                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {

                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
//                                        Toast.makeText(Register.this, "沒成功", Toast.LENGTH_SHORT).show();
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(Register.this, "註冊成功", Toast.LENGTH_SHORT).show();
                                                        FirebaseDatabase.getInstance()
                                                                .getReference("buyer_file")
                                                                .child(count1)
                                                                .setValue(new BuyerFile_Container(
                                                                                account,
                                                                                0,
                                                                                0,
                                                                                "null",
                                                                                "null",
                                                                                et34.getText().toString(),
                                                                                "null",
                                                                                "null",
                                                                                0,
                                                                                0,
                                                                                "新手買家，請多指教",
                                                                                 count1,
                                                                                "null",
                                                                                "null",
                                                                        et99.getText().toString(),
                                                                        "null"

                                                                        )

                                                                );
                                                        Intent intent = new Intent();
                                                        intent.setClass(Register.this, MainPage.class);
                                                        startActivity(intent);
                                                        finish();
                                                    } else {

                                                        Log.e(TAG, "onComplete: Failed=" + task.getException().getMessage());


                                                        Toast.makeText(Register.this, "沒成功", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }


                            });

                        }
                        else{
                            Toast.makeText(Register.this, "與上方密碼不一致，請重新輸入", Toast.LENGTH_SHORT).show();
                            return;

                        }




                        break;

                }


            }

        };
        btn31.setOnClickListener(OCL);
        btn32.setOnClickListener(OCL);
        btn33.setOnClickListener(OCL);



    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String code = String.valueOf(msg.what);

            et35.setText(code);
            smsVerify(code);
        }
    };


    private void smsVerify(String code){
        if ("47m2z5".equals(code)){
            unregisterContentObservers();
            new AlertDialog.Builder(this)
                    .setTitle("簡訊驗證")
                    .setMessage("簡訊驗證成功")
                    .setPositiveButton("OK", null)
                    .show();
        }
    }
    @Override//1 555 521 5554
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_SMS:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                    sendSMS();
                }
        }

    }


    private SMSContentObserver smsContentObserver;

    private void registerContentObservers() {
        Uri uri = Telephony.Sms.CONTENT_URI;
        smsContentObserver = new SMSContentObserver(this, handler);
        getContentResolver().registerContentObserver(uri, true, smsContentObserver);
    }

    private void unregisterContentObservers() {
        getContentResolver().unregisterContentObserver(smsContentObserver);
    }

    private void sendSMS() {
        SmsManager smsManager = SmsManager.getDefault();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(Register.this, 0, new Intent(), 0);
        smsManager.sendTextMessage(
                et34.getText().toString(),
                null,
                "47m2z5",
                pendingIntent,
                null

        );
        registerContentObservers();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.setClass(Register.this, MainPage.class);
            startActivity(intent);
            Register.this.finish();
        }
        return false;
    }


}

