package com.team2_wpi.android.student_event_registration.data;

/**
 * Created by kavitabaradur on 4/3/17.
 */

public abstract class SQLCommand {

    public static String QUERY_1 = "select STD_PASSWORD from STUDENT where STD_NAME=?";

    public static String QUERY_2 = "select * from Event";

    // init org event history
    public static String ORG_EVENT_HIS = "select Event_ID as _id, Event_Name as name, Event_Type as type, Event_Date as date " +
                                         "from Event " +
                                         "where Org_ID = ?";
    // init org feedback history
    public static String ORG_FEEDBACK = "select E.Event_ID as _id, E.Event_Name as name, SUM(F.Great) as great, SUM(F.Fair) as fair, SUM(F.Need_Imp) as need_imp " +
                                         "from Event E, Feedback F " +
                                         "where E.Event_ID = F.Event_ID " +
                                         "and E.Org_ID = ? " +
                                         "group by E.Event_ID";
}
