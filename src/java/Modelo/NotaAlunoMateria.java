/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.io.Serializable;

/**
 *
 * @author matheus
 */
public class NotaAlunoMateria implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private double nota;
    private Aluno alunomatricula;
    private Materia materiaid;

    public NotaAlunoMateria() {
    }

    public NotaAlunoMateria(Integer id) {
        this.id = id;
    }

    public NotaAlunoMateria(Integer id, double nota) {
        this.id = id;
        this.nota = nota;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public Aluno getAlunomatricula() {
        return alunomatricula;
    }

    public void setAlunomatricula(Aluno alunomatricula) {
        this.alunomatricula = alunomatricula;
    }

    public Materia getMateriaid() {
        return materiaid;
    }

    public void setMateriaid(Materia materiaid) {
        this.materiaid = materiaid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotaAlunoMateria)) {
            return false;
        }
        NotaAlunoMateria other = (NotaAlunoMateria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.NotaAlunoMateria[ id=" + id + " ]";
    }
    
}
