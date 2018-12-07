package com.example.aramamu1.rockinthebump;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "new1.db";
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "UserID";
    public static final String COLUMN_NAME = "UserName";
    public static final String COLUMN_NAME1 = "UserPswd";
    public static final String COLUMN_NAME2 = "DeliveryDate";
    public static final String COLUMN_NAME3 = "InitialWeight";

    public static final String TABLE_APPOINTMENT = "appointment";
    public static final String COLUMN_ID1 = "ApptID";
    public static final String COLUMN_NAME10 = "UserID";
    public static final String COLUMN_NAME11 = "Date";
    public static final String COLUMN_NAME12 = "Description";

    public static final String TABLE_HEALTH = "health";
    public static final String COLUMN_ID2 = "HealthID";
    public static final String COLUMN_NAME20 = "UserID";
    public static final String COLUMN_NAME21 = "Date";
    public static final String COLUMN_NAME22 = "Weight";
    public static final String COLUMN_NAME23 = "BloodPressure";
    public static final String COLUMN_NAME24 = "FetalHB";


    //initialize the database
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USER_TABLE = "CREATE TABLE " +
                TABLE_USERS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME
                + " TEXT, " + COLUMN_NAME1 + " TEXT, "+ COLUMN_NAME2 + " TEXT, "+COLUMN_NAME3 + " TEXT "+")";
        db.execSQL(CREATE_USER_TABLE);
        String CREATE_APPOINTMENT_TABLE = "CREATE TABLE " +
                TABLE_APPOINTMENT + "(" + COLUMN_ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME10 + " INTEGER, "+ COLUMN_NAME11
                + " TEXT, " + COLUMN_NAME12 + " TEXT "+")";
        db.execSQL(CREATE_APPOINTMENT_TABLE);
        String CREATE_HEALTH_TABLE = "CREATE TABLE " +
                TABLE_HEALTH + "(" + COLUMN_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME20
                + " INTEGER, " + COLUMN_NAME21 + " TEXT, "+ COLUMN_NAME22 + " INTEGER, "+ COLUMN_NAME23
                + " INTEGER, "+ COLUMN_NAME24 + " INTEGER "+")";
        db.execSQL(CREATE_HEALTH_TABLE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPOINTMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HEALTH);
        onCreate(db);

    }

    //for health table
    //add
    public void addHealthHandler(Health health) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME20, health.getUserID());
        values.put(COLUMN_NAME21, health.getDate());
        values.put(COLUMN_NAME22, health.getWeight());
        values.put(COLUMN_NAME23, health.getBloodpressure());
        values.put(COLUMN_NAME24, health.getFetalhb());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_HEALTH, null, values);
        db.close();
    }

    //load
    public String loadHealthHandler(int userid) {
        String result = "";
        String query = "Select * FROM " + TABLE_HEALTH + " WHERE " +
                COLUMN_NAME20 + " = '" + String.valueOf(userid) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            cursor.getInt(0);
            int result_1 = cursor.getInt(1);
            String result_2 = cursor.getString(2);
            int result_3 = cursor.getInt(3);
            int result_4 = cursor.getInt(4);
            int result_5 = cursor.getInt(5);

            result += "User ID: "+String.valueOf(result_1) + " Date: " + result_2 + " Weight: " + String.valueOf(result_3) +" Blood Pressure: " + String.valueOf(result_4) +" Fetal Heartbeat: "+ String.valueOf(result_5) +" "+
                    System.getProperty("line.separator");
            while (cursor.moveToNext()) {
                cursor.getInt(0);
                result_1 = cursor.getInt(1);
                result_2 = cursor.getString(2);
                result_3 = cursor.getInt(3);
                result_4 = cursor.getInt(4);
                result_5 = cursor.getInt(5);

                result += "User ID: "+String.valueOf(result_1) + " Date: " + result_2 + " Weight: " + String.valueOf(result_3) +" Blood Pressure: " + String.valueOf(result_4) +" Fetal Heartbeat: "+ String.valueOf(result_5) +" "+
                        System.getProperty("line.separator");
            }
            cursor.close();
        } else {
            result = null;
        }
        cursor.close();
        db.close();
        return result;
    }

    //for appt table
    //add
    public void addApptHandler(Appointment appt) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME10, appt.getUserID());
        values.put(COLUMN_NAME11, appt.getDate());
        values.put(COLUMN_NAME12, appt.getDescription());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_APPOINTMENT, null, values);
        db.close();
    }

    //delete
    public boolean deleteApptHandler(String date) {
        boolean result = false;
        String query = "Select*FROM " + TABLE_APPOINTMENT + " WHERE " + COLUMN_NAME11 + " = '" + date + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Appointment apt = new Appointment();
        if (cursor.moveToFirst()) {
            apt.setDate(cursor.getString(2));

            db.delete(TABLE_APPOINTMENT, COLUMN_NAME11 + "=?",
                    new String[] {
                            apt.getDate()
                    });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }


    //find
    public Appointment findApptHandler(int userid, String date) {
        String query = "Select * FROM " + TABLE_APPOINTMENT + " WHERE " +
                COLUMN_NAME10 + " = '" + String.valueOf(userid) + "'"+ " AND " +
                COLUMN_NAME11 + " = '" +date+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Appointment appt = new Appointment();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            cursor.getString(0);
            appt.setUserID(Integer.parseInt(cursor.getString(1)));
            appt.setDate(cursor.getString(2));
            appt.setDescription(cursor.getString(3));

            cursor.close();
        } else {
            appt = null;
        }
        db.close();
        return appt;
    }

    public boolean updateApptHandler(int uid, String date, String description ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_NAME10, uid);
        args.put(COLUMN_NAME11, date);
        args.put(COLUMN_NAME12, description);
        return db.update(TABLE_APPOINTMENT, args, COLUMN_NAME11 + "=" + date, null) > 0;
    }

    public String loadApptHandler(int userid) {
        String result = "";
        String query = "Select * FROM " + TABLE_APPOINTMENT + " WHERE " +
                COLUMN_NAME10 + " = '" + String.valueOf(userid) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            cursor.getInt(0);
            String result_1 = cursor.getString(1);
            String result_2 = cursor.getString(2);
            String result_3 = cursor.getString(3);

            result += "User ID: "+result_1 + " Date: " + result_2 + " Description: " + result_3 +" " +
                    System.getProperty("line.separator");
            while (cursor.moveToNext()) {
                cursor.getInt(0);
                result_1 = cursor.getString(1);
                result_2 = cursor.getString(2);
                result_3 = cursor.getString(3);
                result += "User ID: "+result_1 + " Date: " + result_2 + " Description: " + result_3 +" " +
                        System.getProperty("line.separator");
            }
            cursor.close();
        } else {
            result = null;
        }
        cursor.close();
        db.close();
        return result;
    }

    //for the user database

    public void addHandler(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, user.getID());
        values.put(COLUMN_NAME, user.getUserName());
        values.put(COLUMN_NAME1, user.getUserPswd());
        values.put(COLUMN_NAME2, user.getDeliveryDate());
        values.put(COLUMN_NAME3, user.getInitialWeight());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public User findHandler(int userid, String userpswd) {
        String query = "Select * FROM " + TABLE_USERS + " WHERE " +
                COLUMN_ID + " = '" + String.valueOf(userid) + "'"+ " AND " +
                COLUMN_NAME1 + " = '" +userpswd+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user = new User();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            user.setID(Integer.parseInt(cursor.getString(0)));
            user.setUserName(cursor.getString(1));
            user.setUserPswd(cursor.getString(2));
            user.setDeliveryDate(cursor.getString(3));
            user.setInitialWeight(cursor.getString(4));
            cursor.close();
        } else {
            user = null;
        }
        db.close();
        return user;
    }

    public String loadHandler() {
        String result = "";
        String query = "Select*FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            String result_2 = cursor.getString(2);
            String result_3 = cursor.getString(3);
            String result_4 = cursor.getString(4);
            result += "User ID: "+String.valueOf(result_0) + " Name: " + result_1 + " Password: " + result_2 + " Delivery Date: " + result_3 +" Initial Weight: " + result_4 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }

    public boolean deleteHandler(int userid) {
        boolean result1 = false;
        boolean result2 = false;
        boolean result3 = false;
        boolean result = false;
        String query = "Select*FROM " + TABLE_USERS + " WHERE " + COLUMN_ID + " = '" + String.valueOf(userid) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user = new User();
        if (cursor.moveToFirst()) {
            user.setID(Integer.parseInt(cursor.getString(0)));

            db.delete(TABLE_USERS, COLUMN_ID + "=?",
                   new String[] {
                           String.valueOf(user.getID())
                   });
            cursor.close();
            result1 = true;
        }
        db.close();
        String query1 = "Select*FROM " + TABLE_APPOINTMENT + " WHERE " + COLUMN_NAME10 + " = '" + String.valueOf(userid) + "'";
        SQLiteDatabase db1 = this.getWritableDatabase();
        Cursor cursor1 = db1.rawQuery(query1, null);
        Appointment appt = new Appointment();
        if (cursor1.moveToFirst()) {
            appt.setUserID(Integer.parseInt(cursor1.getString(1)));

            db1.delete(TABLE_APPOINTMENT, COLUMN_NAME10 + "=?",
                    new String[] {
                            String.valueOf(appt.getUserID())
                    });
            cursor1.close();
            result2 = true;
        }
        db1.close();

        String query2 = "Select*FROM " + TABLE_HEALTH + " WHERE " + COLUMN_NAME20 + " = '" + String.valueOf(userid) + "'";
        SQLiteDatabase db2 = this.getWritableDatabase();
        Cursor cursor2 = db2.rawQuery(query2, null);
        Health health = new Health();
        if (cursor2.moveToFirst()) {
            health.setUserID(Integer.parseInt(cursor2.getString(1)));

            db2.delete(TABLE_HEALTH, COLUMN_NAME20 + "=?",
                    new String[] {
                            String.valueOf(health.getUserID())
                    });
            cursor2.close();
            result3 = true;
        }
        result = result1 | result2 | result3;
        db2.close();
        return result;
    }

    public boolean updateHandler(int ID, String name, String pswd, String deliveryDate ,String weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, ID);
        args.put(COLUMN_NAME, name);
        args.put(COLUMN_NAME1, pswd);
        args.put(COLUMN_NAME2, deliveryDate);
        args.put(COLUMN_NAME3, weight);
        return db.update(TABLE_USERS, args, COLUMN_ID + "=" + ID, null) > 0;
    }

}
