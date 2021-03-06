package com.kevin.rumahbatikinventory.product;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.kevin.rumahbatikinventory.R;
import com.kevin.rumahbatikinventory.utilities;

public class productDelete extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_delete);
        findViewById(R.id.buttonScan).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i){
            case R.id.buttonScan:
                scanBarcode();
            default:
                break;
                
        }
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
                scanChecker(result);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
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

    public void scanChecker(String[] result){
//        String Tipe = result[0]; (Cewe/Cowo)
        final String code = result[1]; //(kode KA-RUH-1
        final String bahan = bahanChecker(result[1].substring(0,1)); // bahan KA/KB/DA/DB
        final String lengan = lenganChecker(result[1].substring(1,2));
        final String harga = result[2]; // kode harga UHK/RUH
        final String size = sizeChecker(result[3]); // size S,M,L,XL,XXL

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Product");
        builder.setMessage("Delete Product : "+ code);
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //yes
                utilities.firebaseRoot.child("product/"
                        +bahan+"/"
                        +lengan+"/"
                        +harga+"/"
                        +code+"/"
                        +"quantityList/"
                        +size).
                        addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                dataSnapshot.getRef().setValue(dataSnapshot.getValue(Long.class)-1);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                Toast.makeText(productDelete.this, "Produk Terjual: "+code.toString()+" | "+ size.toString(), Toast.LENGTH_LONG).show();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //no
            }
        });
        builder.show();
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

    private String sizeChecker(String size){
        switch (size){
            case "s":
                return "0";
            case "m":
                return "1";
            case "l":
                return "2";
            case "xl":
                return "3";
            case "xxl":
                return "4";
            default:
                return "";
        }
    }
}
