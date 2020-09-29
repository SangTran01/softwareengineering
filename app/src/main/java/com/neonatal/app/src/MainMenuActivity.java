package com.neonatal.app.src;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ListView;

import com.neonatal.app.src.adapters.PatientPersonAdapter;
import com.neonatal.app.src.classes.PatientPerson;
import com.neonatal.app.src.database.AppDatabase;
import com.neonatal.app.src.entity.Patient;
import com.neonatal.app.src.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class MainMenuActivity extends DrawerActivity implements ListView.OnItemClickListener {
    Toolbar toolbar;
    NeonatalApp app;
    AppDatabase db;
    ArrayList<PatientPerson> arrayPatients;
    PatientPersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main_menu);

        super.onCreateDrawer();

        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_main_menu);
        View inflated = stub.inflate();

        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Menu");


        app = ((NeonatalApp) getApplicationContext());
        db = AppDatabase.getAppDatabase(getApplicationContext());

        refreshPatientList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshPatientList();
    }

    public void refreshPatientList() {
        arrayPatients = getPatientsByUserId(app.getCurrentUser());

        adapter = new PatientPersonAdapter(this, android.R.layout.simple_list_item_1, arrayPatients);
        ListView listViewPatients = (ListView) findViewById(R.id.listViewPatients);
        listViewPatients.setAdapter(adapter);
        listViewPatients.setOnItemClickListener(this);
    }

    private ArrayList<PatientPerson> getPatientsByUserId(int userId) {
        List<Patient> patients = db.patientDAO().getByUserId(userId);
        Person person;
        ArrayList<PatientPerson> result = new ArrayList<>();

        for (Patient patient : patients) {
            person = db.personDAO().getById(patient.getPersonId());
            PatientPerson patientPerson = new PatientPerson(patient, person);
            result.add(patientPerson);
        }

        return result;
    }

    public void NicuVideo(View v){
        startActivity(new Intent(MainMenuActivity.this, VideoActivity.class));
        //this.finish();
    }

    public void NewPatient(View v){
        startActivity(new Intent(MainMenuActivity.this, PatientProfileActivity.class));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (! (position > arrayPatients.size()))
        {
            Intent displayIntent = new Intent(this, PatientMenuActivity.class);
            app.setCurrentPatient(arrayPatients.get(position).getPatientId());
            startActivity(displayIntent);
        }
    }

    public void openPDF(View view) {
        String url = "http://199.212.121.215/sitemaker/websitefiles/hamhscon10071/documents/Patient%20Education/NeonatalNurseriesWelcome-lw.pdf";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
