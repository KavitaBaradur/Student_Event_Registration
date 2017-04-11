package com.team2_wpi.android.student_event_registration;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.team2_wpi.android.student_event_registration.data.SQLCommand;
import com.team2_wpi.android.student_event_registration.util.DBOperator;

/**
 * Created by sylor on 4/7/17.
 */

public class OrganizerAddEventActivity extends AppCompatActivity implements View.OnClickListener {
    private Button check;
    private Button submit;
    private Button funding;
    private EditText name;
    private EditText type;
    private EditText date;
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // link view
        setContentView(R.layout.organizer_add_event);
        // find elements
        name = (EditText) findViewById(R.id.org_add_event_name);
        type = (EditText) findViewById(R.id.org_add_event_type);
        date = (EditText) findViewById(R.id.org_add_event_date);
        check = (Button) findViewById(R.id.org_add_event_chkaval);
        submit = (Button) findViewById(R.id.org_add_event_submit);
        funding = (Button) findViewById(R.id.org_add_event_funding);
        listView = (ListView) findViewById(R.id.org_add_event_lv);
        // set up on click
        check.setOnClickListener(this);
        submit.setOnClickListener(this);
        funding.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        // assign action
        if (id == R.id.org_add_event_chkaval) {
            // check places
            // init args
            String init_args[] = new String[1];
            init_args[0] = date.getText().toString();
            // execute the sql
            Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.ORG_AVAL_PLACE, init_args);
            // bind the data to list
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),
                    R.layout.org_place_listitem,
                    cursor,
                    new String[] { "name" },
                    new int[] { R.id.org_place_name },
                    SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
            listView.setAdapter(adapter);
        }
        else if (id == R.id.org_add_event_submit) {
            // go select building page
        }
        else if (id == R.id.org_add_event_funding) {
            // go add funding
            Intent intent = new Intent(this, OrganizerAddSponsorActivity.class);
            this.startActivity(intent);
        }
    }
}
