package giapdqph34273.fpoly.pnlib.model;

public class PhieuMuon {
    private int id;
    private String tenTV;
    private String tenSach;
    private int tienThue;
    private String ngayThue;
    private int trangThaiMuon;
    private int isHidden;

    public PhieuMuon(String tenTV, String tenSach, int tienThue, String ngayThue, int trangThaiMuon) {
        this.tenTV = tenTV;
        this.tenSach = tenSach;
        this.tienThue = tienThue;
        this.ngayThue = ngayThue;
        this.trangThaiMuon = trangThaiMuon;
    }

    public PhieuMuon(String tenTV, String tenSach, int tienThue, String ngayThue, int trangThaiMuon, int isHidden) {
        this.tenTV = tenTV;
        this.tenSach = tenSach;
        this.tienThue = tienThue;
        this.ngayThue = ngayThue;
        this.trangThaiMuon = trangThaiMuon;
        this.isHidden = isHidden;
    }

    public int getIsHidden() {
        return isHidden;
    }


    public void setIsHidden(int isHidden) {
        this.isHidden = isHidden;
    }

    public PhieuMuon() {
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

    public String getNgayThue() {
        return ngayThue;
    }

    public void setNgayThue(String ngayThue) {
        this.ngayThue = ngayThue;
    }

    public int getTrangThaiMuon() {
        return trangThaiMuon;
    }

    public void setTrangThaiMuon(int trangThaiMuon) {
        this.trangThaiMuon = trangThaiMuon;
    }
}
