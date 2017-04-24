package com.team2_wpi.android.student_event_registration;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.team2_wpi.android.student_event_registration.data.SQLCommand;
import com.team2_wpi.android.student_event_registration.util.DBOperator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kavitabaradur on 4/21/17.
 */

public class StudentEventsAttendedActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button feedback_btn;
    Spinner eventSpinner;
    String stud_id, event_id;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_events_attended);

        feedback_btn = (Button) this.findViewById(R.id.std_feedback_btn);
        feedback_btn.setOnClickListener(this);
        eventSpinner = (Spinner) this.findViewById(R.id.std_event_list_spinner);

        eventSpinner.setOnItemSelectedListener(this);

        loadSpinnerData();

    }

    private void loadSpinnerData() {

        stud_id = getIntent().getExtras().getString("student_id");
        Log.i(stud_id, "STUDENT_ID");
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.QUERY_8, new String[]{stud_id});

        List<String> attended_events = new ArrayList<String>();

        if (cursor.moveToFirst()) {
            do {
                Log.i(cursor.getString(1), "EVENT");
                attended_events.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        ArrayAdapter<String> eventsAdapter = new ArrayAdapter<String>(StudentEventsAttendedActivity.this,
                R.layout.student_spinner_item, attended_events);

        eventSpinner.setAdapter(eventsAdapter);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.std_feedback_btn) {
            Intent intent = new Intent(this, StudentSubmitFeedbackActivity.class);
            stud_id = getIntent().getExtras().getString("student_id");

           /* Cursor cursor = (Cursor) eventSpinner.getItemAtPosition(R.id.spinner_item);
            event_id = cursor.getString(0);*/
            String event = eventSpinner.getSelectedItem().toString();
            Log.i(event, "EVENT_NAME");

            Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.QUERY_9, new String[]{event});
            cursor.moveToFirst();

            event_id = cursor.getString(0);
            intent.putExtra("student_id", stud_id);
            intent.putExtra("event_id", event_id);
            Log.i(stud_id, "student_id");
            Log.i(event_id, "event_id");
            this.startActivity(intent);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
