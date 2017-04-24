package com.team2_wpi.android.student_event_registration;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.team2_wpi.android.student_event_registration.data.SQLCommand;
import com.team2_wpi.android.student_event_registration.util.DBOperator;

/**
 * Created by sylor on 4/11/17.
 */

public class OrganizerAddSponsorActivity extends AppCompatActivity implements View.OnClickListener {
    private String event_id;
    private String sponsor_id;
    private String org_id;
    private ListView listView;
    private EditText funding;
    private Button submit;
    private TextView selectedSponsor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get org id
        org_id = getIntent().getStringExtra("Org ID");
        // get event id
        event_id = getIntent().getStringExtra("Event ID");
        // link view
        setContentView(R.layout.organizer_add_sponsor);
        // find elements
        listView = (ListView) findViewById(R.id.org_add_sponsor_lv);
        funding = (EditText) findViewById(R.id.org_add_sponsor_funding);
        submit = (Button) findViewById(R.id.org_add_sponsor_submit);
        selectedSponsor = (TextView) findViewById(R.id.org_add_sponsor_selected);
        // set up on click
        submit.setOnClickListener(this);
        listView.setOnItemClickListener(new ItemClickListener());
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
        int id = v.getId();
        // assign action
        if (id == R.id.org_add_sponsor_submit) {
            // insert into event detail
            // init args
            String init_args[] = new String[3];
            init_args[0] = event_id;
            init_args[1] = sponsor_id;
            init_args[2] = funding.getText().toString();
            // execute the sql
            DBOperator.getInstance().execSQL(SQLCommand.ORG_INS_EVENT_DETAIL, init_args);
            // go back to org main
            Intent intent = new Intent(this, OrganizerActivity.class);
            intent.putExtra("Org ID", org_id);
            this.startActivity(intent);
        }
    }

    private class ItemClickListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
            Cursor cursor = (Cursor) listView.getItemAtPosition(position);
            sponsor_id = cursor.getString(0);
            selectedSponsor.setText(cursor.getString(1));
        }
    }
}
