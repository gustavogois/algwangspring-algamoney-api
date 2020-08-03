package study.gois.algamoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.gois.algamoneyapi.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
}
