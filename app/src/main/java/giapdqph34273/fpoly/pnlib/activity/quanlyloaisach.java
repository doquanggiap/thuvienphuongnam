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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import giapdqph34273.fpoly.pnlib.DAO.LoaiSachDAO;
import giapdqph34273.fpoly.pnlib.DAO.AdminDao;
import giapdqph34273.fpoly.pnlib.R;
import giapdqph34273.fpoly.pnlib.adapter.LoaiSachAdapter;
import giapdqph34273.fpoly.pnlib.model.LoaiSach;

public class quanlyloaisach extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageButton btnThem;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private LoaiSachDAO loaiSachDAO;
    private ArrayList<LoaiSach> list;
    private LoaiSachAdapter loaiSachAdapter;
    private AdminDao adminDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlyloaisach);

        anhxa();
        setUpToolbar();

        loaiSachDAO = new LoaiSachDAO(this);
        list = loaiSachDAO.getAllLoaiSach();
        loaiSachAdapter = new LoaiSachAdapter(this,list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(loaiSachAdapter);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogThem();
            }
        });

    }

    private void dialogThem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(quanlyloaisach.this);
        View view = getLayoutInflater().inflate(R.layout.item_add_loaisach, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText edtTenLoai;
        Button btnAdd, btnHuy;

        edtTenLoai = view.findViewById(R.id.edtTenLoai);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnHuy = view.findViewById(R.id.btnHuy);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenloai = edtTenLoai.getText().toString();

                if (tenloai.isEmpty()){
                    Toast.makeText(quanlyloaisach.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                LoaiSach loaiSach = new LoaiSach(tenloai);

                if (loaiSachDAO.addLS(loaiSach) > 0) {
                    Toast.makeText(quanlyloaisach.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(loaiSachDAO.getAllLoaiSach());
                    loaiSachAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }else {
                    Toast.makeText(quanlyloaisach.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
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
        getSupportActionBar().setTitle("Quản lý loại sách");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.qlpm) {
                    Intent intent = new Intent(quanlyloaisach.this, quanlyphieumuon.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.qlls) {
                    drawerLayout.close();
                } else if (item.getItemId() == R.id.qls) {
                    Intent intent = new Intent(quanlyloaisach.this, quanlysach.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.qltv) {
                    Intent intent = new Intent(quanlyloaisach.this, quanlythanhvien.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.topten) {
                    Intent intent = new Intent(quanlyloaisach.this, Top10Sach.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.doanhthu) {
                    Intent intent = new Intent(quanlyloaisach.this, tongDoanhThu.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.themNguoiDung) {
                    SharedPreferences sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
                    String loggedInUser = sharedPreferences.getString("loggedInUser", "");
                    String loggedInPass = sharedPreferences.getString("loggedInPass", "");

                    if (adminDao.checkAdmin(loggedInUser,loggedInPass)) {
                        // Người dùng có quyền admin
                        // Cho phép họ truy cập chức năng thêm thành viên
                        Intent intent = new Intent(quanlyloaisach.this, themthuthu.class);
                        startActivity(intent);
                    } else {
                        // Người dùng không có quyền admin
                        Toast.makeText(quanlyloaisach.this, "Bạn không có quyền truy cập chức năng này.", Toast.LENGTH_SHORT).show();
                    }
                } else if (item.getItemId() == R.id.doiMatKhau) {
                    Intent intent = new Intent(quanlyloaisach.this, doiMatKhau.class);
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

    private void anhxa() {
        recyclerView = findViewById(R.id.recycleView);
        btnThem = findViewById(R.id.btnThem);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.my_toolbar);
        navigationView = findViewById(R.id.navigationView);
        adminDao = new AdminDao(this);
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
                Intent intent = new Intent(quanlyloaisach.this, dangNhap.class);

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