package giapdqph34273.fpoly.pnlib.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import giapdqph34273.fpoly.pnlib.database.DBHelper_LoaiSach;
import giapdqph34273.fpoly.pnlib.database.DBHelper_ThanhVien;
import giapdqph34273.fpoly.pnlib.model.LoaiSach;
import giapdqph34273.fpoly.pnlib.model.ThanhVien;

public class ThanhVienDAO {
    private DBHelper_ThanhVien db_tv;
    SQLiteDatabase database;

    public ThanhVienDAO(Context context) {
        db_tv = new DBHelper_ThanhVien(context);
    }

    public ArrayList<ThanhVien> getAllThanhVien() {
        ArrayList<ThanhVien> list = new ArrayList<>();
        database = db_tv.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM thanhvien", null);
        while (cursor.moveToNext()) {
            ThanhVien tv = new ThanhVien(
                    cursor.getString(1),
                    cursor.getInt(2)
            );
            tv.setId(cursor.getInt(0));
            list.add(tv);
        }
        return list;
    }

    public long addTV(ThanhVien thanhVien) {
        database = db_tv.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENTV", thanhVien.getTenTV());
        values.put("NAMSINH", thanhVien.getNamSinh());
        return database.insert("thanhvien", null, values);
    }

    public long deleteTV(int id) {
        database = db_tv.getWritableDatabase();
        long check = database.delete("thanhvien", "ID=?", new String[]{String.valueOf(id)});
        return check;
    }

    public long updateTV(ThanhVien thanhVien) {
        database = db_tv.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENTV", thanhVien.getTenTV());
        values.put("NAMSINH", thanhVien.getNamSinh());
        long check = database.update("thanhvien", values, "ID=?", new String[]{String.valueOf(thanhVien.getId())});
        return check;
    }
}
