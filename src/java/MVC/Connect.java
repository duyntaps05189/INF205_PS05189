package MVC;


import java.sql.Connection;
import java.sql.DriverManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DELL
 */
public class Connect {
    public static Connection getConnection(){
        Connection con = null;
        try{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=QLLT_JV4_PS05189";
        con = DriverManager.getConnection(url, "sa", "123456");
        if(con !=null){
            System.out.println("thanh cong");
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }
    public static void main(String[] args) {
        System.out.println(getConnection());
    }
}
