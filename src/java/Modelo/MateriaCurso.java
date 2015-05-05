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
public class MateriaCurso implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private Materia materiaid;
    private Curso cursoid;

    public MateriaCurso() {
    }

    public MateriaCurso(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Materia getMateriaid() {
        return materiaid;
    }

    public void setMateriaid(Materia materiaid) {
        this.materiaid = materiaid;
    }

    public Curso getCursoid() {
        return cursoid;
    }

    public void setCursoid(Curso cursoid) {
        this.cursoid = cursoid;
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
        if (!(object instanceof MateriaCurso)) {
            return false;
        }
        MateriaCurso other = (MateriaCurso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.MateriaCurso[ id=" + id + " ]";
    }
    
}
