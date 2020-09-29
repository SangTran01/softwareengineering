package com.neonatal.app.src;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;



import java.util.ArrayList;

public class DrawerActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ListView drawerList;
    public String[] layers;
    private ActionBarDrawerToggle drawerToggle;
    //private Person person = new Person();
    //private Patient patient = new Patient();
   // private Map map;

    protected void onCreateDrawer() {
        //TODO: This drawer is not accessible on the main screen of the app. We need to fix that
        // R.id.drawer_layout should be in every activity with exactly the same id.

        setContentView(R.layout.activity_drawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_drawer);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        drawerToggle = new ActionBarDrawerToggle((Activity) this, drawerLayout, toolbar, 0, 0)
        {
            public void onDrawerClosed(View view)
            {
                getSupportActionBar().setTitle(R.string.app_name);
            }

            public void onDrawerOpened(View drawerView)
            {
                getSupportActionBar().setTitle(R.string.menu);
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        layers = getResources().getStringArray(R.array.layers_array); //Drawer List
        drawerList = (ListView) findViewById(R.id.left_drawer);
        View header = getLayoutInflater().inflate(R.layout.drawer_list_header, null);
        drawerList.addHeaderView(header, null, false);
        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, android.R.id.text1,
                layers));
        View footerView = ((LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
                R.layout.drawer_list_footer, null, false);
        drawerList.addFooterView(footerView);

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
                //TODO: NAVIGATE BACK TO ROOT ACTIVITY (Main Menu/Login?) BEFORE OPENING NEW ONES
                switch (pos){
                    case 1:
                        startActivity(new Intent(DrawerActivity.this, MainMenuActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(DrawerActivity.this, VideoActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(DrawerActivity.this, PatientHistoryActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(DrawerActivity.this, GraphActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(DrawerActivity.this, JournalActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(DrawerActivity.this, EquipmentActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(DrawerActivity.this, TwitterActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(DrawerActivity.this, AboutActivity.class));
                        break;
                    default:
                        startActivity(new Intent(DrawerActivity.this, LoginActivity.class));
                        break;
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
}
