package net.runa.viewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.runa.viewer.entity.Categoria;

public interface CategoriaContenidoRepositorio extends JpaRepository<Categoria, Long>{

}
