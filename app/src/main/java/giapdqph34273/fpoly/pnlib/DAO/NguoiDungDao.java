package giapdqph34273.fpoly.pnlib.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import giapdqph34273.fpoly.pnlib.database.DBHelper_NguoiDung;
import giapdqph34273.fpoly.pnlib.model.NguoiDung;

public class NguoiDungDao {
    private DBHelper_NguoiDung db_user;
    SQLiteDatabase database;

    public NguoiDungDao(Context context) {
        db_user = new DBHelper_NguoiDung(context);
    }

    public boolean checkUser(String username, String password) {
        database = db_user.getReadableDatabase();
        String[] columns = {"ID"};
        String selection = "TENDANGNHAP" + "=? and " + "MATKHAU" + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = database.query("user", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        database.close();
        return count > 0;
    }
}
