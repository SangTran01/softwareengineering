package com.neonatal.app.src;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.neonatal.app.src.database.AppDatabase;
import com.neonatal.app.src.entity.Person;
import com.neonatal.app.src.entity.User;

import java.util.Dictionary;
import java.util.Hashtable;

public class RegisterActivity extends AppCompatActivity {

    Dictionary<String, EditText> inputs = new Hashtable<>();
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputs.put("username",(EditText) findViewById(R.id.editText_username));
        inputs.put("password",(EditText) findViewById(R.id.editText_password));
        inputs.put("fname",(EditText) findViewById(R.id.editText_firstName));
        inputs.put("lname",(EditText) findViewById(R.id.editText_lastName));
        inputs.put("email",(EditText) findViewById(R.id.editText_email));
        inputs.put("phone",(EditText) findViewById(R.id.editText_phone));

        db = AppDatabase.getAppDatabase(getApplicationContext());
    }

    public void Register(View v){
        if (validateForm()) {
            Person person = new Person();
            person.setFirstName(inputs.get("fname").getText().toString());
            person.setLastName(inputs.get("lname").getText().toString());
            person.setEmail(inputs.get("email").getText().toString());
            person.setPhone(inputs.get("phone").getText().toString());
            int personId = (int) db.personDAO().insertAll(person)[0];

            User user = new User();
            user.setPersonId(personId);
            user.setUsername(inputs.get("username").getText().toString());
            user.setPassword(inputs.get("password").getText().toString());
            db.userDAO().insertAll(user);

            this.finish();
        }
    }


    private static User addUser(final AppDatabase db, User user) {
        db.userDAO().insertAll(user);
        return user;
    }

    private static long addPerson(final AppDatabase db, Person person) {
        return  db.personDAO().insertAll(person)[0];
    }

    private boolean validateForm(){
        final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        final String PHONE_REGEX = "^\\d{3}-\\d{3}-\\d{4}$";

        //Username
        if (inputs.get("username").getText().toString().length() < 6) {
            Toast.makeText(this, "Username must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return false;
        }
        User existingUser = db.userDAO().getUserByUserName(inputs.get("username").getText().toString());
        if (existingUser != null) {
            Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
            return false;
        }

        //Password
        if (inputs.get("password").getText().toString().length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return false;
        }

        //First Name
        if (inputs.get("fname").getText().toString().length() <= 0) {
            Toast.makeText(this, "First name is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        //Email
        String email = inputs.get("email").getText().toString();
        if (email.length() > 0) {
            if (!email.matches(EMAIL_REGEX)) {
                Toast.makeText(this, "Wrong email format (Email is not required)", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        //Phone
        String phone = inputs.get("phone").getText().toString();
        if (phone.length() > 0) {
            if (!phone.matches(PHONE_REGEX)) {
                Toast.makeText(this, "Wrong phone number format (Phone is not required)", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }
}
