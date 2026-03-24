package net.runa.viewer.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="contenido")
public class Contenido {
	
	public Contenido() {
		
	}
	
	public Contenido( TipoContenido idTipo, String titulo, String sinopsis, Integer anio,
			 String rutaArchivo, String formato, Integer duracion,	String fechaRegistro,
			 Integer tmdbId) {
		super();
		this.idTipo = idTipo;
		this.titulo = titulo;
		this.sinopsis = sinopsis;
		this.anio = anio;
		this.rutaArchivo = rutaArchivo;
		this.formato = formato;
		this.duracion = duracion;
		this.fechaRegistro = fechaRegistro;
		this.tmdbId = tmdbId;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public String getRutaArchivo() {
		return rutaArchivo;
	}

	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	public TipoContenido getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(TipoContenido idTipo) {
		this.idTipo = idTipo;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public Integer getTmdbId() {
		return tmdbId;
	}


	public void setTmdbId(Integer tmdbId) {
		this.tmdbId = tmdbId;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	@Override
	public String toString() {
		return "Contenido [idTipo=" + idTipo + ", titulo=" + titulo + ", sinopsis=" + sinopsis
				+ ", anio=" + anio + ", rutaArchivo=" + rutaArchivo + ", formato=" + formato
				+ ", duracion=" + duracion + ", fechaRegistro=" + fechaRegistro + ", tmdbId=" + tmdbId
				+ ", posterPath?=" + posterPath + "]\n";
	}

	@Valid
	@NotNull(message="Campo requerido")
	@ManyToOne
	@JoinColumn(name="id_tipo")
	private TipoContenido idTipo;
	
	// EXPLICACION MAS AMPLIA DE LAS ANOTACIONES !!!!!
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "contenido_categoria", joinColumns = @JoinColumn(name = "id_contenido"), 
	inverseJoinColumns = @JoinColumn(name = "id_categoria"))
	private List<Categoria> categorias;

	@Column(name="id_contenido")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message="Campo necesario")
	@Column(name="titulo")
	private String titulo;
	
	@NotBlank(message="Campo necesario")
	@Lob
	@Column(name="sinopsis")
	private String sinopsis;
	
	@Min(value=1900, message="Año minimo 1900")
	@Max(value=2030, message="Año maximo 2030")
	@NotNull(message="Campo necesario")
	@Column(name="anio_lanzamiento")
	private Integer anio;
	
	@NotBlank(message="Campo requerido")
	@Column(name="ruta_archivo")
	private String rutaArchivo;
	
	@NotBlank(message="Campo requerido")
	@Column(name="formato")
	private String formato;
	
	@Min(value=9, message="Tamaño minimo 9")
	@Max(value=300, message="Tamaño maximo 300")
	@NotNull(message="Campo necesario")
	@Column(name="duracion_minutos")
	private Integer duracion;
	
	@Column(name="fecha_registro")
	private String fechaRegistro;
	
	@NotNull(message="Campo requerido")
	@Column(name="tmdb_id")
	private Integer tmdbId;
	
	@Column(name="poster_path")
	private String posterPath;
	
}
