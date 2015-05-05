/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Curso;
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
public class CursoDao {

    Connection con;

    public CursoDao() throws SQLException {
        con = ConexaoJDBC.getConexao();
    }

    public void inserirCurso(Curso c) throws SQLException {

        String sql = "INSERT INTO gestaoacademica.Curso(nome) VALUES (?)";
        PreparedStatement pst = con.prepareCall(sql);
        pst.setString(1, c.getNome());
        pst.execute();
    }

    public void atualizaCurso(Curso c) throws SQLException {

        String sql = "UPDATE gestaoacademica.Curso SET nome = ? "
                + "WHERE id = " + c.getId();
        PreparedStatement pst = con.prepareCall(sql);
        pst.setInt(1, c.getId());
        pst.setString(2, c.getNome());
        pst.execute();
    }

    public void removeCurso(Curso c) throws SQLException {
        String sql = "DELETE FROM gestaoacademica.Curso WHERE id = ?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, c.getId());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Curso> listar() {
        String sql = "SELECT * FROM Curso";
        List<Curso> cursoList = new ArrayList<Curso>();
        Curso curso;
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                curso = new Curso();
                curso.setId(rs.getInt("id"));
                curso.setNome(rs.getString("nome"));
                cursoList.add(curso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cursoList;
    }

    public Curso findById(int id) throws SQLException {
        String sql = "select * from Curso where id = ?";
        Curso c = new Curso();
        PreparedStatement pst = con.prepareCall(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        rs.next();
        c.setId(rs.getInt("id"));
        c.setNome(rs.getString("nome"));
        return c;

    }
}
