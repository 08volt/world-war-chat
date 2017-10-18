package com.example.android.BluetoothChat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DbAdapter {
    @SuppressWarnings("unused")
    private static final String LOG_TAG = DbAdapter.class.getSimpleName();

    private Context context;
    private SQLiteDatabase database;

    // Database fields
    private static final String DATABASE_TABLE = "messaggi";

    public static final String KEY_ID = "_id";
    public static final String KEY_MITT = "mitt";
    public static final String KEY_DEST = "dest";
    public static final String KEY_TESTO = "testo";

    public DbAdapter(Context context) {
        this.context = context;
    }

    public void open() throws SQLException {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();

    }

    public void close() {
        database.close();
    }

    private ContentValues createContentValues(String mittente, String destinatario,String testo) {
        ContentValues values = new ContentValues();
        values.put(KEY_MITT, mittente);
        values.put(KEY_DEST, destinatario);
        values.put(KEY_TESTO,testo);

        return values;
    }

    //create a record, return the row ID or -1
    public long createRecord(String m, String d,String t) {
        ContentValues initialValues = createContentValues(m, d,t);
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    //update a record
    public boolean updateRecord(long contactID, String m, String d, String t) {
        ContentValues updateValues = createContentValues(m, d,t);
        return database.update(DATABASE_TABLE, updateValues, KEY_ID + "=" + contactID, null) > 0;
    }

    //delete a record
    public boolean deleteRecord(long contactID) {
        return database.delete(DATABASE_TABLE, KEY_ID + "=" + contactID, null) > 0;
    }

    //delete all from someone
    public int deleteFromSomeone(String someone) {
        return database.delete(DATABASE_TABLE, "mitt='" + someone + "' OR dest='" + someone+"'", null);
    }



    //fetch all records
    public Cursor fetchAllRecord() {
        return database.query(DATABASE_TABLE, new String[]{KEY_ID, KEY_MITT, KEY_DEST,KEY_TESTO}, null, null, null, null, null);
    }

    //fetch records filter by a string
    public Cursor fetchContactsByDestinatario(String destinatario) {
        Cursor mCursor = database.query(true, DATABASE_TABLE, new String[]{
                        KEY_ID, KEY_MITT, KEY_DEST,KEY_TESTO},
                KEY_DEST + " like '%" + destinatario + "%'", null, null, null, null, null);

        return mCursor;
    }

    //fetch records filter by a string
    public Cursor fetchContactsByMittente(String mittente) {
        Cursor mCursor = database.query(true, DATABASE_TABLE, new String[]{
                        KEY_ID, KEY_MITT, KEY_DEST,KEY_TESTO},
                KEY_MITT + " like '%" + mittente + "%'", null, null, null, null, null);

        return mCursor;
    }

    //fetch records filter by a string
    public Cursor fetchContactsByContact(String contatto) {
        Cursor mCursor = database.query(true, DATABASE_TABLE, new String[]{
                        KEY_ID, KEY_MITT, KEY_DEST,KEY_TESTO},
                KEY_MITT + " like '%" + contatto + "%' OR "+KEY_DEST+" like '%"+contatto+"%'", null, null, null, null, null);

        return mCursor;
    }



}