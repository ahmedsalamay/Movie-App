package com.example.ahmed.popularmoviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            Bundle args =new Bundle();
            args.putString(DetailActivityFragment.DETAIL_URI,getIntent()
                    .getStringExtra(Intent.EXTRA_TEXT));
            DetailActivityFragment fragment=new DetailActivityFragment();
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.detail_container, fragment)
                    .commit();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        if(id==R.id.action_settings){
            Intent settingIntent=new Intent(this,SettingsActivity.class);
            startActivity(settingIntent);
            return true;
        }
        if (id == android.R.id.home) {
            //   startActivity(new Intent(this, MainActivity.class));
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
