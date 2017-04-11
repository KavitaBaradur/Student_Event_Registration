package com.team2_wpi.android.student_event_registration.data;

/**
 * Created by kavitabaradur on 4/3/17.
 */

public abstract class SQLCommand {

    public static String QUERY_1 = "select STD_PASSWORD from STUDENT where STD_NAME=?";

    public static String QUERY_2 = "select * from Event";

    // init org event history
    public static String ORG_EVENT_HIS = "select E.Event_ID as _id, E.Event_Name as name, E.Event_Type as type, R.EventDate as date " +
                                         "from Event E, Reserve R " +
                                         "where E.Event_ID = R.Event_ID " +
                                         "and E.Org_ID = ?";
    // init org feedback history
    public static String ORG_FEEDBACK = "select E.Event_ID as _id, E.Event_Name as name, SUM(F.Great) as great, SUM(F.Fair) as fair, SUM(F.Need_Imp) as need_imp " +
                                         "from Event E, Feedback F " +
                                         "where E.Event_ID = F.Event_ID " +
                                         "and E.Org_ID = ? " +
                                         "group by E.Event_ID";
    // get available place
    public static String ORG_AVAL_PLACE = "select distinct P.Place_ID as _id, P.Place_Name as name " +
                                          "from Place P, Reserve R " +
                                          "where P.Place_ID = R.Place_ID " +
                                          "and P.Place_ID not in (select P2.Place_ID " +
                                                                 "from Place P2, Reserve R2 " +
                                                                 "where P2.Place_ID = R2.Place_ID " +
                                                                 "and R2.EventDate = ?";
    // get sponsors
    public static String ORG_ALL_SPON = "select Sponsor_ID as _id, Sponsor_Name as name " +
                                        "from Sponsor";
}
