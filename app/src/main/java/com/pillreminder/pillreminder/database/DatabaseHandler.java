package com.pillreminder.pillreminder.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pillreminder.pillreminder.model.NewMedModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DatabaseHandler extends SQLiteOpenHelper {


    private static DatabaseHandler mInstance;
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 14;

    // Database Name
    private static final String DATABASE_NAME = "PillReminder";

    // Tables names
    private static final String TABLE_NEW_MEDICATION = "new_medication";
    private static final String TABLE_TAKE = "take_table";
    private static final String TABLE_SKIP = "skip_table";
    private static final String TABLE_MISSED_INTAKE = "missed_intake";


    // New Med Table Columns names
    private static final String NM_KEY_ID = "id";
    private static final String NM_KEY_MEDNAME = "medname";
    private static final String NM_KEY_MEDFORMNAME = "medformname";
    private static final String NM_KEY_MEDIMAGEID = "medimageid";
    private static final String NM_KEY_DOSAGECOUNT = "dosagecount";
    private static final String NM_KEY_DOSAGETYPE = "dosagetype";
    private static final String NM_KEY_WITHREGARD = "withregard";
    private static final String NM_KEY_STARTDATE = "startdate";
    private static final String NM_KEY_ENDDATE = "enddate";
    private static final String NM_KEY_FREQUENCY = "frequency";
    private static final String NM_KEY_TIMESDAY = "timesday";
    private static final String NM_KEY_INTAKETIME_1 = "intaketimeone";
    private static final String NM_KEY_INTAKETIME_2 = "intaketimetwo";
    private static final String NM_KEY_INTAKETIME_3 = "intaketimethree";
    private static final String NM_KEY_ISREFILLREMINDON = "isrefillremind";
    private static final String NM_KEY_PILLS_TOTAL = "totalpills";
    private static final String NM_KEY_REMINDBEFORE = "remindbefore";
    private static final String NM_KEY_REFILLREMINDERTIME = "refilltime";
    //    private static final String NM_KEY_ISINTAKENOTIFON = "isintakenotifon";
    private static final String NM_KEY_NOTIFTEXT = "notiftext";
    private static final String NM_KEY_CURRDATE = "curr_date";
    private static final String NM_KEY_TIMETEXT1 = "time1";
    private static final String NM_KEY_TIMETEXT2 = "time2";
    private static final String NM_KEY_TIMETEXT3 = "time3";
    private static final String NM_KEY_REFILLTIMETEXT = "refill_time";
    private static final String NM_KEY_CID = "cid";
    private static final String KEY_TAKEN_TIME = "taken_time";
    private static final String KEY_TAKEN_DATE = "taken_date";
    private static final String KEY_SKIP_TIME = "skip_time";
    private static final String KEY_SKIP_DATE = "skip_date";
    private static final String KEY_STATUS = "status";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHandler getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new DatabaseHandler(context.getApplicationContext());
        }
        return mInstance;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

//        String CREATE_PROFILE_TABLE = "CREATE TABLE " + TABLE_PROFILE_NAMES + "("
//                + KEY_CID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT,"
//                + KEY_DATE + " TEXT, " + KEY_RTYPE + " TEXT, " + KEY_UDATE + " TEXT " + ")";

