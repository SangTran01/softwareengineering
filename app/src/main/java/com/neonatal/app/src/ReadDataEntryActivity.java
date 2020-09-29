package com.neonatal.app.src;

import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.neonatal.app.src.database.AppDatabase;
import com.neonatal.app.src.entity.DataEntry;
import com.neonatal.app.src.entity.DataField;
import com.neonatal.app.src.entity.Event;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

public class ReadDataEntryActivity extends DrawerActivity {


    Calendar myCalendar = Calendar.getInstance();
    AppDatabase db = null;
    NeonatalApp app;

    private EditText[] fields = null;

    TextView height = null;
    TextView weight = null;
    TextView head = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_create_journal);
        super.onCreateDrawer();

        app = (NeonatalApp)getApplicationContext();

        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_data_lookup);
        View inflated = stub.inflate();

        height = findViewById(R.id.textViewHeight);
        weight = findViewById(R.id.textViewWeight);
        head = findViewById(R.id.textViewHead);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null){
                ArrayList<String> data = new ArrayList<String>();
                try{
                    data = (ArrayList<String>) extras.get("data");
                }catch (Exception ex){
                    data = null;
                }

                if(data.get(0) != null){
                    height.setText(data.get(0).toString());
                    weight.setText(data.get(1).toString());
                    head.setText(data.get(2).toString());
                }
            }
        }
    }



   /* private static User addUser(final AppDatabase db, User user) {
        db.userDAO().insertAll(user);
        return user;
    }

    private static long addPerson(final AppDatabase db, Person person) {
        return  db.personDAO().insertAll(person)[0];
    }*/

    //@Override
    /*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }*/
}
