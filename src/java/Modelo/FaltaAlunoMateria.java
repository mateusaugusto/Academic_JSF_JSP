/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;


/**
 *
 * @author matheus
 */
public class FaltaAlunoMateria {
    private Integer id;
    private String data;
    private Aluno alunomatricula;
    private Materia materiaid;

    public FaltaAlunoMateria() {
    }

    public FaltaAlunoMateria(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
        if (!(object instanceof FaltaAlunoMateria)) {
            return false;
        }
        FaltaAlunoMateria other = (FaltaAlunoMateria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.FaltaAlunoMateria[ id=" + id + " ]";
    }
    
}
