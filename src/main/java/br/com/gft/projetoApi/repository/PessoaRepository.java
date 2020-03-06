package br.com.gft.projetoApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.projetoApi.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
