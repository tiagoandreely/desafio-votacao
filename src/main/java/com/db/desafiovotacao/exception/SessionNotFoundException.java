package com.db.desafiovotacao.exception;

public class SessionNotFoundException extends RuntimeException
{
    public SessionNotFoundException()
    {
        super("Sessão não encontrada!");
    }
}
