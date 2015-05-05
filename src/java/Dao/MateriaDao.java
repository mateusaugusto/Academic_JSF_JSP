/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Materia;
import Modelo.MateriaCurso;
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
public class MateriaDao {

    Connection con;

    public MateriaDao() throws SQLException {
        con = ConexaoJDBC.getConexao();
    }

    public int getMaxId() throws SQLException {
        int i;
        String sql = "select max(id) from gestaoacademica.Materia";
        PreparedStatement pst = con.prepareCall(sql);
        ResultSet rs = pst.executeQuery();
        rs.next();
        i = rs.getInt("max(id)");
        return i;
    }

    public void inserirMateria(MateriaCurso mc) throws SQLException {

        String sql = "INSERT INTO gestaoacademica.Materia(Professor_id,nome) VALUES (?,?)";
        String sqlMateriaCurso = "INSERT INTO MateriaCurso(Curso_id, Materia_id) VALUES (?,?)";

        PreparedStatement pst = con.prepareCall(sql);
        PreparedStatement pst1 = con.prepareCall(sqlMateriaCurso);
                

        pst.setInt(1, mc.getMateriaid().getProfessorid().getMatricula());
        pst.setString(2, mc.getMateriaid().getNome());
        pst.execute();
        

        pst1.setInt(1, mc.getCursoid().getId());
        pst1.setInt(2, getMaxId());
        pst1.execute();
    }

    public void atualizaMateria(Materia m) throws SQLException {

        String sql = "UPDATE gestaooacademica.Materia SET Professor_id = ?, nome = ? WHERE id = " + m.getId();
        PreparedStatement pst = con.prepareCall(sql);

        pst.setInt(1, m.getProfessorid().getMatricula());
        pst.setString(2, m.getNome());

        pst.execute();
    }

    public void removeMateria(MateriaCurso mc) throws SQLException {
        String sqlMateriaCurso = "DELETE FROM gestaoacademica.MateriaCurso"
                + " WHERE Curso_id = ? and Materia_id = ? ";
        String sql = "DELETE FROM gestaoacademica.Materia WHERE id = ?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            PreparedStatement pst1 = con.prepareCall(sqlMateriaCurso);

            pst1.setInt(1, mc.getCursoid().getId());
            pst1.setInt(2, mc.getMateriaid().getId());
            pst.setInt(1, mc.getMateriaid().getId());

            pst1.execute();
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void removeMateria (Materia m) throws SQLException {
        String sqlRemoveMateria = "DELETE FROM gestaoacademica.Materia " +
                "WHERE id = ?";
        
        
        PreparedStatement pst = con.prepareStatement(sqlRemoveMateria);
        
        pst.setInt(1, m.getId());
        
        pst.execute();
    }

    public List<Materia> listar() {
        String sql = "SELECT * FROM Materia m inner join Professor p "
                + "on m.Professor_id = p.matricula";
        List<Materia> materiaList = new ArrayList<Materia>();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Materia mat = new Materia();
                Professor pro = new Professor();
                mat.setId(Integer.parseInt(rs.getString("m.id")));
                mat.setNome(rs.getString("m.nome"));
                pro.setMatricula(rs.getInt("p.matricula"));
                pro.setNome(rs.getString("p.nome"));
                pro.setSexo(rs.getString("p.sexo"));
                pro.setSenha(rs.getString("p.senha"));
                mat.setProfessorid(pro);
                materiaList.add(mat);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materiaList;
    }

    public Materia findByMatricula(int id) {
        String sql = "SELECT * FROM Materia as m inner join Professor as p "
                + "on m.Professor_id = p.matricula WHERE m.id = ?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            Materia mat = new Materia();
            Professor pro = new Professor();
            rs.next();
            mat.setId(Integer.parseInt(rs.getString("m.id")));
            mat.setNome(rs.getString("m.nome"));
            pro.setMatricula(rs.getInt("p.matricula"));
            pro.setNome(rs.getString("p.nome"));
            pro.setSexo(rs.getString("p.sexo"));
            pro.setSenha(rs.getString("p.senha"));
            mat.setProfessorid(pro);

            return mat;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

}
