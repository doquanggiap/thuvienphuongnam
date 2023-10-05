package giapdqph34273.fpoly.pnlib.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import giapdqph34273.fpoly.pnlib.database.DBHelper_LoaiSach;
import giapdqph34273.fpoly.pnlib.database.DBHelper_Sach;
import giapdqph34273.fpoly.pnlib.model.LoaiSach;
import giapdqph34273.fpoly.pnlib.model.Sach;

public class SachDAO {
    private DBHelper_Sach db_s;
    SQLiteDatabase database;

    public SachDAO(Context context) {
        db_s = new DBHelper_Sach(context);
    }

    public ArrayList<Sach> getAllSach() {
        ArrayList<Sach> list = new ArrayList<>();
        database = db_s.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM sach", null);
        while (cursor.moveToNext()) {
            Sach s = new Sach(
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3)
            );
            s.setId(cursor.getInt(0));
            list.add(s);
        }
        return list;
    }

    public long addS(Sach sach) {
        database = db_s.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENSACH", sach.getTenSach());
        values.put("TIENTHUE", sach.getTienThue());
        values.put("LOAISACH", sach.getLoaiSach());
        return database.insert("sach", null, values);
    }

    public long deleteS(int id) {
        database = db_s.getWritableDatabase();
        long check = database.delete("sach", "ID=?", new String[]{String.valueOf(id)});
        return check;
    }

    public long updateS(Sach sach) {
        database = db_s.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENSACH", sach.getTenSach());
        values.put("TIENTHUE", sach.getTienThue());
        values.put("LOAISACH", sach.getLoaiSach());
        long check = database.update("sach", values, "ID=?", new String[]{String.valueOf(sach.getId())});
        return check;
    }
}