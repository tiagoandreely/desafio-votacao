package com.db.desafiovotacao.exception;

public class AssociateNotFoundException extends RuntimeException
{
    public AssociateNotFoundException()
    {
        super("Associado não encontrado!");
    }
}
