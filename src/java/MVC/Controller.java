package MVC;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DUYGAU
 */
@WebServlet(urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    final private String loginPage = "login.jsp";
    final private String homePage = "index.jsp";
    final private String failedPage = "fail.jsp";
    final private String adminPage = "admin.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String action = request.getParameter("Submit");
            if (action.equals("Login")) {
                String Email = request.getParameter("email");
                String Pass = request.getParameter("password");
                Login lg = new Login();
                boolean result = lg.checkLogin(Email, Pass);
                String url = failedPage;

                if (result) {
                    HttpSession ss = request.getSession(true);
                    ss.setAttribute("EMAIL", Email);
                    url = adminPage;
                }
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }if (action.equals("Create new Product")) {
                String ten = request.getParameter("txtTenSP");
                String chuthich = request.getParameter("txtChuThich");
                String hinhanh = request.getParameter("txtHinhAnh");
                String gia = request.getParameter("txtGiaSP");
                String dec = request.getParameter("txtDecrip");
                SanPham updatesp = new SanPham();
                boolean result = updatesp.insertsp(ten,chuthich,hinhanh,Double.parseDouble(gia),dec);
                RequestDispatcher rd = request.getRequestDispatcher(adminPage);
                rd.forward(request, response);
            } else if (action.equals("Update")) {
                String Id = request.getParameter("value");
                SanPham sp = new SanPham();
                SanPham ds = sp.showdata(Id);
                request.setAttribute("TenSP", ds.getTenSP());
                request.setAttribute("ChuThich", ds.getChuThich());
                request.setAttribute("HinhAnh", ds.getHinhAnh());
                request.setAttribute("GiaSP", ds.getGia());
                request.setAttribute("Decrip", ds.getDec());
                request.getRequestDispatcher("update.jsp").forward(request, response);
            } else if (action.equals("UpdateProduct")) {
                String ten = request.getParameter("txtTenSP");
                String chuthich = request.getParameter("txtChuThich");
                String hinhanh = request.getParameter("txtHinhAnh");
                String gia = request.getParameter("txtGiaSP");
                String dec = request.getParameter("txtDecrip");
                SanPham updateSp = new SanPham(ten,chuthich,hinhanh,Double.parseDouble(gia),dec);
                boolean result = updateSp.updateProduct();
                request.getRequestDispatcher(adminPage).forward(request, response);
            }else if(action.equals("Delete")){
                String tensp = request.getParameter("value");
                SanPham sp = new SanPham();
                sp.setTenSP(tensp);
                boolean result = sp.deleteProduct();
                request.getRequestDispatcher(adminPage).forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
