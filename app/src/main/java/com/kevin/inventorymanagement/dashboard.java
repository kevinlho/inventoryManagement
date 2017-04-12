package com.kevin.inventorymanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class dashboard extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        findViewById(R.id.dashboard_button_addProduct).setOnClickListener(this);
        findViewById(R.id.dashboard_button_sellProduct).setOnClickListener(this);
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
            default:
                break;
        }
    }
}
