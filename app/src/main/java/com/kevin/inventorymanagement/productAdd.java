package com.kevin.inventorymanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class productAdd extends AppCompatActivity {

    final private static DatabaseReference firebaseRoot = FirebaseDatabase.getInstance().getReference();

    EditText productCode;
    EditText productName;
    EditText productQuantity;

    Spinner productType;
    Spinner productBahan;

    ArrayAdapter<String> typeAdapter;
    ArrayAdapter<String> bahanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);

        productCode = (EditText)findViewById(R.id.productAdd_productCode);
        productName = (EditText)findViewById(R.id.productAdd_productName);

        productType = (Spinner)findViewById(R.id.productAdd_productType);
        productBahan = (Spinner)findViewById(R.id.productAdd_productBahan);

        typeAdapter =  new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spinner_TipeBaju));
        bahanAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spinner_Bahan));

        productType.setAdapter(typeAdapter);
        productBahan.setAdapter(bahanAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void addProduct(){
        firebaseRoot.child("product/"
                +productBahan.getSelectedItem().toString()+"/"
                +productType.getSelectedItem().toString()+"/"
                +productCode.getText().toString());
    }

    private void updateProduct(){

    }
}
