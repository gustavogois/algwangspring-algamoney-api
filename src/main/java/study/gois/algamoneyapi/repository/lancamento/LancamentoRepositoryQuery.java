package study.gois.algamoneyapi.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.gois.algamoneyapi.model.Lancamento;
import study.gois.algamoneyapi.repository.filter.LancamentoFilter;


public interface LancamentoRepositoryQuery {
    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
}
