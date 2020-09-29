package com.neonatal.app.src;

import android.app.Application;

/**
 * Created by Maurice on 1/5/2018.
 */
//TODO: find out what the hell this is
public class NeonatalApp extends Application {

    private int currentUser;
    private int currentPatient;

    public int getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(int userId) {
        currentUser = userId;
    }

    public int getCurrentPatient() {
        return currentPatient;
    }

    public void setCurrentPatient(int patientId) {
        currentPatient = patientId;
    }
}
