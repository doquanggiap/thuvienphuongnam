package giapdqph34273.fpoly.pnlib.model;

public class ThuThu {
    private String id;
    private String tenTT;
    private String hoTen;
    private String matKhau;

    public ThuThu(String tenTT, String hoTen, String matKhau) {
        this.tenTT = tenTT;
        this.hoTen = hoTen;
        this.matKhau = matKhau;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenTT() {
        return tenTT;
    }

    public void setTenTT(String tenTT) {
        this.tenTT = tenTT;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
