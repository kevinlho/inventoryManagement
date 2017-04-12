package com.kevin.inventorymanagement;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Kevin on 4/7/2017.
 */

public class utilities extends AppCompatActivity {

    //firebase Connection
    private final static FirebaseDatabase firebase = FirebaseDatabase.getInstance();
    public final static DatabaseReference firebaseRoot = firebase.getReference();

    public ProgressDialog mProgressDialog;

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public static void goTo(Context currentActivity, Class selectedClass){
        final Intent myIntent = new Intent(currentActivity,selectedClass);
        currentActivity.startActivity(myIntent);
    }
}
