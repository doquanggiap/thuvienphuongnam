package giapdqph34273.fpoly.pnlib.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import giapdqph34273.fpoly.pnlib.DAO.PhieuMuonDAO;
import giapdqph34273.fpoly.pnlib.R;
import giapdqph34273.fpoly.pnlib.model.TopSach;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.TopViewHolder> {
    private Context context;
    private ArrayList<TopSach> list;
    public TopAdapter(Context context, ArrayList<TopSach> list, PhieuMuonDAO phieumuonDAO) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public TopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_top10, parent, false);
        return new TopAdapter.TopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopViewHolder holder, int position) {
        TopSach topSach = list.get(position);
        holder.txtTenSach.setText(topSach.getTenSach());
        holder.txtSoLuong.setText(String.valueOf(topSach.getSoLuong()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class TopViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenSach,txtSoLuong;
        public TopViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong);

        }
    }
}
