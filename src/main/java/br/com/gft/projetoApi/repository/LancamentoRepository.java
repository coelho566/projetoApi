package br.com.gft.projetoApi.repository;

import br.com.gft.projetoApi.repository.lancamento.LancamentoRepositoryQuey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gft.projetoApi.model.Lancamento;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuey {

}
