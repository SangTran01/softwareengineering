package com.neonatal.app.src.classes;

import com.neonatal.app.src.entity.DataEntry;

public class PatientDatesEvents {
    private String eventDate;
    private String eventType;
    public DataEntry dataEntry;
    public boolean hasDate;
    private int eventId;

    public PatientDatesEvents(boolean hasDate, DataEntry dataEntry) {
        this.hasDate = hasDate;
        this.dataEntry = dataEntry;
    }

    public PatientDatesEvents(boolean hasDate, String eventDate, int eventId) {
        this.hasDate = hasDate;
        this.eventDate = eventDate;
        this.eventId = eventId;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
