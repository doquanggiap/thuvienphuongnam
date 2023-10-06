package giapdqph34273.fpoly.pnlib.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.time.LocalDate;
import java.util.ArrayList;

import giapdqph34273.fpoly.pnlib.DAO.PhieuMuonDAO;
import giapdqph34273.fpoly.pnlib.R;
import giapdqph34273.fpoly.pnlib.adapter.PhieuMuonAdapter;
import giapdqph34273.fpoly.pnlib.model.PhieuMuon;

public class quanlyphieumuon extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView navigationView;
    RecyclerView recyclerView;
    ImageButton btnThem;
    DrawerLayout drawerLayout;
    PhieuMuonDAO phieuMuonDAO;
    ArrayList<PhieuMuon> list;
    PhieuMuonAdapter phieuMuonAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlyphieumuon);

        recyclerView = findViewById(R.id.recycleView);
        btnThem = findViewById(R.id.btnThem);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.my_toolbar);
        navigationView = findViewById(R.id.navigationView);
        setUpToolbar();

        phieuMuonDAO = new PhieuMuonDAO(this);
        list = phieuMuonDAO.getAllPhieuMuon();
        phieuMuonAdapter = new PhieuMuonAdapter(this, list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(phieuMuonAdapter);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_them();
            }
        });


    }

    public void dialog_them() {
        AlertDialog.Builder builder = new AlertDialog.Builder(quanlyphieumuon.this);
        View view = getLayoutInflater().inflate(R.layout.item_add_phieumuon, null);
        builder.setView(view);
        builder.setCancelable(false);
        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText edtTenTV, edtTenSach, edtTienThue;
        CheckBox chkTrangThai;
        Button btnAdd, btnHuy;

        edtTenTV = view.findViewById(R.id.edtTenTV);
        edtTenSach = view.findViewById(R.id.edtTenSach);
        edtTienThue = view.findViewById(R.id.edtTienThue);
        chkTrangThai = view.findViewById(R.id.chkTrangThai);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnHuy = view.findViewById(R.id.btnHuy);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenTV = edtTenTV.getText().toString();
                String tenSach = edtTenSach.getText().toString();
                String tienThue = edtTienThue.getText().toString();

                if (tenTV.isEmpty() || tenSach.isEmpty() || tienThue.isEmpty()) {
                    Toast.makeText(quanlyphieumuon.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!tienThue.matches("\\d+")) {
                    Toast.makeText(quanlyphieumuon.this, "Tiền thuê phải là số", Toast.LENGTH_SHORT).show();
                    return;
                }
                String ngay = String.valueOf(LocalDate.now()); // lấy ngày hiện tại



                PhieuMuon pm = new PhieuMuon();
                pm.setTenTV(tenTV);
                pm.setTenSach(tenSach);
                pm.setTienThue(Integer.parseInt(tienThue));
                pm.setNgayThue(ngay);
                if (chkTrangThai.isChecked()){
                    pm.setTrangThaiMuon(1);
                }else{
                    pm.setTrangThaiMuon(0);
                }
                if (phieuMuonDAO.addPM(pm) > 0) {
                    Toast.makeText(quanlyphieumuon.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    list.clear();//xóa tất cả các phần tử trong danh sách list
                    list.addAll(phieuMuonDAO.getAllPhieuMuon());//thêm tất cả các phần tử của danh sách sản phẩm được lấy từ cơ sở dữ liệu vào danh sách list.
                    phieuMuonAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }else {
                    Toast.makeText(quanlyphieumuon.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }


            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_icon);
        getSupportActionBar().setTitle("Quản lý phiếu mượn");
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.qlpm) {
                    drawerLayout.close();
                } else if (item.getItemId() == R.id.qlls) {
                    Intent intent = new Intent(quanlyphieumuon.this, quanlyloaisach.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.qls) {
                    Intent intent = new Intent(quanlyphieumuon.this, quanlysach.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.qltv) {

                } else if (item.getItemId() == R.id.topten) {

                } else if (item.getItemId() == R.id.doanhthu) {

                } else if (item.getItemId() == R.id.themThanhVien) {

                } else if (item.getItemId() == R.id.doiMatKhau) {

                } else if (item.getItemId() == R.id.dangxuat) {

                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }


}