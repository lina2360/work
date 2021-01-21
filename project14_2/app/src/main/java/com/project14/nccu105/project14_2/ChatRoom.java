package com.project14.nccu105.project14_2;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatRoom  extends AppCompatActivity {



    private int[] image = {
            R.drawable.notify1, R.drawable.notify2, R.drawable.notify3
    };
    private String[] nameText = {
            "花花", "泡泡", "毛毛"
    };
    private String[] timeText2 = {
            "2019-03-10", "2019-03-11", "2019-03-12"
    };
    private String[] contentText3 = {
            "想請問這個商品還可以幫我買嗎", "貨已經出囉~希望你會喜歡~", "想請問一次買多可以便宜一點嗎"
    };



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatroom);

        ListView listview1 = (ListView) findViewById(R.id.lv32_1);
        List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
        for (int i = 0; i < image.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("image", image[i]);
            item.put("text", nameText[i]);
            item.put("text2", timeText2[i]);
            item.put("text3", contentText3[i]);
            items.add(item);
        }
        //ListView 要顯示的內容
        //android.R.layout.simple_list_item_1 為內建樣式，還有其他樣式可自行研究
        SimpleAdapter adapter1 = new SimpleAdapter(this, items,
                R.layout.listview2,new String[]{"image", "text", "text2", "text3"}, new int[]{R.id.listimg2, R.id.listtext2_1, R.id.listtext2_2, R.id.listtext2_3}
        );
        listview1.setAdapter(adapter1);





    }

}