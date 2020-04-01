package br.com.gft.projetoApi.repository.lancamento;

import br.com.gft.projetoApi.model.Lancamento;
import br.com.gft.projetoApi.repository.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LancamentoRepositoryQuey {
    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
}
