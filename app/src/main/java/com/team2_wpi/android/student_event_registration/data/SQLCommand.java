package com.team2_wpi.android.student_event_registration.data;

/**
 * Created by kavitabaradur on 4/3/17.
 */

public abstract class SQLCommand {

    public static String QUERY_1 = "select STD_PASSWORD from STUDENT where STD_ID=?";

    public static String QUERY_2 = "select * from Event";

    public static String QUERY_3 = "select STD_NAME from Student where STD_ID=?";

    public static String QUERY_4 = "select STD_MAJOR from Student where STD_ID=?";

    public static String QUERY_5 = "update STUDENT set STD_PASSWORD=? where STD_ID=?";

}
