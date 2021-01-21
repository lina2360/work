package com.project14.nccu105.project14_2;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Test1 extends AppCompatActivity {

    private EditText etMain;
    private Button btnMain;
    private TextView tvMain;
    private ClinetThread mClientThread;

    //在主线程中定义Handler传入子线程用于更新TextView
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test1);

        etMain = (EditText) findViewById(R.id.et_main);
        btnMain = (Button) findViewById(R.id.btn_main);
        tvMain = (TextView) findViewById(R.id.tv_main);

        mHandler=new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0) {
                    tvMain.append("\n" + msg.obj.toString());
                }
            }
        };

        //点击button时，获取EditText中string并且调用子线程的Handler发送到服务器
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Message msg = new Message();
                    msg.what = 1;

                    msg.obj = etMain.getText().toString();

                    mClientThread.revHandler.sendMessage(msg);
                    Toast.makeText(Test1.this, "connect success!", Toast.LENGTH_SHORT).show();
                    etMain.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        mClientThread = new ClinetThread(mHandler);
        new Thread(mClientThread).start();


    }
}
