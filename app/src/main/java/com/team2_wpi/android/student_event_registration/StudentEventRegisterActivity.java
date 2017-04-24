package com.team2_wpi.android.student_event_registration;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.team2_wpi.android.student_event_registration.data.SQLCommand;
import com.team2_wpi.android.student_event_registration.util.DBOperator;

/**
 * Created by kavitabaradur on 4/21/17.
 */

public class StudentEventRegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button register_btn, back_btn;
    TextView event_name, event_type, event_addr, event_date, event_time;
    String stud_id, event_id;
    CheckBox volunteer;
    EditText volunteer_hrs;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_event_detail);

        volunteer = (CheckBox) this.findViewById(R.id.std_volunteer);
        volunteer_hrs = (EditText) this.findViewById(R.id.volunteer_hrs);
        register_btn = (Button) this.findViewById(R.id.register_btn);
        register_btn.setOnClickListener(this);
        back_btn = (Button) this.findViewById(R.id.back_btn);
        back_btn.setOnClickListener(this);


        displayEventDetails();
    }

    private void displayEventDetails() {

        // stud_id = getIntent().getExtras().getString("student_id");
        event_id = getIntent().getExtras().getString("event_id");

        // Log.i(stud_id,"ID");
        Log.i(event_id, "EVENT");
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.QUERY_6, new String[]{event_id});
        cursor.moveToFirst();
        Log.i(cursor.getString(0), "event_id");
        Log.i(cursor.getString(1), "event_name");

        event_name = (TextView) this.findViewById(R.id.std_event_name);
        event_name.setText(cursor.getString(1));
        event_type = (TextView) this.findViewById(R.id.std_event_type);
        event_type.setText(cursor.getString(2));
        event_addr = (TextView) this.findViewById(R.id.std_event_address);
        event_addr.setText(cursor.getString(3));
        event_date = (TextView) this.findViewById(R.id.std_event_date);
        event_date.setText(cursor.getString(4));
        event_time = (TextView) this.findViewById(R.id.std_event_time);
        event_time.setText(cursor.getString(5));
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (volunteer.isChecked()) {
            volunteerEvent();
        }

        /*student login screen*/
        if (id == R.id.register_btn) {
            registerEvent();

        } else if (id == R.id.back_btn) {
            Intent intent = new Intent(this, StudentEventActivity.class);
            String userid = getIntent().getExtras().getString("student_id");
            intent.putExtra("student_id", userid);
            Log.i(userid, "student_id");
            this.startActivity(intent);
            finish();
        }

    }

    private void volunteerEvent() {

        stud_id = getIntent().getExtras().getString("student_id");
        event_id = getIntent().getExtras().getString("event_id");


        DBOperator.getInstance().execSQL(SQLCommand.QUERY_11, new String[]{stud_id, event_id, volunteer_hrs.getText().toString()});

        Log.i(volunteer_hrs.getText().toString(), "Volunteer_hrs");

    }

    private void registerEvent() {

        stud_id = getIntent().getExtras().getString("student_id");
        event_id = getIntent().getExtras().getString("event_id");

        Log.i(stud_id, "ID");
        Log.i(event_id, "EVENT");
        DBOperator.getInstance().execSQL(SQLCommand.QUERY_7, new String[]{stud_id, event_id});

        Toast.makeText(getApplicationContext(), "REGISTERED FOR EVENT", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, StudentEventActivity.class);
        String userid = getIntent().getExtras().getString("student_id");
        intent.putExtra("student_id", userid);
        Log.i(userid, "student_id");
        this.startActivity(intent);

    }
}
