package com.team2_wpi.android.student_event_registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by sylor on 4/7/17.
 */

public class OrganizerAddEventActivity extends AppCompatActivity implements View.OnClickListener {
    private Button next;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // link view
        setContentView(R.layout.organizer_add_event);
        // find elements
        next = (Button) findViewById(R.id.org_add_event_next);
        // set up on click
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        // assign action
        if (id == R.id.org_add_event_next) {
            // go select building page
        }
    }
}
