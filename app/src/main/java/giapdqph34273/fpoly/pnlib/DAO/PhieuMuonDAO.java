package giapdqph34273.fpoly.pnlib.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import giapdqph34273.fpoly.pnlib.database.DBHelper_NguoiDung;
import giapdqph34273.fpoly.pnlib.database.DBHelper_PhieuMuon;
import giapdqph34273.fpoly.pnlib.model.PhieuMuon;

public class PhieuMuonDAO {
    private DBHelper_PhieuMuon db_pm;
    SQLiteDatabase database;

    public PhieuMuonDAO(Context context) {
        db_pm = new DBHelper_PhieuMuon(context);
    }
    public ArrayList<PhieuMuon> getAllPhieuMuon() {
        ArrayList<PhieuMuon> list = new ArrayList<>();
        database = db_pm.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM PM", null);
        while (cursor.moveToNext()) {
            PhieuMuon pm = new PhieuMuon(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getInt(5)
                    );
            pm.setId(cursor.getInt(0));
            list.add(pm);
        }
        return list;
    }
    public long addPM(PhieuMuon pm) {
        database = db_pm.getWritableDatabase();// Lấy đối tượng SQLiteDatabase để ghi dữ liệu vào cơ sở dữ liệu
        ContentValues values = new ContentValues();// Tạo một đối tượng ContentValues để chứa các giá trị của đối tượng sanPham
        values.put("TENTV", pm.getTenTV());
        values.put("TENSACH", pm.getTenSach());
        values.put("TIENTHUE", pm.getTienThue());
        values.put("NGAYTHUE", pm.getNgayThue());
        values.put("TRANGTHAI", pm.getTrangThaiMuon());
        return database.insert("sanpham", null, values);
    }
    public long deletePM(int id) {
        database = db_pm.getWritableDatabase();
        long check = database.delete("PM", "ID=?", new String[]{String.valueOf(id)});
        return check;
    }
    public long updatePM(PhieuMuon pm) {
        database = db_pm.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENTV", pm.getTenTV());
        values.put("TENSACH", pm.getTenSach());
        values.put("TIENTHUE", pm.getTienThue());
        values.put("NGAYTHUE", pm.getNgayThue());
        values.put("TRANGTHAI", pm.getTrangThaiMuon());
        long check = database.update("PM", values, "ID=?", new String[]{String.valueOf(pm.getId())});
        return check;
    }
}