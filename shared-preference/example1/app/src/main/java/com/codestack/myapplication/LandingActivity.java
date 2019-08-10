package com.codestack.myapplication;
/*
    This example is written by Vijith just for conceptual explanation.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
    }

    public void openActivityLevelSPPage(View view) {
        Intent myIntent = new Intent(LandingActivity.this, ActivityLevelActivity.class);
        LandingActivity.this.startActivity(myIntent);
    }

    public void openApplicationLevelSPPage(View view) {
        Intent myIntent = new Intent(LandingActivity.this, ApplicationLevelActivity.class);
        LandingActivity.this.startActivity(myIntent);
    }

    public void openGenericSPPage(View view) {
        Intent myIntent = new Intent(LandingActivity.this, GenericActivity.class);
        LandingActivity.this.startActivity(myIntent);
    }

    public void openGsonSPPage(View view) {
        Intent myIntent = new Intent(LandingActivity.this, GsonActivity.class);
        LandingActivity.this.startActivity(myIntent);
    }
}
