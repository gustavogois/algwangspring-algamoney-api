package study.gois.algamoneyapi.service;

import org.springframework.stereotype.Service;
import study.gois.algamoneyapi.model.Lancamento;
import study.gois.algamoneyapi.model.Pessoa;
import study.gois.algamoneyapi.repository.LancamentoRepository;
import study.gois.algamoneyapi.repository.PessoaRepository;
import study.gois.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService extends AlgamoneyService<Lancamento> {

    private final LancamentoRepository lancamentoRepository;
    private final PessoaRepository pessoaRepository;

    public LancamentoService(LancamentoRepository lancamentoRepository, PessoaRepository pessoaRepository) {
        super(lancamentoRepository);
        this.lancamentoRepository = lancamentoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public Lancamento salvar(Lancamento lancamento) {
        Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo())
                .orElseThrow(() -> new PessoaInexistenteOuInativaException());
        if (pessoa.isInativo()) {
            throw new PessoaInexistenteOuInativaException();
        }

        return lancamentoRepository.save(lancamento);
    }
}
