package giapdqph34273.fpoly.pnlib.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper_Sach extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sach.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper_Sach(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE sach(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TENSACH TEXT," +
                "TIENTHUE INTEGER," +
                "LOAISACH TEXT)";
        db.execSQL(createTable);// truy vấn

        String macdinh = "INSERT INTO sach(TENSACH,TIENTHUE,LOAISACH) VALUES" +
                "('Doraemon',1000,'Lập trình')," +
                "('Photoshop',3000,'Đồ họa')";
        db.execSQL(macdinh);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP TABLE IF EXISTS sach";
        db.execSQL(dropTable);
        onCreate(db);
    }
}