//        String CREATE_CO_TABLE = "CREATE TABLE " + TABLE_CAREER_OBJ + "("
//                + CO_KEY_ID + " INTEGER PRIMARY KEY, " + CO_KEY_CID + " INTEGER,"
//                + CO_KEY_CO + " TEXT " + ")";
//
//        String CREATE_AI_TABLE = "CREATE TABLE " + TABLE_ACADEMIC_INFO + "("
//                + AI_KEY_ID + " INTEGER PRIMARY KEY, " + AI_KEY_CID + " INTEGER,"
//                + AI_KEY_PGUNIVERSITY + " TEXT, " + AI_KEY_PGNAME + " TEXT, "  + AI_KEY_PGYEAR + " TEXT, " + AI_KEY_PGPERSUING + " TEXT, " + AI_KEY_PGPERCENTAGE + " TEXT, "
//                + AI_KEY_GUNIVERSITY + " TEXT, " + AI_KEY_GNAME + " TEXT, "  + AI_KEY_GYEAR + " TEXT, " + AI_KEY_GPERSUING + " TEXT, " + AI_KEY_GPERCENTAGE + " TEXT, "
//                + AI_KEY_CBOARD + " TEXT, " + AI_KEY_CNAME + " TEXT, "  + AI_KEY_CYEAR + " TEXT, " + AI_KEY_CPERCENTAGE + " TEXT, "
//                + AI_KEY_SBOARD + " TEXT, " + AI_KEY_SNAME + " TEXT, "  + AI_KEY_SYEAR + " TEXT, " + AI_KEY_SPERCENTAGE + " TEXT, "
//                + AI_KEY_PGNYET + " TEXT " + ")";
//        String CREATE_WORK_EX_TABLE = "CREATE TABLE " + TABLE_WORK_EXPERIENCE + "("
//                + EX_KEY_ID + " INTEGER PRIMARY KEY, " + EX_CID + " INTEGER, "+ EX_JOBTITLE + " TEXT, "
//                + EX_JOBDESCRIPTION + " TEXT," + EX_COMPANYNAME
//                + " TEXT, " + EX_STARTDATE + " TEXT, " + EX_ENDDATE+ " TEXT, " + EX_STILLWORKING+ " TEXT " + ")" ;
//
//        String CREATE_DECLARATION_TABLE = "CREATE TABLE " + TABLE_DECLARATION + "("
//                + DC_KEY_ID + " INTEGER PRIMARY KEY, " + DC_KEY_CID + " INTEGER,"
//                + DC_KEY_DECLARATION + " TEXT, " + DC_KEY_DATE + " TEXT, " + DC_KEY_PLACE + " TEXT " + ")";


        String CREATE_NEW_MED_TABLE = "CREATE TABLE " + TABLE_NEW_MEDICATION + "("
                + NM_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NM_KEY_MEDNAME + " TEXT,"
                + NM_KEY_MEDFORMNAME + " TEXT, " + NM_KEY_MEDIMAGEID + " TEXT, " + NM_KEY_DOSAGECOUNT + " TEXT, "
                + NM_KEY_DOSAGETYPE + " TEXT, " + NM_KEY_WITHREGARD + " TEXT, " + NM_KEY_STARTDATE + " LONG, " + NM_KEY_ENDDATE + " LONG, "
                + NM_KEY_FREQUENCY + " TEXT, " + NM_KEY_TIMESDAY + " TEXT, " + NM_KEY_INTAKETIME_1 + " LONG, "
                + NM_KEY_INTAKETIME_2 + " LONG, " + NM_KEY_INTAKETIME_3 + " LONG, " + NM_KEY_ISREFILLREMINDON + " TEXT, "
                + NM_KEY_PILLS_TOTAL + " TEXT, " + NM_KEY_REMINDBEFORE + " TEXT, " + NM_KEY_REFILLREMINDERTIME + " LONG, "
                + NM_KEY_NOTIFTEXT + " TEXT, " + NM_KEY_CURRDATE + " TEXT, " + NM_KEY_TIMETEXT1 + " TEXT, "
                + NM_KEY_TIMETEXT2 + " TEXT, " + NM_KEY_TIMETEXT3 + " TEXT, " + NM_KEY_REFILLTIMETEXT + " TEXT, " + NM_KEY_CID + " INTEGER, "
                + KEY_STATUS + " TEXT " + ")";


        String CREATE_TAKE_TABLE = "CREATE TABLE " + TABLE_TAKE + "("
                + NM_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NM_KEY_MEDNAME + " TEXT,"
                + NM_KEY_MEDFORMNAME + " TEXT, " + NM_KEY_MEDIMAGEID + " TEXT, " + NM_KEY_DOSAGECOUNT + " TEXT, "
                + NM_KEY_DOSAGETYPE + " TEXT, " +
                NM_KEY_WITHREGARD + " TEXT, " + NM_KEY_STARTDATE + " LONG, " + NM_KEY_ENDDATE + " LONG, "
                + NM_KEY_FREQUENCY + " TEXT, " + NM_KEY_TIMESDAY + " TEXT, " + NM_KEY_INTAKETIME_1 + " LONG, "
                + NM_KEY_INTAKETIME_2 + " LONG, " + NM_KEY_INTAKETIME_3 + " LONG, " + NM_KEY_ISREFILLREMINDON + " TEXT, "
                + NM_KEY_PILLS_TOTAL + " TEXT, " + NM_KEY_REMINDBEFORE + " TEXT, " + NM_KEY_REFILLREMINDERTIME + " LONG, " +
                NM_KEY_NOTIFTEXT + " TEXT, " + NM_KEY_CURRDATE + " TEXT, " + NM_KEY_TIMETEXT1 + " TEXT, " +
                NM_KEY_TIMETEXT2 + " TEXT, " + NM_KEY_TIMETEXT3 + " TEXT, " + NM_KEY_REFILLTIMETEXT + " TEXT, " + NM_KEY_CID + " INTEGER, "
                + KEY_TAKEN_TIME + " TEXT, " + KEY_TAKEN_DATE + " TEXT " + ")";

        String CREATE_SKIP_TABLE = "CREATE TABLE " + TABLE_SKIP + "("
                + NM_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NM_KEY_MEDNAME + " TEXT,"
                + NM_KEY_MEDFORMNAME + " TEXT, " + NM_KEY_MEDIMAGEID + " TEXT, " + NM_KEY_DOSAGECOUNT + " TEXT, "
                + NM_KEY_DOSAGETYPE + " TEXT, " +
                NM_KEY_WITHREGARD + " TEXT, " + NM_KEY_STARTDATE + " LONG, " + NM_KEY_ENDDATE + " LONG, "
                + NM_KEY_FREQUENCY + " TEXT, " + NM_KEY_TIMESDAY + " TEXT, " + NM_KEY_INTAKETIME_1 + " LONG, "
                + NM_KEY_INTAKETIME_2 + " LONG, " + NM_KEY_INTAKETIME_3 + " LONG, " + NM_KEY_ISREFILLREMINDON + " TEXT, "
                + NM_KEY_PILLS_TOTAL + " TEXT, " + NM_KEY_REMINDBEFORE + " TEXT, " + NM_KEY_REFILLREMINDERTIME + " LONG, " +
                NM_KEY_NOTIFTEXT + " TEXT, " + NM_KEY_CURRDATE + " TEXT, " + NM_KEY_TIMETEXT1 + " TEXT, " +
                NM_KEY_TIMETEXT2 + " TEXT, " + NM_KEY_TIMETEXT3 + " TEXT, " + NM_KEY_REFILLTIMETEXT + " TEXT, " + NM_KEY_CID + " INTEGER, "
                + KEY_SKIP_TIME + " TEXT, " + KEY_SKIP_DATE + " TEXT " + ")";


        String CREATE_MISSED_INTAKE_TABLE = "CREATE TABLE " + TABLE_MISSED_INTAKE + "("
                + NM_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NM_KEY_MEDNAME + " TEXT,"
                + NM_KEY_MEDFORMNAME + " TEXT, " + NM_KEY_MEDIMAGEID + " TEXT, " + NM_KEY_DOSAGECOUNT + " TEXT, "
                + NM_KEY_DOSAGETYPE + " TEXT, " + NM_KEY_WITHREGARD + " TEXT, " + NM_KEY_STARTDATE + " LONG, " + NM_KEY_ENDDATE + " LONG, "
                + NM_KEY_FREQUENCY + " TEXT, " + NM_KEY_TIMESDAY + " TEXT, " + NM_KEY_INTAKETIME_1 + " LONG, "
                + NM_KEY_INTAKETIME_2 + " LONG, " + NM_KEY_INTAKETIME_3 + " LONG, " + NM_KEY_ISREFILLREMINDON + " TEXT, "
                + NM_KEY_PILLS_TOTAL + " TEXT, " + NM_KEY_REMINDBEFORE + " TEXT, " + NM_KEY_REFILLREMINDERTIME + " LONG, "
                + NM_KEY_NOTIFTEXT + " TEXT, " + NM_KEY_CURRDATE + " TEXT, " + NM_KEY_TIMETEXT1 + " TEXT, "
                + NM_KEY_TIMETEXT2 + " TEXT, " + NM_KEY_TIMETEXT3 + " TEXT, " + NM_KEY_REFILLTIMETEXT + " TEXT, " + NM_KEY_CID + " INTEGER, "
                + KEY_STATUS + " TEXT " + ")";
