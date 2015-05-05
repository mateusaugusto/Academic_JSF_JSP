/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Dao.ProfessorDao;
import Modelo.Professor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author modesto
 */
@WebServlet(name = "ProfessorController", urlPatterns = {"/ProfessorController"})
public class ProfessorController extends HttpServlet {

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

        String action = request.getParameter("action");
        ProfessorDao professorDAO = null;
        Professor prof;
        String forward = "";

        try {
            professorDAO = new ProfessorDao();
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
        }

        switch (action) {
            case "add":
                prof = new Professor();
                prof.setNome(request.getParameter("nome"));
                //prof.setMatricula(Integer.parseInt(request.getParameter("matricula")));
                prof.setSenha(request.getParameter("senha"));
                prof.setSexo(request.getParameter("sexo"));
                try {
                    professorDAO.inserirProfessor(prof);
                    request.getSession().setAttribute("listaProfessor", professorDAO.listar());
                    forward = "added";
                } catch (SQLException ex) {
                    Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case "list":
                request.getSession().setAttribute("listaProfessor", professorDAO.listar());

                forward = "list";

                break;

            case "delete":
                prof = professorDAO.findByMatricula(Integer.parseInt(request.getParameter("id")));
                
                try {
                    professorDAO.removeProfessor(prof);
                    request.getSession().setAttribute("listaProfessor", professorDAO.listar());
                    forward = "deleted";
                } catch (SQLException ex) {
                    Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
                    response.sendRedirect("erros/professor_foreign_key.html");
                }

                break;
        }

        if (forward.equals("added")) {
            response.sendRedirect("Adm/cad_professores.jsp");
        } else if (forward.equals("list")) {
            response.sendRedirect("Adm/cad_professores.jsp");
        } else if (forward.equals("deleted")) {
            response.sendRedirect("Adm/cad_professores.jsp");
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
