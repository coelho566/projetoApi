package br.com.gft.projetoApi.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<List<Lancamento>> listar(){
		
		List<Lancamento> lacamento = lancamentoService.listar();
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
	
	@ExceptionHandler
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex){
		
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		
		return ResponseEntity.badRequest().body(erros);
	}
}
