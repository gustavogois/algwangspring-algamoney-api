package study.gois.algamoneyapi.service;

import org.springframework.stereotype.Service;
import study.gois.algamoneyapi.model.Lancamento;
import study.gois.algamoneyapi.repository.LancamentoRepository;

@Service
public class LancamentoService extends AlgamoneyService<Lancamento> {

    public LancamentoService(LancamentoRepository repository) {
        super(repository);
    }
}
