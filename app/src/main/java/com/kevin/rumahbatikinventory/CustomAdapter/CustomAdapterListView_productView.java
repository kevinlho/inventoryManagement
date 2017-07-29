package com.kevin.rumahbatikinventory.CustomAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kevin.rumahbatikinventory.Class.Batik;
import com.kevin.rumahbatikinventory.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 03/06/2017.
 */

public class CustomAdapterListView_productView extends ArrayAdapter {
    public CustomAdapterListView_productView(@NonNull Context context, List ArrayList){
        super(context, R.layout.customlistview_product_view , ArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customProductView = inflater.inflate(R.layout.customlistview_product_view,parent,false);

        Batik batikReader = (Batik) getItem(position);
        TextView code = (TextView)customProductView.findViewById(R.id.customListView_product_view_batikCode);
        TextView name = (TextView)customProductView.findViewById(R.id.customListView_product_view_batikName);
        TextView quantity = (TextView)customProductView.findViewById(R.id.customListView_product_view_batikQuantity);

        ArrayList<Long> getQuantity = batikReader.getQuantityList();
        String getQuantityString = "|";

        for (Long loop : getQuantity){
            getQuantityString += " "+loop.toString()+" |";
        }
        code.setText(batikReader.getCode());
        name.setText(String.valueOf(batikReader.getName()));
        quantity.setText(getQuantityString);

        return customProductView;
    }
}
