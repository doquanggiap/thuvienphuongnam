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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import giapdqph34273.fpoly.pnlib.DAO.LoaiSachDAO;
import giapdqph34273.fpoly.pnlib.DAO.ThanhVienDAO;
import giapdqph34273.fpoly.pnlib.R;
import giapdqph34273.fpoly.pnlib.model.LoaiSach;
import giapdqph34273.fpoly.pnlib.model.ThanhVien;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ThanhVienViewHolder> {
    private Context context;
    private ArrayList<ThanhVien> list;
    ThanhVienDAO thanhVienDAO;

    public ThanhVienAdapter(Context context, ArrayList<ThanhVien> list) {
        this.context = context;
        this.list = list;
        thanhVienDAO = new ThanhVienDAO(context);
    }

    @NonNull
    @Override
    public ThanhVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thanhvien, parent, false);
        return new ThanhVienAdapter.ThanhVienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhVienViewHolder holder, int position) {
        ThanhVien thanhVien = list.get(position);
        holder.txtTenTV.setText(thanhVien.getTenTV());
        holder.txtNamSinh.setText(String.valueOf(thanhVien.getNamSinh()));

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.drawable.baseline_question_mark_24);
                builder.setCancelable(false);
                builder.setTitle("Xóa loại sách");
                builder.setMessage("Bạn có muốn xóa không ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // bắt sự kiện nhấn nút Yes
                        if (thanhVienDAO.deleteTV(thanhVien.getId()) > 0) {
                            list.clear();
                            list.addAll(thanhVienDAO.getAllThanhVien());
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
                updateDialog(thanhVien);
                return true;
            }
        });
    }

    private void updateDialog(ThanhVien thanhVien) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update_thanhvien, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText edtTenTV,edtNamSinh;
        Button btnSua,btnHuy;

        edtTenTV = view.findViewById(R.id.edtTenTV);
        edtNamSinh = view.findViewById(R.id.edtNamSinh);
        btnSua = view.findViewById(R.id.btnSua);
        btnHuy = view.findViewById(R.id.btnHuy);

        edtTenTV.setText(thanhVien.getTenTV());
        edtNamSinh.setText(String.valueOf(thanhVien.getNamSinh()));

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tentv = edtTenTV.getText().toString();
                String namsinh = edtNamSinh.getText().toString();

                if (tentv.isEmpty()||namsinh.isEmpty()){
                    Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!namsinh.matches("\\d+")){
                    Toast.makeText(context, "Năm sinh phải là số", Toast.LENGTH_SHORT).show();
                    return;
                }
                thanhVien.setTenTV(tentv);
                thanhVien.setNamSinh(Integer.parseInt(namsinh));

                if (thanhVienDAO.updateTV(thanhVien)>0){
                    list.clear();
                    list.addAll(thanhVienDAO.getAllThanhVien());
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

    public static class ThanhVienViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenTV,txtNamSinh;
        ImageButton btnDelete;
        public ThanhVienViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenTV = itemView.findViewById(R.id.txtTenTV);
            txtNamSinh = itemView.findViewById(R.id.txtNamSinh);
            btnDelete = itemView.findViewById(R.id.btnDelete);

        }
    }
}
