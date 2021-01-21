package com.project14.nccu105.project14_2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class PackageNumber extends AppCompatActivity {
    final String iam= FirebaseAuth.getInstance()
            .getCurrentUser()
            .getEmail();
    Boolean p1=false;
    Boolean p2=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.package_number);

        getSupportActionBar().hide();
        final EditText et571= (EditText)findViewById(R.id.et57_1);
        Button btn571= (Button)findViewById(R.id.btn57_1);
        Button btn572= (Button)findViewById(R.id.btn57_2);
        final Bundle bundle=getIntent().getExtras();
        final String orderNum=bundle.getString("orderNum");
        final String user2=bundle.getString("user2");
        final String orderkind=bundle.getString("orderkind");
        final String orderpic =bundle.getString("orderpic");

        btn571.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle1=new Bundle();
                bundle1.putString("orderNum",orderNum);
                bundle1.putString("orderkind", orderkind);
                bundle1.putString("orderpic", orderpic);
                Intent intent1 = new Intent();
                intent1.setClass(PackageNumber.this,Order.class);
                intent1.putExtras(bundle1);
                startActivity(intent1);
                PackageNumber.this.finish();
            }
        });

        btn572.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et571.getText().length()!=12){//物流要有幾碼...
                    Toast.makeText(PackageNumber.this, "請確認是否填寫正確物流編號唷", Toast.LENGTH_SHORT).show();
                }
                else{
                    new AlertDialog.Builder(PackageNumber.this)
                            .setTitle("物流編號確認")
                            .setMessage("按下'確認'後，我們將更新物流編號與訂單狀態，日後不能再做更改，感謝您！")
                            .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    long messageTime = new Date().getTime();
                                    final String time = Long.toString(messageTime);

                                    DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_file")};//顯示資訊
                                    reference_contacts[0].addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                                if(ds.child("mail").getValue().equals(iam)){

                                                    String childf="orderState";
                                                    String valuef="已出貨";
                                                    for(int i=1;i<=4;i++){
                                                        FirebaseDatabase.getInstance()
                                                                .getReference("buyer_file")
                                                                .child(ds.child("memberNum").getValue().toString())
                                                                .child("mybuy")
                                                                .child(orderNum)
                                                                .child(childf)
                                                                .setValue(valuef);
                                                        if(i==1)
                                                        {
                                                            childf="renewTime";
                                                            valuef=time;
                                                        }
                                                        else if(i==2){
                                                            childf="deliveryTime";
                                                            valuef=time;
                                                        }
                                                        else if(i==3){
                                                            childf="deliveryNum";
                                                            valuef=et571.getText().toString();
                                                        }
//
                                                    }

                                                    p1=true;
                                                }
                                                else if(ds.child("mail").getValue().equals(user2)){

                                                    String childf="orderState";
                                                    String valuef="已出貨";
                                                    for(int i=1;i<=4;i++){
                                                        FirebaseDatabase.getInstance()
                                                                .getReference("buyer_file")
                                                                .child(ds.child("memberNum").getValue().toString())
                                                                .child("mybuy")
                                                                .child(orderNum)
                                                                .child(childf)
                                                                .setValue(valuef);
                                                        if(i==1)
                                                        {
                                                            childf="renewTime";
                                                            valuef=time;
                                                        }
                                                        else if(i==2){
                                                            childf="deliveryTime";
                                                            valuef=time;
                                                        }
                                                        else if(i==3){
                                                            childf="deliveryNum";
                                                            valuef=et571.getText().toString();
                                                        }

                                                    }

                                                    p2=true;
                                                }

                                            }
                                            if(p1==true&&p2==true){
//
                                                Intent intent1 = new Intent();
                                                intent1.setClass(PackageNumber.this,Notify.class);
                                                startActivity(intent1);
                                                PackageNumber.this.finish();

                                            }

                                        }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                        }
                                    });
//
                                }
                            })
                            .setNegativeButton("關閉視窗", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();


                }

            }

        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            final Bundle bundle=getIntent().getExtras();
            final String orderNum=bundle.getString("orderNum");
            final String user2=bundle.getString("user2");
            final String orderkind=bundle.getString("orderkind");
            final String orderpic =bundle.getString("orderpic");


            Bundle bundle1=new Bundle();
            bundle1.putString("orderNum",orderNum);
            bundle1.putString("orderkind", orderkind);
            bundle1.putString("orderpic", orderpic);
            Intent intent1 = new Intent();
            intent1.setClass(PackageNumber.this,Order.class);
            intent1.putExtras(bundle1);
            startActivity(intent1);
            PackageNumber.this.finish();
        }
        return false;
    }
}