//        db.execSQL(CREATE_PROFILE_TABLE);
//        db.execSQL(CREATE_PINFO_TABLE);
//        db.execSQL(CREATE_CO_TABLE);
//        db.execSQL(CREATE_AI_TABLE);
//        db.execSQL(CREATE_WORK_EX_TABLE);
//        db.execSQL(CREATE_DECLARATION_TABLE);
        db.execSQL(CREATE_NEW_MED_TABLE);
        db.execSQL(CREATE_TAKE_TABLE);
        db.execSQL(CREATE_SKIP_TABLE);
        db.execSQL(CREATE_MISSED_INTAKE_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NEW_MEDICATION);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAKE);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SKIP);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MISSED_INTAKE);

        onCreate(db);
    }


    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

//    public void updateDate(int cid) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        String updatedDate=getDateTime();
//        // Inserting Row
//       db.execSQL("update profiles set cudate='" +updatedDate+ "' WHERE " + KEY_CID + " = " + cid);
//    }


    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Getting All MedData
    public List<NewMedModel> getAllMedData() {
        List<NewMedModel> newMedList = new ArrayList<NewMedModel>();
        SQLiteDatabase db = this.getReadableDatabase();

        //for reverse order
        //Cursor cursor = db.rawQuery("select * from " + TABLE_PROFILE_NAMES + " order by cid desc", null);

        Cursor cursor = db.rawQuery("select * from " + TABLE_NEW_MEDICATION, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                NewMedModel nm = new NewMedModel();
                nm.setID(Integer.parseInt(cursor.getString(0)));
                nm.setMedName(cursor.getString(1));
                nm.setMedFormName(cursor.getString(2));
                nm.setMedImageID(cursor.getString(3));
                nm.setDosageCount(cursor.getString(4));
                nm.setDosageType(cursor.getString(5));
                nm.setIsWithRegardOrNot(cursor.getString(6));
                nm.setStartDate(Long.parseLong(cursor.getString(7)));
                nm.setEndDate(Long.parseLong(cursor.getString(8)));
                nm.setFrequency(cursor.getString(9));
                nm.setTimesInDay(cursor.getString(10));
                nm.setIntakeTime1(Long.parseLong(cursor.getString(11)));
                nm.setIntakeTime2(Long.parseLong(cursor.getString(12)));
                nm.setIntakeTime3(Long.parseLong(cursor.getString(13)));
                nm.setIsRefillReminderOn(cursor.getString(14));
                nm.setTotalPills(cursor.getString(15));
                nm.setRemindBeforeCount(cursor.getString(16));
                nm.setRefillReminderTime(Long.parseLong(cursor.getString(17)));
                nm.setNotifText(cursor.getString(18));
                nm.setCurrDate(cursor.getString(19));
                nm.setTime1(cursor.getString(20));
                nm.setTime2(cursor.getString(21));
                nm.setTime3(cursor.getString(22));
                nm.setRefillTimeText(cursor.getString(23));
                nm.setcID(Integer.parseInt(cursor.getString(24)));
                nm.setStatus(cursor.getString(25));

                // Adding profiles to list
                newMedList.add(nm);
            } while (cursor.moveToNext());
        }

        // return profile list
        return newMedList;
    }

    //
    //getting single record from NEW MED
    public List<NewMedModel> getCdayMedModel(String cDate) {
        SQLiteDatabase db = this.getReadableDatabase();

//        String selectQuery = "SELECT  * FROM " + TABLE_NEW_MEDICATION + " WHERE "
//                + NM_KEY_CURRDATE + " = '" + cDate +"'";

        String sql = "SELECT * FROM new_medication WHERE curr_date = '" + cDate + "'";

        Cursor c = db.rawQuery(sql, null);

//        Cursor c = db.rawQuery(selectQuery, null);
        List<NewMedModel> cDayMedList = new ArrayList<NewMedModel>();
//        NewMedModel nm = new NewMedModel();
        NewMedModel nm;
        Log.e("Cursor count", c.getCount() + "" + cDate);
        if (c != null && c.moveToFirst()) {

            Log.e("C not null", "C not null");
            do {
                nm = new NewMedModel();
                nm.setID(Integer.parseInt(c.getString(c.getColumnIndex(NM_KEY_ID))));
                nm.setMedName(c.getString(c.getColumnIndex(NM_KEY_MEDNAME)));
                nm.setMedFormName(c.getString(c.getColumnIndex(NM_KEY_MEDFORMNAME)));
                nm.setMedImageID(c.getString(c.getColumnIndex(NM_KEY_MEDIMAGEID)));
                nm.setDosageCount(c.getString(c.getColumnIndex(NM_KEY_DOSAGECOUNT)));
                nm.setDosageType(c.getString(c.getColumnIndex(NM_KEY_DOSAGETYPE)));
                nm.setIsWithRegardOrNot(c.getString(c.getColumnIndex(NM_KEY_WITHREGARD)));
                nm.setStartDate(Long.parseLong(c.getString(c.getColumnIndex(NM_KEY_STARTDATE))));
                nm.setEndDate(Long.parseLong(c.getString(c.getColumnIndex(NM_KEY_ENDDATE))));
                nm.setFrequency(c.getString(c.getColumnIndex(NM_KEY_FREQUENCY)));
                nm.setTimesInDay(c.getString(c.getColumnIndex(NM_KEY_TIMESDAY)));
                nm.setIntakeTime1(Long.parseLong(c.getString(c.getColumnIndex(NM_KEY_INTAKETIME_1))));
                nm.setIntakeTime2(Long.parseLong(c.getString(c.getColumnIndex(NM_KEY_INTAKETIME_2))));
                nm.setIntakeTime3(Long.parseLong(c.getString(c.getColumnIndex(NM_KEY_INTAKETIME_3))));
                nm.setIsRefillReminderOn(c.getString(c.getColumnIndex(NM_KEY_ISREFILLREMINDON)));
                nm.setTotalPills(c.getString(c.getColumnIndex(NM_KEY_PILLS_TOTAL)));
                nm.setRemindBeforeCount(c.getString(c.getColumnIndex(NM_KEY_REMINDBEFORE)));
                nm.setRefillReminderTime(Long.parseLong(c.getString(c.getColumnIndex(NM_KEY_REFILLREMINDERTIME))));
                nm.setNotifText(c.getString(c.getColumnIndex(NM_KEY_NOTIFTEXT)));
                nm.setCurrDate(c.getString(c.getColumnIndex(NM_KEY_CURRDATE)));
                nm.setTime1(c.getString(c.getColumnIndex(NM_KEY_TIMETEXT1)));
                nm.setTime2(c.getString(c.getColumnIndex(NM_KEY_TIMETEXT2)));
                nm.setTime3(c.getString(c.getColumnIndex(NM_KEY_TIMETEXT3)));
                nm.setRefillTimeText(c.getString(c.getColumnIndex(NM_KEY_REFILLTIMETEXT)));
                nm.setcID(Integer.parseInt(c.getString(c.getColumnIndex(NM_KEY_CID))));
                nm.setStatus(c.getString(c.getColumnIndex(KEY_STATUS)));

                cDayMedList.add(nm);
                Log.e("MEdName", c.getString(c.getColumnIndex(NM_KEY_MEDNAME)));
            } while (c.moveToNext());

        } else {
            return null;
        }
        return cDayMedList;
    }

    //getting single record from Intake info
    public List<NewMedModel> getSingleIntake(int cid) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_NEW_MEDICATION + " WHERE "
                + NM_KEY_CID + " = " + cid;

