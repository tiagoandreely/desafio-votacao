package com.db.desafiovotacao.exception;

public class SessionOpenException extends RuntimeException
{
    public SessionOpenException()
    {
        super("Já existe uma sessão aberta para a pauta!");
    }
}
