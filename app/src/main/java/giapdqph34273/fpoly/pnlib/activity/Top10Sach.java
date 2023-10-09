package giapdqph34273.fpoly.pnlib.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import giapdqph34273.fpoly.pnlib.DAO.PhieuMuonDAO;
import giapdqph34273.fpoly.pnlib.R;
import giapdqph34273.fpoly.pnlib.adapter.TopAdapter;
import giapdqph34273.fpoly.pnlib.model.TopSach;

public class Top10Sach extends AppCompatActivity {
    RecyclerView recyclerView;
    PhieuMuonDAO phieumuonDAO;
    ArrayList<TopSach> list = new ArrayList<>();
    TopAdapter topAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top10_sach);

        recyclerView = findViewById(R.id.recycleView);
        phieumuonDAO = new PhieuMuonDAO(this);
        list = (ArrayList<TopSach>) phieumuonDAO.getTop10Sach();
        topAdapter = new TopAdapter(Top10Sach.this, list, phieumuonDAO);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(topAdapter);
    }
}