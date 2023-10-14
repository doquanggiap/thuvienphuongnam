package giapdqph34273.fpoly.pnlib.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import giapdqph34273.fpoly.pnlib.database.DBHelper;
import giapdqph34273.fpoly.pnlib.model.LoaiSach;
import giapdqph34273.fpoly.pnlib.model.ThuThu;

public class ThuThuDAO {
    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private Context context;
    private SharedPreferences sharedPreferences;

    public ThuThuDAO(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        sharedPreferences = context.getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
    }

    public long addTT(ThuThu thuThu) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENDANGNHAP", thuThu.getTenTT());
        values.put("HOTEN", thuThu.getHoTen());
        values.put("MATKHAU", thuThu.getMatKhau());
        return database.insert("thuthu", null, values);
    }

    public boolean checkTT(String username, String password) { // kiểm tra tài khoản có trong bảng thuthu không
        database = dbHelper.getReadableDatabase();
        String[] columns = {"ID"};
        String selection = "TENDANGNHAP" + "=? and " + "MATKHAU" + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = database.query("thuthu", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        database.close();
        return count > 0;
    }
    public boolean doiMKTT(String oldPassword, String newPassword) {
        // Thực hiện kiểm tra mật khẩu cũ và username cũ ở đây
        String username = sharedPreferences.getString("loggedInUser", "");

        if (checkTT(username, oldPassword)) {
            // Mật khẩu cũ đúng
            database = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("MATKHAU", newPassword); // Cập nhật mật khẩu mới

            // Thực hiện cập nhật mật khẩu mới cho người dùng có tên đăng nhập tương ứng
            database.update("thuthu", values, "TENDANGNHAP = ?", new String[]{username});
            database.close();
            return true; // Trả về true nếu đổi mật khẩu thành công
        } else {
            // Mật khẩu cũ không đúng
            return false;
        }
    }
}
