package br.com.gft.projetoApi.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.gft.projetoApi.event.RecursoCriadoEvent;
import br.com.gft.projetoApi.exception.PessoaInexistenteOuInativaException;
import br.com.gft.projetoApi.model.Lancamento;
import br.com.gft.projetoApi.model.Pessoa;
import br.com.gft.projetoApi.repository.LancamentoRepository;
import br.com.gft.projetoApi.repository.PessoaRepository;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	public List<Lancamento> listar(){
		
		List<Lancamento> lancamento = repository.findAll();
		return lancamento;
		
	}
	
	public Lancamento buscar(Long id) {
		
		Optional<Lancamento> lancamento = repository.findById(id);
		
		if(lancamento.isPresent()) {
			return lancamento.get();
		}else {
			throw new EmptyResultDataAccessException(1);
		}
		
	}
	
	public Lancamento salvar(Lancamento lancamento, HttpServletResponse response) {
		
		Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getId()).get();

		if(pessoa == null || pessoa.isInativo()) {
			
			throw new PessoaInexistenteOuInativaException();
		}
		
		Lancamento lacamento = repository.save(lancamento);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lacamento.getId()));
		return lacamento;
	}
	
}
