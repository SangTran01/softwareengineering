package com.neonatal.app.src;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.neonatal.app.src.classes.Converters;
import com.neonatal.app.src.classes.PatientPerson;
import com.neonatal.app.src.database.AppDatabase;
import com.neonatal.app.src.entity.DataEntry;
import com.neonatal.app.src.entity.Event;
import com.neonatal.app.src.entity.Patient;
import com.neonatal.app.src.entity.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GraphActivity extends DrawerActivity {
    Toolbar toolbar;
    NeonatalApp app;
    AppDatabase db;
    PatientPerson patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreateDrawer();

        ViewStub stub = findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_graph);
        stub.inflate();

        app = ((NeonatalApp) getApplicationContext());
        db = AppDatabase.getAppDatabase(getApplicationContext());
        patient = getPatientPerson();

        List<Event> dataEntryEvents = db.eventDAO().getEventByChildIdASC(patient.getPatientId(), "dataEntry");
        List<DataEntry> entries = db.dataEntryDAO().getAll();

        GraphView gv_height = (GraphView) findViewById(R.id.graph_height);
        GraphView gv_weight = (GraphView) findViewById(R.id.graph_weight);
        GraphView gv_head = (GraphView) findViewById(R.id.graph_head);


        LineGraphSeries<DataPoint> lineHeights = new LineGraphSeries<DataPoint>();
        LineGraphSeries<DataPoint> lineWeights = new LineGraphSeries<DataPoint>();
        LineGraphSeries<DataPoint> lineHeads = new LineGraphSeries<DataPoint>();

        //GET Dates
        for (int i = 0; i < dataEntryEvents.size(); i++) {
            String strDate = dataEntryEvents.get(i).getEventDateTime();
            try {
                Date currentDate = new SimpleDateFormat("MM/dd/yyyy").parse(strDate);

                for (int j = 0; j < entries.size(); j++) {
                    if (strDate.equals(entries.get(j).getDateEntered())) {
                        String currentHeight = Converters.fromString(entries.get(j).getValues()).get(0);
                        String currentWeight = Converters.fromString(entries.get(j).getValues()).get(1);
                        String currentHead = Converters.fromString(entries.get(j).getValues()).get(2);

                        lineHeights.appendData(new DataPoint(currentDate,
                                Double.parseDouble(currentHeight)), false, entries.size());

                        lineWeights.appendData(new DataPoint(currentDate,
                                Double.parseDouble(currentWeight)), false, dataEntryEvents.size());

                        lineHeads.appendData(new DataPoint(currentDate,
                                Double.parseDouble(currentHead)), false, entries.size());
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        gv_height.addSeries(lineHeights);

        gv_height.setTitle("Height Progress");
        gv_height.setTitleTextSize(60);

        // set date label formatter
        gv_height.getGridLabelRenderer().setVerticalAxisTitle("Value (cm)");
        gv_height.getGridLabelRenderer().setHorizontalAxisTitle("Date");
        gv_height.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(gv_height.getContext()));
        gv_height.getGridLabelRenderer().setNumHorizontalLabels(entries.size());

        // set manual X bounds
        gv_height.getViewport().setYAxisBoundsManual(true);
        gv_height.getViewport().setMinY(lineHeights.getLowestValueY());
        gv_height.getViewport().setMaxY(lineHeights.getHighestValueY());

        gv_height.getViewport().setXAxisBoundsManual(true);
        gv_height.getViewport().setMinX(lineHeights.getLowestValueX());
        gv_height.getViewport().setMaxX(lineHeights.getHighestValueX());

        // enable scaling and scrolling
        gv_height.getViewport().setScrollable(true); // enables horizontal scrolling
        gv_height.getViewport().setScrollableY(true); // enables vertical scrolling
        gv_height.getViewport().setScalable(true);
        gv_height.getViewport().setScalableY(true);

        // as we use dates as labels, the human rounding to nice readable numbers
        // is not nessecary
        gv_height.getGridLabelRenderer().setHumanRounding(false);

        gv_height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent heightIntent = new Intent(getApplicationContext(), GraphFullScreenActivity.class);
                heightIntent.putExtra("graph", "height");
                startActivity(heightIntent);
            }
        });

        //-----------

        gv_weight.addSeries(lineWeights);

        gv_weight.setTitle("Weight Progress");
        gv_weight.setTitleTextSize(60);

        //set date label formatter
        gv_weight.getGridLabelRenderer().setVerticalAxisTitle("Value (g)");
        gv_weight.getGridLabelRenderer().setHorizontalAxisTitle("Date");
        gv_weight.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(gv_weight.getContext()));
        gv_weight.getGridLabelRenderer().setNumHorizontalLabels(entries.size());

        // set manual x bounds to have nice steps
        gv_weight.getViewport().setMinX(lineWeights.getLowestValueX());
        gv_weight.getViewport().setMaxX(lineWeights.getHighestValueX());
        gv_weight.getViewport().setXAxisBoundsManual(true);

        // as we use dates as labels, the human rounding to nice readable numbers
        // is not nessecary
        gv_weight.getGridLabelRenderer().setHumanRounding(false);

        gv_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent weightIntent = new Intent(getApplicationContext(), GraphFullScreenActivity.class);
                weightIntent.putExtra("graph", "weight");
                startActivity(weightIntent);
            }
        });

        //-----------

        gv_head.addSeries(lineHeads);

        gv_head.setTitle("Head Circumference Progress");
        gv_head.setTitleTextSize(60);

        //set date label formatter
        gv_head.getGridLabelRenderer().setVerticalAxisTitle("Value (cm)");
        gv_head.getGridLabelRenderer().setHorizontalAxisTitle("Date");
        gv_head.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(gv_head.getContext()));
        gv_head.getGridLabelRenderer().setNumHorizontalLabels(entries.size());

        // set manual x bounds to have nice steps
        gv_head.getViewport().setMinX(lineHeads.getLowestValueX());
        gv_head.getViewport().setMaxX(lineHeads.getHighestValueX());
        gv_head.getViewport().setXAxisBoundsManual(true);

        // as we use dates as labels, the human rounding to nice readable numbers
        // is not nessecary
        gv_head.getGridLabelRenderer().setHumanRounding(false);

        gv_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent headIntent = new Intent(getApplicationContext(), GraphFullScreenActivity.class);
                headIntent.putExtra("graph", "head");
                startActivity(headIntent);
            }
        });
    }

    private PatientPerson getPatientPerson() {
        Patient patient = db.patientDAO().getById(app.getCurrentPatient());
        Person person = db.personDAO().getById(patient.getPersonId());
        return new PatientPerson(patient, person);
    }
}
