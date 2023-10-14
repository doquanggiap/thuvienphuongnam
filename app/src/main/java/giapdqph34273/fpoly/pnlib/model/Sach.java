package giapdqph34273.fpoly.pnlib.model;

public class Sach {
    private int id;
    private String tenSach;
    private int tienThue;
    private String loaiSach;
    private int namXB;

    public Sach(String tenSach, int tienThue, String loaiSach, int namXB) {
        this.tenSach = tenSach;
        this.tienThue = tienThue;
        this.loaiSach = loaiSach;
        this.namXB = namXB;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }

    public String getLoaiSach() {
        return loaiSach;
    }

    public void setLoaiSach(String loaiSach) {
        this.loaiSach = loaiSach;
    }

    public int getNamXB() {
        return namXB;
    }

    public void setNamXB(int namXB) {
        this.namXB = namXB;
    }
}
