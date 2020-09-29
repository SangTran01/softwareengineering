package com.neonatal.app.src;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewStub;
import android.widget.CalendarView;
import android.widget.Toast;

public class CalendarViewActivity extends DrawerActivity implements CalendarView.OnDateChangeListener{

    View inflated = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_calendar);
        super.onCreateDrawer();
        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_calendar);
        inflated = stub.inflate();

        CalendarView calendar = (CalendarView) findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(this);



    }


    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//        TextView testText = (TextView) findViewById(R.id.testDateSelect);
//        testText.setText("" + year + "/" + month + "/" + dayOfMonth);

        Intent intent = new Intent(this, EventsActivity.class);
        String dateString = "" + year + "/" + month + "/" + dayOfMonth;

        intent.putExtra("date", dateString);

        startActivity(intent);
    }


    public void linkToMaster(View view) {
        final Snackbar snackbar = Snackbar.make(inflated, "TEST", Snackbar.LENGTH_LONG);
        snackbar.setAction("Dismiss", new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }
}