//    String sql = "SELECT * FROM new_medication WHERE curr_date = '" + cDate + "'";

        Cursor c = db.rawQuery(selectQuery, null);
        Log.e("IntakeCursorcount", c.getCount() + "");
        List<NewMedModel> intakeList = new ArrayList<NewMedModel>();
        NewMedModel nm;
        if (c != null && c.moveToFirst()) {

            Log.e("C not null", "C not null");
            nm = new NewMedModel();
            nm.setID(Integer.parseInt(c.getString(c.getColumnIndex(NM_KEY_ID))));
            nm.setMedName(c.getString(c.getColumnIndex(NM_KEY_MEDNAME)));
            nm.setMedFormName(c.getString(c.getColumnIndex(NM_KEY_MEDFORMNAME)));
            nm.setMedImageID(c.getString(c.getColumnIndex(NM_KEY_MEDIMAGEID)));
            nm.setDosageCount(c.getString(c.getColumnIndex(NM_KEY_DOSAGECOUNT)));
            nm.setDosageType(c.getString(c.getColumnIndex(NM_KEY_DOSAGETYPE)));
            nm.setIsWithRegardOrNot(c.getString(c.getColumnIndex(NM_KEY_WITHREGARD)));
            nm.setStartDate(Long.parseLong(c.getString(c.getColumnIndex(NM_KEY_STARTDATE))));
            nm.setEndDate(Long.parseLong(c.getString(c.getColumnIndex(NM_KEY_ENDDATE))));
            nm.setFrequency(c.getString(c.getColumnIndex(NM_KEY_FREQUENCY)));
            nm.setTimesInDay(c.getString(c.getColumnIndex(NM_KEY_TIMESDAY)));
            nm.setIntakeTime1(Long.parseLong(c.getString(c.getColumnIndex(NM_KEY_INTAKETIME_1))));
            nm.setIntakeTime2(Long.parseLong(c.getString(c.getColumnIndex(NM_KEY_INTAKETIME_2))));
            nm.setIntakeTime3(Long.parseLong(c.getString(c.getColumnIndex(NM_KEY_INTAKETIME_3))));
            nm.setIsRefillReminderOn(c.getString(c.getColumnIndex(NM_KEY_ISREFILLREMINDON)));
            nm.setTotalPills(c.getString(c.getColumnIndex(NM_KEY_PILLS_TOTAL)));
            nm.setRemindBeforeCount(c.getString(c.getColumnIndex(NM_KEY_REMINDBEFORE)));
            nm.setRefillReminderTime(Long.parseLong(c.getString(c.getColumnIndex(NM_KEY_REFILLREMINDERTIME))));
            nm.setNotifText(c.getString(c.getColumnIndex(NM_KEY_NOTIFTEXT)));
            nm.setCurrDate(c.getString(c.getColumnIndex(NM_KEY_CURRDATE)));
            nm.setTime1(c.getString(c.getColumnIndex(NM_KEY_TIMETEXT1)));
            nm.setTime2(c.getString(c.getColumnIndex(NM_KEY_TIMETEXT2)));
            nm.setTime3(c.getString(c.getColumnIndex(NM_KEY_TIMETEXT3)));
            nm.setRefillTimeText(c.getString(c.getColumnIndex(NM_KEY_REFILLTIMETEXT)));
            nm.setcID(Integer.parseInt(c.getString(c.getColumnIndex(NM_KEY_CID))));
            nm.setStatus(c.getString(c.getColumnIndex(KEY_STATUS)));

            intakeList.add(nm);

        } else {
            return null;
        }
        return intakeList;
    }

    //Add NewMed Info
    public long insNewMeaValues(NewMedModel nm) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NM_KEY_MEDNAME, nm.getMedName());
        values.put(NM_KEY_MEDFORMNAME, nm.getMedFormName());
        values.put(NM_KEY_MEDIMAGEID, nm.getMedImageID());
        values.put(NM_KEY_DOSAGECOUNT, nm.getDosageCount());
        values.put(NM_KEY_DOSAGETYPE, nm.getDosageType());
        values.put(NM_KEY_WITHREGARD, nm.getIsWithRegardOrNot());
        values.put(NM_KEY_STARTDATE, nm.getStartDate());
        values.put(NM_KEY_ENDDATE, nm.getEndDate());
        values.put(NM_KEY_FREQUENCY, nm.getFrequency());
        values.put(NM_KEY_TIMESDAY, nm.getTimesInDay());
        values.put(NM_KEY_INTAKETIME_1, nm.getIntakeTime1());
        values.put(NM_KEY_INTAKETIME_2, nm.getIntakeTime2());
        values.put(NM_KEY_INTAKETIME_3, nm.getIntakeTime3());
        values.put(NM_KEY_ISREFILLREMINDON, nm.getIsRefillReminderOn());
        values.put(NM_KEY_PILLS_TOTAL, nm.getTotalPills());
        values.put(NM_KEY_REMINDBEFORE, nm.getRemindBeforeCount());
        values.put(NM_KEY_REFILLREMINDERTIME, nm.getRefillReminderTime());
        values.put(NM_KEY_NOTIFTEXT, nm.getNotifText());
        values.put(NM_KEY_CURRDATE, nm.getCurrDate());
        values.put(NM_KEY_TIMETEXT1, nm.getTime1());
        values.put(NM_KEY_TIMETEXT2, nm.getTime2());
        values.put(NM_KEY_TIMETEXT3, nm.getTime3());
        values.put(NM_KEY_REFILLTIMETEXT, nm.getRefillTimeText());
        values.put(NM_KEY_CID, nm.getcID());
        values.put(KEY_STATUS, nm.getStatus());

        // Inserting Row
        long n = db.insert(TABLE_NEW_MEDICATION, null, values);
        //db.close(); // Closing database connection
        return n;
    }

    //Add Missed intake Info
    public long insMissedIntake(NewMedModel nm) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NM_KEY_MEDNAME, nm.getMedName());
        values.put(NM_KEY_MEDFORMNAME, nm.getMedFormName());
        values.put(NM_KEY_MEDIMAGEID, nm.getMedImageID());
        values.put(NM_KEY_DOSAGECOUNT, nm.getDosageCount());
        values.put(NM_KEY_DOSAGETYPE, nm.getDosageType());
        values.put(NM_KEY_WITHREGARD, nm.getIsWithRegardOrNot());
        values.put(NM_KEY_STARTDATE, nm.getStartDate());
        values.put(NM_KEY_ENDDATE, nm.getEndDate());
        values.put(NM_KEY_FREQUENCY, nm.getFrequency());
        values.put(NM_KEY_TIMESDAY, nm.getTimesInDay());
        values.put(NM_KEY_INTAKETIME_1, nm.getIntakeTime1());
        values.put(NM_KEY_INTAKETIME_2, nm.getIntakeTime2());
        values.put(NM_KEY_INTAKETIME_3, nm.getIntakeTime3());
        values.put(NM_KEY_ISREFILLREMINDON, nm.getIsRefillReminderOn());
        values.put(NM_KEY_PILLS_TOTAL, nm.getTotalPills());
        values.put(NM_KEY_REMINDBEFORE, nm.getRemindBeforeCount());
        values.put(NM_KEY_REFILLREMINDERTIME, nm.getRefillReminderTime());
        values.put(NM_KEY_NOTIFTEXT, nm.getNotifText());
        values.put(NM_KEY_CURRDATE, nm.getCurrDate());
        values.put(NM_KEY_TIMETEXT1, nm.getTime1());
        values.put(NM_KEY_TIMETEXT2, nm.getTime2());
        values.put(NM_KEY_TIMETEXT3, nm.getTime3());
        values.put(NM_KEY_REFILLTIMETEXT, nm.getRefillTimeText());
        values.put(NM_KEY_CID, nm.getcID());
        values.put(KEY_STATUS, nm.getStatus());

        // Inserting Row
        long n = db.insert(TABLE_MISSED_INTAKE, null, values);
        //db.close(); // Closing database connection
        return n;
    }
    //ins Take Info
    public long insTakeValues(NewMedModel nm) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NM_KEY_MEDNAME, nm.getMedName());
        values.put(NM_KEY_MEDFORMNAME, nm.getMedFormName());
        values.put(NM_KEY_MEDIMAGEID, nm.getMedImageID());
        values.put(NM_KEY_DOSAGECOUNT, nm.getDosageCount());
        values.put(NM_KEY_DOSAGETYPE, nm.getDosageType());
        values.put(NM_KEY_WITHREGARD, nm.getIsWithRegardOrNot());
        values.put(NM_KEY_STARTDATE, nm.getStartDate());
        values.put(NM_KEY_ENDDATE, nm.getEndDate());
        values.put(NM_KEY_FREQUENCY, nm.getFrequency());
        values.put(NM_KEY_TIMESDAY, nm.getTimesInDay());
        values.put(NM_KEY_INTAKETIME_1, nm.getIntakeTime1());
        values.put(NM_KEY_INTAKETIME_2, nm.getIntakeTime2());
        values.put(NM_KEY_INTAKETIME_3, nm.getIntakeTime3());
        values.put(NM_KEY_ISREFILLREMINDON, nm.getIsRefillReminderOn());
        values.put(NM_KEY_PILLS_TOTAL, nm.getTotalPills());
        values.put(NM_KEY_REMINDBEFORE, nm.getRemindBeforeCount());
        values.put(NM_KEY_REFILLREMINDERTIME, nm.getRefillReminderTime());
        values.put(NM_KEY_NOTIFTEXT, nm.getNotifText());
        values.put(NM_KEY_CURRDATE, nm.getCurrDate());
        values.put(NM_KEY_TIMETEXT1, nm.getTime1());
        values.put(NM_KEY_TIMETEXT2, nm.getTime2());
        values.put(NM_KEY_TIMETEXT3, nm.getTime3());
        values.put(NM_KEY_REFILLTIMETEXT, nm.getRefillTimeText());
        values.put(NM_KEY_CID, nm.getcID());
        values.put(KEY_TAKEN_TIME, nm.getTaken_time());
        values.put(KEY_TAKEN_DATE, nm.getTaken_date());
