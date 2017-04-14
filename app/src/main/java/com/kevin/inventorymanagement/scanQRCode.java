package com.kevin.inventorymanagement;

import android.support.v7.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;

/**
 * Created by kevinsusanto on 4/13/17.
 */

public class scanQRCode extends AppCompatActivity{
    public void scanBarcode(){
        IntentIntegrator scanInit = new IntentIntegrator(this);
//        scanInit.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        scanInit.setPrompt("Scan");
        scanInit.setCameraId(0);
        scanInit.setBeepEnabled(false);
        scanInit.setBarcodeImageEnabled(false);
        scanInit.initiateScan();
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
