package giapdqph34273.fpoly.pnlib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class dangNhap extends AppCompatActivity {
    EditText txtUser, txtPass;
    Button btnDangnhap, btnHuy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPass);
        btnDangnhap = findViewById(R.id.btnDangnhap);
        btnHuy = findViewById(R.id.btnHuy);

        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txtUser.getText().toString();
                String pass = txtPass.getText().toString();
                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(dangNhap.this, "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

}