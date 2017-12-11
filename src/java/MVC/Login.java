package MVC;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DUYGAU
 */
public class Login implements Serializable {

    private String Email;
    private String Pass;

    public Login() {
    }

    public Login(String Email, String Pass) {
        this.Email = Email;
        this.Pass = Pass;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    public boolean checkLogin(String Email, String Pass) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=QLLT_JV4_PS05189;instanceName=SQL2012";
            Connection con = DriverManager.getConnection(url, "sa", "123456");
            String sql = "Select * From Users Where Email = ? and Password = ? ";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, Email);
            stm.setString(2, Pass);
            ResultSet rs = stm.executeQuery();
            boolean result = rs.next();
            rs.close();
            stm.close();
            con.close();
            if (result) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
