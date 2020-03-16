package br.com.gft.projetoApi.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.gft.projetoApi.event.RecursoCriadoEvent;
import br.com.gft.projetoApi.model.Pessoa;
import br.com.gft.projetoApi.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	public List<Pessoa> listar() {

		List<Pessoa> pessoa = pessoaRepository.findAll();
		return pessoa;

	}

	public Pessoa salvar(Pessoa pessoa, HttpServletResponse response) {

		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getId()));

		return pessoaSalva;
	}

	public Pessoa atulizar(Long id, Pessoa pessoa) {

		Optional<Pessoa> pessoaAtulizar = pessoaRepository.findById(id);

		if (pessoaAtulizar.isPresent()) {
			BeanUtils.copyProperties(pessoa, pessoaAtulizar, "id");
			return pessoaRepository.save(pessoaAtulizar.get());
		} else {

			throw new EmptyResultDataAccessException(1);
		}

	}
	
	public Pessoa atulizarAtivo(Long id, Boolean ativo) {
		
		Pessoa pessoaSalva = this.buscar(id);
		pessoaSalva.setAtivo(ativo);
		return pessoaRepository.save(pessoaSalva);
	}
	
	public Pessoa buscar(Long id) {
		
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);

		if (pessoa.isPresent()) {
			return pessoa.get();	
		} else {
			throw new EmptyResultDataAccessException(1);
		}
	}
	
	public void delete(Long id) {
		
		try {
			pessoaRepository.deleteById(id);
		} catch (Exception e) {
			throw new EmptyResultDataAccessException(1);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
