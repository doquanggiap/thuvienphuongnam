package giapdqph34273.fpoly.pnlib.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import giapdqph34273.fpoly.pnlib.database.DBHelper;

public class AdminDao {
    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private Context context;
    private SharedPreferences sharedPreferences;

    public AdminDao(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        sharedPreferences = context.getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
    }

    public boolean checkUser(String username, String password) { // kiểm tra tài khoản có trong bảng admin không
        database = dbHelper.getReadableDatabase();
        String[] columns = {"ID"};
        String selection = "TENDANGNHAP" + "=? and " + "MATKHAU" + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = database.query("admin", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        database.close();
        return count > 0;
    }

    public boolean checkPasswordAndChange(String oldPassword, String newPassword) {
        // Thực hiện kiểm tra mật khẩu cũ và username cũ ở đây
        String username = sharedPreferences.getString("loggedInUser", "");

        if (checkUser(username, oldPassword)) {
            // Mật khẩu cũ đúng
            database = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("MATKHAU", newPassword); // Cập nhật mật khẩu mới

            // Thực hiện cập nhật mật khẩu mới cho người dùng có tên đăng nhập tương ứng
            database.update("admin", values, "TENDANGNHAP = ?", new String[]{username});
            database.close();
            return true; // Trả về true nếu đổi mật khẩu thành công
        } else {
            // Mật khẩu cũ không đúng
            return false;
        }
    }



}
