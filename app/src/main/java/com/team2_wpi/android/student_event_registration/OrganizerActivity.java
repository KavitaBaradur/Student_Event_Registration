package com.team2_wpi.android.student_event_registration;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.team2_wpi.android.student_event_registration.data.SQLCommand;
import com.team2_wpi.android.student_event_registration.util.DBOperator;

/**
 * Created by kavitabaradur on 4/1/17.
 */

public class OrganizerActivity extends AppCompatActivity implements View.OnClickListener {
    private Button feedback;
    private Button add_new;
    private String org_id;
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get org id
        org_id = getIntent().getStringExtra("Org ID");
        // link view
        setContentView(R.layout.organizer_welcome);
        // find elements
        feedback = (Button) findViewById(R.id.org_welcome_feedback);
        add_new = (Button) findViewById(R.id.org_welcome_add);
        listView = (ListView) findViewById(R.id.org_event_his_lv);
        // set up on click
        feedback.setOnClickListener(this);
        add_new.setOnClickListener(this);
        // visualize event history
        visualHistory();
    }

    private void visualHistory() {
        // init args
        String init_args[] = new String[1];
        init_args[0] = org_id;
        // execute the sql
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.ORG_EVENT_HIS, init_args);
        // bind the data to list
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),
                                                              R.layout.org_event_listitem,
                                                              cursor,
                                                              new String[] { "name", "type", "date", "time" },
                                                              new int[] { R.id.org_event_name, R.id.org_event_type, R.id.org_event_date, R.id.org_event_time },
                                                              SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        // assign action
        if (id == R.id.org_welcome_feedback) {
            // go feedback page
            Intent intent = new Intent(this, OrganizerFeedbackActivity.class);
            intent.putExtra("Org ID", org_id);
            this.startActivity(intent);
        }
        else if (id == R.id.org_welcome_add) {
            // go add new event page
            Intent intent = new Intent(this,OrganizerAddEventActivity.class);
            intent.putExtra("Org ID", org_id);
            this.startActivity(intent);
        }
    }
}
