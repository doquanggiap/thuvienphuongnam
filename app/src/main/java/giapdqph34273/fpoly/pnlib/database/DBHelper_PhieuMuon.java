package giapdqph34273.fpoly.pnlib.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper_PhieuMuon extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "phieumuon.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper_PhieuMuon(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE PM(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TENTV TEXT," +
                "TENSACH TEXT," +
                "TIENTHUE INTEGER," +
                "NGAYTHUE TEXT," +
                "TRANGTHAI BOOLEAN)";
        db.execSQL(createTable);
        String pms = "INSERT INTO PM (TENSP, GIABAN, SOLUONG) VALUES" +
                "('Đỗ Quang Giáp','Java 1',10000,'01-10-2023',true)";
        db.execSQL(pms);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableSanPham = "DROP TABLE IF EXISTS PM";
        db.execSQL(dropTableSanPham);
        onCreate(db);
    }
}
