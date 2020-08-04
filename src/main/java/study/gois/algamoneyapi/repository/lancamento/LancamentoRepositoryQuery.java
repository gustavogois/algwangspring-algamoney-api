package study.gois.algamoneyapi.repository.lancamento;

import study.gois.algamoneyapi.model.Lancamento;
import study.gois.algamoneyapi.repository.filter.LancamentoFilter;

import java.util.List;

public interface LancamentoRepositoryQuery {
    public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
}
