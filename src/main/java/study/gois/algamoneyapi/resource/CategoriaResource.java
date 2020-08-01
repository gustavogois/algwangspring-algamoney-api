package study.gois.algamoneyapi.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import study.gois.algamoneyapi.model.Categoria;
import study.gois.algamoneyapi.repository.CategoriaRepository;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Categoria> criar(@RequestBody Categoria categoria, HttpServletResponse response) {
        Categoria categoriaSalva = categoriaRepository.save(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
                .buildAndExpand(categoriaSalva.getCodigo()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(categoriaSalva);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Categoria> buscaPeloCodigo(@PathVariable Long codigo) {

        Optional<Categoria> categoriaOpt = categoriaRepository.findById(codigo);
        if (categoriaOpt.isPresent()) {
            return ResponseEntity.ok(categoriaOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
