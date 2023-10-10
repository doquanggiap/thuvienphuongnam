package giapdqph34273.fpoly.pnlib.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import giapdqph34273.fpoly.pnlib.database.DBHelper;
import giapdqph34273.fpoly.pnlib.database.DBHelper_NguoiDung;
import giapdqph34273.fpoly.pnlib.database.DBHelper_PhieuMuon;
import giapdqph34273.fpoly.pnlib.model.PhieuMuon;
import giapdqph34273.fpoly.pnlib.model.TopSach;

public class PhieuMuonDAO {
    private DBHelper dbHelper;
    SQLiteDatabase database;

    public PhieuMuonDAO(Context context) {
        dbHelper = new DBHelper(context);
    }
    public ArrayList<PhieuMuon> getAllPhieuMuon() {
        ArrayList<PhieuMuon> list = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
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
        database = dbHelper.getWritableDatabase();// Lấy đối tượng SQLiteDatabase để ghi dữ liệu vào cơ sở dữ liệu
        ContentValues values = new ContentValues();// Tạo một đối tượng ContentValues để chứa các giá trị của đối tượng sanPham
        values.put("TENTV", pm.getTenTV());
        values.put("TENSACH", pm.getTenSach());
        values.put("TIENTHUE", pm.getTienThue());
        values.put("NGAYTHUE", pm.getNgayThue());
        values.put("TRANGTHAI", pm.getTrangThaiMuon());
        return database.insert("PM", null, values);
    }
    public long deletePM(int id) {
        database = dbHelper.getWritableDatabase();
        long check = database.delete("PM", "ID=?", new String[]{String.valueOf(id)});
        return check;
    }
    public long updatePM(PhieuMuon pm) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENTV", pm.getTenTV());
        values.put("TENSACH", pm.getTenSach());
        values.put("TIENTHUE", pm.getTienThue());
        values.put("NGAYTHUE", pm.getNgayThue());
        values.put("TRANGTHAI", pm.getTrangThaiMuon());
        long check = database.update("PM", values, "ID=?", new String[]{String.valueOf(pm.getId())});
        return check;
    }

    public ArrayList<TopSach> getTop10Sach() {
        ArrayList<TopSach> top10List = new ArrayList<>();

        // Chuỗi truy vấn SQL để lấy ra 10 cuốn sách được thuê nhiều nhất
        String query = "SELECT s.TENSACH AS TenSach, COUNT(pm.ID) AS SoLuotMuon " +
                "FROM sach s " +
                "INNER JOIN PM pm ON s.TENSACH = pm.TENSACH " +
                "GROUP BY s.TENSACH " +
                "ORDER BY SoLuotMuon DESC " +
                "LIMIT 10";

         database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String tenSach = cursor.getString(cursor.getColumnIndex("TenSach"));
                @SuppressLint("Range") int soLuotMuon = cursor.getInt(cursor.getColumnIndex("SoLuotMuon"));

                TopSach topSach = new TopSach(tenSach,soLuotMuon);

                top10List.add(topSach);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return top10List;
    }



}
