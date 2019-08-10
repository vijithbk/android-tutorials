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
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;

public class GenericActivity extends AppCompatActivity {
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
        Intent myIntent = new Intent(GenericActivity.this, LandingActivity.class);
        GenericActivity.this.startActivity(myIntent);
    }

    public void saveAccountData(View view) {
        Account account = getAccountObject();
        DataSum<Account> dataSum = new DataSum<>();
        dataSum.setObject(account);

        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        // Serialization
        Gson gson = new Gson();
        Type type = new TypeToken<DataSum<Account>>(){}.getType();
        String jsonString = gson.toJson(dataSum, type);
        editor.putString(Constants.KEY_GENERIC_DATA, jsonString);
        editor.apply();

        this.loadAccountData(view);
    }

    public void clearAccountData(View view) {
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(Constants.KEY_GENERIC_DATA);
        editor.apply();

        // OR clear all the data
        // editor.clear();
        // editor.apply();

        this.loadAccountData(view);
    }

    public void loadAccountData(View view) {
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        String jsonString = sp.getString(Constants.KEY_GENERIC_DATA, "");
        Gson gson = new Gson();
        Type type = new TypeToken<DataSum<Account>>(){}.getType();
        if(jsonString != null) {
            DataSum<Account> accountDataSum = gson.fromJson(jsonString, type);
            if(accountDataSum != null) {
                Account account = accountDataSum.getObject();
                if (account != null) {
                    String name = account.getName();
                    String profession = account.getProfession();
                    txtName.setText(name);
                    txtProfession.setText(profession);
                } else {
                    txtName.setText("N/A");
                    txtProfession.setText("N/A");
                }
            } else {
                txtName.setText("N/A");
                txtProfession.setText("N/A");
            }
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
