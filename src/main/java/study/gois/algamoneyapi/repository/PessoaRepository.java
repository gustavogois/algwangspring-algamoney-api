package study.gois.algamoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.gois.algamoneyapi.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
