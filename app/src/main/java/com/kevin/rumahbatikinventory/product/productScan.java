package com.kevin.rumahbatikinventory.product;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.kevin.rumahbatikinventory.Class.Batik;
import com.kevin.rumahbatikinventory.R;
import com.kevin.rumahbatikinventory.utilities;

public class productScan extends AppCompatActivity implements View.OnClickListener{

    final private static DatabaseReference firebaseRoot = FirebaseDatabase.getInstance().getReference();

    TextView productBahan;
    TextView productCode;
    TextView productName;
    TextView productQuantity;
    TextView productType;
    TextView QRCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_scan);

        productBahan = (TextView)findViewById(R.id.productView_productBahan);
        productCode = (TextView)findViewById(R.id.productView_productCode);
        productName = (TextView)findViewById(R.id.productView_productName);
        productQuantity = (TextView)findViewById(R.id.productView_productQuantity);
        productType = (TextView)findViewById(R.id.productView_productType);
        QRCode = (TextView)findViewById(R.id.productView_qrCode);

        findViewById(R.id.productView_scanItem).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        firebaseRoot.child("product/dolby/lenganpendek/uhk").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    Batik product = new Batik();
//                    product.setCode(snapshot.getKey());
//                    product.setName((String)snapshot.child("name").getValue());
//                    for(DataSnapshot x : snapshot.child("quantity").getChildren()){
//                        Log.v("xD",(String)x.getValue());
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i){
            case R.id.productView_scanItem:
                scanBarcode();
                break;
            default:
                break;
        }
    }

    public void scanBarcode(){
        IntentIntegrator scanInit = new IntentIntegrator(this);
        scanInit.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        scanInit.setPrompt("Scan");
        scanInit.setCameraId(0);
        scanInit.setBeepEnabled(false);
        scanInit.setBarcodeImageEnabled(false);
        scanInit.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(scanResult != null) {
            if(scanResult.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                String[] result = scanResult.getContents().toLowerCase().split("_");
                QRCode.setText(scanResult.getContents());
                scanChecker(result);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void scanChecker(String[] result){
//        String Tipe = result[0]; (Cewe/Cowo)
        final String code = result[1]; //(kode KA-RUH-1
        final String bahan = bahanChecker(result[1].substring(0,1)); // bahan KA/KB/DA/DB
        final String lengan = lenganChecker(result[1].substring(1,2));
        String harga = result[2]; // kode harga UHK/RUH

        utilities.firebaseRoot.child("product/"
                +bahan+"/"
                +lengan+"/"
                +harga+"/"
                +code).
                addListenerForSingleValueEvent(new ValueEventListener() {
                    String getQuantity = "";
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot: dataSnapshot.child("quantityList").getChildren()){
                            getQuantity += String.valueOf(snapshot.getValue()+" ");
                        }
                        Batik viewBatik = new Batik(code,dataSnapshot.child("name").getValue(String.class));
                        productCode.setText(viewBatik.getCode());
                        productName.setText(viewBatik.getName());
                        productBahan.setText(bahan);
                        productType.setText(lengan);
                        productQuantity.setText(getQuantity);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    private String bahanChecker(String bahan){
        switch (bahan){
            case "k":
                return "katun";
            case "t":
                return "tenun";
            case "v":
                return "viscose";
            case "d":
                return "dolby";
            case "s":
                return "sutra";
            default:
                return "";
        }
    }

    private String lenganChecker(String lengan){
        switch (lengan){
            case "a":
                return "lengan pendek";
            case "b":
                return "lengan panjang";
            default:
                return "";
        }
    }
}
