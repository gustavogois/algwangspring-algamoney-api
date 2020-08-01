package study.gois.algamoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.gois.algamoneyapi.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
