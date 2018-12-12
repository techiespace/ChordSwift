package com.techiespace.projects.chordswift;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import com.techiespace.projects.chordswift.pianoHelpers.Note;
import com.techiespace.projects.chordswift.pianoHelpers.PianoView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class PracticeActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
    private final static float SEEKBAR_OFFSET_SIZE = -12;
    public TextView noteText;
    private SeekBar seekbar;
    private int scrollProgress = 0;
    private PianoView pianoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Remove the title bar
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);


        pianoView = findViewById(R.id.pv);
        noteText = findViewById(R.id.note_text);

        seekbar = findViewById(R.id.sb);
        seekbar.setThumbOffset((int) convertDpToPixel(SEEKBAR_OFFSET_SIZE));
        seekbar.setOnSeekBarChangeListener(this);


        AudioProcessor Ap = new AudioProcessor(noteText, pianoView);

        //TODO: Figure out the reason for the lag in opening practice activty. This happens even when the midi parser is not present.
        MidiParser midiParser = new MidiParser(this);
        Note[] notes = midiParser.parse("CScale.mid");
        System.out.println("The returned notes array is: ");
        for (int i = 0; i < notes.length; i++) {
            System.out.println(notes[i].getNote() + " " + notes[i].getStartTime() + " " + notes[i].getEndTime());
        }

        //read parsed txt file
        Note[] parsedNotes = readParsedMidi();
        System.out.println("Read from parsed txt file into Note object: ");
        for (int i = 0; i < notes.length; i++) {
            System.out.println(parsedNotes[i].getNote() + " " + parsedNotes[i].getStartTime() + " " + parsedNotes[i].getEndTime());
        }
    }

    private Note[] readParsedMidi() {
        Note[] notes;
        ArrayList<Note> noteArrayList = new ArrayList<Note>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("CScale.txt")));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                //process line
                String[] noteStr = mLine.split(" ");
                int startTime = Integer.parseInt(noteStr[1]);
                int stopTime = Integer.parseInt(noteStr[2]);
                String noteName = noteStr[0];
                Note note = new Note(startTime, stopTime, noteName);
                noteArrayList.add(note);
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        notes = noteArrayList.toArray(new Note[noteArrayList.size()]);
        return notes;
    }

    @Override
    protected void onResume() {

        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        Log.d("v", "onProgressChange" + i + " " + b);
        pianoView.scroll(i);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private float convertDpToPixel(float dp) {
        Resources resources = this.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

}
