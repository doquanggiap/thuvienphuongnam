package giapdqph34273.fpoly.pnlib.model;

public class ThanhVien {
    private int id;
    private String tenTV;
    private int namSinh;

    public ThanhVien(String tenTV, int namSinh) {
        this.tenTV = tenTV;
        this.namSinh = namSinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenTV() {
        return tenTV;
    }

    public void setTenTV(String tenTV) {
        this.tenTV = tenTV;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }
}
