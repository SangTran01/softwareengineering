package com.neonatal.app.src.classes;

import com.neonatal.app.src.entity.Patient;
import com.neonatal.app.src.entity.Person;

/**
 * Created by Maurice on 1/5/2018.
 */

public class PatientPerson {
    private int patientId;
    private int personId;
    private int userId;

    private String firstName;
    private String lastName;
    private String sex;
    private String birthDate;
    private String gestationalStartDate;
    private String phone;
    private String email;

    public PatientPerson (Patient patient, Person person) {
        this.patientId = patient.getId();
        this.userId = patient.getUserId();
        this.gestationalStartDate = patient.getGestationalStartDate();

        this.personId = person.getId();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.sex = person.getSex();
        this.birthDate = person.getBirthDate();
        this.phone = person.getPhone();
        this.email = person.getEmail();
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGestationalStartDate() {
        return gestationalStartDate;
    }

    public void setGestationalStartDate(String gestationalStartDate) {
        this.gestationalStartDate = gestationalStartDate;
    }
}
