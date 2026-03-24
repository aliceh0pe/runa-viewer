package net.runa.viewer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="episodio")
public class Episodio {
	
	public Episodio() {
		
	}
	
	public Episodio(Integer temporada, Integer numeroEpisodio) {
		super();
		this.temporada = temporada;
		this.numeroEpisodio = numeroEpisodio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTemporada() {
		return temporada;
	}

	public void setTemporada(Integer temporada) {
		this.temporada = temporada;
	}

	public Integer getNumero_episodio() {
		return numeroEpisodio;
	}

	public void setNumero_episodio(Integer numero_episodio) {
		this.numeroEpisodio = numero_episodio;
	}

	@Override
	public String toString() {
		return "Episodio [temporada=" + temporada + ", numeroEpisodio=" + numeroEpisodio + "]";
	}

	@Column(name="id_episodio")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_contenido")
	private Contenido idContenido;
	
	@Column(name="temporada")
	private Integer temporada;
	
	@Column(name="numero_episodio")
	private Integer numeroEpisodio;

}
