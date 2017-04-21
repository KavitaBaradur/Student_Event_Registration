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
 * Created by sylor on 4/13/17.
 */

public class OrganizerLoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText org_name, org_pass;
    Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.organizer_login);
        org_name = (EditText)this.findViewById(R.id.org_login_name);
        org_pass = (EditText)this.findViewById(R.id.org_login_pwd);
        signin = (Button)this.findViewById(R.id.org_login_submit);
        signin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.ORG_LOGIN, new String[]{org_name.getText().toString()});
        cursor.moveToFirst();

        if (cursor.getCount() <= 0)
        {
            String data1 = "no such name";
            Log.i(data1, "WRONG NAME");
            Toast.makeText(getApplicationContext(), "WRONG USERNAME", Toast.LENGTH_SHORT).show();
        }
        else {
            String db_password = cursor.getString(0);
            Log.d(db_password, "PASSWORD");
            // do what ever you want here
            if (db_password.equals(org_pass.getText().toString())) {
                Intent intent = new Intent(this, OrganizerActivity.class);
                intent.putExtra("Org ID", cursor.getString(1));
                this.startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "WRONG PASSWORD", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
