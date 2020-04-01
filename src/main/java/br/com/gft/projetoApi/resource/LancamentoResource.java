package br.com.gft.projetoApi.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import br.com.gft.projetoApi.repository.filter.LancamentoFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.gft.projetoApi.exception.HandlerException.Erro;
import br.com.gft.projetoApi.exception.PessoaInexistenteOuInativaException;
import br.com.gft.projetoApi.model.Lancamento;
import br.com.gft.projetoApi.service.LancamentoService;

@RestController
@RequestMapping("/lancamento")
public class LancamentoResource {
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping
	public ResponseEntity<Page<Lancamento>> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable){
		
		Page<Lancamento> lacamento = lancamentoService.listar(lancamentoFilter, pageable);
		return ResponseEntity.status(HttpStatus.OK).body(lacamento);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Lancamento> buscar(@PathVariable("id") Long id){
		
		Lancamento lancamento = lancamentoService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(lancamento);
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> salvar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response){
		
		Lancamento lancamentoSalva = lancamentoService.salvar(lancamento, response);
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalva);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		lancamentoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@ExceptionHandler
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex){
		
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		
		return ResponseEntity.badRequest().body(erros);
	}
}
