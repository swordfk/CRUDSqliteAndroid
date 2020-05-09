package com.example.crudbarang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "data_brg.db";
    public static final String TABLE_NAME = "table_brg";
    public static final String COL_1 = "kode";
    public static final String COL_2 = "nama_brg";
    public static final String COL_3 = "hrg_brg";
    public static final String COL_4 = "jml_brg";
    public static final int DATABASE_VERSION = 1;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table table_brg(kode text primary key ,nama_brg text not null,hrg_brg text not null,jml_brg text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //mengambil data
    public ArrayList<HashMap<String, String>> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT * FROM "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("kode",cursor.getString(cursor.getColumnIndex(COL_1)));
            user.put("nama_brg",cursor.getString(cursor.getColumnIndex(COL_2)));
            userList.add(user);
        }
        return  userList;
    }

    //mengambil data
    public ArrayList<HashMap<String, String>> GetDetails(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT * FROM "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("kode",cursor.getString(cursor.getColumnIndex(COL_1)));
            user.put("nama_brg",cursor.getString(cursor.getColumnIndex(COL_2)));
            user.put("hrg_brg",cursor.getString(cursor.getColumnIndex(COL_3)));
            user.put("jml_brg",cursor.getString(cursor.getColumnIndex(COL_4)));
            userList.add(user);
        }
        return  userList;
    }

    //metode untuk tambah data
    public boolean insertData(String kode, String nama_brg, String hrg_brg,String jml_brg) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, kode);
        contentValues.put(COL_2, nama_brg);
        contentValues.put(COL_3, hrg_brg);
        contentValues.put(COL_4, jml_brg);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    //metode untuk merubah data
    public boolean updateData(String kode, String nama_brg, String hrg_brg,String jml_brg) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, kode);
        contentValues.put(COL_2, nama_brg);
        contentValues.put(COL_3, hrg_brg);
        contentValues.put(COL_4, jml_brg);

        db.update(TABLE_NAME, contentValues, "kode = ?", new String[]{kode});
        return true;
    }


    //metode untuk menghapus data
    public int deleteData(String kode) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "kode = ?", new String[]{kode});
    }

    //sorting ASC
    public ArrayList<HashMap<String, String>> GetSortASC(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT * FROM "+ TABLE_NAME+" ORDER BY nama_brg ASC ";
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("kode",cursor.getString(cursor.getColumnIndex(COL_1)));
            user.put("nama_brg",cursor.getString(cursor.getColumnIndex(COL_2)));
            user.put("hrg_brg",cursor.getString(cursor.getColumnIndex(COL_3)));
            user.put("jml_brg",cursor.getString(cursor.getColumnIndex(COL_4)));
            userList.add(user);
        }
        return  userList;
    }
}