package com.team2_wpi.android.student_event_registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.team2_wpi.android.student_event_registration.data.SQLCommand;
import com.team2_wpi.android.student_event_registration.util.DBOperator;

/**
 * Created by kavitabaradur on 4/22/17.
 */

public class StudentSubmitFeedbackActivity extends AppCompatActivity implements View.OnClickListener {

    Button submit_btn;
    String stud_id, event_id;
    RadioGroup feedback_values;
    RadioButton radioButton;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_feedback_screen);

        feedback_values = (RadioGroup) this.findViewById(R.id.std_feedback_values);
        submit_btn = (Button) this.findViewById(R.id.std_submit_btn);
        submit_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        stud_id = getIntent().getExtras().getString("student_id");
        Log.i(stud_id, "STUDENT_ID_FEED");
        event_id = getIntent().getExtras().getString("event_id");
        Log.i(event_id, "EVENT_ID_FEED");

        int selectedId = feedback_values.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        radioButton = (RadioButton) findViewById(selectedId);

        //Toast.makeText(StudentSubmitFeedbackActivity.this,radioButton.getText(), Toast.LENGTH_SHORT).show();
        Log.i(String.valueOf(radioButton.getText()), "FEEDBACK");

        DBOperator.getInstance().execSQL(SQLCommand.QUERY_10, new String[]{stud_id, event_id, String.valueOf(radioButton.getText())});

        Toast.makeText(StudentSubmitFeedbackActivity.this, "FEEDBACK SUBMITTED", Toast.LENGTH_SHORT).show();

        //back to main student screen after feedback submission
        Intent intent = new Intent(this, StudentEventActivity.class);
        intent.putExtra("student_id", stud_id);
        this.startActivity(intent);

    }
}
