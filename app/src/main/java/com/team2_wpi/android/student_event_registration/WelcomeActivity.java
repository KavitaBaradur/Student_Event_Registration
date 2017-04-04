package com.team2_wpi.android.student_event_registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.team2_wpi.android.student_event_registration.util.DBOperator;


public class WelcomeActivity extends AppCompatActivity implements OnClickListener {

    Button stud,org;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        stud = (Button)this.findViewById(R.id.student_btn);
        stud.setOnClickListener(this);
        org = (Button)this.findViewById(R.id.organizer_btn);
        org.setOnClickListener(this);

        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onClick(View v)
    {
        int id = v.getId();
        if (id == R.id.student_btn){
            Intent intent = new Intent(this, StudentActivity.class);
            this.startActivity(intent);

        }else if (id == R.id.organizer_btn){
            Intent intent = new Intent(this, OrganizerActivity.class);
            this.startActivity(intent);
        }
    }

}

