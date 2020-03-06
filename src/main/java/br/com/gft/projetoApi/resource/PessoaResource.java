package br.com.gft.projetoApi.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.gft.projetoApi.model.Pessoa;
import br.com.gft.projetoApi.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {

	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public ResponseEntity<List<Pessoa>> listar() {
		List<Pessoa> pessoa = pessoaRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(pessoa);
	}

	@PostMapping
	public ResponseEntity<Pessoa> salvar(@RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(pessoaSalva.getId())
				.toUri();

		return ResponseEntity.created(uri).body(pessoaSalva);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody Pessoa pessoa, @PathVariable("id") Long id) {

		Pessoa pessoaAtulizar = pessoaRepository.findById(id).get();

		pessoaAtulizar.setNome(pessoa.getNome());

		pessoaRepository.save(pessoaAtulizar);

		return ResponseEntity.noContent().build();

	}

	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> buscar(@PathVariable("id") Long id) {

		Optional<Pessoa> pessoa = pessoaRepository.findById(id);

		if (pessoa.isPresent()) {

			return ResponseEntity.ok().body(pessoa.get());
			
		} else {
			
			return ResponseEntity.noContent().build();
			
		}
	}

}
