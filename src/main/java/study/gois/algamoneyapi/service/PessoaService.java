package study.gois.algamoneyapi.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import study.gois.algamoneyapi.model.Pessoa;
import study.gois.algamoneyapi.repository.PessoaRepository;

@Service
public class PessoaService extends AlgamoneyService<Pessoa> {

    public PessoaService(PessoaRepository repository) {
        super(repository);
    }

    public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) throws Throwable {
        Pessoa pessoaSalva = (Pessoa) repository.findById(codigo)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        pessoaSalva.setAtivo(ativo);
        repository.save(pessoaSalva);
    }
}
