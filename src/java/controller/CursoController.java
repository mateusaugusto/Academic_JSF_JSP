/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Dao.CursoDao;
import Modelo.Curso;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author modesto
 */
@WebServlet(name = "CursoController", urlPatterns = {"/CursoController"})
public class CursoController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String action = request.getParameter("action");
            String forward = "desconhecido";
            CursoDao cursoDAO = null;
            Curso c;
            try {
                cursoDAO = new CursoDao();
            } catch (SQLException ex) {
                Logger.getLogger(CursoController.class.getName()).log(Level.SEVERE, null, ex);
            }

            switch (action) {
                case "add":
                    c = new Curso();

                    c.setNome(request.getParameter("nome"));
                    try {
                        cursoDAO.inserirCurso(c);
                        List<Curso> listaCurso = cursoDAO.listar();
                        request.getSession().setAttribute("listaCurso", listaCurso);
                        forward = "added";
                    } catch (SQLException ex) {
                        Logger.getLogger(CursoController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;

                case "edit":
                    out.println("OI");
                    break;

                case "delete":
                    try {
                        c = cursoDAO.findById(Integer.parseInt(request.getParameter("id")));
                        cursoDAO.removeCurso(c);
                                                
                        request.getSession().setAttribute("listaCurso", cursoDAO.listar());
                        
                        response.sendRedirect("Adm/cad_cursos.jsp");
                    } catch (SQLException ex) {
                        Logger.getLogger(CursoController.class.getName()).log(Level.SEVERE, null, ex);
                        out.println(ex.getMessage());
                    }

                    break;

                case "get":
                    break;

                case "list":
                    List<Curso> listaCurso = cursoDAO.listar();

                    request.getSession().setAttribute("listaCurso", listaCurso);

                    forward = "list";
                    break;
            }

            if (forward.equals("added")) {
                response.sendRedirect("Adm/cad_cursos.jsp");
                //request.getRequestDispatcher("Adm/cad_cursos.jsp").forward(request, response);

            } else if (forward.equals("list")) {
                response.sendRedirect("Adm/cad_cursos.jsp");
                //request.getRequestDispatcher("Adm/cad_cursos.jsp").forward(request, response);
            }
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
