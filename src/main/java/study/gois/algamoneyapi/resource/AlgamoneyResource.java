package study.gois.algamoneyapi.resource;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import study.gois.algamoneyapi.event.RecursoCriadoEvent;
import study.gois.algamoneyapi.model.AlgamoneyEntity;
import study.gois.algamoneyapi.service.AlgamoneyService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public class AlgamoneyResource<T extends AlgamoneyEntity> {

    final ApplicationEventPublisher publisher;
    final JpaRepository repository;
    protected AlgamoneyService service;

    public AlgamoneyResource(JpaRepository repository, ApplicationEventPublisher publisher, AlgamoneyService service) {
        this.repository = repository;
        this.publisher = publisher;
        this.service = service;
    }

    @GetMapping
    public List<T> listar() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<T> criar(@Valid @RequestBody T type, HttpServletResponse response) {
        T entitySaved = (T) repository.save(type);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, entitySaved.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(entitySaved);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<T> buscarPeloCodigo(@PathVariable Long codigo) {

        Optional<T> categoriaOpt = repository.findById(codigo);
        return categoriaOpt.isPresent() ? ResponseEntity.ok(categoriaOpt.get()) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        repository.deleteById(codigo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> atualizar(@PathVariable Long codigo, @Valid @RequestBody T body) throws Throwable {
        return ResponseEntity.ok(service.atualizar(codigo, body));
    }
}
