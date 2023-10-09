package giapdqph34273.fpoly.pnlib.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper_ThanhVien extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "thanhvien.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper_ThanhVien(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE thanhvien(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TENTV TEXT," +
                "NAMSINH INT)";
        db.execSQL(createTable);// truy vấn

        String macdinh = "INSERT INTO thanhvien(TENTV,NAMSINH) VALUES" +
                "('Nguyễn Tuấn Phong',2004)," +
                "('Kiều Phương Trinh',1999)";
        db.execSQL(macdinh);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP TABLE IF EXISTS thanhvien";
        db.execSQL(dropTable);
        onCreate(db);
    }
}
