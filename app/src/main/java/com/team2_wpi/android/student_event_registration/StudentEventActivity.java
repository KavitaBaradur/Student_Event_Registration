package com.team2_wpi.android.student_event_registration;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.team2_wpi.android.student_event_registration.data.SQLCommand;
import com.team2_wpi.android.student_event_registration.util.DBOperator;

/**
 * Created by kavitabaradur on 4/4/17.
 */

public class StudentEventActivity extends AppCompatActivity implements View.OnClickListener{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_welcome);
    }


    @Override
    public void onClick(View v) {

        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.QUERY_2);

    }
}
