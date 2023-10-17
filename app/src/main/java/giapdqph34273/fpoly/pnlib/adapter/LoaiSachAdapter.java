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
import giapdqph34273.fpoly.pnlib.R;
import giapdqph34273.fpoly.pnlib.model.LoaiSach;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.LoaiSachViewHolder> {
    private Context context;
    private ArrayList<LoaiSach> list;
    LoaiSachDAO loaiSachDAO;

    public LoaiSachAdapter(Context context, ArrayList<LoaiSach> list) {
        this.context = context;
        this.list = list;
        loaiSachDAO = new LoaiSachDAO(context);
    }

    @NonNull
    @Override
    public LoaiSachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaisach, parent, false);
        return new LoaiSachAdapter.LoaiSachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiSachViewHolder holder, int position) {
        LoaiSach loaiSach = list.get(position);
        holder.txtTenLoai.setText(loaiSach.getTenLoai());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.drawable.baseline_question_mark_24);
                builder.setTitle("Xóa loại sách");
                builder.setMessage("Bạn có muốn xóa không ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // bắt sự kiện nhấn nút Yes
                        if (loaiSachDAO.deleteLS(loaiSach.getId()) > 0) {
                            list.clear();
                            list.addAll(loaiSachDAO.getAllLoaiSach());
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
                updateDialog(loaiSach);
                return true;
            }
        });
    }

    private void updateDialog(LoaiSach loaiSach) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update_loaisach, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText edtTenLoai;
        Button btnSua, btnHuy;

        edtTenLoai = view.findViewById(R.id.edtTenLoai);
        btnSua = view.findViewById(R.id.btnSua);
        btnHuy = view.findViewById(R.id.btnHuy);

        edtTenLoai.setText(loaiSach.getTenLoai());

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenloai = edtTenLoai.getText().toString();

                if (tenloai.isEmpty()){
                    Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                loaiSach.setTenLoai(tenloai);

                if (loaiSachDAO.updateLS(loaiSach)>0){
                    list.clear();
                    list.addAll(loaiSachDAO.getAllLoaiSach());
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

    public static class LoaiSachViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenLoai;
        ImageButton btnDelete;
        public LoaiSachViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenLoai = itemView.findViewById(R.id.txtTenLoai);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
