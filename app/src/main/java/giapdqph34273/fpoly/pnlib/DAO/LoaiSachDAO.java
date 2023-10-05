package giapdqph34273.fpoly.pnlib.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import giapdqph34273.fpoly.pnlib.database.DBHelper_LoaiSach;
import giapdqph34273.fpoly.pnlib.database.DBHelper_PhieuMuon;
import giapdqph34273.fpoly.pnlib.model.LoaiSach;
import giapdqph34273.fpoly.pnlib.model.PhieuMuon;

public class LoaiSachDAO {
    private DBHelper_LoaiSach db_ls;
    SQLiteDatabase database;

    public LoaiSachDAO(Context context) {
        db_ls = new DBHelper_LoaiSach(context);
    }
    public ArrayList<LoaiSach> getAllLoaiSach() {
        ArrayList<LoaiSach> list = new ArrayList<>();
        database = db_ls.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM loaiSach", null);
        while (cursor.moveToNext()) {
            LoaiSach ls = new LoaiSach(
                    cursor.getString(1)
            );
            ls.setId(cursor.getInt(0));
            list.add(ls);
        }
        return list;
    }
    public long addLS(LoaiSach loaiSach) {
        database = db_ls.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENLOAI", loaiSach.getTenLoai());
        return database.insert("loaiSach", null, values);
    }

    public long deleteLS(int id) {
        database = db_ls.getWritableDatabase();
        long check = database.delete("loaiSach", "ID=?", new String[]{String.valueOf(id)});
        return check;
    }

    public long updateLS(LoaiSach loaiSach) {
        database = db_ls.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENLOAI", loaiSach.getTenLoai());
        long check = database.update("loaiSach", values, "ID=?", new String[]{String.valueOf(loaiSach.getId())});
        return check;
    }
}
