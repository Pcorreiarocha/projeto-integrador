package com.faculdade.commons.support;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @param <M> DTO (REQUISIÇÃO DAS OPERAÇÕES DE CONTROLLER)
 * @param <N> DTO (RETORNO DAS OPERAÇÕES DE CONTROLLER)
 * @param <E> ENTITY (ENTIDADE DO BANCO)
 * @param <K> KEY OF ENTITY (ID DA ENTIDADE)
 */
public interface IGenericController<M, N, K> {

    @PostMapping()
    ResponseEntity<Response<N>> save( @Validated @RequestBody M input );

    @GetMapping()
    ResponseEntity<PageDto<N>> findAll( @RequestParam( defaultValue = "0" ) @Parameter( name = "page",
            description = "Pagina de resultados que desejar obter." ) int page,
            @RequestParam( defaultValue = "10" ) @Parameter( name = "size",
                    description = "Número de registros por página." ) int size,
            @RequestParam( defaultValue = "id", required = false ) @Parameter( name = "sort",
                    description =
                            "Ordenação no seguinte formato: propriedade,(asc|desc)." +
                                    "<br/>Ordenação padrão é ASC." +
                                    "<br/>Ordenação múltipla é permitida." +
                                    "\"<br/>Exemplo: id,desc" ) String[] sort );

    @GetMapping( value = "/filter" )
    ResponseEntity<PageDto<N>> findAllByFilter( M input, @RequestParam( defaultValue = "0" ) @Parameter( name = "page",
            description = "Pagina de resultados que desejar obter." ) int page,
            @RequestParam( defaultValue = "10" ) @Parameter( name = "size",
                    description = "Número de registros por página." ) int size,
            @RequestParam( defaultValue = "id",
                    required = false ) @Parameter( name = "sort",
                    description =
                            "Ordenação no seguinte formato: propriedade(asc|desc)." +
                                    "<br/>Ordenação padrão é ASC." +
                                    "<br/>Ordenação múltipla é permitida." +
                                    "\"<br/>Exemplo: id,desc" ) String[] sort );

    @GetMapping( value = "{id}" )
    ResponseEntity<Response<N>> findById( @PathVariable K id );

    @PutMapping( value = "{id}" )
    ResponseEntity<Response<N>> update( @PathVariable K id, @Validated @RequestBody M input );

    @DeleteMapping( value = "{id}" )
    ResponseEntity<Object> delete( @PathVariable K id );

}
