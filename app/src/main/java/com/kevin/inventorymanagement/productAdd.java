package com.kevin.inventorymanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kevin.inventorymanagement.Class.Batik;

import java.util.ArrayList;

public class productAdd extends utilities implements View.OnClickListener{

    final private static DatabaseReference firebaseRoot = FirebaseDatabase.getInstance().getReference();

    EditText productCode;
    EditText productName;
    EditText productPrice;
    EditText productQuantity_S;
    EditText productQuantity_M;
    EditText productQuantity_L;
    EditText productQuantity_XL;
    EditText productQuantity_XXL;

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
        productPrice = (EditText)findViewById(R.id.productAdd_productPrice);
        productQuantity_S = (EditText)findViewById(R.id.productAdd_productQuantity_S);
        productQuantity_M = (EditText)findViewById(R.id.productAdd_productQuantity_M);
        productQuantity_L = (EditText)findViewById(R.id.productAdd_productQuantity_L);
        productQuantity_XL = (EditText)findViewById(R.id.productAdd_productQuantity_XL);
        productQuantity_XXL = (EditText)findViewById(R.id.productAdd_productQuantity_XXL);

        productType = (Spinner)findViewById(R.id.productAdd_productType);
        productBahan = (Spinner)findViewById(R.id.productAdd_productBahan);

        typeAdapter =  new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spinner_TipeBaju));
        bahanAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spinner_Bahan));

        findViewById(R.id.productAdd_addButton).setOnClickListener(this);
        findViewById(R.id.productAdd_updateButton).setOnClickListener(this);

        productType.setAdapter(typeAdapter);
        productBahan.setAdapter(bahanAdapter);
    }

    private void addProduct(){

        showProgressDialog();

        ArrayList<Long> quantityList = new ArrayList<Long>();
        quantityList.add(Long.parseLong(productQuantity_S.getText().toString()));
        quantityList.add(Long.parseLong(productQuantity_M.getText().toString()));
        quantityList.add(Long.parseLong(productQuantity_L.getText().toString()));
        quantityList.add(Long.parseLong(productQuantity_XL.getText().toString()));
        quantityList.add(Long.parseLong(productQuantity_XXL.getText().toString()));

        Batik newBatik = new Batik(productCode.getText().toString().toLowerCase(),productName.getText().toString().toLowerCase(),quantityList);
        firebaseRoot.child("product/"
                +productBahan.getSelectedItem().toString().toLowerCase()+"/"
                +productType.getSelectedItem().toString().toLowerCase()+"/"
                +productPrice.getText().toString().toLowerCase()+"/"
                +productCode.getText().toString().toLowerCase()+"/"
        ).setValue(newBatik);

        hideProgressDialog();
        Toast.makeText(this,"Produk telah di tambah",Toast.LENGTH_SHORT).show();
    }

    private void updateProduct(){
        final ArrayList<Long> updatedList = new ArrayList<Long>();
        updatedList.add(Long.parseLong(productQuantity_S.getText().toString()));
        updatedList.add(Long.parseLong(productQuantity_M.getText().toString()));
        updatedList.add(Long.parseLong(productQuantity_L.getText().toString()));
        updatedList.add(Long.parseLong(productQuantity_XL.getText().toString()));
        updatedList.add(Long.parseLong(productQuantity_XXL.getText().toString()));

        utilities.firebaseRoot.child("product/"
                +productBahan.getSelectedItem().toString().toLowerCase()+"/"
                +productType.getSelectedItem().toString().toLowerCase()+"/"
                +productPrice.getText().toString().toLowerCase()+"/"
                +productCode.getText().toString().toLowerCase()+"/quantityList").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    int getKey = Integer.parseInt(snapshot.getKey());
                    Long getVal = snapshot.getValue(Long.class);
                    long totalVal = getVal + updatedList.get(getKey);
                    snapshot.getRef().setValue(totalVal);
                    Toast.makeText(productAdd.this, "Produk berhasil di tambah!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i){
            case R.id.productAdd_addButton:
                addProduct();
                break;
            case R.id.productAdd_updateButton:
                updateProduct();
                break;
            default:
                break;
        }
    }
}
