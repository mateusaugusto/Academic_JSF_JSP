/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.util.List;

/**
 *
 * @author matheus
 */
public class Aluno {
    private Integer matricula;
    private String nome;
    private String cpf;
    private String sexo;
    private String senha;
    private Curso cursoid;

    public Aluno() {
    }

    public Aluno(Integer matricula) {
        this.matricula = matricula;
    }

    public Aluno(Integer matricula, String nome, String cpf, String sexo, String senha) {
        this.matricula = matricula;
        this.nome = nome;
        this.cpf = cpf;
        this.sexo = sexo;
        this.senha = senha;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Curso getCursoid() {
        return cursoid;
    }

    public void setCursoid(Curso cursoid) {
        this.cursoid = cursoid;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aluno)) {
            return false;
        }
        Aluno other = (Aluno) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Aluno[ matricula=" + matricula + " ]";
    }
    
}
