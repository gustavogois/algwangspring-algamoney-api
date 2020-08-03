package study.gois.algamoneyapi.resource;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import study.gois.algamoneyapi.model.Pessoa;
import study.gois.algamoneyapi.repository.PessoaRepository;
import study.gois.algamoneyapi.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource extends AlgamoneyResource<Pessoa> {

    private PessoaService pessoaService;

    public PessoaResource(PessoaRepository pessoaRepository, ApplicationEventPublisher publisher, PessoaService pessoaService) {
        super(pessoaRepository, publisher, pessoaService);
        this.pessoaService = pessoaService;
    }

    @PutMapping("/{codigo}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) throws Throwable {
        pessoaService.atualizarPropriedadeAtivo(codigo, ativo);
    }
}
