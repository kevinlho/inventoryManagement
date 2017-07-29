package com.kevin.rumahbatikinventory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kevin.rumahbatikinventory.product.productAdd;
import com.kevin.rumahbatikinventory.product.productDelete;
import com.kevin.rumahbatikinventory.product.productScan;
import com.kevin.rumahbatikinventory.product.productView;

public class dashboard extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        findViewById(R.id.dashboard_button_addProduct).setOnClickListener(this);
        findViewById(R.id.dashboard_button_sellProduct).setOnClickListener(this);
        findViewById(R.id.dashboard_button_checkProduct).setOnClickListener(this);
        findViewById(R.id.dashboard_button_viewProduct).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i){
            case R.id.dashboard_button_addProduct:
                utilities.goTo(this,productAdd.class);
                break;
            case R.id.dashboard_button_sellProduct:
                utilities.goTo(this,productDelete.class);
                break;
            case R.id.dashboard_button_checkProduct:
                utilities.goTo(this,productScan.class);
                break;
            case R.id.dashboard_button_viewProduct:
                utilities.goTo(this,productView.class);
                break;
            default:
                break;
        }
    }
}
