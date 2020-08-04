package study.gois.algamoneyapi.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.gois.algamoneyapi.event.RecursoCriadoEvent;
import study.gois.algamoneyapi.exceptionhandler.AlgamoneyExceptionHandler;
import study.gois.algamoneyapi.model.Lancamento;
import study.gois.algamoneyapi.repository.LancamentoRepository;
import study.gois.algamoneyapi.repository.filter.LancamentoFilter;
import study.gois.algamoneyapi.service.LancamentoService;
import study.gois.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource extends AlgamoneyResource<Lancamento> {

    @Autowired
    private MessageSource messageSource;

    public LancamentoResource(LancamentoRepository repository, ApplicationEventPublisher publisher,
                              LancamentoService service) {
        super(repository, publisher, service);
    }

    @GetMapping("/pesquisar")
    public List<Lancamento> pesquisar(LancamentoFilter filter) {
        return ((LancamentoRepository)repository).filtrar(filter);
    }

    @PostMapping
    public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento type, HttpServletResponse response) {
        Lancamento lancamentoSaved = ((LancamentoService)service).salvar(type);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSaved.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSaved);
    }

    @ExceptionHandler({PessoaInexistenteOuInativaException.class })
    public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
        String messageUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null,
                LocaleContextHolder.getLocale());
        String messageDesenvolvedor = ex.toString();
        List<AlgamoneyExceptionHandler.Erro> erros = Arrays.asList(new AlgamoneyExceptionHandler.Erro(messageUsuario, messageDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }
}
