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
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.time.LocalDate;
import java.util.ArrayList;

import giapdqph34273.fpoly.pnlib.DAO.AdminDao;
import giapdqph34273.fpoly.pnlib.DAO.PhieuMuonDAO;
import giapdqph34273.fpoly.pnlib.DAO.SachDAO;
import giapdqph34273.fpoly.pnlib.DAO.ThanhVienDAO;
import giapdqph34273.fpoly.pnlib.R;
import giapdqph34273.fpoly.pnlib.adapter.PhieuMuonAdapter;
import giapdqph34273.fpoly.pnlib.model.PhieuMuon;
import giapdqph34273.fpoly.pnlib.model.Sach;
import giapdqph34273.fpoly.pnlib.model.ThanhVien;

public class quanlyphieumuon extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView navigationView;
    RecyclerView recyclerView;
    ImageButton btnThem;
    DrawerLayout drawerLayout;
    PhieuMuonDAO phieuMuonDAO;
    ArrayList<PhieuMuon> list;
    PhieuMuonAdapter phieuMuonAdapter;
    AdminDao adminDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlyphieumuon);

        anhxa();
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

    private void anhxa() {
        recyclerView = findViewById(R.id.recycleView);
        btnThem = findViewById(R.id.btnThem);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.my_toolbar);
        navigationView = findViewById(R.id.navigationView);
        adminDao = new AdminDao(this);
    }

    public void dialog_them() {
        AlertDialog.Builder builder = new AlertDialog.Builder(quanlyphieumuon.this);
        View view = getLayoutInflater().inflate(R.layout.item_add_phieumuon, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Spinner edtTenSach,edtTenTV;
        CheckBox chkTrangThai;
        Button btnAdd, btnHuy;

        edtTenTV = view.findViewById(R.id.edtTenTV);
        edtTenSach = view.findViewById(R.id.edtTenSach);
        chkTrangThai = view.findViewById(R.id.chkTrangThai);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnHuy = view.findViewById(R.id.btnHuy);

        ArrayList<String> tenTVList = getTenTV();
        ArrayAdapter<String> spinnerTV = new ArrayAdapter<>(
                quanlyphieumuon.this,
                android.R.layout.simple_spinner_item,
                tenTVList
        );

        spinnerTV.
                setDropDownViewResource(android.R.layout.
                        simple_spinner_dropdown_item);

        edtTenTV.setAdapter(spinnerTV);

        ArrayList<String> tenSachList = getTenSachList();      // Lấy danh sách tên sách và lưu vào biến tenSachList
        ArrayAdapter<String> spinnerSach = new ArrayAdapter<>( // Tạo một Adapter để hiển thị danh sách tên sách trong Spinner
                quanlyphieumuon.this,                          // Context của Activity hiện tại
                android.R.layout.simple_spinner_item,          // Layout cho mỗi mục trong Spinner
                tenSachList                                    // Danh sách dữ liệu cho Spinner
        );

        spinnerSach.
                setDropDownViewResource(android.R.layout.
                        simple_spinner_dropdown_item);         // Đặt layout cho dropdown của Spinner (có thể thay đổi nếu muốn)

        edtTenSach.setAdapter(spinnerSach);                   // Gắn Adapter vào Spinner để hiển thị danh sách tên sách

        ArrayList<Integer> giaTienThueList =
                getGiaTienThueList();                         // Lấy danh sách giá tiền thuê sách và lưu vào biến giaTienThueList

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenTV = edtTenTV.getSelectedItem().toString();
                String tenSach = edtTenSach.getSelectedItem().toString();
                int giaTienThue =
                        giaTienThueList.get(edtTenSach.getSelectedItemPosition()); // lấy giá tiền thuê của cuốn sách được chọn trong Spinner

                if (tenTV.isEmpty() || tenSach.isEmpty()) {
                    Toast.makeText(quanlyphieumuon.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                String ngay = String.valueOf(LocalDate.now()); // lấy ngày hiện tại

                PhieuMuon pm = new PhieuMuon();
                pm.setTenTV(tenTV);
                pm.setTenSach(tenSach);
                pm.setTienThue(giaTienThue);
                pm.setNgayThue(ngay);
                if (chkTrangThai.isChecked()) {
                    pm.setTrangThaiMuon(1);
                } else {
                    pm.setTrangThaiMuon(0);
                }
                if (phieuMuonDAO.addPM(pm) > 0) {
                    Toast.makeText(quanlyphieumuon.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    list.clear();//xóa tất cả các phần tử trong danh sách list
                    list.addAll(phieuMuonDAO.getAllPhieuMuon());//thêm tất cả các phần tử của danh sách sản phẩm được lấy từ cơ sở dữ liệu vào danh sách list.
                    phieuMuonAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                } else {
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

    private ArrayList<Integer> getGiaTienThueList() { // lấy danh sách tiền thuê của tất cả sách
        SachDAO sachDAO = new SachDAO(getApplicationContext());
        ArrayList<Sach> list1 = sachDAO.getAllSach();
        ArrayList<Integer> giaTienThueList = new ArrayList<>();

        for (Sach sach : list1) {
            giaTienThueList.add(sach.getTienThue());
        }
        return giaTienThueList;
    }


    private ArrayList<String> getTenSachList() { // lấy danh sách các cuốn sách
        SachDAO sachDAO = new SachDAO(getApplicationContext());
        ArrayList<Sach> list1 = sachDAO.getAllSach();
        ArrayList<String> tenSachList = new ArrayList<>();

        for (Sach sach : list1) {
            tenSachList.add(sach.getTenSach());
        }
        return tenSachList;
    }

    private ArrayList<String> getTenTV() { // lấy danh sách các thành viên
        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(getApplicationContext());
        ArrayList<ThanhVien> list1 = thanhVienDAO.getAllThanhVien();
        ArrayList<String> tenTVList = new ArrayList<>();

        for (ThanhVien thanhVien : list1) {
            tenTVList.add(thanhVien.getTenTV());
        }
        return tenTVList;
    }



    private void setUpToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_icon);
        getSupportActionBar().setTitle("Quản lý phiếu mượn");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
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
                    Intent intent = new Intent(quanlyphieumuon.this, quanlythanhvien.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.topten) {
                    Intent intent = new Intent(quanlyphieumuon.this, Top10Sach.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.doanhthu) {
                    Intent intent = new Intent(quanlyphieumuon.this, tongDoanhThu.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.themNguoiDung) {
                    SharedPreferences sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
                    String loggedInUser = sharedPreferences.getString("loggedInUser", "");
                    String loggedInPass = sharedPreferences.getString("loggedInPass", "");

                    if (adminDao.checkUser(loggedInUser,loggedInPass)) {
                        // Người dùng có quyền admin
                        // Cho phép họ truy cập chức năng thêm thành viên
                        Intent intent = new Intent(quanlyphieumuon.this, themthuthu.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(quanlyphieumuon.this, "Bạn không có quyền truy cập chức năng này.", Toast.LENGTH_SHORT).show();
                    }
                } else if (item.getItemId() == R.id.doiMatKhau) {
                    Intent intent = new Intent(quanlyphieumuon.this, doiMatKhau.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.dangxuat) {
                    dialog_dangxuat();
                }
                return false;
            }
        });
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
                Intent intent = new Intent(quanlyphieumuon.this, dangNhap.class);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}