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

public class OrganizerFeedbackActivity extends AppCompatActivity implements View.OnClickListener {
    private Button back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // link view
        setContentView(R.layout.organizer_feedback);
        // find elements
        back = (Button) findViewById(R.id.org_feedback_back);
        // set up on click
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        // assign actions
        if (id == R.id.org_feedback_back) {
            Intent intent = new Intent(this, OrganizerActivity.class);
            this.startActivity(intent);
        }
    }
}
