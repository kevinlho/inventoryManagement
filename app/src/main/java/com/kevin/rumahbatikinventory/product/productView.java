package com.kevin.rumahbatikinventory.product;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.kevin.rumahbatikinventory.Class.Batik;
import com.kevin.rumahbatikinventory.CustomAdapter.CustomAdapterListView_productView;
import com.kevin.rumahbatikinventory.R;
import com.kevin.rumahbatikinventory.utilities;

import java.util.ArrayList;

public class productView extends AppCompatActivity implements View.OnClickListener {

    TextView textDataNotFound;

    Spinner spinnerBahan;
    Spinner spinnerTipe;
    Spinner spinnerKode;

    ArrayAdapter bahanAdapter;
    ArrayAdapter tipeAdapter;
    ArrayAdapter kodeAdapter;

    ListView productView;

    ArrayList<Batik> batikList = new ArrayList<Batik>();
    CustomAdapterListView_productView productViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        findViewById(R.id.productView_Search).setOnClickListener(this);

        textDataNotFound = (TextView)findViewById(R.id.productView_textDataNotFound);

        spinnerBahan = (Spinner)findViewById(R.id.productView_spinnerBahan);
        spinnerTipe = (Spinner)findViewById(R.id.productView_spinnerTipe);
        spinnerKode = (Spinner)findViewById(R.id.productView_spinnerKode);

        bahanAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spinner_Bahan));
        tipeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spinner_TipeBaju));
        kodeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spinner_KodeBaju));

        productView = (ListView)findViewById(R.id.productView_ListView);

        spinnerBahan.setAdapter(bahanAdapter);
        spinnerTipe.setAdapter(tipeAdapter);
        spinnerKode.setAdapter(kodeAdapter);
        productViewAdapter = new CustomAdapterListView_productView(this,batikList);

    }

    @Override
    protected void onStart() {
        super.onStart();

//        utilities.firebaseRoot.child("product/"+
//                        spinnerBahan.getSelectedItem().toString().toLowerCase()+"/"+
//                        spinnerTipe.getSelectedItem().toString().toLowerCase()+"/"+
//                        spinnerKode.getSelectedItem().toString().toLowerCase()
//        ).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
//                    Log.v("xD","2");
//                    Batik getBatik = snapshot.getValue(Batik.class);
//                    Log.v("xD",getBatik.getName());
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }

    public final void populateListView(){
        utilities.firebaseRoot.child("product/"+
                        spinnerBahan.getSelectedItem().toString().toLowerCase()+"/"+
                        spinnerTipe.getSelectedItem().toString().toLowerCase()+"/"+
                        spinnerKode.getSelectedItem().toString().toLowerCase()
        ).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                batikList.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Batik getBatik = snapshot.getValue(Batik.class);
                    batikList.add(getBatik);
                }
                productView.setAdapter(productViewAdapter);
                if (batikList.isEmpty()) {
                    textDataNotFound.setVisibility(View.VISIBLE);
                }
                else {
                    textDataNotFound.setVisibility(View.INVISIBLE);
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
            case R.id.productView_Search:
                populateListView();
                break;
            default:
                break;
        }
    }
}
