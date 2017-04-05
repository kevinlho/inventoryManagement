package com.kevin.inventorymanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kevin.inventorymanagement.Class.Batik;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    final public static String TAG = "xD : ";
    final private static DatabaseReference firebaseRoot = FirebaseDatabase.getInstance().getReference();

    TextView text;

    ArrayList product = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        text = (TextView)findViewById(R.id.text);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG,"xDz");
        firebaseRoot.child("product/dolby/lenganpendek/uhk").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Batik product = new Batik();
                    product.setCode(snapshot.getKey());
                    product.setName((String)snapshot.child("name").getValue());
                    Log.v(TAG,product.getCode());
                    Log.v(TAG,product.getName());
                    for(DataSnapshot x : snapshot.child("quantity").getChildren()){
                        Log.v("xD",(String)x.getValue());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
