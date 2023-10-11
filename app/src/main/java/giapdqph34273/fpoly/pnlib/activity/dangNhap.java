package giapdqph34273.fpoly.pnlib.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import giapdqph34273.fpoly.pnlib.DAO.NguoiDungDao;
import giapdqph34273.fpoly.pnlib.R;

public class dangNhap extends AppCompatActivity {
    EditText txtUser, txtPass;
    Button btnDangnhap, btnHuy;
    NguoiDungDao nguoiDungDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPass);
        btnDangnhap = findViewById(R.id.btnDangnhap);
        btnHuy = findViewById(R.id.btnHuy);
        nguoiDungDao = new NguoiDungDao(dangNhap.this);

        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txtUser.getText().toString();
                String pass = txtPass.getText().toString();
                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(dangNhap.this, "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (nguoiDungDao.checkUser(user,pass)){
                    Toast.makeText(dangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(dangNhap.this, quanlyphieumuon.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(dangNhap.this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}