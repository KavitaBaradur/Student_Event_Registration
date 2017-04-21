package com.team2_wpi.android.student_event_registration;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.team2_wpi.android.student_event_registration.data.SQLCommand;
import com.team2_wpi.android.student_event_registration.util.DBOperator;

/**
 * Created by kavitabaradur on 4/4/17.
 */

public class StudentEventActivity extends AppCompatActivity implements View.OnClickListener, MenuItem.OnMenuItemClickListener {

    BottomNavigationView student_navigation;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_welcome);

        student_navigation = (BottomNavigationView) this.findViewById(R.id.student_bottom_navigation_window);

        Menu student_menu = student_navigation.getMenu();
        MenuItem profile = student_menu.findItem(R.id.action_profile);
        MenuItem logout = student_menu.findItem(R.id.action_logout);
        profile.setOnMenuItemClickListener(this);
        logout.setOnMenuItemClickListener(this);

    }


    @Override
    public void onClick(View v) {
/*code to display list of Events*/
        Cursor cursor1 = DBOperator.getInstance().execQuery(SQLCommand.QUERY_2);
        int id = v.getId();


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
            case R.id.action_reg_events:
                 /*view registered events*/


                break;
            case R.id.action_att_events:
                /*view attended events*/


                break;
            case R.id.action_logout:
                /*logout*/
                Intent intent2 = new Intent(this, WelcomeActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
                break;

    /*        if(id == R.id.action_profile)
        {
            Intent intent1 = new Intent(this, StudentDetailsActivity.class);
            String userid = getIntent().getExtras().getString("student_id");
            intent1.putExtra("student_id",userid);
            startActivity(intent1);
        }
        else if(id == R.id.action_logout)
        {
            Intent intent2 = new Intent(this, WelcomeActivity.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
        }

        return false;*/
        }
        return false;
    }
}
