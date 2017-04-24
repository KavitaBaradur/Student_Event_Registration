package com.team2_wpi.android.student_event_registration;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.team2_wpi.android.student_event_registration.data.SQLCommand;
import com.team2_wpi.android.student_event_registration.util.DBOperator;

/**
 * Created by kavitabaradur on 4/4/17.
 */

public class StudentEventActivity extends AppCompatActivity implements View.OnClickListener, MenuItem.OnMenuItemClickListener {

    BottomNavigationView student_navigation;
    private ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_welcome);

        student_navigation = (BottomNavigationView) this.findViewById(R.id.student_bottom_navigation_window);

        Menu student_menu = student_navigation.getMenu();
        MenuItem profile = student_menu.findItem(R.id.action_profile);
        MenuItem logout = student_menu.findItem(R.id.action_logout);
        MenuItem attended_events = student_menu.findItem(R.id.action_att_events);
        profile.setOnMenuItemClickListener(this);
        logout.setOnMenuItemClickListener(this);
        attended_events.setOnMenuItemClickListener(this);

        listView = (ListView) this.findViewById(R.id.events_list);
        listView.setOnItemClickListener(new ItemClickListener());

        viewEvents();
    }

    private void viewEvents() {


        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.QUERY_2);
        //  cursor.moveToFirst();
        // Log.i(cursor.getString(0), "EVENT_NAME");

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),
                R.layout.student_event_listitem, cursor, new String[]{"event_name"},
                new int[]{R.id.student_event_name}, SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.action_profile:
                /*view profile*/
                Intent intent1 = new Intent(this, StudentDetailsActivity.class);
                String userid = getIntent().getExtras().getString("student_id");
                intent1.putExtra("student_id", userid);
                startActivity(intent1);
                break;

            case R.id.action_att_events:
                /*view attended events*/
                Intent intent2 = new Intent(this, StudentEventsAttendedActivity.class);
                String studentid = getIntent().getExtras().getString("student_id");
                intent2.putExtra("student_id", studentid);
                startActivity(intent2);
                break;

            case R.id.action_logout:
                /*logout*/
                Intent intent3 = new Intent(this, WelcomeActivity.class);
                intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent3);
                break;
        }
        return false;
    }

    private class ItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Cursor cursor = (Cursor) listView.getItemAtPosition(position);
            String eventid = cursor.getString(0);

            Intent intent4 = new Intent(StudentEventActivity.this, StudentEventRegisterActivity.class);
            String userid = getIntent().getExtras().getString("student_id");
            intent4.putExtra("student_id", userid);
            intent4.putExtra("event_id", eventid);

            Log.i(eventid, "EVENT_ID");
            Log.i(userid, "STUDENT_ID");
            startActivity(intent4);

        }
    }
}
