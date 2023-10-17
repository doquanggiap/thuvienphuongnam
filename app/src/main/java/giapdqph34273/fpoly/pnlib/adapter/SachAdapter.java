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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import giapdqph34273.fpoly.pnlib.DAO.LoaiSachDAO;
import giapdqph34273.fpoly.pnlib.DAO.SachDAO;
import giapdqph34273.fpoly.pnlib.R;
import giapdqph34273.fpoly.pnlib.model.LoaiSach;
import giapdqph34273.fpoly.pnlib.model.Sach;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.SachViewHolder> {
    private Context context;
    private ArrayList<Sach> list;
    SachDAO sachDAO;

    public SachAdapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
        sachDAO = new SachDAO(context);
    }

    @NonNull
    @Override
    public SachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sach, parent, false);
        return new SachAdapter.SachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SachViewHolder holder, int position) {
        Sach sach = list.get(position);
        holder.txtTenSach.setText(sach.getTenSach());
        holder.txtTienThue.setText(String.valueOf(sach.getTienThue()));
        holder.txtLoaiSach.setText(sach.getLoaiSach());
        holder.txtNamXB.setText(String.valueOf(sach.getNamXB()));
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.drawable.baseline_question_mark_24);
                builder.setCancelable(false);
                builder.setTitle("Xóa sách");
                builder.setMessage("Bạn có muốn xóa không ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // bắt sự kiện nhấn nút Yes
                        if (sachDAO.deleteS(sach.getId()) > 0) {
                            list.clear();
                            list.addAll(sachDAO.getAllSach());
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
                updateDialog(sach);
                return true;
            }
        });
    }

    private void updateDialog(Sach sach) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update_sach, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText edtTenSach,edtTienThue,edtNamXB;
        Spinner edtTenLoai;
        Button btnSua, btnHuy;

        edtTenSach = view.findViewById(R.id.edtTenSach);
        edtTienThue = view.findViewById(R.id.edtTienThue);
        edtTenLoai = view.findViewById(R.id.edtTenLoai);
        edtNamXB = view.findViewById(R.id.edtNamXB);
        btnSua = view.findViewById(R.id.btnSua);
        btnHuy = view.findViewById(R.id.btnHuy);

        edtTenSach.setText(sach.getTenSach());
        edtTienThue.setText(String.valueOf(sach.getTienThue()));
        edtNamXB.setText(String.valueOf(sach.getNamXB()));
        // Thay đổi dòng này:
// edtTenLoai.setText(sach.getLoaiSach());
// Thành dòng này:
        edtTenLoai.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, getTenLoaiSachList()));
        edtTenLoai.setSelection(getTenLoaiSachList().indexOf(sach.getLoaiSach()));


        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tensach = edtTenSach.getText().toString();
                String tienthue = edtTienThue.getText().toString();
                String tenloai = edtTenLoai.getSelectedItem().toString();
                String namxb = edtNamXB.getText().toString();

                if (tensach.isEmpty()||tienthue.isEmpty()||namxb.isEmpty()){
                    Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!tienthue.matches("\\d+")){
                    Toast.makeText(context, "Tiền thuê phải là số", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!namxb.matches("\\d+")){
                    Toast.makeText(context, "Năm xuất bản phải là số", Toast.LENGTH_SHORT).show();
                    return;
                }

                sach.setTenSach(tensach);
                sach.setTienThue(Integer.parseInt(tienthue));
                sach.setLoaiSach(tenloai);
                sach.setNamXB(Integer.parseInt(namxb));

                if (sachDAO.updateS(sach)>0){
                    list.clear();
                    list.addAll(sachDAO.getAllSach());
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
    private ArrayList<String> getTenLoaiSachList() {
        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
        ArrayList<LoaiSach> list1 = loaiSachDAO.getAllLoaiSach();
        ArrayList<String> tenLoaiSachList = new ArrayList<>();

        for (LoaiSach loaiSach: list1){
            tenLoaiSachList.add(loaiSach.getTenLoai());
        }
        return tenLoaiSachList;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class SachViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenSach,txtTienThue,txtLoaiSach,txtNamXB;
        ImageButton btnDelete;
        public SachViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);
            txtTienThue = itemView.findViewById(R.id.txtTienThue);
            txtLoaiSach = itemView.findViewById(R.id.txtLoaiSach);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            txtNamXB = itemView.findViewById(R.id.txtNamXB);

        }
    }
}
