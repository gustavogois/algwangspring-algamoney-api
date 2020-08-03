package study.gois.algamoneyapi.service;

import org.springframework.stereotype.Service;
import study.gois.algamoneyapi.model.Categoria;
import study.gois.algamoneyapi.repository.CategoriaRepository;

@Service
public class CategoriaService extends AlgamoneyService<Categoria> {

    public CategoriaService(CategoriaRepository repository) {
        super(repository);
    }
}
