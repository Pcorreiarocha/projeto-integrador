package com.faculdade.commons.exceptions;

import lombok.Getter;

@Getter
public class NegocioException extends RuntimeException {
    
    private final Integer code;

    public NegocioException() {
        super();
        this.code = -1;
    }

    public NegocioException( String message ) {
        super( message );
        this.code = -1;
    }

    public NegocioException( String message, Integer code ) {
        super( message );
        this.code = code;
    }

    public NegocioException( String message, Throwable cause ) {
        super( message, cause );
        this.code = -1;
    }

    public NegocioException( String message, Integer code, Throwable cause ) {
        super( message, cause );
        this.code = code;
    }

    public NegocioException( Throwable cause ) {
        super( cause );
        this.code = -1;
    }

    protected NegocioException( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
        super( message, cause, enableSuppression, writableStackTrace );
        this.code = -1;
    }

    protected NegocioException( String message, Integer code, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace ) {
        super( message, cause, enableSuppression, writableStackTrace );
        this.code = code;
    }

}
