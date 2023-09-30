package giapdqph34273.fpoly.pnlib.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB_Helper_NguoiDung extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "nguoidungdb.db";
    private static final int DATABASE_VERSION = 2;

    public DB_Helper_NguoiDung(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE user(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TENDANGNHAP TEXT," +
                "HOTEN TEXT," +
                "MATKHAU)";
        db.execSQL(createTable);// truy vấn

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP TABLE IF EXISTS user";
        db.execSQL(dropTable);
        onCreate(db);
    }
}
