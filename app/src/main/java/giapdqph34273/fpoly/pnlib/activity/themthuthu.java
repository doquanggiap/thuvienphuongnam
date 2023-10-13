package giapdqph34273.fpoly.pnlib.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import giapdqph34273.fpoly.pnlib.DAO.ThuThuDAO;
import giapdqph34273.fpoly.pnlib.R;
import giapdqph34273.fpoly.pnlib.model.ThanhVien;
import giapdqph34273.fpoly.pnlib.model.ThuThu;

public class themthuthu extends AppCompatActivity {
    EditText edtUser,edtHoten,edtPass,edtNhapLai;
    Button btnLuu;
    private ThuThuDAO thuThuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themthuthu);

        anhxa();

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString();
                String hoten = edtHoten.getText().toString();
                String pass = edtPass.getText().toString();
                String nhaplai = edtNhapLai.getText().toString();


                if (user.isEmpty()||hoten.isEmpty()||pass.isEmpty()||nhaplai.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!pass.equals(nhaplai)){
                    Toast.makeText(getApplicationContext(), "Nhập lại mật khẩu sai", Toast.LENGTH_SHORT).show();
                    return;
                }

                ThuThu thuThu = new ThuThu(user,hoten,pass);

                if (thuThuDAO.addTT(thuThu) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    edtHoten.setText("");
                    edtUser.setText("");
                    edtPass.setText("");
                    edtNhapLai.setText("");

                }else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void anhxa() {
        edtUser = findViewById(R.id.edtUser);
        edtHoten = findViewById(R.id.edtHoten);
        edtPass = findViewById(R.id.edtPass);
        edtNhapLai = findViewById(R.id.edtNhapLai);
        btnLuu = findViewById(R.id.btnLuu);
        thuThuDAO = new ThuThuDAO(this);
    }
}