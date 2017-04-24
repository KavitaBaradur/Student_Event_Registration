package com.team2_wpi.android.student_event_registration;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.team2_wpi.android.student_event_registration.data.SQLCommand;
import com.team2_wpi.android.student_event_registration.util.DBOperator;

/**
 * Created by kavitabaradur on 4/8/17.
 */

public class StudentDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    Button submit_btn;
    EditText stud_name, stud_major, new_pwd, confirm_pwd;
    String stud_id;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_details);

        stud_name = (EditText) this.findViewById(R.id.db_name);
        stud_name.setEnabled(false);
        stud_major = (EditText) this.findViewById(R.id.db_major);
        stud_major.setEnabled(false);

        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.QUERY_3,
                new String[]{getIntent().getExtras().getString("student_id")});
        cursor.moveToFirst();
        stud_name.setText(cursor.getString(0));
        String name = cursor.getString(0);
        Log.d(name, "NAME");

        Cursor cursor1 = DBOperator.getInstance().execQuery(SQLCommand.QUERY_4, new String[]{getIntent().getExtras().getString("student_id")});
        cursor1.moveToFirst();
        stud_major.setText(cursor1.getString(0));
        String major = cursor1.getString(0);
        Log.d(major, "MAJOR");

        submit_btn = (Button) this.findViewById(R.id.submit_details);
        submit_btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        stud_id = getIntent().getExtras().getString("student_id");
        updatePassword(stud_id);
    }

    public void updatePassword(String id) {

        new_pwd = (EditText) this.findViewById(R.id.new_password);
        String new_pwd_value = new_pwd.getText().toString();
        confirm_pwd = (EditText) this.findViewById(R.id.confirm_password);

        if (new_pwd_value.equals(confirm_pwd.getText().toString())) {
            stud_id = getIntent().getExtras().getString("student_id");
            String update = "update STUDENT set STD_PASSWORD='" + new_pwd_value + "' where STD_ID='" + stud_id + "'";

            DBOperator.getInstance().execSQL(update);
            Toast.makeText(getApplicationContext(), "Password Updated", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getApplicationContext(), "Both Passwords do not match", Toast.LENGTH_SHORT).show();
        }


    }
}
