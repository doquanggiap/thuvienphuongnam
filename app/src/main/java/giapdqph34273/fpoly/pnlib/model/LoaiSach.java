package giapdqph34273.fpoly.pnlib.model;

public class LoaiSach {
    private int id;
    private String tenLoai;

    public LoaiSach(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
