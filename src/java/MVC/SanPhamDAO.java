package MVC;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DELL
 */
public class SanPhamDAO {

//    Danh sách sản phẩm
    public ArrayList<SanPham> getListSanPham() throws SQLException {
        Connection con = Connect.getConnection();
        String sql = "Select * From SanPham";
        PreparedStatement ps = con.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<SanPham> list = new ArrayList<>();
        while (rs.next()) {
            SanPham sp = new SanPham();
            sp.setTenSP(rs.getString("TenSP"));
            sp.setChuThich(rs.getString("ChuThich"));
            sp.setHinhAnh(rs.getString("HinhAnh"));
            sp.setGia(rs.getDouble("GiaSP"));
            sp.setDec(rs.getString("Decrip"));
            list.add(sp);
        }
        return list;
    }
    
//    Chi tiết sản phẩm
    public SanPham getSanPham(String TenSP) throws SQLException{
        Connection con = Connect.getConnection();
        String sql = "Select * from SanPham Where TenSP = '" + TenSP +"'";
        PreparedStatement ps = con.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        SanPham sp = new SanPham();
        while(rs.next()){
            sp.setTenSP(rs.getString("TenSP"));
            sp.setChuThich(rs.getString("ChuThich"));
            sp.setHinhAnh(rs.getString("HinhAnh"));
            sp.setGia(rs.getDouble("GiaSP")); 
            sp.setDec(rs.getString("Decrip"));
        }
        return sp;
    }
    
    public static void main(String[] args) throws SQLException {
        SanPhamDAO spDAO = new SanPhamDAO();
        spDAO.getListSanPham();
    }
}