//        values.put(KEY_TAKEN_TIME, ReusableCode.getFullDate(Calendar.getInstance().getTimeInMillis()));
//        values.put(KEY_TAKEN_DATE, ReusableCode.getDate(Calendar.getInstance().getTimeInMillis()));
        // Inserting Row
        long n = db.insert(TABLE_TAKE, null, values);
        //db.close(); // Closing database connection
        return n;
    }

    //ins skip Info
    public long insSkipValues(NewMedModel nm) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NM_KEY_MEDNAME, nm.getMedName());
        values.put(NM_KEY_MEDFORMNAME, nm.getMedFormName());
        values.put(NM_KEY_MEDIMAGEID, nm.getMedImageID());
        values.put(NM_KEY_DOSAGECOUNT, nm.getDosageCount());
        values.put(NM_KEY_DOSAGETYPE, nm.getDosageType());
        values.put(NM_KEY_WITHREGARD, nm.getIsWithRegardOrNot());
        values.put(NM_KEY_STARTDATE, nm.getStartDate());
        values.put(NM_KEY_ENDDATE, nm.getEndDate());
        values.put(NM_KEY_FREQUENCY, nm.getFrequency());
        values.put(NM_KEY_TIMESDAY, nm.getTimesInDay());
        values.put(NM_KEY_INTAKETIME_1, nm.getIntakeTime1());
        values.put(NM_KEY_INTAKETIME_2, nm.getIntakeTime2());
        values.put(NM_KEY_INTAKETIME_3, nm.getIntakeTime3());
        values.put(NM_KEY_ISREFILLREMINDON, nm.getIsRefillReminderOn());
        values.put(NM_KEY_PILLS_TOTAL, nm.getTotalPills());
        values.put(NM_KEY_REMINDBEFORE, nm.getRemindBeforeCount());
        values.put(NM_KEY_REFILLREMINDERTIME, nm.getRefillReminderTime());
        values.put(NM_KEY_NOTIFTEXT, nm.getNotifText());
        values.put(NM_KEY_CURRDATE, nm.getCurrDate());
        values.put(NM_KEY_TIMETEXT1, nm.getTime1());
        values.put(NM_KEY_TIMETEXT2, nm.getTime2());
        values.put(NM_KEY_TIMETEXT3, nm.getTime3());
        values.put(NM_KEY_REFILLTIMETEXT, nm.getRefillTimeText());
        values.put(NM_KEY_CID, nm.getcID());
        values.put(KEY_SKIP_TIME, nm.getSkip_time());
        values.put(KEY_SKIP_DATE, nm.getSkip_date());
        // Inserting Row
        long n = db.insert(TABLE_SKIP, null, values);
        //db.close(); // Closing database connection
        return n;
    }

    public long updateStatus(String status, int cid) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues data = new ContentValues();
        data.put(KEY_STATUS, status);
        long n = db.update(TABLE_NEW_MEDICATION, data, "cid=" + cid, null);
        return n;
    }

    public long updateStatusIntake(String status, int cid) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues data = new ContentValues();
        data.put(KEY_STATUS, status);
        long n = db.update(TABLE_MISSED_INTAKE, data, "cid=" + cid, null);
        return n;
    }

    // Getting All MissedIntakesData
    public List<NewMedModel> getAllMissedIntake() {
        List<NewMedModel> missedIntakeList = new ArrayList<NewMedModel>();
        SQLiteDatabase db = this.getReadableDatabase();

        //for reverse order
        //Cursor cursor = db.rawQuery("select * from " + TABLE_PROFILE_NAMES + " order by cid desc", null);

        Cursor cursor = db.rawQuery("select * from " + TABLE_MISSED_INTAKE, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                NewMedModel nm = new NewMedModel();
                nm.setID(Integer.parseInt(cursor.getString(0)));
                nm.setMedName(cursor.getString(1));
                nm.setMedFormName(cursor.getString(2));
                nm.setMedImageID(cursor.getString(3));
                nm.setDosageCount(cursor.getString(4));
                nm.setDosageType(cursor.getString(5));
                nm.setIsWithRegardOrNot(cursor.getString(6));
                nm.setStartDate(Long.parseLong(cursor.getString(7)));
                nm.setEndDate(Long.parseLong(cursor.getString(8)));
                nm.setFrequency(cursor.getString(9));
                nm.setTimesInDay(cursor.getString(10));
                nm.setIntakeTime1(Long.parseLong(cursor.getString(11)));
                nm.setIntakeTime2(Long.parseLong(cursor.getString(12)));
                nm.setIntakeTime3(Long.parseLong(cursor.getString(13)));
                nm.setIsRefillReminderOn(cursor.getString(14));
                nm.setTotalPills(cursor.getString(15));
                nm.setRemindBeforeCount(cursor.getString(16));
                nm.setRefillReminderTime(Long.parseLong(cursor.getString(17)));
                nm.setNotifText(cursor.getString(18));
                nm.setCurrDate(cursor.getString(19));
                nm.setTime1(cursor.getString(20));
                nm.setTime2(cursor.getString(21));
                nm.setTime3(cursor.getString(22));
                nm.setRefillTimeText(cursor.getString(23));
                nm.setcID(Integer.parseInt(cursor.getString(24)));
                nm.setStatus(cursor.getString(25));

                // Adding profiles to list
                missedIntakeList.add(nm);
            } while (cursor.moveToNext());
        }

        // return profile list
        return missedIntakeList;
    }

