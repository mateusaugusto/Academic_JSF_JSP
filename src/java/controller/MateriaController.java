/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Dao.CursoDao;
import Dao.MateriaDao;
import Dao.ProfessorDao;
import Modelo.Curso;
import Modelo.Materia;
import Modelo.MateriaCurso;
import Modelo.Professor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author modesto
 */
@WebServlet(name = "MateriaController", urlPatterns = {"/MateriaController"})
public class MateriaController extends HttpServlet {

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

        MateriaDao materiaDAO = null;
        ProfessorDao professorDAO = null;
        CursoDao cursoDAO = null;

        try {
            materiaDAO = new MateriaDao();
            professorDAO = new ProfessorDao();
            cursoDAO = new CursoDao();
        } catch (SQLException ex) {
            Logger.getLogger(MateriaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String action = request.getParameter("action");

        if (action != null && action.equals("list")) {
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession();

            session.setAttribute("listaMateria", materiaDAO.listar());
            session.setAttribute("listaProfessor", professorDAO.listar());
            session.setAttribute("listaCurso", cursoDAO.listar());

            response.sendRedirect("Adm/cad_materias.jsp");

        } else if (action != null && action.equalsIgnoreCase("add")) {
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession();
            MateriaCurso mc = new MateriaCurso();
            Curso c = null;

            Materia m = new Materia();

            try {
                c = cursoDAO.findById(Integer.parseInt(request.getParameter("curso")));
            } catch (SQLException ex) {
                Logger.getLogger(MateriaController.class.getName()).log(Level.SEVERE, null, ex);
            }

            m.setNome(request.getParameter("nome"));
            m.setProfessorid(professorDAO.findByMatricula(Integer.parseInt(request.getParameter("professor"))));

            mc.setMateriaid(m);
            mc.setCursoid(c);

            try {
                materiaDAO.inserirMateria(mc);
            } catch (SQLException ex) {
                Logger.getLogger(MateriaController.class.getName()).log(Level.SEVERE, null, ex);
            }

            session.setAttribute("listaMateria", materiaDAO.listar());

            response.sendRedirect("Adm/cad_materias.jsp");
        } else if (action != null && action.equalsIgnoreCase("delete")) {
            Materia m = materiaDAO.findByMatricula(Integer.parseInt(request.getParameter("id")));

            try {
                materiaDAO.removeMateria(m);
                request.getSession().setAttribute("listaMateria", materiaDAO.listar());
                response.sendRedirect("Adm/cad_materias.jsp");
            } catch (SQLException ex) {
                Logger.getLogger(MateriaController.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect("erros/materias_foreign_key.html");
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
