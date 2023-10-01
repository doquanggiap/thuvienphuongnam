package giapdqph34273.fpoly.pnlib.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper_NguoiDung extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "nguoidungdb.db";
    private static final int DATABASE_VERSION = 2;

    public DBHelper_NguoiDung(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE user(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TENDANGNHAP TEXT," +
                "HOTEN TEXT," +
                "MATKHAU TEXT)";
        db.execSQL(createTable);// truy vấn
        String dsnd = "INSERT INTO user (TENDANGNHAP, HOTEN, MATKHAU) VALUES" +
                "('admin','Đỗ Quang Giáp','admin')";
        db.execSQL(dsnd);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP TABLE IF EXISTS user";
        db.execSQL(dropTable);
        onCreate(db);
    }
}
