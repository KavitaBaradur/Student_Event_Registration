package com.team2_wpi.android.student_event_registration;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.team2_wpi.android.student_event_registration.data.SQLCommand;
import com.team2_wpi.android.student_event_registration.util.DBOperator;

/**
 * Created by sylor on 4/7/17.
 */

public class OrganizerFeedbackActivity extends AppCompatActivity implements View.OnClickListener {
    private Button back;
    private String org_id;
    private String selectedEvent;
    private String selectedEventName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get org id
        org_id = getIntent().getStringExtra("Org ID");
        selectedEvent = getIntent().getStringExtra("Event ID");
        selectedEventName = getIntent().getStringExtra("Event Name");
        // link view
        setContentView(R.layout.organizer_feedback);
        // find elements
        back = (Button) findViewById(R.id.org_feedback_back);
        // set up on click
        back.setOnClickListener(this);
        // visualize feedbacks
        visualFeedback();
    }

    private void visualFeedback() {
        // init args
        String init_args[] = new String[2];
        init_args[0] = selectedEvent;
        init_args[1] = org_id;
        // execute the sql
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.ORG_FEEDBACK, init_args);
        // bind the data to list
            // find element
        TextView EventName = (TextView) findViewById(R.id.org_feedback_eventName);
        TextView Great = (TextView) findViewById(R.id.org_feedback_greatNum);
        TextView Fair  = (TextView) findViewById(R.id.org_feedback_fairNum);
        TextView Imp   = (TextView) findViewById(R.id.org_feedback_impNum);
        String feedback;
            // set event name
        EventName.setText(selectedEventName);
            // set feedbacks
        while (cursor.moveToNext()) {
            feedback = cursor.getString(2);
            if (feedback.equals("Great")) {
                Great.setText(cursor.getString(3));
            }
            else if (feedback.equals("Fair")) {
                Fair.setText(cursor.getString(3));
            }
            else if (feedback.equals("Need_Imp")) {
                Imp.setText(cursor.getString(3));
            }
        }


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        // assign actions
        if (id == R.id.org_feedback_back) {
            Intent intent = new Intent(this, OrganizerActivity.class);
            intent.putExtra("Org ID", org_id);
            this.startActivity(intent);
        }
    }
}
