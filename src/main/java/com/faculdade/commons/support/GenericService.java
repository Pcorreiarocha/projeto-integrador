package com.faculdade.commons.support;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @param <E> ENTITY (ENTIDADE DO BANCO)
 * @param <K> KEY OF ENTITY (ID DA ENTIDADE)
 */
public abstract class GenericService<E, K> {

    public abstract JpaRepository<E, K> getRepository();

    /**
     * Realiza a busca de um registro por id
     *
     * @param id
     * @return O Registro pesquisado
     */
    public Optional<E> findById( K id ) {
        return getRepository().findById( verifyId( id ) );
    }

    /**
     * Realiza a busca de todos os registros de forma paginada
     *
     * @param pageable
     * @return A lista de registros
     */
    public Page<E> findAll( Pageable pageable ) {
        return getRepository().findAll( pageable );
    }

    /**
     * Realiza a pesquisa por igualdade ignorando case
     *
     * @return
     */
    protected ExampleMatcher getExampleMatcher() {
        return ExampleMatcher.matchingAll()
                             .withIgnoreCase()
                             .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );
    }

    /**
     * Realiza a pesquisa por filtro de forma paginada
     *
     * @param input
     * @param pageable
     * @return
     */
    public Page<E> findByFilter( E input, Pageable pageable ) {
        Example<E> example = Example.of( input, getExampleMatcher() );
        return getRepository().findAll( example, pageable );
    }

    /**
     * Persiste o registro recebido
     *
     * @param entity
     * @return A Entidade persistida
     */
    @Transactional
    public E save( E entity ) {
        E saved = getRepository().save( preSave( entity ) );
        return postSave( saved );
    }

    /**
     * Realiza o "delete" do registro
     *
     * @param entity
     */
    public void delete( E entity ) {
        getRepository().delete( entity );
    }

    /**
     * Realiza alguma ação antes de um "save" ser executado.
     *
     * @param entity
     * @return A Entidade a ser persistida
     */
    public E preSave( E entity ) {
        return entity;
    }

    /**
     * Realiza alguma ação após a execução do "save".
     *
     * @param entity
     * @return A Entidade após ser persistida
     */
    public E postSave( E entity ) {
        return entity;
    }

    /**
     * @param id
     * @return Se é um id válido para a entidade (CWE-566)
     */
    public abstract K verifyId( K id );

}
