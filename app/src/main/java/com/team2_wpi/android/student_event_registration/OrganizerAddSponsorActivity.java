package com.team2_wpi.android.student_event_registration;

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
 * Created by sylor on 4/11/17.
 */

public class OrganizerAddSponsorActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView listView;
    private EditText funding;
    private Button submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // link view
        setContentView(R.layout.organizer_add_sponsor);
        // find elements
        listView = (ListView) findViewById(R.id.org_add_sponsor_lv);
        funding = (EditText) findViewById(R.id.org_add_sponsor_funding);
        submit = (Button) findViewById(R.id.org_add_sponsor_submit);
        // set up on click
        submit.setOnClickListener(this);
        // visualize sponsors
        visualSponsor();
    }

    private void visualSponsor() {
        // execute the sql
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.ORG_ALL_SPON);
        // bind the data to list
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),
                R.layout.org_place_listitem,
                cursor,
                new String[] { "name" },
                new int[] { R.id.org_place_name },
                SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

    }
}
