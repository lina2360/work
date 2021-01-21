package com.project14.nccu105.project14_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PaymentCompleted extends AppCompatActivity {
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_completed);

        getSupportActionBar().hide();

        final TextView tv455 = (TextView)findViewById(R.id.tv45_5);
//        TextView tv457 = (TextView)findViewById(R.id .tv45_7);
//        TextView tv459 = (TextView)findViewById(R.id.tv45_9);
        TextView tv4511 = (TextView)findViewById(R.id.tv45_11);
        TextView tv4513 = (TextView)findViewById(R.id.tv45_13);
        TextView tv4515 = (TextView)findViewById(R.id.tv45_15);
        Button btn451 = (Button)findViewById(R.id.btn45_1);

        bundle=getIntent().getExtras();
        tv455.setText(bundle.getString("ordernum"));
//        int price=bundle.getInt("price")
        int total=(bundle.getInt("price")+bundle.getInt("fee"))*bundle.getInt("num");
        tv4511.setText(Integer.toString(total));
        tv4513.setText(bundle.getString("address"));
        tv4515.setText(bundle.getString("moneyway"));


        btn451.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        Bundle bundle2=new Bundle();
                bundle2.putString("orderNum",tv455.getText().toString());

                Intent intent = new Intent();
                intent.setClass(PaymentCompleted.this, Order.class);
                bundle2.putString("usernick",bundle.getString("usernick"));
                bundle2.putString("orderNum", tv455.getText().toString());
                bundle2.putString("orderkind", "【購物】");
                bundle2.putString("orderpic", bundle.getString("picture"));
                bundle2.putString("user",bundle.getString("user"));
                intent.putExtras(bundle2);
                startActivity(intent);
                PaymentCompleted.this.finish();
            }

        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            final TextView tv455 = (TextView)findViewById(R.id.tv45_5);
            Bundle bundle2=new Bundle();
            Intent intent = new Intent();
            intent.setClass(PaymentCompleted.this, Order.class);
            bundle2.putString("orderNum", tv455.getText().toString());
            bundle2.putString("orderkind", "【購物】");
            bundle2.putString("orderpic", bundle.getString("picture"));
            intent.putExtras(bundle2);
            startActivity(intent);
            PaymentCompleted.this.finish();
        }
        return false;
    }
}
