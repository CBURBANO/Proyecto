/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CAROLINA
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

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
        try {
            String stMensaje = "";

            if (request.getParameter("txtEmail").equals("")) {
                stMensaje += "Ingrese  email,";
            }
            if (request.getParameter("txtPassword").equals("")) {
                stMensaje += "Ingrese  password,";
            }

            if (!stMensaje.equals("")) {
                
                throw new Exception(stMensaje.substring(0, stMensaje.length() - 1));
            }
           //intanciando 1 
           Models.clsLogin obclsLogin = new Models.clsLogin();
           //asignacion de propiedades
           obclsLogin.setStEmail(request.getParameter("txtEmail").toString());
           obclsLogin.setStPassword(request.getParameter("txtPassword").toString());
           
           //instanciando controlador
           BL.clsLogin obBLclsLogin  = new BL.clsLogin();
           
           //Invocacion--ValidaLogin
           
           boolean blBandera = obBLclsLogin.validarLogin(obclsLogin );
           
           if(blBandera)
               
               //Direccionamiento al jsp
               request.getRequestDispatcher("Index.jsp").forward(request, response);
           else 
               throw new Exception ("Email o Password Incorrecto");
           
           
           //Direccionamiento al servlet
        } catch (Exception ex) {
                // envio parametro o los valores
            request.setAttribute("stError", ex.getMessage());
            request.getRequestDispatcher("Login.jsp").forward(request,response);
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
