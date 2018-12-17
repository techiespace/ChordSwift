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

    public Note(int startTime, int endTime, int midiNoteNumber) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.note = getMidiNoteName(midiNoteNumber);
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getMidiNoteName(int midiNumber) {
        //TODO: Map midiNoteNumber to midiNoteName - Implement efficient solution
        switch (midiNumber) {
            case 60:
                return "C4";
            case 62:
                return "D4";
            case 64:
                return "E4";
            case 65:
                return "F4";
            case 67:
                return "G4";
            case 69:
                return "A4";
            case 71:
                return "B4";
            case 72:
                return "C5";
            default:
                return "Error";
        }
    }
}
