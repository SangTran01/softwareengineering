package com.neonatal.app.src;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class EventsActivity extends DrawerActivity {
    //TODO: my not be needed
    Toolbar toolbar;
    String [] titles = {"Test", "test", "test"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_events);

        super.onCreateDrawer();
        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_events);
        View inflated = stub.inflate();

        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Different Events");

        Intent intent = getIntent();
        String date = intent.getStringExtra("date");


        TextView text = (TextView) findViewById(R.id.txtDate);
        text.setText("Events for: " + date);


        ListView listView = (ListView) findViewById(R.id.events);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titles);
        listView.setBackground(getDrawable(R.drawable.mybutton));
        listView.setAdapter(adapter);

    }
}
