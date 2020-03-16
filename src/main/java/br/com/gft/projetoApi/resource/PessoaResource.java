package br.com.gft.projetoApi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gft.projetoApi.model.Pessoa;
import br.com.gft.projetoApi.repository.PessoaRepository;
import br.com.gft.projetoApi.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> listar() {
		List<Pessoa> pessoa = pessoaService.listar();
		return ResponseEntity.status(HttpStatus.OK).body(pessoa);
	}

	@PostMapping
	public ResponseEntity<Pessoa> salvar(@RequestBody Pessoa pessoa, HttpServletResponse response) {
		
		Pessoa pessoaSalva = pessoaService.salvar(pessoa, response);
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> atualizar(@Valid @RequestBody Pessoa pessoa, @PathVariable("id") Long id) {

		Pessoa pessoaAtulizar = pessoaService.atulizar(id, pessoa);
		return ResponseEntity.status(HttpStatus.OK).body(pessoaAtulizar);

	}
	
	@PutMapping("/{id}/ativo")
	public ResponseEntity<Pessoa> atualizarAtivo(@PathVariable Long id, @RequestBody Boolean ativo) {
		
		Pessoa pessoa = pessoaService.atulizarAtivo(id, ativo);
		return ResponseEntity.status(HttpStatus.OK).body(pessoa);
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> buscar(@PathVariable("id") Long id) {
		
		Pessoa pessoa = pessoaService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(pessoa);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		
		pessoaRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}

}
