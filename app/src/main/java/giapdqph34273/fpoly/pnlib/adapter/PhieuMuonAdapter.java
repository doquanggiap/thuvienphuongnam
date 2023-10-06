package giapdqph34273.fpoly.pnlib.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import giapdqph34273.fpoly.pnlib.DAO.PhieuMuonDAO;
import giapdqph34273.fpoly.pnlib.R;
import giapdqph34273.fpoly.pnlib.model.PhieuMuon;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.PhieuMuonViewHolder> {
    private Context context;
    ArrayList<PhieuMuon> list;
    PhieuMuonDAO phieuMuonDAO;

    public PhieuMuonAdapter(Context context, ArrayList<PhieuMuon> list) {
        this.context = context;
        this.list = list;
        phieuMuonDAO = new PhieuMuonDAO(context);
    }

    @NonNull
    @Override
    public PhieuMuonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_phieumuon, parent, false);
        return new PhieuMuonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhieuMuonViewHolder holder, int position) {
        PhieuMuon phieuMuon = list.get(position);
        holder.txtTenTV.setText(phieuMuon.getTenTV());
        holder.txtNgay.setText(phieuMuon.getNgayThue());
        holder.txtTenSach.setText(phieuMuon.getTenSach());
        holder.txtTienThue.setText(String.valueOf(phieuMuon.getTienThue()));
        if (phieuMuon.getTrangThaiMuon()==1){
            holder.txtTrangThai.setText("Đã trả sách");
            holder.txtTrangThai.setTextColor(Color.parseColor("#1D0FC6"));
        }else{
            holder.txtTrangThai.setText("Chưa trả sách");
            holder.txtTrangThai.setTextColor(Color.parseColor("#ED0C0C"));
        }

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.drawable.baseline_question_mark_24);
                builder.setCancelable(false);
                builder.setTitle("Xóa phiếu mượn");
                builder.setMessage("Bạn có muốn xóa không ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // bắt sự kiện nhấn nút Yes
                        if (phieuMuonDAO.deletePM(phieuMuon.getId()) > 0) {
                            list.clear();
                            list.addAll(phieuMuonDAO.getAllPhieuMuon());
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // bắt sự kiện nhấn nút No
                    }
                });
                builder.show();
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                updateDialog(phieuMuon);
                return true;
            }
        });


    }

    public void updateDialog(PhieuMuon phieuMuon) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update_phieumuon, null);
        builder.setView(view);
        builder.setCancelable(false);
        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText edtTenTV, edtTenSach;
        TextView txtNgayThue, txtTienThue;
        CheckBox chkTrangThai;
        Button btnSua, btnHuy;

        edtTenTV = view.findViewById(R.id.edtTenTV);
        edtTenSach = view.findViewById(R.id.edtTenSach);
        txtNgayThue = view.findViewById(R.id.txtNgayThue);
        txtTienThue = view.findViewById(R.id.txtTienThue);
        chkTrangThai = view.findViewById(R.id.chkTrangThai);
        btnSua = view.findViewById(R.id.btnSua);
        btnHuy = view.findViewById(R.id.btnHuy);

        edtTenTV.setText(phieuMuon.getTenTV());
        edtTenSach.setText(phieuMuon.getTenSach());
        txtNgayThue.setText(phieuMuon.getNgayThue());
        txtTienThue.setText(String.valueOf(phieuMuon.getTienThue()));
        if (phieuMuon.getTrangThaiMuon() == 1) {
            chkTrangThai.setChecked(true);
        } else {
            chkTrangThai.setChecked(false);
        }
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenTV = edtTenTV.getText().toString();
                String tenSach = edtTenSach.getText().toString();

                if (chkTrangThai.isChecked()){
                    phieuMuon.setTrangThaiMuon(1);
                }else{
                    phieuMuon.setTrangThaiMuon(0);
                }

                if(tenTV.isEmpty()||tenSach.isEmpty()){
                    Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                phieuMuon.setTenTV(tenTV);
                phieuMuon.setTenSach(tenSach);

                if (phieuMuonDAO.updatePM(phieuMuon)>0){
                    list.clear();
                    list.addAll(phieuMuonDAO.getAllPhieuMuon());
                    notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Lỗi cập nhật", Toast.LENGTH_SHORT).show();
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


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class PhieuMuonViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenTV, txtTenSach, txtTienThue, txtTrangThai, txtNgay;
        ImageButton btnDelete;

        public PhieuMuonViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenTV = itemView.findViewById(R.id.txtTenTV);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);
            txtTienThue = itemView.findViewById(R.id.txtTienThue);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
            txtNgay = itemView.findViewById(R.id.txtNgay);
            btnDelete = itemView.findViewById(R.id.btnDelete);

        }

    }
}
