/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Aluno;
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
public class AlunoDao {

    Connection con;

    public AlunoDao() throws SQLException {
        con = ConexaoJDBC.getConexao();
    }

    public void inserirAluno(Aluno a) throws SQLException {

        String sql = "INSERT INTO gestaoacademica.aluno(nome,cpf,sexo,senha,Curso_id) VALUES (?,?,?,?,?)";
        PreparedStatement pst = con.prepareCall(sql);
        pst.setString(1, a.getNome());
        pst.setString(2, a.getCpf());
        pst.setString(3, a.getSexo());
        pst.setString(4, a.getSenha());
        pst.setInt(5, a.getCursoid().getId());
        pst.execute();
    }

    public void atualizaAluno(Aluno a) throws SQLException {

        String sql = "UPDATE cc_te_gestao_academica.aluno SET nome = ?, cpf = ? "
                + "sexo = ?, senha = ?, Curso_id = ? WHERE matricula = " + a.getMatricula();
        PreparedStatement pst = con.prepareCall(sql);

        pst.setString(1, a.getNome());
        pst.setString(2, a.getCpf());
        pst.setString(3, a.getSexo());
        pst.setString(4, a.getSenha());
        pst.setInt(5, a.getCursoid().getId());
        pst.execute();
    }

    public void removeAluno(Aluno a) throws SQLException {
        String sql = "DELETE FROM gestaoacademica.Aluno WHERE matricula = ?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, a.getMatricula());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Aluno> listar() {
        String sql = "SELECT * FROM Aluno AS a INNER JOIN Curso AS c ON a.Curso_id = c.id ";
        List<Aluno> alunoList = new ArrayList<Aluno>();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            Aluno alu = new Aluno();
            Curso curso = new Curso();
            while (rs.next()) {
                alu.setMatricula(Integer.parseInt(rs.getString("a.matricula")));
                alu.setCpf(rs.getString("a.cpf"));
                alu.setSexo(rs.getString("a.sexo"));
                alu.setSenha(rs.getString("a.senha"));
                alu.setNome(rs.getString("a.nome"));
                curso.setId(rs.getInt("c.id"));
                curso.setNome(rs.getString("c.nome"));
                alu.setCursoid(curso);

                alunoList.add(alu);
                alu = new Aluno();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alunoList;
    }

    public Aluno findByMatricula(int matricula) {
        String sql = "SELECT * FROM Aluno a INNER JOIN Curso c ON a.Curso_id = c.id "
                + "WHERE a.matricula = ?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, matricula);
            ResultSet rs = pst.executeQuery();
            Aluno alu = new Aluno();
            Curso curso = new Curso();
            rs.next();
            alu.setMatricula(Integer.parseInt(rs.getString("a.matricula")));
            alu.setCpf(rs.getString("a.cpf"));
            alu.setSexo(rs.getString("a.sexo"));
            alu.setSenha(rs.getString("a.senha"));
            alu.setNome(rs.getString("a.nome"));

            curso.setId(rs.getInt("c.id"));
            curso.setNome(rs.getString("c.nome"));
            alu.setCursoid(curso);

            return alu;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

}
