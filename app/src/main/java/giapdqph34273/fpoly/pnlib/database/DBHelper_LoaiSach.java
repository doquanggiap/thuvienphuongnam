package giapdqph34273.fpoly.pnlib.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper_LoaiSach extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "loaisach.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper_LoaiSach(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE loaiSach(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TENLOAI TEXT)";
        db.execSQL(createTable);// truy vấn

        String macdinh = "INSERT INTO loaiSach(TENLOAI) VALUES" +
                "('Đồ họa')," +
                "('Lập trình')";
        db.execSQL(macdinh);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP TABLE IF EXISTS loaiSach";
        db.execSQL(dropTable);
        onCreate(db);
    }
}
