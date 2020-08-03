package study.gois.algamoneyapi.resource;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.gois.algamoneyapi.model.Categoria;
import study.gois.algamoneyapi.repository.CategoriaRepository;
import study.gois.algamoneyapi.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource extends AlgamoneyResource<Categoria> {

    public CategoriaResource(CategoriaRepository repository, ApplicationEventPublisher publisher,
                             CategoriaService service) {
        super(repository, publisher, service);
    }

}
