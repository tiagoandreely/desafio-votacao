package com.db.desafiovotacao.exception;

public class MandatoryFieldException extends RuntimeException
{
    public MandatoryFieldException( String field )
    {
        super("O campo "+ field + " é obrigatório!");
    }
}
