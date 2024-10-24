package com.faculdade.commons.support;

import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Supplier;

public abstract class GenericController<M, N, E, K> implements IGenericController<M, N, K> {

    protected final ModelMapper mapper;
    protected final MessageSource messageSource;
    private final   Class<N>      dtoResponseType;
    private final   Class<E>      entityType;

    protected GenericController( ModelMapper mapper, MessageSource messageSource ) {
        this.mapper = mapper;
        this.messageSource = messageSource;
        this.dtoResponseType = (Class<N>) getParameterizedTypeByPosition( 1 );
        this.entityType = (Class<E>) getParameterizedTypeByPosition( 2 );
    }

    public static PageRequest getPageRequest( int page, int size, String[] sort ) {
        return PageRequest.of( page, size, Objects.requireNonNull( getSort( sort ) ) );
    }

    private static Sort getSort( String[] sortParameter ) {
        List<Sort.Order> result = new ArrayList<>();
        if( sortParameter == null ) {
            result.add( Sort.Order.by( "id" ) );
        }
        else {
            for( String param : sortParameter ) {
                String[] split = param.split( "," );
                if( split.length == 1 ) {
                    result.add( Sort.Order.asc( split[0] ) );
                }
                else {
                    if( "desc".equalsIgnoreCase( split[1] ) ) {
                        result.add( Sort.Order.desc( split[0] ) );
                    }
                    else {
                        result.add( Sort.Order.asc( split[0] ) );
                    }
                }
            }
        }
        return Sort.by( result.toArray( new Sort.Order[result.size()] ) );
    }

    protected abstract GenericService<E, K> getService();

    @GetMapping
    public ResponseEntity<PageDto<N>> findAll( int page, int size, String[] sort ) {
        return ResponseEntity.ok( entityToDto( getService().findAll( getPageRequest( page, size, sort ) ) ) );
    }

    @GetMapping( value = "/filter" )
    public ResponseEntity<PageDto<N>> findAllByFilter( M input, int page, int size, String[] sort ) {
        return ResponseEntity.ok(
                entityToDto( getService().findByFilter( dtoToEntity( input ), getPageRequest( page, size, sort ) ) ) );
    }

    @GetMapping( value = "{id}" )
    public ResponseEntity<Response<N>> findById( @PathVariable K id ) {
        return getService().findById( id )
                           .map( o -> ResponseEntity.ok( entityToDto( o ) ) )
                           .orElseThrow( resourceNotFoundResponseStatusExceptionSupplier() );
    }

    @PostMapping
    public ResponseEntity<Response<N>> save( @Validated @RequestBody M input ) {
        E entity = getService().save( dtoToEntity( input ) );
        return new ResponseEntity<>( entityToDto( entity ), HttpStatus.CREATED );
    }

    @PutMapping( value = "{id}" )
    public ResponseEntity<Response<N>> update( @PathVariable K id, M input ) {
        return getService().findById( id )
                           .map( o -> {
                               mapper.map( input, o );
                               return ResponseEntity.ok( entityToDto( getService().save( o ) ) );
                           } )
                           .orElseThrow( resourceNotFoundResponseStatusExceptionSupplier() );
    }

    @DeleteMapping( value = "{id}" )
    public ResponseEntity<Object> delete( @PathVariable K id ) {
        return getService().findById( id )
                           .map( o -> {
                               getService().delete( o );
                               return ResponseEntity.noContent()
                                                    .build();
                           } )
                           .orElseThrow( resourceNotFoundResponseStatusExceptionSupplier() );
    }

    protected Response<N> entityToDto( E entityObject ) {
        return (Response<N>) Response.builder()
                                     .code( 0 )
                                     .success( Boolean.TRUE )
                                     .message( "OK" )
                                     .data( mapper.map( entityObject, dtoResponseType ) )
                                     .build();
    }

    protected PageDto<N> entityToDto( Page<E> pageEntity ) {
        PageDto<N> result     = mapper.map( pageEntity, PageDto.class );
        List<N>    newContent = new ArrayList<>();
        for( Object entity : result.getContent() ) {
            newContent.add( mapper.map( entity, dtoResponseType ) );
        }
        result.setContent( newContent );
        return result;
    }

    protected E dtoToEntity( M dtoObject ) {
        return mapper.map( dtoObject, entityType );
    }

    private Class<?> getParameterizedTypeByPosition( int position ) {
        return ( (Class<?>) ( ( ParameterizedType ) getClass().getGenericSuperclass() ).getActualTypeArguments()[position] );
    }

    private Supplier<ResponseStatusException> resourceNotFoundResponseStatusExceptionSupplier() {
        return () -> new ResponseStatusException( HttpStatus.NOT_FOUND, getMessage( "error.404" ) );
    }

    protected String getMessage( String code, Object... args ) {
        return messageSource.getMessage( code, args, Locale.getDefault() );
    }

}
