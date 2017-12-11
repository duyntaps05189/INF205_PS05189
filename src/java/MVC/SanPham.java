package MVC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DELL
 */
public class SanPham {

    private String TenSP;
    private String ChuThich;
    private String HinhAnh;
    private Double Gia;
    private String Dec;

    public SanPham() {
    }

    public SanPham(String TenSP, String ChuThich, String HinhAnh, Double Gia, String Dec) {
        this.TenSP = TenSP;
        this.ChuThich = ChuThich;
        this.HinhAnh = HinhAnh;
        this.Gia = Gia;
        this.Dec = Dec;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getChuThich() {
        return ChuThich;
    }

    public void setChuThich(String ChuThich) {
        this.ChuThich = ChuThich;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

    public Double getGia() {
        return Gia;
    }

    public void setGia(Double Gia) {
        this.Gia = Gia;
    }

    public String getDec() {
        return Dec;
    }

    public void setDec(String Dec) {
        this.Dec = Dec;
    }

    public ArrayList<SanPham> showProduct() {
        List<SanPham> sanpham = null;
        try {
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLLT_JV4_PS05189;user=sa;password=123";
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectionUrl);

            String sql = "Select * from SanPham";
            Statement stm = con.createStatement();

            ResultSet rs = stm.executeQuery(sql);
            sanpham = new ArrayList<SanPham>();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setTenSP(rs.getString("TenSP"));
                sp.setChuThich(rs.getString("ChuThich"));
                sp.setHinhAnh(rs.getString("HinhAnh"));
                sp.setGia(rs.getDouble("GiaSP"));   
                sp.setDec(rs.getString("Decrip"));

                sanpham.add(sp);
            }

            rs.close();
            stm.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ArrayList<SanPham>) sanpham;
    }

    public SanPham showdata(String Id) {
        SanPham sp = null;
        try {
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLLT_JV4_PS05189;user=sa;password=123";
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectionUrl);

            String sql = "Select * from SanPham where TenSP=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, Id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                sp = new SanPham();
                sp.setTenSP(rs.getString("TenSP"));
                sp.setChuThich(rs.getString("ChuThich"));
                sp.setHinhAnh(rs.getString("HinhAnh"));
                sp.setGia(rs.getDouble("GiaSP"));   
                sp.setDec(rs.getString("Decrip"));
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sp;
    }

    public boolean insertsp(String tensp, String chuthich, String hinhanh, Double gia, String dec) {
        try {
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLLT_JV4_PS05189;user=sa;password=123";
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectionUrl);

            String sql = "Insert into SanPham(TenSP,ChuThich,HinhAnh,GiaSP,Decrip)" + "Values(?,?,?,?,?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, tensp);
            stm.setString(2, chuthich);
            stm.setString(3, hinhanh);
            stm.setDouble(4, gia);
            stm.setString(5, dec);

            int result = stm.executeUpdate();

            stm.close();
            con.close();

            if (result > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateProduct() {
        try {
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLLT_JV4_PS05189;user=sa;password=123";
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectionUrl);

            String sql = "Update SanPham Set TenSP = ?, ChuThich = ?, HinhAnh = ?, GiaSP=?, Decrip=? Where Product_ID = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, TenSP);
            stm.setString(2, ChuThich);
            stm.setString(3, HinhAnh);
            stm.setDouble(4, Gia);
            stm.setString(5, Dec);

            int result = stm.executeUpdate();
            stm.close();
            con.close();
            if (result > 0) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteProduct() {
        try {
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLLT_JV4_PS05189;user=sa;password=123";
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectionUrl);

            String sql = "Delete from SanPham Where TenSP=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, TenSP);
            int result = stm.executeUpdate();

            stm.close();
            con.close();

            if (result > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
