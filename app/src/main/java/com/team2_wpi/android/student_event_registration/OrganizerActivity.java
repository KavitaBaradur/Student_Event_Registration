package com.team2_wpi.android.student_event_registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by kavitabaradur on 4/1/17.
 */

public class OrganizerActivity extends AppCompatActivity implements View.OnClickListener {
    private Button feedback;
    private Button add_new;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // link view
        setContentView(R.layout.organizer_welcome);
        // find elements
        feedback = (Button) findViewById(R.id.org_welcome_feedback);
        add_new = (Button) findViewById(R.id.org_welcome_add);
        // set up on click
        feedback.setOnClickListener(this);
        add_new.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        // assign action
        if (id == R.id.org_welcome_feedback) {
            // go feedback page
            Intent intent = new Intent(this, OrganizerFeedbackActivity.class);
            this.startActivity(intent);
        }
        else if (id == R.id.org_welcome_add) {
            // go add new event page
            Intent intent = new Intent(this,OrganizerAddEventActivity.class);
            this.startActivity(intent);
        }
    }
}
