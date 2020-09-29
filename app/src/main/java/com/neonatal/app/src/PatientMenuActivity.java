package com.neonatal.app.src;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.neonatal.app.src.classes.PatientPerson;
import com.neonatal.app.src.database.AppDatabase;
import com.neonatal.app.src.entity.Patient;
import com.neonatal.app.src.entity.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PatientMenuActivity extends DrawerActivity {
    Toolbar toolbar;
    NeonatalApp app;
    AppDatabase db;
    PatientPerson patient;

    final int DAY_LIMIT = 14;
    final int WEEK_LIMIT = 8;
    final int MONTH_LIMIT = 24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_patient_menu);

        super.onCreateDrawer();

        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_patient_menu);
        View inflated = stub.inflate();

        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Child");

        app = ((NeonatalApp) getApplicationContext());
        db = AppDatabase.getAppDatabase(getApplicationContext());

        refreshPatientData();
    }

    private void refreshPatientData() {
        patient = getPatientPerson();

        String patientFullName = patient.getFirstName() + " " + patient.getLastName();
        String age = calculateAge(patient.getBirthDate());
        String gestationalAge = calculateGestationalAge(patient.getGestationalStartDate());

        TextView textView_titleText = (TextView) findViewById(R.id.textView_titleText);
        TextView textView_sex = (TextView) findViewById(R.id.textView_sexValue);
        TextView textView_dob = (TextView) findViewById(R.id.textView_birthDateValue);
        TextView textView_age = (TextView) findViewById(R.id.textView_ageValue);
        TextView textView_gestationalAge = (TextView) findViewById(R.id.textView_gestationalAgeValue);

        textView_titleText.setText(patientFullName);
        textView_sex.setText(patient.getSex());
        textView_dob.setText(patient.getBirthDate());
        textView_age.setText(age);
        textView_gestationalAge.setText(gestationalAge);
    }

    private String calculateAge(String birthDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ageUnits = "days";
        Calendar dob = Calendar.getInstance();
        Calendar currentDate = Calendar.getInstance();

        try {
            dob.setTime(dateFormat.parse(birthDate));
        } catch (ParseException ex) {
            return "Invalid birth date";
        }

        long age = (currentDate.getTime().getTime() - dob.getTime().getTime()) / (1000 * 60 * 60 * 24);

        if (age >= DAY_LIMIT) {
            ageUnits = "weeks";
            age = age / 7;
            if (age > WEEK_LIMIT) {
                ageUnits = "months";

                if (currentDate.YEAR - dob.YEAR > 0) {
                    age = 12 * (currentDate.YEAR - dob.YEAR);
                    age += (currentDate.MONTH - dob.MONTH);
                }

                if (age > MONTH_LIMIT) {
                    ageUnits = "years";
                    age = age / 12;
                }
            }
        }

        return age + " " + ageUnits;
    }

    private String calculateGestationalAge(String gestationalStartDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ageUnits = "days";
        Calendar gsd = Calendar.getInstance();
        Calendar currentDate = Calendar.getInstance();

        try {
            gsd.setTime(dateFormat.parse(gestationalStartDate));
        } catch (ParseException ex) {
            return "Invalid birth date";
        }

        long age = (currentDate.getTime().getTime() - gsd.getTime().getTime()) / (1000 * 60 * 60 * 24 * 7);

        return age + " weeks";
    }

    private PatientPerson getPatientPerson() {
        Patient patient = db.patientDAO().getById(app.getCurrentPatient());
        Person person = db.personDAO().getById(patient.getPersonId());
        return new PatientPerson(patient, person);
    }

    //BEFORE
//    public void ViewHistory(View v){
//        Intent childIntent = new Intent(PatientMenuActivity.this, DataEntryActivity.class);
//        startActivity(childIntent);
//    }

    public void ViewHistory(View v){
        Intent childIntent = new Intent(PatientMenuActivity.this, PatientHistoryActivity.class);
        startActivity(childIntent);
    }

    public void ViewJournal(View v){
        startActivity(new Intent(PatientMenuActivity.this, JournalActivity.class));
    }

    public void ViewAppointments(View view) {
        //startActivity(new Intent(PatientMenuActivity.this, AppointmentActivity.class));
    }
}
