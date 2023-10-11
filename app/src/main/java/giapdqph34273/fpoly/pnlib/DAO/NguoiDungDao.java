package giapdqph34273.fpoly.pnlib.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import giapdqph34273.fpoly.pnlib.database.DBHelper;

public class NguoiDungDao {
    private DBHelper dbHelper;
    SQLiteDatabase database;

    public NguoiDungDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    public boolean checkUser(String username, String password) {
        database = dbHelper.getReadableDatabase();
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
