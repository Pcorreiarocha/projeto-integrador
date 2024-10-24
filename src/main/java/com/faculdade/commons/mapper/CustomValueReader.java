package com.faculdade.commons.mapper;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.modelmapper.spi.ValueReader;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class CustomValueReader implements ValueReader<Object> {

    @Override
    public Object get( Object source, String memberName ) {
        try {
            Field field = getField( memberName, source.getClass() );
            if( field != null ) {
                org.springframework.util.ReflectionUtils.makeAccessible( field );
                return field.get( source );
            }
        }
        catch( Exception ex ) {
            log.error( "Erro ao tentar recuperar atributo da classe.", ex );
        }
        return null;
    }

    @Override
    public Member<Object> getMember( Object source, String memberName ) {
        final Object value      = get( source, memberName );
        Class<?>     memberType = value != null ? value.getClass() : Object.class;
        return new Member<>( memberType ) {

            @Override
            public Object get( Object source, String memberName ) {
                return CustomValueReader.this.get( source, memberName );
            }
        };
    }

    @Override
    @SuppressWarnings( { "unchecked", "rawtypes" } )
    public Collection<String> memberNames( Object source ) {
        Set<String> properties = new HashSet<>();
        for( Field field : ReflectionUtils.getAllFields( source.getClass() ) ) {
            org.springframework.util.ReflectionUtils.makeAccessible( field );//needed for accessing field using reflection
            boolean valid      = true;
            Object  fieldValue = null;
            try {
                fieldValue = field.get( source );
            }
            catch( Exception ex ) {
                valid = false;
            }
            boolean isInited = valid && Hibernate.isInitialized( fieldValue );
            if( isInited ) {
                properties.add( field.getName() );
            }
        }
        return properties;
    }

    private Field getField( String memberName, Class<?> type ) {
        Set<Field> fieldSet = ReflectionUtils.getAllFields( type );
        return fieldSet.stream()
                       .filter( field -> memberName.equals( field.getName() ) )
                       .findFirst()
                       .orElse( null );
    }

}
