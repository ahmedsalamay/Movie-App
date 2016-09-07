package com.example.ahmed.popularmoviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity  extends AppCompatActivity implements MainActivityFragment.Callback {
    private final static  String DETAILFRAGMENT_TAG="DFTAG";
    private boolean  mTwoPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(findViewById(R.id.detail_container)!=null){
            mTwoPane=true;

            if(savedInstanceState==null){
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.detail_container,new DetailActivityFragment()
                                ,DETAILFRAGMENT_TAG)
                        .commit();
            }else{
                mTwoPane=false;
            }
        }
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
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(String uriString) {
        if(mTwoPane){
            Bundle args=new Bundle();
            args.putString(DetailActivityFragment.DETAIL_URI,uriString);
            DetailActivityFragment fragment =new DetailActivityFragment();
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.detail_container,fragment,
                    DETAILFRAGMENT_TAG).commit();

        }else{
            Intent intent=new Intent(this,DetailActivity.class)
                    .putExtra(Intent.EXTRA_TEXT,uriString);
            startActivity(intent);
        }
    }
}
