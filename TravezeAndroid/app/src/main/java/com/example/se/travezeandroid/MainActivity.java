package com.example.se.travezeandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    MyPreference myPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myPreference = MyPreference.getInstance(getApplicationContext());

        if (!myPreference.isLoggedIn()){
            myPreference.startLoginActivity();
            finish();
        }

        Intent intent = new Intent(this,TestActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);


    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        Log.i("Main activity","menu item clicked");

        if (id == R.id.action_my_account) {
            myPreference.startMyAccountActivity();
        } else if(id == R.id.action_logout) {
            Log.i("Menu Item","logout");
            myPreference.logout();
            myPreference.startLoginActivity();
        } else if(id == R.id.action_login){
            myPreference.startLoginActivity();
        } else if(id == R.id.action_admin){
            myPreference.startAdminActivity();
        } else if(id == R.id.action_manager){
            myPreference.startManagerActivity();
        }


        return super.onOptionsItemSelected(item);
    }

}
