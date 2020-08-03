package study.gois.algamoneyapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import study.gois.algamoneyapi.model.AlgamoneyEntity;

public class AlgamoneyService<T> {

    protected JpaRepository repository;

    public AlgamoneyService(JpaRepository repository) {
        this.repository = repository;
    }

    public T atualizar(Long codigo, T type) throws Throwable {
        T entitySaved = (T) repository.findById(codigo)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        BeanUtils.copyProperties(type, entitySaved, AlgamoneyEntity.CODIGO);
        return (T) repository.save(entitySaved);
    }
}
