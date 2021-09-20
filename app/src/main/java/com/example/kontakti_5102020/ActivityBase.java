package com.example.kontakti_5102020;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;

public abstract class ActivityBase extends AppCompatActivity {
    protected void initDb() throws SQLException {
        SQLiteDatabase db=
                SQLiteDatabase.openOrCreateDatabase(
                  getFilesDir().getPath()+"/uravneniqdb.db",
                        null
                );
        String createQ="CREATE TABLE if not exists URAVNENIQ( " +
                "ID integer PRIMARY KEY AUTOINCREMENT, " +
                "A text not null, " +
                "B text not null, " +
                "C text not null, " +
                "'');";
        db.execSQL(createQ);
        db.close();

    }

    protected void ExecSQL(String SQL, Object[] args, OnQuerySuccess success) throws SQLException{
        SQLiteDatabase db=
                SQLiteDatabase.openOrCreateDatabase(
                        getFilesDir().getPath()+"/uravneniqdb.db",
                        null
                );

        db.execSQL(SQL, args);
        success.OnSuccess();
        db.close();

    }

    public void SelectSQL(String SQL, String[] args, OnSelectElement iterate)
    throws Exception
    {
        SQLiteDatabase db=
                SQLiteDatabase.openOrCreateDatabase(
                        getFilesDir().getPath()+"/uravneniqdb.db",
                        null
                );

        Cursor cursor=db.rawQuery(SQL, args);
        while (cursor.moveToNext()){
            String ID=cursor.getString(cursor.getColumnIndex("ID"));
            String A=cursor.getString(cursor.getColumnIndex("A"));
            String B=cursor.getString(cursor.getColumnIndex("B"));
            String C=cursor.getString(cursor.getColumnIndex("C"));
            iterate.OnElementIterate(A, B, C, ID);
        }
        db.close();

    }

    protected interface OnQuerySuccess{

        public void OnSuccess();

    }

    protected interface OnSelectElement{
        public void OnElementIterate(String A, String B, String C, String ID);
    }
}
