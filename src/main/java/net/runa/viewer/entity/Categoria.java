package net.runa.viewer.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="categoria")
public class Categoria {
	
	public Categoria() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String categoria) {
		this.nombreCategoria = categoria;
	}

	public List<Contenido> getContenidos() {
		return contenidos;
	}

	public void setContenidos(List<Contenido> contenidos) {
		this.contenidos = contenidos;
	}

	@Override
	public String toString() {
		return nombreCategoria;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_categoria")
	private Long id;
	
	@Column(name="nombre_categoria")
	private String nombreCategoria;
	
	@ManyToMany(mappedBy = "categorias") 
    private List<Contenido> contenidos;
	
}
