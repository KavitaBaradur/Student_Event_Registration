package com.team2_wpi.android.student_event_registration;

import android.content.Intent;
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
 * Created by kavitabaradur on 4/1/17.
 */

public class StudentLoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText stud_id, stud_pass;
    Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        stud_id = (EditText) this.findViewById(R.id.userid);
        stud_pass = (EditText) this.findViewById(R.id.password);
        signin = (Button) this.findViewById(R.id.signin_btn);
        signin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        /*get password from database for particular student_id*/
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.QUERY_1, new String[]{stud_id.getText().toString()});
        cursor.moveToFirst();

        if (cursor.getCount() <= 0) {
            String data1 = "no such id";
            Log.i(data1, "WRONG ID");
            Toast.makeText(getApplicationContext(), "PROVIDE CORRECT ID", Toast.LENGTH_SHORT).show();
        }
        /*if password entered is correct then StudentEvent screen*/
        else {
            String db_password = cursor.getString(0);
            Log.d(db_password, "PASSWORD");
            // do what ever you want here
            if (db_password.equals(stud_pass.getText().toString())) {
                Intent intent = new Intent(this, StudentEventActivity.class);
                intent.putExtra("student_id",stud_id.getText().toString());
                this.startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "WRONG PASSWORD", Toast.LENGTH_SHORT).show();
            }
        }
    }
}






