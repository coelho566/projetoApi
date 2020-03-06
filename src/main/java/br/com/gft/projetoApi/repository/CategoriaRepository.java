package br.com.gft.projetoApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gft.projetoApi.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
