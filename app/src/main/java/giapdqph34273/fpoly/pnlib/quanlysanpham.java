package giapdqph34273.fpoly.pnlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class quanlysanpham extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView navigationView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlysanpham);
    }
//    private void setUpToolbar() {
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_icon);
//        getSupportActionBar().setTitle("Quản lý bán hàng");
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                if (item.getItemId()==R.id.qlsp){
//                    drawerLayout.close();
//                } else if (item.getItemId()==R.id.gioithieu) {
//                    Intent intent = new Intent(quanLySanPham.this, gioiThieu.class);
//                    startActivity(intent);
//                } else if (item.getItemId()==R.id.caidat) {
//                    caiDatDialog();
//                } else if (item.getItemId()==R.id.dangxuat) {
//                    dialog_dangxuat();
//                }
//                return false;
//            }
//        });
//    }


}