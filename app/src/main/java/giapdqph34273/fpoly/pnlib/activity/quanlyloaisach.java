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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import giapdqph34273.fpoly.pnlib.DAO.LoaiSachDAO;
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
        builder.setCancelable(false);
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

    private void anhxa() {
        recyclerView = findViewById(R.id.recycleView);
        btnThem = findViewById(R.id.btnThem);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.my_toolbar);
        navigationView = findViewById(R.id.navigationView);
    }

}