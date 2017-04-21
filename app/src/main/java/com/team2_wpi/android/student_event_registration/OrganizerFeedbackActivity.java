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
 * Created by sylor on 4/7/17.
 */

public class OrganizerFeedbackActivity extends AppCompatActivity implements View.OnClickListener {
    private Button back;
    private String org_id;
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get org id
        org_id = getIntent().getStringExtra("Org ID");
        // link view
        setContentView(R.layout.organizer_feedback);
        // find elements
        back = (Button) findViewById(R.id.org_feedback_back);
        listView = (ListView) findViewById(R.id.org_feedback_lv);
        // set up on click
        back.setOnClickListener(this);
        // visualize feedbacks
        visualFeedback();
    }

    private void visualFeedback() {
        // init args
        String init_args[] = new String[1];
        init_args[0] = org_id;
        // execute the sql
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.ORG_FEEDBACK, init_args);
        // bind the data to list
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),
                                                              R.layout.org_event_listitem,
                                                              cursor,
                                                              new String[] { "name", "great", "fair", "need_imp" },
                                                              new int[] { R.id.org_feedback_name, R.id.org_feedback_great, R.id.org_feedback_fair, R.id.org_feedback_need_imp },
                                                              SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);
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
