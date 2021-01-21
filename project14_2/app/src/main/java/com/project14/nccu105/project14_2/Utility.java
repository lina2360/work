package com.project14.nccu105.project14_2;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Utility {
    public static void setListViewHeightBasedOnChildren(ListView listView) {
// 獲得填充資料後的ListAdapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
//獲得每個條目內容的高度,條目高度相加,獲得資料的總高度
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
//listView.getDividerHeight() * (listAdapter.getCount() - 1)分割線的高度
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
//設定listview的高度
        listView.setLayoutParams(params);
    }
}

