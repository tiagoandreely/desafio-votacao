package com.db.desafiovotacao.exception;

public class SessionClosedException extends RuntimeException
{
    public SessionClosedException()
    {
        super("Sessão está encerrada!");
    }
}
