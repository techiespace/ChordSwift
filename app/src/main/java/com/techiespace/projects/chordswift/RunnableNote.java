package com.techiespace.projects.chordswift;

import android.app.Activity;
import android.util.Log;

import com.techiespace.projects.chordswift.pianoHelpers.Note;
import com.techiespace.projects.chordswift.pianoHelpers.PianoView;

public class RunnableNote extends Activity implements Runnable {

    final String note;
    int startTime;
    int endTime;
    PianoView pianoView;

    public RunnableNote(Note note, PianoView view) {
        this.startTime = note.getStartTime();
        this.endTime = note.getEndTime();
        this.note = note.getNote();
        this.pianoView = view;
    }

    @Override
    public void run() {
        //Press the key

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.v("Start  " + note, String.valueOf(System.currentTimeMillis()));
                pianoView.PerformClickDown(note);

            }
        });


        //Sleep for the pressed duration
        try {
            Thread.sleep(endTime - startTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Make the key unpressed again
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.v("End  " + note, String.valueOf(System.currentTimeMillis()));
                pianoView.PerformClickUp(note);
            }
        });


    }
}
