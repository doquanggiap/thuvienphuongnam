package giapdqph34273.fpoly.pnlib.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import giapdqph34273.fpoly.pnlib.database.DBHelper;
import giapdqph34273.fpoly.pnlib.model.Sach;

public class SachDAO {
    private DBHelper dbHelper;
    SQLiteDatabase database;

    public SachDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<Sach> getAllSach() {
        ArrayList<Sach> list = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM sach", null);
        while (cursor.moveToNext()) {
            Sach s = new Sach(
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getInt(4)
            );
            s.setId(cursor.getInt(0));
            list.add(s);
        }
        return list;
    }

    public long addS(Sach sach) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENSACH", sach.getTenSach());
        values.put("TIENTHUE", sach.getTienThue());
        values.put("LOAISACH", sach.getLoaiSach());
        values.put("NAMXB", sach.getNamXB());
        return database.insert("sach", null, values);
    }

    public long deleteS(int id) {
        database = dbHelper.getWritableDatabase();
        long check = database.delete("sach", "ID=?", new String[]{String.valueOf(id)});
        return check;
    }

    public long updateS(Sach sach) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENSACH", sach.getTenSach());
        values.put("TIENTHUE", sach.getTienThue());
        values.put("LOAISACH", sach.getLoaiSach());
        values.put("NAMXB", sach.getNamXB());
        long check = database.update("sach", values, "ID=?", new String[]{String.valueOf(sach.getId())});
        return check;
    }
}
