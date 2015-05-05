/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Professor;

import Util.ConexaoJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matheus
 */
public class ProfessorDao {

    Connection con;

    public ProfessorDao() throws SQLException {
        con = ConexaoJDBC.getConexao();
    }

    public void inserirProfessor(Professor p) throws SQLException {

        String sql = "INSERT INTO gestaoacademica.Professor(nome,sexo,senha) VALUES (?,?,?)";
        PreparedStatement pst = con.prepareCall(sql);
        pst.setString(1, p.getNome());
        pst.setString(2, p.getSexo());
        pst.setString(3, p.getSenha());
        pst.execute();
    }

    public void atualizaProfessor(Professor p) throws SQLException {

        String sql = "UPDATE gestaoacademica.Professor SET nome = ?, sexo = ?, senha = ? "
                + "WHERE matricula = " + p.getMatricula();
        PreparedStatement pst = con.prepareCall(sql);

        pst.setString(1, p.getNome());
        pst.setString(2, p.getSexo());
        pst.setString(3, p.getSenha());
        pst.execute();
    }

    public void removeProfessor(Professor p) throws SQLException {
        String sql = "DELETE FROM gestaoacademica.Professor WHERE matricula = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, p.getMatricula());
        pst.execute();

    }

    public List<Professor> listar() {
        String sql = "SELECT * FROM Professor";
        List<Professor> professorList = new ArrayList<Professor>();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            Professor pro = new Professor();
            while (rs.next()) {
                pro.setMatricula(rs.getInt("matricula"));
                pro.setNome(rs.getString("nome"));
                pro.setSexo(rs.getString("sexo"));
                pro.setSenha(rs.getString("senha"));
                professorList.add(pro);
                pro = new Professor();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return professorList;
    }

    public Professor findByMatricula(int matricula) {
        String sql = "SELECT * FROM Professor "
                + "where matricula = ?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, matricula);
            ResultSet rs = pst.executeQuery();
            Professor pro = new Professor();
            rs.next();
            pro.setMatricula(rs.getInt("matricula"));
            pro.setNome(rs.getString("nome"));
            pro.setSexo(rs.getString("sexo"));
            pro.setSenha(rs.getString("senha"));
            return pro;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

}