//
//    //Update Personal Info
//    public long updatePersonalInfo(PersonalInfo pi) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(PI_KEY_FULLNAME, pi.getpFullname());
//        values.put(PI_KEY_PHONE, pi.getpPhoneNumber());
//        values.put(PI_KEY_ALT_PHONE, pi.getpAltPhoneNumber());
//        values.put(PI_KEY_EMAIL, pi.getpEmail());
//        values.put(PI_KEY_ALT_EMAIL, pi.getpAltEmail());
//        values.put(PI_KEY_HOUSE, pi.getpHouse());
//        values.put(PI_KEY_STREET, pi.getpStreet());
//        values.put(PI_KEY_ADDRESS, pi.getpAddress());
//        values.put(PI_KEY_COUNTRY, pi.getpCountry());
//        values.put(PI_KEY_CITY, pi.getpCity());
//        values.put(PI_KEY_PINCODE, pi.getpPincode());
//        values.put(PI_KEY_PAN, pi.getpPan());
//        values.put(PI_KEY_PASSPORT, pi.getpPassport());
//        values.put(PI_KEY_DOB, pi.getpDob());
//        values.put(PI_KEY_GENDER, pi.getpGender());
//        values.put(PI_KEY_MARITAL_STATUS, pi.getpMStatus());
//        values.put(PI_KEY_PHOTO, pi.getpImage());
//
//        // Inserting Row
//        long n = db.update(TABLE_PERSONAL_INFO, values, "cid=" + pi.getCid(), null);
//        ;
//        //db.close(); // Closing database connection
//        return n;
//    }
//
//    // Getting single contact
//    public String getContact() {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.rawQuery("select *from personal_info where pid = 1 ", null);
//
//        String name = cursor.getString(1);
//
//
//        return name;
//
//
//    }
//
//    public int getProfilesCount() {
//        String countQuery = "SELECT  * FROM " + TABLE_PERSONAL_INFO;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//        int cnt = cursor.getCount();
//        cursor.close();
//        return cnt;
//    }
//
//    private byte[] getBlob(Cursor cursor, String colName, byte[] defaultValue) {
//        try {
//            int colIndex;
//            if (cursor != null && (colIndex = cursor.getColumnIndex(colName)) > -1
//                    && !cursor.isNull(colIndex))
//                return cursor.getBlob(colIndex);
//            return defaultValue;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return defaultValue;
//        }
//    }
//
//
//    // Adding Career Objective
//    public long addCO(CareerObjective co) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//
//        ContentValues values = new ContentValues();
//        values.put(CO_KEY_CID, co.getCid());
//        values.put(CO_KEY_CO, co.getCo());
//
//        // Inserting Row
//        long n = db.insert(TABLE_CAREER_OBJ, null, values);
//        //db.close(); // Closing database connection
//        return n;
//    }
//
//    //Adding Work Experience
//    public long addWorkExperience(WorkExperience we){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        String stw= we.getExStlworking();
//
//        ContentValues values = new ContentValues();
//        values.put(EX_CID, we.getexCid());
//        values.put(EX_JOBTITLE, we.getexJobtitle());
//        values.put(EX_JOBDESCRIPTION, we.getexJobdescription());
//        values.put(EX_COMPANYNAME, we.getexCompanyname());
//        values.put(EX_STARTDATE, we.getexStartdate());
//        values.put(EX_ENDDATE, we.getexEnddate());
//        values.put(EX_STILLWORKING, we.getExStlworking());
//        long n = db.insert(TABLE_WORK_EXPERIENCE, null, values);
//        return n;
//
//    }
//    public long updateExperience(WorkExperience we) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(EX_CID, we.getexCid());
//        values.put(EX_JOBTITLE, we.getexJobtitle());
//        values.put(EX_JOBDESCRIPTION, we.getexJobdescription());
//        values.put(EX_COMPANYNAME, we.getexCompanyname());
//        values.put(EX_STARTDATE, we.getexStartdate());
//        values.put(EX_ENDDATE, we.getexEnddate());
//        values.put(EX_STILLWORKING, we.getExStlworking());
//
//
//        // Updating Row
//        long n = db.update(TABLE_WORK_EXPERIENCE, values, "exJobtitle= '" + we.getexJobtitle() + "';", null);
//        //db.close(); // Closing database connection
//        return n;
//    }
//    // Getting All Experience details
//    public List<WorkExperience> getAllExperienceDetails(int id) {
//        List<WorkExperience> workExperienceList = new ArrayList<WorkExperience>();
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        //for reverse order
//        //Cursor cursor = db.rawQuery("select * from " + TABLE_PROFILE_NAMES + " order by cid desc", null);
//
//        Cursor cursor = db.rawQuery("select * from " + TABLE_WORK_EXPERIENCE + " where cid= " + id, null);
//
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                WorkExperience workExperience = new WorkExperience();
//                workExperience.setexCid(Integer.parseInt(cursor.getString(cursor.getColumnIndex(EX_CID))));
//                workExperience.setexJobtitle(cursor.getString(cursor.getColumnIndex(EX_JOBTITLE)));
//                workExperience.setexJobdescription(cursor.getString(cursor.getColumnIndex(EX_JOBDESCRIPTION)));
//                workExperience.setexCompanyname(cursor.getString(cursor.getColumnIndex(EX_COMPANYNAME)));
//                workExperience.setexStartdate(cursor.getString(cursor.getColumnIndex(EX_STARTDATE)));
//                workExperience.setexEnddate(cursor.getString(cursor.getColumnIndex(EX_ENDDATE)));
//                workExperience.setExStlworking(cursor.getString(cursor.getColumnIndex(EX_STILLWORKING)));
//                // Adding profiles to list
//                workExperienceList.add(workExperience);
//            } while (cursor.moveToNext());
//        }
//
//        // return profile list
//        return workExperienceList;
//    }
//
//
//
//    // delete a WorkExperience from list
//    public boolean deleteWorkExperience(String title) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        //Cursor cursor = db.rawQuery(" delete from " + TABLE_PROFILE_NAMES + " where " + KEY_CID + " = " + id, null);
//
//        boolean n = db.delete(TABLE_WORK_EXPERIENCE, EX_JOBTITLE + "= '" + title + "';", null) > 0;
//
//        db.close();
//        return n;
//    }
//
//
//
//    //Update Career Objective
//    public long updateCO(CareerObjective co) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(CO_KEY_CID, co.getCid());
//        values.put(CO_KEY_CO, co.getCo());
//
//        // Inserting Row
//        long n = db.update(TABLE_CAREER_OBJ, values, "cid=" + co.getCid(), null);
//        //db.close(); // Closing database connection
//        return n;
//    }
//
//    //getting single record
//    public CareerObjective getSingleCO(int cid) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        String selectQuery = "SELECT  * FROM " + TABLE_CAREER_OBJ + " WHERE "
//                + KEY_CID + " = " + cid;
//
//        Cursor c = db.rawQuery(selectQuery, null);
//        CareerObjective careerObjective = new CareerObjective();
//        if (c != null && c.moveToFirst()) {
//
//            careerObjective.setCid(c.getInt(c.getColumnIndex(CO_KEY_CID)));
//            careerObjective.setCo(c.getString(c.getColumnIndex(CO_KEY_CO)));
//
//
//            c.close();
//            return careerObjective;
//        }
//        return null;
//    }
//
//    //Add Academic Info
//    public long addAcademicInfo(AcademicInfo ai) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = aiContentValues(ai);
//        // Inserting Row
//        long n = db.insert(TABLE_ACADEMIC_INFO, null, cv);
//        //db.close(); // Closing database connection
//        return n;
//    }
//
//    public ContentValues aiContentValues(AcademicInfo ai){
//        ContentValues values = new ContentValues();
//        values.put(AI_KEY_CID, ai.getCid());
//
//        values.put(AI_KEY_PGUNIVERSITY, ai.getPguniversity());
//        values.put(AI_KEY_PGNAME, ai.getPgname());
//        values.put(AI_KEY_PGYEAR, ai.getPgyear());
//        values.put(AI_KEY_PGPERSUING, ai.getPgpersuing());
//        values.put(AI_KEY_PGPERCENTAGE, ai.getPgpercentage());
//
//        values.put(AI_KEY_GUNIVERSITY, ai.getGuniversity());
//        values.put(AI_KEY_GNAME, ai.getGname());
//        values.put(AI_KEY_GYEAR, ai.getGyear());
//        values.put(AI_KEY_GPERSUING, ai.getGpersuing());
//        values.put(AI_KEY_GPERCENTAGE, ai.getGpercentage());
//
//        values.put(AI_KEY_CBOARD, ai.getCboard());
//        values.put(AI_KEY_CNAME, ai.getCname());
//        values.put(AI_KEY_CYEAR, ai.getCyear());
//        values.put(AI_KEY_CPERCENTAGE, ai.getCpercentage());
//
//        values.put(AI_KEY_SBOARD, ai.getSboard());
//        values.put(AI_KEY_SNAME, ai.getSname());
//        values.put(AI_KEY_SYEAR, ai.getSyear());
//        values.put(AI_KEY_SPERCENTAGE, ai.getSpercentage());
//
//        values.put(AI_KEY_PGNYET,ai.getPgnotyet());
//        return values;
//    }
//
//    //Update Personal Info
//    public long updateAcademicInfo(AcademicInfo ai) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = aiContentValues(ai);
//        // Inserting Row
//        long n = db.update(TABLE_ACADEMIC_INFO, cv, "cid=" + ai.getCid(), null);
//
//        //db.close(); // Closing database connection
//        return n;
//    }
//
//    //getting single record of Academic info
//    public AcademicInfo getSingleAI(int cid) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        String selectQuery = "SELECT  * FROM " + TABLE_ACADEMIC_INFO + " WHERE "
//                + AI_KEY_CID + " = " + cid;
//
//        Cursor c = db.rawQuery(selectQuery, null);
//        AcademicInfo academicInfo = new AcademicInfo();
//        if (c != null && c.moveToFirst()) {
//
//            academicInfo.setCid(c.getInt(c.getColumnIndex(AI_KEY_CID)));
//            academicInfo.setPgnotyet(c.getString(c.getColumnIndex(AI_KEY_PGNYET)));
//            academicInfo.setPguniversity(c.getString(c.getColumnIndex(AI_KEY_PGUNIVERSITY)));
//            academicInfo.setPgname(c.getString(c.getColumnIndex(AI_KEY_PGNAME)));
//            academicInfo.setPgyear(c.getString(c.getColumnIndex(AI_KEY_PGYEAR)));
//            academicInfo.setPgpersuing(c.getString(c.getColumnIndex(AI_KEY_PGPERSUING)));
//            academicInfo.setPgpercentage(c.getString(c.getColumnIndex(AI_KEY_PGPERCENTAGE)));
//            academicInfo.setGuniversity(c.getString(c.getColumnIndex(AI_KEY_GUNIVERSITY)));
//            academicInfo.setGname(c.getString(c.getColumnIndex(AI_KEY_GNAME)));
//            academicInfo.setGyear(c.getString(c.getColumnIndex(AI_KEY_GYEAR)));
//            academicInfo.setGpersuing(c.getString(c.getColumnIndex(AI_KEY_GPERSUING)));
//            academicInfo.setGpercentage(c.getString(c.getColumnIndex(AI_KEY_GPERCENTAGE)));
//            academicInfo.setCboard(c.getString(c.getColumnIndex(AI_KEY_CBOARD)));
//            academicInfo.setCname(c.getString(c.getColumnIndex(AI_KEY_CNAME)));
//            academicInfo.setCyear(c.getString(c.getColumnIndex(AI_KEY_CYEAR)));
//            academicInfo.setCpercentage(c.getString(c.getColumnIndex(AI_KEY_CPERCENTAGE)));
//            academicInfo.setSboard(c.getString(c.getColumnIndex(AI_KEY_SBOARD)));
//            academicInfo.setSname(c.getString(c.getColumnIndex(AI_KEY_SNAME)));
//            academicInfo.setSyear(c.getString(c.getColumnIndex(AI_KEY_SYEAR)));
//            academicInfo.setSpercentage(c.getString(c.getColumnIndex(AI_KEY_SPERCENTAGE)));
//
//            c.close();
//            return academicInfo;
//        }
//        return null;
//    }
//
//
//    // Adding declaration
//    public long addDeclaration(Declaration dc) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//
//        ContentValues values = new ContentValues();
//        values.put(DC_KEY_CID, dc.getdCid());
//        values.put(DC_KEY_DECLARATION, dc.getdDeclaration());
//        values.put(DC_KEY_DATE,dc.getdDate());
//        values.put(DC_KEY_PLACE,dc.getdPlace());
//
//
//        // Inserting Row
//        long n = db.insert(TABLE_DECLARATION, null, values);
//        //db.close(); // Closing database connection
//        return n;
//    }
//
//
//    //Update Declaration
//    public long updateDC(Declaration dc) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(DC_KEY_CID, dc.getdCid());
//        values.put(DC_KEY_DECLARATION, dc.getdDeclaration());
//        values.put(DC_KEY_DATE, dc.getdDate());
//        values.put(DC_KEY_PLACE, dc.getdPlace());
//
//        // Inserting Row
//        long n = db.update(TABLE_DECLARATION, values, "cid=" + dc.getdCid(), null);
//        //db.close(); // Closing database connection
//        return n;
//    }
//
//    //getting single record
//    public Declaration getSingleDC(int cid) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        String selectQuery = "SELECT  * FROM " + TABLE_DECLARATION + " WHERE "
//                + DC_KEY_CID + " = " + cid;
//
//        Cursor c = db.rawQuery(selectQuery, null);
//        Declaration declaration = new Declaration();
//        if (c != null && c.moveToFirst()) {
//
//            declaration.setdDeclaration(c.getString(c.getColumnIndex(DC_KEY_DECLARATION)));
//            declaration.setdDate(c.getString(c.getColumnIndex(DC_KEY_DATE)));
//            declaration.setdPlace(c.getString(c.getColumnIndex(DC_KEY_PLACE)));
//
//            c.close();
//            return declaration;
//        }
//        return null;
//    }

}