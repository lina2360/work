package com.project14.nccu105.project14_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PaymentCompleted2 extends AppCompatActivity {
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_completed2);

        getSupportActionBar().hide();

        final TextView tv535 = (TextView)findViewById(R.id.tv53_5);
        TextView tv5311 = (TextView)findViewById(R.id.tv53_11);
        TextView tv5313 = (TextView)findViewById(R.id.tv53_13);
        TextView tv5315 = (TextView)findViewById(R.id.tv53_15);


        bundle=getIntent().getExtras();

        tv535.setText(bundle.getString("ordernum"));
        tv5315.setText(bundle.getString("moneyway"));

        tv5311.setText(Integer.toString(bundle.getInt("price")*bundle.getInt("num")+bundle.getInt("fee")*bundle.getInt("num")));
        tv5313.setText(Integer.toString(bundle.getInt("fee")*bundle.getInt("num")));


        Button btn531 = (Button)findViewById(R.id.btn53_1);



        btn531.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(PaymentCompleted2.this, Order.class);
                Bundle bundle2=new Bundle();
                bundle2.putString("orderNum",tv535.getText().toString());
                bundle2.putString("usernick",bundle.getString("usernick"));
                bundle2.putString("orderkind", "【接單】");
                bundle2.putString("orderpic", bundle.getString("picture"));
                bundle2.putString("user",bundle.getString("user"));
                intent.putExtras(bundle2);
                startActivity(intent);
                PaymentCompleted2.this.finish();
            }

        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            final TextView tv535 = (TextView)findViewById(R.id.tv53_5);
            Bundle bundle2=new Bundle();
            Intent intent = new Intent();
            intent.setClass(PaymentCompleted2.this, Order.class);
            bundle2.putString("orderNum", tv535.getText().toString());
            bundle2.putString("orderkind", "【接單】");
            bundle2.putString("orderpic", bundle.getString("picture"));
            intent.putExtras(bundle2);
            startActivity(intent);
            PaymentCompleted2.this.finish();

        }
        return false;
    }
}

