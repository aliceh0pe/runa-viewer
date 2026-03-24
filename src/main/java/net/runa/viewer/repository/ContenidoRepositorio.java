package net.runa.viewer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.runa.viewer.entity.Contenido;

@Repository
public interface ContenidoRepositorio extends JpaRepository<Contenido, Long> {
	
	Contenido findByTitulo(String titulo);
	
	@Query("SELECT c FROM Contenido c JOIN c.idTipo t WHERE t.id = :idTipo ORDER BY c.titulo ASC")
	List<Contenido> findByTipo(@Param("idTipo")Long idTipo);

    // Para las categorías, si en Contenido tienes un objeto 'Categoria'
	@Query("SELECT c FROM Contenido c JOIN c.categorias cat WHERE cat.nombreCategoria = :nombre ORDER BY c.titulo ASC")
    List<Contenido> findByCategoria(@Param("nombre")String nombre);
	
	// Obtiene todos los contenidos con posterPath null
	@Query("SELECT c FROM Contenido c WHERE c.posterPath IS NULL")
	List<Contenido> findByPosterPathIsNull();
}
