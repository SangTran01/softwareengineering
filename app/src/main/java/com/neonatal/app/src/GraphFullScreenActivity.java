package com.neonatal.app.src;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
import java.util.Date;
import java.util.List;

public class GraphFullScreenActivity extends AppCompatActivity {

    NeonatalApp app;
    AppDatabase db;
    PatientPerson patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_full_screen_activity);

        app = ((NeonatalApp) getApplicationContext());
        db = AppDatabase.getAppDatabase(getApplicationContext());
        patient = getPatientPerson();

        List<Event> dataEntryEvents = db.eventDAO().getEventByChildIdASC(patient.getPatientId(), "dataEntry");
        List<DataEntry> entries = db.dataEntryDAO().getAll();

        GraphView gv_fullscreen = (GraphView) findViewById(R.id.graph_fullscreen);

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

        Intent intent = getIntent();
        String graphview = intent.getStringExtra("graph");

        if (graphview.equals("height")) {
            gv_fullscreen.addSeries(lineHeights);

            gv_fullscreen.setTitle("Height Progress");
            gv_fullscreen.setTitleTextSize(60);

            // set date label formatter
            gv_fullscreen.getGridLabelRenderer().setVerticalAxisTitle("Value (cm)");
            gv_fullscreen.getGridLabelRenderer().setHorizontalAxisTitle("Date");
            gv_fullscreen.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(gv_fullscreen.getContext()));
            gv_fullscreen.getGridLabelRenderer().setNumHorizontalLabels(entries.size());

            // set manual X bounds
            gv_fullscreen.getViewport().setYAxisBoundsManual(true);
            gv_fullscreen.getViewport().setMinY(lineHeights.getLowestValueY());
            gv_fullscreen.getViewport().setMaxY(lineHeights.getHighestValueY());

            gv_fullscreen.getViewport().setXAxisBoundsManual(true);
            gv_fullscreen.getViewport().setMinX(lineHeights.getLowestValueX());
            gv_fullscreen.getViewport().setMaxX(lineHeights.getHighestValueX());
        } else if (graphview.equals("weight")) {
            gv_fullscreen.addSeries(lineWeights);

            gv_fullscreen.setTitle("Weight Progress");
            gv_fullscreen.setTitleTextSize(60);

            // set date label formatter
            gv_fullscreen.getGridLabelRenderer().setVerticalAxisTitle("Value (lb)");
            gv_fullscreen.getGridLabelRenderer().setHorizontalAxisTitle("Date");
            gv_fullscreen.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(gv_fullscreen.getContext()));
            gv_fullscreen.getGridLabelRenderer().setNumHorizontalLabels(entries.size());

            // set manual X bounds
            gv_fullscreen.getViewport().setYAxisBoundsManual(true);
            gv_fullscreen.getViewport().setMinY(lineWeights.getLowestValueY());
            gv_fullscreen.getViewport().setMaxY(lineWeights.getHighestValueY());

            gv_fullscreen.getViewport().setXAxisBoundsManual(true);
            gv_fullscreen.getViewport().setMinX(lineWeights.getLowestValueX());
            gv_fullscreen.getViewport().setMaxX(lineWeights.getHighestValueX());

        } else if (graphview.equals("head")) {
            gv_fullscreen.addSeries(lineHeads);

            gv_fullscreen.setTitle("Head Circumference Progress");
            gv_fullscreen.setTitleTextSize(60);

            // set date label formatter
            gv_fullscreen.getGridLabelRenderer().setVerticalAxisTitle("Value (cm)");
            gv_fullscreen.getGridLabelRenderer().setHorizontalAxisTitle("Date");
            gv_fullscreen.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(gv_fullscreen.getContext()));
            gv_fullscreen.getGridLabelRenderer().setNumHorizontalLabels(entries.size());

            // set manual X bounds
            gv_fullscreen.getViewport().setYAxisBoundsManual(true);
            gv_fullscreen.getViewport().setMinY(lineHeads.getLowestValueY());
            gv_fullscreen.getViewport().setMaxY(lineHeads.getHighestValueY());

            gv_fullscreen.getViewport().setXAxisBoundsManual(true);
            gv_fullscreen.getViewport().setMinX(lineHeads.getLowestValueX());
            gv_fullscreen.getViewport().setMaxX(lineHeads.getHighestValueX());
        }

        // enable scaling and scrolling
        gv_fullscreen.getViewport().setScrollable(true); // enables horizontal scrolling
        gv_fullscreen.getViewport().setScrollableY(true); // enables vertical scrolling
        gv_fullscreen.getViewport().setScalable(true);
        gv_fullscreen.getViewport().setScalableY(true);

        // as we use dates as labels, the human rounding to nice readable numbers
        // is not nessecary
        gv_fullscreen.getGridLabelRenderer().setHumanRounding(false);
    }

    private PatientPerson getPatientPerson() {
        Patient patient = db.patientDAO().getById(app.getCurrentPatient());
        Person person = db.personDAO().getById(patient.getPersonId());
        return new PatientPerson(patient, person);
    }
}
