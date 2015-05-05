/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.MateriaCurso;
import Util.ConexaoJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author matheus
 */
public class MateriaCursoDao {

    Connection con;

    public MateriaCursoDao() throws SQLException {
        con = ConexaoJDBC.getConexao();
    }

    public void associaMateriaCurso(MateriaCurso materiaCurso) throws SQLException {
        String sql = "INSERT INTO MateriaCurso(Curso_id, Materia_id) VALUES (?,?)";
        PreparedStatement pst = con.prepareCall(sql);
        pst.setInt(1, materiaCurso.getCursoid().getId());
        pst.setInt(2, materiaCurso.getMateriaid().getId());
        pst.execute();
    }
}
