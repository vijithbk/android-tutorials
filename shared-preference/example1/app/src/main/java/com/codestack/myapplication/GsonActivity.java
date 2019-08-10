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

import com.google.gson.Gson;

import java.util.Arrays;

public class GsonActivity extends AppCompatActivity {
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
        Intent myIntent = new Intent(GsonActivity.this, LandingActivity.class);
        GsonActivity.this.startActivity(myIntent);
    }

    public void saveAccountData(View view) {
        Account account = getAccountObject();

        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        // Serialization
        Gson gson = new Gson();
        String jsonString = gson.toJson(account, Account.class);
        editor.putString(Constants.KEY_ACCOUNT, jsonString);
        editor.apply();

        this.loadAccountData(view);
    }

    public void clearAccountData(View view) {
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(Constants.KEY_ACCOUNT);
        editor.apply();

        // OR clear all the data
        // editor.clear();
        // editor.apply();

        this.loadAccountData(view);
    }

    public void loadAccountData(View view) {
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        String jsonString = sp.getString(Constants.KEY_ACCOUNT, "");
        Gson gson = new Gson();
        Account account = gson.fromJson(jsonString, Account.class);

        if(jsonString != "") {
            txtName.setText(account.getName());
            txtProfession.setText(account.getProfession());
        } else {
            txtName.setText("N/A");
            txtProfession.setText("N/A");
        }
    }

    private Account getAccountObject() {
        Account account = new Account();
        account.setName(etName.getText().toString());
        account.setProfession(etProfession.getText().toString());
        account.setRoles(Arrays.asList("Dev", "Admin"));
        return account;
    }
}
