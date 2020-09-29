package com.neonatal.app.src;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.neonatal.app.src.database.AppDatabase;
import com.neonatal.app.src.entity.Event;
import com.neonatal.app.src.entity.JournalEntry;
import com.neonatal.app.src.entity.Milestone;
import com.neonatal.app.src.entity.Person;
import com.neonatal.app.src.entity.User;

import java.util.ArrayList;
import java.util.List;

//TODO: We may want to integrate some other kind of login service if said login service will allow us to save some data. We can talk about.
public class LoginActivity extends AppCompatActivity {
    NeonatalApp app;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        app = ((NeonatalApp) getApplicationContext());
        db = AppDatabase.getAppDatabase(getApplicationContext());

        if(db.userDAO().getUserByUserName("admin") == null)
        {
            populateWithTestData(db);
        }


        //EditText editText = (EditText) findViewById(R.id.txt_Username);
        //editText.setText(getDatabasePath("neonatal-database").getAbsolutePath());
    }

    public void Register(View v){
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    private static User getUserByUsername(AppDatabase db, String username) {
        return db.userDAO().getUserByUserName(username);
    }

    //POPULATE WITH TEST DATA
    //==============================================================================================
    private static User addUser(final AppDatabase db, User user) {
        db.userDAO().insertAll(user);
        return user;
    }

    private static Person addPerson(final AppDatabase db, Person person) {
        db.personDAO().insertAll(person);
        return person;
    }

    private static List<Milestone> addMilestones(final AppDatabase db, List<Milestone> milestones) {
        db.milestoneDAO().insertAll(milestones.toArray(new Milestone[milestones.size()]));
        return milestones;
    }

    private static Event addEvent(final AppDatabase db, Event event){
        db.eventDAO().insertAll(event);
        return event;
    }

    private static JournalEntry addJournalEntry (final AppDatabase db, JournalEntry journalEntry) {
        db.journalEntryDAO().insertAll(journalEntry);
        return journalEntry;
    }

    private static void populateWithTestData(AppDatabase db) {


        //User
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setPersonId(1);
        addUser(db, user);

        //Person
        Person person = new Person();
        person.setFirstName("Mother");
        person.setLastName("Goose");
        person.setEmail("mgoose@hotmail.com");
        person.setPhone("905-777-7777");
        addPerson(db, person);

        /*//Event
        Event event = new Event();
        event.setEventDateTime("2017-12-21 13:14:15");
        event.setEventType("JournalEntry");
        event.setPersonId(1);
        event.setEventChildId(1);
        addEvent(db, event);

        //Journal Entry
        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setBodyText("Today Susan had her first breastfeeding!");
        journalEntry.setMilestoneId(1);
        addJournalEntry(db, journalEntry);*/

        //Milestone
        String[] milestoneDescriptions1 = {
                //TODO: Restructure the Milestone entity class to fit these into 2 different drop downs
                //Breathing Related
                "Was able to breathe on my own",
                "Came off oxygen",
                "Got rid of my breathing tube",
                "Came off CPAP",
                "Came off high flow"
        };
        String[] milestoneDescriptions2 = {
                //Beds,Seats and Swings Related
                "Moved to a crib ",
                "Sat in a bouncy chair",
                "Sat in a swing",
                "Went for a stroller ride"
        };
        String[] milestoneDescriptions3 = {
                //Cuddles Related
                "Was held by mum or dad for the first time",
                "Did my first kangaroo care",
                "Had my first cuddle with mom or dad",
                "Met a family member/friend",
        };
        String[] milestoneDescriptions4 = {
                //Growth Related
                "Weigh over 1kg\n",
                "Weigh over 2kg",
                "Weigh over 3kg",
                "Weigh over 4kg"
        };
        String[] milestoneDescriptions5 = {
                //Transitions Related
                "Graduated to the level 2 nursery",
                "Went to care by parent",
                "Went home",
                "Today is my due date"
        };
        String[] milestoneDescriptions6 = {
                //Activites of Daily Living Related
                "Opened my eyes ",
                "Wore clothes",
                "Had my first smile",
                "Had my first bath",
                "Started tummy time",
                "Had my first haircut"
        };
        String[] milestoneDescriptions7 = {
                //Feeding Related
                "Started sucking on my soother",
                "Graduated to the term soother",
                "Had milk for the first time ",
                "Had first non-nutritive breast feed",
                "Went to the breast",
                "Had formula for the first time",
                "Reached full feeds",
                "Had a bottle feed",
                "Fed tube free"
        };
        String[] milestoneDescriptions8 = {
                //Tests and Procedures
                "Got my first immunizations",
                "Passed my Hearing Test"
        };

        List<Milestone> milestones = new ArrayList<Milestone>();

        for (String description : milestoneDescriptions1)
        {
            Milestone milestone = new Milestone();
            milestone.setDescription(description);
            milestone.setCategory("Breathing Related");
            milestones.add(milestone);
        }
        for (String description : milestoneDescriptions2)
        {
            Milestone milestone = new Milestone();
            milestone.setDescription(description);
            milestone.setCategory("Beds, Seats, and Swings Related");
            milestones.add(milestone);
        }
        for (String description : milestoneDescriptions3)
        {
            Milestone milestone = new Milestone();
            milestone.setDescription(description);
            milestone.setCategory("Cuddles Related");
            milestones.add(milestone);
        }
        for (String description : milestoneDescriptions4)
        {
            Milestone milestone = new Milestone();
            milestone.setDescription(description);
            milestone.setCategory("Growth Related");
            milestones.add(milestone);
        }
        for (String description : milestoneDescriptions5)
        {
            Milestone milestone = new Milestone();
            milestone.setDescription(description);
            milestone.setCategory("Transitions Related");
            milestones.add(milestone);
        }
        for (String description : milestoneDescriptions6)
        {
            Milestone milestone = new Milestone();
            milestone.setDescription(description);
            milestone.setCategory("Activities of Daily Living Related");
            milestones.add(milestone);
        }
        for (String description : milestoneDescriptions7)
        {
            Milestone milestone = new Milestone();
            milestone.setDescription(description);
            milestone.setCategory("Feeding Related");
            milestones.add(milestone);
        }
        for (String description : milestoneDescriptions8)
        {
            Milestone milestone = new Milestone();
            milestone.setDescription(description);
            milestone.setCategory("Tests and Procedures");
            milestones.add(milestone);
        }
        addMilestones(db, milestones);

    }
    //==============================================================================================

    public void Login(View v){
        TextView textView_username =  (TextView) findViewById(R.id.txt_Username);
        TextView textView_password =  (TextView) findViewById(R.id.txt_password);

        String inputUsername = textView_username.getText().toString();
        String inputPassword = textView_password.getText().toString();

        User user = null;
        if(!inputUsername.equals("")){
            user = getUserByUsername(db, inputUsername);
        }

        if(user!=null)
        {
            if(inputUsername.equals(user.getUsername()) && inputPassword.equals(user.getPassword()))
            {
                app.setCurrentUser(user.getId());
                startActivity(new Intent(LoginActivity.this, MainMenuActivity.class));
                this.finish();
            }
            else
            {
                Context context = getApplicationContext();
                CharSequence text = "Wrong username/password";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Wrong username/password";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }


    }
}
