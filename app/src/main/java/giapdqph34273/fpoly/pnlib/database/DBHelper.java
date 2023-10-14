package giapdqph34273.fpoly.pnlib.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "pnlibdata.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // admin
        String createTableAdmin = "CREATE TABLE admin(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TENDANGNHAP TEXT," +
                "HOTEN TEXT," +
                "MATKHAU TEXT)";
        db.execSQL(createTableAdmin);// truy vấn
        String dsnd = "INSERT INTO admin (TENDANGNHAP, HOTEN, MATKHAU) VALUES" +
                "('admin','Đỗ Quang Giáp','admin')," +
                "('123','Đỗ Quang Giáp','123')";
        db.execSQL(dsnd);

        // thủ thư
        String createTableThuThu = "CREATE TABLE thuthu(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TENDANGNHAP TEXT," +
                "HOTEN TEXT," +
                "MATKHAU TEXT)";
        db.execSQL(createTableThuThu);// truy vấn
        String dstt = "INSERT INTO thuthu (TENDANGNHAP, HOTEN, MATKHAU) VALUES" +
                "('thuthu','Nguyễn Văn Toàn','thuthu')," +
                "('cr7','Cristiano Ronaldo','cr7')," +
                "('m10','Lionel Messi','m10')";
        db.execSQL(dstt);

        // phiếu mượn
        String createTablePM = "CREATE TABLE PM(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TENTV TEXT," +
                "TENSACH TEXT," +
                "TIENTHUE INTEGER," +
                "NGAYTHUE TEXT," +
                "TRANGTHAI INTEGER)";
        db.execSQL(createTablePM);
//        String pms = "INSERT INTO PM (TENTV, TENSACH, TIENTHUE,NGAYTHUE,TRANGTHAI) VALUES" +
//                "('Nguyễn Tuấn Phong','Photoshop',3000,'2023-01-10',1)";
//        db.execSQL(pms);

        // loại sách
        String createTableLoaiSach = "CREATE TABLE loaiSach(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TENLOAI TEXT)";
        db.execSQL(createTableLoaiSach);// truy vấn
        String dsls = "INSERT INTO loaiSach(TENLOAI) VALUES" +
                "('Đồ họa')," +
                "('Lập trình')";
        db.execSQL(dsls);

        // sách
        String createTableSach = "CREATE TABLE sach(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TENSACH TEXT," +
                "TIENTHUE INTEGER," +
                "LOAISACH TEXT," +
                "NAMXB TEXT)";
        db.execSQL(createTableSach);// truy vấn

        String dss = "INSERT INTO sach(TENSACH,TIENTHUE,LOAISACH,NAMXB) VALUES" +
                "('Doraemon',1000,'Lập trình',2000)," +
                "('Photoshop',3000,'Đồ họa',2013)";
        db.execSQL(dss);

        // thành viên
        String createTableThanhVien = "CREATE TABLE thanhvien(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TENTV TEXT," +
                "NAMSINH INT)";
        db.execSQL(createTableThanhVien);// truy vấn

        String macdinh = "INSERT INTO thanhvien(TENTV,NAMSINH) VALUES" +
                "('Nguyễn Tuấn Phong',2004)," +
                "('Kiều Phương Trinh',1999)";
        db.execSQL(macdinh);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // người sử dụng
        String dropTableUser = "DROP TABLE IF EXISTS user";
        db.execSQL(dropTableUser);
        onCreate(db);

        // phiếu mượn
        String dropTablePM = "DROP TABLE IF EXISTS PM";
        db.execSQL(dropTablePM);
        onCreate(db);

        // loại sách
        String dropTableLoaiSach = "DROP TABLE IF EXISTS loaiSach";
        db.execSQL(dropTableLoaiSach);
        onCreate(db);

        // sách
        String dropTableSach = "DROP TABLE IF EXISTS sach";
        db.execSQL(dropTableSach);
        onCreate(db);

        // thành viên
        String dropTableThanhVien = "DROP TABLE IF EXISTS thanhvien";
        db.execSQL(dropTableThanhVien);
        onCreate(db);

    }
}
