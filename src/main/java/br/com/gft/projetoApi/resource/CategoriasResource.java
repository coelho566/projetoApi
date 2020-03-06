package br.com.gft.projetoApi.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.gft.projetoApi.model.Categoria;
import br.com.gft.projetoApi.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriasResource {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public List<Categoria> listar(){
		return categoriaRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(categoriaSalva.getId()).toUri();
		
		return ResponseEntity.created(uri).body(categoriaSalva);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Categoria> buscarPeloId(@PathVariable("id") Long id) {
		
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		
		if(categoria.isPresent()) {
			
			return ResponseEntity.ok().body(categoria.get());
		}else {
			
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
