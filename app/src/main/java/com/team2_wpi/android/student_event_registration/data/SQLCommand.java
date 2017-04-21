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

    // org login
    public static String ORG_LOGIN = "select Org_Password, Org_ID " +
                                     "from Organizer " +
                                     "where Org_Name = ?";

    // init org event history
    public static String ORG_EVENT_HIS = "select E.Event_ID as _id, E.Event_Name as name, E.Event_Type as type, R.EventDate as date, R.EventTime as time " +
                                         "from Event E, Reserve R " +
                                         "where E.Event_ID = R.Event_ID " +
                                         "and E.Org_ID = ?";
    // init org feedback history
    public static String ORG_FEEDBACK = "select E.Event_ID as _id, E.Event_Name as name, SUM(F.Good) as great, SUM(F.Fair) as fair, SUM(F.Need_Imp) as need_imp " +
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
                                                                 "and R2.EventDate = ?)";
    // get sponsors
    public static String ORG_ALL_SPON = "select Sponsor_ID as _id, Sponsor_Name as name " +
                                        "from Sponsor";
    // insert to event
    public static String ORG_INS_EVENT = "insert into Event(Org_ID, Event_Name, Event_Type) " +
                                         "values (?, ?, ?);";
    // insert to reserve
    public static String ORG_INS_RESERVE = "insert into Reserve(Event_ID, Place_ID, EventDate, EventTime) " +
                                           "values (?, ?, ?, ?);";
    // insert to event detail
    public static String ORG_INS_EVENT_DETAIL = "insert into Event_Detail(Event_ID, Sponsor_ID, Fund) " +
                                                "values (?, ?, ?)";
    // get last insert rowid
    public static String GET_LAST_ID = "select last_insert_rowid();";
}
