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
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import giapdqph34273.fpoly.pnlib.DAO.LoaiSachDAO;
import giapdqph34273.fpoly.pnlib.DAO.SachDAO;
import giapdqph34273.fpoly.pnlib.R;
import giapdqph34273.fpoly.pnlib.adapter.SachAdapter;
import giapdqph34273.fpoly.pnlib.model.LoaiSach;
import giapdqph34273.fpoly.pnlib.model.Sach;

public class quanlysach extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageButton btnThem;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private SachDAO sachDAO;
    private ArrayList<Sach> list;
    private SachAdapter sachAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlysach);

        anhxa();
        setUpToolbar();

        sachDAO = new SachDAO(this);
        list = sachDAO.getAllSach();
        sachAdapter = new SachAdapter(this,list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(sachAdapter);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogThem();
            }
        });

    }

    private void dialogThem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(quanlysach.this);
        View view = getLayoutInflater().inflate(R.layout.item_add_sach, null);
        builder.setView(view);
        builder.setCancelable(false);
        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText edtTenSach,edtTienThue;
        Button btnAdd, btnHuy;
        Spinner edtTenLoai;

        edtTenSach = view.findViewById(R.id.edtTenSach);
        edtTienThue = view.findViewById(R.id.edtTienThue);
        edtTenLoai = view.findViewById(R.id.edtTenLoai);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnHuy = view.findViewById(R.id.btnHuy);

        ArrayList<String> tenLoaiSachList = getTenLoaiSachList();
        ArrayAdapter<String> spinnerLoaiSach = new ArrayAdapter<>(
                quanlysach.this,
                android.R.layout.simple_spinner_item,
                tenLoaiSachList
                );
        spinnerLoaiSach.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtTenLoai.setAdapter(spinnerLoaiSach);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tensach = edtTenSach.getText().toString();
                String tienthue = edtTienThue.getText().toString();
                String tenloai = edtTenLoai.getSelectedItem().toString();

                if (tensach.isEmpty()||tienthue.isEmpty()||tenloai.isEmpty()){
                    Toast.makeText(quanlysach.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!tienthue.matches("\\d+")){
                    Toast.makeText(quanlysach.this, "Tiền thuê phải là sô", Toast.LENGTH_SHORT).show();
                    return;
                }
                Sach sach = new Sach(tensach,Integer.parseInt(tienthue),tenloai);

                if (sachDAO.addS(sach) > 0) {
                    Toast.makeText(quanlysach.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(sachDAO.getAllSach());
                    sachAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }else {
                    Toast.makeText(quanlysach.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
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

    private ArrayList<String> getTenLoaiSachList() {
        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(getApplicationContext());
        ArrayList<LoaiSach> list1 = loaiSachDAO.getAllLoaiSach();
        ArrayList<String> tenLoaiSachList = new ArrayList<>();

        for (LoaiSach loaiSach: list1){
            tenLoaiSachList.add(loaiSach.getTenLoai());
        }
        return tenLoaiSachList;
    }

    private void anhxa() {
        recyclerView = findViewById(R.id.recycleView);
        btnThem = findViewById(R.id.btnThem);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.my_toolbar);
        navigationView = findViewById(R.id.navigationView);
    }
    private void setUpToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_icon);
        getSupportActionBar().setTitle("Quản lý sách");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.qlpm) {
                    Intent intent = new Intent(quanlysach.this, quanlyphieumuon.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.qlls) {
                    Intent intent = new Intent(quanlysach.this, quanlyloaisach.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.qls) {
                    drawerLayout.close();
                } else if (item.getItemId() == R.id.qltv) {
                    Intent intent = new Intent(quanlysach.this, quanlythanhvien.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.topten) {
                    Intent intent = new Intent(quanlysach.this, Top10Sach.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.doanhthu) {
                    Intent intent = new Intent(quanlysach.this, Top10Sach.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.themThanhVien) {

                } else if (item.getItemId() == R.id.doiMatKhau) {
                    Intent intent = new Intent(quanlysach.this, doiMatKhau.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.dangxuat) {
                    dialog_dangxuat();
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
    public void dialog_dangxuat() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.baseline_question_mark_24);
        builder.setCancelable(false);
        builder.setTitle("Đăng xuất");
        builder.setMessage("Bạn có muốn đăng xuất không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Bắt sự kiện nhấn nút Có
                SharedPreferences sharedPreferences = getSharedPreferences("thongtin", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", false);
                editor.apply();
                Intent intent = new Intent(quanlysach.this, dangNhap.class);

                // Đặt cờ FLAG_ACTIVITY_NEW_TASK để tạo một nhiệm vụ mới
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Bắt sự kiện nhấn nút Không
            }
        });
        builder.show();
    }

}