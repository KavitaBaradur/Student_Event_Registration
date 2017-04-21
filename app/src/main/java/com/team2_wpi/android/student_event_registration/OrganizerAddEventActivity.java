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
import android.widget.Toast;

import com.team2_wpi.android.student_event_registration.data.SQLCommand;
import com.team2_wpi.android.student_event_registration.util.DBOperator;

/**
 * Created by sylor on 4/7/17.
 */

public class OrganizerAddEventActivity extends AppCompatActivity implements View.OnClickListener {
    private String org_id;
    private String place_id;
    private Button check;
    private Button submit;
    private Button funding;
    private EditText name;
    private EditText type;
    private EditText date;
    private EditText time;
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get org id
        org_id = getIntent().getStringExtra("Org ID");
        // link view
        setContentView(R.layout.organizer_add_event);
        // find elements
        name = (EditText) findViewById(R.id.org_add_event_name);
        type = (EditText) findViewById(R.id.org_add_event_type);
        date = (EditText) findViewById(R.id.org_add_event_date);
        time = (EditText) findViewById(R.id.org_add_event_time);
        check = (Button) findViewById(R.id.org_add_event_chkaval);
        submit = (Button) findViewById(R.id.org_add_event_submit);
        funding = (Button) findViewById(R.id.org_add_event_funding);
        listView = (ListView) findViewById(R.id.org_add_event_lv);
        // set up on click
        check.setOnClickListener(this);
        submit.setOnClickListener(this);
        funding.setOnClickListener(this);
        listView.setOnItemClickListener(new ItemClickListener());
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
            // insert into event
            // init args
            String init_args[] = new String[3];
            init_args[0] = org_id;
            init_args[1] = name.getText().toString();
            init_args[2] = type.getText().toString();
            // execute the sql
            DBOperator.getInstance().execSQL(SQLCommand.ORG_INS_EVENT, init_args);
            // insert into reserve
            // get event id
            Cursor last_id = DBOperator.getInstance().execQuery(SQLCommand.GET_LAST_ID);
            String event_id = null;
            while (last_id.moveToNext()) {
                event_id = last_id.getString(last_id.getColumnIndex("last_insert_rowid()"));
            }
            // init args
            init_args = new String[4];
            init_args[0] = event_id;
            init_args[1] = place_id;
            init_args[2] = date.getText().toString();
            init_args[3] = time.getText().toString();
            // execute the sql
            DBOperator.getInstance().execSQL(SQLCommand.ORG_INS_RESERVE, init_args);
            // go back to org main
            Intent intent = new Intent(this, OrganizerActivity.class);
            intent.putExtra("Org ID", org_id);
            this.startActivity(intent);
        }
        else if (id == R.id.org_add_event_funding) {
            // insert into event
            // init args
            String init_args[] = new String[3];
            init_args[0] = org_id;
            init_args[1] = name.getText().toString();
            init_args[2] = type.getText().toString();
            // execute the sql
            DBOperator.getInstance().execSQL(SQLCommand.ORG_INS_EVENT, init_args);
            // insert into reserve
            // get event id
            Cursor last_id = DBOperator.getInstance().execQuery(SQLCommand.GET_LAST_ID);
            String event_id = null;
            while (last_id.moveToNext()) {
                event_id = last_id.getString(last_id.getColumnIndex("last_insert_rowid()"));
            }
            // init args
            init_args = new String[4];
            init_args[0] = event_id;
            init_args[1] = place_id;
            init_args[2] = date.getText().toString();
            init_args[3] = time.getText().toString();
            // execute the sql
            DBOperator.getInstance().execSQL(SQLCommand.ORG_INS_RESERVE, init_args);
            // go add funding
            Intent intent = new Intent(this, OrganizerAddSponsorActivity.class);
            intent.putExtra("Org ID", org_id);
            intent.putExtra("Event ID", event_id);
            this.startActivity(intent);
        }
    }

    private class ItemClickListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
            Cursor cursor = (Cursor) listView.getItemAtPosition(position);
            place_id = cursor.getString(0);
        }
    }
}
