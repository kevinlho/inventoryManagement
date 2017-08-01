package com.kevin.rumahbatikinventory.sales;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.kevin.rumahbatikinventory.R;

public class salesView extends AppCompatActivity {

    Spinner spinnerToko;
    Spinner spinnerTanggal;

    ArrayAdapter tokoSpinnerAdapter;

    ListView salesView_ListView;

    TextView shopView_textTotal;
    TextView shopView_textTerjual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_view);

        spinnerToko = (Spinner)findViewById(R.id.salesView_spinnerToko);
        spinnerTanggal = (Spinner)findViewById(R.id.salesView_spinnerTanggal);

        salesView_ListView = (ListView)findViewById(R.id.salesView_ListView);

        shopView_textTotal = (TextView)findViewById(R.id.shopView_textTotal);
        shopView_textTerjual = (TextView)findViewById(R.id.salesView_textTerjual);

        tokoSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spinner_Toko));

        spinnerToko.setAdapter(tokoSpinnerAdapter);

    }
}
