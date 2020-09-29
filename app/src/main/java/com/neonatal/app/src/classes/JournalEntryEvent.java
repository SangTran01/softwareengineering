package com.neonatal.app.src.classes;

import com.neonatal.app.src.entity.Event;
import com.neonatal.app.src.entity.JournalEntry;

/**
 * Created by ufe-i134-pc10 on 07/01/2018.
 */

public class JournalEntryEvent {
    private int eventId;
    private int personId;
    private int journalEntryId;
    private int milestoneId;

    private String eventDateTime;
    private String imagePath;
    private String bodyText;

    public JournalEntryEvent(Event event, JournalEntry journalEntry) {
        this.eventId = event.getId();
        this.personId = event.getPersonId();
        this.eventDateTime = event.getEventDateTime();

        this.journalEntryId = journalEntry.getId();
        this.milestoneId = journalEntry.getMilestoneId();
        this.imagePath = journalEntry.getImageString();
        this.bodyText = journalEntry.getBodyText();
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getJournalEntryId() {
        return journalEntryId;
    }

    public void setJournalEntryId(int journalEntryId) {
        this.journalEntryId = journalEntryId;
    }

    public int getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(int milestoneId) {
        this.milestoneId = milestoneId;
    }

    public String getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(String eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }
}
