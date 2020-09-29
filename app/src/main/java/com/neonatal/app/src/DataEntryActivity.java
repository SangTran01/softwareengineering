package com.neonatal.app.src;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.neonatal.app.src.classes.Converters;
import com.neonatal.app.src.database.AppDatabase;
import com.neonatal.app.src.entity.DataEntry;
import com.neonatal.app.src.entity.DataField;
import com.neonatal.app.src.entity.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DataEntryActivity extends DrawerActivity {

    private ImageView mImageView;
    private Bitmap mImageBitmap;
    Calendar myCalendar = Calendar.getInstance();
    AppDatabase db = null;
    NeonatalApp app;

    EditText text_treatment = null;
    EditText text_Type = null;
    EditText text_Reason = null;
    EditText editTextact = null;

    Button btnSave;
    Button btnDelete;


    ////////////////////////////////////////////
    //height
    EditText editHeight = null;
    EditText editWeight = null;
    EditText editHead = null;
    EditText editTextDate = null;
    //weight
    ///////////////////////////////////////////

    long[] idField = null;
    String[] dataFielddescription = new String[]{"Height", "Weight", "Head"};
    String[] dataFieldUnit = new String[]{"cm", "lb", "cm"};


    private EditText[] fields = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreateDrawer();

        app = (NeonatalApp) getApplicationContext();

        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_data_entry);
        stub.inflate();
        //////////////////////////////database////////////////////////////////////////
        db = AppDatabase.getAppDatabase(getApplicationContext());
        /////////////////////////////elements////////////////////////////////////////
        CheckBox chbxTreatment = (CheckBox) findViewById(R.id.checkBox);
        text_treatment = (EditText) findViewById(R.id.editTextTreatment);
        text_Type = (EditText) findViewById(R.id.editTextType);
        text_Reason = (EditText) findViewById(R.id.editTextReason);
        editTextact = (EditText) findViewById(R.id.editTextHead);
        editHead = (EditText) findViewById(R.id.editTextHead);
        editHeight = (EditText) findViewById(R.id.editTextHeight);
        editWeight = (EditText) findViewById(R.id.editTextWeight);
        editTextDate = (EditText) findViewById(R.id.editTextJurnalDate);
        btnDelete = findViewById(R.id.button10);
        btnSave = findViewById(R.id.button9);


        chbxTreatment.setChecked(false);
        //comment this line out if you need treatment set
        chbxTreatment.setVisibility(View.GONE);
        text_treatment.setVisibility(View.GONE);
        text_Type.setVisibility(View.GONE);
        text_Reason.setVisibility(View.GONE);

        editTextact.requestFocus();

        fields = new EditText[]{
                editHeight,
                editWeight,
                editHead
        };

        Intent intent = getIntent();

        if (intent.hasExtra("dataEntryID")) {
            btnDelete = findViewById(R.id.button10);
            btnSave = findViewById(R.id.button9);

            btnSave.setText("Update");

            btnDelete.setText("Delete Entry");

            //SET edittext fields from db
            int dataEntryId = intent.getIntExtra("dataEntryID", -1);
            List<Event> events = db.eventDAO().getPatientsEvents(app.getCurrentPatient());
            DataEntry currentEntry = db.dataEntryDAO().getById(dataEntryId);
            List<DataEntry> entries = db.dataEntryDAO().getAll();

            editTextDate.setText(currentEntry.getDateEntered());
            ArrayList<String> values = Converters.fromString(currentEntry.getValues());
            editHeight.setText(values.get(0));
            editWeight.setText(values.get(1));
            editHead.setText(values.get(2));
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                if (intent.hasExtra("dataEntryID")) {
                    updateEntry();
                } else {
                    saveEntry();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEntry();
            }
        });

        List<DataField> dataFields_list = queryDataField(db);
        int index = 0;
        if (dataFields_list.size() > 0) {
            for (DataField df : dataFields_list) {
                if (index < dataFielddescription.length) {
                    String discription = dataFielddescription[index];
                    if (!discription.equals(df.getDescription())) {
                        df.setDescription(discription);
                        df.setDataType(dataFieldUnit[index]);
                        insertDataField(db, df);
                    }
                }
                index++;
            }
        } else {
            for (String discription : dataFielddescription) {
                DataField df = new DataField();
                //String discription  = dataFielddescription[index];
                df.setDescription(discription);
                df.setDataType(dataFieldUnit[index]);
                insertDataField(db, df);
                index++;
            }
        }
    }

    /////////BUTTON FUNCTIONS///////////////
    public void showTreatment(View view) {
        CheckBox chbxTreatment = (CheckBox) findViewById(R.id.checkBox);
        EditText text_treatment = (EditText) findViewById(R.id.editTextTreatment);
        EditText text_Type = (EditText) findViewById(R.id.editTextType);
        EditText text_Reason = (EditText) findViewById(R.id.editTextReason);

        if (!chbxTreatment.isChecked()) {
            text_treatment.setVisibility(View.GONE);
            text_Type.setVisibility(View.GONE);
            text_Reason.setVisibility(View.GONE);

        } else {
            text_treatment.setVisibility(View.VISIBLE);
            text_Type.setVisibility(View.VISIBLE);
            text_Reason.setVisibility(View.VISIBLE);
        }
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Some information from the data entry layout doesn't appear on screen.
            // TODO We need to add a button on this page to bring up the input screen. As of now you can only get to this screen by first inputting data every time you want to get here.
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };


    public void imgCameraAccess(View view) {

    }

    public void datepickerFunc(View view) {
        DatePickerDialog dpd = new DatePickerDialog(DataEntryActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));

        dpd.show();
    }

    private void updateLabel() {
        EditText editText = (EditText) findViewById(R.id.editTextJurnalDate);
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editText.setText(sdf.format(myCalendar.getTime()));
    }

    private void saveEntry(){
        EditText et_date = findViewById(R.id.editTextJurnalDate);
        EditText et_height = findViewById(R.id.editTextHeight);
        EditText et_weight = findViewById(R.id.editTextWeight);
        EditText et_head = findViewById(R.id.editTextHead);

        String date = et_date.getText().toString();
        String height = et_height.getText().toString();
        String weight = et_weight.getText().toString();
        String head = et_head.getText().toString();

        //Datafields
        //1 = height
        //2 = weight
        //3 = head circumference

        if (et_date.getText().toString().trim().isEmpty()
                || et_height.getText().toString().trim().isEmpty()
                || et_weight.getText().toString().trim().isEmpty()
                || et_head.getText().toString().trim().isEmpty()) {
            Toast.makeText(app, "Fields can't be empty.", Toast.LENGTH_SHORT).show();
        } else {
            Event currentEvent = new Event();
            currentEvent.setEventType("dataEntry");
            currentEvent.setEventDateTime(date);
            currentEvent.setEventChildId(app.getCurrentPatient());
            currentEvent.setPersonId(app.getCurrentUser());
            currentEvent.setUserId(app.getCurrentUser());

            List<Event> events = db.eventDAO().getPatientsEvents(app.getCurrentPatient());
            ArrayList<String> dates = new ArrayList<>();

            for (int i = 0; i < events.size(); i++) {
                dates.add(events.get(i).getEventDateTime());
            }

            boolean hasDate = dates.contains(date);

            if (!hasDate) {
                //add
                db.eventDAO().insertAll(currentEvent);
            }
            //else just add data entires
            int latestEventId = db.eventDAO().getEventByDate(date, app.getCurrentPatient()).getId();
            //int latestEventId = db.eventDAO().getAll().size();
            DataEntry dataEntry = new DataEntry();
            dataEntry.setDateEntered(date);
            dataEntry.setPatientId(app.getCurrentPatient());
            dataEntry.setEventId(latestEventId);

            ArrayList<String> values = new ArrayList<String>();
            values.add(height);
            values.add(weight);
            values.add(head);
            String newValues = Converters.fromArrayList(values);
            dataEntry.setValues(newValues);

            db.dataEntryDAO().insertAll(dataEntry);

            et_date.setText("");
            et_height.setText("");
            et_weight.setText("");
            et_head.setText("");

            startActivity(new Intent(this, PatientHistoryActivity.class));
        }
    }

    //TODO: call function on listitem click
    public void updateEntry() {
        //IF DATE change run saveDate to make new event and dataentry
        //IF entry deleted remove event if data entries for it are 0
        //Else update normally

        EditText et_date = findViewById(R.id.editTextJurnalDate);
        EditText et_height = findViewById(R.id.editTextHeight);
        EditText et_weight = findViewById(R.id.editTextWeight);
        EditText et_head = findViewById(R.id.editTextHead);

        String date = et_date.getText().toString();
        String height = et_height.getText().toString();
        String weight = et_weight.getText().toString();
        String head = et_head.getText().toString();

        if (et_date.getText().toString().trim().isEmpty()
                || et_height.getText().toString().trim().isEmpty()
                || et_weight.getText().toString().trim().isEmpty()
                || et_head.getText().toString().trim().isEmpty()) {
            Toast.makeText(app, "Fields can't be empty.", Toast.LENGTH_SHORT).show();
        } else {
            //CHECK DATE CHANGE
            Intent intent = getIntent();
            int dataEntryId = intent.getIntExtra("dataEntryID", -1);
            DataEntry currentEntry = db.dataEntryDAO().getById(dataEntryId);

            if (!currentEntry.getDateEntered().equals(date)) {
                //create new event and data entry
                //then delete current event
                Event currentEvent = new Event();
                currentEvent.setEventType("dataEntry");
                currentEvent.setEventDateTime(date);
                currentEvent.setEventChildId(app.getCurrentPatient());
                currentEvent.setPersonId(app.getCurrentUser());
                currentEvent.setUserId(app.getCurrentUser());

                List<Event> events = db.eventDAO().getPatientsEvents(app.getCurrentPatient());
                ArrayList<String> dates = new ArrayList<>();

                for (int i = 0; i < events.size(); i++) {
                    dates.add(events.get(i).getEventDateTime());
                }

                boolean hasDate = dates.contains(date);

                if (!hasDate) {
                    //add
                    db.eventDAO().insertAll(currentEvent);
                }
                //else just add data entires
                int latestEventId = db.eventDAO().getEventByDate(date, app.getCurrentPatient()).getId();
                //int latestEventId = db.eventDAO().getAll().size();
                DataEntry dataEntry = new DataEntry();
                dataEntry.setDateEntered(date);
                dataEntry.setPatientId(app.getCurrentPatient());
                dataEntry.setEventId(latestEventId);

                ArrayList<String> values = new ArrayList<String>();
                values.add(height);
                values.add(weight);
                values.add(head);
                String newValues = Converters.fromArrayList(values);
                dataEntry.setValues(newValues);

                db.dataEntryDAO().insertAll(dataEntry);

                et_date.setText("");
                et_height.setText("");
                et_weight.setText("");
                et_head.setText("");

                //NOW delete previous event and entry if 0 events
                db.dataEntryDAO().delete(currentEntry);

                //Now delete event if data entries for this event = 0;
                int eventId = intent.getIntExtra("eventID", -1);
                Event parentEvent = db.eventDAO().getEventById(eventId);
                int count = db.dataEntryDAO().getEntriesPerEvent(eventId);

                if (count == 0) {
                    db.eventDAO().delete(parentEvent);
                }
            } else {
                ArrayList<String> values = new ArrayList<String>();
                values.add(height);
                values.add(weight);
                values.add(head);
                String newValues = Converters.fromArrayList(values);

                currentEntry.setValues(newValues);

                db.dataEntryDAO().update(currentEntry);

                et_date.setText("");
                et_height.setText("");
                et_weight.setText("");
                et_head.setText("");
            }

            startActivity(new Intent(this, PatientHistoryActivity.class));
        }
    }

    private void deleteEntry() {
        Intent intent = getIntent();
        if (intent.hasExtra("eventID")) {
            int dataEntryId = intent.getIntExtra("dataEntryID", -1);
            DataEntry currentEntry = db.dataEntryDAO().getById(dataEntryId);
            db.dataEntryDAO().delete(currentEntry);

            //Now delete event if data entries for this event = 0;
            int eventId = intent.getIntExtra("eventID", -1);
//            Event parentEvent = db.eventDAO().getEventByChildIdAndUserId(app.getCurrentPatient(),
//                    app.getCurrentUser(), "dataEntry");

            Event parentEvent = db.eventDAO().getEventById(eventId);

            int count = db.dataEntryDAO().getEntriesPerEvent(eventId);

            if (count == 0) {
                db.eventDAO().delete(parentEvent);
            }

            startActivity(new Intent(this, PatientHistoryActivity.class));
        } else {
            //go back previous activity
            this.finish();
        }
    }

    private List<DataField> queryDataField(final AppDatabase db) {
        return db.dataFieldDAO().getAll();
    }

    private long[] insertDataField(final AppDatabase db, DataField df) {
        return db.dataFieldDAO().insertAll(df);
    }


    //@Override
    /*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }*/
}
