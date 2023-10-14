package giapdqph34273.fpoly.pnlib.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import giapdqph34273.fpoly.pnlib.DAO.AdminDao;
import giapdqph34273.fpoly.pnlib.DAO.ThuThuDAO;
import giapdqph34273.fpoly.pnlib.R;

public class dangNhap extends AppCompatActivity {
    EditText txtUser, txtPass;
    Button btnDangnhap, btnHuy;
    AdminDao adminDao;
    ThuThuDAO thuThuDAO;
    CheckBox chkRemember;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        anhxa();

        SharedPreferences sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString("loggedInUser", "");
        String savedPassword = sharedPreferences.getString("loggedInPass", "");

        if (!savedUsername.isEmpty() && !savedPassword.isEmpty()) {
            // Đã có username và mật khẩu đã lưu trước đó
            // Gán username và mật khẩu vào EditText hoặc tự động đăng nhập
            txtUser.setText(savedUsername);
            txtPass.setText(savedPassword);

            // Cho checkbox Nhớ mật khẩu có dấu tích v
            chkRemember.setChecked(true);
        }

        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txtUser.getText().toString();
                String pass = txtPass.getText().toString();
                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(dangNhap.this, "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (adminDao.checkUser(user, pass)) {
                    // Lưu tên đăng nhập vào SharedPreferences khi đăng nhập thành công
                    if (chkRemember.isChecked()){
                        SharedPreferences sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("loggedInUser", user); // Lưu tên đăng nhập
                        editor.putString("loggedInPass", pass); // Lưu tên đăng nhập
                        editor.apply();
                    }
                    else{
                        SharedPreferences sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("loggedInUser");
                        editor.remove("loggedInPass");
                        editor.apply();
                    }
                    Toast.makeText(dangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(dangNhap.this, quanlyphieumuon.class);
                    startActivity(intent);
                    finish();
                } else if (thuThuDAO.checkTT(user,pass)) {
                    if (chkRemember.isChecked()){
                        SharedPreferences sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("loggedInUser", user); // Lưu tên đăng nhập
                        editor.putString("loggedInPass", pass); // Lưu tên đăng nhập
                        editor.apply();
                    }
                    else{
                        SharedPreferences sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("loggedInUser");
                        editor.remove("loggedInPass");
                        editor.apply();
                    }
                    Toast.makeText(dangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(dangNhap.this, quanlyphieumuon.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(dangNhap.this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }





            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đóng màn hình hiện tại
                finish();

                // Đóng ứng dụng
                System.exit(0);
            }
        });

    }

    private void anhxa() {
        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPass);
        btnDangnhap = findViewById(R.id.btnDangnhap);
        btnHuy = findViewById(R.id.btnHuy);
        chkRemember = findViewById(R.id.chkRemember);
        adminDao = new AdminDao(this);
        thuThuDAO = new ThuThuDAO(this);
    }

}