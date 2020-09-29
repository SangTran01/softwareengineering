package com.neonatal.app.src;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.neonatal.app.src.adapters.HistoryAdapter;
import com.neonatal.app.src.classes.Converters;
import com.neonatal.app.src.classes.PatientDatesEvents;
import com.neonatal.app.src.classes.PatientPerson;
import com.neonatal.app.src.database.AppDatabase;
import com.neonatal.app.src.entity.DataEntry;
import com.neonatal.app.src.entity.DataField;
import com.neonatal.app.src.entity.Event;
import com.neonatal.app.src.entity.Patient;
import com.neonatal.app.src.entity.Person;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientHistoryActivity extends DrawerActivity {

    //TODO: display some kind of data chart to show babies progress.
    //TODO: Also this doesn't show at all. This page needs to come up below the data lookup
    Toolbar toolbar;
    NeonatalApp app;
    AppDatabase db;
    PatientPerson patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.onCreateDrawer();

        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_patient_history);
        stub.inflate();

        app = ((NeonatalApp) getApplicationContext());
        db = AppDatabase.getAppDatabase(getApplicationContext());

        patient = getPatientPerson();

        List<Event> events = db.eventDAO().getAll();
        List<Event> dataEntryEvents = db.eventDAO().getEventByChildId(patient.getPatientId(), "dataEntry");


        List<DataEntry> entries = db.dataEntryDAO().getAll();

        ArrayList<PatientDatesEvents> datesEvents = new ArrayList<>();


        for (int i = 0; i < dataEntryEvents.size(); i++) {
            //get dates
            String date = dataEntryEvents.get(i).getEventDateTime();
            String eventType = dataEntryEvents.get(i).getEventType();
            //Add Date divider
            datesEvents.add(new PatientDatesEvents(true, date, dataEntryEvents.get(i).getId()));

            List<DataEntry> entriesByDate = db.dataEntryDAO().getByPatientAndDate(patient.getPatientId(), date);

            for (int j = 0; j < entriesByDate.size(); j++) {
                //Adding entries for each Event
                datesEvents.add(new PatientDatesEvents(false, entriesByDate.get(j)));
            }
        }

        //Datafields
        //1 = height
        //2 = weight
        //3 = head circumference

        String patientFullName = patient.getFirstName() + " " + patient.getLastName();

        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Patient History");


        TextView txtName = findViewById(R.id.txtName);
        txtName.setText("Patient: " + patientFullName);

        ListView lstHistory = findViewById(R.id.lstHistory);

        HistoryAdapter adapter = new HistoryAdapter(PatientHistoryActivity.this, R.layout.patient_date_list_item, datesEvents);
        lstHistory.setAdapter(adapter);

        ImageButton img_chart = findViewById(R.id.img_chart);
        img_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientHistoryActivity.this, ChartActivity.class));
            }
        });
    }

    private PatientPerson getPatientPerson() {
        Patient patient = db.patientDAO().getById(app.getCurrentPatient());
        Person person = db.personDAO().getById(patient.getPersonId());
        return new PatientPerson(patient, person);
    }

    public void AddEntry(View view) {
        Intent intent = new Intent(PatientHistoryActivity.this, DataEntryActivity.class);
        startActivity(intent);
    }
}
