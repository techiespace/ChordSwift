package com.techiespace.projects.chordswift.pianoHelpers;

public class Note {

    int startTime;
    int endTime;
    String note;


    public Note(int startTime, int endTime, String note) {

        this.startTime = startTime;
        this.endTime = endTime;
        this.note = note;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return this.endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;

    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }


}
