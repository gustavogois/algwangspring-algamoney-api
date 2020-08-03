package study.gois.algamoneyapi.resource;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.gois.algamoneyapi.model.Lancamento;
import study.gois.algamoneyapi.repository.LancamentoRepository;
import study.gois.algamoneyapi.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource extends AlgamoneyResource<Lancamento> {

    public LancamentoResource(LancamentoRepository repository, ApplicationEventPublisher publisher,
                              LancamentoService service) {
        super(repository, publisher, service);
    }
}
