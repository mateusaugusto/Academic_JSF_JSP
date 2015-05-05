/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Dao.AlunoDao;
import Dao.CursoDao;
import Modelo.Aluno;
import Modelo.Curso;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author modesto
 */
public class AlunoController extends HttpServlet {

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
        AlunoDao alunoDAO = null;
        CursoDao cursoDAO = null;
        String action = request.getParameter("action");
        String forward = "";
        try (PrintWriter out = response.getWriter()) {
            try {
                alunoDAO = new AlunoDao();
                cursoDAO = new CursoDao();
            } catch (SQLException ex) {
                Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            }

            switch (action) {

                case "add":
                    Aluno a = new Aluno();

                    a.setNome(request.getParameter("nome"));
                    a.setCpf(request.getParameter("cpf"));
                    a.setSenha(request.getParameter("senha"));
                    a.setSexo(request.getParameter("sexo"));
                    Curso c = null;

                    try {
                        c = cursoDAO.findById(Integer.parseInt(request.getParameter("curso")));
                    } catch (SQLException ex) {
                        Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    a.setCursoid(c);
                    try {
                        alunoDAO.inserirAluno(a);
                        request.getSession().setAttribute("listaAluno", alunoDAO.listar());
                        forward = "added";
                    } catch (SQLException ex) {
                        Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                    
                case "list":
                        request.getSession().setAttribute("listaAluno", alunoDAO.listar());
                        request.getSession().setAttribute("listaCurso", cursoDAO.listar());

                        forward = "list";

                    break;
            }
            
            if (forward.equals("added")) {
                response.sendRedirect("Adm/cad_alunos.jsp");
            } else if (forward.equals("list")) {
                response.sendRedirect("Adm/cad_alunos.jsp");
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
