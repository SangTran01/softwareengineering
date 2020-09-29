package com.neonatal.app.src;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.neonatal.app.src.adapters.MilestoneAdapter;
import com.neonatal.app.src.database.AppDatabase;
import com.neonatal.app.src.entity.Event;
import com.neonatal.app.src.entity.JournalEntry;
import com.neonatal.app.src.entity.Milestone;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CreateJournalActivity extends DrawerActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    NeonatalApp app;
    AppDatabase db;

    private ImageView mImageView;
    private Bitmap mImageBitmap;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreateDrawer();

        app = ((NeonatalApp) getApplicationContext());
        db = AppDatabase.getAppDatabase(getApplicationContext());

        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_create_journal);
        View inflated = stub.inflate();

        ImageView img = findViewById(R.id.photoView);
        img.setOnClickListener(this);

        ArrayList<String> milestones = new ArrayList(db.milestoneDAO().getAllMilestoneCatagories());

        SpinnerAdapter categories = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, milestones);

        Spinner spinner_milestone_cat = (Spinner) findViewById(R.id.spinner_milestone2);
        spinner_milestone_cat.setOnItemSelectedListener(this);
        spinner_milestone_cat.setAdapter(categories);
        spinner_milestone_cat.setSelection(1);

    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };


    public void imgCameraAccess(View view) {

    }

    public void pickDate(View view) {
        new DatePickerDialog(CreateJournalActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateLabel() {
        EditText editText = (EditText)findViewById(R.id.editText_journalDate) ;
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editText.setText(sdf.format(myCalendar.getTime()));
    }

    public void save(View view) {
        if (validateForm()) {
            EditText editText_bodyText = (EditText) findViewById(R.id.editText_bodyText);
            EditText editText_journalDate = (EditText) findViewById(R.id.editText_journalDate);
            Spinner spinner_milestone = (Spinner) findViewById(R.id.spinner_milestone);

            JournalEntry journalEntry = new JournalEntry();
            if(mImageBitmap != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                mImageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                journalEntry.setImageString(encoded);
            }else{
                journalEntry.setImageString(null);
            }
            journalEntry.setBodyText(editText_bodyText.getText().toString());
            journalEntry.setMilestoneId(((Milestone)spinner_milestone.getSelectedItem()).getId());
            journalEntry.setMilestoneName(((Milestone)spinner_milestone.getSelectedItem()).getDescription());
            int journalEntryId = (int) db.journalEntryDAO().insertAll(journalEntry)[0];

            Event event = new Event();
            event.setEventDateTime(editText_journalDate.getText().toString());
            event.setPersonId(db.patientDAO().getById(app.getCurrentPatient()).getPersonId());
            event.setEventType("JournalEntry");
            event.setEventChildId(journalEntryId);
            db.eventDAO().insertAll(event);

            this.finish();
        }
    }

    private boolean validateForm() {
        //Date
        EditText editText_journalDate = (EditText) findViewById(R.id.editText_journalDate);
        if (editText_journalDate.getText().toString().length() <= 0) {
            Toast.makeText(this, "Journal date is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        //BodyText
        EditText editText_bodyText = (EditText) findViewById(R.id.editText_bodyText);
        if (editText_bodyText.getText().toString().length() <= 0) {
            Toast.makeText(this, "Journal body text is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Object item = parent.getItemAtPosition(position);
        ArrayList<Milestone> milestones = new ArrayList(db.milestoneDAO().getAllMilestonesInCatagory(item.toString()));
        MilestoneAdapter adapter = new MilestoneAdapter(this, android.R.layout.simple_spinner_item, milestones);
        Spinner spinner_milestone = (Spinner) findViewById(R.id.spinner_milestone);
        spinner_milestone.setAdapter(adapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //Not Implemented. Something should always be selected
    }

    @Override
    public void onClick(View view) {
        PackageManager pm = this.getPackageManager();
        switch(view.getId()){
            case R.id.photoView:
                if(pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT) || pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)){
                    dispatchTakePictureIntent();
                }
                break;
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            if (checkSelfPermission(Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA},1);
            }else {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
             mImageBitmap = (Bitmap) extras.get("data");
            ImageView imageView = findViewById(R.id.photoView);
            imageView.setImageBitmap(mImageBitmap);
        }
    }
}
