package com.codestack.myapplication;
/*
    This example is written by Vijith just for conceptual explanation.
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityLevelActivity extends AppCompatActivity {
    EditText etName;
    EditText etProfession;
    TextView txtName;
    TextView txtProfession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        etName = (EditText) findViewById(R.id.etName);
        etProfession = (EditText) findViewById(R.id.etProfession);
        txtName = (TextView) findViewById(R.id.txtName);
        txtProfession = (TextView) findViewById(R.id.txtProfession);
    }

    public void gotoHome(View view) {
        Intent myIntent = new Intent(ActivityLevelActivity.this, LandingActivity.class);
        ActivityLevelActivity.this.startActivity(myIntent);
    }

    public void saveAccountData(View view) {
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Constants.KEY_NAME, etName.getText().toString());
        editor.putString(Constants.KEY_PROFESSION, etProfession.getText().toString());
        editor.apply(); //editor.commit();

        this.loadAccountData(view);
    }

    public void clearAccountData(View view) {
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(Constants.KEY_NAME);
        editor.remove(Constants.KEY_PROFESSION);
        editor.apply();

        // OR clear all the data
        // editor.clear();
        // editor.apply();

        this.loadAccountData(view);
    }

    public void loadAccountData(View view) {
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        txtName.setText(sp.getString(Constants.KEY_NAME, "N/A"));
        txtProfession.setText(sp.getString(Constants.KEY_PROFESSION, "N/A"));
    }
}
