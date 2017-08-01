package com.kevin.rumahbatikinventory.CustomAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.kevin.rumahbatikinventory.R;

import java.util.List;

/**
 * Created by kevin on 7/31/17.
 */

public class CustomAdapterListView_salesView extends ArrayAdapter {
    public CustomAdapterListView_salesView (@NonNull Context context, List ArrayList){
        super(context, R.layout.customlistview_product_view , ArrayList);
    }
}
