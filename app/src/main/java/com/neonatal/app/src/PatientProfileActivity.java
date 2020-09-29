package com.neonatal.app.src;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.neonatal.app.src.database.AppDatabase;
import com.neonatal.app.src.entity.Patient;
import com.neonatal.app.src.entity.Person;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PatientProfileActivity extends DrawerActivity {
    NeonatalApp app;
    AppDatabase db;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreateDrawer();

        app = ((NeonatalApp) getApplicationContext());
        db = AppDatabase.getAppDatabase(getApplicationContext());

        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_patient_profile);
        View inflated = stub.inflate();

        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Baby Profile");


        DatePicker datePicker_birthDate = (DatePicker) findViewById(R.id.datePicker_birthDate);
        datePicker_birthDate.setMaxDate(Calendar.getInstance().getTime().getTime());
    }

    public void createNewPatient(View view) {
        if (validateForm()) {

            try {
                Patient patient = new Patient();
                Person person = new Person();

                EditText editText_firstName = (EditText) findViewById(R.id.editText_firstName);
                EditText editText_lastName = (EditText) findViewById(R.id.editText_lastName);
                Spinner spinner_sex = (Spinner) findViewById(R.id.spinner_sex);
                DatePicker datePicker_birthDate = (DatePicker) findViewById(R.id.datePicker_birthDate);
                EditText editText_gestationalAgeAtBirth = (EditText) findViewById(R.id.editText_gestationalAgeAtBirth);

                //Birth Date
                Calendar birthDateCalendar = Calendar.getInstance();
                int year = datePicker_birthDate.getYear();
                int month = datePicker_birthDate.getMonth();
                int day = datePicker_birthDate.getDayOfMonth();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                birthDateCalendar.set(year, month, day);

                //Gestational Start Date
                Calendar gestationalStartCalendar = Calendar.getInstance();
                gestationalStartCalendar.set(year, month, day);
                int gestationalAgeAtBirth = Integer.parseInt(editText_gestationalAgeAtBirth.getText().toString());
                gestationalStartCalendar.add(Calendar.DAY_OF_YEAR, -(7 * gestationalAgeAtBirth));

                //Save to DB
                person.setFirstName(editText_firstName.getText().toString());
                person.setLastName(editText_lastName.getText().toString());
                person.setBirthDate(dateFormat.format(birthDateCalendar.getTime()));
                person.setSex(spinner_sex.getSelectedItem().toString());
                int personId = (int)db.personDAO().insertAll(person)[0];

                patient.setUserId(app.getCurrentUser());
                patient.setPersonId(personId);
                patient.setGestationalStartDate(dateFormat.format(gestationalStartCalendar.getTime()));
                db.patientDAO().insertAll(patient);

                this.finish();
            } catch (Exception ex) {
                Toast.makeText(this, "Please ensure all fields are filled out", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validateForm() {
        final int MINIMUM_GESTATIONAL_AGE = 0;

        // First Name
        EditText editText_firstName = (EditText) findViewById(R.id.editText_firstName);
        if (editText_firstName.getText().toString().length() <= 0) {
            Toast.makeText(this, "First Name is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        //Gestational Age
        EditText editText_gestationalAge = (EditText) findViewById(R.id.editText_gestationalAgeAtBirth);
        try {
            int gestationalAge = Integer.parseInt(editText_gestationalAge.getText().toString());
            if (gestationalAge < MINIMUM_GESTATIONAL_AGE) {
                Toast.makeText(this, "Gestational Age must be greater than " + MINIMUM_GESTATIONAL_AGE + " weeks", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (NumberFormatException ex) {
            Toast.makeText(this, "Gestational Age must be a valid integer", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}
